package com.nnroad.payroll.domain.exportC;

import cn.hutool.core.util.NumberUtil;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * iit_report对象 iit_report
 *
 * @author HROne
 * @date 2021-01-15
 */
public class IITReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 员工类型 */
    @Excel(name = "用工类型")
    private String employmentNature;

    /** 个税缴纳地 */
    @Excel(name = "个税缴纳地")
    private String taxPaymentPlace;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String erName;

    /** 工号 */
    @Excel(name = "工号")
    private String idNo;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** *证件类型 */
    @Excel(name = "*证件类型")
    private String certificateType;

    /** *证件号码 */
    @Excel(name = "*证件号码")
    private String certificateNumber;

    /** *收入额 */
    @Excel(name = "*收入额", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal income;

    /** 免税所得 */
    @Excel(name = "免税所得", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal fsAllowance;

    /** 基本养老保险费 */
    @Excel(name = "基本养老保险费", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal pension;

    /** 基本医疗保险费 */
    @Excel(name = "基本医疗保险费", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal medical;

    /** 失业保险费 */
    @Excel(name = "失业保险费", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal unemployment;

    /** 住房公积金 */
    @Excel(name = "住房公积金", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal housingFund;

    /** 累计子女教育 */
    @Excel(name = "累计子女教育", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal accumulatedChildEducation;

    /** 累计继续教育 */
    @Excel(name = "累计继续教育", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal accumulatedContinuingEducation;

    /** 累计住房贷款利息 */
    @Excel(name = "累计住房贷款利息", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal accumulatedHli;

    /** 累计住房租金 */
    @Excel(name = "累计住房租金", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal accumulatedHousingRent;

    /** 累计赡养老人 */
    @Excel(name = "累计赡养老人", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal accumulatedSfte;

    /** 累计婴幼儿照护费用 */
    @Excel(name = "累计婴幼儿照护费用", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal accumulatedChildCare;

    /** 企业(职业)年金 */
    @Excel(name = "企业(职业)年金", cellType = Excel.ColumnType.NUMERIC)
    private String annuity;

    /** 商业健康保险 */
    @Excel(name = "商业健康保险", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal commercialHealthInsurance;

    /** 税延养老保险 */
    @Excel(name = "税延养老保险", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal tdpInsurance;

    /** 其他 */
    @Excel(name = "其他", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal otherIit;

    /** 准予扣除的捐赠额 */
    @Excel(name = "准予扣除的捐赠额", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal otherTaxFreeDeductions;

    /** 减免税额 */
    @Excel(name = "减免税额", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal taxSavings;

    /** 备注 */
    @Excel(name = "备注")
    private String remarkIit;

    /** 工资税合计 */
    @Excel(name = "工资税合计", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal sumPayrollTax;

    /** 年终奖应税合计 */
    @Excel(name = "年终奖应税合计", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal annualBonus;

    /** 年终奖税合计 */
    @Excel(name = "年终奖税合计", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal annualBonusLlt;

    /** 税后补（离职补偿金） */
    @Excel(name = "税后补（离职补偿金）", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal afterTaxReissue;

    /** 离职补偿金 */
    @Excel(name = "离职补偿金", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal indemnity;

    /** 离职补偿金税 */
    @Excel(name = "离职补偿金税", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal indemnityTax;

    /** 股权激励 */
    @Excel(name = "股权激励", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal shareIncentives;

    /** 股权激励税 */
    @Excel(name = "股权激励税", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal shareIncentivesTax;

    /** 个税合计 */
    @Excel(name = "个税合计", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal sumIit;

    /** 期间 */
    @Excel(name = "当前税务月", cellType = Excel.ColumnType.NUMERIC)
    private String duration;

    /** 当前扣缴义务人 */
    @Excel(name = "当前扣缴义务人")
    private String currentTaxAgent;

    public void setEmploymentNature(String employmentNature)
    {
        this.employmentNature = employmentNature;
    }
    public String getEmploymentNature()
    {
        return employmentNature;
    }

    public void setTaxPaymentPlace(String taxPaymentPlace)
    {
        this.taxPaymentPlace = taxPaymentPlace;
    }
    public String getTaxPaymentPlace()
    {
        return taxPaymentPlace;
    }

    public void setErName(String erName) { this.erName = erName; }
    public String getErName() { return erName; }

    public void setIdNo(String idNo)
    {
        this.idNo = idNo;
    }
    public String getIdNo()
    {
        return idNo;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }

    public void setCertificateType(String certificateType)
    {
        this.certificateType = certificateType;
    }
    public String getCertificateType()
    {
        return certificateType;
    }

    public void setCertificateNumber(String certificateNumber)
    {
        this.certificateNumber = certificateNumber;
    }
    public String getCertificateNumber()
    {
        return certificateNumber;
    }

    public void setIncome(BigDecimal income)
    {
        this.income = income;
    }
    public BigDecimal getIncome(){
        return NumberUtil.round(income,2);
    }

    public void setFsAllowance(BigDecimal fsAllowance)
    {
        this.fsAllowance = fsAllowance;
    }
    public BigDecimal getFsAllowance()
    {
        return fsAllowance;
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

    public void setAccumulatedChildEducation(BigDecimal accumulatedChildEducation) { this.accumulatedChildEducation = accumulatedChildEducation; }
    public BigDecimal getAccumulatedChildEducation()
    {
        return accumulatedChildEducation;
    }

    public void setAccumulatedContinuingEducation(BigDecimal accumulatedContinuingEducation) { this.accumulatedContinuingEducation = accumulatedContinuingEducation; }
    public BigDecimal getAccumulatedContinuingEducation()
    {
        return accumulatedContinuingEducation;
    }

    public void setAccumulatedHli(BigDecimal accumulatedHli)
    {
        this.accumulatedHli = accumulatedHli;
    }
    public BigDecimal getAccumulatedHli()
    {
        return accumulatedHli;
    }

    public void setAccumulatedHousingRent(BigDecimal accumulatedHousingRent) { this.accumulatedHousingRent = accumulatedHousingRent; }
    public BigDecimal getAccumulatedHousingRent()
    {
        return accumulatedHousingRent;
    }

    public void setAccumulatedSfte(BigDecimal accumulatedSfte)
    {
        this.accumulatedSfte = accumulatedSfte;
    }
    public BigDecimal getAccumulatedSfte()
    {
        return accumulatedSfte;
    }

    public BigDecimal getAccumulatedChildCare() {
        return accumulatedChildCare;
    }
    public void setAccumulatedChildCare(BigDecimal accumulatedChildCare) {
        this.accumulatedChildCare = accumulatedChildCare;
    }

    public void setAnnuity(String annuity)
    {
        this.annuity = annuity;
    }
    public String getAnnuity()
    {
        return annuity;
    }

    public void setCommercialHealthInsurance(BigDecimal commercialHealthInsurance) { this.commercialHealthInsurance = commercialHealthInsurance; }
    public BigDecimal getCommercialHealthInsurance()
    {
        return commercialHealthInsurance;
    }

    public void setTdpInsurance(BigDecimal tdpInsurance)
    {
        this.tdpInsurance = tdpInsurance;
    }
    public BigDecimal getTdpInsurance()
    {
        return tdpInsurance;
    }

    public void setOtherIit(BigDecimal otherIit)
    {
        this.otherIit = otherIit;
    }
    public BigDecimal getOtherIit()
    {
        return otherIit;
    }

    public void setOtherTaxFreeDeductions(BigDecimal otherTaxFreeDeductions) { this.otherTaxFreeDeductions = otherTaxFreeDeductions; }
    public BigDecimal getOtherTaxFreeDeductions()
    {
        return otherTaxFreeDeductions;
    }

    public void setTaxSavings(BigDecimal taxSavings)
    {
        this.taxSavings = taxSavings;
    }
    public BigDecimal getTaxSavings()
    {
        return taxSavings;
    }

    public void setRemarkIit(String remarkIit)
    {
        this.remarkIit = remarkIit;
    }
    public String getRemarkIit()
    {
        return remarkIit;
    }

    public BigDecimal getSumPayrollTax() { return sumPayrollTax; }
    public void setSumPayrollTax(BigDecimal sumPayrollTax) { this.sumPayrollTax = sumPayrollTax; }

    public void setAnnualBonus(BigDecimal annualBonus) { this.annualBonus = annualBonus; }
    public BigDecimal getAnnualBonus()
    {
        return annualBonus;
    }

    public void setAnnualBonusLlt(BigDecimal annualBonusLlt)
    {
        this.annualBonusLlt = annualBonusLlt;
    }
    public BigDecimal getAnnualBonusLlt()
    {
        return annualBonusLlt;
    }

    public void setAfterTaxReissue(BigDecimal afterTaxReissue)
    {
        this.afterTaxReissue = afterTaxReissue;
    }
    public BigDecimal getAfterTaxReissue()
    {
        return afterTaxReissue;
    }

    public BigDecimal getIndemnity() {
        return indemnity;
    }
    public void setIndemnity(BigDecimal indemnity) {
        this.indemnity = indemnity;
    }

    public BigDecimal getIndemnityTax() {
        return indemnityTax;
    }
    public void setIndemnityTax(BigDecimal indemnityTax) {
        this.indemnityTax = indemnityTax;
    }

    public BigDecimal getShareIncentives() {
        return shareIncentives;
    }
    public void setShareIncentives(BigDecimal shareIncentives) {
        this.shareIncentives = shareIncentives;
    }

    public BigDecimal getShareIncentivesTax() {
        return shareIncentivesTax;
    }
    public void setShareIncentivesTax(BigDecimal shareIncentivesTax) {
        this.shareIncentivesTax = shareIncentivesTax;
    }

    public void setSumIit(BigDecimal sumIit) { this.sumIit = sumIit; }
    public BigDecimal getSumIit() { return sumIit; }

    public void setDuration(String duration)
    {
        this.duration = duration;
    }
    public String getDuration()
    {
        return duration;
    }

    public void setCurrentTaxAgent(String currentTaxAgent)
    {
        this.currentTaxAgent = currentTaxAgent;
    }
    public String getCurrentTaxAgent()
    {
        return currentTaxAgent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("employmentNature", getEmploymentNature())
                .append("taxPaymentPlace", getTaxPaymentPlace())
                .append("erName", getErName())
                .append("idNo", getIdNo())
                .append("name", getName())
                .append("certificateType", getCertificateType())
                .append("certificateNumber", getCertificateNumber())
                .append("income", getIncome())
                .append("fsAllowance", getFsAllowance())
                .append("pension", getPension())
                .append("medical", getMedical())
                .append("unemployment", getUnemployment())
                .append("housingFund", getHousingFund())
                .append("accumulatedChildEducation", getAccumulatedChildEducation())
                .append("accumulatedContinuingEducation", getAccumulatedContinuingEducation())
                .append("accumulatedHli", getAccumulatedHli())
                .append("accumulatedHousingRent", getAccumulatedHousingRent())
                .append("accumulatedSfte", getAccumulatedSfte())
                .append("accumulatedChildCare", getAccumulatedChildCare())
                .append("annuity", getAnnuity())
                .append("commercialHealthInsurance", getCommercialHealthInsurance())
                .append("tdpInsurance", getTdpInsurance())
                .append("otherIit", getOtherIit())
                .append("otherTaxFreeDeductions", getOtherTaxFreeDeductions())
                .append("taxSavings", getTaxSavings())
                .append("remarkIit", getRemarkIit())
                .append("sumPayrollTax", getSumPayrollTax())
                .append("annualBonus", getAnnualBonus())
                .append("annualBonusLlt", getAnnualBonusLlt())
                .append("afterTaxReissue", getAfterTaxReissue())
                .append("indemnity", getIndemnity())
                .append("indemnityTax", getIndemnityTax())
                .append("shareIncentives", getShareIncentives())
                .append("shareIncentivesTax", getShareIncentivesTax())
                .append("sumIit", getSumIit())
                .append("duration", getDuration())
                .append("currentTaxAgent", getCurrentTaxAgent())
                .toString();
    }
}
