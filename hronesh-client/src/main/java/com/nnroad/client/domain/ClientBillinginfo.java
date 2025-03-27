package com.nnroad.client.domain;


import com.nnroad.common.core.domain.BaseEntity;

/**
 * 【客户账单配置】对象 cmgt_bill_config
 *
 * @author Hrone
 * @date 2024-01-29
 */
public class ClientBillinginfo extends BaseEntity
{
    private String clientCode;
    private String clientName;
    private String isPasswordDynamic;
    private String payNoticePassword;

    // Getters and setters
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPayNoticePassword() {
        return payNoticePassword;
    }

    public void setPayNoticePassword(String payNoticePassword) {
        this.payNoticePassword = payNoticePassword;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getIsPasswordDynamic() {
        return isPasswordDynamic;
    }

    public void setIsPasswordDynamic(String isPasswordDynamic) {
        this.isPasswordDynamic = isPasswordDynamic;
    }
}
