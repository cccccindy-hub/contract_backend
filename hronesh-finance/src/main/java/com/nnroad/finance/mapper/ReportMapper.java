package com.nnroad.finance.mapper;

import com.nnroad.finance.model.dto.BankMonthlyReportDto;
import com.nnroad.finance.model.form.BankMonthlyReportForm;
import com.nnroad.finance.model.form.FinMonthlyStatisticsPageForm;
import com.nnroad.finance.model.vo.FinMonthlyStatisticsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportMapper {

    List<BankMonthlyReportDto> monthlyReports(BankMonthlyReportForm form);

    List<FinMonthlyStatisticsVo> finMonthlyStatistics(@Param("form") FinMonthlyStatisticsPageForm form,
                                                      @Param("serviceTypeId") String serviceTypeId);
}
