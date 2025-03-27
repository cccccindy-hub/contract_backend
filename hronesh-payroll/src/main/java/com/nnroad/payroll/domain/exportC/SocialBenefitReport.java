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
public class SocialBenefitReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 周期 */
    @Excel(name = "期间")
    private String duration;

    /** id no */
    @Excel(name = "员工编号")
    private String idNo;

    /** Employee  Name */
    @Excel(name = "公司名称")
    private String erName;

    /** name */
    @Excel(name = "员工姓名")
    private String name;

    /** certificate number */
    @Excel(name = "证件号码")
    private String certificateNumber;

    /** social benefits address */
    @Excel(name = "社保城市")
    private String socialBenefitsAddress;

    /** Pension base */
    @Excel(name = "社保基数社保基数")
    private BigDecimal pensionBase;

    /** Housing Fund Base */
    @Excel(name = "公积金基数")
    private BigDecimal housingFundBase;

    /** Pension */
    @Excel(name = "养老保险-员工")
    private BigDecimal pension;

    /** Medical */
    @Excel(name = "医疗保险-员工")
    private BigDecimal medical;

    /** Unemployment */
    @Excel(name = "失业保险-员工")
    private BigDecimal unemployment;

    /** Pension repayment */
    @Excel(name = "养老保险补缴-员工")
    private BigDecimal pensionRepayment;

    /** Medical repayment */
    @Excel(name = "医疗保险补缴-员工")
    private BigDecimal medicalRepayment;

    /** Unemployment repayment */
    @Excel(name = "失业保险补缴-员工")
    private BigDecimal unemploymentRepayment;

    /** Company Pension */
    @Excel(name = "养老保险-公司")
    private BigDecimal companyPension;

    /** Company Medical */
    @Excel(name = "医疗保险-公司")
    private BigDecimal companyMedical;

    /** Company Unemployment */
    @Excel(name = "失业保险-公司")
    private BigDecimal companyUnemployment;

    /** Company Work-Related-Injury */
    @Excel(name = "工伤保险-公司")
    private BigDecimal companyWri;

    /** Company Maternity */
    @Excel(name = "生育保险-公司")
    private BigDecimal companyMaternity;

    /**  */
    @Excel(name = "养老保险补缴-公司")
    private BigDecimal erPensionRepayment;

    /**  */
    @Excel(name = "医疗保险补缴-公司")
    private BigDecimal erMedicalRepayment;

    /**  */
    @Excel(name = "失业保险补缴-公司")
    private BigDecimal erUnemployementRepayment;

    /**  */
    @Excel(name = "工伤保险补缴-公司")
    private BigDecimal erWriRepayment;

    /**  */
    @Excel(name = "生育保险补缴-公司")
    private BigDecimal erMaternityRepayment;

    /** Company Social Benefits repayment */
    @Excel(name = "公司社保补缴")
    private BigDecimal csbRepayment;

    /**  */
    @Excel(name = "五险合计")
    private BigDecimal sumFiveSi;

    /**  */
    @Excel(name = "采暖费-公司")
    private BigDecimal heatingFee;

    /** Company Disability */
    @Excel(name = "残保金-公司")
    private BigDecimal companyDisability;

    /** Company Disability repayment */
    @Excel(name = "残保金补缴-公司")
    private BigDecimal cdRepayment;

    /**  */
    @Excel(name = "社保总计")
    private BigDecimal sumSi;

    /** Housing Fund */
    @Excel(name = "住房公积金-员工")
    private BigDecimal housingFund;

    /** Supplementary Housing Fund (tax exempt) */
    @Excel(name = "补充住房公积金-员工")
    private BigDecimal steHousingFund;

    /** Housing fund repayment */
    @Excel(name = "住房公积金补缴-员工")
    private BigDecimal housingFundPayment;

    /** Company Housing Fund */
    @Excel(name = "住房公积金-公司")
    private BigDecimal companyHousingFund;

    /** Company Supplementary Housing Fund */
    @Excel(name = "补充住房公积金-公司")
    private BigDecimal companyShf;

    /** Company Housing Fund repayment */
    @Excel(name = "住房公积金补缴-公司")
    private BigDecimal chfRepayment;

    /**  */
    @Excel(name = "本月公积金合计")
    private BigDecimal sumHf;

    /** 供应商 */
    @Excel(name = "供应商")
    private String vendor;

    public void setIdNo(String idNo)
    {
        this.idNo = idNo;
    }

    public String getIdNo()
    {
        return idNo;
    }
    public void setErName(String erName)
    {
        this.erName = erName;
    }

    public String getErName()
    {
        return erName;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setCertificateNumber(String certificateNumber)
    {
        this.certificateNumber = certificateNumber;
    }

    public String getCertificateNumber()
    {
        return certificateNumber;
    }
    public void setSocialBenefitsAddress(String socialBenefitsAddress)
    {
        this.socialBenefitsAddress = socialBenefitsAddress;
    }

    public String getSocialBenefitsAddress()
    {
        return socialBenefitsAddress;
    }
    public void setPensionBase(BigDecimal pensionBase)
    {
        this.pensionBase = pensionBase;
    }

    public BigDecimal getPensionBase()
    {
        return pensionBase;
    }
    public void setHousingFundBase(BigDecimal housingFundBase)
    {
        this.housingFundBase = housingFundBase;
    }

    public BigDecimal getHousingFundBase()
    {
        return housingFundBase;
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
    public void setPensionRepayment(BigDecimal pensionRepayment)
    {
        this.pensionRepayment = pensionRepayment;
    }

    public BigDecimal getPensionRepayment()
    {
        return pensionRepayment;
    }
    public void setMedicalRepayment(BigDecimal medicalRepayment)
    {
        this.medicalRepayment = medicalRepayment;
    }

    public BigDecimal getMedicalRepayment()
    {
        return medicalRepayment;
    }
    public void setUnemploymentRepayment(BigDecimal unemploymentRepayment)
    {
        this.unemploymentRepayment = unemploymentRepayment;
    }

    public BigDecimal getUnemploymentRepayment()
    {
        return unemploymentRepayment;
    }
    public void setCompanyPension(BigDecimal companyPension)
    {
        this.companyPension = companyPension;
    }

    public BigDecimal getCompanyPension()
    {
        return companyPension;
    }
    public void setCompanyMedical(BigDecimal companyMedical)
    {
        this.companyMedical = companyMedical;
    }

    public BigDecimal getCompanyMedical()
    {
        return companyMedical;
    }
    public void setCompanyUnemployment(BigDecimal companyUnemployment)
    {
        this.companyUnemployment = companyUnemployment;
    }

    public BigDecimal getCompanyUnemployment()
    {
        return companyUnemployment;
    }
    public void setCompanyWri(BigDecimal companyWri)
    {
        this.companyWri = companyWri;
    }

    public BigDecimal getCompanyWri()
    {
        return companyWri;
    }
    public void setCompanyMaternity(BigDecimal companyMaternity)
    {
        this.companyMaternity = companyMaternity;
    }

    public BigDecimal getCompanyMaternity()
    {
        return companyMaternity;
    }
    public void setErPensionRepayment(BigDecimal erPensionRepayment)
    {
        this.erPensionRepayment = erPensionRepayment;
    }

    public BigDecimal getErPensionRepayment()
    {
        return erPensionRepayment;
    }
    public void setErMedicalRepayment(BigDecimal erMedicalRepayment)
    {
        this.erMedicalRepayment = erMedicalRepayment;
    }

    public BigDecimal getErMedicalRepayment()
    {
        return erMedicalRepayment;
    }
    public void setErUnemployementRepayment(BigDecimal erUnemployementRepayment)
    {
        this.erUnemployementRepayment = erUnemployementRepayment;
    }

    public BigDecimal getErUnemployementRepayment()
    {
        return erUnemployementRepayment;
    }
    public void setErWriRepayment(BigDecimal erWriRepayment)
    {
        this.erWriRepayment = erWriRepayment;
    }

    public BigDecimal getErWriRepayment()
    {
        return erWriRepayment;
    }
    public void setErMaternityRepayment(BigDecimal erMaternityRepayment)
    {
        this.erMaternityRepayment = erMaternityRepayment;
    }

    public BigDecimal getErMaternityRepayment()
    {
        return erMaternityRepayment;
    }
    public void setCsbRepayment(BigDecimal csbRepayment)
    {
        this.csbRepayment = csbRepayment;
    }

    public BigDecimal getCsbRepayment()
    {
        return csbRepayment;
    }
    public void setSumFiveSi(BigDecimal sumFiveSi)
    {
        this.sumFiveSi = sumFiveSi;
    }

    public BigDecimal getSumFiveSi()
    {
        return sumFiveSi;
    }
    public void setHeatingFee(BigDecimal heatingFee)
    {
        this.heatingFee = heatingFee;
    }

    public BigDecimal getHeatingFee()
    {
        return heatingFee;
    }
    public void setCompanyDisability(BigDecimal companyDisability)
    {
        this.companyDisability = companyDisability;
    }

    public BigDecimal getCompanyDisability()
    {
        return companyDisability;
    }
    public void setCdRepayment(BigDecimal cdRepayment)
    {
        this.cdRepayment = cdRepayment;
    }

    public BigDecimal getCdRepayment()
    {
        return cdRepayment;
    }
    public void setSumSi(BigDecimal sumSi)
    {
        this.sumSi = sumSi;
    }

    public BigDecimal getSumSi()
    {
        return sumSi;
    }
    public void setHousingFund(BigDecimal housingFund)
    {
        this.housingFund = housingFund;
    }

    public BigDecimal getHousingFund()
    {
        return housingFund;
    }
    public void setSteHousingFund(BigDecimal steHousingFund)
    {
        this.steHousingFund = steHousingFund;
    }

    public BigDecimal getSteHousingFund()
    {
        return steHousingFund;
    }
    public void setHousingFundPayment(BigDecimal housingFundPayment)
    {
        this.housingFundPayment = housingFundPayment;
    }

    public BigDecimal getHousingFundPayment()
    {
        return housingFundPayment;
    }
    public void setCompanyHousingFund(BigDecimal companyHousingFund)
    {
        this.companyHousingFund = companyHousingFund;
    }

    public BigDecimal getCompanyHousingFund()
    {
        return companyHousingFund;
    }
    public void setCompanyShf(BigDecimal companyShf)
    {
        this.companyShf = companyShf;
    }

    public BigDecimal getCompanyShf()
    {
        return companyShf;
    }
    public void setChfRepayment(BigDecimal chfRepayment)
    {
        this.chfRepayment = chfRepayment;
    }

    public BigDecimal getChfRepayment()
    {
        return chfRepayment;
    }
    public void setSumHf(BigDecimal sumHf)
    {
        this.sumHf = sumHf;
    }

    public BigDecimal getSumHf()
    {
        return sumHf;
    }

    public String getVendor() {
        return vendor;
    }
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setDuration(String duration)
    {
        this.duration = duration;
    }
    public String getDuration()
    {
        return duration;
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
                .append("csbRepayment", getCsbRepayment())
                .append("sumFiveSi", getSumFiveSi())
                .append("heatingFee", getHeatingFee())
                .append("companyDisability", getCompanyDisability())
                .append("cdRepayment", getCdRepayment())
                .append("sumSi", getSumSi())
                .append("housingFund", getHousingFund())
                .append("steHousingFund", getSteHousingFund())
                .append("housingFundPayment", getHousingFundPayment())
                .append("companyHousingFund", getCompanyHousingFund())
                .append("companyShf", getCompanyShf())
                .append("chfRepayment", getChfRepayment())
                .append("sumHf", getSumHf())
                .append("vendor", getVendor())
                .append("duration", getDuration())
                .toString();
    }
}