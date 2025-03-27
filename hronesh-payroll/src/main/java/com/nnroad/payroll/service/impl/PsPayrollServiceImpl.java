package com.nnroad.payroll.service.impl;


import com.nnroad.common.core.domain.entity.SysUser;
import com.nnroad.common.exception.BusinessException;
import com.nnroad.common.utils.*;
import com.nnroad.employee.mapper.EmployeeInfoMapper;
import com.nnroad.employee.mapper.SysEmployeeMapper;
import com.nnroad.payroll.constants.PayrollConstants;
import com.nnroad.payroll.domain.common.PsBasicInfo;
import com.nnroad.payroll.domain.common.PsPayroll;
import com.nnroad.payroll.domain.common.PsPayslip;
import com.nnroad.payroll.mapper.PsPayrollMapper;
import com.nnroad.payroll.mapper.PsPayslipMapper;
import com.nnroad.payroll.service.IPsPayrollService;
import com.nnroad.vendor.domain.SysVendor;
import com.nnroad.vendor.mapper.SysVendorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;

/**
 * ps_payrollService业务层处理
 *
 * @author Hrone
 * @date 2021-01-21
 */
@Service
public class PsPayrollServiceImpl implements IPsPayrollService {
    private static final Logger log = LoggerFactory.getLogger(PsPayrollServiceImpl.class);

    @Autowired
    private PsPayrollMapper psPayrollMapper;

    @Autowired
    private PsPayslipMapper psPayslipMapper;

    @Autowired
    private SysEmployeeMapper sysEmployeeMapper;

    @Autowired
    private SysVendorMapper sysVendorMapper;



    /**
     * 新增ps_payroll
     *
     * @param psPayroll ps_payroll
     * @return 结果
     */
    public int insertPsPayroll(PsPayroll psPayroll) {
        return psPayrollMapper.insertPsPayroll(psPayroll);
    }

    public int insertVendorPsPayroll(PsPayroll psPayroll) {
        return psPayrollMapper.insertVendorPsPayroll(psPayroll);
    }

    /**
     * 修改ps_payroll
     *
     * @param psPayroll ps_payroll
     * @return 结果
     */
    public int updatePsPayroll(PsPayroll psPayroll) {
        return psPayrollMapper.updatePsPayroll(psPayroll);
    }

    @Override
    public int updateBatchPsPayroll(List<PsPayroll> psPayrollList) {
        int success=0;
        for (PsPayroll psPayroll : psPayrollList) {
            BigDecimal TotalCost=psPayroll.getNetIncome()
                    .add(psPayroll.getExpense())
                    .add(psPayroll.getForeignerAllowance())
                    .add(psPayroll.getIpPension())
                    .add(psPayroll.getIpMedical())
                    .add(psPayroll.getIpUnemployment())
                    .add(psPayroll.getIpHousingFund())
                    .add(psPayroll.getIpAnnuity())
                    .add(psPayroll.getIpUnionFee())
                    .add(psPayroll.getCommercialInsurance())
                    .add(psPayroll.getIIT())
                    .add(psPayroll.getBalanceCharge())
                    .add(psPayroll.getEpPension())
                    .add(psPayroll.getEpMedical())
                    .add(psPayroll.getEpUnemployment())
                    .add(psPayroll.getMaternity())
                    .add(psPayroll.getWorkRelatedInjury())
                    .add(psPayroll.getDisability())
                    .add(psPayroll.getEpHousingFund())
                    .add(psPayroll.getEpAnnuity())
                    .add(psPayroll.getEpUnionFee())
                    .add(psPayroll.getOtherBenefits())
                    .add(psPayroll.getOthers())
                    .add(psPayroll.getBusinessTax())
                    .add(psPayroll.getEmployerLiability())
                    .add(psPayroll.getServiceFee())
                    .add(psPayroll.getDeposit())
                    .add(psPayroll.getValueAddedTax());
            psPayroll.setTotalCost(TotalCost);
            psPayrollMapper.updatePsPayroll(psPayroll);
            success++;
        }
        return success;
    }

