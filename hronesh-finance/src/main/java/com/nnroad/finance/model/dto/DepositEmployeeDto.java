package com.nnroad.finance.model.dto;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.nnroad.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositEmployeeDto extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 客户编号
     */
    @ExcelProperty(value = "Company Code", index = 0)
    @ColumnWidth(20)
    private String clientCode;
    /**
     * 客户名称
     */
    @ExcelProperty(value = "Company Name", index = 1)
    @ColumnWidth(25)
    private String clientName;
    /**
     * 员工编号
     */
    @ExcelProperty(value = "Employee Code", index = 2)
    @ColumnWidth(20)
    private String employeeCode;
    /**
     * 员工名称
     */
    @ExcelProperty(value = "Employee Name", index = 3)
    @ColumnWidth(25)
    private String employeeName;
    /**
     * 剩余押金
     */
    @ExcelProperty(value = "Deposit", index = 4)
    @ColumnWidth(20)
    private BigDecimal amount;
}
