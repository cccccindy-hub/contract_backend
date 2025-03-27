package com.nnroad.payroll.domain;


import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 雇主信息模块对象 employer
 *
 * @author yincl
 * @date 2020-12-21
 */
public class Employer extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 雇主ID
     */
    private Long erId;

    /**
     * 雇主编号
     */
    @Excel(name = "Company ID")
    private String erNo;

    /**
     * 雇主名称
     */
    @Excel(name = "公司名称")
    private String erName;

    /**
     * 雇主类型
     */
    @Excel(name = "雇主类型")
    private String erType;

    /**
     * 雇主状态
     */
    private String erStatus;

    /**
     * 特殊处理客户类型
     */
    private String speClientType;

    /** 发薪日 */
    @Excel(name = "发薪日")
    private String payDay;

    /** 货币 */
    @Excel(name = "货币")
    private String currency;

    private String org;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    private Long deptId;

    private String leaderId;

    /**
     * 权限组用户
     */
    private String groupIds;

    private String userId;

    public void setErId(Long erId) {
        this.erId = erId;
    }

    public Long getErId() {
        return erId;
    }

    public String getErNo() {
        return erNo;
    }

    public void setErNo(String erNo) {
        this.erNo = erNo;
    }

    public void setErName(String erName) {
        this.erName = erName;
    }

    public String getErName() {
        return erName;
    }

    public void setErType(String erType) {
        this.erType = erType;
    }

    public String getErType() {
        return erType;
    }

    public String getErStatus() {
        return erStatus;
    }

    public void setErStatus(String erStatus) {
        this.erStatus = erStatus;
    }

    public String getSpeClientType() {
        return speClientType;
    }

    public void setSpeClientType(String speClientType) {
        this.speClientType = speClientType;
    }

    public String getPayDay() { return payDay; }

    public void setPayDay(String payDay) { this.payDay = payDay; }

    public String getCurrency() { return currency; }

    public void setCurrency(String currency) { this.currency = currency; }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("erId", getErId())
                .append("erNo", getErNo())
                .append("erName", getErName())
                .append("erType", getErType())
                .append("erStatus", getErStatus())
                .append("speClientType", getSpeClientType())
                .append("payDay", getPayDay())
                .append("currency", getCurrency())
                .append("deptId", getDeptId())
                .append("groupIds", getGroupIds())
                .toString();
    }
}
