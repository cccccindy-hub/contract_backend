package com.nnroad.payroll.domain;

import com.nnroad.common.annotation.Excel;

import java.math.BigDecimal;

public class Salary {
    private String name;

    @Excel(name = "Basic Salary\n基本工资")
    private BigDecimal basicSalary;

    /** 加班费 */
    @Excel(name = "OT Payment\n加班费")
    private BigDecimal otPayment;

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

    /** 年终奖/13薪 */
    @Excel(name = "Annual Bonus/13th Salary\n年终奖/13薪")
    private BigDecimal annualBonus13thSalary;

    /** 日常奖金 */
    @Excel(name = "Normal Bonus\n日常奖金")
    private BigDecimal normalBonus;

    /** 税前调整 */
    @Excel(name = "Adjustment before Tax/税前调整")
    private BigDecimal pretaxAdjustment;

    /** 股权激励 */
    @Excel(name = "Stock option incentive/股权激励")
    private BigDecimal shareIncentives;

    /** 离职补偿金/赔偿金 */
    @Excel(name = "Compensation离职补偿金/赔偿金")
    private BigDecimal compensation;

    /** 入离职缺勤扣 */
    @Excel(name = "Deduction for 1st/last Month' Absence\n入离职缺勤扣")
    private BigDecimal absence;

    /** 扣事假 */
    @Excel(name = "Deduction for Personal Affair Leave\n扣事假")
    private BigDecimal affairLeave;

    /** 扣旷工 */
    @Excel(name = "Deduction for Absenteeism\n扣旷工")
    private BigDecimal absenteeism;

    /** 扣病假 */
    @Excel(name = "Deduction for Sick Leave\n扣病假")
    private BigDecimal sickLeave;

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

    /** 代缴费用补差 */
    @Excel(name = "Balance For Charge On Behalf\n代缴费用补差")
    private BigDecimal balanceCharge;

    /** 缴年金 */
    @Excel(name = "Annuity\n缴年金")
    private BigDecimal annuity;

    /** 报销款 */
    @Excel(name = "Expense\n报销款")
    private BigDecimal expense;

    /** 外籍生活津贴 */
    @Excel(name = "Subsistence Allowance for Foreigners\n外籍生活津贴")
    private BigDecimal foreignersSubsistence;

    /** 累计子女教育 */
    @Excel(name = "YTD Children Education\n累计子女教育")
    private String yTDChildrenEducation;

    /** 累计继续教育 */
    @Excel(name = "YTD Continue Education\n累计继续教育")
    private String yTDContinueEducation;

    /** 累计住房贷款利息 */
    @Excel(name = "YTD Housing Loan Interest\n累计住房贷款利息")
    private String yTDHousingLoanInterest;

    /** 累计住房租金 */
    @Excel(name = "YTD Housing Rental\n累计住房租金")
    private String yTDHousingRental;

    /** 累计赡养老人 */
    @Excel(name = "YTD Caring For The Elderly\n累计赡养老人")
    private String yTDCaringForTheElderly;

    /** 累计婴幼儿照护费用 */
    @Excel(name = "YTD Child Care Costs\n累计婴幼儿照护费用")
    private String yTDChildCare;

    /** 个税 */
    @Excel(name = "IIT\n个税")
    private BigDecimal IIT;

    /** 公司负税项 */
    @Excel(name = "Income Employer Afford Tax\n公司负税项")
    private BigDecimal affordTax;

    /** 其他 */
    @Excel(name = "Other\n其他")
    private BigDecimal other;


    /** 实发 */
    @Excel(name = "Net Income\n实发")
    private BigDecimal netIncome;

    /** 社保基数 */
    @Excel(name = "Basis for Social Benefits Calculation\n社保基数",type = Excel.Type.IMPORT)
    private BigDecimal socialBenefitsBasis;

    /** 公积金基数 */
    @Excel(name = "Basis  for Housing Fund Calculation\n公积金基数",type = Excel.Type.IMPORT)
    private BigDecimal housingFundBasis;


    /** 工会费 */
    @Excel(name = "Union Fee\n工会费",type = Excel.Type.IMPORT)
    private BigDecimal unionFee;

    /** 税后调整 */
    @Excel(name = "Adjustment after Tax/税后调整",type = Excel.Type.IMPORT)
    private BigDecimal afterTaxAdjustment;

