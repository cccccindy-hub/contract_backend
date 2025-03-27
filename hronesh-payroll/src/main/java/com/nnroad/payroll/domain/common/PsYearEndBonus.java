package com.nnroad.payroll.domain.common;

import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * ps_year_end_bonus对象 ps_year_end_bonus
 * 
 * @author Hrone
 * @date 2021-01-21
 */
public class PsYearEndBonus extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 年终奖税ID */
    private Long id;

    /** 工号 */
    @Excel(name = "excel.yearendbonustaxp.id_no")
    private String idNo;

    /** 期间 */
    private String duration;

    /** 客户简称 */
    @Excel(name = "excel.yearendbonustaxp.er_name")
    private String erName;

    /** 用工性质 */
    @Excel(name = "excel.yearendbonustaxp.employment_nature")
    private String employmentNature;

    /** 姓名 */
    @Excel(name = "excel.yearendbonustaxp.name")
    private String name;

    /** 证照类型 */
    @Excel(name = "excel.yearendbonustaxp.credentials")
    private String credentials;

    /** 证照号码 */
    @Excel(name = "excel.yearendbonustaxp.credentials_number")
    private String credentialsNumber;

    /** 全年一次性奖金额 */
    @Excel(name = "excel.yearendbonustaxp.annual_sum")
    private BigDecimal annualSum;

    /** 免税收入 */
    @Excel(name = "excel.yearendbonustaxp.tax_free_income")
    private BigDecimal taxFreeIncome;

    /** 企业(职业)年金 */
    @Excel(name = "excel.yearendbonustaxp.annuity")
    private BigDecimal annuity;

    /** 商业保险 */
    @Excel(name = "excel.yearendbonustaxp.commercial_insurance")
    private BigDecimal commercialInsurance;

    /** 税延养老保险 */
    @Excel(name = "excel.yearendbonustaxp.tax_pension")
    private BigDecimal taxPension;

    /** 其他 */
    @Excel(name = "excel.yearendbonustaxp.other")
    private BigDecimal other;

    /** 准予扣除的捐赠额 */
    @Excel(name = "excel.yearendbonustaxp.allowed_deduct_donation")
    private BigDecimal allowedDeductDonation;

    /** 减免税额 */
    @Excel(name = "excel.yearendbonustaxp.tax_savings")
    private BigDecimal taxSavings;

    /** 已扣缴税额 */
    @Excel(name = "excel.yearendbonustaxp.tax_withheld")
    private BigDecimal taxWithheld;

    /** 年终奖个税 */
    @Excel(name = "excel.yearendbonustaxp.annuity_tax")
    private BigDecimal annuityTax;

    /** 个税缴纳地 */
    @Excel(name = "excel.yearendbonustaxp.tax_payment_place")
    private String taxPaymentPlace;

    /** 备注 */
    @Excel(name = "excel.yearendbonustaxp.remarks")
    private String remarks;

    /** 权限组 */
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
    public void setAnnualSum(BigDecimal annualSum) 
    {
        this.annualSum = annualSum;
    }

    public BigDecimal getAnnualSum() 
    {
        return annualSum;
    }
    public void setTaxFreeIncome(BigDecimal taxFreeIncome) 
    {
        this.taxFreeIncome = taxFreeIncome;
    }

    public BigDecimal getTaxFreeIncome() 
    {
        return taxFreeIncome;
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
    public void setAllowedDeductDonation(BigDecimal allowedDeductDonation) 
    {
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
    public void setTaxWithheld(BigDecimal taxWithheld) 
    {
        this.taxWithheld = taxWithheld;
    }

    public BigDecimal getTaxWithheld() 
    {
        return taxWithheld;
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
            .append("annualSum", getAnnualSum())
            .append("taxFreeIncome", getTaxFreeIncome())
            .append("annuity", getAnnuity())
            .append("commercialInsurance", getCommercialInsurance())
            .append("taxPension", getTaxPension())
            .append("other", getOther())
            .append("allowedDeductDonation", getAllowedDeductDonation())
            .append("taxSavings", getTaxSavings())
            .append("taxWithheld", getTaxWithheld())
            .append("remark", getRemark())
            .append("annuityTax", getAnnuityTax())
            .append("taxPaymentPlace", getTaxPaymentPlace())
            .toString();
    }
}
