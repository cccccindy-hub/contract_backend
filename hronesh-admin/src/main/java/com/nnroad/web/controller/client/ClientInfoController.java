package com.nnroad.web.controller.client;


import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.nnroad.client.domain.SysClient;
import com.nnroad.client.service.ISysClientService;
import com.nnroad.common.annotation.Anonymous;
import com.nnroad.employee.domain.EmployeeInfo;
import com.nnroad.employee.domain.SysEmployee;
import com.nnroad.employee.service.ISysEmployeeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.client.domain.ClientInfo;
import com.nnroad.client.service.ISysClientInfoService;
import com.nnroad.common.utils.poi.ExcelUtil;
import com.nnroad.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author nnroad
 * @date 2024-11-08
 */
@RestController
@RequestMapping("/clientInfo")
public class ClientInfoController extends BaseController
{
    @Autowired
    private ISysClientInfoService clientInfoService;

    @Autowired
    private ISysClientService sysClientService;

    /**
     * 查询【请填写功能名称】列表
     */
//    @Anonymous
    @PreAuthorize("@ss.hasPermi('client:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClientInfo clientInfo)
    {
        startPage();
        List<ClientInfo> list = clientInfoService.selectClientInfoList(clientInfo);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('client:info:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClientInfo clientInfo)
    {
        List<ClientInfo> list = clientInfoService.selectClientInfoList(clientInfo);
        ExcelUtil<ClientInfo> util = new ExcelUtil<ClientInfo>(ClientInfo.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @Anonymous
//    @PreAuthorize("@ss.hasPermi('client:info:query')")
    @GetMapping(value = "{tableName}/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id, @PathVariable("tableName") String tableName)
    {
        return success(clientInfoService.selectClientInfoById(id, tableName));
    }

    /**
     * 新增【请填写功能名称】
     */
//    @Anonymous
    @PreAuthorize("@ss.hasPermi('client:info:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClientInfo clientInfo)
    {
        return toAjax(clientInfoService.insertClientInfo(clientInfo));
    }

    /**
     * 修改【请填写功能名称】
     */
//    @Anonymous
    @PreAuthorize("@ss.hasPermi('client:info:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClientInfo clientInfo)
    {
        return toAjax(clientInfoService.updateClientInfo(clientInfo));
    }

    /**
     * 删除【请填写功能名称】
     */
    @Anonymous
//    @PreAuthorize("@ss.hasPermi('client:info:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("{tableName}/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids, @PathVariable("tableName") String tableName)
    {
        return toAjax(clientInfoService.deleteClientInfoByIds(ids, tableName));
    }

//    @Anonymous
    @PreAuthorize("@ss.hasPermi('client:sync:data')")
    @GetMapping("/sync-data")
    public AjaxResult getClientInfoSyncData(@RequestParam String org, @RequestParam String code, @RequestParam String tableName)
    {
        return success(clientInfoService.getClientOtherSyncData(org, code, tableName));
    }

    @Anonymous
//    @PreAuthorize("@ss.hasPermi('employee:add')")
    @PreAuthorize("@ss.hasPermi('client:sync:data')")
    @Log(title = "ClientInfo", businessType = BusinessType.INSERT)
    @PostMapping("/sync-data")
    public AjaxResult updateClientInfoBySync(@RequestBody ClientInfo clientInfo)
    {
        //check if the employee exists
        SysClient client = sysClientService.selectBasicByClientCode(clientInfo.getCompanyCode());
        // return error if the employee not exists
        if (client == null) {
            return AjaxResult.error("Please sync the associated client first.");
        }
        else{
            // check if the client info exists
            ClientInfo info = clientInfoService.selectClientInfoByCode(clientInfo.getCompanyCode(), clientInfo.getTableName());
            // update the client info if exists
            if (info != null) {
                clientInfo.setClientId(client.getId());
                return success(clientInfoService.updateClientInfoBySync(clientInfo));
            }
            // add the employee info if not exist
            else{
                clientInfo.setClientId(client.getId());
                return success(clientInfoService.insertClientInfoBySync(clientInfo));
            }
        }
    }
}

