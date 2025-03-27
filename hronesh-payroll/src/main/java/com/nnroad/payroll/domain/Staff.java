package com.nnroad.payroll.domain;

import com.nnroad.common.core.domain.BaseEntity;

public class Staff extends BaseEntity {


    String eeCode;

    String eeName;

    Long userId;

    String email;

    String clientCode;
    String idNum;
    String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Staff() {
    }
    public String getEeCode() {
        return eeCode;
    }

    public void setEeCode(String eeCode) {
        this.eeCode = eeCode;
    }

    public String getEeName() {
        return eeName;
    }

    public void setEeName(String eeName) {
        this.eeName = eeName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "eeCode='" + eeCode + '\'' +
                ", eeName='" + eeName + '\'' +
                ", userId=" + userId +
                ", email='" + email + '\'' +
                ", clientCode='" + clientCode + '\'' +
                ", idNum='" + idNum + '\'' +
                '}';
    }
}
