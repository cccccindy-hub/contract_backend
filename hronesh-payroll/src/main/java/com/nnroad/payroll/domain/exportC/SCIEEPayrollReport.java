package com.nnroad.payroll.domain.exportC;

import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * VIEW对象 s_payroll_report
 * 
 * @author Hrone
 * @date 2021-06-07
 */
public class SCIEEPayrollReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 期间 */
    private String duration;

    /** 工号 */
    @Excel(name = "export.CIEE_payroll_idNo")
    private String idNo;

    /** 姓名 */
    @Excel(name = "export.CIEE_payroll_name")
    private String name;

    /** 身份证号 */
    @Excel(name = "export.CIEE_payroll_idNumber")
    private String idNumber;

    /** 社保基数 */
    @Excel(name = "export.CIEE_payroll_socialBenefitsBasis")
    private BigDecimal socialBenefitsBasis;

    /** 公积金基数 */
    @Excel(name = "export.CIEE_payroll_housingFundBasis")
    private BigDecimal housingFundBasis;

    /** 基本工资 */
    @Excel(name = "export.CIEE_payroll_basicSalary")
    private BigDecimal basicSalary;

    /** 其它调整 */
    @Excel(name = "export.CIEE_payroll_adjustment")
    private BigDecimal adjustment;

    /** 应发工资 */
    @Excel(name = "export.CIEE_payroll_pretaxSalary")
    private BigDecimal pretaxSalary;

    /** 扣个调税 */
    @Excel(name = "export.CIEE_payroll_incomeTax")
    private BigDecimal incomeTax;

    /** 其它调整 */
    @Excel(name = "export.CIEE_payroll_others")
    private BigDecimal others;

    /** 实发工资 */
    @Excel(name = "export.CIEE_payroll_netSalary")
    private BigDecimal netSalary;

    /** 累计应纳税所得额 */
    @Excel(name = "export.CIEE_payroll_ytdTax")
    private BigDecimal ytdTax;

    /** 备注 */
    @Excel(name = "export.CIEE_payroll_remarks")
    private String remarks;

    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getIdNo() {
        return idNo;
    }
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public BigDecimal getSocialBenefitsBasis() {
        return socialBenefitsBasis;
    }
    public void setSocialBenefitsBasis(BigDecimal socialBenefitsBasis) {
        this.socialBenefitsBasis = socialBenefitsBasis;
    }

    public BigDecimal getHousingFundBasis() {
        return housingFundBasis;
    }
    public void setHousingFundBasis(BigDecimal housingFundBasis) {
        this.housingFundBasis = housingFundBasis;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }
    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getAdjustment() {
        return adjustment;
    }
    public void setAdjustment(BigDecimal adjustment) {
        this.adjustment = adjustment;
    }

    public BigDecimal getPretaxSalary() {
        return pretaxSalary;
    }
    public void setPretaxSalary(BigDecimal pretaxSalary) {
        this.pretaxSalary = pretaxSalary;
    }

    public BigDecimal getIncomeTax() {
        return incomeTax;
    }
    public void setIncomeTax(BigDecimal incomeTax) {
        this.incomeTax = incomeTax;
    }

    public BigDecimal getOthers() {
        return others;
    }
    public void setOthers(BigDecimal others) {
        this.others = others;
    }

    public BigDecimal getNetSalary() {
        return netSalary;
    }
    public void setNetSalary(BigDecimal netSalary) {
        this.netSalary = netSalary;
    }

    public BigDecimal getYtdTax() {
        return ytdTax;
    }
    public void setYtdTax(BigDecimal ytdTax) {
        this.ytdTax = ytdTax;
    }

    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("duration", getDuration())
            .append("idNo", getIdNo())
            .append("name", getName())
            .append("idNumber", getIdNumber())
            .append("socialBenefitsBasis", getSocialBenefitsBasis())
            .append("housingFundBasis", getHousingFundBasis())
            .append("basicSalary", getBasicSalary())
            .append("adjustment", getAdjustment())
            .append("pretaxSalary", getPretaxSalary())
            .append("incomeTax", getIncomeTax())
            .append("others", getOthers())
            .append("netSalary", getNetSalary())
            .append("ytdTax", getYtdTax())
            .append("remarks", getRemarks())
            .toString();
    }
}
