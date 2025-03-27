package com.nnroad.web.controller.finance;

import com.nnroad.common.annotation.Anonymous;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.finance.domain.HroneAccount;
import com.nnroad.finance.model.form.FinanceQueryForm;
import com.nnroad.finance.service.HroneAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/finance/account")
@RequiredArgsConstructor
@Slf4j
public class HroneAccountController extends BaseController {

    private final HroneAccountService hroneAccountService;

    @PostMapping("/page")
    @RequiresPermissions("finance:account:page")
    public TableDataInfo page(HroneAccount hroneAccount) {
        startPage();
        List<HroneAccount> list = hroneAccountService.queryPage(hroneAccount);

        return getDataTable(list);
    }

    @GetMapping("/dict/list")
    @Anonymous
    public AjaxResult dictList() {

        return success(hroneAccountService.dictList());
    }

    @GetMapping("/detail/{accountId}")
    @RequiresPermissions("finance:account:page")
    public AjaxResult detail(@PathVariable Long accountId) {

        return success(hroneAccountService.detail(accountId));
    }

    @PutMapping("/modify")
    @RequiresPermissions("finance:account:modify")
    public AjaxResult modify(@Valid @RequestBody HroneAccount hroneAccount) {
        hroneAccountService.modify(hroneAccount);
        return success();
    }

    @PostMapping("/add")
    @RequiresPermissions("finance:account:add")
    public AjaxResult add(@Valid @RequestBody HroneAccount account) {

        hroneAccountService.add(account);
        return success();
    }

    @DeleteMapping("/del/{accId}")
    @RequiresPermissions("finance:account:delete")
    public AjaxResult del(@PathVariable Long accId) {
        hroneAccountService.delById(accId);
        return success();
    }

    @DeleteMapping("/del_batch")
    @RequiresPermissions("finance:account:delete")
    public AjaxResult delBatch(@RequestBody FinanceQueryForm form) {
        hroneAccountService.delBatchByIds(form.getIds());

        return success();
    }

    @PostMapping("/export")
    @RequiresPermissions("finance:account:export")
    public void export(@RequestBody HroneAccount hroneAccount,
                       HttpServletResponse response) {

        hroneAccountService.export(hroneAccount, response);
    }

    @GetMapping("/download_temp")
    @RequiresPermissions("finance:account:import")
    public void downloadTemp(HttpServletResponse response) {
        hroneAccountService.downloadTemp(response);
    }

    @PostMapping("/import")
    @RequiresPermissions("finance:account:import")
    public AjaxResult importAccount(@RequestParam("file") MultipartFile file) {

        hroneAccountService.importAccount(file);
        return success();
    }

    @PostMapping("/select/import/{type}")
    @RequiresPermissions("finance:account:select:import")
    public AjaxResult accountSelectImport(@RequestBody List<HroneAccount> accounts,
                                          @PathVariable String type) {
        hroneAccountService.accountSelectImport(accounts, type);
        return success();
    }
}
