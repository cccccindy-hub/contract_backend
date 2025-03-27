package com.nnroad.web.controller.payroll;

import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.common.utils.poi.HroneExcelUtil;
import com.nnroad.payroll.domain.exportC.SocialBenefitReport;
import com.nnroad.payroll.service.ISocialBenefitReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * social_benefit_reportController
 * 

 */
@Controller
@RequestMapping("/payroll/social_benefit_report")
public class SocialBenefitReportController extends BaseController
{
    private String prefix = "payroll/social_benefit_report";

    @Autowired
    private ISocialBenefitReportService socialBenefitReportService;

    /**
     * 查询social_benefit_report列表
     */
    @PreAuthorize("@ss.hasPermi('payroll:social_benefit_report:list')")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody SocialBenefitReport socialBenefitReport)
    {
        startPage();
        List<SocialBenefitReport> list = socialBenefitReportService.selectSocialBenefitReportList(socialBenefitReport);
        return getDataTable(list);
    }

    /**
     * 导出social_benefit_report列表
     */
    @PreAuthorize("@ss.hasPermi('payroll:social_benefit_report:export')")
    @Log(title = "social_benefit_report", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody SocialBenefitReport socialBenefitReport)
    {
        List<SocialBenefitReport> list = socialBenefitReportService.selectSocialBenefitReportList(socialBenefitReport);
        HroneExcelUtil<SocialBenefitReport> util = new HroneExcelUtil<SocialBenefitReport>(SocialBenefitReport.class);
        return util.exportExcel(list, "social_benefit_report");
    }
}
