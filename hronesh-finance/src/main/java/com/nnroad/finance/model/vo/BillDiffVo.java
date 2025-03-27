package com.nnroad.finance.model.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BillDiffVo {

    private String eeCode;

    private String eeName;

    private String clientName;

    private String forPeriod;

    private String vendorName;

    private BigDecimal totalCost;

    private BigDecimal serviceFee;

    private BigDecimal customerTotalCost;

    private BigDecimal customerServiceFee;

    private BigDecimal costDiff;

    private BigDecimal serviceFeeDiff;
}
