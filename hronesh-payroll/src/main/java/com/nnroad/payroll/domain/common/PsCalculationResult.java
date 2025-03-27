package com.nnroad.payroll.domain.common;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ps_calculation_result对象 ps_calculation_result
 * 
 * @author Hrone
 * @date 2021-01-17
 */
public class PsCalculationResult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 期间 */
    @Excel(name = "期间")
    private String duration;

    /** 客户简称 */
    @Excel(name = "客户简称")
    private String erName;

    /** 工号 */
    @Excel(name = "工号")
    private String idNo;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String certificateNumber;

    /** 社保城市 */
    @Excel(name = "社保城市")
    private String socialBenefitsAddress;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 基本工资 */
    @Excel(name = "基本工资")
    private BigDecimal basicSalary;

    /** 浮动工资 */
    @Excel(name = "浮动工资")
    private BigDecimal floatingSalary;

    /** 月奖 */
    @Excel(name = "月奖")
    private BigDecimal monthlyAward;

    /** 季奖 */
    @Excel(name = "季奖")
    private BigDecimal quarterAward;

    /** 加班工资150 */
    @Excel(name = "加班工资150")
    private BigDecimal overtimeAward150;

    /** 加班工资200 */
    @Excel(name = "加班工资200")
    private BigDecimal overtimeAward200;

    /** 加班工资300 */
    @Excel(name = "加班工资300")
    private BigDecimal overtimeAward300;

    /** 入离职缺勤扣 */
    @Excel(name = "入离职缺勤扣")
    private BigDecimal ealaDeduction;

    /** 扣事假 */
    @Excel(name = "扣事假")
    private BigDecimal alDeduction;

    /** 扣旷工 */
    @Excel(name = "扣旷工")
    private BigDecimal absenteeismDeduction;

    /** 扣病假 */
    @Excel(name = "扣病假")
    private BigDecimal slDeduction;

    /** 交通费 */
    @Excel(name = "交通费")
    private BigDecimal transportationPayment;

    /** 通讯费 */
    @Excel(name = "通讯费")
    private BigDecimal communicationPayment;

    /** 餐食津贴 */
    @Excel(name = "餐食津贴")
    private BigDecimal mealAllowance;

    /** 生日礼金 */
    @Excel(name = "生日礼金")
    private BigDecimal birthdayGift;

    /** 其他津贴 */
    @Excel(name = "其他津贴")
    private BigDecimal otherBenefits;

    /** 中班费 */
    @Excel(name = "中班费")
    private BigDecimal middleShiftPayment;

    /** 夜班费 */
    @Excel(name = "夜班费")
    private BigDecimal nightShiftPayment;

    /** 高温费 */
    @Excel(name = "高温费")
    private BigDecimal highTemperaturePayment;

    /** 高温作业费 */
    @Excel(name = "高温作业费")
    private BigDecimal hotWorkingPayment;

    /** 有毒有害作业费 */
    @Excel(name = "有毒有害作业费")
    private BigDecimal tahwPayment;

    /** 补发 */
    @Excel(name = "补发")
    private BigDecimal reissue;

    /** 补扣 */
    @Excel(name = "补扣")
    private BigDecimal wageDeduction;

    /** 缴养老 */
    @Excel(name = "缴养老")
    private BigDecimal pension;

    /** 缴医疗 */
    @Excel(name = "缴医疗")
    private BigDecimal medical;

    /** 缴失业 */
    @Excel(name = "缴失业")
    private BigDecimal unemployment;

    /** 缴公积金 */
    @Excel(name = "缴公积金")
    private BigDecimal housingFund;

    /** 缴补充公积金（免税） */
    @Excel(name = "缴补充公积金（免税）")
    private BigDecimal steHousingFund;

    /** 社保调整 */
    @Excel(name = "社保调整")
    private BigDecimal socialBenefitsAdjustment;

    /** 公积金调整 */
    @Excel(name = "公积金调整")
    private BigDecimal housingFundAdjustment;

    /** 年金 */
    @Excel(name = "年金")
    private BigDecimal annuity;

    /** 工会费 */
    @Excel(name = "工会费")
    private BigDecimal unionFee;

    /** 计税不发 */
    @Excel(name = "计税不发")
    private BigDecimal taxCalculation;

    /** 当月税前收入额 */
    @Excel(name = "当月税前收入额")
    private BigDecimal preTaxIncome;

    /** 当月基本扣除 */
    @Excel(name = "当月基本扣除")
    private BigDecimal basicDeduction;

    /** 累计税前收入-员工 */
    @Excel(name = "累计税前收入-员工")
    private BigDecimal accumulatedIpti;

    /** 累计个人社保公积金 */
    @Excel(name = "累计个人社保公积金")
    private BigDecimal accumulatedIsiahf;

    /** 累计基本扣除 */
    @Excel(name = "累计基本扣除")
    private BigDecimal accumulatedBd;

    /** 累计子女教育 */
    @Excel(name = "累计子女教育")
    private BigDecimal accumulatedChildEducation;

    /** 累计继续教育 */
    @Excel(name = "累计继续教育")
    private BigDecimal accumulatedContinuingEducation;

    /** 累计住房贷款利息 */
    @Excel(name = "累计住房贷款利息")
    private BigDecimal accumulatedHli;

    /** 累计住房租金 */
    @Excel(name = "累计住房租金")
    private BigDecimal accumulatedHousingRent;

    /** 累计赡养老人 */
    @Excel(name = "累计赡养老人")
    private BigDecimal accumulatedSfte;

    /** 累计其他税前扣除 */
    @Excel(name = "累计其他税前扣除")
    private BigDecimal accumulatedOther;

    /** 累计免税扣除合计(不含社保公积金) */
    @Excel(name = "累计免税扣除合计(不含社保公积金）")
    private BigDecimal accumulatedTftd;

    /** 累计应税总额 */
    @Excel(name = "累计应税总额")
    private BigDecimal accumulatedTotalCost;

    /** 累计个税（个人） */
    @Excel(name = "累计个税（个人）")
    private BigDecimal accumulatedIl;

    /** 当月个税（个人） */
    @Excel(name = "当月个税（个人）")
    private BigDecimal individualLlt;

    /** 公司负税项 */
    @Excel(name = "公司负税项")
    private BigDecimal companyTax;

    /** 当月公司负税合计 */
    @Excel(name = "当月公司负税合计")
    private BigDecimal companyTotalTax;

    /** 累计税后计税收入 */
    @Excel(name = "累计税后计税收入")
    private BigDecimal accumulatedAti;

    /** 累计税前收入 */
    @Excel(name = "累计税前收入")
    private BigDecimal accumulatedBti;

    /** 导算当月应发工资 */
    @Excel(name = "导算当月应发工资")
    private BigDecimal theoreticalIncome;

    /** 累计个税（公司+个人） */
    @Excel(name = "累计个税（公司+个人）")
    private BigDecimal accumulatedLltCai;

    /** 当月个税（公司） */
    @Excel(name = "当月个税（公司）")
    private BigDecimal companyLlt;

    /** 独生子女费 */
    @Excel(name = "独生子女费")
    private BigDecimal onlyChildAllowance;

    /** 缴补充公积金（不免税） */
    @Excel(name = "缴补充公积金（不免税）")
    private BigDecimal snteHousingFund;

    /** 报销款 */
    @Excel(name = "报销款")
    private BigDecimal expense;

    /** 报销款25% */
    @Excel(name = "报销款25%")
    private BigDecimal expense25;

    /** 外籍生活津贴 */
    @Excel(name = "外籍生活津贴")
    private BigDecimal fsAllowance;

    /** 税后补 */
    @Excel(name = "税后补")
    private BigDecimal afterTaxReissue;

    /** 税后扣 */
    @Excel(name = "税后扣")
    private BigDecimal afterTaxDeduction;

    /** 13薪/年终奖 */
    @Excel(name = "13薪/年终奖")
    private BigDecimal annualBonus;

    /** 13薪/年终奖税 */
    @Excel(name = "13薪/年终奖税")
    private BigDecimal annualBonusLlt;

    /** 应发 */
    @Excel(name = "应发")
    private BigDecimal income;

    /** 应扣 */
    @Excel(name = "应扣")
    private BigDecimal cost;

    /** 实发 */
    @Excel(name = "实发")
    private BigDecimal netIncome;

    /** 公司养老 */
    @Excel(name = "公司养老")
    private BigDecimal companyPension;

    /** 公司医疗 */
    @Excel(name = "公司医疗")
    private BigDecimal companyMedical;

    /** 公司失业 */
    @Excel(name = "公司失业")
    private BigDecimal companyUnemployment;

    /** 生育 */
    @Excel(name = "生育")
    private BigDecimal companyMaternity;

    /** 工伤 */
    @Excel(name = "工伤")
    private BigDecimal companyWri;

    /** 残保金 */
    @Excel(name = "残保金")
    private BigDecimal companyDisability;

    /** 公积金 */
    @Excel(name = "公积金")
    private BigDecimal companyHousingFund;

    /** 补充公积金（免税） */
    @Excel(name = "补充公积金（免税）")
    private BigDecimal companyShf;

    /** 累计已扣工资税 */
    @Excel(name = "累计已扣工资税")
    private BigDecimal accumulatedPayrollTax;

    /** 雇主责任险 */
    @Excel(name = "雇主责任险")
    private BigDecimal employerLiability;

    /** 押金 */
    @Excel(name = "押金")
    private BigDecimal deposit;

    /** 服务费 */
    @Excel(name = "服务费")
    private BigDecimal serviceFee;

    /** 增值税 */
    @Excel(name = "增值税")
    private BigDecimal valueAddedTax;

    /** 其他奖金 */
    @Excel(name = "其他奖金")
    private BigDecimal otherBonus;

    /** 税前调整 */
    @Excel(name = "税前调整")
    private BigDecimal pretaxAdjustment;

    /** 离职补偿金/赔偿金 */
    @Excel(name = "离职补偿金/赔偿金")
    private BigDecimal indemnity;

    /** 离职补偿金税/赔偿金税 */
    @Excel(name = "离职补偿金税/赔偿金税")
    private BigDecimal indemnityTax;

    /** 股权激励 */
    @Excel(name = "股权激励")
    private BigDecimal shareIncentives;

    /** 股权激励税 */
    @Excel(name = "股权激励税")
    private BigDecimal shareIncentivesTax;

    /** 服务费税 */
    @Excel(name = "服务费税")
    private BigDecimal serviceFeeTax;

    /** 出差补贴 */
    @Excel(name = "出差补贴")
    private BigDecimal travelAllowance;

    /** 累计婴幼儿照护费用 */
    @Excel(name = "累计婴幼儿照护费用")
    private BigDecimal accumulatedChildCare;

    /** 客户编号 */
    @Excel(name = "客户编号")
    private String clientCode;

    /** 入职日期 */
    @Excel(name = "入职日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date entryDate;


    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    private String groupIds;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDuration(String duration) 
    {
        this.duration = duration;
    }

    public String getDuration() 
    {
        return duration;
    }
    public void setIdNo(String idNo) 
    {
        this.idNo = idNo;
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
    public void setBasicSalary(BigDecimal basicSalary) 
    {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getBasicSalary() 
    {
        return basicSalary;
    }
    public void setFloatingSalary(BigDecimal floatingSalary) 
    {
        this.floatingSalary = floatingSalary;
    }

    public BigDecimal getFloatingSalary() 
    {
        return floatingSalary;
    }
    public void setMonthlyAward(BigDecimal monthlyAward) 
    {
        this.monthlyAward = monthlyAward;
    }

    public BigDecimal getMonthlyAward() 
    {
        return monthlyAward;
    }
    public void setQuarterAward(BigDecimal quarterAward) 
    {
        this.quarterAward = quarterAward;
    }

    public BigDecimal getQuarterAward() 
    {
        return quarterAward;
    }
    public void setOvertimeAward150(BigDecimal overtimeAward150) 
    {
        this.overtimeAward150 = overtimeAward150;
    }

    public BigDecimal getOvertimeAward150() 
    {
        return overtimeAward150;
    }
    public void setOvertimeAward200(BigDecimal overtimeAward200) 
    {
        this.overtimeAward200 = overtimeAward200;
    }

    public BigDecimal getOvertimeAward200() 
    {
        return overtimeAward200;
    }
    public void setOvertimeAward300(BigDecimal overtimeAward300) 
    {
        this.overtimeAward300 = overtimeAward300;
    }

    public BigDecimal getOvertimeAward300() 
    {
        return overtimeAward300;
    }
    public void setEalaDeduction(BigDecimal ealaDeduction) 
    {
        this.ealaDeduction = ealaDeduction;
    }

    public BigDecimal getEalaDeduction() 
    {
        return ealaDeduction;
    }
    public void setAlDeduction(BigDecimal alDeduction) 
    {
        this.alDeduction = alDeduction;
    }

    public BigDecimal getAlDeduction() 
    {
        return alDeduction;
    }
    public void setAbsenteeismDeduction(BigDecimal absenteeismDeduction) {
        this.absenteeismDeduction = absenteeismDeduction;
    }

    public BigDecimal getAbsenteeismDeduction() 
    {
        return absenteeismDeduction;
    }
    public void setSlDeduction(BigDecimal slDeduction) 
    {
        this.slDeduction = slDeduction;
    }

    public BigDecimal getSlDeduction() 
    {
        return slDeduction;
    }
    public void setTransportationPayment(BigDecimal transportationPayment) {
        this.transportationPayment = transportationPayment;
    }

    public BigDecimal getTransportationPayment() 
    {
        return transportationPayment;
    }
    public void setCommunicationPayment(BigDecimal communicationPayment) {
        this.communicationPayment = communicationPayment;
    }

    public BigDecimal getCommunicationPayment() 
    {
        return communicationPayment;
    }
    public void setOtherBenefits(BigDecimal otherBenefits) 
    {
        this.otherBenefits = otherBenefits;
    }

    public BigDecimal getOtherBenefits() 
    {
        return otherBenefits;
    }
    public void setMiddleShiftPayment(BigDecimal middleShiftPayment) 
    {
        this.middleShiftPayment = middleShiftPayment;
    }

    public BigDecimal getMiddleShiftPayment() 
    {
        return middleShiftPayment;
    }
    public void setNightShiftPayment(BigDecimal nightShiftPayment) 
    {
        this.nightShiftPayment = nightShiftPayment;
    }

    public BigDecimal getNightShiftPayment() 
    {
        return nightShiftPayment;
    }
    public void setHighTemperaturePayment(BigDecimal highTemperaturePayment) {
        this.highTemperaturePayment = highTemperaturePayment;
    }

    public BigDecimal getHighTemperaturePayment() 
    {
        return highTemperaturePayment;
    }
    public void setHotWorkingPayment(BigDecimal hotWorkingPayment) 
    {
        this.hotWorkingPayment = hotWorkingPayment;
    }

    public BigDecimal getHotWorkingPayment() 
    {
        return hotWorkingPayment;
    }
    public void setTahwPayment(BigDecimal tahwPayment) 
    {
        this.tahwPayment = tahwPayment;
    }

    public BigDecimal getTahwPayment() 
    {
        return tahwPayment;
    }
    public void setReissue(BigDecimal reissue) 
    {
        this.reissue = reissue;
    }

    public BigDecimal getReissue() 
    {
        return reissue;
    }
    public void setWageDeduction(BigDecimal wageDeduction) 
    {
        this.wageDeduction = wageDeduction;
    }

    public BigDecimal getWageDeduction() 
    {
        return wageDeduction;
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
    public void setSteHousingFund(BigDecimal steHousingFund) 
    {
        this.steHousingFund = steHousingFund;
    }

    public BigDecimal getSteHousingFund() 
    {
        return steHousingFund;
    }
    public void setSocialBenefitsAdjustment(BigDecimal socialBenefitsAdjustment) {
        this.socialBenefitsAdjustment = socialBenefitsAdjustment;
    }

    public BigDecimal getSocialBenefitsAdjustment() 
    {
        return socialBenefitsAdjustment;
    }
    public void setHousingFundAdjustment(BigDecimal housingFundAdjustment) {
        this.housingFundAdjustment = housingFundAdjustment;
    }

    public BigDecimal getHousingFundAdjustment() 
    {
        return housingFundAdjustment;
    }
    public void setAnnuity(BigDecimal annuity) 
    {
        this.annuity = annuity;
    }

    public BigDecimal getAnnuity() 
    {
        return annuity;
    }
    public void setUnionFee(BigDecimal unionFee) 
    {
        this.unionFee = unionFee;
    }

    public BigDecimal getUnionFee() 
    {
        return unionFee;
    }
    public void setTaxCalculation(BigDecimal taxCalculation) 
    {
        this.taxCalculation = taxCalculation;
    }

    public BigDecimal getTaxCalculation() 
    {
        return taxCalculation;
    }
    public void setPreTaxIncome(BigDecimal preTaxIncome) 
    {
        this.preTaxIncome = preTaxIncome;
    }

    public BigDecimal getPreTaxIncome() 
    {
        return preTaxIncome;
    }
    public void setBasicDeduction(BigDecimal basicDeduction) 
    {
        this.basicDeduction = basicDeduction;
    }

    public BigDecimal getBasicDeduction() 
    {
        return basicDeduction;
    }
    public void setAccumulatedIpti(BigDecimal accumulatedIpti) 
    {
        this.accumulatedIpti = accumulatedIpti;
    }

    public BigDecimal getAccumulatedIpti() 
    {
        return accumulatedIpti;
    }
    public void setAccumulatedIsiahf(BigDecimal accumulatedIsiahf) 
    {
        this.accumulatedIsiahf = accumulatedIsiahf;
    }

    public BigDecimal getAccumulatedIsiahf() 
    {
        return accumulatedIsiahf;
    }
    public void setAccumulatedBd(BigDecimal accumulatedBd) 
    {
        this.accumulatedBd = accumulatedBd;
    }

    public BigDecimal getAccumulatedBd() 
    {
        return accumulatedBd;
    }
    public void setAccumulatedChildEducation(BigDecimal accumulatedChildEducation) {
        this.accumulatedChildEducation = accumulatedChildEducation;
    }

    public BigDecimal getAccumulatedChildEducation() 
    {
        return accumulatedChildEducation;
    }
    public void setAccumulatedContinuingEducation(BigDecimal accumulatedContinuingEducation) {
        this.accumulatedContinuingEducation = accumulatedContinuingEducation;
    }

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
    public void setAccumulatedHousingRent(BigDecimal accumulatedHousingRent) {
        this.accumulatedHousingRent = accumulatedHousingRent;
    }

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
    public void setAccumulatedOther(BigDecimal accumulatedOther) 
    {
        this.accumulatedOther = accumulatedOther;
    }

    public BigDecimal getAccumulatedOther() 
    {
        return accumulatedOther;
    }
    public void setAccumulatedTftd(BigDecimal accumulatedTftd) 
    {
        this.accumulatedTftd = accumulatedTftd;
    }

    public BigDecimal getAccumulatedTftd() 
    {
        return accumulatedTftd;
    }
    public void setAccumulatedTotalCost(BigDecimal accumulatedTotalCost) {
        this.accumulatedTotalCost = accumulatedTotalCost;
    }

    public BigDecimal getAccumulatedTotalCost() 
    {
        return accumulatedTotalCost;
    }
    public void setAccumulatedIl(BigDecimal accumulatedIl) 
    {
        this.accumulatedIl = accumulatedIl;
    }

    public BigDecimal getAccumulatedIl() 
    {
        return accumulatedIl;
    }
    public void setIndividualLlt(BigDecimal individualLlt) 
    {
        this.individualLlt = individualLlt;
    }

    public BigDecimal getIndividualLlt() 
    {
        return individualLlt;
    }
    public void setCompanyTax(BigDecimal companyTax) 
    {
        this.companyTax = companyTax;
    }

    public BigDecimal getCompanyTax() 
    {
        return companyTax;
    }
    public void setCompanyTotalTax(BigDecimal companyTotalTax) 
    {
        this.companyTotalTax = companyTotalTax;
    }

    public BigDecimal getCompanyTotalTax() 
    {
        return companyTotalTax;
    }
    public void setAccumulatedAti(BigDecimal accumulatedAti) 
    {
        this.accumulatedAti = accumulatedAti;
    }

    public BigDecimal getAccumulatedAti() 
    {
        return accumulatedAti;
    }
    public void setAccumulatedBti(BigDecimal accumulatedBti) 
    {
        this.accumulatedBti = accumulatedBti;
    }

    public BigDecimal getAccumulatedBti() 
    {
        return accumulatedBti;
    }
    public void setTheoreticalIncome(BigDecimal theoreticalIncome) 
    {
        this.theoreticalIncome = theoreticalIncome;
    }

    public BigDecimal getTheoreticalIncome() 
    {
        return theoreticalIncome;
    }
    public void setAccumulatedLltCai(BigDecimal accumulatedLltCai) 
    {
        this.accumulatedLltCai = accumulatedLltCai;
    }

    public BigDecimal getAccumulatedLltCai() 
    {
        return accumulatedLltCai;
    }
    public void setCompanyLlt(BigDecimal companyLlt) 
    {
        this.companyLlt = companyLlt;
    }

    public BigDecimal getCompanyLlt() 
    {
        return companyLlt;
    }
    public void setOnlyChildAllowance(BigDecimal onlyChildAllowance) 
    {
        this.onlyChildAllowance = onlyChildAllowance;
    }

    public BigDecimal getOnlyChildAllowance() 
    {
        return onlyChildAllowance;
    }
    public void setSnteHousingFund(BigDecimal snteHousingFund) 
    {
        this.snteHousingFund = snteHousingFund;
    }

    public BigDecimal getSnteHousingFund() 
    {
        return snteHousingFund;
    }
    public void setExpense(BigDecimal expense) 
    {
        this.expense = expense;
    }

    public BigDecimal getExpense() 
    {
        return expense;
    }
    public void setExpense25(BigDecimal expense25) 
    {
        this.expense25 = expense25;
    }

    public BigDecimal getExpense25() 
    {
        return expense25;
    }
    public void setFsAllowance(BigDecimal fsAllowance) 
    {
        this.fsAllowance = fsAllowance;
    }

    public BigDecimal getFsAllowance() 
    {
        return fsAllowance;
    }
    public void setAfterTaxReissue(BigDecimal afterTaxReissue) 
    {
        this.afterTaxReissue = afterTaxReissue;
    }

    public BigDecimal getAfterTaxReissue() 
    {
        return afterTaxReissue;
    }
    public void setAfterTaxDeduction(BigDecimal afterTaxDeduction) 
    {
        this.afterTaxDeduction = afterTaxDeduction;
    }

    public BigDecimal getAfterTaxDeduction() 
    {
        return afterTaxDeduction;
    }
    public void setAnnualBonus(BigDecimal annualBonus) 
    {
        this.annualBonus = annualBonus;
    }

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
    public void setIncome(BigDecimal income) 
    {
        this.income = income;
    }

    public BigDecimal getIncome() 
    {
        return income;
    }
    public void setCost(BigDecimal cost) 
    {
        this.cost = cost;
    }

    public BigDecimal getCost() 
    {
        return cost;
    }
    public void setNetIncome(BigDecimal netIncome) 
    {
        this.netIncome = netIncome;
    }

    public BigDecimal getNetIncome() 
    {
        return netIncome;
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
    public void setCompanyUnemployment(BigDecimal companyUnemployment) {
        this.companyUnemployment = companyUnemployment;
    }

    public BigDecimal getCompanyUnemployment() 
    {
        return companyUnemployment;
    }
    public void setCompanyMaternity(BigDecimal companyMaternity) 
    {
        this.companyMaternity = companyMaternity;
    }

    public BigDecimal getCompanyMaternity() 
    {
        return companyMaternity;
    }
    public void setCompanyWri(BigDecimal companyWri) 
    {
        this.companyWri = companyWri;
    }

    public BigDecimal getCompanyWri() 
    {
        return companyWri;
    }
    public void setCompanyDisability(BigDecimal companyDisability) 
    {
        this.companyDisability = companyDisability;
    }

    public BigDecimal getCompanyDisability() 
    {
        return companyDisability;
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
    public void setAccumulatedPayrollTax(BigDecimal accumulatedPayrollTax) {
        this.accumulatedPayrollTax = accumulatedPayrollTax;
    }

    public BigDecimal getAccumulatedPayrollTax() 
    {
        return accumulatedPayrollTax;
    }

    public BigDecimal getEmployerLiability() {
        return employerLiability;
    }
    public void setEmployerLiability(BigDecimal employerLiability) {
        this.employerLiability = employerLiability;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }
    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }
    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getValueAddedTax() {
        return valueAddedTax;
    }
    public void setValueAddedTax(BigDecimal valueAddedTax) {
        this.valueAddedTax = valueAddedTax;
    }

    public BigDecimal getOtherBonus() {
        return otherBonus;
    }
    public void setOtherBonus(BigDecimal otherBonus) {
        this.otherBonus = otherBonus;
    }

    public BigDecimal getPretaxAdjustment() {
        return pretaxAdjustment;
    }
    public void setPretaxAdjustment(BigDecimal pretaxAdjustment) {
        this.pretaxAdjustment = pretaxAdjustment;
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

    public BigDecimal getServiceFeeTax() {
        return serviceFeeTax;
    }
    public void setServiceFeeTax(BigDecimal serviceFeeTax) {
        this.serviceFeeTax = serviceFeeTax;
    }

    public BigDecimal getTravelAllowance() {
        return travelAllowance;
    }
    public void setTravelAllowance(BigDecimal travelAllowance) {
        this.travelAllowance = travelAllowance;
    }

    public BigDecimal getAccumulatedChildCare() {
        return accumulatedChildCare;
    }
    public void setAccumulatedChildCare(BigDecimal accumulatedChildCare) {
        this.accumulatedChildCare = accumulatedChildCare;
    }

    public String getErName() {
        return erName;
    }
    public void setErName(String erName) {
        this.erName = erName;
    }

    public String getClientCode() {
        return clientCode;
    }
    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getGroupIds() {
        return groupIds;
    }
    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public BigDecimal getMealAllowance() {
        return mealAllowance;
    }

    public void setMealAllowance(BigDecimal mealAllowance) {
        this.mealAllowance = mealAllowance;
    }

    public BigDecimal getBirthdayGift() {
        return birthdayGift;
    }

    public void setBirthdayGift(BigDecimal birthdayGift) {
        this.birthdayGift = birthdayGift;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("duration", getDuration())
            .append("idNo", getIdNo())
            .append("name", getName())
            .append("basicSalary", getBasicSalary())
            .append("floatingSalary", getFloatingSalary())
            .append("monthlyAward", getMonthlyAward())
            .append("quarterAward", getQuarterAward())
            .append("overtimeAward150", getOvertimeAward150())
            .append("overtimeAward200", getOvertimeAward200())
            .append("overtimeAward300", getOvertimeAward300())
            .append("ealaDeduction", getEalaDeduction())
            .append("alDeduction", getAlDeduction())
            .append("absenteeismDeduction", getAbsenteeismDeduction())
            .append("slDeduction", getSlDeduction())
            .append("transportationPayment", getTransportationPayment())
            .append("communicationPayment", getCommunicationPayment())
            .append("otherBenefits", getOtherBenefits())
            .append("middleShiftPayment", getMiddleShiftPayment())
            .append("nightShiftPayment", getNightShiftPayment())
            .append("highTemperaturePayment", getHighTemperaturePayment())
            .append("hotWorkingPayment", getHotWorkingPayment())
            .append("tahwPayment", getTahwPayment())
            .append("reissue", getReissue())
            .append("wageDeduction", getWageDeduction())
            .append("pension", getPension())
            .append("medical", getMedical())
            .append("unemployment", getUnemployment())
            .append("housingFund", getHousingFund())
            .append("steHousingFund", getSteHousingFund())
            .append("socialBenefitsAdjustment", getSocialBenefitsAdjustment())
            .append("housingFundAdjustment", getHousingFundAdjustment())
            .append("annuity", getAnnuity())
            .append("unionFee", getUnionFee())
            .append("taxCalculation", getTaxCalculation())
            .append("preTaxIncome", getPreTaxIncome())
            .append("basicDeduction", getBasicDeduction())
            .append("accumulatedIpti", getAccumulatedIpti())
            .append("accumulatedIsiahf", getAccumulatedIsiahf())
            .append("accumulatedBd", getAccumulatedBd())
            .append("accumulatedChildEducation", getAccumulatedChildEducation())
            .append("accumulatedContinuingEducation", getAccumulatedContinuingEducation())
            .append("accumulatedHli", getAccumulatedHli())
            .append("accumulatedHousingRent", getAccumulatedHousingRent())
            .append("accumulatedSfte", getAccumulatedSfte())
            .append("accumulatedOther", getAccumulatedOther())
            .append("accumulatedTftd", getAccumulatedTftd())
            .append("accumulatedTotalCost", getAccumulatedTotalCost())
            .append("accumulatedIl", getAccumulatedIl())
            .append("individualLlt", getIndividualLlt())
            .append("companyTax", getCompanyTax())
            .append("companyTotalTax", getCompanyTotalTax())
            .append("accumulatedAti", getAccumulatedAti())
            .append("accumulatedBti", getAccumulatedBti())
            .append("theoreticalIncome", getTheoreticalIncome())
            .append("accumulatedLltCai", getAccumulatedLltCai())
            .append("companyLlt", getCompanyLlt())
            .append("onlyChildAllowance", getOnlyChildAllowance())
            .append("snteHousingFund", getSnteHousingFund())
            .append("expense", getExpense())
            .append("expense25", getExpense25())
            .append("fsAllowance", getFsAllowance())
            .append("afterTaxReissue", getAfterTaxReissue())
            .append("afterTaxDeduction", getAfterTaxDeduction())
            .append("annualBonus", getAnnualBonus())
            .append("annualBonusLlt", getAnnualBonusLlt())
            .append("income", getIncome())
            .append("cost", getCost())
            .append("netIncome", getNetIncome())
            .append("companyPension", getCompanyPension())
            .append("companyMedical", getCompanyMedical())
            .append("companyUnemployment", getCompanyUnemployment())
            .append("companyMaternity", getCompanyMaternity())
            .append("companyWri", getCompanyWri())
            .append("companyDisability", getCompanyDisability())
            .append("companyHousingFund", getCompanyHousingFund())
            .append("companyShf", getCompanyShf())
            .append("accumulatedPayrollTax", getAccumulatedPayrollTax())
            .append("employerLiability", getEmployerLiability())
            .append("deposit", getDeposit())
            .append("serviceFee", getServiceFee())
            .append("valueAddedTax", getValueAddedTax())
            .append("otherBonus", getOtherBonus())
            .append("pretaxAdjustment", getPretaxAdjustment())
            .append("indemnity", getIndemnity())
            .append("indemnityTax", getIndemnityTax())
            .append("shareIncentives", getShareIncentives())
            .append("shareIncentivesTax", getShareIncentivesTax())
            .append("serviceFeeTax", getServiceFeeTax())
            .append("travelAllowance", getTravelAllowance())
            .append("accumulatedChildCare", getAccumulatedChildCare())
            .append("erName", getErName())
            .append("clientCode", getClientCode())
            .append("entryDate", getEntryDate())
            .toString();
    }
}
