package com.nnroad.payroll.service;



import com.nnroad.payroll.domain.Employer;
import com.nnroad.payroll.domain.common.PsBasicInfo;
import com.nnroad.payroll.domain.common.PsCoverInfo;
import com.nnroad.payroll.domain.exportC.*;

import java.util.List;
import java.util.Map;

/**
 * payment_notice_reportService接口
 *
 */
public interface IPaymentNoticeReportService
{
    /**
    *查询所有客户信息
     */
    List<Employer> selectAllClient();

    /*根据客户ID查询客户数据*/
    Employer selectClientInfo(String erId);

    List<PsBasicInfo> selectEmployeeListByClient(Map<String, Object> map);


    PsCoverInfo selectBankInfo(String erNo);

    String selectServiceContractPartyB(String erNo);

    String selectEmployeeServiceContractPartyB(String idNo);

    List<SPayrollReport> selectSPayrollList(Map<String, Object> map);

    List<SOtherFeeReport> selectSOtherFeeList(Map<String, Object> map);

    List<SPayslipReport> selectSPayslipList(Map<String, Object> map);

    List<SEmployeeInfo> selectSEmployeeInfoList(Map<String, Object> map);

    List<SCIEEPayrollReport> selectCIEEPayrollList(Map<String, Object> map);

}
