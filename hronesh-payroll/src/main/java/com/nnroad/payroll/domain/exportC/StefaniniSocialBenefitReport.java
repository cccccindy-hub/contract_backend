package com.nnroad.payroll.domain.exportC;

import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * social_benefit_report对象 social_benefit_report
 *
 * @author hrone
 * @date 2021-01-19
 */
public class StefaniniSocialBenefitReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 员工编号 */
    @Excel(name = "员工编号")
    private String idNo;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String erName;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String name;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String certificateNumber;

    /** 社保城市 */
    @Excel(name = "社保城市")
    private String socialBenefitsAddress;

    /** 期间 */
    @Excel(name = "期间")
    private String duration;

    /** 社保基数 */
    @Excel(name = "社保基数", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal pensionBase;

    /** 公积金基数 */
    @Excel(name = "公积金基数", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal housingFundBase;

    /** 养老保险-员工 */
    @Excel(name = "养老保险-员工", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal pension;

    /** 医疗保险-员工 */
    @Excel(name = "医疗保险-员工", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal medical;

    /** 失业保险-员工 */
    @Excel(name = "失业保险-员工", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal unemployment;

    /** 养老保险补缴-员工 */
    @Excel(name = "养老保险补缴-员工", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal pensionRepayment;

    /** 医疗保险补缴-员工 */
    @Excel(name = "医疗保险补缴-员工", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal medicalRepayment;

    /** 失业保险补缴-员工 */
    @Excel(name = "失业保险补缴-员工", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal unemploymentRepayment;

    /** 社保合计-员工 */
    @Excel(name = "社保合计-员工", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal sumPersonSb;

    /** 养老保险-公司 */
    @Excel(name = "养老保险-公司", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal companyPension;

    /** 医疗保险-公司 */
    @Excel(name = "医疗保险-公司", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal companyMedical;

    /** 失业保险-公司 */
    @Excel(name = "失业保险-公司", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal companyUnemployment;

    /** 工伤保险-公司 */
    @Excel(name = "工伤保险-公司", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal companyWri;

    /** 生育保险-公司 */
    @Excel(name = "生育保险-公司", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal companyMaternity;

    /** 养老保险补缴-公司 */
    @Excel(name = "养老保险补缴-公司", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal erPensionRepayment;

    /** 医疗保险补缴-公司 */
    @Excel(name = "医疗保险补缴-公司", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal erMedicalRepayment;

    /** 失业保险补缴-公司 */
    @Excel(name = "失业保险补缴-公司", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal erUnemployementRepayment;

    /** 工伤保险补缴-公司 */
    @Excel(name = "工伤保险补缴-公司", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal erWriRepayment;

    /** 生育保险补缴-公司 */
    @Excel(name = "生育保险补缴-公司", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal erMaternityRepayment;

    /** 五险合计-公司 */
    @Excel(name = "五险合计-公司", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal sumCompanyFiveSi;

    /** 五险合计 */
    @Excel(name = "五险合计", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal sumFiveSi;

    /** 采暖费-公司 */
    @Excel(name = "采暖费-公司", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal heatingFee;

    /** 残保金-公司 */
    @Excel(name = "残保金-公司", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal companyDisability;

    /** 社保其他-公司 */
    @Excel(name = "社保其他-公司", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal companySbOther;

    /** 采暖费补缴-公司 */
    @Excel(name = "采暖费补缴-公司", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal heatingFeeRepayment;

    /** 残保金补缴-公司 */
    @Excel(name = "残保金补缴-公司", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal cdRepayment;

    /** 社保总计 */
    @Excel(name = "社保总计", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal sumSi;

    /** 住房公积金-员工 */
    @Excel(name = "住房公积金-员工", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal housingFund;

    /** 住房公积金补缴-员工 */
    @Excel(name = "住房公积金补缴-员工", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal housingFundPayment;

    /** 公积金合计-员工 */
    @Excel(name = "公积金合计-员工", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal sumPersonHf;

    /** 住房公积金-公司 */
    @Excel(name = "住房公积金-公司", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal companyHousingFund;

    /** 住房公积金补缴-公司 */
    @Excel(name = "住房公积金补缴-公司", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal chfRepayment;

    /** 公积金合计-公司 */
    @Excel(name = "公积金合计-公司", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal sumCompanyHf;

    /** 本月公积金合计 */
    @Excel(name = "本月公积金合计", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal sumHf;

    /** 本月社保公积金合计 */
    @Excel(name = "本月社保公积金合计", cellType = Excel.ColumnType.NUMERIC, scale = 2)
    private BigDecimal sumSiHf;

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getErName() {
        return erName;
    }

    public void setErName(String erName) {
        this.erName = erName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getSocialBenefitsAddress() {
        return socialBenefitsAddress;
    }

    public void setSocialBenefitsAddress(String socialBenefitsAddress) {
        this.socialBenefitsAddress = socialBenefitsAddress;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public BigDecimal getPensionBase() {
        return pensionBase;
    }

    public void setPensionBase(BigDecimal pensionBase) {
        this.pensionBase = pensionBase;
    }

    public BigDecimal getHousingFundBase() {
        return housingFundBase;
    }

    public void setHousingFundBase(BigDecimal housingFundBase) {
        this.housingFundBase = housingFundBase;
    }

    public BigDecimal getPension() {
        return pension;
    }

    public void setPension(BigDecimal pension) {
        this.pension = pension;
    }

    public BigDecimal getMedical() {
        return medical;
    }

    public void setMedical(BigDecimal medical) {
        this.medical = medical;
    }

    public BigDecimal getUnemployment() {
        return unemployment;
    }

    public void setUnemployment(BigDecimal unemployment) {
        this.unemployment = unemployment;
    }

    public BigDecimal getPensionRepayment() {
        return pensionRepayment;
    }

    public void setPensionRepayment(BigDecimal pensionRepayment) {
        this.pensionRepayment = pensionRepayment;
    }

    public BigDecimal getMedicalRepayment() {
        return medicalRepayment;
    }

    public void setMedicalRepayment(BigDecimal medicalRepayment) {
        this.medicalRepayment = medicalRepayment;
    }

    public BigDecimal getUnemploymentRepayment() {
        return unemploymentRepayment;
    }

    public void setUnemploymentRepayment(BigDecimal unemploymentRepayment) {
        this.unemploymentRepayment = unemploymentRepayment;
    }

    public BigDecimal getSumPersonSb() {
        return sumPersonSb;
    }

    public void setSumPersonSb(BigDecimal sumPersonSb) {
        this.sumPersonSb = sumPersonSb;
    }

    public BigDecimal getCompanyPension() {
        return companyPension;
    }

    public void setCompanyPension(BigDecimal companyPension) {
        this.companyPension = companyPension;
    }

    public BigDecimal getCompanyMedical() {
        return companyMedical;
    }

    public void setCompanyMedical(BigDecimal companyMedical) {
        this.companyMedical = companyMedical;
    }

    public BigDecimal getCompanyUnemployment() {
        return companyUnemployment;
    }

    public void setCompanyUnemployment(BigDecimal companyUnemployment) {
        this.companyUnemployment = companyUnemployment;
    }

    public BigDecimal getCompanyWri() {
        return companyWri;
    }

    public void setCompanyWri(BigDecimal companyWri) {
        this.companyWri = companyWri;
    }

    public BigDecimal getCompanyMaternity() {
        return companyMaternity;
    }

    public void setCompanyMaternity(BigDecimal companyMaternity) {
        this.companyMaternity = companyMaternity;
    }

    public BigDecimal getErPensionRepayment() {
        return erPensionRepayment;
    }

    public void setErPensionRepayment(BigDecimal erPensionRepayment) {
        this.erPensionRepayment = erPensionRepayment;
    }

    public BigDecimal getErMedicalRepayment() {
        return erMedicalRepayment;
    }

    public void setErMedicalRepayment(BigDecimal erMedicalRepayment) {
        this.erMedicalRepayment = erMedicalRepayment;
    }

    public BigDecimal getErUnemployementRepayment() {
        return erUnemployementRepayment;
    }

    public void setErUnemployementRepayment(BigDecimal erUnemployementRepayment) {
        this.erUnemployementRepayment = erUnemployementRepayment;
    }

    public BigDecimal getErWriRepayment() {
        return erWriRepayment;
    }

    public void setErWriRepayment(BigDecimal erWriRepayment) {
        this.erWriRepayment = erWriRepayment;
    }

    public BigDecimal getErMaternityRepayment() {
        return erMaternityRepayment;
    }

    public void setErMaternityRepayment(BigDecimal erMaternityRepayment) {
        this.erMaternityRepayment = erMaternityRepayment;
    }

    public BigDecimal getSumCompanyFiveSi() {
        return sumCompanyFiveSi;
    }

    public void setSumCompanyFiveSi(BigDecimal sumCompanyFiveSi) {
        this.sumCompanyFiveSi = sumCompanyFiveSi;
    }

    public BigDecimal getSumFiveSi() {
        return sumFiveSi;
    }

    public void setSumFiveSi(BigDecimal sumFiveSi) {
        this.sumFiveSi = sumFiveSi;
    }

    public BigDecimal getHeatingFee() {
        return heatingFee;
    }

    public void setHeatingFee(BigDecimal heatingFee) {
        this.heatingFee = heatingFee;
    }

    public BigDecimal getCompanyDisability() {
        return companyDisability;
    }

    public void setCompanyDisability(BigDecimal companyDisability) {
        this.companyDisability = companyDisability;
    }

    public BigDecimal getCompanySbOther() {
        return companySbOther;
    }

    public void setCompanySbOther(BigDecimal companySbOther) {
        this.companySbOther = companySbOther;
    }

    public BigDecimal getHeatingFeeRepayment() {
        return heatingFeeRepayment;
    }

    public void setHeatingFeeRepayment(BigDecimal heatingFeeRepayment) {
        this.heatingFeeRepayment = heatingFeeRepayment;
    }

    public BigDecimal getCdRepayment() {
        return cdRepayment;
    }

    public void setCdRepayment(BigDecimal cdRepayment) {
        this.cdRepayment = cdRepayment;
    }

    public BigDecimal getSumSi() {
        return sumSi;
    }

    public void setSumSi(BigDecimal sumSi) {
        this.sumSi = sumSi;
    }

    public BigDecimal getHousingFund() {
        return housingFund;
    }

    public void setHousingFund(BigDecimal housingFund) {
        this.housingFund = housingFund;
    }

    public BigDecimal getHousingFundPayment() {
        return housingFundPayment;
    }

    public void setHousingFundPayment(BigDecimal housingFundPayment) {
        this.housingFundPayment = housingFundPayment;
    }

    public BigDecimal getSumPersonHf() {
        return sumPersonHf;
    }

    public void setSumPersonHf(BigDecimal sumPersonHf) {
        this.sumPersonHf = sumPersonHf;
    }

    public BigDecimal getCompanyHousingFund() {
        return companyHousingFund;
    }

    public void setCompanyHousingFund(BigDecimal companyHousingFund) {
        this.companyHousingFund = companyHousingFund;
    }

    public BigDecimal getChfRepayment() {
        return chfRepayment;
    }

    public void setChfRepayment(BigDecimal chfRepayment) {
        this.chfRepayment = chfRepayment;
    }

    public BigDecimal getSumCompanyHf() {
        return sumCompanyHf;
    }

    public void setSumCompanyHf(BigDecimal sumCompanyHf) {
        this.sumCompanyHf = sumCompanyHf;
    }

    public BigDecimal getSumHf() {
        return sumHf;
    }

    public void setSumHf(BigDecimal sumHf) {
        this.sumHf = sumHf;
    }

    public BigDecimal getSumSiHf() {
        return sumSiHf;
    }

    public void setSumSiHf(BigDecimal sumSiHf) {
        this.sumSiHf = sumSiHf;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("idNo", getIdNo())
            .append("erName", getErName())
            .append("name", getName())
            .append("certificateNumber", getCertificateNumber())
            .append("socialBenefitsAddress", getSocialBenefitsAddress())
            .append("pensionBase", getPensionBase())
            .append("housingFundBase", getHousingFundBase())
            .append("pension", getPension())
            .append("medical", getMedical())
            .append("unemployment", getUnemployment())
            .append("pensionRepayment", getPensionRepayment())
            .append("medicalRepayment", getMedicalRepayment())
            .append("unemploymentRepayment", getUnemploymentRepayment())
            .append("sumPersonSb", getSumPersonSb())
            .append("companyPension", getCompanyPension())
            .append("companyMedical", getCompanyMedical())
            .append("companyUnemployment", getCompanyUnemployment())
            .append("companyWri", getCompanyWri())
            .append("companyMaternity", getCompanyMaternity())
            .append("erPensionRepayment", getErPensionRepayment())
            .append("erMedicalRepayment", getErMedicalRepayment())
            .append("erUnemployementRepayment", getErUnemployementRepayment())
            .append("erWriRepayment", getErWriRepayment())
            .append("erMaternityRepayment", getErMaternityRepayment())
            .append("sumCompanyFiveSi", getSumCompanyFiveSi())
            .append("sumFiveSi", getSumFiveSi())
            .append("heatingFee", getHeatingFee())
            .append("companyDisability", getCompanyDisability())
            .append("companySbOther", getCompanySbOther())
            .append("heatingFeeRepayment", getHeatingFeeRepayment())
            .append("cdRepayment", getCdRepayment())
            .append("sumSi", getSumSi())
            .append("housingFund", getHousingFund())
            .append("housingFundPayment", getHousingFundPayment())
            .append("companyHousingFund", getCompanyHousingFund())
            .append("chfRepayment", getChfRepayment())
            .append("sumHf", getSumHf())
            .append("duration", getDuration())
            .append("sumPersonHf", getSumPersonHf())
            .append("sumCompanyHf", getSumCompanyHf())
            .append("sumSiHf", getSumSiHf())
            .toString();
    }
}