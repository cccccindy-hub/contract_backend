package com.nnroad.finance.domain;


import com.alibaba.excel.annotation.ExcelProperty;
import com.nnroad.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class DepositClient extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 客户编号
     */
    @ExcelProperty("Client Code")
    private String clientCode;

    /**
     * 客户名称
     */
    @ExcelProperty("Client Name")
    private String clientName;

    /**
     * 剩余押金
     */
    @ExcelProperty("Deposit")
    private BigDecimal amount;

    /**
     * 货币
     */
    @ExcelProperty("Currency")
    private String currency;

    /**
     * 货币金额
     */
    @ExcelProperty("Currency Amount")
    private BigDecimal currencyAmount;

    /**
     * 结算年月
     */
    @ExcelProperty("Month Settle")
    private String monthSettle;
}
