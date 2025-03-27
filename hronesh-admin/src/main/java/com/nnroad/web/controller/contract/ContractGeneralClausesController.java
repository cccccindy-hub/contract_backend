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
import com.nnroad.system.domain.ContractGeneralClauses;
import com.nnroad.system.service.IContractGeneralClausesService;
import com.nnroad.common.utils.poi.ExcelUtil;
import com.nnroad.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
@RestController
@RequestMapping("/system/clauses")
public class ContractGeneralClausesController extends BaseController
{
    @Autowired
    private IContractGeneralClausesService contractGeneralClausesService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:clauses:list')")
    @GetMapping("/list")
    public TableDataInfo list(ContractGeneralClauses contractGeneralClauses)
    {
        startPage();
        List<ContractGeneralClauses> list = contractGeneralClausesService.selectContractGeneralClausesList(contractGeneralClauses);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:clauses:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ContractGeneralClauses contractGeneralClauses)
    {
        List<ContractGeneralClauses> list = contractGeneralClausesService.selectContractGeneralClausesList(contractGeneralClauses);
        ExcelUtil<ContractGeneralClauses> util = new ExcelUtil<ContractGeneralClauses>(ContractGeneralClauses.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:clauses:query')")
    @GetMapping(value = "/{userid}")
    public AjaxResult getInfo(@PathVariable("userid") String userid)
    {
        return success(contractGeneralClausesService.selectContractGeneralClausesByUserid(userid));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:clauses:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ContractGeneralClauses contractGeneralClauses)
    {
        return toAjax(contractGeneralClausesService.insertContractGeneralClauses(contractGeneralClauses));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:clauses:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ContractGeneralClauses contractGeneralClauses)
    {
        return toAjax(contractGeneralClausesService.updateContractGeneralClauses(contractGeneralClauses));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:clauses:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userids}")
    public AjaxResult remove(@PathVariable String[] userids)
    {
        return toAjax(contractGeneralClausesService.deleteContractGeneralClausesByUserids(userids));
    }
}