    @Override
    public String toString() {
          return "{\"id\": \"name\", \"seq\": 1, \"code\": \"name\",\"name\": \"姓名\", \"ename\": \"name\",\"value\": \""+name+
                "\"}, {\"id\": \"basicSalary\", \"seq\": 2, \"code\": \"basicSalary\",\"name\": \"基本工资\", \"ename\": \"Basic Salary\",\"value\": " +basicSalary+
                "}, {\"id\": \"otPayment\", \"seq\": 3, \"code\": \"otPayment\",\"name\": \"加班费\", \"ename\": \"Overtime Payment\",\"value\": "+otPayment+
                "}, {\"id\": \"allowanceSubsidy\", \"seq\": 4, \"code\": \"allowanceSubsidy\",\"name\": \"津贴/补贴\", \"ename\": \"Allowance/Subsidy\", \"value\": "+allowanceSubsidy+
                "}, {\"id\": \"transportAllowance\", \"seq\": 5, \"code\": \"transportAllowance\",\"name\": \"交通津贴\",\"ename\": \"Floating Wages\", \"value\": "+transportAllowance+
                "}, {\"id\": \"communicationAllowance\", \"seq\": 6, \"code\": \"communicationAllowance\",\"name\": \"通讯津贴\", \"ename\": \"Communication allowance\",\"value\": "+communicationAllowance+
                "}, {\"id\": \"mealAllowance\", \"seq\": 7, \"code\": \"mealAllowance\",\"name\": \"餐食津贴\",\"ename\": \"meal allowance\", \"value\": "+mealAllowance+
                "}, {\"id\": \"birthdayGift\", \"seq\": 8, \"code\": \"birthdayGift\",\"name\": \"生日礼金\",\"ename\": \"Birthday Gift\", \"value\": "+birthdayGift+
                "}, {\"id\": \"otherAllowance\", \"seq\": 9, \"code\": \"otherAllowance\",\"name\": \"其他固定津贴\",\"ename\": \"other allowance\", \"value\": "+otherAllowance+
                "}, {\"id\": \"annualBonus13thSalary\", \"seq\": 10, \"code\": \"annualBonus13thSalary\",\"name\": \"年终奖/13薪\",\"ename\": \"Annual Bonus/13th Month Salary\", \"value\": "+annualBonus13thSalary+
                "}, {\"id\": \"normalBonus\", \"seq\": 11, \"code\": \"normalBonus\",\"name\": \"日常奖金\", \"ename\": \"Other Bonus\",\"value\": "+normalBonus+
                "}, {\"id\": \"pretaxAdjustment\", \"seq\": 12, \"code\": \"pretaxAdjustment\",\"name\": \"税前调整\", \"ename\": \"Adjustment Before Tax\", \"value\": "+pretaxAdjustment+
                "}, {\"id\": \"shareIncentives\", \"seq\": 13, \"code\": \"shareIncentives\",\"name\": \"股权激励\",\"ename\": \"Stock Option Incentive\", \"value\": "+shareIncentives+
                "}, {\"id\": \"compensation\", \"seq\": 14, \"code\": \"compensation\",\"name\": \"离职补偿金/赔偿金\",\"ename\": \"Severance Pay/Other Compensation\", \"value\": "+compensation+
                "}, {\"id\": \"affairLeave\", \"seq\": 15, \"code\": \"affairLeave\",\"name\": \"扣事假\", \"ename\": \"Balancing Deduction for Onboarding/Offboarding\",\"value\": "+affairLeave+
                "}, {\"id\": \"absence\", \"seq\": 16, \"code\": \"absence\",\"name\": \"入离职缺勤扣\",\"ename\": \"Personal Leave\", \"value\": "+absence+
                "}, {\"id\": \"absenteeism\", \"seq\": 17, \"code\": \"absenteeism\",\"name\": \"扣旷工\", \"ename\": \"Unauthorized Absence\",\"value\": "+absenteeism+
                "}, {\"id\": \"sickLeave\", \"seq\": 18, \"code\": \"sickLeave\",\"name\": \"扣病假\", \"ename\": \"Sick Leave\",\"value\": "+sickLeave+
                "}, {\"id\": \"pension\", \"seq\": 19, \"code\": \"pension\",\"name\": \"缴养老\", \"ename\": \"Pension\", \"value\": "+pension+
                "}, {\"id\": \"medical\", \"seq\": 20, \"code\": \"medical\",\"name\": \"缴医疗\", \"ename\": \"Medical Insurance\",\"value\": "+medical+
                "}, {\"id\": \"unemployment\", \"seq\": 21, \"code\": \"unemployment\",\"name\": \"缴失业\",\"ename\": \"Unemployment Fund\", \"value\": "+unemployment+
                "}, {\"id\": \"housingFund\", \"seq\": 22, \"code\": \"housingFund\",\"name\": \"缴公积金\",\"ename\": \"Housing Fund\", \"value\": "+housingFund+
                "}, {\"id\": \"balanceCharge\", \"seq\": 23, \"code\": \"balanceCharge\",\"name\": \"代缴费用补差\",\"ename\": \"Balance For Charge On Behalf\", \"value\": "+balanceCharge+
                "}, {\"id\": \"annuity\", \"seq\": 24, \"code\": \"annuity\",\"name\": \"缴年金\", \"ename\": \"Annuity\",\"value\": "+annuity+
                "}, {\"id\": \"expense\", \"seq\": 25, \"code\": \"expense\",\"name\": \"报销款\", \"ename\": \"Expense\",\"value\": "+expense+
                "}, {\"id\": \"foreignersSubsistence\", \"seq\": 26, \"code\": \"foreignersSubsistence\",\"name\": \"外籍生活津贴\",\"ename\": \"Foreigner's Allowance\", \"value\": "+foreignersSubsistence+
                "}, {\"id\": \"yTDChildrenEducation\", \"seq\": 27, \"code\": \"yTDChildrenEducation\",\"name\": \"累计子女教育\",\"ename\": \"YTD Children Education\",  \"value\": "+yTDChildrenEducation+
                "}, {\"id\": \"yTDContinueEducation\", \"seq\": 28, \"code\": \"yTDContinueEducation\",\"name\": \"累计继续教育\", \"ename\": \"YTD Continue Education\",\"value\": "+ yTDContinueEducation+
                "}, {\"id\": \"yTDHousingLoanInterest\", \"seq\": 29, \"code\": \"yTDHousingLoanInterest\",\"name\": \"累计住房贷款利息\",\"ename\": \"YTD Housing Loan Interest\", \"value\": "+yTDHousingLoanInterest+
                "}, {\"id\": \"yTDHousingRental\", \"seq\": 30, \"code\": \"yTDHousingRental\",\"name\": \"累计住房租金\", \"ename\": \"YTD Housing Rental\", \"value\": "+yTDHousingRental+
                "}, {\"id\": \"yTDCaringForTheElderly\", \"seq\": 31, \"code\": \"yTDCaringForTheElderly\",\"name\": \"累计赡养老人\", \"ename\": \"YTD Caring For The Elderly\",\"value\": "+yTDCaringForTheElderly+
                "}, {\"id\": \"yTDChildCare\", \"seq\": 32, \"code\": \"yTDChildCare\",\"name\": \"累计婴幼儿照护费用 \", \"ename\": \"YTD Child Care Costs\",\"value\": "+yTDChildCare+
                "}, {\"id\": \"IIT\", \"seq\": 33, \"code\": \"IIT\",\"name\": \"个税\", \"ename\": \"IIT\",\"value\": "+IIT+
                "}, {\"id\": \"affordTax\", \"seq\": 34, \"code\": \"affordTax\",\"name\": \"公司负税项\",\"ename\": \"Income Employer Afford Tax\", \"value\": "+ affordTax+
                "}, {\"id\": \"other\", \"seq\": 35, \"code\": \"other\",\"name\": \"其他\", \"ename\": \"Other\",\"value\": "+other+
                "}, {\"id\": \"netIncome\", \"seq\": 36, \"code\": \"netIncome\",\"name\": \"实发\", \"ename\": \"Net Pay\",\"value\": "+netIncome+
                "}, {\"id\": \"socialBenefitsBasis\", \"seq\": 37, \"code\": \"socialBenefitsBasis\",\"name\": \"社保基数\", \"ename\": \"Social Benefits Basis\",\"value\": "+socialBenefitsBasis+
                "}, {\"id\": \"housingFundBasis\", \"seq\": 38, \"code\": \"housingFundBasis\",\"name\": \"公积金基数\", \"ename\": \"Housing Fund Basis\",\"value\": "+housingFundBasis+
                "}, {\"id\": \"unionFee\", \"seq\": 39, \"code\": \"unionFee\",\"name\": \"工会费\", \"ename\": \"Union Fee\", \"value\": "+unionFee+
                "}, {\"id\": \"afterTaxAdjustment\", \"seq\": 40, \"code\": \"afterTaxAdjustment\",\"name\": \"税后调整\", \"ename\": \"After Tax Adjustment\",\"value\": "+afterTaxAdjustment+
                "}";
    }

