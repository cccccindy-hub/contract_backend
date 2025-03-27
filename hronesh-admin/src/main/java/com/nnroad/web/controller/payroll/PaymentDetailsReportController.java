package com.nnroad.web.controller.payroll;

import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.common.utils.poi.HroneExcelUtil;
import com.nnroad.payroll.domain.exportC.PaymentDetailsReport;
import com.nnroad.payroll.service.IPaymentDetailsReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * payment_details_reportController

 */
@Controller
@RequestMapping("/payroll/payment_details_report")
public class PaymentDetailsReportController extends BaseController
{
    @Autowired
    private IPaymentDetailsReportService paymentDetailsReportService;

    /**
     * 查询payment_details_report列表
     */

    @PreAuthorize("@ss.hasPermi('payroll:payment_details_report:list')")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody PaymentDetailsReport paymentDetailsReport)
    {
        startPage();
        List<PaymentDetailsReport> list = paymentDetailsReportService.selectPaymentDetailsReportList(paymentDetailsReport);
        return getDataTable(list);
    }

    /**
     * 导出payment_details_report列表
     */
    @PreAuthorize("@ss.hasPermi('payroll:payment_details_report:export')")
    @Log(title = "payment_details_report", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody PaymentDetailsReport paymentDetailsReport)
    {
        List<PaymentDetailsReport> list = paymentDetailsReportService.selectPaymentDetailsReportList(paymentDetailsReport);
        HroneExcelUtil<PaymentDetailsReport> util = new HroneExcelUtil<>(PaymentDetailsReport.class);
        return util.exportExcel(list, "payment_details_report");
    }


}
