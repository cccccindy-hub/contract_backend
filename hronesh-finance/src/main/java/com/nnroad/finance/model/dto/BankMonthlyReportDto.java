package com.nnroad.finance.model.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BankMonthlyReportDto {

    @ExcelProperty("期间")
    @ColumnWidth(15)
    private String period;

    @ExcelProperty("Bank")
    @ColumnWidth(15)
    private String bank;

    @ExcelProperty("Bank Account")
    @ColumnWidth(30)
    private String bankAccount;

    @ExcelProperty("Currency")
    @ColumnWidth(15)
    private String currency;

    @ExcelProperty("Monthly Debit")
    @ColumnWidth(30)
    private BigDecimal monthlyDebit;

    @ExcelProperty("Monthly Credit")
    @ColumnWidth(30)
    private BigDecimal monthlyCredit;

    @ExcelProperty("balance")
    @ColumnWidth(30)
    private BigDecimal balance;
}
