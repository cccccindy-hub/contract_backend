package com.nnroad.payroll.service;



import com.nnroad.payroll.domain.MyPsPayslip;
import com.nnroad.payroll.domain.MyPsPayslipDto;
import com.nnroad.payroll.domain.common.PsPayslip;

import java.util.List;

/**
 * ps_payslipService接口
 * 
 * @author Hrone
 * @date 2021-01-21
 */
public interface IPsPayslipService 
{

    /**
     * 查询ps_payslip
     *
     * @param id ps_payslipID
     * @return ps_payslip
     */
    public PsPayslip selectPsPayslipById(Long id);


    public String importPsPayslip(List<PsPayslip> psPayslipList, boolean isUpdateSupport, String duration);



    /**
     * 查询ps_payslip列表(包含雇主姓名)
     *
     * @param psPayslip ps_payslip
     * @return ps_payslip集合
     */
    public List<PsPayslip> selectPsPayslipList1(PsPayslip psPayslip);

    /**
     * 查询ps_payslip列表(包含雇主姓名)
     *
     * @param psPayslip ps_payslip
     * @return ps_payslip集合
     */
    public List<PsPayslip> selectPsPayslipListByEmployeeOrg(PsPayslip psPayslip);

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
    public String openQueryPsPayslipByIds(String ids);



    /**
     * 查询ps_payslip列表
     *
     * @param psPayslip ps_payslip
     * @return ps_payslip集合
     */
    public List<PsPayslip> selectPsPayslipList(PsPayslip psPayslip);



}
