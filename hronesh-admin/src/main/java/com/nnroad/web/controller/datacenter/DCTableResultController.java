package com.nnroad.web.controller.datacenter;


import com.github.pagehelper.util.StringUtil;
import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.common.exception.BusinessException;
import com.nnroad.common.utils.MessageUtils;
import com.nnroad.datacenter.domain.*;
import com.nnroad.datacenter.service.IDCTableConfigService;
import com.nnroad.datacenter.service.IDCTableDataOperateLogService;
import com.nnroad.datacenter.service.IDCTableDataPullService;
import com.nnroad.datacenter.service.IDCTableService;
import com.nnroad.extraAttribute.domain.SysExtraAttribute;
import com.nnroad.extraAttribute.service.ISysExtraAttributeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/datacenter/tableResult")
@Slf4j
public class DCTableResultController extends BaseController {

    @Autowired
    private IDCTableConfigService dcTableConfigService;

    @Autowired
    private IDCTableDataOperateLogService dcTableResultLogService;

    @Autowired
    private IDCTableService dcTableService;

    @Autowired
    private IDCTableDataPullService dcTableDataPullService;
    @Autowired
    private ISysExtraAttributeService extraAttributeService;


    @PreAuthorize("@ss.hasPermi('datacenter:tableResult:show')")
    @GetMapping("/showTable/{tableId}")
    public AjaxResult showTable(@PathVariable("tableId") Long tableId) {
        Map<String, Object> result = new HashMap<>();
        DCTableConfig dcTableConfig = new DCTableConfig();
        dcTableConfig.setTableId(tableId);

        // 获取表头参数
        List<List<DCTableColumn>> viewTableColumns = dcTableConfigService.getListColumns(tableId, 0);
        result.put("columns", viewTableColumns);

        // 获取查询参数
        dcTableConfig.setColumnIsquery(1);
        List<DCTableConfig> searchParams = dcTableConfigService.selectDCTableConfigList(dcTableConfig);
        result.put("searchParams", searchParams);

        // 获取字典表数据
        Map<String, List<DCDictData>> dictLists = dcTableConfigService.getDictList(getDCTableConfigList(searchParams));
        result.put("dictLists", dictLists);

        // 获取表配置信息
        DCTable dcTable = dcTableService.selectDCTableByTableId(tableId);
        result.put("config", dcTable);


        // 获取数据拉取配置
        DCTableDataPullConfig dataPullConfig = dcTableDataPullService.selectConfigByTableId(tableId);
        result.put("dataPullConfig", dataPullConfig);
        result.put("dataPullEnable", dataPullConfig != null ? "1" : "0");


        // 设置表名称、数据权限及用户ID
        result.put("tableDbName", dcTable.getTableDbName());

        SysExtraAttribute sysExtraAttribute = new SysExtraAttribute();
        sysExtraAttribute.setTableName(dcTable.getTableDbName());
        List<SysExtraAttribute> extraColumn = extraAttributeService.selectSysExtraAttributeList(sysExtraAttribute);
        result.put("extraColumn", extraColumn);
        return AjaxResult.success(result);
    }


    /**
     * 查询业务表数据
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@ss.hasPermi('datacenter:tableResult:data')")
    public TableDataInfo list(@RequestParam Map<String, String> map) {
        return dcTableService.getGenTableColumnList(map, false);
    }


    private List<String> getDCTableConfigList(List<DCTableConfig> list) {
        List<String> dictList = new ArrayList<String>();
        for (DCTableConfig dcTableConfig : list) {
            if (!StringUtil.isEmpty(dcTableConfig.getColumnDictid())) {
                dictList.add(dcTableConfig.getColumnDictid());
            }
        }
        return dictList;
    }


    /**
     * 新增tableDefinition
     */
    @PreAuthorize("@ss.hasPermi('datacenter:tablerResult:add')")
    @Log(title = "tableResult", businessType = BusinessType.INSERT)
    @PostMapping("add/{tableId}")
    @ResponseBody
    public AjaxResult addTableData(@RequestBody Map<String, String> map, @PathVariable("tableId") Long tableId) {
        DCTable dcTable = dcTableService.selectDCTableByTableId(tableId);
        Long insertIndex = dcTableService.selectInsertIndex(null, dcTable);
        try {
            boolean canInsert = dcTableService.findDataIsDuplicated(tableId, map, false, 0L);
            if (canInsert) {
                map.put("sort_key", insertIndex.toString());
                dcTableService.formatParam(map, dcTable, false);
                try {
                    dcTableService.insertByMap(map, dcTable.getTableDbName());
                    dcTableResultLogService.insertByOne(map, tableId, null);
                    return AjaxResult.success();
                } catch (Exception e) {
//                    e.printStackTrace();
                    return AjaxResult.error("Add Failed");
                }
            } else {
                return AjaxResult.error("Failed to add! This data's datapulling is already exists!");
            }
        } catch (BusinessException e) {
            return AjaxResult.error("Add Failed");
        }
    }

    /**
     * 修改保存配置表
     */
    @PreAuthorize("@ss.hasPermi('datacenter:tableResult:edit')")
    @Log(title = "Table Result Edit", businessType = BusinessType.UPDATE)
    @PostMapping("/edit/{tableId}/{id}")
    @ResponseBody
    @Transactional
    public AjaxResult editSave(@PathVariable("tableId") Long tableId, @PathVariable("id") Long id, @RequestBody Map<String, String> map) throws Exception {
        DCTable paramTable = dcTableService.selectDCTableByTableId(tableId);
        Map<String, Object> oldColumn = dcTableService.selectUseTableDataById(id, paramTable);
        try {
            boolean canUpdate =  dcTableService.findDataIsDuplicated(tableId,map,true,id);
            if(canUpdate){
                if (oldColumn.containsKey("create_time")){
                    Object create_time = oldColumn.get("create_time");
                    if (create_time instanceof Date){
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String format = sdf.format(create_time);
                        map.put("create_time",format);
                    }
                }
                if (oldColumn.containsKey("create_by")){
                    map.put("create_by",oldColumn.get("create_by").toString());
                }
                map.put("id",id.toString());
                // 获取表结构
                DCTable dcTable = dcTableService.selectDCTableByTableId(tableId);
                dcTableService.formatParam(map,dcTable,false);
                try{
                    dcTableResultLogService.updateByOne(map,paramTable,null);
                    dcTableService.updateByMap(map,dcTable.getTableDbName());
                    return AjaxResult.success();
                }catch (Exception e){
                    log.error("datacenter edit error: ", e);
                    return AjaxResult.error(e.getMessage()) ;
                }

            }
            else {
                return AjaxResult.error(MessageUtils.message("datacenter.message.DataIsDuplicated")) ;
            }
        }catch (Exception e){
            return AjaxResult.error(e.getMessage()) ;
        }
    }


    @PreAuthorize("@ss.hasPermi('datacenter:tablerResult:remove')")
    @Log(title = "tablerResult", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tableId}/{id}")
    public AjaxResult remove(@PathVariable Long tableId,
                             @PathVariable String id) {
        return toAjax(dcTableService.delUseTableData(id, tableId));
    }

}
