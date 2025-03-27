package com.nnroad.payroll.domain.exportC;

import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * commercial_insurance_report对象 commercial_insurance_report
 *
 * @author Hrone
 * @date 2021-12-31
 */
public class CommercialInsuranceReport extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     *员工编号
     */
    @Excel(name = "员工编号")
    private String employeeCode;

    /**
     *姓名
     */
    @Excel(name = "姓名")
    private String employeeName;

    /**
     *客户编号
     */
    @Excel(name = "客户编号")
    private String clientCode;
    /**
     *客户
     */
    @Excel(name = "客户")
    private String companyName;

    /**
     *证件号码
     */
    @Excel(name = "证件号码")
    private String idNumber;

    /**
     *证件类型
     */
    @Excel(name = "证件类型")
    private String idType;

    /**
     *投保等级
     */
    @Excel(name = "投保等级")
    private String ciLevel;

    /**
     *商业保险开始时间
     */
    @Excel(name = "商业保险开始时间")
    private String ciStartMonth;

    /**
     *医保状况
     */
    @Excel(name = "医保状况")
    private String isSiReady;

    /**
     *被保人手机
     */
    @Excel(name = "被保险人手机")
    private String telephone;

    /**
     *电子邮箱
     */
    @Excel(name = "电子邮箱")
    private String email;

    /**
     *开户银行
     */
    @Excel(name = "开户银行")
    private String bankName;

    /**
     *银行账号
     */
    @Excel(name = "银行帐号")
    private String bankAccount;

    /**
     *账号所有人姓名
     */
    @Excel(name = "帐号所有人姓名")
    private String bankHolder;

    /** 是否离职*/
    @Excel(name = "是否离职")
    private String isLeave;

    /** 商保是否新增 */
    private String ciIsAdd;

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdType() {
        return idType;
    }

    public void setCiLevel(String ciLevel) {
        this.ciLevel = ciLevel;
    }

    public String getCiLevel() {
        return ciLevel;
    }

    public void setCiStartMonth(String ciStartMonth) {
        this.ciStartMonth = ciStartMonth;
    }

    public String getCiStartMonth() {
        return ciStartMonth;
    }

    public void setIsSiReady(String isSiReady) {
        this.isSiReady = isSiReady;
    }

    public String getIsSiReady() {
        return isSiReady;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankHolder(String bankHolder) {
        this.bankHolder = bankHolder;
    }

    public String getBankHolder() {
        return bankHolder;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getIsLeave() {
        return isLeave;
    }

    public void setIsLeave(String isLeave) {
        this.isLeave = isLeave;
    }

    public String getCiIsAdd() {
        return ciIsAdd;
    }

    public void setCiIsAdd(String ciIsAdd) {
        this.ciIsAdd = ciIsAdd;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("employeeName", getEmployeeName())
                .append("idNumber", getIdNumber())
                .append("idType", getIdType())
                .append("ciLevel", getCiLevel())
                .append("ciStartMonth", getCiStartMonth())
                .append("isSiReady", getIsSiReady())
                .append("telephone", getTelephone())
                .append("email", getEmail())
                .append("bankName", getBankName())
                .append("bankAccount", getBankAccount())
                .append("bankHolder", getBankHolder())
                .append("companyName", getBankHolder())
                .append("clientCode", getClientCode())
                .append("employeeCode", getEmployeeCode())
                .append("isLeave", getIsLeave())
                .append("ciIsAdd", getCiIsAdd())
                .toString();
    }
}
