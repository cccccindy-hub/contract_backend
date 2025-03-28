package main.java.com.nnroad.web.controller.contract;

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
import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.contract.domain.ContractPerface;
import com.nnroad.contract.service.IContractPerfaceService;
import com.nnroad.common.utils.poi.ExcelUtil;
import com.nnroad.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
@RestController
@RequestMapping("/system/perface")
public class ContractPerfaceController extends BaseController
{
    @Autowired
    private IContractPerfaceService contractPerfaceService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:perface:list')")
    @GetMapping("/list")
    public TableDataInfo list(ContractPerface contractPerface)
    {
        startPage();
        List<ContractPerface> list = contractPerfaceService.selectContractPerfaceList(contractPerface);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:perface:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ContractPerface contractPerface)
    {
        List<ContractPerface> list = contractPerfaceService.selectContractPerfaceList(contractPerface);
        ExcelUtil<ContractPerface> util = new ExcelUtil<ContractPerface>(ContractPerface.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:perface:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(contractPerfaceService.selectContractPerfaceByUserId(userId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:perface:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ContractPerface contractPerface)
    {
        return toAjax(contractPerfaceService.insertContractPerface(contractPerface));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:perface:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ContractPerface contractPerface)
    {
        return toAjax(contractPerfaceService.updateContractPerface(contractPerface));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:perface:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(contractPerfaceService.deleteContractPerfaceByUserIds(userIds));
    }
}

