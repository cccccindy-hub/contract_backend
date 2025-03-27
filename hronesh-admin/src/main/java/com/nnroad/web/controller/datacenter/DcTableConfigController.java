package com.nnroad.web.controller.datacenter;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.nnroad.datacenter.domain.DCTable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.datacenter.domain.DCTableConfigTemp;
import com.nnroad.datacenter.service.IDCTableConfigTempService;
import com.nnroad.common.utils.poi.ExcelUtil;
import com.nnroad.common.core.page.TableDataInfo;

/**
 * columnConfigTempController
 *
 * @author Sheng
 * @date 2024-11-12
 */
@RestController
@RequestMapping("/datacenter/config")
public class DcTableConfigController extends BaseController
{
    @Autowired
    private IDCTableConfigTempService dcTableConfigTempService;

    /**
     * 查询columnConfigTemp列表
     */
    @PreAuthorize("@ss.hasPermi('columnConfigTemp:columnConfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(DCTableConfigTemp dcTableConfigTemp)
    {
        List<DCTableConfigTemp> list = dcTableConfigTempService.selectDCTableConfigTempList(dcTableConfigTemp);
        return getDataTable(list);
    }

    /**
     * 查询columnConfigTemp列表
     */
    @PreAuthorize("@ss.hasPermi('columnConfigTemp:columnConfig:listTemp')")
    @PostMapping("/listTemp")
    public TableDataInfo listTemp(@RequestBody DCTableConfigTemp dcTableConfigTemp)
    {
        List<DCTableConfigTemp> list = dcTableConfigTempService.selectDCTableConfigTempList(dcTableConfigTemp);
        return getDataTable(list);
    }


    /**
     * 导出columnConfigTemp列表
     */
    @PreAuthorize("@ss.hasPermi('columnConfigTemp:columnConfig:export')")
    @Log(title = "columnConfigTemp", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DCTableConfigTemp dcTableConfigTemp)
    {
        List<DCTableConfigTemp> list = dcTableConfigTempService.selectDCTableConfigTempList(dcTableConfigTemp);
        ExcelUtil<DCTableConfigTemp> util = new ExcelUtil<DCTableConfigTemp>(DCTableConfigTemp.class);
        util.exportExcel(response, list, "columnConfigTemp数据");
    }

    /**
     * 获取ColumnConfig详细信息
     */
    @PreAuthorize("@ss.hasPermi('datacenter:config:query')")
    @GetMapping(value = "/{columnId}")
    public AjaxResult getInfo(@PathVariable("columnId") Long columnId)
    {
        return success(dcTableConfigTempService.selectDCTableConfigTempById(columnId));
    }


    /**
     * 新增columnConfigTemp
     */
    @PreAuthorize("@ss.hasPermi('columnConfigTemp:columnConfig:add')")
    @Log(title = "columnConfigTemp", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DCTableConfigTemp dcTableConfigTemp)
    {
        return dcTableConfigTempService.insertDCTableConfigTemp(dcTableConfigTemp);
    }

    /**
     * 修改tableDefinition
     */
    @PreAuthorize("@ss.hasPermi('datacenter:config:editTemp')")
    @Log(title = "columnConfig", businessType = BusinessType.UPDATE)
    @PostMapping("/editTemp")
    public AjaxResult edit(@RequestBody DCTableConfigTemp dcTableConfigTemp)
    {
        return dcTableConfigTempService.updateDCTableConfigTemp(dcTableConfigTemp);
    }


    @PreAuthorize("@ss.hasAnyPermi('datacenter:config:remove')")
    @Log(title = "remove ColumnTemp", businessType = BusinessType.DELETE)
    @GetMapping("/removeTemp/{tableId}/{columnId}")
    public AjaxResult removeTemp(@PathVariable("columnId") Long columnId,@PathVariable("tableId") Long tableId){
        if (dcTableConfigTempService.selectCountByParentId(columnId)>0)
        {
            return AjaxResult.warn("This field contains subfields.");
        }
        return toAjax(dcTableConfigTempService.deleteDCTableConfigTempById(columnId,tableId));
    }


}


