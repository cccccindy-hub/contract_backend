package com.nnroad.payroll.domain;

public class TEEPayslip {

    /** 工号 */
    private String idNo;

    /** 证件号码 */
    private String fcertNo;

    /** 姓名 */
    private String fcertName;
    /** 雇主名称 */
    private String femployerName;
    /** 雇主id */
    private String femployer;
    /** 期间 */
    private String fperiod;
    /** 薪资数据 */
    private String fdata;

    private Long fconfirmedAt;

    private String phone;

    private String femail;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getFconfirmedAt() {
        return fconfirmedAt;
    }

    public void setFconfirmedAt(Long fconfirmedAt) {
        this.fconfirmedAt = fconfirmedAt;
    }

    public TEEPayslip(String fcertNo, String fcertName, String femployerName, String femployer, String fperiod, String fdata) {
        this.fcertNo = fcertNo;
        this.fcertName = fcertName;
        this.femployerName = femployerName;
        this.femployer = femployer;
        this.fperiod = fperiod;
        this.fdata = fdata;
    }

    @Override
    public String toString() {
        return "PsPayslipDTO{" +
                "fcertNo='" + fcertNo + '\'' +
                ", fcertName='" + fcertName + '\'' +
                ", femployerName='" + femployerName + '\'' +
                ", femployer='" + femployer + '\'' +
                ", fperiod='" + fperiod + '\'' +
                ", fdata='" + fdata + '\'' +
                '}';
    }

    public String getFperiod() {
        return fperiod;
    }

    public void setFperiod(String fperiod) {
        this.fperiod = fperiod;
    }

    public String getFcertNo() {
        return fcertNo;
    }

    public void setFcertNo(String fcertNo) {
        this.fcertNo = fcertNo;
    }

    public String getFcertName() {
        return fcertName;
    }

    public void setFcertName(String fcertName) {
        this.fcertName = fcertName;
    }

    public String getFemployerName() {
        return femployerName;
    }

    public void setFemployerName(String femployerName) {
        this.femployerName = femployerName;
    }

    public String getFemployer() {
        return femployer;
    }

    public void setFemployer(String femployer) {
        this.femployer = femployer;
    }

    public String getFdata() {
        return fdata;
    }

    public void setFdata(String fdata) {
        this.fdata = fdata;
    }

    public TEEPayslip() {
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getFemail() {
        return femail;
    }

    public void setFemail(String femail) {
        this.femail = femail;
    }
}
