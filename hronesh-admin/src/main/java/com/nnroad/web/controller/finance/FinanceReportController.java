package com.nnroad.web.controller.finance;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.nnroad.common.annotation.Anonymous;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.domain.entity.SysDictData;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.enums.NumEnum;
import com.nnroad.common.exception.ServiceException;
import com.nnroad.common.utils.poi.ExcelUtil;
import com.nnroad.finance.constants.ConstantKey;
import com.nnroad.finance.domain.HroneAccount;
import com.nnroad.finance.model.dto.BankMonthlyReportDto;
import com.nnroad.finance.model.form.BankMonthlyReportForm;
import com.nnroad.finance.model.form.FinMonthlyStatisticsPageForm;
import com.nnroad.finance.model.form.FinanceQueryForm;
import com.nnroad.finance.model.vo.FinMonthlyStatisticsVo;
import com.nnroad.finance.service.IReportService;
import com.nnroad.system.service.ISysDictDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/finance/report")
@RequiredArgsConstructor
@Slf4j
public class FinanceReportController extends BaseController {

    private final IReportService reportService;

    private final ISysDictDataService dictDataService;

    @PostMapping("/bank/monthly/page")
    @RequiresPermissions("finance:report:bank:page")
    public TableDataInfo monthlyReport(BankMonthlyReportForm form) {
        startPage();
        List<BankMonthlyReportDto> reports = reportService.monthlyReports(form);

        return getDataTable(reports);
    }

    @GetMapping("/dicts/{type}")
    @Anonymous
    public AjaxResult monthlyReportDict(@PathVariable Integer type) {
        List<String> dictTypes = NumEnum.ONE.getNum().equals(type) ? Arrays.asList("bank_account", "bank_name")
                : Arrays.asList("client_service_type");
        Map<String, List<SysDictData>> dictMap = dictDataService.selectByDictTypes(dictTypes)
                .stream().collect(Collectors.groupingBy(SysDictData::getDictType));
        return success(dictMap);
    }

    @PostMapping("/bank/monthly/export")
    @RequiresPermissions("finance:report:bank:export")
    public void monthlyReportExport(@RequestBody BankMonthlyReportForm form,
                                    HttpServletResponse response) {
        List<BankMonthlyReportDto> reports = reportService.monthlyReports(form);

        String fileName = String.format(ConstantKey.BANK_MONTHLY_EXPORT_NAME, DateUtil.format(new Date(), "yyyyMMddHHmmss"));
        try (ExcelWriter excelWriter = EasyExcel.write(ExcelUtil.getOutputStream(fileName, response), BankMonthlyReportDto.class).build()) {
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("sheet").build();
            //写入本地文件
            excelWriter.write(reports, writeSheet);
            excelWriter.finish();
        } catch (Exception e) {
            log.error("上海账套导出失败：", e);
            throw new ServiceException(e.getMessage());
        }
    }

    @PostMapping("/statistics/page")
    @RequiresPermissions("finance:report:statistics:page")
    public TableDataInfo statisticsPage(FinMonthlyStatisticsPageForm form) {
        List<FinMonthlyStatisticsVo> reports = reportService.finMonthlyStatistics(form, false);

        return getDataTable(reports);
    }

    @PostMapping("/statistics/export")
    @RequiresPermissions("finance:report:statistics:export")
    public void statisticsExport(@RequestBody FinMonthlyStatisticsPageForm form, HttpServletResponse response) {
        List<FinMonthlyStatisticsVo> reports = reportService.finMonthlyStatistics(form, true);

        String fileName = String.format(ConstantKey.STATISTICS_MONTHLY_EXPORT_NAME, DateUtil.format(new Date(), "yyyyMMddHHmmss"));
        try (ExcelWriter excelWriter = EasyExcel.write(ExcelUtil.getOutputStream(fileName, response), FinMonthlyStatisticsVo.class).build()) {
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("sheet").excludeColumnFieldNames(ConstantKey.FINANCE_STATISTICS_IGNORE_FILES).build();
            //写入本地文件
            excelWriter.write(reports, writeSheet);
            excelWriter.finish();
        } catch (Exception e) {
            log.error("Export Fail：", e);
            throw new ServiceException(e.getMessage());
        }
    }

    @PostMapping("/quick_import/page")
    @RequiresPermissions("finance:account:select:import")
    public TableDataInfo quickImport(FinanceQueryForm form) {
        startPage();

        return getDataTable(reportService.quickImportList(form));
    }

    @PostMapping("/quick_import/export")
    @RequiresPermissions("finance:account:select:export")
    public void quickImportExport(@RequestBody FinanceQueryForm form,
                                  HttpServletResponse response) {
        List<HroneAccount> accounts = Optional.ofNullable(reportService.quickImportList(form)).orElse(new ArrayList<>());
        String fileName = String.format(ConstantKey.ACCOUNT_QUICK_IMPORT_NAME, DateUtil.format(new Date(), "yyyyMMddHHmmss"));
        try (ExcelWriter excelWriter = EasyExcel.write(ExcelUtil.getOutputStream(fileName, response), HroneAccount.class).build()) {
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("sheet").excludeColumnFieldNames(ConstantKey.ENTRY_IGNORE_FILES).build();
            //写入本地文件
            excelWriter.write(accounts, writeSheet);
            excelWriter.finish();
        } catch (Exception e) {
            log.error("上海账套导出失败：", e);
            throw new ServiceException(e.getMessage());
        }
    }
}
