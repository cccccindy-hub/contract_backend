package com.nnroad.web.controller.datacenter;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.nnroad.datacenter.common.TableTypeEnum;
import com.nnroad.datacenter.service.IDCTableTempService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.datacenter.domain.DCTable;
import com.nnroad.datacenter.service.IDCTableService;
import com.nnroad.common.utils.poi.ExcelUtil;
import com.nnroad.common.core.page.TableDataInfo;

/**
 * tableDefinitionController
 * 
 * @author Sheng
 * @date 2024-11-11
 */
@RestController
@RequestMapping("/datacenter/tableDefinition")
public class DcTableController extends BaseController
{
    @Autowired
    private IDCTableService dcTableService;

    @Autowired
    private IDCTableTempService dcTableTempService;

    /**
     * 查询tableDefinition列表
     */
    @PreAuthorize("@ss.hasPermi('datacenter:tableDefinition:list')")
    @GetMapping("/list")
    public TableDataInfo list(DCTable dcTable)
    {
        startPage();
        List<DCTable> list = dcTableService.selectDcTableList(dcTable);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('datacenter:tableDefinition:Billinglist')")
    @GetMapping("/listAuth")
    public TableDataInfo BillingList() {
        startPage();
        List<DCTable> list = dcTableService.selectDCTableByTableType(TableTypeEnum.PAYSTUB.getCode());
        return getDataTable(list);
    }

    /**
     * 导出tableDefinition列表
     */
    @PreAuthorize("@ss.hasPermi('datacenter:tableDefinition:export')")
    @Log(title = "tableDefinition", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DCTable dcTable)
    {
        List<DCTable> list = dcTableService.selectDcTableList(dcTable);
        ExcelUtil<DCTable> util = new ExcelUtil<DCTable>(DCTable.class);
        util.exportExcel(response, list, "tableDefinition数据");
    }

    /**
     * 获取tableDefinition详细信息
     */
    @PreAuthorize("@ss.hasPermi('datacenter:tableDefinition:query')")
    @GetMapping(value = "/{tableId}")
    public AjaxResult getInfo(@PathVariable("tableId") Long tableId)
    {
        return success(dcTableService.selectDCTableByTableId(tableId));
    }

    /**
     * 新增tableDefinition
     */
    @PreAuthorize("@ss.hasPermi('datacenter:tableDefinition:add')")
    @Log(title = "tableDefinition", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DCTable dcTable)
    { if(dcTableService.insertDCTable(dcTable)!=0){
        return success();
    }else {
        return error("业务表名称已存在!请重新输入便于区分的业务表名称");
    }
    }

    /**
     * 修改tableDefinition
     */
    @PreAuthorize("@ss.hasPermi('datacenter:tableDefinition:edit')")
    @Log(title = "tableDefinition", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DCTable dcTable)
    {
        return toAjax(dcTableService.updateDCTable(dcTable));
    }

    /**
     * 删除tableDefinition
     */
    @PreAuthorize("@ss.hasPermi('datacenter:tableDefinition:remove')")
    @Log(title = "tableDefinition", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tableIds}")
    public AjaxResult remove(@PathVariable Long[] tableIds)
    {
        return toAjax(dcTableService.deleteDcTableByTableIds(tableIds));
    }


    @PreAuthorize("@ss.hasPermi('datacenter:tableDefinition:tableType')")
    @GetMapping("/tableTypeOptions")
    public AjaxResult getTableTypeOptions() {
        // 获取 tableType 字典数据
        List<Map<String, Object>> dictArray = TableTypeEnum.toList();
        return AjaxResult.success(dictArray);
    }

    /**
     * 同步表格
     */
    @PreAuthorize("@ss.hasPermi('datacenter:tableDefinition:synTable')")
    @PostMapping("/synTable")
    public AjaxResult synTable(@RequestBody DCTable dcTable) {
        return dcTableService.generateOrChangeTable(dcTable);
    }


    /**
     * 生成表格
     */
    @PreAuthorize("@ss.hasPermi('datacenter:tableDefinition:generate')")
    @PostMapping("/generateOrChangeTable")
    public AjaxResult generateOrChangeTable(@RequestBody DCTable dcTable) {
        return dcTableService.generateOrChangeTable(dcTable);
    }

    /**
     * 生成表格
     */
    @PreAuthorize("@ss.hasPermi('datacenter:tableDefinition:stopTable')")
    @PostMapping("/stopTable")
    public AjaxResult stopTable(@RequestBody DCTable dcTable) {
        return toAjax(dcTableService.updateDCTable(dcTable));
    }

//    /**
//     * 查询临时报表
//     */
//    @PreAuthorize("@ss.hasPermi('datacenter:tableDefinition:tempTable')")
//    @PostMapping("/Temp")
//    public AjaxResult TempTable(@PathVariable("tableId") Long tableId) {
//        return toAjax(dcTableTempService.selectDCTableTempById(tableId));
//    }
//


}


