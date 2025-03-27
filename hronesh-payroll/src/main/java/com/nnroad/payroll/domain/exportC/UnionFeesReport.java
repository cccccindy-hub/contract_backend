package com.nnroad.payroll.domain.exportC;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 工会费对象 union_fees_report
 *
 * @author Hrone
 * @date 2022-01-04
 */
public class UnionFeesReport extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 客户编号
     */
    @Excel(name = "客户编号")
    private String clientCode;
    /**
     * 客户
     */
    @Excel(name = "客户")
    private String clients;
    /**
     * 员工编号
     */
    @Excel(name = "员工编号")
    private String employeeCode;

    /**
     * 员工姓名
     */
    @Excel(name = "员工姓名")
    private String employeeName;

    /**
     * 身份证/护照
     */
    @Excel(name = "身份证/护照")
    private String idOrPassport;

    /**
     * 生日
     */
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /**
     * 电话/手机
     */
    @Excel(name = "电话/手机")
    private String phoneOrMobile;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱")
    private String mailbox;

    /**
     * 生日月
     */
    @Excel(name = "生日月")
    private String birthdayMonth;

    /** 是否离职*/
    @Excel(name = "是否离职")
    private String isLeave;

    /**  */
    @Excel(name = "类型")
    private String allowanceType;

    public void setClients(String clients) {
        this.clients = clients;
    }

    public String getClients() {
        return clients;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setIdOrPassport(String idOrPassport) {
        this.idOrPassport = idOrPassport;
    }

    public String getIdOrPassport() {
        return idOrPassport;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setPhoneOrMobile(String phoneOrMobile) {
        this.phoneOrMobile = phoneOrMobile;
    }

    public String getPhoneOrMobile() {
        return phoneOrMobile;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setAllowanceType(String allowanceType)
    {
        this.allowanceType = allowanceType;
    }

    public String getAllowanceType()
    {
        return allowanceType;
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


    public String getBirthdayMonth() {
        return birthdayMonth;
    }

    public void setBirthdayMonth(String birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
    }

    public String getIsLeave() {
        return isLeave;
    }

    public void setIsLeave(String isLeave) {
        this.isLeave = isLeave;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("clientCode", getClientCode())
                .append("employeeCode", getEmployeeCode())
                .append("clients", getClients())
                .append("employeeName", getEmployeeName())
                .append("idOrPassport", getIdOrPassport())
                .append("birthday", getBirthday())
                .append("phoneOrMobile", getPhoneOrMobile())
                .append("mailbox", getMailbox())
                .append("birthdayMonth", getBirthdayMonth())
                .append("isLeave", getIsLeave())
                .append("allowanceType", getAllowanceType())
                .toString();
    }
}
