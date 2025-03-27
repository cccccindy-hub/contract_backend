package com.nnroad.web.controller.payroll;

import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.common.utils.poi.HroneExcelUtil;
import com.nnroad.payroll.domain.exportC.IITReport;
import com.nnroad.payroll.service.IIITReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * iit_reportController
 * 
 * @author HROne
 * @date 2021-01-15
 */
@Controller
@RequestMapping("/payroll/iit_report")
public class IITReportController extends BaseController
{
    private String prefix = "payroll/iit_report";

    @Autowired
    private IIITReportService iitReportService;


    /**
     * 查询iit_report列表
     */
    @PreAuthorize("@ss.hasPermi('payroll:iit_report:list')")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody IITReport iitReport)
    {
        startPage();
        List<IITReport> list = iitReportService.selectIitReportList(iitReport);
        return getDataTable(list);
    }

    /**
     * 导出iit_report列表
     */
    @PreAuthorize("@ss.hasPermi('payroll:iit_report:export')")
    @Log(title = "iit_report", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody IITReport iitReport)
    {
        List<IITReport> list = iitReportService.selectIitReportList(iitReport);
        HroneExcelUtil<IITReport> util = new HroneExcelUtil<IITReport>(IITReport.class);
        return util.exportExcel(list, "iit_report");
    }

}
