package com.nnroad.web.controller.payroll;

import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.common.utils.poi.HroneExcelUtil;
import com.nnroad.payroll.domain.exportC.AverageSalaryReport;
import com.nnroad.payroll.service.IAverageSalaryReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * AverageSalaryReportController
 */
@Controller
@RequestMapping("/payroll/AverageSalaryReport")
public class AverageSalaryReportController extends BaseController
{

    @Autowired
    private IAverageSalaryReportService averageSalaryReportService;

    /**
     * 查询AverageSalaryReport列表
     */
    @PreAuthorize("@ss.hasPermi('payroll:AverageSalaryReport:list')")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody AverageSalaryReport averageSalaryReport)
    {
        startPage();
        List<AverageSalaryReport> list = averageSalaryReportService.selectAverageSalaryReportList(averageSalaryReport);
        return getDataTable(list);
    }

    /**
     * 导出AverageSalaryReport列表
     */
    @PreAuthorize("@ss.hasPermi('payroll:AverageSalaryReport:export')")
    @Log(title = "AverageSalaryReport", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody AverageSalaryReport averageSalaryReport)
    {
        List<AverageSalaryReport> list = averageSalaryReportService.selectAverageSalaryReportList(averageSalaryReport);
        HroneExcelUtil<AverageSalaryReport> util = new HroneExcelUtil<AverageSalaryReport>(AverageSalaryReport.class);
        return util.exportExcel(list, "AverageSalaryReport");
    }

}
