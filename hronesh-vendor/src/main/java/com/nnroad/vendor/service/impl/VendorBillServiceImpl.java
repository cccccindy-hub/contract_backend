package com.nnroad.vendor.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nnroad.client.domain.SysClient;
import com.nnroad.client.service.ISysClientService;
import com.nnroad.common.exception.Asserts;
import com.nnroad.common.exception.BusinessException;
import com.nnroad.common.utils.SecurityUtils;
import com.nnroad.common.utils.ValidationUtils;
import com.nnroad.employee.domain.SysEmployee;
import com.nnroad.employee.service.ISysEmployeeService;
import com.nnroad.vendor.domain.VendorBill;
import com.nnroad.vendor.mapper.VendorBillMapper;
import com.nnroad.vendor.model.form.VendorQueryForm;
import com.nnroad.vendor.service.IVendorBillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class VendorBillServiceImpl extends ServiceImpl<VendorBillMapper, VendorBill> implements IVendorBillService {

    private final ISysEmployeeService employeeService;

    private final ISysClientService clientService;

    @Override
    public List<VendorBill> queryPage(VendorQueryForm form) {
        return buildWrapper(form)
                .orderByDesc(VendorBill::getId)
                .list();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importData(MultipartFile file) {
        List<VendorBill> bills;
        try {
            bills = readFiles(file.getInputStream());
        } catch (Exception e) {
            log.error("import vendor_bill error:", e);
            throw new BusinessException("Import Read Error!");
        }
        Asserts.notEmpty(bills, "Import Data empty!");
        //数据校验
        String errorMsg = ValidationUtils.validate(bills, false);
        Asserts.isTrue(StrUtil.isBlank(errorMsg), errorMsg);
        //填充
        bills.forEach(bill -> {
            //客户数据
            List<SysClient> sysClients = clientService.selectBasicByClientName(bill.getClientName());
            Asserts.isTrue(CollUtil.isNotEmpty(sysClients) && sysClients.size() == 1,
                    String.format("Client [%s] Data error!", bill.getClientName()));
            bill.setClientCode(sysClients.get(0).getCompanyCode());
            //员工数据
            supplementEEInfo(bill);
        });

        saveBatch(bills, 500);
    }

    private List<VendorBill> readFiles(InputStream inputStream) {
        List<VendorBill> bills = new ArrayList<>();
        EasyExcel.read(inputStream, VendorBill.class, new PageReadListener<VendorBill>(accList -> {
            accList.forEach(acc -> {
                acc.setId(null);
                acc.setCreateBy(SecurityUtils.getUsername());
                acc.setCreateTime(new Date());
            });
            bills.addAll(accList);
        })).sheet().sheetName("异地供应商账单").doRead();

        return bills;
    }

    @Override
    public void add(VendorBill vendorBill) {
        //查询员工信息
        supplementEEInfo(vendorBill);
        save(vendorBill);
    }

    @Override
    public void modify(VendorBill vendorBill) {
        supplementEEInfo(vendorBill);
        updateById(vendorBill);
    }

    public void supplementEEInfo(VendorBill vendorBill) {
        Asserts.notBlank(vendorBill.getClientCode(), "Please Select Client");
        SysEmployee employee = employeeService.selectClientEE(vendorBill.getClientCode(), vendorBill.getEeName(), vendorBill.getIdNumber());
        Asserts.notNull(employee, String.format("Employee[%s] not exist", vendorBill.getEeName()));

        vendorBill.setEeCode(employee.getEmployeeCode());
    }

    private LambdaQueryChainWrapper<VendorBill> buildWrapper(VendorQueryForm form) {
        return lambdaQuery()
                .like(StrUtil.isNotBlank(form.getVendorName()), VendorBill::getVendorName, form.getVendorName())
                .eq(StrUtil.isNotBlank(form.getClientCode()), VendorBill::getClientCode, form.getClientCode())
                .between(StrUtil.isAllNotBlank(form.getBeginPeriod(), form.getEndPeriod()), VendorBill::getForPeriod,
                        form.getBeginPeriod(), form.getEndPeriod());
    }
}
