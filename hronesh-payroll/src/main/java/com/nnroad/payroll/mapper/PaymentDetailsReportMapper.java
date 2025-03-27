package com.nnroad.payroll.mapper;


import com.nnroad.payroll.domain.exportC.PaymentDetailsReport;

import java.util.List;

/**
 * payment_details_reportMapper接口
 */
public interface PaymentDetailsReportMapper 
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