    public int updateVendorPsPayroll(PsPayroll psPayroll) {
        return psPayrollMapper.updateVendorPsPayroll(psPayroll);
    }


    /**
     * 导入ps_payroll信息
     *
     * @param psPayrollList   payroll数据
     * @param isUpdateSupport 是否更新
     * @param duration        年月
     * @return 结果
     */
    @Override
    public String importPsPayroll(List<PsPayroll> psPayrollList, boolean isUpdateSupport, String duration) {
        if (StringUtils.isNull(psPayrollList) || psPayrollList.isEmpty()) {
            throw new BusinessException(MessageFormat.format("{0} 文件无数据或者没有匹配的列！", "S-Payroll Report"));
        }
        int index = 7;
        int failureNum = 0;
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String rtMsg = "";
        for (PsPayroll psPayroll : psPayrollList) {
            index = index + 1;
            try {
                if (StringUtils.isNotEmpty(psPayroll.getIdNo()) && StringUtils.isNotEmpty(psPayroll.getName())
                        && !StringUtils.contains(psPayroll.getIdNo(), PayrollConstants.REF_STR) && !StringUtils.contains(psPayroll.getName(), PayrollConstants.REF_STR)) {
                    // 验证是否存在这个基本信息
                    PsPayroll pp = new PsPayroll();
                    pp.setDuration(duration);
                    pp.setIdNo(psPayroll.getIdNo());

                    List<PsPayroll> ppList = psPayrollMapper.selectPsPayrollList(pp);
                    if (StringUtils.isEmpty(ppList)) {
                        psPayroll.setDuration(duration);
//                        setAuthority(leaderId, sysUser, psPayroll);
                        psPayroll.setCreateBy(SecurityUtils.getUsername());

                        this.insertPsPayroll(psPayroll);
                        successNum++;
                    } else {
                        if (isUpdateSupport) {
                            psPayroll.setId(ppList.get(0).getId());
                            psPayroll.setDuration(duration);
//                            setAuthority(leaderId, sysUser, psPayroll);
                            psPayroll.setUpdateBy(SecurityUtils.getUsername());
                            this.updatePsPayroll(psPayroll);
                            successNum++;
                        } else {
                            failureNum++;
                            failureMsg.append(MessageFormat.format("<br/>excel的{0}行工号和姓名已存在", index));
                        }
                    }
                } else {
                    log.info(MessageFormat.format("<br/>excel的{0}行工号或者姓名为空", index));
                }
            } catch (Exception e) {
                failureNum++;
                String msg = MessageFormat.format("<br/>excel的{0}行数据导入失败", index);
                //failureMsg.append(msg + ExceptionUtil.getExceptionColumn(e.getMessage()));
                failureMsg.append(msg).append(" Data exception for column [").append(MessageUtils.message(ExceptionUtil.getExceptionColumn(e.getMessage()))).append("] ");
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, MessageFormat.format("sheet[{0}] 数据导入已完成，成功{1}条, 失败{2}条。错误如下：", "S-Payroll Report", successNum, failureNum));
            rtMsg = failureMsg.toString();
        } else {
            successMsg.insert(0, MessageFormat.format("sheet[{0}] 数据导入已完成，成功{1}条。", "S-Payroll Report", successNum));
            rtMsg = successMsg.toString();
        }
        return rtMsg;
    }

