package com.nnroad.finance.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nnroad.common.core.domain.entity.SysUser;
import com.nnroad.common.exception.BusinessException;
import com.nnroad.common.exception.ServiceException;
import com.nnroad.common.utils.SecurityUtils;
import com.nnroad.common.utils.poi.ExcelUtil;
import com.nnroad.employee.service.ISysEmployeeService;
import com.nnroad.finance.constants.ConstantKey;
import com.nnroad.finance.domain.DepositClient;
import com.nnroad.finance.domain.HroneAccount;
import com.nnroad.finance.mapper.deposit.DepositClientDetailMapper;
import com.nnroad.finance.mapper.deposit.DepositClientMapper;
import com.nnroad.finance.mapper.deposit.DepositEmployeeMapper;
import com.nnroad.finance.model.dto.DepositClientDetailDto;
import com.nnroad.finance.model.form.DepositClientCondition;
import com.nnroad.finance.service.IDepositClientDetailService;
import com.nnroad.finance.service.IDepositClientService;
import com.nnroad.finance.service.IDepositEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 客户押金业务层处理
 *
 * @author Lucio
 * @date 2021-09-13
 */
@Service
public class DepositClientServiceImpl extends ServiceImpl<DepositClientMapper, DepositClient> implements IDepositClientService {

    @Autowired
    private DepositClientMapper depositClientMapper;

    @Autowired
    private DepositClientDetailMapper depositClientDetailMapper;

    @Autowired
    private DepositEmployeeMapper depositEmployeeMapper;

    @Autowired
    private IDepositEmployeeService depositEmployeeService;

    @Autowired
    private IDepositClientDetailService depositClientDetailService;

    @Autowired
    private ISysEmployeeService employeeService;


    /**
     * 获得客户押金合计
     *
     * @return 客户押金合计
     */
    public BigDecimal getDepositTotal() {
        return depositClientMapper.getDepositTotal();
    }

    /**
     * 获得客户押金信息列表
     *
     * @param condition 客户押金条件信息
     * @return 客户押金信息列表
     */
    public List<DepositClient> getDepositClientList(DepositClientCondition condition) {
        return depositClientMapper.selectDepositClientList(condition);
    }

    /**
     * 刷新客户剩余押金
     *
     * @param clientCode 客户编号
     */
    public void refreshDeposit(String clientCode) {
        /*SysUser user = SecurityUtils.getLoginUser().getUser();

        DepositClient dto = depositClientMapper.selectDepositClient(clientCode);
        if (dto == null) {
            dto = new DepositClient();
            dto.setClientCode(clientCode);
            dto.setAmount(BigDecimal.ZERO);
            dto.setCreateBy(user.getLoginName());
            dto.setUpdateBy(user.getLoginName());

            depositClientMapper.insertDepositClient(dto);
        }
        dto = new DepositClient();
        dto.setClientCode(clientCode);
        dto.setUpdateBy(user.getLoginName());
        depositClientMapper.refreshDeposit(dto);*/
    }

