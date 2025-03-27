package com.nnroad.web.controller.extraAttribute;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.nnroad.common.core.domain.entity.SysDictData;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nnroad.common.annotation.Anonymous;
import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.extraAttribute.domain.SysExtraAttribute;
import com.nnroad.extraAttribute.service.ISysExtraAttributeService;
import com.nnroad.common.utils.poi.ExcelUtil;
import com.nnroad.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author nnroad
 * @date 2024-10-16
 */
@RestController
@RequestMapping("/system/attribute")
public class ExtraAttributeController extends BaseController
{
    @Autowired
    private ISysExtraAttributeService sysExtraAttributeService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('attribute:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysExtraAttribute sysExtraAttribute)
    {
        startPage();
        List<SysExtraAttribute> list = sysExtraAttributeService.selectSysExtraAttributeList(sysExtraAttribute);
        return getDataTable(list);
    }
    
    /**
     * Get list of attributes by table name in hierarchy
     */
    //@PreAuthorize("@ss.hasPermi('attribute:query')")
    @Anonymous
    @GetMapping(value = "/listInHierarchy")
    public TableDataInfo getListByTableName(@RequestParam("tableName") String tableName, @RequestParam(value = "stage", required = false) String stage)
    {
    	List<SysExtraAttribute> list = sysExtraAttributeService.selectSysExtraAttributeInHierarchy(tableName, stage);
    	return getDataTable(list);
    }

    @Anonymous
    @GetMapping(value = "/employeeOnboard/list")
    public TableDataInfo getEEOnboardList()
    {
        List<SysExtraAttribute> list = sysExtraAttributeService.selectEEOnboardList();
        return getDataTable(list);
    }

    @Anonymous
    @GetMapping(value = "/dicts/list")
    public Map<String, List<SysDictData>> getDicts(@RequestParam("tableName") String tableName) {
        Map<String, List<SysDictData>> dictsMap = sysExtraAttributeService.getDicts(tableName);
        return dictsMap;
    }
    
    /**
     * Get list of parent attributes
     */
    @Anonymous
    @GetMapping(value = "/parentList/{tableName}")
    public TableDataInfo getParentListByTableName(@PathVariable("tableName") String tableName)
    {
    	List<SysExtraAttribute> list = sysExtraAttributeService.selectParentAttribute(tableName);
    	return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('attribute:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysExtraAttribute sysExtraAttribute)
    {
        List<SysExtraAttribute> list = sysExtraAttributeService.selectSysExtraAttributeList(sysExtraAttribute);
        ExcelUtil<SysExtraAttribute> util = new ExcelUtil<SysExtraAttribute>(SysExtraAttribute.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    //@PreAuthorize("@ss.hasPermi('attribute:query')")
    @Anonymous
    @GetMapping(value = "{tableName}/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id, @PathVariable("tableName") String tableName)
    {
        return success(sysExtraAttributeService.selectSysExtraAttributeById(id, tableName));
    }
    

    /**
     * 新增【请填写功能名称】
     */
    //@PreAuthorize("@ss.hasPermi('attribute:add')")
    @Anonymous
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysExtraAttribute sysExtraAttribute)
    {
        return toAjax(sysExtraAttributeService.insertSysExtraAttribute(sysExtraAttribute));
    }
    
//    /**
//     * Insert a record into the specified table
//     */
//    @Anonymous
//    @Log(title = "Dynamic Insert", businessType = BusinessType.INSERT)
//    @PostMapping("/{tableName}")
//    public AjaxResult add(@PathVariable("tableName") String tableName, @RequestBody SysExtraAttribute sysExtraAttribute)
//    {
//        return toAjax(sysExtraAttributeService.insertSysExtraAttributeCustom(tableName, sysExtraAttribute));
//    }

    /**
     * 修改【请填写功能名称】
     */
    //@PreAuthorize("@ss.hasPermi('attribute:edit')")
    @Anonymous
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysExtraAttribute sysExtraAttribute)
    {
        return toAjax(sysExtraAttributeService.updateSysExtraAttribute(sysExtraAttribute));
    }
    
    /**
     * 删除【请填写功能名称】
     */
    //@PreAuthorize("@ss.hasPermi('attribute:remove')")
    @Anonymous
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("{tableName}/{id}")
    public AjaxResult remove(@PathVariable("id") Long id, @PathVariable("tableName") String tableName)
    {
        return toAjax(sysExtraAttributeService.deleteSysExtraAttributeById(id, tableName));
    }
}
