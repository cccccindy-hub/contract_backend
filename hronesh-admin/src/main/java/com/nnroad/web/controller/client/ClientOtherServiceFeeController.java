package com.nnroad.web.controller.client;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.nnroad.common.annotation.Anonymous;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.client.domain.ClientOtherServiceFee;
import com.nnroad.client.service.IClientOtherServiceFeeService;
import com.nnroad.common.utils.poi.ExcelUtil;
import com.nnroad.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2024-12-17
 */
@RestController
@RequestMapping("/client/otherServiceFee")
public class ClientOtherServiceFeeController extends BaseController
{
    @Autowired
    private IClientOtherServiceFeeService clientOtherServiceFeeService;

    /**
     * 查询【请填写功能名称】列表
     */
    @Anonymous
//    @PreAuthorize("@ss.hasPermi('client:otherService:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClientOtherServiceFee clientOtherServiceFee)
    {
        startPage();
        List<ClientOtherServiceFee> list = clientOtherServiceFeeService.selectClientOtherServiceFeeList(clientOtherServiceFee);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @Anonymous
//    @PreAuthorize("@ss.hasPermi('client:otherService:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClientOtherServiceFee clientOtherServiceFee)
    {
        List<ClientOtherServiceFee> list = clientOtherServiceFeeService.selectClientOtherServiceFeeList(clientOtherServiceFee);
        ExcelUtil<ClientOtherServiceFee> util = new ExcelUtil<ClientOtherServiceFee>(ClientOtherServiceFee.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @Anonymous
//    @PreAuthorize("@ss.hasPermi('client:otherService:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(clientOtherServiceFeeService.selectClientOtherServiceFeeById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @Anonymous
//    @PreAuthorize("@ss.hasPermi('client:otherService:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClientOtherServiceFee clientOtherServiceFee)
    {
        return toAjax(clientOtherServiceFeeService.insertClientOtherServiceFee(clientOtherServiceFee));
    }

    /**
     * 修改【请填写功能名称】
     */
    @Anonymous
//    @PreAuthorize("@ss.hasPermi('client:otherService:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClientOtherServiceFee clientOtherServiceFee)
    {
        return toAjax(clientOtherServiceFeeService.updateClientOtherServiceFee(clientOtherServiceFee));
    }

    /**
     * 删除【请填写功能名称】
     */
    @Anonymous
//    @PreAuthorize("@ss.hasPermi('client:otherService:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(clientOtherServiceFeeService.deleteClientOtherServiceFeeByIds(ids));
    }
}
