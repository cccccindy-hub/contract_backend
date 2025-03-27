package com.nnroad.web.controller.employee;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.nnroad.client.domain.SysClient;
import com.nnroad.common.annotation.Anonymous;
import com.nnroad.employee.domain.SysEmployee;
import com.nnroad.employee.service.ISysEmployeeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.employee.domain.EmployeeInfo;
import com.nnroad.employee.service.EmployeeInfoService;
import com.nnroad.common.utils.poi.ExcelUtil;
import com.nnroad.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author nnroad
 * @date 2024-11-13
 */
@RestController
@RequestMapping("/employeeInfo")
public class EmployeeInfoController extends BaseController
{
    @Autowired
    private EmployeeInfoService employeeInfoService;

    @Autowired
    private ISysEmployeeService sysEmployeeService;

    /**
     * 查询【请填写功能名称】列表
     */
//    @Anonymous
    @PreAuthorize("@ss.hasPermi('employee:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(EmployeeInfo employeeInfo)
    {
        startPage();
        List<EmployeeInfo> list = employeeInfoService.selectEmployeeInfoList(employeeInfo);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('employee:info:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EmployeeInfo employeeInfo)
    {
        List<EmployeeInfo> list = employeeInfoService.selectEmployeeInfoList(employeeInfo);
        ExcelUtil<EmployeeInfo> util = new ExcelUtil<EmployeeInfo>(EmployeeInfo.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
//    @Anonymous
    @PreAuthorize("@ss.hasPermi('employee:info:edit')")
    @GetMapping(value = "{tableName}/{id}")
    public AjaxResult getInfo(@PathVariable("tableName") String tableName, @PathVariable("id") Long id)
    {
        return success(employeeInfoService.selectEmployeeInfoById(id, tableName));
    }

    @PreAuthorize("@ss.hasPermi('employee:sync:data')")
//    @Anonymous
    @GetMapping("/sync-data")
    public AjaxResult getEmployeeInfoSyncData(@RequestParam String org, @RequestParam String code, @RequestParam String tableName)
    {
        return success(employeeInfoService.getEmployeeOtherSyncData(org, code, tableName));
    }

//    @Anonymous
    @PreAuthorize("@ss.hasPermi('employee:sync:data')")
    @Log(title = "EmployeeInfo", businessType = BusinessType.INSERT)
    @PostMapping("/sync-data")
    public AjaxResult updateEmployeeInfoBySync(@RequestBody EmployeeInfo employeeInfo)
    {
        //check if the employee exists
        SysEmployee employee = sysEmployeeService.selectEmployeeByCode(employeeInfo.getEmployeeCode());
        // return error if the employee not exists
        if (employee == null) {
            return AjaxResult.error("Please sync the associated employee first.");
        }
        else{
            // check if the employee info exists
            EmployeeInfo info = employeeInfoService.selectEmployeeInfoByCode(employeeInfo.getEmployeeCode(), employeeInfo.getTableName());
            // update the employee info if exists
            if (info != null) {
                employeeInfo.setEmployeeId(employee.getId());
                return success(employeeInfoService.updateEmployeeInfoBySync(employeeInfo));
            }
            // add the employee info if not exist
            else{
                employeeInfo.setEmployeeId(employee.getId());
                return success(employeeInfoService.insertEmployeeInfoBySync(employeeInfo));
            }
        }
    }

    /**
     * 新增【请填写功能名称】
     */
//    @Anonymous
    @PreAuthorize("@ss.hasPermi('employee:info:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EmployeeInfo employeeInfo)
    {
        return toAjax(employeeInfoService.insertEmployeeInfo(employeeInfo));
    }

    /**
     * 修改【请填写功能名称】
     */
//    @Anonymous
    @PreAuthorize("@ss.hasPermi('employee:info:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EmployeeInfo employeeInfo)
    {
        return toAjax(employeeInfoService.updateEmployeeInfo(employeeInfo));
    }

    /**
     * 删除【请填写功能名称】
     */
//    @Anonymous
    @PreAuthorize("@ss.hasPermi('employee:info:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("{tableName}/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids, @PathVariable String tableName)
    {
        return toAjax(employeeInfoService.deleteEmployeeInfoByIds(ids, tableName));
    }
}
