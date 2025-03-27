package com.nnroad.payroll.domain.exportC;

import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * VIEW对象 s_payslip_report
 * 
 * @author Hrone
 * @date 2021-06-07
 */
public class SPayslipReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 期间 */
    private String duration;

    /** 客户名 */
    private String erName;

    /** 部门 */
    @Excel(name = "Dept./cost code\n部门/成本中心")
    private String department;

    /** 客户员工号 */
    @Excel(name = "Employee ID\n客户员工号")
    private String employeeNo;

    /** 工号 */
    @Excel(name = "ID No. 工号")
    private String idNo;

    /** 姓名 */
    @Excel(name = "Name 姓名")
    private String name;

    /** 基本工资 */
    @Excel(name = "Basic Salary\n基本工资")
    private BigDecimal basicSalary;

    /** 实际基本工资 */
    @Excel(name = "Actual Base Salary\n实际基本工资")
    private BigDecimal acutalBasicSalary;

    /** 奖金 */
    @Excel(name = "Bonus\n奖金")
    private BigDecimal normalBonus;

    /** 年终奖/13薪 */
    @Excel(name = "Annual Bonus/13th Salary\n年终奖/13薪")
    private BigDecimal annualBonus13thSalary;

    /** 津贴/补贴 */
    @Excel(name = "Allowance/Subsidy\n津贴/补贴")
    private BigDecimal allowanceSubsidy;

    /** 交通津贴 */
    @Excel(name = "Transport Allowance\n交通津贴")
    private BigDecimal transportAllowance;

    /** 通讯津贴 */
    @Excel(name = "Communication Allowance\n通讯津贴")
    private BigDecimal communicationAllowance;

    /** 餐食津贴 */
    @Excel(name = "Meal Allowance\n餐食津贴")
    private BigDecimal mealAllowance;

    /** 生日礼金 */
    @Excel(name = "Birthday Gift\n生日礼金")
    private BigDecimal birthdayGift;

    /** 其他固定津贴 */
    @Excel(name = "Other Allowance\n其他津贴")
    private BigDecimal otherAllowance;

    /** 加班费 */
    @Excel(name = "OT Payment\n加班费")
    private BigDecimal otPayment;

    /** 税前其他调整 */
    @Excel(name = "Other Adjustment Before Tax\n税前其他调整")
    private BigDecimal other;

    /** 公司负税项 */
    @Excel(name = "Income Employer Afford Tax\n公司负税项")
    private BigDecimal affordTax;

    /** 股权激励 */
    @Excel(name = "Stock option incentive/股权激励")
    private BigDecimal shareIncentives;

    /** 离职补偿金/赔偿金 */
    @Excel(name = "Compensation离职补偿金/赔偿金")
    private BigDecimal compensation;

    /** 社保基数 */
    @Excel(name = "Basis for Social Benefits Calculation\n社保基数")
    private BigDecimal socialBenefitsBasis;

    /** 公积金基数 */
    @Excel(name = "Basis for Housing Fund Calculation\n公积金基数")
    private BigDecimal housingFundBasis;

    /** 缴养老 */
    @Excel(name = "Pension\n缴养老")
    private BigDecimal pension;

    /** 缴医疗 */
    @Excel(name = "Medical\n缴医疗")
    private BigDecimal medical;

    /** 缴失业 */
    @Excel(name = "Unemployment\n缴失业")
    private BigDecimal unemployment;

    /** 缴公积金 */
    @Excel(name = "Housing Fund\n缴公积金")
    private BigDecimal housingFund;

    /** 社保补差-个人 */
    @Excel(name = "SB Adj-EE\n社保补差-个人")
    private BigDecimal sbAdjEe;

    /** 公积金补差-个人 */
    @Excel(name = "PHF Adj-EE\n公积金补差-个人")
    private BigDecimal phfAdjEe;

    /** 扣事假/旷工 */
    @Excel(name = "Deduction for Personal Affair/Absenteeism Leave\n扣事假/旷工")
    private BigDecimal affairAbsenteeismLeave;

    /** 扣病假/产假 */
    @Excel(name = "Deduction for Sick and Maternity Leave\n扣病假/产假")
    private BigDecimal sickLeave;

    /** 缴年金 */
    @Excel(name = "Annuity\n缴年金")
    private BigDecimal annuity;

    /** 工会费 */
    @Excel(name = "Union Fee\n工会费")
    private BigDecimal unionFee;

    /** 个税 */
    @Excel(name = "IIT\n个税")
    private BigDecimal IIT;

    /** 税后工资调整 */
    @Excel(name = "Adjustment After Tax\n税后工资调整")
    private BigDecimal afterTaxReissue;

    /** 报销款 */
    @Excel(name = "Expense\n报销款")
    private BigDecimal expense;

    /** 外籍生活津贴 */
    @Excel(name = "Subsistence Allowance for Foreigners\n外籍生活津贴")
    private BigDecimal foreignersSubsistence;

    /** 实发 */
    @Excel(name = "Net Income\n实发")
    private BigDecimal netIncome;

    /** 累计子女教育 */
    @Excel(name = "Accumulated Child Education\n累计子女教育")
    private BigDecimal accumulatedChildEducation;

    /** 累计住房贷款利息 */
    @Excel(name = "Accumulated HLI\n累计住房贷款利息")
    private BigDecimal accumulatedHLI;

    /** 累计住房租金 */
    @Excel(name = "Accumulated Housing Rent\n累计住房租金")
    private BigDecimal accumulatedHousingRent;

    /** 累计赡养老人 */
    @Excel(name = "Accumulated SFTE\n累计赡养老人")
    private BigDecimal accumulatedSFTE;

    /** 累计继续教育 */
    @Excel(name = "Accumulated Continuing Education\n累计继续教育")
    private BigDecimal accumulatedContinuingEducation;

    /** 累计婴幼儿照护费用 */
    @Excel(name = "Accumulated ICCC\n累计婴幼儿照护费用")
    private BigDecimal accumulatedChildCare;

    /** 累计专项附加扣除 */
    @Excel(name = "Accumulated Additional Deduction\n累计专项附加扣除")
    private BigDecimal accumulatedAdditionalDeduction;

    /** 每月固定工资 */
    @Excel(name = "Monthly Fixed Salary\n每月固定工资")
    private BigDecimal monthlyFixedSalary;

    /** 手机号 */
    @Excel(name = "Mobile\n手机号")
    private String mobile;

    /** 银行卡号 */
    @Excel(name = "Bank Account\n银行账号")
    private String bankAccount;

    /** 开户行 */
    @Excel(name = "Bank\n银行")
    private String bank;

    /** 银行账号持有人 */
    @Excel(name = "Bank account holder\n银行账号持有人")
    private String bankAccountHolder;


    @Excel(name = "Taxable Non-payment Salary\n计税不发")
    private BigDecimal taxCalculation;

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

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }
    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
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

    public void setAcutalBasicSalary(BigDecimal acutalBasicSalary) 
    {
        this.acutalBasicSalary = acutalBasicSalary;
    }
    public BigDecimal getAcutalBasicSalary() 
    {
        return acutalBasicSalary;
    }

    public void setNormalBonus(BigDecimal normalBonus) 
    {
        this.normalBonus = normalBonus;
    }
    public BigDecimal getNormalBonus() 
    {
        return normalBonus;
    }

    public void setAnnualBonus13thSalary(BigDecimal annualBonus13thSalary) {
        this.annualBonus13thSalary = annualBonus13thSalary;
    }
    public BigDecimal getAnnualBonus13thSalary() 
    {
        return annualBonus13thSalary;
    }

    public void setAllowanceSubsidy(BigDecimal allowanceSubsidy) 
    {
        this.allowanceSubsidy = allowanceSubsidy;
    }
    public BigDecimal getAllowanceSubsidy() 
    {
        return allowanceSubsidy;
    }

    public void setOtPayment(BigDecimal otPayment) 
    {
        this.otPayment = otPayment;
    }
    public BigDecimal getOtPayment() 
    {
        return otPayment;
    }

    public void setOther(BigDecimal other) 
    {
        this.other = other;
    }
    public BigDecimal getOther() 
    {
        return other;
    }

    public void setAffordTax(BigDecimal affordTax) 
    {
        this.affordTax = affordTax;
    }
    public BigDecimal getAffordTax() 
    {
        return affordTax;
    }

    public BigDecimal getShareIncentives() {
        return shareIncentives;
    }
    public void setShareIncentives(BigDecimal shareIncentives) {
        this.shareIncentives = shareIncentives;
    }

    public BigDecimal getCompensation() {
        return compensation;
    }
    public void setCompensation(BigDecimal compensation) {
        this.compensation = compensation;
    }

    public void setSocialBenefitsBasis(BigDecimal socialBenefitsBasis) {
        this.socialBenefitsBasis = socialBenefitsBasis;
    }
    public BigDecimal getSocialBenefitsBasis() 
    {
        return socialBenefitsBasis;
    }

    public void setHousingFundBasis(BigDecimal housingFundBasis) 
    {
        this.housingFundBasis = housingFundBasis;
    }
    public BigDecimal getHousingFundBasis() 
    {
        return housingFundBasis;
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

    public void setSbAdjEe(BigDecimal sbAdjEe) 
    {
        this.sbAdjEe = sbAdjEe;
    }
    public BigDecimal getSbAdjEe() 
    {
        return sbAdjEe;
    }

    public void setPhfAdjEe(BigDecimal phfAdjEe)
    {
        this.phfAdjEe = phfAdjEe;
    }
    public BigDecimal getPhfAdjEe()
    {
        return phfAdjEe;
    }

    public void setAffairAbsenteeismLeave(BigDecimal affairAbsenteeismLeave) {
        this.affairAbsenteeismLeave = affairAbsenteeismLeave;
    }
    public BigDecimal getAffairAbsenteeismLeave() 
    {
        return affairAbsenteeismLeave;
    }

    public void setSickLeave(BigDecimal sickLeave) 
    {
        this.sickLeave = sickLeave;
    }
    public BigDecimal getSickLeave() 
    {
        return sickLeave;
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

    public void setIIT(BigDecimal IIT) 
    {
        this.IIT = IIT;
    }
    public BigDecimal getIIT() 
    {
        return IIT;
    }

    public BigDecimal getAfterTaxReissue() {
        return afterTaxReissue;
    }
    public void setAfterTaxReissue(BigDecimal afterTaxReissue) {
        this.afterTaxReissue = afterTaxReissue;
    }

    public void setExpense(BigDecimal expense)
    {
        this.expense = expense;
    }
    public BigDecimal getExpense() 
    {
        return expense;
    }

    public void setForeignersSubsistence(BigDecimal foreignersSubsistence) {
        this.foreignersSubsistence = foreignersSubsistence;
    }
    public BigDecimal getForeignersSubsistence() 
    {
        return foreignersSubsistence;
    }

    public void setNetIncome(BigDecimal netIncome) 
    {
        this.netIncome = netIncome;
    }
    public BigDecimal getNetIncome() 
    {
        return netIncome;
    }

    public BigDecimal getAccumulatedChildEducation() {
        return accumulatedChildEducation;
    }
    public void setAccumulatedChildEducation(BigDecimal accumulatedChildEducation) {
        this.accumulatedChildEducation = accumulatedChildEducation;
    }

    public BigDecimal getAccumulatedHLI() {
        return accumulatedHLI;
    }
    public void setAccumulatedHLI(BigDecimal accumulatedHLI) {
        this.accumulatedHLI = accumulatedHLI;
    }

    public BigDecimal getAccumulatedHousingRent() {
        return accumulatedHousingRent;
    }
    public void setAccumulatedHousingRent(BigDecimal accumulatedHousingRent) {
        this.accumulatedHousingRent = accumulatedHousingRent;
    }

    public BigDecimal getAccumulatedSFTE() {
        return accumulatedSFTE;
    }
    public void setAccumulatedSFTE(BigDecimal accumulatedSFTE) {
        this.accumulatedSFTE = accumulatedSFTE;
    }

    public BigDecimal getAccumulatedContinuingEducation() {
        return accumulatedContinuingEducation;
    }
    public void setAccumulatedContinuingEducation(BigDecimal accumulatedContinuingEducation) {
        this.accumulatedContinuingEducation = accumulatedContinuingEducation;
    }

    public BigDecimal getAccumulatedChildCare() {
        return accumulatedChildCare;
    }
    public void setAccumulatedChildCare(BigDecimal accumulatedChildCare) {
        this.accumulatedChildCare = accumulatedChildCare;
    }

    public BigDecimal getAccumulatedAdditionalDeduction() {
        return accumulatedAdditionalDeduction;
    }
    public void setAccumulatedAdditionalDeduction(BigDecimal accumulatedAdditionalDeduction) {
        this.accumulatedAdditionalDeduction = accumulatedAdditionalDeduction;
    }

    public BigDecimal getMonthlyFixedSalary() {
        return monthlyFixedSalary;
    }
    public void setMonthlyFixedSalary(BigDecimal monthlyFixedSalary) {
        this.monthlyFixedSalary = monthlyFixedSalary;
    }

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBankAccount() {
        return bankAccount;
    }
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBank() {
        return bank;
    }
    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankAccountHolder() {
        return bankAccountHolder;
    }
    public void setBankAccountHolder(String bankAccountHolder) {
        this.bankAccountHolder = bankAccountHolder;
    }

    public BigDecimal getTransportAllowance() {
        return transportAllowance;
    }

    public void setTransportAllowance(BigDecimal transportAllowance) {
        this.transportAllowance = transportAllowance;
    }

    public BigDecimal getCommunicationAllowance() {
        return communicationAllowance;
    }

    public void setCommunicationAllowance(BigDecimal communicationAllowance) {
        this.communicationAllowance = communicationAllowance;
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

    public BigDecimal getOtherAllowance() {
        return otherAllowance;
    }

    public void setOtherAllowance(BigDecimal otherAllowance) {
        this.otherAllowance = otherAllowance;
    }

    public BigDecimal getTaxCalculation() {return taxCalculation;}

    public void setTaxCalculation(BigDecimal taxCalculation) {this.taxCalculation = taxCalculation;}

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("duration", getDuration())
            .append("erName", getErName())
            .append("department", getDepartment())
            .append("idNo", getIdNo())
            .append("name", getName())
            .append("basicSalary", getBasicSalary())
            .append("acutalBasicSalary", getAcutalBasicSalary())
            .append("normalBonus", getNormalBonus())
            .append("annualBonus13thSalary", getAnnualBonus13thSalary())
            .append("allowanceSubsidy", getAllowanceSubsidy())
            .append("otPayment", getOtPayment())
            .append("other", getOther())
            .append("affordTax", getAffordTax())
            .append("shareIncentives", getShareIncentives())
            .append("compensation", getCompensation())
            .append("socialBenefitsBasis", getSocialBenefitsBasis())
            .append("housingFundBasis", getHousingFundBasis())
            .append("pension", getPension())
            .append("medical", getMedical())
            .append("unemployment", getUnemployment())
            .append("housingFund", getHousingFund())
            .append("sbAdjEe", getSbAdjEe())
            .append("phfAdjEe", getPhfAdjEe())
            .append("affairAbsenteeismLeave", getAffairAbsenteeismLeave())
            .append("sickLeave", getSickLeave())
            .append("annuity", getAnnuity())
            .append("unionFee", getUnionFee())
            .append("IIT", getIIT())
            .append("afterTaxReissue", getAfterTaxReissue())
            .append("expense", getExpense())
            .append("foreignersSubsistence", getForeignersSubsistence())
            .append("netIncome", getNetIncome())
            .append("accumulatedChildEducation", getAccumulatedChildEducation())
            .append("accumulatedHLI", getAccumulatedHLI())
            .append("accumulatedHousingRent", getAccumulatedHousingRent())
            .append("accumulatedSFTE", getAccumulatedSFTE())
            .append("accumulatedContinuingEducation", getAccumulatedContinuingEducation())
            .append("accumulatedChildCare", getAccumulatedChildCare())
            .append("accumulatedAdditionalDeduction", getAccumulatedAdditionalDeduction())
            .append("monthlyFixedSalary", getMonthlyFixedSalary())
            .append("mobile", getMobile())
            .append("bankAccount", getBankAccount())
            .append("bank", getBank())
            .append("bankAccountHolder", getBankAccountHolder())
            .toString();
    }
}
