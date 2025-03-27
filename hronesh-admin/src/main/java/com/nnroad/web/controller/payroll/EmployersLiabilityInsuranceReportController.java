package com.nnroad.web.controller.payroll;

import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.common.utils.poi.HroneExcelUtil;
import com.nnroad.payroll.domain.exportC.EmployersLiabilityInsuranceReport;
import com.nnroad.payroll.service.IEmployersLiabilityInsuranceReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.nnroad.common.core.domain.AjaxResult;

import java.util.List;

/**
 * 雇主责任险Controller
 * 
 * @author liwengang
 * @date 2022-01-05
 */
@Controller
@RequestMapping("/payroll/employers_liability_insurance_report")
public class EmployersLiabilityInsuranceReportController extends BaseController
{
    private String prefix = "payroll/employers_liability_insurance_report";

    @Autowired
    private IEmployersLiabilityInsuranceReportService employersLiabilityInsuranceService;

    /**
     * 查询雇主责任险列表
     */
    @PreAuthorize("@ss.hasPermi('payroll:employers_liability_insurance_report:list')")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody EmployersLiabilityInsuranceReport employersLiabilityInsuranceReport)
    {
        startPage();
        List<EmployersLiabilityInsuranceReport> list = employersLiabilityInsuranceService.selectEmployersLiabilityInsuranceReportList(employersLiabilityInsuranceReport);
        return getDataTable(list);
    }

    /**
     * 导出雇主责任险列表
     */
    @PreAuthorize("@ss.hasPermi('payroll:employers_liability_insurance_report:export')")
    @Log(title = "雇主责任险", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody EmployersLiabilityInsuranceReport employersLiabilityInsuranceReport)
    {
        List<EmployersLiabilityInsuranceReport> list = employersLiabilityInsuranceService.selectEmployersLiabilityInsuranceReportList(employersLiabilityInsuranceReport);
        HroneExcelUtil<EmployersLiabilityInsuranceReport> util = new HroneExcelUtil<EmployersLiabilityInsuranceReport>(EmployersLiabilityInsuranceReport.class);
        return util.exportExcel(list, "employers_liability_insurance_report");
    }

    /**
     * 新增雇主责任险
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存雇主责任险
     */
    @RequiresPermissions("payroll:employers_liability_insurance_report:add")
    @Log(title = "雇主责任险", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EmployersLiabilityInsuranceReport employersLiabilityInsuranceReport)
    {
        return toAjax(employersLiabilityInsuranceService.insertEmployersLiabilityInsuranceReport(employersLiabilityInsuranceReport));
    }

    /**
     * 修改雇主责任险
     */
    @GetMapping("/edit/{name}")
    public String edit(@PathVariable("name") String name, ModelMap mmap)
    {
        EmployersLiabilityInsuranceReport employersLiabilityInsuranceReport = employersLiabilityInsuranceService.selectEmployersLiabilityInsuranceReportById(name);
        mmap.put("EmployersLiabilityInsuranceReport", employersLiabilityInsuranceReport);
        return prefix + "/edit";
    }

    /**
     * 修改保存雇主责任险
     */
    @RequiresPermissions("payroll:employers_liability_insurance_report:edit")
    @Log(title = "雇主责任险", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EmployersLiabilityInsuranceReport employersLiabilityInsuranceReport)
    {
        return toAjax(employersLiabilityInsuranceService.updateEmployersLiabilityInsuranceReport(employersLiabilityInsuranceReport));
    }

    /**
     * 删除雇主责任险
     */
    @RequiresPermissions("payroll:employers_liability_insurance_report:remove")
    @Log(title = "雇主责任险", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(employersLiabilityInsuranceService.deleteEmployersLiabilityInsuranceReportByIds(ids));
    }
}
