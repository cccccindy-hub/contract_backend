package com.nnroad.finance.model.form;

import lombok.Data;

@Data
public class FinMonthlyStatisticsPageForm {

    private String clientCode;

    private String serviceType;

    private String beginPeriod;

    private String endPeriod;

    private String beginActualDate;

    private String endActualDate;

    private String serviceTypeId;
}
