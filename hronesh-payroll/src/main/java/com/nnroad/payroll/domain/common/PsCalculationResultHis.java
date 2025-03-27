package com.nnroad.payroll.domain.common;

import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 计算结果历史对象 ps_calculation_result_his
 * 
 * @author Aaron
 * @date 2021-12-13
 */
public class PsCalculationResultHis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** Duration */
    @Excel(name = "excel.calresult.duration")
    private String duration;

    /** ID Number */
    @Excel(name = "excel.calresult.id_no")
    private String idNo;

    /** Name */
    @Excel(name = "excel.calresult.name")
    private String name;

    /** Basic Salary */
    @Excel(name = "excel.calresult.basic_salary")
    private BigDecimal basicSalary;

    /** Floating Salary */
    @Excel(name = "excel.calresult.floating_salary")
    private BigDecimal floatingSalary;

    /** Monthly Award */
    @Excel(name = "excel.calresult.monthly_award")
    private BigDecimal monthlyAward;

    /** Quarter Award */
    @Excel(name = "excel.calresult.quarter_award")
    private BigDecimal quarterAward;

    /** Overtime Award 150 */
    @Excel(name = "excel.calresult.overtime_award_150")
    private BigDecimal overtimeAward150;

    /** Overtime Award 200 */
    @Excel(name = "excel.calresult.overtime_award_200")
    private BigDecimal overtimeAward200;

    /** Overtime Award 300 */
    @Excel(name = "excel.calresult.overtime_award_300")
    private BigDecimal overtimeAward300;

    /** Deduction for 1st/last Month Absence */
    @Excel(name = "excel.calresult.eala_deduction")
    private BigDecimal ealaDeduction;

    /** Deduction for Personal Affair Leave */
    @Excel(name = "excel.calresult.al_deduction")
    private BigDecimal alDeduction;

    /** Deduction for Absenteeism */
    @Excel(name = "excel.calresult.absenteeism_deduction")
    private BigDecimal absenteeismDeduction;

    /** Deduction for Sick Leave */
    @Excel(name = "excel.calresult.sl_deduction")
    private BigDecimal slDeduction;

    /** Transportation Payment */
    @Excel(name = "excel.calresult.transportation_payment")
    private BigDecimal transportationPayment;

    /** Communication Payment */
    @Excel(name = "excel.calresult.communication_payment")
    private BigDecimal communicationPayment;

    @Excel(name = "excel.calresult.birthday_gift")
    private BigDecimal birthdayGift;

    @Excel(name = "excel.calresult.meal_allowance")
    private BigDecimal mealAllowance;

    /** Other Benefits */
    @Excel(name = "excel.calresult.other_benefits")
    private BigDecimal otherBenefits;

    /** Middle Shift Payment */
    @Excel(name = "excel.calresult.middle_shift_payment")
    private BigDecimal middleShiftPayment;

    /** Night Shift Payment */
    @Excel(name = "excel.calresult.night_shift_payment")
    private BigDecimal nightShiftPayment;

    /** High Temperature Payment */
    @Excel(name = "excel.calresult.high_temperature_payment")
    private BigDecimal highTemperaturePayment;

    /** Hot Working Days */
    @Excel(name = "excel.calresult.hot_working_payment")
    private BigDecimal hotWorkingPayment;

    /** Toxic and Harmful Working Payment */
    @Excel(name = "excel.calresult.tahw_payment")
    private BigDecimal tahwPayment;

    /** Reissue */
    @Excel(name = "excel.calresult.reissue")
    private BigDecimal reissue;

    /** Wage Deduction */
    @Excel(name = "excel.calresult.wage_deduction")
    private BigDecimal wageDeduction;

    /** Pension */
    @Excel(name = "excel.calresult.pension")
    private BigDecimal pension;

    /** Medical */
    @Excel(name = "excel.calresult.medical")
    private BigDecimal medical;

    /** Unemployment */
    @Excel(name = "excel.calresult.unemployment")
    private BigDecimal unemployment;

    /** Housing Fund */
    @Excel(name = "excel.calresult.housing_fund")
    private BigDecimal housingFund;

    /** Supplementary Housing Fund (tax exempt) */
    @Excel(name = "excel.calresult.ste_housing_fund")
    private BigDecimal steHousingFund;

    /** Social Benefits Adjustment */
    @Excel(name = "excel.calresult.social_benefits_adjustment")
    private BigDecimal socialBenefitsAdjustment;

    /** Housing Fund Adjustment */
    @Excel(name = "excel.calresult.housing_fund_adjustment")
    private BigDecimal housingFundAdjustment;

    /** Annuity */
    @Excel(name = "excel.calresult.annuity")
    private BigDecimal annuity;

    /** Union Fee */
    @Excel(name = "excel.calresult.union_fee")
    private BigDecimal unionFee;

    /** 计税不发 */
    @Excel(name = "excel.calresult.tax_calculation")
    private BigDecimal taxCalculation;

    /** Pre Tax Income */
    @Excel(name = "excel.calresult.pre_tax_income")
    private BigDecimal preTaxIncome;

    /** Basic Deduction */
    @Excel(name = "excel.calresult.basic_deduction")
    private BigDecimal basicDeduction;

    /** Accumulated Individual Pre Tax Income */
    @Excel(name = "excel.calresult.accumulated_ipti")
    private BigDecimal accumulatedIpti;

    /** Accumulated Individual Social Insurance and Housing Fund */
    @Excel(name = "excel.calresult.accumulated_isiahf")
    private BigDecimal accumulatedIsiahf;

    /** Accumulated Basic Deduction */
    @Excel(name = "excel.calresult.accumulated_bd")
    private BigDecimal accumulatedBd;

    /** Accumulated Children education */
    @Excel(name = "excel.calresult.accumulated_child_education")
    private BigDecimal accumulatedChildEducation;

    /** Accumulated Continuing Education */
    @Excel(name = "excel.calresult.accumulated_continuing_education")
    private BigDecimal accumulatedContinuingEducation;

    /** Accumulated Housing Loan Interest */
    @Excel(name = "excel.calresult.accumulated_hli")
    private BigDecimal accumulatedHli;

    /** Accumulated Housing Rent */
    @Excel(name = "excel.calresult.accumulated_housing_rent")
    private BigDecimal accumulatedHousingRent;

    /** Accumulated Support for the Elderly */
    @Excel(name = "excel.calresult.accumulated_sfte")
    private BigDecimal accumulatedSfte;

    /** Accumulated Other */
    @Excel(name = "excel.calresult.accumulated_other")
    private BigDecimal accumulatedOther;

    /** Accumulated Tax-Free Total Deductions */
    @Excel(name = "excel.calresult.accumulated_tftd")
    private BigDecimal accumulatedTftd;

    /** Accumulated Total Cost */
    @Excel(name = "excel.calresult.accumulated_total_cost")
    private BigDecimal accumulatedTotalCost;

    /** Accumulated Individual llT */
    @Excel(name = "excel.calresult.accumulated_il")
    private BigDecimal accumulatedIl;

    /** Individual llT */
    @Excel(name = "excel.calresult.individual_llt")
    private BigDecimal individualLlt;

    /** 公司负税项 */
    @Excel(name = "excel.calresult.company_tax")
    private BigDecimal companyTax;

    /** Company Toal Tax */
    @Excel(name = "excel.calresult.company_total_tax")
    private BigDecimal companyTotalTax;

    /** Accumulated after Tax Income */
    @Excel(name = "excel.calresult.accumulated_ati")
    private BigDecimal accumulatedAti;

    /** Accumulated before Tax Income */
    @Excel(name = "excel.calresult.accumulated_bti")
    private BigDecimal accumulatedBti;

    /** Theoretical Income */
    @Excel(name = "excel.calresult.theoretical_income")
    private BigDecimal theoreticalIncome;

    /** Accumulated  llT(Company and Individual) */
    @Excel(name = "excel.calresult.accumulated_llt_cai")
    private BigDecimal accumulatedLltCai;

    /** llT(Company) */
    @Excel(name = "excel.calresult.company_llt")
    private BigDecimal companyLlt;

    /** Only Child Allowance */
    @Excel(name = "excel.calresult.only_child_allowance")
    private BigDecimal onlyChildAllowance;

    /** Supplementary Housing Fund (not exempt from tax) */
    @Excel(name = "excel.calresult.snte_housing_fund")
    private BigDecimal snteHousingFund;

    /** Expense */
    @Excel(name = "excel.calresult.expense")
    private BigDecimal expense;

    /** Expenset 25% */
    @Excel(name = "excel.calresult.expense_25")
    private BigDecimal expense25;

    /** Subsistence Allowance for Foreigners */
    @Excel(name = "excel.calresult.fs_allowance")
    private BigDecimal fsAllowance;

    /** After-tax Reissue */
    @Excel(name = "excel.calresult.after_tax_reissue")
    private BigDecimal afterTaxReissue;

    /** 税后扣 */
    @Excel(name = "excel.calresult.after_tax_deduction")
    private BigDecimal afterTaxDeduction;

    /** Annual Bonus/13th Salary */
    @Excel(name = "excel.calresult.annual_bonus")
    private BigDecimal annualBonus;

    /** Annual Bonus IIT */
    @Excel(name = "excel.calresult.annual_bonus_llt")
    private BigDecimal annualBonusLlt;

    /** Income */
    @Excel(name = "excel.calresult.income")
    private BigDecimal income;

    /** Cost */
    @Excel(name = "excel.calresult.cost")
    private BigDecimal cost;

    /** Net Income */
    @Excel(name = "excel.calresult.net_income")
    private BigDecimal netIncome;

    /** Company Pension */
    @Excel(name = "excel.calresult.company_pension")
    private BigDecimal companyPension;

    /** Company Medical */
    @Excel(name = "excel.calresult.company_medical")
    private BigDecimal companyMedical;

    /** Company Unemployment */
    @Excel(name = "excel.calresult.company_unemployment")
    private BigDecimal companyUnemployment;

    /** Company Maternity */
    @Excel(name = "excel.calresult.company_maternity")
    private BigDecimal companyMaternity;

    /** Company Work-Related-Injury */
    @Excel(name = "excel.calresult.company_wri")
    private BigDecimal companyWri;

    /** Company Disability */
    @Excel(name = "excel.calresult.company_disability")
    private BigDecimal companyDisability;

    /** Company Housing Fund */
    @Excel(name = "excel.calresult.company_housing_fund")
    private BigDecimal companyHousingFund;

    /** Company Supplementary Housing Fund */
    @Excel(name = "excel.calresult.company_shf")
    private BigDecimal companyShf;

    /** Accumulated Payroll Tax */
    @Excel(name = "excel.calresult.accumulated_payroll_tax")
    private BigDecimal accumulatedPayrollTax;

    /** 雇主责任险 */
    @Excel(name = "excel.calresult.employerLiability")
    private BigDecimal employerLiability;

    /** 押金 */
    @Excel(name = "excel.calresult.deposit")
    private BigDecimal deposit;

    /** 服务费 */
    @Excel(name = "excel.calresult.serviceFee")
    private BigDecimal serviceFee;

    /** 增值税 */
    @Excel(name = "excel.calresult.valueAddedTax")
    private BigDecimal valueAddedTax;

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
    public void setAbsenteeismDeduction(BigDecimal absenteeismDeduction) 
    {
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
    public void setTransportationPayment(BigDecimal transportationPayment) 
    {
        this.transportationPayment = transportationPayment;
    }

    public BigDecimal getTransportationPayment() 
    {
        return transportationPayment;
    }
    public void setCommunicationPayment(BigDecimal communicationPayment) 
    {
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
    public void setHighTemperaturePayment(BigDecimal highTemperaturePayment) 
    {
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
    public void setSocialBenefitsAdjustment(BigDecimal socialBenefitsAdjustment) 
    {
        this.socialBenefitsAdjustment = socialBenefitsAdjustment;
    }

    public BigDecimal getSocialBenefitsAdjustment() 
    {
        return socialBenefitsAdjustment;
    }
    public void setHousingFundAdjustment(BigDecimal housingFundAdjustment) 
    {
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
    public void setAccumulatedChildEducation(BigDecimal accumulatedChildEducation) 
    {
        this.accumulatedChildEducation = accumulatedChildEducation;
    }

    public BigDecimal getAccumulatedChildEducation() 
    {
        return accumulatedChildEducation;
    }
    public void setAccumulatedContinuingEducation(BigDecimal accumulatedContinuingEducation) 
    {
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
    public void setAccumulatedHousingRent(BigDecimal accumulatedHousingRent) 
    {
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
    public void setAccumulatedTotalCost(BigDecimal accumulatedTotalCost) 
    {
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
    public void setCompanyUnemployment(BigDecimal companyUnemployment) 
    {
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
    public void setAccumulatedPayrollTax(BigDecimal accumulatedPayrollTax) 
    {
        this.accumulatedPayrollTax = accumulatedPayrollTax;
    }

    public BigDecimal getAccumulatedPayrollTax() 
    {
        return accumulatedPayrollTax;
    }
    public void setEmployerLiability(BigDecimal employerLiability) 
    {
        this.employerLiability = employerLiability;
    }

    public BigDecimal getEmployerLiability() 
    {
        return employerLiability;
    }
    public void setDeposit(BigDecimal deposit) 
    {
        this.deposit = deposit;
    }

    public BigDecimal getDeposit() 
    {
        return deposit;
    }
    public void setServiceFee(BigDecimal serviceFee) 
    {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getServiceFee() 
    {
        return serviceFee;
    }
    public void setValueAddedTax(BigDecimal valueAddedTax) 
    {
        this.valueAddedTax = valueAddedTax;
    }

    public BigDecimal getValueAddedTax() 
    {
        return valueAddedTax;
    }
    public void setGroupIds(String groupIds) 
    {
        this.groupIds = groupIds;
    }

    public String getGroupIds() 
    {
        return groupIds;
    }

    public BigDecimal getBirthdayGift() {
        return birthdayGift;
    }

    public void setBirthdayGift(BigDecimal birthdayGift) {
        this.birthdayGift = birthdayGift;
    }

    public BigDecimal getMealAllowance() {
        return mealAllowance;
    }

    public void setMealAllowance(BigDecimal mealAllowance) {
        this.mealAllowance = mealAllowance;
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
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("groupIds", getGroupIds())
            .toString();
    }
}
