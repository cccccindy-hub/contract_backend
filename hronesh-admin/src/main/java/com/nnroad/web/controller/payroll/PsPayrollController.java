package com.nnroad.web.controller.payroll;

import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.payroll.domain.common.PsPayroll;
import com.nnroad.payroll.service.IPsPayrollService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ps PayrollController
 */
@Controller
@RequestMapping("/payroll/PsPayroll")
public class PsPayrollController extends BaseController
{
    @Autowired
    private IPsPayrollService psPayrollService;


    /**
     * 查询Ps PayRoll
     */
    //权限标识
    @PreAuthorize("@ss.hasPermi('payroll:PsPayroll:list')")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestBody PsPayroll psPayroll)
    {
        startPage();
        List<PsPayroll> list = psPayrollService.selectPsPayrollListBySource(psPayroll);
        return getDataTable(list);
    }

    /**
     * 修改保存基本信息
     */
    //权限标识
    @PreAuthorize("@ss.hasPermi('payroll:PsPayroll:edit')")
    @Log(title = "payroll信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody PsPayroll psPayroll)
    {
        int result = psPayrollService.updatePsPayroll(psPayroll);
        return toAjax(result);
    }


    /**
     * 修改保存基本信息
     */
    //权限标识
    @PreAuthorize("@ss.hasPermi('payroll:PsPayroll:edit')")
    @Log(title = "payroll信息", businessType = BusinessType.UPDATE)
    @PostMapping("/editBatch")
    @ResponseBody
    public AjaxResult editBatchSave(@RequestBody List<PsPayroll> psPayrollList)
    {
        int result = psPayrollService.updateBatchPsPayroll(psPayrollList);
        return toAjax(result);
    }
}
