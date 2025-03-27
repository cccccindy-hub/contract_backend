package com.nnroad.payroll.domain;

/**
 * ps_payslip对象 ps_payslip
 *
 */
public class MyPsPayslipDto {
    private String duration;
    private String eeNo;
    private String kfFlag;
    private Long id;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEeNo() {
        return eeNo;
    }

    public void setEeNo(String eeNo) {
        this.eeNo = eeNo;
    }

    public String getKfFlag() {
        return kfFlag;
    }

    public void setKfFlag(String kfFlag) {
        this.kfFlag = kfFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
