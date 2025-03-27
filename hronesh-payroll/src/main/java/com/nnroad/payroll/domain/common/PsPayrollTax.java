package com.nnroad.payroll.domain.common;


import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * payroll_tax对象 payroll_tax
 * 
 * @author Hrone
 * @date 2021-01-21
 */
public class PsPayrollTax extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** payroll_taxID */
    private Long id;

    /** 工号 */
    @Excel(name = "excel.payrolltax.id_no")
    private String idNo;

    /** 期间 */
    private String duration;

    /** 客户简称 */
    @Excel(name = "excel.payrolltax.er_name")
    private String erName;

    /** 用工性质 */
    @Excel(name = "excel.payrolltax.employment_nature")
    private String employmentNature;

    /** 姓名 */
    @Excel(name = "excel.payrolltax.name")
    private String name;

    /** 证照类型 */
    @Excel(name = "excel.payrolltax.credentials")
    private String credentials;

    /** 证照号码 */
    @Excel(name = "excel.payrolltax.credentials_number")
    private String credentialsNumber;

    /** 本期收入 */
    @Excel(name = "excel.payrolltax.income")
    private BigDecimal income;

    /** 本期免税收入 */
    @Excel(name = "excel.payrolltax.tax_free_income")
    private BigDecimal taxFreeIncome;

    /** 基本养老保险费 */
    @Excel(name = "excel.payrolltax.pension")
    private BigDecimal pension;

    /** 基本医疗保险费 */
    @Excel(name = "excel.payrolltax.medical")
    private BigDecimal medical;

    /** 失业保险费 */
    @Excel(name = "excel.payrolltax.unemployment")
    private BigDecimal unemployment;

    /** 住房公积金 */
    @Excel(name = "excel.payrolltax.housing_fund")
    private BigDecimal housingFund;

    /** 累计子女教育 */
    @Excel(name = "excel.payrolltax.children_education")
    private BigDecimal childrenEducation;

    /** 累计继续教育 */
    @Excel(name = "excel.payrolltax.continuing_education")
    private BigDecimal continuingEducation;

    /** 住房贷款利息 */
    @Excel(name = "excel.payrolltax.housing_loan_interest")
    private BigDecimal housingLoanInterest;

    /** 累计住房租金 */
    @Excel(name = "excel.payrolltax.housing_rent")
    private BigDecimal housingRent;

    /** 累计赡养老人 */
    @Excel(name = "excel.payrolltax.supporting_elderly")
    private BigDecimal supportingElderly;

    /** 企业(职业)年金 */
    @Excel(name = "excel.payrolltax.annuity")
    private BigDecimal annuity;

    /** 商业保险 */
    @Excel(name = "excel.payrolltax.commercial_insurance")
    private BigDecimal commercialInsurance;

    /** 税延养老保险 */
    @Excel(name = "excel.payrolltax.tax_pension")
    private BigDecimal taxPension;

    /** 其他 */
    @Excel(name = "excel.payrolltax.other")
    private BigDecimal other;

    /** 准予扣除的捐赠额 */
    @Excel(name = "excel.payrolltax.allowed_deduct_donation")
    private BigDecimal allowedDeductDonation;

    /** 减免税额 */
    @Excel(name = "excel.payrolltax.tax_savings")
    private BigDecimal taxSavings;

    /** 应缴税款 */
    @Excel(name = "excel.payrolltax.tax_payment")
    private BigDecimal taxPayment;

    /** 实缴税额 */
    @Excel(name = "excel.payrolltax.paid_tax")
    private BigDecimal paidTax;

    /** 年终奖个税 */
    @Excel(name = "excel.payrolltax.annuity_tax")
    private BigDecimal annuityTax;

    /** 个税缴纳地 */
    @Excel(name = "excel.payrolltax.tax_payment_place")
    private String taxPaymentPlace;

    /** 备注 */
    @Excel(name = "excel.payrolltax.remarks")
    private String remarks;

    /** 离职补偿金 */
    @Excel(name = "excel.payrolltax.severance")
    private BigDecimal severance;

    private String groupIds;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setIdNo(String idNo) 
    {
        this.idNo = idNo;
    }

    public String getIdNo() 
    {
        return idNo;
    }
    public void setDuration(String duration) 
    {
        this.duration = duration;
    }

    public String getDuration() 
    {
        return duration;
    }
    public void setErName(String erName) 
    {
        this.erName = erName;
    }

    public String getErName() 
    {
        return erName;
    }
    public void setEmploymentNature(String employmentNature) 
    {
        this.employmentNature = employmentNature;
    }

    public String getEmploymentNature() 
    {
        return employmentNature;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCredentials(String credentials) 
    {
        this.credentials = credentials;
    }

    public String getCredentials() 
    {
        return credentials;
    }
    public void setCredentialsNumber(String credentialsNumber) 
    {
        this.credentialsNumber = credentialsNumber;
    }

    public String getCredentialsNumber() 
    {
        return credentialsNumber;
    }
    public void setIncome(BigDecimal income) 
    {
        this.income = income;
    }

    public BigDecimal getIncome() 
    {
        return income;
    }
    public void setTaxFreeIncome(BigDecimal taxFreeIncome) 
    {
        this.taxFreeIncome = taxFreeIncome;
    }

    public BigDecimal getTaxFreeIncome() 
    {
        return taxFreeIncome;
    }
    public void setPension(BigDecimal pension) 
    {
        this.pension = pension;
    }

    public BigDecimal getPension() 
    {
        return pension;
    }
    public void setMedical(BigDecimal medical) 
    {
        this.medical = medical;
    }

    public BigDecimal getMedical() 
    {
        return medical;
    }
    public void setUnemployment(BigDecimal unemployment) 
    {
        this.unemployment = unemployment;
    }

    public BigDecimal getUnemployment() 
    {
        return unemployment;
    }
    public void setHousingFund(BigDecimal housingFund) 
    {
        this.housingFund = housingFund;
    }

    public BigDecimal getHousingFund() 
    {
        return housingFund;
    }
    public void setChildrenEducation(BigDecimal childrenEducation) 
    {
        this.childrenEducation = childrenEducation;
    }

    public BigDecimal getChildrenEducation() 
    {
        return childrenEducation;
    }
    public void setContinuingEducation(BigDecimal continuingEducation) 
    {
        this.continuingEducation = continuingEducation;
    }

    public BigDecimal getContinuingEducation() 
    {
        return continuingEducation;
    }
    public void setHousingLoanInterest(BigDecimal housingLoanInterest) 
    {
        this.housingLoanInterest = housingLoanInterest;
    }

    public BigDecimal getHousingLoanInterest() 
    {
        return housingLoanInterest;
    }
    public void setHousingRent(BigDecimal housingRent) 
    {
        this.housingRent = housingRent;
    }

    public BigDecimal getHousingRent() 
    {
        return housingRent;
    }
    public void setSupportingElderly(BigDecimal supportingElderly) 
    {
        this.supportingElderly = supportingElderly;
    }

    public BigDecimal getSupportingElderly() 
    {
        return supportingElderly;
    }
    public void setAnnuity(BigDecimal annuity) 
    {
        this.annuity = annuity;
    }

    public BigDecimal getAnnuity() 
    {
        return annuity;
    }
    public void setCommercialInsurance(BigDecimal commercialInsurance) 
    {
        this.commercialInsurance = commercialInsurance;
    }

    public BigDecimal getCommercialInsurance() 
    {
        return commercialInsurance;
    }
    public void setTaxPension(BigDecimal taxPension) 
    {
        this.taxPension = taxPension;
    }

    public BigDecimal getTaxPension() 
    {
        return taxPension;
    }
    public void setOther(BigDecimal other) 
    {
        this.other = other;
    }

    public BigDecimal getOther() 
    {
        return other;
    }
    public void setAllowedDeductDonation(BigDecimal allowedDeductDonation) {
        this.allowedDeductDonation = allowedDeductDonation;
    }

    public BigDecimal getAllowedDeductDonation() 
    {
        return allowedDeductDonation;
    }
    public void setTaxSavings(BigDecimal taxSavings) 
    {
        this.taxSavings = taxSavings;
    }

    public BigDecimal getTaxSavings() 
    {
        return taxSavings;
    }
    public void setTaxPayment(BigDecimal taxPayment) 
    {
        this.taxPayment = taxPayment;
    }

    public BigDecimal getTaxPayment() 
    {
        return taxPayment;
    }
    public void setPaidTax(BigDecimal paidTax) 
    {
        this.paidTax = paidTax;
    }

    public BigDecimal getPaidTax() 
    {
        return paidTax;
    }
    public void setAnnuityTax(BigDecimal annuityTax) 
    {
        this.annuityTax = annuityTax;
    }

    public BigDecimal getAnnuityTax() 
    {
        return annuityTax;
    }
    public void setTaxPaymentPlace(String taxPaymentPlace) 
    {
        this.taxPaymentPlace = taxPaymentPlace;
    }

    public String getTaxPaymentPlace() 
    {
        return taxPaymentPlace;
    }

    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public BigDecimal getSeverance() {
        return severance;
    }
    public void setSeverance(BigDecimal severance) {
        this.severance = severance;
    }

    public String getGroupIds() {
        return groupIds;
    }
    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("idNo", getIdNo())
            .append("duration", getDuration())
            .append("erName", getErName())
            .append("employmentNature", getEmploymentNature())
            .append("name", getName())
            .append("credentials", getCredentials())
            .append("credentialsNumber", getCredentialsNumber())
            .append("income", getIncome())
            .append("taxFreeIncome", getTaxFreeIncome())
            .append("pension", getPension())
            .append("medical", getMedical())
            .append("unemployment", getUnemployment())
            .append("housingFund", getHousingFund())
            .append("childrenEducation", getChildrenEducation())
            .append("continuingEducation", getContinuingEducation())
            .append("housingLoanInterest", getHousingLoanInterest())
            .append("housingRent", getHousingRent())
            .append("supportingElderly", getSupportingElderly())
            .append("annuity", getAnnuity())
            .append("commercialInsurance", getCommercialInsurance())
            .append("taxPension", getTaxPension())
            .append("other", getOther())
            .append("allowedDeductDonation", getAllowedDeductDonation())
            .append("taxSavings", getTaxSavings())
            .append("remarks", getRemarks())
            .append("taxPayment", getTaxPayment())
            .append("paidTax", getPaidTax())
            .append("annuityTax", getAnnuityTax())
            .append("taxPaymentPlace", getTaxPaymentPlace())
            .append("severance", getSeverance())
            .toString();
    }
}