    @Override
    public String importVendorPsPayroll(List<PsPayroll> psPayrollList, boolean isUpdateSupport, String duration) {
        if (StringUtils.isNull(psPayrollList) || psPayrollList.isEmpty()) {
            throw new BusinessException(MessageFormat.format("{0} 文件无数据或者没有匹配的列！", "S-Payroll Report"));
        }
        int index = 7;
        int failureNum = 0;
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String rtMsg = "";
        for (PsPayroll psPayroll : psPayrollList) {
            index = index + 1;
            try {
                if (StringUtils.isNotEmpty(psPayroll.getIdNo()) && StringUtils.isNotEmpty(psPayroll.getName())
                        && !StringUtils.contains(psPayroll.getIdNo(), PayrollConstants.REF_STR) && !StringUtils.contains(psPayroll.getName(), PayrollConstants.REF_STR)) {
                    // 验证是否存在这个基本信息
                    PsPayroll pp = new PsPayroll();
                    pp.setDuration(duration);
                    pp.setIdNo(psPayroll.getIdNo());

                    List<PsPayroll> ppList = psPayrollMapper.selectVendorPsPayrollList(pp);
                    if (StringUtils.isEmpty(ppList)) {
                        psPayroll.setDuration(duration);
//                        setAuthority(leaderId, sysUser, psPayroll);
                        psPayroll.setCreateBy(SecurityUtils.getUsername());

                        this.insertVendorPsPayroll(psPayroll);
                        successNum++;
                    } else {
                        if (isUpdateSupport) {
                            psPayroll.setId(ppList.get(0).getId());
                            psPayroll.setDuration(duration);
//                            setAuthority(leaderId, sysUser, psPayroll);
                            psPayroll.setUpdateBy(SecurityUtils.getUsername());
                            this.updateVendorPsPayroll(psPayroll);
                            successNum++;
                        } else {
                            failureNum++;
                            failureMsg.append(MessageFormat.format("<br/>excel的{0}行工号和姓名已存在", index));
                        }
                    }
                } else {
                    log.info(MessageFormat.format("<br/>excel的{0}行工号或者姓名为空", index));
                }
            } catch (Exception e) {
                failureNum++;
                String msg = MessageFormat.format("<br/>excel的{0}行数据导入失败", index);
                //failureMsg.append(msg + ExceptionUtil.getExceptionColumn(e.getMessage()));
                failureMsg.append(msg).append(" Data exception for column [").append(MessageUtils.message(ExceptionUtil.getExceptionColumn(e.getMessage()))).append("] ");
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, MessageFormat.format("sheet[{0}] 数据导入已完成，成功{1}条, 失败{2}条。错误如下：", "S-Payroll Report", successNum, failureNum));
            rtMsg = failureMsg.toString();
        } else {
            successMsg.insert(0, MessageFormat.format("sheet[{0}] 数据导入已完成，成功{1}条。", "S-Payroll Report", successNum));
            rtMsg = successMsg.toString();
        }
        return rtMsg;
    }


    /**
     * 工资汇总表更新
     *
     * @param leaderId  数据拥有者
     * @param sysUser   当前用户
     * @param psPayroll 工资汇总数据
     */
    private void setAuthority(String leaderId, SysUser sysUser, PsPayroll psPayroll) {
//        if(leaderId.length() == 0){
//            if(sysUser.isAdmin()){
//                psPayroll.setGroupIds(ShiroUtils.getUserId().toString());
//            }else{
//                psPayroll.setGroupIds("1," + ShiroUtils.getUserId().toString());
//            }
//        }else{
//            if(sysUser.isAdmin()){
//                psPayroll.setGroupIds(leaderId + "," + ShiroUtils.getUserId().toString());
//            }else{
//                psPayroll.setGroupIds(leaderId + ",1," + ShiroUtils.getUserId().toString());
//            }
//        }
        // 设置权限组
        if (StringUtils.isNotEmpty(leaderId)) {
            psPayroll.setGroupIds(leaderId);
        }
    }



    /**
     * 查询ps_payroll列表
     *
     * @param psPayroll ps_payroll
     * @return ps_payroll
     */
    @Override
    public List<PsPayroll> selectPsPayrollList(PsPayroll psPayroll) {
        return psPayrollMapper.selectPsPayrollList(psPayroll);
    }

    @Override
    public List<PsPayroll> selectPsPayrollListBySource(PsPayroll psPayroll) {
        return psPayrollMapper.selectPsPayrollListBySource(psPayroll);
    }


    /**
     * 查询ps_payroll
     *
     * @param id ps_payrollID
     * @return ps_payroll
     */
    @Override
    public PsPayroll selectPsPayrollById(Long id) {
        return psPayrollMapper.selectPsPayrollById(id);
    }

