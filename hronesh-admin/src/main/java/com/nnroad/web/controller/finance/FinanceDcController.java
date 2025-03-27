package com.nnroad.web.controller.finance;

import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.datacenter.common.TableTypeEnum;
import com.nnroad.datacenter.domain.DCTable;
import com.nnroad.datacenter.service.IDCTableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/finance/dc_table")
@Slf4j
@RequiredArgsConstructor
public class FinanceDcController extends BaseController {

    private final IDCTableService dcTableService;

    @PreAuthorize("@ss.hasPermi('dc:def:finance_accounts')")
    @PostMapping("/accounts")
    public TableDataInfo accountsDCList(@RequestBody DCTable dcTable) {
        dcTable.setTableType(TableTypeEnum.FINANCE_ACCOUNT.getCode());
        dcTable.setTableIsgen(1);
        startPage();
        List<DCTable> list = dcTableService.selectDcTableList(dcTable);

        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('dc:def:finance_reports')")
    @PostMapping("/reports")
    public TableDataInfo BillingList(@RequestBody DCTable dcTable) {
        dcTable.setTableType(TableTypeEnum.FINANCE_REPORT.getCode());
        dcTable.setTableIsgen(1);
        startPage();
        List<DCTable> list = dcTableService.selectDcTableList(dcTable);

        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('dc:def:config:finance_accounts')")
    @PostMapping("/config/accounts")
    public TableDataInfo configAccounts(@RequestBody DCTable dcTable) {
        dcTable.setTableType(TableTypeEnum.FINANCE_ACCOUNT.getCode());
        startPage();
        List<DCTable> list = dcTableService.selectDcTableList(dcTable);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('dc:def:config:finance_reports')")
    @PostMapping("/config/reports")
    public TableDataInfo configReports(@RequestBody DCTable dcTable) {
        dcTable.setTableType(TableTypeEnum.FINANCE_REPORT.getCode());
        startPage();
        List<DCTable> list = dcTableService.selectDcTableList(dcTable);
        return getDataTable(list);
    }

}
