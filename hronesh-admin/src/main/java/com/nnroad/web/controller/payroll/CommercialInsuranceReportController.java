package com.nnroad.web.controller.payroll;

import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.common.utils.poi.HroneExcelUtil;
import com.nnroad.payroll.domain.exportC.CommercialInsuranceReport;
import com.nnroad.payroll.service.ICommercialInsuranceReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CommercialInsuranceReportController

 */
@Controller
@RequestMapping("/payroll/commercial_insurance_report")
public class CommercialInsuranceReportController extends BaseController
{
    private String prefix = "payroll/commercial_insurance_report";

    @Autowired
    private ICommercialInsuranceReportService iCommercialInsuranceReportService;

    /**
     * 查询commercial_insurance_report列表
     */
    @PreAuthorize("@ss.hasPermi('payroll:commercial_insurance_report:list')")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody CommercialInsuranceReport commercialInsuranceReport)
    {
        startPage();
        List<CommercialInsuranceReport> list = iCommercialInsuranceReportService.selectCommercialInsuranceReportList(commercialInsuranceReport);
        return getDataTable(list);
    }

    /**
     * 导出commercial_insurance_report列表
     */
    @PreAuthorize("@ss.hasPermi('payroll:commercial_insurance_report:export')")
    @Log(title = "导出商业保险", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody CommercialInsuranceReport commercialInsuranceReport)
    {
        List<CommercialInsuranceReport> list = iCommercialInsuranceReportService.selectCommercialInsuranceReportList(commercialInsuranceReport);
        HroneExcelUtil<CommercialInsuranceReport> util = new HroneExcelUtil<CommercialInsuranceReport>(CommercialInsuranceReport.class);
        return util.exportExcel(list, "commercial_insurance_report");
    }

//    /**
//     * 新增commercial_insurance_report
//     */
//    @GetMapping("/add")
//    public String add()
//    {
//        return prefix + "/add";
//    }
//
//    /**
//     * 新增保存commercial_insurance_report
//     */
//    @RequiresPermissions("payroll:commercial_insurance_report:add")
//    @Log(title = "新增保存商业保险", businessType = BusinessType.INSERT)
//    @PostMapping("/add")
//    @ResponseBody
//    public AjaxResult addSave(CommercialInsuranceReport commercialInsuranceReport)
//    {
//        return toAjax(iCommercialInsuranceReportService.insertCommercialInsuranceReport(commercialInsuranceReport));
//    }

//    /**
//     * 修改commercial_insurance_report
//     */
//    @GetMapping("/edit/{employeeName}")
//    public String edit(@PathVariable("employeeName") String employeeName, ModelMap mmap)
//    {
//        CommercialInsuranceReport commercialInsuranceReport = iCommercialInsuranceReportService.selectCommercialInsuranceReportById(employeeName);
//        mmap.put("CommercialInsuranceReport", commercialInsuranceReport);
//        return prefix + "/edit";
//    }
//
//    /**
//     * 修改保存commercial_insurance_report
//     */
//    @RequiresPermissions("payroll:commercial_insurance_report:edit")
//    @Log(title = "修改保存商业保险", businessType = BusinessType.UPDATE)
//    @PostMapping("/edit")
//    @ResponseBody
//    public AjaxResult editSave(CommercialInsuranceReport commercialInsuranceReport)
//    {
//        return toAjax(iCommercialInsuranceReportService.updateCommercialInsuranceReport(commercialInsuranceReport));
//    }
//
//    /**
//     * 删除商业保险
//     */
//    @RequiresPermissions("payroll:commercial_insurance_report:remove")
//    @Log(title = "删除商业保险", businessType = BusinessType.DELETE)
//    @PostMapping( "/remove")
//    @ResponseBody
//    public AjaxResult remove(String ids)
//    {
//        return toAjax(iCommercialInsuranceReportService.deleteCommercialInsuranceReportByIds(ids));
//    }
}
