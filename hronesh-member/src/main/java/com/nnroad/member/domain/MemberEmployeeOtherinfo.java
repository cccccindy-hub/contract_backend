package com.nnroad.member.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;

public class MemberEmployeeOtherinfo extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 员工编号 */
    @Excel(name = "员工编号")
    private String eeCode;

    /** 试用期终止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "试用期终止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tryoutEndDate;

    /** 合同终止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同终止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contractEndDate;

    /** 续签合同终止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "续签合同终止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date renewalContractEndDate;

    /** 工作/居留许可到期日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "工作/居留许可到期日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date residencePermitEndDate;

    /** 工龄 */
    @Excel(name = "工龄")
    private String seniority;

    /** 基本工资 */
    @Excel(name = "基本工资")
    private String basicSalary;

    /** 绩效工资基数 */
    @Excel(name = "绩效工资基数")
    private String performanceSalaryBase;

    /** 岗位工资 */
    @Excel(name = "岗位工资")
    private String postSalary;

    /** 社保基数 */
    @Excel(name = "社保基数")
    private String socialSecurityBase;

    /** 公积金基数 */
    @Excel(name = "公积金基数")
    private String providentFundBase;

    /** 社保/公积金缴纳单位 */
    @Excel(name = "社保/公积金缴纳单位")
    private String payUnit;

    /** 社保起缴月 */
    @JsonFormat(pattern = "yyyy-MM")
    @Excel(name = "社保起缴月", width = 30, dateFormat = "yyyy-MM")
    private Date socialSecurityPayMonth;

    /** 商业险级别 */
    @Excel(name = "商业险级别")
    private String commercialInsuranceLevel;

    /** 商业保险 */
    @Excel(name = "商业保险")
    private String commercialInsurance;

    /** 雇主责任险 */
    @Excel(name = "雇主责任险")
    private String erDutyInsurance;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setEeCode(String eeCode)
    {
        this.eeCode = eeCode;
    }

    public String getEeCode()
    {
        return eeCode;
    }
    public void setTryoutEndDate(Date tryoutEndDate)
    {
        this.tryoutEndDate = tryoutEndDate;
    }

    public Date getTryoutEndDate()
    {
        return tryoutEndDate;
    }
    public void setContractEndDate(Date contractEndDate)
    {
        this.contractEndDate = contractEndDate;
    }

    public Date getContractEndDate()
    {
        return contractEndDate;
    }
    public void setRenewalContractEndDate(Date renewalContractEndDate)
    {
        this.renewalContractEndDate = renewalContractEndDate;
    }

    public Date getRenewalContractEndDate()
    {
        return renewalContractEndDate;
    }
    public void setResidencePermitEndDate(Date residencePermitEndDate)
    {
        this.residencePermitEndDate = residencePermitEndDate;
    }

    public Date getResidencePermitEndDate()
    {
        return residencePermitEndDate;
    }
    public void setSeniority(String seniority)
    {
        this.seniority = seniority;
    }

    public String getSeniority()
    {
        return seniority;
    }
    public void setBasicSalary(String basicSalary)
    {
        this.basicSalary = basicSalary;
    }

    public String getBasicSalary()
    {
        return basicSalary;
    }
    public void setPerformanceSalaryBase(String performanceSalaryBase)
    {
        this.performanceSalaryBase = performanceSalaryBase;
    }

    public String getPerformanceSalaryBase()
    {
        return performanceSalaryBase;
    }
    public void setPostSalary(String postSalary)
    {
        this.postSalary = postSalary;
    }

    public String getPostSalary()
    {
        return postSalary;
    }
    public void setSocialSecurityBase(String socialSecurityBase)
    {
        this.socialSecurityBase = socialSecurityBase;
    }

    public String getSocialSecurityBase()
    {
        return socialSecurityBase;
    }
    public void setProvidentFundBase(String providentFundBase)
    {
        this.providentFundBase = providentFundBase;
    }

    public String getProvidentFundBase()
    {
        return providentFundBase;
    }
    public void setPayUnit(String payUnit)
    {
        this.payUnit = payUnit;
    }

    public String getPayUnit()
    {
        return payUnit;
    }
    public void setSocialSecurityPayMonth(Date socialSecurityPayMonth)
    {
        this.socialSecurityPayMonth = socialSecurityPayMonth;
    }

    public Date getSocialSecurityPayMonth()
    {
        return socialSecurityPayMonth;
    }
    public void setCommercialInsuranceLevel(String commercialInsuranceLevel)
    {
        this.commercialInsuranceLevel = commercialInsuranceLevel;
    }

    public String getCommercialInsuranceLevel()
    {
        return commercialInsuranceLevel;
    }
    public void setCommercialInsurance(String commercialInsurance)
    {
        this.commercialInsurance = commercialInsurance;
    }

    public String getCommercialInsurance()
    {
        return commercialInsurance;
    }
    public void setErDutyInsurance(String erDutyInsurance)
    {
        this.erDutyInsurance = erDutyInsurance;
    }

    public String getErDutyInsurance()
    {
        return erDutyInsurance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("eeCode", getEeCode())
                .append("tryoutEndDate", getTryoutEndDate())
                .append("contractEndDate", getContractEndDate())
                .append("renewalContractEndDate", getRenewalContractEndDate())
                .append("residencePermitEndDate", getResidencePermitEndDate())
                .append("seniority", getSeniority())
                .append("basicSalary", getBasicSalary())
                .append("performanceSalaryBase", getPerformanceSalaryBase())
                .append("postSalary", getPostSalary())
                .append("socialSecurityBase", getSocialSecurityBase())
                .append("providentFundBase", getProvidentFundBase())
                .append("payUnit", getPayUnit())
                .append("socialSecurityPayMonth", getSocialSecurityPayMonth())
                .append("commercialInsuranceLevel", getCommercialInsuranceLevel())
                .append("commercialInsurance", getCommercialInsurance())
                .append("erDutyInsurance", getErDutyInsurance())
                .toString();
    }
}
