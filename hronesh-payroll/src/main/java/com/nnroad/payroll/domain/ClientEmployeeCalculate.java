package com.nnroad.payroll.domain;

import com.nnroad.common.core.domain.BaseEntity;

import java.math.BigDecimal;

public class ClientEmployeeCalculate extends BaseEntity {


    String eeCode;
    String clientCode;
    String ServiceFeeType;
    String ServiceFeeValue;
    String ServiceFeeCurrency;
    String minValue;
    String maxValue;

    public ClientEmployeeCalculate() {
    }
    public String getEeCode() {
        return eeCode;
    }

    public void setEeCode(String eeCode) {
        this.eeCode = eeCode;
    }



    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }


    public String getServiceFeeType() {
        return ServiceFeeType;
    }

    public void setServiceFeeType(String serviceFeeType) {
        ServiceFeeType = serviceFeeType;
    }

    public String getServiceFeeValue() {
        return ServiceFeeValue;
    }

    public void setServiceFeeValue(String serviceFeeValue) {
        ServiceFeeValue = serviceFeeValue;
    }

    public String getServiceFeeCurrency() {
        return ServiceFeeCurrency;
    }

    public void setServiceFeeCurrency(String serviceFeeCurrency) {
        ServiceFeeCurrency = serviceFeeCurrency;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public String toString() {
        return "ClientEmployeeCalculate{" +
                "eeCode='" + eeCode + '\'' +
                ", clientCode='" + clientCode + '\'' +
                '}';
    }
}