    @Override
    public String importData(List<DepositClientDetailDto> list, boolean updateSupport, String operName) {
        if (CollUtil.isEmpty(list)) {
            throw new BusinessException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        List<DepositClientDetailDto> existList = selectDepositClientDetailDto(new DepositClientDetailDto());
        boolean flag = false;
        for (DepositClientDetailDto importData : list) {
            try {
                if (importData.getClientCode().isEmpty() || ("").equals(importData.getClientCode()) || importData.getDate().toString().isEmpty()) {
                    flag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、数据 " + importData.getEmployeeCode() + "-" + importData.getClientCode() + "-" + importData.getDate() + "数据不完整");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、数据 " + importData.getEmployeeCode() + "-" + importData.getClientCode() + "-" + importData.getDate() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }

        }
        if (!flag) {
            for (DepositClientDetailDto importData : list) {
                try {
                    // 验证是否存在
                    boolean ClientFlag = false;
                    for (DepositClientDetailDto entry : existList) {
                        if (importData.getEmployeeCode().isEmpty() || ("").equals(importData.getEmployeeCode())) {
                            if (entry.getClientCode().equals(importData.getClientCode()) && entry.getDate().equals(importData.getDate())) {
                                importData.setId(entry.getId());
                                ClientFlag = true;
                                break;
                            }
                        } else {
                            if (entry.getClientCode().equals(importData.getClientCode()) && entry.getDate().equals(importData.getDate()) && entry.getEmployeeCode().equals(importData.getEmployeeCode())) {
                                importData.setId(entry.getId());
                                ClientFlag = true;
                                break;
                            }
                        }
                    }
                    if (!ClientFlag) {
                        importData.initAuthor(false);
                        addDepositClientDetail(importData);
                        successNum++;
                        successMsg.append("<br/>" + successNum + "、数据 " + importData.getEmployeeCode() + "-" + importData.getClientCode() + "-" + importData.getDate() + " 导入成功");
                    } else if (updateSupport) {
                        importData.initAuthor(true);
                        updateDepositClientDetail(importData);
                        successNum++;
                        successMsg.append("<br/>" + successNum + "、数据 " + importData.getEmployeeCode() + "-" + importData.getClientCode() + "-" + importData.getDate() + " 更新成功");
                    } else {
                        failureNum++;
                        failureMsg.append("<br/>" + failureNum + "、数据 " + importData.getEmployeeCode() + "-" + importData.getClientCode() + "-" + importData.getDate() + " 已存在");
                    }
                } catch (Exception e) {
                    failureNum++;
                    String msg = "<br/>" + failureNum + "、数据 " + importData.getEmployeeCode() + "-" + importData.getClientCode() + "-" + importData.getDate() + " 导入失败：";
                    failureMsg.append(msg + e.getMessage());
                }
            }
        }
        if (flag) {
            failureMsg.insert(0, "很抱歉，导入失败！数据不完整!");
            throw new BusinessException(failureMsg.toString());
        } else if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public void export(DepositClientCondition condition, HttpServletResponse response) {
        List<DepositClient> depositList = getDepositClientList(condition);
        String fileName = String.format(ConstantKey.DEPOSIT_CLIENT_EXPORT_NAME, DateUtil.format(new Date(), "yyyyMMddHHmmss"));
        try (ExcelWriter excelWriter = EasyExcel.write(ExcelUtil.getOutputStream(fileName, response), DepositClient.class).build()) {
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("sheet").excludeColumnFieldNames(ConstantKey.ENTRY_IGNORE_FILES).build();
            //写入本地文件
            excelWriter.write(depositList, writeSheet);
            excelWriter.finish();
        } catch (Exception e) {
            log.error("上海账套导出失败：", e);
            throw new ServiceException(e.getMessage());
        }
    }

    private void updateDepositClientDetail(DepositClientDetailDto depositClientDetailDto) {
        depositClientDetailDto.initAuthor(true);
        depositClientDetailService.saveDepositClientDetail(depositClientDetailDto);
    }

    public List<DepositClientDetailDto> selectDepositClientDetailDto(DepositClientDetailDto depositClientDetailDto) {
        return depositClientDetailMapper.selectDepositClientDetailDto(depositClientDetailDto);
    }

    /**
     * 添加客户押金详细
     *
     * @param depositClientDetailDto 客户押金详细
     */
    public void addDepositClientDetail(DepositClientDetailDto depositClientDetailDto) {
        // 区别自动拉取的数据
        depositClientDetailDto.setDelFlag(0);
        depositClientDetailDto.initAuthor(false);
        // 创建押金详细
        depositClientDetailService.createDepositClientDetail(depositClientDetailDto);

        //// 计算雇员剩余押金
        //if (StringUtils.isNotEmpty(depositClientDetailDto.getEmployeeCode())) {
        //    depositEmployeeService.refreshDeposit(depositClientDetailDto.getEmployeeCode());
        //}
        //
        //// 计算客户剩余押金
        //if (StringUtils.isNotEmpty(depositClientDetailDto.getClientCode())) {
        //    this.refreshDeposit(depositClientDetailDto.getClientCode());
        //}
    }

    /**
     * 更新客户押金详细
     *
     * @param depositClientDetailDto 客户押金详细
     */
    public void editDepositClientDetail(DepositClientDetailDto depositClientDetailDto) {

        depositClientDetailDto.initAuthor(true);

        // 编辑押金详细
        depositClientDetailService.saveDepositClientDetail(depositClientDetailDto);

        //// 计算雇员剩余押金
        //if (StringUtils.isNotEmpty(depositClientDetailDto.getEmployeeCode())) {
        //    depositEmployeeService.refreshDeposit(depositClientDetailDto.getEmployeeCode());
        //}
        //
        //// 计算客户剩余押金
        //DepositClientDetailDto dto = depositClientDetailService.getDepositClientDetailById(depositClientDetailDto.getId());
        //if (StringUtils.isNotEmpty(dto.getClientCode())) {
        //    this.refreshDeposit(dto.getClientCode());
        //}
    }

    /**
     * 移除客户押金详细
     *
     * @param id 客户押金详细标识
     */
    public void removeDepositClientDetail(Long id) {

        // 删除押金详细
        depositClientDetailService.deleteDepositClientDetailById(id);

        //// 计算雇员剩余押金
        //if (StringUtils.isNotEmpty(depositClientDetailDto.getEmployeeCode())) {
        //    depositEmployeeService.refreshDeposit(depositClientDetailDto.getEmployeeCode());
        //}
        //
        //// 计算客户剩余押金
        //if (StringUtils.isNotEmpty(depositClientDetailDto.getClientCode())) {
        //    this.refreshDeposit(depositClientDetailDto.getClientCode());
        //}
    }

    /**
     * 结算客户账单押金
     *
     * @param clientCode 客户编号
     */
    public void settleDepositClient(String clientCode) {
        /*SysUser user = SecurityUtils.getLoginUser().getUser();

        DepositClient dto = depositClientMapper.selectDepositClient(clientCode);
        String monthMin = "190001";
        String monthMax = DateUtils.dateTimeNow("YYYYMM");
        if (dto != null && StrUtil.isNotBlank(dto.getMonthSettle())) {
            monthMin = DateUtils.parseDateToStr("YYYYMM", DateUtils.parseDate(dto.getMonthSettle()));
        }
        // 删除结算完的押金数据
        DepositClientDetailCondition condition = new DepositClientDetailCondition();
        condition.setClientCode(clientCode);
        condition.setMonthMin(monthMin);
        condition.setMonthMax(monthMax);
        depositClientDetailService.deleteDepositClientDetailBySettle(condition);
        // 查询所有client员工
        ClientEmployeeDto clientEmployeeDto = new ClientEmployeeDto();
        clientEmployeeDto.setClientCode(clientCode);
        List<ClientEmployeeDto> employeeList = clientEmployeeMapper.selectClientEmployeeList(clientEmployeeDto);
        SysEmployee employee = new SysEmployee();
        employeeService.selectSysEmployeeList()

        for (ClientEmployeeDto employee : employeeList) {
            // 从payroll数据库获取最新押金数据
            condition = new DepositClientDetailCondition();
            condition.setEmployeeCode(employee.getCode());
            condition.setMonthMin(monthMin);
            condition.setMonthMax(monthMax);
            List<DepositClientDetailDto> depositList = depositClientDetailService.getDepositClientDetailListFromPayroll(condition);

            for (DepositClientDetailDto depositClientDetailDto : depositList) {
                depositClientDetailDto.setClientCode(employee.getClientCode());
                if (depositClientDetailDto.getAmount().doubleValue() >= 0) {
                    depositClientDetailDto.setType(DBConstants.DEPOSIT_DETAIL_TYPE_COLLECT_SETTLE);
                } else {
                    depositClientDetailDto.setType(DBConstants.DEPOSIT_DETAIL_TYPE_BACK_SETTLE);
                }
                depositClientDetailDto.setCreateBy(user.getLoginName());
                depositClientDetailDto.setUpdateBy(user.getLoginName());

                // 创建押金详细
                depositClientDetailService.createDepositClientDetail(depositClientDetailDto);
            }

            // 计算雇员剩余押金
            depositEmployeeService.refreshDeposit(employee.getCode());

            // 计算客户剩余押金
            this.refreshDeposit(employee.getClientCode());
        }
        // 查询不到client员工为香港客户，需从financial_status_hk中查询押金
        if (employeeList.size() == 0) {
            // 从financial_status_hk数据库获取最新押金数据
            condition = new DepositClientDetailCondition();
            condition.setClientCode(clientCode);
            List<DepositClientDetailDto> depositDetailList = depositClientDetailMapper.selectClientDepositDetailFromHk(condition);
            // 创建押金详细
            for (DepositClientDetailDto depositClientDetailDto : depositDetailList) {
                depositClientDetailDto.setClientCode(clientCode);
                depositClientDetailDto.setEmployeeCode(clientCode);
                if (depositClientDetailDto.getAmount().doubleValue() >= 0) {
                    depositClientDetailDto.setType(DBConstants.DEPOSIT_DETAIL_TYPE_COLLECT_SETTLE);
                } else {
                    depositClientDetailDto.setType(DBConstants.DEPOSIT_DETAIL_TYPE_BACK_SETTLE);
                }
                depositClientDetailDto.setCreateBy(user.getLoginName());
                depositClientDetailDto.setUpdateBy(user.getLoginName());

                // 创建押金详细
                depositClientDetailMapper.insertDepositClientDetail(depositClientDetailDto);
            }
            // 计算雇员剩余押金
            DepositEmployeeDto depositEmployeeDto = depositEmployeeMapper.selectDepositEmployee(clientCode);
            if (depositEmployeeDto == null) {
                depositEmployeeDto = new DepositEmployeeDto();
                depositEmployeeDto.setEmployeeCode(clientCode);
                depositEmployeeDto.setClientCode(clientCode);
                depositEmployeeDto.setAmount(BigDecimal.ZERO);
                depositEmployeeDto.setCreateBy(user.getLoginName());
                depositEmployeeDto.setUpdateBy(user.getLoginName());

                depositEmployeeMapper.insertDepositEmployee(depositEmployeeDto);
            }
            depositEmployeeDto = new DepositEmployeeDto();
            depositEmployeeDto.setEmployeeCode(clientCode);
            depositEmployeeDto.setUpdateBy(user.getLoginName());
            depositEmployeeMapper.refreshDeposit(depositEmployeeDto);


            // 计算客户剩余押金
            this.refreshDeposit(clientCode);
        }*/
    }
}