package com.nnroad.finance.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.nnroad.common.core.domain.entity.SysDictData;
import com.nnroad.common.exception.Asserts;
import com.nnroad.common.exception.BusinessException;
import com.nnroad.common.exception.ServiceException;
import com.nnroad.common.utils.CommonUtil;
import com.nnroad.common.utils.SecurityUtils;
import com.nnroad.common.utils.ValidationUtils;
import com.nnroad.common.utils.poi.ExcelUtil;
import com.nnroad.finance.constants.ConstantKey;
import com.nnroad.finance.domain.HroneAccount;
import com.nnroad.finance.mapper.HroneAccountMapper;
import com.nnroad.finance.service.HroneAccountService;
import com.nnroad.system.service.ISysDictDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class HroneAccountServiceImpl extends ServiceImpl<HroneAccountMapper, HroneAccount> implements HroneAccountService {

    private final ISysDictDataService sysDictDataService;

    private final HroneAccountMapper hroneAccountMapper;

    @Override
    public List<HroneAccount> queryPage(HroneAccount account) {
        LambdaQueryChainWrapper<HroneAccount> wrapper = buildQueryWrapper(account);
        wrapper.orderByDesc(HroneAccount::getId);

        return wrapper.list();
    }

    @Override
    public Map<String, List<SysDictData>> dictList() {
        //dictData
        return sysDictDataService.selectByDictTypes(Arrays.asList("bank_account", "bank_name", "service_name", "currency"))
                .stream().collect(Collectors.groupingBy(SysDictData::getDictType));
    }

    @Override
    public HroneAccount detail(Long accountId) {
        return lambdaQuery()
                .eq(HroneAccount::getId, accountId)
                .one();
    }

    @Override
    public void modify(HroneAccount hroneAccount) {
        hroneAccount.initAuthor(true);
        hroneAccountMapper.updateById(hroneAccount);
    }

    @Override
    public void add(HroneAccount account) {
        account.initAuthor(false);
        hroneAccountMapper.insert(account);
    }

    @Override
    public void delById(Long accId) {
        hroneAccountMapper.deleteById(accId);
    }

    @Override
    public void export(HroneAccount hroneAccount, HttpServletResponse response) {
        Integer total = buildQueryWrapper(hroneAccount).count();
        if (total == 0) {
            return;
        }
        int pageSize = 500;
        int totalPage = (int) Math.ceil(((double) total) / pageSize);
        String fileName = String.format(ConstantKey.ACCOUNT_EXPORT_NAME, DateUtil.format(new Date(), "yyyyMMddHHmmss"));
        try (ExcelWriter excelWriter = EasyExcel.write(ExcelUtil.getOutputStream(fileName, response), HroneAccount.class).build()) {
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("sheet").excludeColumnFieldNames(ConstantKey.ENTRY_IGNORE_FILES).build();
            for (int i = 1; i <= totalPage; i++) {
                try {
                    PageHelper.startPage(i, pageSize);
                    List<HroneAccount> hroneAccounts = queryPage(hroneAccount);
                    //写入本地文件
                    excelWriter.write(hroneAccounts, writeSheet);
                } finally {
                    PageHelper.clearPage();
                }
            }
            excelWriter.finish();
        } catch (Exception e) {
            log.error("上海账套导出失败：", e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void downloadTemp(HttpServletResponse response) {
        String fileName = String.format(ConstantKey.ACCOUNT_EXPORT_NAME, DateUtil.format(new Date(), "yyyyMMddHHmmss"));
        try (ExcelWriter excelWriter = EasyExcel.write(ExcelUtil.getOutputStream(fileName, response), HroneAccount.class).build()) {
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("sheet").excludeColumnFieldNames(ConstantKey.ENTRY_IGNORE_FILES).build();
            //写入本地文件
            excelWriter.write(new ArrayList<>(), writeSheet);
            excelWriter.finish();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importAccount(MultipartFile file) {
        try {
            List<HroneAccount> accounts = readFiles(file.getInputStream());
            Asserts.notEmpty(accounts, "Please select import Data!");

            String errorMsg = ValidationUtils.validate(accounts, false);
            Asserts.isTrue(StrUtil.isBlank(errorMsg), errorMsg);
            accounts.forEach(account -> account.initAuthor(false));

            saveBatch(accounts);
        } catch (IOException e) {
            log.error("读取Account文件异常：", e);
            throw new BusinessException("Import fail");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void accountSelectImport(List<HroneAccount> accounts, String type) {
        Asserts.notEmpty(accounts, "Please select import Data!");
        accounts.forEach(account -> account.initAuthor(false));

        saveBatch(accounts, 100);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delBatchByIds(List<Long> ids) {
        Asserts.notEmpty(ids, "Please select delete Data!");

        baseMapper.deleteBatchIds(ids);
    }

    private List<HroneAccount> readFiles(InputStream inputStream) {
        List<HroneAccount> accounts = new ArrayList<>();
        EasyExcel.read(inputStream, HroneAccount.class, new PageReadListener<HroneAccount>(accList -> {
            accList.forEach(acc -> acc.setId(null));
            accounts.addAll(accList);
        })).sheet().doRead();

        return accounts;
    }

    private LambdaQueryChainWrapper<HroneAccount> buildQueryWrapper(HroneAccount account) {
        Map<String, Object> params = Optional.ofNullable(account.getParams()).orElse(new HashMap<>());
        String[] dateArr = Optional.ofNullable(params.get("date")).map(date -> ((String)date).split(",")).orElse(null);
        String[] forPeriodArr = Optional.ofNullable(params.get("forPeriod")).map(date -> ((String)date).split(",")).orElse(null);

        LambdaQueryChainWrapper<HroneAccount> wrapper = lambdaQuery()
                .eq(StrUtil.isNotBlank(account.getCheckType()), HroneAccount::getCheckType, account.getCheckType())
                .eq(StrUtil.isNotBlank(account.getClientCode()), HroneAccount::getClientCode, account.getClientCode())
                .like(StrUtil.isNotBlank(account.getClientName()), HroneAccount::getClientName, account.getClientName())
                .eq(StrUtil.isNotBlank(account.getBank()), HroneAccount::getBank, account.getBank())
                .eq(StrUtil.isNotBlank(account.getBankAccount()), HroneAccount::getBankAccount, account.getBankAccount())
                .eq(StrUtil.isNotBlank(account.getWantedCurrency()), HroneAccount::getWantedCurrency, account.getWantedCurrency())
                .eq(StrUtil.isNotBlank(account.getInBankCurrency()), HroneAccount::getInBankCurrency, account.getInBankCurrency());
        if (dateArr != null && dateArr.length == 2) {
            wrapper.between(HroneAccount::getDate, dateArr[0], dateArr[1]);
        }
        if (forPeriodArr != null && forPeriodArr.length == 2) {
            wrapper.between(HroneAccount::getForPeriod, forPeriodArr[0], forPeriodArr[1]);
        }

        return wrapper;
    }
}
