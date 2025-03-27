package com.nnroad.common.enums;

import lombok.Getter;

@Getter
public enum TableEnum {

    EMPLOYEE_CONTACT("employee_contract"),

    EMPLOYEE_EE_PAYROLL_CRM("employee_payroll_crm"),

    ;

    private final String tabName;

    TableEnum(String tabName) {
        this.tabName = tabName;
    }
}
