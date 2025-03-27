package com.nnroad.web.controller.payroll;


import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.common.utils.poi.HroneExcelUtil;
import com.nnroad.payroll.domain.common.PsCalculationResult;
import com.nnroad.payroll.service.IPsCalculationResultService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 计算结果Controller
 */
@Controller
@RequestMapping("/payroll/PsCalculationResult")
public class PsCalculationResultController extends BaseController
{
    private String prefix = "payroll/PsCalculationResult";

    @Autowired
    private IPsCalculationResultService psCalculationResultService;
    /**
     * 查询计算结果列表
     */
    @PreAuthorize("@ss.hasPermi('payroll:PsCalculationResult:list')")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody PsCalculationResult psCalculationResult)
    {
        startPage();
        List<PsCalculationResult> list = psCalculationResultService.selectPsCalculationResultList(psCalculationResult);
        return getDataTable(list);
    }

    /**
     * 导出计算结果列表
     */
    @PreAuthorize("@ss.hasPermi('payroll:PsCalculationResult:export')")
    @Log(title = "计算结果", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(@RequestBody PsCalculationResult psCalculationResult)
    {
        List<PsCalculationResult> list = psCalculationResultService.selectPsCalculationResultList(psCalculationResult);
        HroneExcelUtil<PsCalculationResult> util = new HroneExcelUtil<PsCalculationResult>(PsCalculationResult.class);
        return util.exportExcel(list, "PsCalculationResult");
    }

}
