package com.nnroad.payroll.domain.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ps_payslip对象 ps_payslip
 * 
 * @author Hrone
 * @date 2021-01-21
 */
public class PsPayslip extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** payslipID */
    private Long id;

    /** 部门 */
    @Excel(name = "Department 部门", type = Excel.Type.IMPORT)
    private String department;

    /** 工号 */
    @Excel(name = "ID No. 工号")
    private String idNo;

    /** 姓名 */
    @Excel(name = "Name 姓名")
    private String name;

    /** 期间 */
    @Excel(name = "Duration 期间")
    private String duration;

    private String clientCode;

    /** 雇主 */
    @Excel(name = "Client Name 雇主")
    private String clientName;

    /** 基本工资 */
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

    /** 其他津贴 */
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
    @Excel(name = "Basis for Social Benefits Calculation\n社保基数", type = Excel.Type.IMPORT)
    private BigDecimal socialBenefitsBasis;

    /** 公积金基数 */
    @Excel(name = "Basis  for Housing Fund Calculation\n公积金基数", type = Excel.Type.IMPORT)
    private BigDecimal housingFundBasis;

    /** 工会费 */
    @Excel(name = "Union Fee\n工会费", type = Excel.Type.IMPORT)
    private BigDecimal unionFee;

    /** 税后调整 */
    @Excel(name = "Adjustment after Tax/税后调整", type = Excel.Type.IMPORT)
    private BigDecimal afterTaxAdjustment;

    /** 计税不发 */
    @Excel(name = "Tax Calculation\n计税不发")
    private BigDecimal taxCalculation;


    /** 开放标志 */
    private String kfFlag;

    /** 出账状态 */
    private String czFlag;

    private String org;
    private List<String> orgList;

    private String groupIds;

    /** 登录用户ID */
    private String userId;

    /** 开放时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date openTime;

    /** 发薪日 */
    private String payDay;

    /** payslip类型 */
    private String psType;

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setDepartment(String department)
    {
        this.department = department;
    }

    public String getDepartment()
    {
        return department;
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
    public void setAffordTax(BigDecimal affordTax)
    {
        this.affordTax = affordTax;
    }

    public BigDecimal getAffordTax()
    {
        return affordTax;
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
    public void setAbsence(BigDecimal absence)
    {
        this.absence = absence;
    }

    public BigDecimal getAbsence()
    {
        return absence;
    }
    public void setAffairLeave(BigDecimal affairLeave)
    {
        this.affairLeave = affairLeave;
    }

    public BigDecimal getAffairLeave()
    {
        return affairLeave;
    }
    public void setAbsenteeism(BigDecimal absenteeism)
    {
        this.absenteeism = absenteeism;
    }

    public BigDecimal getAbsenteeism()
    {
        return absenteeism;
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
    public void setOther(BigDecimal other)
    {
        this.other = other;
    }

    public BigDecimal getOther()
    {
        return other;
    }
    public void setIIT(BigDecimal IIT)
    {
        this.IIT = IIT;
    }

    public BigDecimal getIIT()
    {
        return IIT;
    }
    public void setBalanceCharge(BigDecimal balanceCharge)
    {
        this.balanceCharge = balanceCharge;
    }

    public BigDecimal getBalanceCharge()
    {
        return balanceCharge;
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

    public String getKfFlag() {
        return kfFlag;
    }
    public void setKfFlag(String kfFlag) {
        this.kfFlag = kfFlag;
    }

    public String getCzFlag() {
        return czFlag;
    }
    public void setCzFlag(String czFlag) {
        this.czFlag = czFlag;
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

    public BigDecimal getAfterTaxAdjustment() {
        return afterTaxAdjustment;
    }
    public void setAfterTaxAdjustment(BigDecimal afterTaxAdjustment) {
        this.afterTaxAdjustment = afterTaxAdjustment;
    }

    public String getGroupIds() {
        return groupIds;
    }
    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getOpenTime() {
        return openTime;
    }
    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public String getPayDay() { return payDay; }
    public void setPayDay(String payDay) { this.payDay = payDay; }

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

    public String getPsType() {
        return psType;
    }

    public void setPsType(String psType) {
        this.psType = psType;
    }

    public BigDecimal getTaxCalculation() {
        return taxCalculation;
    }

    public void setTaxCalculation(BigDecimal taxCalculation) {
        this.taxCalculation = taxCalculation;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public List<String> getOrgList() {
        return orgList;
    }

    public void setOrgList(List<String> orgList) {
        this.orgList = orgList;
    }

    @Override
    public String toString() {
        return "PsPayslip{" +
                "id=" + id +
                ", department='" + department + '\'' +
                ", idNo='" + idNo + '\'' +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", clientCode='" + clientCode + '\'' +
                ", clientName='" + clientName + '\'' +
                ", basicSalary=" + basicSalary +
                ", otPayment=" + otPayment +
                ", allowanceSubsidy=" + allowanceSubsidy +
                ", transportAllowance=" + transportAllowance +
                ", communicationAllowance=" + communicationAllowance +
                ", mealAllowance=" + mealAllowance +
                ", birthdayGift=" + birthdayGift +
                ", otherAllowance=" + otherAllowance +
                ", annualBonus13thSalary=" + annualBonus13thSalary +
                ", normalBonus=" + normalBonus +
                ", pretaxAdjustment=" + pretaxAdjustment +
                ", shareIncentives=" + shareIncentives +
                ", compensation=" + compensation +
                ", absence=" + absence +
                ", affairLeave=" + affairLeave +
                ", absenteeism=" + absenteeism +
                ", sickLeave=" + sickLeave +
                ", pension=" + pension +
                ", medical=" + medical +
                ", unemployment=" + unemployment +
                ", housingFund=" + housingFund +
                ", balanceCharge=" + balanceCharge +
                ", annuity=" + annuity +
                ", expense=" + expense +
                ", foreignersSubsistence=" + foreignersSubsistence +
                ", yTDChildrenEducation='" + yTDChildrenEducation + '\'' +
                ", yTDContinueEducation='" + yTDContinueEducation + '\'' +
                ", yTDHousingLoanInterest='" + yTDHousingLoanInterest + '\'' +
                ", yTDHousingRental='" + yTDHousingRental + '\'' +
                ", yTDCaringForTheElderly='" + yTDCaringForTheElderly + '\'' +
                ", yTDChildCare='" + yTDChildCare + '\'' +
                ", IIT=" + IIT +
                ", affordTax=" + affordTax +
                ", other=" + other +
                ", netIncome=" + netIncome +
                ", socialBenefitsBasis=" + socialBenefitsBasis +
                ", housingFundBasis=" + housingFundBasis +
                ", unionFee=" + unionFee +
                ", afterTaxAdjustment=" + afterTaxAdjustment +
                ", taxCalculation=" + taxCalculation +
                ", kfFlag='" + kfFlag + '\'' +
                ", czFlag='" + czFlag + '\'' +
                ", groupIds='" + groupIds + '\'' +
                ", userId='" + userId + '\'' +
                ", openTime=" + openTime +
                ", payDay='" + payDay + '\'' +
                ", psType='" + psType + '\'' +
                '}';
    }

}