    public Salary() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getOtPayment() {
        return otPayment;
    }

    public void setOtPayment(BigDecimal otPayment) {
        this.otPayment = otPayment;
    }

    public BigDecimal getAllowanceSubsidy() {
        return allowanceSubsidy;
    }

    public void setAllowanceSubsidy(BigDecimal allowanceSubsidy) {
        this.allowanceSubsidy = allowanceSubsidy;
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

    public BigDecimal getAnnualBonus13thSalary() {
        return annualBonus13thSalary;
    }

    public void setAnnualBonus13thSalary(BigDecimal annualBonus13thSalary) {
        this.annualBonus13thSalary = annualBonus13thSalary;
    }

    public BigDecimal getNormalBonus() {
        return normalBonus;
    }

    public void setNormalBonus(BigDecimal normalBonus) {
        this.normalBonus = normalBonus;
    }

    public BigDecimal getPretaxAdjustment() {
        return pretaxAdjustment;
    }

    public void setPretaxAdjustment(BigDecimal pretaxAdjustment) {
        this.pretaxAdjustment = pretaxAdjustment;
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

    public BigDecimal getAbsence() {
        return absence;
    }

    public void setAbsence(BigDecimal absence) {
        this.absence = absence;
    }

    public BigDecimal getAffairLeave() {
        return affairLeave;
    }

    public void setAffairLeave(BigDecimal affairLeave) {
        this.affairLeave = affairLeave;
    }

    public BigDecimal getAbsenteeism() {
        return absenteeism;
    }

    public void setAbsenteeism(BigDecimal absenteeism) {
        this.absenteeism = absenteeism;
    }

    public BigDecimal getSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(BigDecimal sickLeave) {
        this.sickLeave = sickLeave;
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

    public BigDecimal getHousingFund() {
        return housingFund;
    }

    public void setHousingFund(BigDecimal housingFund) {
        this.housingFund = housingFund;
    }

    public BigDecimal getBalanceCharge() {
        return balanceCharge;
    }

    public void setBalanceCharge(BigDecimal balanceCharge) {
        this.balanceCharge = balanceCharge;
    }

    public BigDecimal getAnnuity() {
        return annuity;
    }

    public void setAnnuity(BigDecimal annuity) {
        this.annuity = annuity;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    public BigDecimal getForeignersSubsistence() {
        return foreignersSubsistence;
    }

    public void setForeignersSubsistence(BigDecimal foreignersSubsistence) {
        this.foreignersSubsistence = foreignersSubsistence;
    }

    public String getyTDChildrenEducation() {
        return yTDChildrenEducation;
    }

    public void setyTDChildrenEducation(String yTDChildrenEducation) {
        this.yTDChildrenEducation = yTDChildrenEducation;
    }

    public String getyTDContinueEducation() {
        return yTDContinueEducation;
    }

    public void setyTDContinueEducation(String yTDContinueEducation) {
        this.yTDContinueEducation = yTDContinueEducation;
    }

    public String getyTDHousingLoanInterest() {
        return yTDHousingLoanInterest;
    }

    public void setyTDHousingLoanInterest(String yTDHousingLoanInterest) {
        this.yTDHousingLoanInterest = yTDHousingLoanInterest;
    }

    public String getyTDHousingRental() {
        return yTDHousingRental;
    }

    public void setyTDHousingRental(String yTDHousingRental) {
        this.yTDHousingRental = yTDHousingRental;
    }

    public String getyTDCaringForTheElderly() {
        return yTDCaringForTheElderly;
    }

    public void setyTDCaringForTheElderly(String yTDCaringForTheElderly) {
        this.yTDCaringForTheElderly = yTDCaringForTheElderly;
    }

    public String getyTDChildCare() {
        return yTDChildCare;
    }

    public void setyTDChildCare(String yTDChildCare) {
        this.yTDChildCare = yTDChildCare;
    }

    public BigDecimal getIIT() {
        return IIT;
    }

    public void setIIT(BigDecimal IIT) {
        this.IIT = IIT;
    }

    public BigDecimal getAffordTax() {
        return affordTax;
    }

    public void setAffordTax(BigDecimal affordTax) {
        this.affordTax = affordTax;
    }

    public BigDecimal getOther() {
        return other;
    }

    public void setOther(BigDecimal other) {
        this.other = other;
    }

    public BigDecimal getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(BigDecimal netIncome) {
        this.netIncome = netIncome;
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

    public BigDecimal getUnionFee() {
        return unionFee;
    }

    public void setUnionFee(BigDecimal unionFee) {
        this.unionFee = unionFee;
    }

    public BigDecimal getAfterTaxAdjustment() {
        return afterTaxAdjustment;
    }

    public void setAfterTaxAdjustment(BigDecimal afterTaxAdjustment) {
        this.afterTaxAdjustment = afterTaxAdjustment;
    }
}
