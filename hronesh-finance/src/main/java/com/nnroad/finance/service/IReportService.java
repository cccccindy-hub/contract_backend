package com.nnroad.finance.service;

import com.nnroad.finance.domain.HroneAccount;
import com.nnroad.finance.model.dto.BankMonthlyReportDto;
import com.nnroad.finance.model.form.BankMonthlyReportForm;
import com.nnroad.finance.model.form.FinMonthlyStatisticsPageForm;
import com.nnroad.finance.model.form.FinanceQueryForm;
import com.nnroad.finance.model.vo.FinMonthlyStatisticsVo;

import java.util.List;

public interface IReportService {

    List<BankMonthlyReportDto> monthlyReports(BankMonthlyReportForm form);

    List<FinMonthlyStatisticsVo> finMonthlyStatistics(FinMonthlyStatisticsPageForm form, boolean export);

    List<HroneAccount> quickImportList(FinanceQueryForm form);
}
