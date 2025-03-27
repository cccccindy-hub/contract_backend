package com.nnroad.payroll.service;


import com.nnroad.payroll.domain.common.PsBasicInfo;
import com.nnroad.payroll.domain.common.PsPayroll;

import java.util.List;

/**
 * ps_payrollService接口
 * 
 * @author Hrone
 * @date 2021-01-21
 */
public interface IPsPayrollService 
{
    /**
     * 导入ps_payroll信息
     * @param psPayrollList payroll数据
     * @param isUpdateSupport 是否更新
     * @param duration 年月

     * @return 结果
     */
    public String importPsPayroll(List<PsPayroll> psPayrollList, boolean isUpdateSupport, String duration);
    public String importVendorPsPayroll(List<PsPayroll> psPayrollList, boolean isUpdateSupport, String duration);
    /**
     * 查询ps_payroll列表
     *
     * @param psPayroll ps_payroll
     * @return ps_payroll集合
     */
    public List<PsPayroll> selectPsPayrollList(PsPayroll psPayroll);

    public List<PsPayroll> selectPsPayrollListBySource(PsPayroll psPayroll);


    /**
     * 修改ps_payroll
     *
     * @param psPayroll ps_payroll
     * @return 结果
     */
    public int updatePsPayroll(PsPayroll psPayroll);

    public int updateBatchPsPayroll(List<PsPayroll> psPayrollList);

    /**
     * 查询ps_payroll
     *
     * @param id ps_payrollID
     * @return ps_payroll
     */
    public PsPayroll selectPsPayrollById(Long id);

    void updatePsPayrollByPayslip(String duration, List<PsBasicInfo> psBasicInfoList);

    void updateVendorPsPayrollByPayslip(String duration, List<PsBasicInfo> psBasicInfoList);
}
