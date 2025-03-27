package com.nnroad.payroll.mapper;


import com.nnroad.payroll.domain.exportC.CommercialInsuranceReport;

import java.util.List;

/**
 * commercial_insurance_reportMapper接口
 * 
 * @author Hrone
 * @date 2021-12-31
 */
public interface CommercialInsuranceReportMapper 
{
    /**
     * 查询commercial_insurance_report
     * 
     * @param employeeName commercial_insurance_reportID
     * @return commercial_insurance_report
     */
    public CommercialInsuranceReport selectCommercialInsuranceReportById(String employeeName);

    /**
     * 查询commercial_insurance_report列表
     * 
     * @param commercialInsuranceReport commercial_insurance_report
     * @return commercial_insurance_report集合
     */
    public List<CommercialInsuranceReport> selectCommercialInsuranceReportList(CommercialInsuranceReport commercialInsuranceReport);

    /**
     * 新增commercial_insurance_report
     * 
     * @param commercialInsuranceReport commercial_insurance_report
     * @return 结果
     */
    public int insertCommercialInsuranceReport(CommercialInsuranceReport commercialInsuranceReport);

    /**
     * 修改commercial_insurance_report
     * 
     * @param commercialInsuranceReport commercial_insurance_report
     * @return 结果
     */
    public int updateCommercialInsuranceReport(CommercialInsuranceReport commercialInsuranceReport);

    /**
     * 删除commercial_insurance_report
     * 
     * @param employeeName commercial_insurance_reportID
     * @return 结果
     */
    public int deleteCommercialInsuranceReportById(String employeeName);

    /**
     * 批量删除commercial_insurance_report
     * 
     * @param employeeNames 需要删除的数据ID
     * @return 结果
     */
    public int deleteCommercialInsuranceReportByIds(String[] employeeNames);
}
