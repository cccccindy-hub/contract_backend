package com.nnroad.payroll.mapper;


import com.nnroad.payroll.domain.common.PsPayroll;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ps_payrollMapper接口
 *
 * @author Hrone
 * @date 2021-01-21
 */

public interface PsPayrollMapper
{
    /**
     * 查询ps_payroll列表
     *
     * @param psPayroll ps_payroll
     * @return ps_payroll集合
     */
    public List<PsPayroll> selectPsPayrollList(PsPayroll psPayroll);

    /**
     * 查询ps_payroll列表
     *
     * @param psPayroll ps_payroll
     * @return ps_payroll集合
     */
    public List<PsPayroll> selectVendorPsPayrollList(PsPayroll psPayroll);



    /**
     * 查询ps_payroll
     *
     * @param id ps_payrollID
     * @return ps_payroll
     */
    public PsPayroll selectPsPayrollById(Long id);


    public List<PsPayroll> selectPsPayrollListBySource(PsPayroll psPayroll);

    /**
     * 新增ps_payroll
     *
     * @param psPayroll ps_payroll
     * @return 结果
     */
    public int insertPsPayroll(PsPayroll psPayroll);

    public int insertVendorPsPayroll(PsPayroll psPayroll);

    /**
     * 修改ps_payroll
     *
     * @param psPayroll ps_payroll
     * @return 结果
     */
    public int updatePsPayroll(PsPayroll psPayroll);

    public int batchUpdatePsPayroll(List<PsPayroll> psPayrollList);

    public int updateVendorPsPayroll(PsPayroll psPayroll);


    /**
     * 删除ps_payroll
     *
     * @param psPayroll ps_payroll
     * @return 结果
     */
    public int deletePsPayrollByKey(PsPayroll psPayroll);

    public PsPayroll getPsPayrollByIdnoAndduration(@Param("idNo")String idNo,@Param("duration") String duartion);

    public PsPayroll getVendorPsPayrollByIdnoAndduration(@Param("idNo")String idNo,@Param("duration") String duartion);

    public PsPayroll getPsPayroll(PsPayroll psPayroll);

}
