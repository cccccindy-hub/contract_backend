package com.nnroad.payroll.domain.exportC;

import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;

import com.nnroad.common.utils.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;


public class SEmployeeInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 部门 */
    @Excel(name="Department")
    String department;

    /** 工号 */
    @Excel(name="ID No.")
    String idNo;

    /** 姓名 */
    @Excel(name="Name")
    String name;

    /** 社保城市 */
    @Excel(name="Mandatory Benefits Location")
    String socialBenefitsAddress;

    /** 养老基数 */
    @Excel(name="Pension Base", cellType=Excel.ColumnType.NUMERIC)
    BigDecimal pensionBase;

    /** 医疗基数 */
    @Excel(name="Medical Base", cellType=Excel.ColumnType.NUMERIC)
    BigDecimal medicalBase;

    /** 失业基数 */
    @Excel(name="Unemployment Base", cellType=Excel.ColumnType.NUMERIC)
    BigDecimal unemploymentBase;

    /** 生育基数 */
    @Excel(name="Maternity Base", cellType=Excel.ColumnType.NUMERIC)
    BigDecimal maternityBase;

    /** 工伤基数 */
    @Excel(name="Work Related Injury Base", cellType=Excel.ColumnType.NUMERIC)
    BigDecimal workRelatedInjuryBase;

    /** 残保金基数 */
    @Excel(name="Disability Base", cellType=Excel.ColumnType.NUMERIC)
    BigDecimal disabilityBase;

    /** 公积金基数 */
    @Excel(name="Housing Fund Base", cellType=Excel.ColumnType.NUMERIC)
    BigDecimal housingFundBase;

    /** 补充公积金基数 */
    @Excel(name="Supplementary Housing Fund Base", cellType=Excel.ColumnType.NUMERIC)
    BigDecimal shfBase;

    /** 个人养老% */
    @Excel(name="Personal Pension%", suffix="%")
    String personalPension;

    /** 个人医疗% */
    @Excel(name="Personal Medical%", suffix="%")
    String personalMedical;

    /** 个人失业% */
    @Excel(name="Personal Unemployment%", suffix="%")
    String personalUnemployment;

    /** 个人公积金% */
    @Excel(name="Personal Housing Fund%", suffix="%")
    String personalHousingFund;

    /** 个人补充公积金%（免税） */
    @Excel(name="Personal Supplementary Housing Fund%", suffix="%")
    String personalShfTe;

    /** 个人医疗固定数 */
    @Excel(name="Personal Medical Fixed Number",cellType=Excel.ColumnType.NUMERIC)
    String personalMedicalFixedNumber;

    /** 公司养老% */
    @Excel(name="Employer Pension%", suffix="%")
    String companyPension;

    /** 公司医疗% */
    @Excel(name="Employer Medical%", suffix="%")
    String companyMedical;

    /** 公司失业% */
    @Excel(name="Employer Unemployment%", suffix="%")
    String companyUnemployment;

    /** 公司生育% */
    @Excel(name="Employer Maternity%", suffix="%")
    String companyMaternity;

    /** 公司工伤% */
    @Excel(name="Employer Work Related Injury%", suffix="%")
    String companyWorkRelatedInjury;

    /** 公司残保金% */
    @Excel(name="Employer Disability Base%", suffix="%")
    String companyDisability;

    /** 公司公积金% */
    @Excel(name="Employer Housing Fund%", suffix="%")
    String companyHousingFund;

    /** 公司补充公积金% */
    @Excel(name="Employer Supplementary Housing Fund%", suffix="%")
    String companyShf;

    /** 公司医疗固定数 */
    @Excel(name="Employer Medical Fixed Number", cellType=Excel.ColumnType.NUMERIC)
    BigDecimal companyMedicalFixedNumber;

    /** 公司工伤固定数 */
    @Excel(name="Employer Work Related Injury Fixed Number", cellType=Excel.ColumnType.NUMERIC)
    BigDecimal companyEriFixedNumber;

    /** 公司残保固定数 */
    @Excel(name="Employer Disability Fixed Number", cellType=Excel.ColumnType.NUMERIC)
    BigDecimal companyDisabilityFixedNumber;

    /** 累计子女教育 */
    @Excel(name="Accumulated Child Education", cellType=Excel.ColumnType.NUMERIC)
    BigDecimal accumulatedChildEducation;

    /** 累计继续教育 */
    @Excel(name="Accumulated Continuing Education", cellType=Excel.ColumnType.NUMERIC)
    BigDecimal accumulatedContinuingEducation;

    /** 累计住房贷款利息 */
    @Excel(name="Accumulated Home Loan Interest", cellType=Excel.ColumnType.NUMERIC)
    BigDecimal accumulatedHli;

    /** 累计住房租金 */
    @Excel(name="Accumulated Housing Rent", cellType=Excel.ColumnType.NUMERIC)
    BigDecimal accumulatedHousingRent;

    /** 累计赡养老人 */
    @Excel(name="Accumulated Support Parents", cellType=Excel.ColumnType.NUMERIC)
    BigDecimal accumulatedSfte;

    /** 累计婴幼儿照护费用 */
    @Excel(name="Accumulated Child Care", cellType=Excel.ColumnType.NUMERIC)
    BigDecimal accumulatedChildCare;

    /** 商业保险方案级别 */
    @Excel(name="Level")
    String commercialInsuranceLevel;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getSocialBenefitsAddress() {
        return socialBenefitsAddress;
    }

    public void setSocialBenefitsAddress(String socialBenefitsAddress) {
        this.socialBenefitsAddress = socialBenefitsAddress;
    }

    public BigDecimal getPensionBase() {
        return pensionBase;
    }

    public void setPensionBase(BigDecimal pensionBase) {
        this.pensionBase = pensionBase;
    }

    public BigDecimal getMedicalBase() {
        return medicalBase;
    }

    public void setMedicalBase(BigDecimal medicalBase) {
        this.medicalBase = medicalBase;
    }

    public BigDecimal getUnemploymentBase() {
        return unemploymentBase;
    }

    public void setUnemploymentBase(BigDecimal unemploymentBase) {
        this.unemploymentBase = unemploymentBase;
    }

    public BigDecimal getMaternityBase() {
        return maternityBase;
    }

    public void setMaternityBase(BigDecimal maternityBase) {
        this.maternityBase = maternityBase;
    }

    public BigDecimal getWorkRelatedInjuryBase() {
        return workRelatedInjuryBase;
    }

    public void setWorkRelatedInjuryBase(BigDecimal workRelatedInjuryBase) {
        this.workRelatedInjuryBase = workRelatedInjuryBase;
    }

    public BigDecimal getDisabilityBase() {
        return disabilityBase;
    }

    public void setDisabilityBase(BigDecimal disabilityBase) {
        this.disabilityBase = disabilityBase;
    }

    public BigDecimal getHousingFundBase() {
        return housingFundBase;
    }

    public void setHousingFundBase(BigDecimal housingFundBase) {
        this.housingFundBase = housingFundBase;
    }

    public BigDecimal getShfBase() {
        return shfBase;
    }

    public void setShfBase(BigDecimal shfBase) {
        this.shfBase = shfBase;
    }

    public String getPersonalPension() {
        return StringUtils.isEmpty(personalPension) ? "0" :  String.valueOf(Math.round(Double.parseDouble(personalPension)*10000)/100.0);
    }

    public void setPersonalPension(String personalPension) {
        this.personalPension = personalPension;
    }

    public String getPersonalMedical() {
        return StringUtils.isEmpty(personalMedical) ? "0" :  String.valueOf(Math.round(Double.parseDouble(personalMedical)*10000)/100.0);
    }

    public void setPersonalMedical(String personalMedical) {
        this.personalMedical = personalMedical;
    }

    public String getPersonalUnemployment() {
        return StringUtils.isEmpty(personalUnemployment) ? "0" :  String.valueOf(Math.round(Double.parseDouble(personalUnemployment)*10000)/100.0);
    }

    public void setPersonalUnemployment(String personalUnemployment) {
        this.personalUnemployment = personalUnemployment;
    }

    public String getPersonalHousingFund() {
        return StringUtils.isEmpty(personalHousingFund) ? "0" :  String.valueOf(Math.round(Double.parseDouble(personalHousingFund)*10000)/100.0);
    }

    public void setPersonalHousingFund(String personalHousingFund) {
        this.personalHousingFund = personalHousingFund;
    }

    public String getCompanyPension() {
        return StringUtils.isEmpty(companyPension)?"0": String.valueOf(Math.round(Double.parseDouble(companyPension)*10000)/100.0);
    }

    public void setCompanyPension(String companyPension) {
        this.companyPension = companyPension;
    }

    public String getCompanyUnemployment() {
        return StringUtils.isEmpty(companyUnemployment)?"0": String.valueOf(Math.round(Double.parseDouble(companyUnemployment)*10000)/100.0);
    }

    public void setCompanyUnemployment(String companyUnemployment) {
        this.companyUnemployment = companyUnemployment;
    }

    public String getCompanyMaternity() {
        return StringUtils.isEmpty(companyMaternity)?"0": String.valueOf(Math.round(Double.parseDouble(companyMaternity)*10000)/100.0);
    }

    public void setCompanyMaternity(String companyMaternity) {
        this.companyMaternity = companyMaternity;
    }

    public String getCompanyWorkRelatedInjury() {
        return StringUtils.isEmpty(companyWorkRelatedInjury)?"0": String.valueOf(Math.round(Double.parseDouble(companyWorkRelatedInjury)*10000)/100.0);
    }

    public void setCompanyWorkRelatedInjury(String companyWorkRelatedInjury) {
        this.companyWorkRelatedInjury = companyWorkRelatedInjury;
    }

    public String getCompanyDisability() {
        return StringUtils.isEmpty(companyDisability)?"0": String.valueOf(Math.round(Double.parseDouble(companyDisability)*10000)/100.0);
    }

    public void setCompanyDisability(String companyDisability) {
        this.companyDisability = companyDisability;
    }

    public String getCompanyHousingFund() {
        return StringUtils.isEmpty(companyHousingFund)?"0": String.valueOf(Math.round(Double.parseDouble(companyHousingFund)*10000)/100.0);
    }

    public void setCompanyHousingFund(String companyHousingFund) {
        this.companyHousingFund = companyHousingFund;
    }

    public String getCompanyShf() {
        return StringUtils.isEmpty(companyShf)?"0": String.valueOf(Math.round(Double.parseDouble(companyShf)*10000)/100.0);
    }

    public void setCompanyShf(String companyShf) {
        this.companyShf = companyShf;
    }

    public BigDecimal getCompanyMedicalFixedNumber() {
        return companyMedicalFixedNumber == null ? BigDecimal.ZERO:companyMedicalFixedNumber.multiply(BigDecimal.valueOf(100));
    }

    public void setCompanyMedicalFixedNumber(BigDecimal companyMedicalFixedNumber) {
        this.companyMedicalFixedNumber = companyMedicalFixedNumber;
    }

    public String getCommercialInsuranceLevel() {
        return commercialInsuranceLevel;
    }

    public void setCommercialInsuranceLevel(String commercialInsuranceLevel) {
        this.commercialInsuranceLevel = commercialInsuranceLevel;
    }

    public BigDecimal getAccumulatedChildEducation() {
        return accumulatedChildEducation;
    }

    public void setAccumulatedChildEducation(BigDecimal accumulatedChildEducation) {
        this.accumulatedChildEducation = accumulatedChildEducation;
    }

    public BigDecimal getAccumulatedContinuingEducation() {
        return accumulatedContinuingEducation;
    }

    public void setAccumulatedContinuingEducation(BigDecimal accumulatedContinuingEducation) {
        this.accumulatedContinuingEducation = accumulatedContinuingEducation;
    }

    public BigDecimal getAccumulatedHli() {
        return accumulatedHli;
    }

    public void setAccumulatedHli(BigDecimal accumulatedHli) {
        this.accumulatedHli = accumulatedHli;
    }

    public BigDecimal getAccumulatedHousingRent() {
        return accumulatedHousingRent;
    }

    public void setAccumulatedHousingRent(BigDecimal accumulatedHousingRent) {
        this.accumulatedHousingRent = accumulatedHousingRent;
    }

    public BigDecimal getAccumulatedSfte() {
        return accumulatedSfte;
    }

    public void setAccumulatedSfte(BigDecimal accumulatedSfte) {
        this.accumulatedSfte = accumulatedSfte;
    }

    public BigDecimal getAccumulatedChildCare() {
        return accumulatedChildCare;
    }

    public void setAccumulatedChildCare(BigDecimal accumulatedChildCare) {
        this.accumulatedChildCare = accumulatedChildCare;
    }

    public String getCompanyMedical() {
        return StringUtils.isEmpty(companyMedical)?"0": String.valueOf(Math.round(Double.parseDouble(companyMedical)*10000)/100.0);
    }

    public void setCompanyMedical(String companyMedical) {
        this.companyMedical = companyMedical;
    }

    public String getPersonalShfTe() {
        return personalShfTe;
    }

    public void setPersonalShfTe(String personalShfTe) {
        this.personalShfTe = personalShfTe;
    }

    public String getPersonalMedicalFixedNumber() {
        return personalMedicalFixedNumber;
    }

    public void setPersonalMedicalFixedNumber(String personalMedicalFixedNumber) {
        this.personalMedicalFixedNumber = personalMedicalFixedNumber;
    }

    public BigDecimal getCompanyEriFixedNumber() {
        return companyEriFixedNumber;
    }

    public void setCompanyEriFixedNumber(BigDecimal companyEriFixedNumber) {
        this.companyEriFixedNumber = companyEriFixedNumber;
    }

    public BigDecimal getCompanyDisabilityFixedNumber() {
        return companyDisabilityFixedNumber;
    }

    public void setCompanyDisabilityFixedNumber(BigDecimal companyDisabilityFixedNumber) {
        this.companyDisabilityFixedNumber = companyDisabilityFixedNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("department",getDepartment())
                .append("idNo",getIdNo())
                .append("name",getName())
                .append("socialBenefitsAddress",getSocialBenefitsAddress())
                .append("pensionBase",getPensionBase())
                .append("medicalBase",getMedicalBase())
                .append("unemploymentBase",getUnemploymentBase())
                .append("maternityBase",getMaternityBase())
                .append("workRelatedInjuryBase",getWorkRelatedInjuryBase())
                .append("disabilityBase",getDisabilityBase())
                .append("housingFundBase",getHousingFundBase())
                .append("shfBase",getShfBase())
                .append("personalPension",getPersonalPension())
                .append("personalMedical",getPersonalMedical())
                .append("personalUnemployment",getPersonalUnemployment())
                .append("personalHousingFund",getPersonalHousingFund())
                .append("personalShfTe",getPersonalShfTe())
                .append("personalMedicalFixedNumber",getPersonalMedicalFixedNumber())
                .append("companyPension",getCompanyPension())
                .append("companyMedical",getCompanyMedical())
                .append("companyUnemployment",getCompanyUnemployment())
                .append("companyMaternity",getCompanyMaternity())
                .append("companyWorkRelatedInjury",getCompanyWorkRelatedInjury())
                .append("companyDisability",getCompanyDisability())
                .append("companyHousingFund",getCompanyHousingFund())
                .append("companyShf",getCompanyShf())
                .append("companyMedicalFixedNumber",getCompanyMedicalFixedNumber())
                .append("companyEriFixedNumber",getCompanyEriFixedNumber())
                .append("companyDisabilityFixedNumber",getCompanyDisabilityFixedNumber())
                .append("accumulatedChildEducation",getAccumulatedChildEducation())
                .append("accumulatedContinuingEducation",getAccumulatedContinuingEducation())
                .append("accumulatedHli",getAccumulatedHli())
                .append("accumulatedHousingRent",getAccumulatedHousingRent())
                .append("accumulatedSfte",getAccumulatedSfte())
                .append("accumulatedChildCare",getAccumulatedChildCare())
                .append("commercialInsuranceLevel",getCommercialInsuranceLevel())
                .toString();
    }
}
