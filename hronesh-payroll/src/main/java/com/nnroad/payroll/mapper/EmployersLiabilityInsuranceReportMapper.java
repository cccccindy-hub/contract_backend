package com.nnroad.payroll.mapper;


import com.nnroad.payroll.domain.exportC.EmployersLiabilityInsuranceReport;

import java.util.List;

/**
 * 雇主责任险Mapper接口
 * 
 * @author liwengang
 * @date 2022-01-05
 */
public interface EmployersLiabilityInsuranceReportMapper
{
    /**
     * 查询雇主责任险
     * 
     * @param name 雇主责任险ID
     * @return 雇主责任险
     */
    public EmployersLiabilityInsuranceReport selectEmployersLiabilityInsuranceReportById(String name);

    /**
     * 查询雇主责任险列表
     * 
     * @param employersLiabilityInsuranceReport 雇主责任险
     * @return 雇主责任险集合
     */
    public List<EmployersLiabilityInsuranceReport> selectEmployersLiabilityInsuranceReportList(EmployersLiabilityInsuranceReport employersLiabilityInsuranceReport);

    /**
     * 新增雇主责任险
     * 
     * @param employersLiabilityInsuranceReport 雇主责任险
     * @return 结果
     */
    public int insertEmployersLiabilityInsuranceReport(EmployersLiabilityInsuranceReport employersLiabilityInsuranceReport);

    /**
     * 修改雇主责任险
     * 
     * @param employersLiabilityInsuranceReport 雇主责任险
     * @return 结果
     */
    public int updateEmployersLiabilityInsuranceReport(EmployersLiabilityInsuranceReport employersLiabilityInsuranceReport);

    /**
     * 删除雇主责任险
     * 
     * @param name 雇主责任险ID
     * @return 结果
     */
    public int deleteEmployersLiabilityInsuranceReportById(String name);

    /**
     * 批量删除雇主责任险
     * 
     * @param names 需要删除的数据ID
     * @return 结果
     */
    public int deleteEmployersLiabilityInsuranceReportByIds(String[] names);
}
