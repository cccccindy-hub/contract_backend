package com.nnroad.web.controller.payroll;

import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.common.utils.poi.HroneExcelUtil;
import com.nnroad.payroll.domain.exportC.UnionFeesReport;
import com.nnroad.payroll.service.IUnionFeesReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.nnroad.common.utils.PageUtils.startPage;

/**
 * 工会费Controller

 */
@Controller
@RequestMapping("/payroll/union_fees_report")
public class UnionFeesReportController extends BaseController
{
    private String prefix = "payroll/union_fees_report";

    @Autowired
    private IUnionFeesReportService unionFeesReportService;

    /**
     * 查询工会费列表
     */
    @PreAuthorize("@ss.hasPermi('payroll:union_fees_report:list')")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody UnionFeesReport unionFeesReport)
    {
        startPage();
        List<UnionFeesReport> list = unionFeesReportService.selectUnionFeesReportList(unionFeesReport);
        return getDataTable(list);
    }

    /**
     * 导出工会费列表
     */
    @PreAuthorize("@ss.hasPermi('payroll:union_fees_report:export')")
    @Log(title = "导出工会费列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody UnionFeesReport unionFeesReport)
    {
        List<UnionFeesReport> list = unionFeesReportService.selectUnionFeesReportList(unionFeesReport);
        HroneExcelUtil<UnionFeesReport> util = new HroneExcelUtil<UnionFeesReport>(UnionFeesReport.class);
        return util.exportExcel(list, "union_fees_report");
    }




}
