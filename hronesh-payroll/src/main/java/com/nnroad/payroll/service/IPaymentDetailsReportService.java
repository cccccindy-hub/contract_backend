package com.nnroad.payroll.service;

import com.nnroad.payroll.domain.exportC.PaymentDetailsReport;

import java.util.List;

/**
 * payment_details_reportService接口
 * 
 * @author Hrone
 * @date 2021-01-13
 */
public interface IPaymentDetailsReportService
{
    /**
     * 查询payment_details_report
     *
     * @param hroneNo hroneNo
     * @return payment_details_report
     */
    public PaymentDetailsReport selectPaymentDetailsReportById(String hroneNo);

    /**
     * 查询payment_details_report列表
     *
     * @param paymentDetailsReport payment_details_report
     * @return payment_details_report集合
     */
    public List<PaymentDetailsReport> selectPaymentDetailsReportList(PaymentDetailsReport paymentDetailsReport);

}