    @Override
    public void updatePsPayrollByPayslip(String duration, List<PsBasicInfo> psBasicInfoList) {
        updatePayrollByPayslip(duration, psBasicInfoList, false);
    }

    @Override
    public void updateVendorPsPayrollByPayslip(String duration, List<PsBasicInfo> psBasicInfoList) {
        updatePayrollByPayslip(duration, psBasicInfoList, true);
    }

    /**
     * 通用方法处理 PsPayroll 的更新逻辑
     *
     * @param duration          时间段
     * @param psBasicInfoList   PsBasicInfo 列表
     * @param isVendorSpecific  是否为供应商特定逻辑
     */
    private void updatePayrollByPayslip(String duration, List<PsBasicInfo> psBasicInfoList, boolean isVendorSpecific) {
        for (PsBasicInfo psBasicInfo : psBasicInfoList) {
            // 检查 idNo 和 duration 是否为空
            if (psBasicInfo.getIdNo() == null || psBasicInfo.getIdNo().trim().isEmpty()) {
                System.out.println("idNo 为空，跳过当前记录！");
                continue;
            }
            if (psBasicInfo.getDuration() == null || psBasicInfo.getDuration().trim().isEmpty()) {
                System.out.println("PsBasicInfo 的 duration 为空，跳过当前记录！");
                continue;
            }

            PsPayroll payroll;
            // 获取 PsPayroll 和 PsPayslip 数据
            if (isVendorSpecific){
                payroll = psPayrollMapper.getVendorPsPayrollByIdnoAndduration(psBasicInfo.getIdNo(),psBasicInfo.getDuration());
            }else {
                payroll = psPayrollMapper.getPsPayrollByIdnoAndduration(psBasicInfo.getIdNo(), psBasicInfo.getDuration());
            }

            if (payroll == null) {
                System.out.println("未找到对应的 PsPayroll，跳过当前记录！idNo: " + psBasicInfo.getIdNo() + ", duration: " + psBasicInfo.getDuration());
                continue;
            }

            PsPayslip payslip = psPayslipMapper.selectPsPayslipByDurationAndeeCode(psBasicInfo.getIdNo(), psBasicInfo.getDuration());
            if (payslip == null) {
                System.out.println("未找到对应的 PsPayslip，跳过当前记录！idNo: " + psBasicInfo.getIdNo() + ", duration: " + psBasicInfo.getDuration());
                continue;
            }

            // 更新 PsPayroll 的字段
            payroll.setIpPension(payslip.getPension());
            payroll.setIpMedical(payslip.getMedical());
            payroll.setIpUnemployment(payslip.getUnemployment());
            payroll.setIpHousingFund(payslip.getHousingFund());
            payroll.setIpAnnuity(payslip.getAnnuity());
            payroll.setIpUnionFee(payslip.getUnionFee());
            payroll.setUpdateBy(SecurityUtils.getUsername());

            // 查询 Employment Contract 数据
            String employmentContract = sysEmployeeMapper.selectEmploymentContract(psBasicInfo.getIdNo());
            if (employmentContract != null) {
                List<SysVendor> vendorList = sysVendorMapper.getVendorNameByEmploymentContract(employmentContract);
                if (vendorList != null && !vendorList.isEmpty()) {
                    payroll.setPayrollSource(employmentContract);
                } else {
                    System.out.println("未找到对应的供应商信息，employmentContract: " + employmentContract);
                }
            }

            if (isVendorSpecific) {
                // 供应商特定逻辑
                PsPayroll existingPayroll = psPayrollMapper.getPsPayrollByIdnoAndduration(payroll.getIdNo(), payroll.getDuration());
                if (existingPayroll == null) {
                    psPayrollMapper.insertPsPayroll(payroll);
                }else {
                    existingPayroll.setPayrollSource(employmentContract);
                    psPayrollMapper.updatePsPayroll(existingPayroll);
                }
                psPayrollMapper.updateVendorPsPayroll(payroll);
            } else {
                // 通用逻辑
                psPayrollMapper.updatePsPayroll(payroll);
            }
        }
    }

}
