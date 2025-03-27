package com.nnroad.payroll.mapper;


import com.nnroad.payroll.domain.MyPsPayslip;
import com.nnroad.payroll.domain.MyPsPayslipDto;
import com.nnroad.payroll.domain.common.PsPayslip;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ps_payslipMapper接口
 * 
 * @author Hrone
 * @date 2021-01-21
 */
public interface PsPayslipMapper 
{
    /**
     * 查询ps_payslip列表
     * 
     * @param psPayslip ps_payslip
     * @return ps_payslip集合
     */
    public List<PsPayslip> selectPsPayslipList(PsPayslip psPayslip);

    public PsPayslip getPsPayslip(PsPayslip psPayslip);

    /**
     * 新增ps_payslip
     * 
     * @param psPayslip ps_payslip
     * @return 结果
     */
    public int insertPsPayslip(PsPayslip psPayslip);

    /**
     * 修改ps_payslip
     * 
     * @param psPayslip ps_payslip
     * @return 结果
     */
    public int updatePsPayslip(PsPayslip psPayslip);

    /**
     * 删除ps_payslip
     *
     * @param psPayslip ps_payslip
     * @return 结果
     */
    public int deletePsPayslipByKey(PsPayslip psPayslip);

    /**
     * 查询ps_payslip列表
     *
     * @param psPayslip ps_payslip
     * @return ps_payslip集合
     */
    public List<PsPayslip> selectPsPayslipList1(PsPayslip psPayslip);


    public List<PsPayslip> selectPsPayslipListByEmployeeOrg(PsPayslip psPayslip);
    /**
     * 查询ps_payslip
     *
     * @param id ps_payslipID
     * @return ps_payslip
     */
    public PsPayslip selectPsPayslipById(Long id);

    /**
     *  获取我的工资单信息
     * @param myPsPayslipDto 检索条件
     * @return 结果
     */
    public MyPsPayslip getMyPayslipData(MyPsPayslipDto myPsPayslipDto);


    /**
     * 开发查询ps_payslip信息
     * @param ids 信息id
     * @return 结果
     */
    public int openQueryPsPayslipByIds(String[] ids);


    void closeQueryPsPayslipByIds(String[] ids);



    List<PsPayslip> selectPsPayslipByIds(String[] ids);

    public PsPayslip selectPsPayslipByDurationAndeeCode(@Param("eeCode") String eeCode, @Param("duration") String duration);

}
