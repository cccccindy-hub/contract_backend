package com.nnroad.member.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;

/**
 * 对象 mmgt_employee_baseInfo
 * 
 * @author hgr
 * @date 2024-12-26
 */
public class MemberEmployeeBaseinfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 公司编号 */
    @Excel(name = "公司编号")
    private String companyCode;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 员工编号 */
    @Excel(name = "员工编号")
    private String eeCode;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String eeName;

    /** 部门 */
    @Excel(name = "部门")
    private String department;

    /** 职位 */
    @Excel(name = "职位")
    private String post;

    /** 户籍类型 */
    @Excel(name = "户籍类型")
    private String domicileType;

    /** 入职日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入职日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date entryDate;

    /** 状态(0:离职 1:在职 -1:未入职) */
    @Excel(name = "状态(0:离职 1:在职 -1:未入职)")
    private String status;

    /** 国籍/国家 */
    @Excel(name = "国籍/国家")
    private String nationality;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String idType;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idNumber;

    /** 户口所在地 */
    @Excel(name = "户口所在地")
    private String permanentResidence;

    /** 家庭地址 */
    @Excel(name = "家庭地址")
    private String homeAddress;

    /** 家庭邮编 */
    @Excel(name = "家庭邮编")
    private String familyPostalCode;

    /** 英文名 */
    @Excel(name = "英文名")
    private String nameEn;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String mobile;

    /** 私人邮箱 */
    @Excel(name = "私人邮箱")
    private String privateEmail;

    /** 工作邮箱 */
    @Excel(name = "工作邮箱")
    private String workEmail;

    /** 银行账号 */
    @Excel(name = "银行账号")
    private String bankAccount;

    /** 开户行(支行) */
    @Excel(name = "开户行(支行)")
    private String openBank;

    /** 银行账号持有人 */
    @Excel(name = "银行账号持有人")
    private String bankAccountHolder;

    /** 学历 */
    @Excel(name = "学历")
    private String qualification;

    /** 职称/证书 */
    @Excel(name = "职称/证书")
    private String titleCertificate;

    @Excel(name = "内线分机号")
    private String internalExtension;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCompanyCode(String companyCode) 
    {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() 
    {
        return companyCode;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }
    public void setEeCode(String eeCode) 
    {
        this.eeCode = eeCode;
    }

    public String getEeCode() 
    {
        return eeCode;
    }
    public void setEeName(String eeName) 
    {
        this.eeName = eeName;
    }

    public String getEeName() 
    {
        return eeName;
    }
    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public String getDepartment() 
    {
        return department;
    }
    public void setPost(String post) 
    {
        this.post = post;
    }

    public String getPost() 
    {
        return post;
    }
    public void setDomicileType(String domicileType) 
    {
        this.domicileType = domicileType;
    }

    public String getDomicileType() 
    {
        return domicileType;
    }
    public void setEntryDate(Date entryDate) 
    {
        this.entryDate = entryDate;
    }

    public Date getEntryDate() 
    {
        return entryDate;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setNationality(String nationality) 
    {
        this.nationality = nationality;
    }

    public String getNationality() 
    {
        return nationality;
    }
    public void setIdType(String idType) 
    {
        this.idType = idType;
    }

    public String getIdType() 
    {
        return idType;
    }
    public void setIdNumber(String idNumber) 
    {
        this.idNumber = idNumber;
    }

    public String getIdNumber() 
    {
        return idNumber;
    }
    public void setPermanentResidence(String permanentResidence) 
    {
        this.permanentResidence = permanentResidence;
    }

    public String getPermanentResidence() 
    {
        return permanentResidence;
    }
    public void setHomeAddress(String homeAddress) 
    {
        this.homeAddress = homeAddress;
    }

    public String getHomeAddress() 
    {
        return homeAddress;
    }
    public void setFamilyPostalCode(String familyPostalCode) 
    {
        this.familyPostalCode = familyPostalCode;
    }

    public String getFamilyPostalCode() 
    {
        return familyPostalCode;
    }
    public void setNameEn(String nameEn) 
    {
        this.nameEn = nameEn;
    }

    public String getNameEn() 
    {
        return nameEn;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setPrivateEmail(String privateEmail) 
    {
        this.privateEmail = privateEmail;
    }

    public String getPrivateEmail() 
    {
        return privateEmail;
    }
    public void setWorkEmail(String workEmail) 
    {
        this.workEmail = workEmail;
    }

    public String getWorkEmail() 
    {
        return workEmail;
    }
    public void setBankAccount(String bankAccount) 
    {
        this.bankAccount = bankAccount;
    }

    public String getBankAccount() 
    {
        return bankAccount;
    }
    public void setOpenBank(String openBank) 
    {
        this.openBank = openBank;
    }

    public String getOpenBank() 
    {
        return openBank;
    }
    public void setBankAccountHolder(String bankAccountHolder) 
    {
        this.bankAccountHolder = bankAccountHolder;
    }

    public String getBankAccountHolder() 
    {
        return bankAccountHolder;
    }
    public void setQualification(String qualification) 
    {
        this.qualification = qualification;
    }

    public String getQualification() 
    {
        return qualification;
    }
    public void setTitleCertificate(String titleCertificate) 
    {
        this.titleCertificate = titleCertificate;
    }

    public String getTitleCertificate() 
    {
        return titleCertificate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyCode", getCompanyCode())
            .append("companyName", getCompanyName())
            .append("eeCode", getEeCode())
            .append("eeName", getEeName())
            .append("department", getDepartment())
            .append("post", getPost())
            .append("domicileType", getDomicileType())
            .append("entryDate", getEntryDate())
            .append("status", getStatus())
            .append("nationality", getNationality())
            .append("idType", getIdType())
            .append("idNumber", getIdNumber())
            .append("permanentResidence", getPermanentResidence())
            .append("homeAddress", getHomeAddress())
            .append("familyPostalCode", getFamilyPostalCode())
            .append("nameEn", getNameEn())
            .append("birthday", getBirthday())
            .append("sex", getSex())
            .append("mobile", getMobile())
            .append("privateEmail", getPrivateEmail())
            .append("workEmail", getWorkEmail())
            .append("bankAccount", getBankAccount())
            .append("openBank", getOpenBank())
            .append("bankAccountHolder", getBankAccountHolder())
            .append("qualification", getQualification())
            .append("titleCertificate", getTitleCertificate())
            .append("internalExtension", getInternalExtension())
            .toString();
    }

    public String getInternalExtension() {
        return internalExtension;
    }

    public void setInternalExtension(String internalExtension) {
        this.internalExtension = internalExtension;
    }
}
