package com.nnroad.finance.model.form;

import com.nnroad.finance.model.dto.BankMonthlyReportDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BankMonthlyReportForm extends BankMonthlyReportDto {

    private String beginPeriod;

    private String endPeriod;
}
