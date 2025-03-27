package com.nnroad.common.enums;

import lombok.Getter;

@Getter
public enum ColumnEnum {
    ER_CONTRACT_SERVICE_TYPE("client_billing_status", "185"),

    //家庭地址
    EE_HOME_ADDR("sys_employee", "268"),
    //邮编
    EE_POST_CODE("sys_employee", "269"),
    //户籍类型
    EE_REGISTERED_RESIDENCE_TYPE("sys_employee", "270"),
    //户籍地址
    EE_RESIDENCE_ADDR("sys_employee", "271"),

    //合同终止日期
    EE_CONTRACT_END_DATE("employee_contract", "229"),
    //税前税后
    EE_SALARY_BEFORE_OR_AFTER_TAX("employee_contract", "231"),
    //职位
    EE_CONTRACT_POSITION("employee_contract", "262"),

    EE_SERVICE_TYPE("employee_payroll_crm", "224"),
    EE_IS_PAY_SLIPS("employee_payroll_crm", "265"),
    EE_BUSINESS_INSURANCE_LEVELS("employee_payroll_crm", "273"),
    ;
    /**
     * 表名
     */
    private final String t;
    /**
     * 字段名
     */
    private final String fId;

    ColumnEnum(String tableName, String fId) {
        this.t = tableName;
        this.fId = fId;
    }
}
