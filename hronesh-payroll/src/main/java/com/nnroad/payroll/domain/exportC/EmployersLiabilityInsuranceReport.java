package com.nnroad.payroll.domain.exportC;

import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 雇主责任险对象 employers_liability_insurance_report
 *
 * @author liwengang
 * @date 2022-01-05
 */
public class EmployersLiabilityInsuranceReport extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 员工编号
     */
    @Excel(name = "员工编号")
    private String employeeCode;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String name;

    /**
     * 员工编号
     */
    @Excel(name = "客户编号")
    private String clientCode;

    /**
     * 客户公司名称
     */
    @Excel(name = "客户公司名称")
    private String clientCompanyName;

    /**
     * 身份证号码
     */
    @Excel(name = "身份证号码")
    private String idNumber;

    /**
     * 税前月收入
     */
    @Excel(name = "税前月收入")
    private String monthlyIncomeBeforeTax;

    /**
     * 年保费前
     */
    private BigDecimal beforeTaxAnnualPremium;

    /**
     * 年保费税后
     */
    private BigDecimal afterTaxAnnualPremium;

    /**
     * 年保费
     */
    @Excel(name = "年保费")
    private BigDecimal annualPremium;

    /**
     * 加保日期
     */
    @Excel(name = "加保日期")
    private String dateOfInsuranceIncrease;

    /**
     * 劳动合同签署地
     */
    @Excel(name = "劳动合同签署地")
    private String placeOfEmploymentContractSigning;

    /**
     * 是否离职
     */
    @Excel(name = "是否离职")
    private String isLeave;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setMonthlyIncomeBeforeTax(String monthlyIncomeBeforeTax) {
        this.monthlyIncomeBeforeTax = monthlyIncomeBeforeTax;
    }

    public String getMonthlyIncomeBeforeTax() {
        return monthlyIncomeBeforeTax;
    }

    public void setAnnualPremium(BigDecimal annualPremium) {
        this.annualPremium = annualPremium;
    }

    public BigDecimal getAnnualPremium() {
        return annualPremium;
    }

    public void setDateOfInsuranceIncrease(String dateOfInsuranceIncrease) {
        this.dateOfInsuranceIncrease = dateOfInsuranceIncrease;
    }

    public String getDateOfInsuranceIncrease() {
        return dateOfInsuranceIncrease;
    }

    public void setClientCompanyName(String clientCompanyName) {
        this.clientCompanyName = clientCompanyName;
    }

    public String getClientCompanyName() {
        return clientCompanyName;
    }

    public void setPlaceOfEmploymentContractSigning(String placeOfEmploymentContractSigning) {
        this.placeOfEmploymentContractSigning = placeOfEmploymentContractSigning;
    }

    public String getPlaceOfEmploymentContractSigning() {
        return placeOfEmploymentContractSigning;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("name", getName())
                .append("idNumber", getIdNumber())
                .append("monthlyIncomeBeforeTax", getMonthlyIncomeBeforeTax())
                .append("annualPremium", getAnnualPremium())
                .append("dateOfInsuranceIncrease", getDateOfInsuranceIncrease())
                .append("clientCompanyName", getClientCompanyName())
                .append("placeOfEmploymentContractSigning", getPlaceOfEmploymentContractSigning())
                .append("employeeCode", getEmployeeCode())
                .append("afterTaxAnnualPremium", getAfterTaxAnnualPremium())
                .append("beforeTaxAnnualPremium", getBeforeTaxAnnualPremium())
                .append("clientCode", getClientCode())
                .append("isLeave", getIsLeave())
                .toString();
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }


    public BigDecimal getAfterTaxAnnualPremium() {
        return afterTaxAnnualPremium;
    }

    public void setAfterTaxAnnualPremium(BigDecimal afterTaxAnnualPremium) {
        this.afterTaxAnnualPremium = afterTaxAnnualPremium;
    }

    public BigDecimal getBeforeTaxAnnualPremium() {
        return beforeTaxAnnualPremium;
    }

    public void setBeforeTaxAnnualPremium(BigDecimal beforeTaxAnnualPremium) {
        this.beforeTaxAnnualPremium = beforeTaxAnnualPremium;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getIsLeave() {
        return isLeave;
    }

    public void setIsLeave(String isLeave) {
        this.isLeave = isLeave;
    }
}
