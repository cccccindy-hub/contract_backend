package com.nnroad.finance.model.form;

import lombok.Data;

@Data
public class DepositForm {

    private String clientCode;

    private String monthSettle;

    private String employeeCode;

    private String type;

    private String dateRange;
}
