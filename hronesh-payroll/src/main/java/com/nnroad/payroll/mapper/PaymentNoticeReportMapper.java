package com.nnroad.payroll.mapper;


import com.nnroad.payroll.domain.Employer;
import com.nnroad.payroll.domain.common.PsBasicInfo;
import com.nnroad.payroll.domain.common.PsCoverInfo;
import com.nnroad.payroll.domain.exportC.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * payment_details_reportMapper接口
 */

public interface PaymentNoticeReportMapper {
    /**
     * 查询Payroll
     *
     * @param map 期间,公司名
     * @return Payroll集合
     */
    public List<SPayrollReport> selectSPayrollList(@Param("params") Map<String, Object> map);

    /**
     * 查询Payslip列表
     *
     * @param map 期间,公司名
     * @return Payslip集合
     */

    public List<SPayslipReport> selectSPayslipList(@Param("params") Map<String, Object> map);

    /**
     * 查询Other fee列表
     *
     * @param map 期间,公司编号
     * @return OtherFee集合
     */

    public List<SOtherFeeReport> selectSOtherFeeList(@Param("params") Map<String, Object> map);

    /**
     * 查询payment notice封面打款银行信息
     *
     * @param erNo 公司编号
     * @return payment notice封面打款银行信息
     */

    public PsCoverInfo selectBankInfo(String erNo);

    /**
     * 查询payment notice封面用客户信息
     *
     * @param erNo 公司编号
     * @return payment notice封面用客户信息
     */

    public Employer selectClientInfo(String erNo);

    /**
     * 查询payment notice页面client下拉框
     *
     * @return client info信息
     */

    public List<Employer> selectAllClient();

    /**
     * 查询雇员列表
     *
     * @param map 期间,公司编号
     * @return 雇员列表
     */

    public List<PsBasicInfo> selectEmployeeListByClient(Map<String, Object> map);

    /**
     * 查询CIEE_Payroll
     *
     * @param map 期间,员工号
     * @return Payroll集合
     */

    public List<SCIEEPayrollReport> selectCIEEPayrollList(@Param("params") Map<String, Object> map);

    /**
     * 查询EmployeeInfo列表
     *
     * @param map 期间,公司名
     * @return EmployeeInfo集合
     */

    public List<SEmployeeInfo> selectSEmployeeInfoList(@Param("params") Map<String, Object> map);


    public String selectServiceContractPartyB(String erNo);


    public String selectEmployeeServiceContractPartyB(String IdNo);
}
