package com.nnroad.payroll.domain;

import com.nnroad.common.core.domain.BaseEntity;

import java.util.Objects;

public class PayrollEntry extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String fid;

    private String fpayroll;

    private Long forder;

    private String fsysEmoNo;

    private String fcertNO;

    private String fcertName;

    private String fcertType;

    private String fempNo;

    private Long fisGenPs;

    private String fempId;

    private String fdata;

    private String fdiff;

    private String fconfig;

    private Integer fstatus;

    private Long fcreatedAt;

    private Long fcreatedBy;

    private Long fupdatedAt;

    private Long fupdatedBy;

    public PayrollEntry() {
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFpayroll() {
        return fpayroll;
    }

    public void setFpayroll(String fpayroll) {
        this.fpayroll = fpayroll;
    }

    public Long getForder() {
        return forder;
    }

    public void setForder(Long forder) {
        this.forder = forder;
    }

    public String getFsysEmoNo() {
        return fsysEmoNo;
    }

    public void setFsysEmoNo(String fsysEmoNo) {
        this.fsysEmoNo = fsysEmoNo;
    }

    public String getFcertNO() {
        return fcertNO;
    }

    public void setFcertNO(String fcertNO) {
        this.fcertNO = fcertNO;
    }

    public String getFcertName() {
        return fcertName;
    }

    public void setFcertName(String fcertName) {
        this.fcertName = fcertName;
    }

    public String getFcertType() {
        return fcertType;
    }

    public void setFcertType(String fcertType) {
        this.fcertType = fcertType;
    }

    public String getFempNo() {
        return fempNo;
    }

    public void setFempNo(String fempNo) {
        this.fempNo = fempNo;
    }

    public Long getFisGenPs() {
        return fisGenPs;
    }

    public void setFisGenPs(Long fisGenPs) {
        this.fisGenPs = fisGenPs;
    }

    public String getFempId() {
        return fempId;
    }

    public void setFempId(String fempId) {
        this.fempId = fempId;
    }

    public String getFdata() {
        return fdata;
    }

    public void setFdata(String fdata) {
        this.fdata = fdata;
    }

    public String getFdiff() {
        return fdiff;
    }

    public void setFdiff(String fdiff) {
        this.fdiff = fdiff;
    }

    public String getFconfig() {
        return fconfig;
    }

    public void setFconfig(String fconfig) {
        this.fconfig = fconfig;
    }

    public Integer getFstatus() {
        return fstatus;
    }

    public void setFstatus(Integer fstatus) {
        this.fstatus = fstatus;
    }

    public Long getFcreatedAt() {
        return fcreatedAt;
    }

    public void setFcreatedAt(Long fcreatedAt) {
        this.fcreatedAt = fcreatedAt;
    }

    public Long getFcreatedBy() {
        return fcreatedBy;
    }

    public void setFcreatedBy(Long fcreatedBy) {
        this.fcreatedBy = fcreatedBy;
    }

    public Long getFupdatedAt() {
        return fupdatedAt;
    }

    public void setFupdatedAt(Long fupdatedAt) {
        this.fupdatedAt = fupdatedAt;
    }

    public Long getFupdatedBy() {
        return fupdatedBy;
    }

    public void setFupdatedBy(Long fupdatedBy) {
        this.fupdatedBy = fupdatedBy;
    }

    @Override
    public String toString() {
        return "PayrollEntry{" +
                "fid='" + fid + '\'' +
                ", fpayroll='" + fpayroll + '\'' +
                ", forder=" + forder +
                ", fsysEmoNo='" + fsysEmoNo + '\'' +
                ", fcertNO='" + fcertNO + '\'' +
                ", fcertName='" + fcertName + '\'' +
                ", fcertType='" + fcertType + '\'' +
                ", fempNo='" + fempNo + '\'' +
                ", fisGenPs=" + fisGenPs +
                ", fempId='" + fempId + '\'' +
                ", fdata='" + fdata + '\'' +
                ", fdiff='" + fdiff + '\'' +
                ", fconfig='" + fconfig + '\'' +
                ", fstatus=" + fstatus +
                ", fcreatedAt=" + fcreatedAt +
                ", fcreatedBy=" + fcreatedBy +
                ", fupdatedAt=" + fupdatedAt +
                ", fupdatedBy=" + fupdatedBy +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PayrollEntry)) return false;
        PayrollEntry that = (PayrollEntry) o;
        return getForder() == that.getForder() && getFisGenPs() == that.getFisGenPs() && getFstatus() == that.getFstatus() && getFcreatedAt() == that.getFcreatedAt() && getFcreatedBy() == that.getFcreatedBy() && getFupdatedAt() == that.getFupdatedAt() && getFupdatedBy() == that.getFupdatedBy() && Objects.equals(getFid(), that.getFid()) && Objects.equals(getFpayroll(), that.getFpayroll()) && Objects.equals(getFsysEmoNo(), that.getFsysEmoNo()) && Objects.equals(getFcertNO(), that.getFcertNO()) && Objects.equals(getFcertName(), that.getFcertName()) && Objects.equals(getFcertType(), that.getFcertType()) && Objects.equals(getFempNo(), that.getFempNo()) && Objects.equals(getFempId(), that.getFempId()) && Objects.equals(getFdata(), that.getFdata()) && Objects.equals(getFdiff(), that.getFdiff()) && Objects.equals(getFconfig(), that.getFconfig());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFid(), getFpayroll(), getForder(), getFsysEmoNo(), getFcertNO(), getFcertName(), getFcertType(), getFempNo(), getFisGenPs(), getFempId(), getFdata(), getFdiff(), getFconfig(), getFstatus(), getFcreatedAt(), getFcreatedBy(), getFupdatedAt(), getFupdatedBy());
    }
}
