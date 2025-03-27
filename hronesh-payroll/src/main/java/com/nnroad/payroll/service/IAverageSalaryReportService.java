package com.nnroad.payroll.service;


import com.nnroad.payroll.domain.exportC.AverageSalaryReport;

import java.util.List;

/**
 * AverageSalaryReportService接口
 */
public interface IAverageSalaryReportService 
{
    /**
     * 查询AverageSalaryReport
     * 
     * @param duration AverageSalaryReportID
     * @return AverageSalaryReport
     */
    public AverageSalaryReport selectAverageSalaryReportById(String duration);

    /**
     * 查询AverageSalaryReport列表
     * 
     * @param averageSalaryReport AverageSalaryReport
     * @return AverageSalaryReport集合
     */
    public List<AverageSalaryReport> selectAverageSalaryReportList(AverageSalaryReport averageSalaryReport);

    /**
     * 新增AverageSalaryReport
     * 
     * @param averageSalaryReport AverageSalaryReport
     * @return 结果
     */
    public int insertAverageSalaryReport(AverageSalaryReport averageSalaryReport);

    /**
     * 修改AverageSalaryReport
     * 
     * @param averageSalaryReport AverageSalaryReport
     * @return 结果
     */
    public int updateAverageSalaryReport(AverageSalaryReport averageSalaryReport);

    /**
     * 批量删除AverageSalaryReport
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAverageSalaryReportByIds(String ids);

    /**
     * 删除AverageSalaryReport信息
     * 
     * @param duration AverageSalaryReportID
     * @return 结果
     */
    public int deleteAverageSalaryReportById(String duration);
}
