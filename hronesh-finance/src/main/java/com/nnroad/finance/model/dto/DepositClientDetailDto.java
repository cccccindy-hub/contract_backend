package com.nnroad.finance.model.dto;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.nnroad.common.core.domain.BaseEntity;
import com.nnroad.common.utils.SecurityUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class DepositClientDetailDto extends BaseEntity {

    @ExcelIgnore
    private Long id;

    @ExcelProperty(value = "date")
    private Date date;

    @ExcelProperty(value = "Client Code")
    private String clientCode;

    @ExcelProperty(value = "Client Name")
    private String clientName;

    @ExcelProperty("Employee Code")
    private String employeeCode;

    @ExcelProperty("Employee Name")
    private String employeeName;

    @ExcelIgnore
    private String type;

    @ExcelProperty("amount")
    private BigDecimal amount;

    @ExcelProperty("currency")
    private String currency;

    @ExcelProperty("rate")
    private BigDecimal rate;

    @ExcelProperty("amountCurrency")
    private BigDecimal amountCurrency;

    @ExcelIgnore
    private int delFlag;

    public void initAuthor(boolean update) {
        String username = SecurityUtils.getUsername();
        Date now = new Date();
        if (!update) {
            this.setCreateBy(username);
            this.setCreateTime(now);
        }
        this.setUpdateBy(username);
        this.setUpdateTime(now);
    }
}
