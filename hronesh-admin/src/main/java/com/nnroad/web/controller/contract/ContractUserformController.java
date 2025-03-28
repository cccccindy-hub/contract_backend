package com.nnroad.web.controller.contract;

// package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.nnroad.contract.IContractUserformService; // Ensure this is the correct package for IContractUserformService
import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.common.utils.poi.ExcelUtil;
import com.nnroad.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2025-03-25
 */
@RestController
@RequestMapping("/system/userform")
public class ContractUserformController extends BaseController
{
    @Autowired
    private IContractUserformService contractUserformService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:userform:list')")
    @GetMapping("/list")
    public TableDataInfo list(ContractUserform contractUserform)
    {
        startPage();
        List<ContractUserform> list = contractUserformService.selectContractUserformList(contractUserform);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:userform:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ContractUserform contractUserform)
    {
        List<ContractUserform> list = contractUserformService.selectContractUserformList(contractUserform);
        ExcelUtil<ContractUserform> util = new ExcelUtil<ContractUserform>(ContractUserform.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:userform:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") String userId)
    {
        return success(contractUserformService.selectContractUserformByUserId(userId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:userform:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ContractUserform contractUserform)
    {
        return toAjax(contractUserformService.insertContractUserform(contractUserform));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:userform:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ContractUserform contractUserform)
    {
        return toAjax(contractUserformService.updateContractUserform(contractUserform));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:userform:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable String[] userIds)
    {
        return toAjax(contractUserformService.deleteContractUserformByUserIds(userIds));
    }
}
