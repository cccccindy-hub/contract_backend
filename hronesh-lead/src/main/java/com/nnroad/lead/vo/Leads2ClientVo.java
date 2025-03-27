package com.nnroad.lead.vo;


import com.nnroad.lead.domain.ClientLeads;

public class Leads2ClientVo extends ClientLeads {
    private Integer serviceFeeType;
    private Double serviceFeeValue;
    private Integer depositType;
    private Double depositValue;
    private Integer exchangeRate;
    private String employmentContract;
    private String CRM;

    public Integer getServiceFeeType() {
        return serviceFeeType;
    }

    public void setServiceFeeType(Integer serviceFeeType) {
        this.serviceFeeType = serviceFeeType;
    }

    public Double getServiceFeeValue() {
        return serviceFeeValue;
    }

    public void setServiceFeeValue(Double serviceFeeValue) {
        this.serviceFeeValue = serviceFeeValue;
    }

    public Integer getDepositType() {
        return depositType;
    }

    public void setDepositType(Integer depositType) {
        this.depositType = depositType;
    }

    public Double getDepositValue() {
        return depositValue;
    }

    public void setDepositValue(Double depositValue) {
        this.depositValue = depositValue;
    }

    public Integer getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Integer exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getEmploymentContract() {
        return employmentContract;
    }

    public void setEmploymentContract(String employmentContract) {
        this.employmentContract = employmentContract;
    }

    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
    }
}
