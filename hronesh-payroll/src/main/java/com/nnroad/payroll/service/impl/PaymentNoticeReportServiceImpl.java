package com.nnroad.payroll.service.impl;


import com.nnroad.payroll.domain.Employer;
import com.nnroad.payroll.domain.common.PsBasicInfo;
import com.nnroad.payroll.domain.common.PsCoverInfo;
import com.nnroad.payroll.domain.exportC.*;
import com.nnroad.payroll.mapper.PaymentNoticeReportMapper;
import com.nnroad.payroll.service.IPaymentNoticeReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * deposit_reportService业务层处理
 *
 */
@Service
public class PaymentNoticeReportServiceImpl implements IPaymentNoticeReportService {
    @Autowired
    private PaymentNoticeReportMapper paymentNoticeReportMapper;


    /**
     * 查询Payroll列表
     *
     * @param map 参数合集
     * @return Payroll列表
     */
    @Override
    public List<SPayrollReport> selectSPayrollList(Map<String, Object> map) {
        return paymentNoticeReportMapper.selectSPayrollList(map);
    }

    /**
     * 查询Payslip列表
     *
     * @param map 参数合集
     * @return Payslip列表
     */
    @Override
    public List<SPayslipReport> selectSPayslipList(Map<String, Object> map) {
        return paymentNoticeReportMapper.selectSPayslipList(map);
    }

    /**
     * 查询Other fee列表
     *
     * @param map 期间,公司编号
     * @return OtherFee集合
     */
    @Override
    public List<SOtherFeeReport> selectSOtherFeeList(Map<String, Object> map){
        return paymentNoticeReportMapper.selectSOtherFeeList(map);
    }

    /**
     * 查询payment notice封面打款银行信息
     *
     * @param erNo 公司编号
     * @return payment notice封面打款银行信息
     */
    @Override
    public PsCoverInfo selectBankInfo(String erNo){
        return paymentNoticeReportMapper.selectBankInfo(erNo);
    }

    /**
     * 查询payment notice封面用客户信息
     *
     * @param erNo 公司编号
     * @return payment notice封面用客户信息
     */
    @Override
    public Employer selectClientInfo(String erNo){
        return paymentNoticeReportMapper.selectClientInfo(erNo);
    }

    /**
     * 查询payment notice页面client下拉框
     *
     * @return client info信息
     */
    @Override
    public List<Employer> selectAllClient(){
        return paymentNoticeReportMapper.selectAllClient();
    }

    /**
     * 查询雇员列表
     *
     * @param map 期间,公司编号
     * @return 雇员列表
     */
    public List<PsBasicInfo> selectEmployeeListByClient(Map<String, Object> map) {
        return paymentNoticeReportMapper.selectEmployeeListByClient(map);
    }

    /**
     * CIEE_Payroll
     *
     * @param map 期间,公司编号
     * @return 雇员列表
     */
    public List<SCIEEPayrollReport> selectCIEEPayrollList(Map<String, Object> map) {
        return paymentNoticeReportMapper.selectCIEEPayrollList(map);
    }

    /**
     * 查询EmployeeInfo列表
     *
     * @param map 参数合集
     * @return EmployeeInfo列表
     */
    @Override
    public List<SEmployeeInfo> selectSEmployeeInfoList(Map<String, Object> map) {
        return paymentNoticeReportMapper.selectSEmployeeInfoList(map);
    }

    @Override
    public String selectServiceContractPartyB(String erNo) {
        return paymentNoticeReportMapper.selectServiceContractPartyB(erNo);
    }

    @Override
    public String selectEmployeeServiceContractPartyB(String IdNo){
        return paymentNoticeReportMapper.selectEmployeeServiceContractPartyB(IdNo);
    }
}
