package com.nnroad.web.controller.finance;

import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.exception.Asserts;
import com.nnroad.finance.domain.BankBeginningBalance;
import com.nnroad.finance.service.IBankBeginningBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/finance/bank")
public class BankBeginningBalanceController extends BaseController {

    private final IBankBeginningBalanceService balanceService;

    @PostMapping("/beginning/page")
    @PreAuthorize("@ss.hasPermi('finance:bank:page')")
    public TableDataInfo beginningPage(BankBeginningBalance balance) {

        return getDataTable(balanceService.queryPage(balance));
    }

    @GetMapping("/beginning/detail/{id}")
    @PreAuthorize("@ss.hasPermi('finance:bank:edit')")
    public AjaxResult beginningDetail(@PathVariable Long id) {

        return success(balanceService.detail(id));
    }

    @PutMapping("/beginning/edit")
    @PreAuthorize("@ss.hasPermi('finance:bank:edit')")
    public AjaxResult beginningEdit(@Valid @RequestBody BankBeginningBalance balance) {
        Asserts.notNull(balance.getId(), "Require args error");
        balanceService.edit(balance);
        return success();
    }

    @PostMapping("/beginning/add")
    @PreAuthorize("@ss.hasPermi('finance:bank:add')")
    public AjaxResult beginningAdd(@Valid @RequestBody BankBeginningBalance balance) {
        balanceService.add(balance);
        return success();
    }

    @DeleteMapping("/beginning/del/{id}")
    @PreAuthorize("@ss.hasPermi('finance:bank:del')")
    public AjaxResult del(@PathVariable Long id) {
        balanceService.del(id);

        return success();
    }
}
