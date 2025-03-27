package com.nnroad.web.controller.timeline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.excel.EasyExcel;
import com.nnroad.common.annotation.Anonymous;
import com.nnroad.extraAttribute.domain.SysExtraAttribute;
import com.nnroad.extraAttribute.service.ISysExtraAttributeService;
import com.nnroad.system.domain.SysTableUpdate;
import com.nnroad.timeline.domain.SysTimeline;
// import com.nnroad.utils.ExcelExporter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.timeline.service.ISysTimelineService;
import com.nnroad.common.utils.poi.ExcelUtil;
import com.nnroad.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/system/timeline")
public class TimelineController extends BaseController
{
    @Autowired
    private ISysTimelineService sysTimelineService;

    private ISysExtraAttributeService sysExtraAttributeService;


    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:timeline:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysTimeline sysTimeline)
    {
        startPage();
        List<SysTimeline> list = sysTimelineService.selectSysTimelineList(sysTimeline);

        return getDataTable(list);
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:timeline:list')")
    @GetMapping("/timelineClientList")
    public ResponseEntity<List<Map<String, Object>>> timelineClientList()
    {
        List<Map<String, Object>> clientList = sysTimelineService.getTimelineClientList();

        return ResponseEntity.ok(clientList);
    }


    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:timeline:list')")
    @GetMapping("/externalVendorList")
    public TableDataInfo externalVendorList(SysTimeline sysTimeline)
    {
        startPage();
        List<SysTimeline> list = sysTimelineService.selectExternalVendorSysTimelineList(sysTimeline);
        return getDataTable(list);
    }


    /**
     *
     */
    @PreAuthorize("@ss.hasPermi('system:timeline:list')")
    @GetMapping("/sysTimelinePendingUpdate")
    public TableDataInfo listTimelinePendingUpdate()
    {
        startPage();
        List<SysTableUpdate> list = sysTimelineService.selectSysTimelinePendingChanges();
        return getDataTable(list);
    }

    /**
     *
     */
    @PreAuthorize("@ss.hasPermi('system:timeline:list')")
    @PostMapping("/acceptSysTimelinePendingUpdate")
    public AjaxResult acceptSysTimelinePendingUpdate()
    {
        startPage();
        return sysTimelineService.acceptSysTimelinePendingChanges();
    }


    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:timeline:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysTimeline sysTimeline) throws IOException {

    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:timeline:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysTimelineService.selectSysTimelineById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:timeline:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysTimeline sysTimeline)
    {
        return sysTimelineService.insertSysTimeline(sysTimeline);
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:timeline:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysTimeline sysTimeline)
    {
        return sysTimelineService.updateSysTimeline(sysTimeline);
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:timeline:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping("/editByColumn")
    public AjaxResult editByColumn(@RequestBody SysTimeline sysTimeline)
    {
        return sysTimelineService.updateSysTimelineByColumn(sysTimeline);
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:timeline:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping("/editMultipleByColumn")
    public AjaxResult editMultipleByColumn(@RequestBody List<SysTimeline> sysTimelines)
    {
        return sysTimelineService.updateMultipleSysTimelineByColumn(sysTimelines);
    }


    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:timeline:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping("/editExternalVendor")
    public AjaxResult editExternalVendor(@RequestBody SysTimeline sysTimeline)
    {
        return toAjax(sysTimelineService.updateSysExternalVendorTimeline(sysTimeline));
    }



    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:timeline:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysTimelineService.deleteSysTimelineByIds(ids));
    }

//    /**
//     * API for generating a timeline when we are the vendor
//     */
//    @PreAuthorize("@ss.hasPermi('system:timeline:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(SysTimeline sysTimeline)
//    {
//        startPage();
//        List<SysTimeline> list = sysTimelineService.selectSysTimelineList(sysTimeline);
//        return getDataTable(list);
//    }
}
