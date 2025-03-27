package com.nnroad.finance.model.vo;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.nnroad.finance.model.dto.DepositEmployeeDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class DepositEmployeeVo extends DepositEmployeeDto {

    private static final long serialVersionUID = 1L;

    /**
     * 货币
     */
    @ExcelProperty(value = "Currency", index = 5)
    @ColumnWidth(12)
    private String currency;

    /**
     * 货币金额
     */
    @ExcelProperty(value = "Currency Amount", index = 6)
    @ColumnWidth(20)
    private BigDecimal currencyAmount;

    /**
     * 结算年月
     */
    @ExcelProperty(value = "Settle Month", index = 7)
    @ColumnWidth(20)
    private String monthSettle;

}
