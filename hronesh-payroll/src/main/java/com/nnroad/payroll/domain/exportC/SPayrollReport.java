package com.nnroad.payroll.domain.exportC;

import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * VIEW对象 s_payroll_report
 * 
 * @author Hrone
 * @date 2021-06-07
 */
public class SPayrollReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 期间 */
    private String duration;

    /** Client 客户 */
    private String erName;

    /** 雇主id */
    private String erId;

    /** 货币 */
    private String currency;

    /** 汇率 */
    private String exchangeRate;

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

    /** 社保城市 */
    @Excel(name = "Social Benefits Address\n社保城市")
    private String socialBenefitsAddress;

    /** 员工实得 */
    @Excel(name = "Net Income\n员工实得")
    private BigDecimal netIncome;

    /** 报销款 */
    @Excel(name = "Expense\n报销款")
    private BigDecimal expense;

    /** 外籍生活津贴 */
    @Excel(name = "Subsistence Allowance for Foreigners\n外籍生活津贴")
    private BigDecimal foreignerAllowance;

    /** 个人养老 */
    @Excel(name = "Pension\n养老-个人")
    private BigDecimal ipPension;

    /** 个人医疗 */
    @Excel(name = "Medical\n医疗-个人")
    private BigDecimal ipMedical;

    /** 个人失业 */
    @Excel(name = "Unemployment\n失业-个人")
    private BigDecimal ipUnemployment;

    /** 个人公积金 */
    @Excel(name = "Housing Fund\n公积金-个人")
    private BigDecimal ipHousingFund;

    /** 个人年金 */
    @Excel(name = "Annuity\n年金-个人")
    private BigDecimal ipAnnuity;

    /** 社保补差-个人 */
    @Excel(name = "SB Adj-EE\n社保补差-个人")
    private BigDecimal sbAdjEe;

    /** 公积金补差-个人 */
    @Excel(name = "PHF Adj-EE\n公积金补差-个人")
    private BigDecimal phfAdjEe;

    /** 个人工会费 */
    @Excel(name = "Union Fee\n工会费-个人")
    private BigDecimal ipUnionFee;

    /** 个税合计 */
    @Excel(name = "IIT Total\n个税合计")
    private BigDecimal IIT;

    /** 工资个税 */
    @Excel(name = "Salary IIT\n工资个税")
    private BigDecimal salaryIIT;

    /** 年终奖个税 */
    @Excel(name = "Annual Bonus IIT\n年终奖个税")
    private BigDecimal annualBonusIIT;

    /** 公司养老 */
    @Excel(name = "Pension\n养老-公司")
    private BigDecimal epPension;

    /** 公司医疗 */
    @Excel(name = "Medical\n医疗-公司")
    private BigDecimal epMedical;

    /** 公司失业 */
    @Excel(name = "Unemployment\n失业-公司")
    private BigDecimal epUnemployment;

    /** 公司生育 */
    @Excel(name = "Maternity\n生育-公司")
    private BigDecimal maternity;

    /** 公司工伤 */
    @Excel(name = "Work-Related-Injury\n工伤")
    private BigDecimal workRelatedInjury;

    /** 公司残保金 */
    @Excel(name = "Disability\n残保金")
    private BigDecimal disability;

    /** 公司公积金 */
    @Excel(name = "Housing Fund\n公积金-公司")
    private BigDecimal epHousingFund;

    /** 公司取暖费 */
    @Excel(name = "Heating Fee\n取暖费")
    private BigDecimal epHeatingFee;

    /** 社保补差-公司 */
    @Excel(name = "SB Adj-Com\n社保补差-公司")
    private BigDecimal sbAdjCom;

    /** 公积金补差-公司 */
    @Excel(name = "PHF Adj-Com\n公积金补差-公司")
    private BigDecimal phfAdjCom;

    /** 残保金补差-公司 */
    @Excel(name = "CD Adj-Com\n残保金补差-公司")
    private BigDecimal cdAdjCom;

    /** 公司年金 */
    @Excel(name = "Annuity\n年金-公司")
    private BigDecimal epAnnuity;

    /** 公司工会费 */
    @Excel(name = "Union Fee\n工会费-公司")
    private BigDecimal epUnionFee;

    /** 公司商业保险 */
    @Excel(name = "Commercial Insurance\n商业保险")
    private BigDecimal commercialInsurance;

    /** 公司报销款税额 */
    @Excel(name = "Business Tax for Expense\n报销款税额")
    private BigDecimal businessTax;

    /** 公司押金 */
    @Excel(name = "Deposit\n押金")
    private BigDecimal deposit;

    /** 公司雇主责任险 */
    @Excel(name = "Employer Liability Insurance\n雇主责任险")
    private BigDecimal employerLiability;

    /** 公司其他 */
    @Excel(name = "Office Seat\n其他")
    private BigDecimal officeSeat;

    /** 公司其他1 */
    @Excel(name = "Others\n其他")
    private BigDecimal other;

    /** 服务费 */
    @Excel(name = "Service Fee\n服务费")
    private BigDecimal serviceFee;

    /** 服务费税 */
    @Excel(name = "SFT\n服务费税")
    private BigDecimal serviceFeeTax;

    /** 增值税 */
    @Excel(name = "Value Added Tax\n增值税")
    private BigDecimal valueAddedTax;

    /** 总成本 */
    @Excel(name = "Total Cost\n总成本")
    private BigDecimal totalCost;

    /** 备注 */
    @Excel(name = "Remark\n备注")
    private String remarks;

    /** 代缴社保 */
    @Excel(name = "pay_sb\n代缴社保")
    private BigDecimal paySb;

    /** 代缴公积金 */
    @Excel(name = "pay_hf\n代缴公积金")
    private BigDecimal payHf;

    /** 代缴个税 */
    @Excel(name = "pay_iit\n代缴个税")
    private BigDecimal payIIt;

    /** 实际发薪 */
    @Excel(name = "Net Salary\n实际发薪")
    private BigDecimal netSalary;

    /** 员工应发-不含年终奖 */
    @Excel(name = "Gross Salary\n员工应发-不含年终奖")
    private BigDecimal grossSalary;

    /** 员工基本工资+其它奖金 */
    @Excel(name = "Basic Salary + Other Bonus\n员工基本工资+其它奖金")
    private BigDecimal salaryBonus;

    /** 出差补贴 */
    @Excel(name = "Travel Allowance\n出差补贴")
    private BigDecimal travelAllowance;

    /** 年终奖/13薪 */
    @Excel(name = "Annual Bonus/13th Salary\n年终奖/13薪")
    private BigDecimal annualBonus;

    /** 免税不发项 */
    @Excel(name = "免税不发项")
    private BigDecimal taxFreeItem;

    /** 社保补差-个人_代缴 */
    @Excel(name = "社保补差-个人_代缴")
    private BigDecimal sbAdjEeAct;

    /** 公积金补差-个人_代缴 */
    @Excel(name = "公积金补差-个人_代缴")
    private BigDecimal phfAdjEeAct;

    /** 社保补差-公司_代缴 */
    @Excel(name = "社保补差-公司_代缴")
    private BigDecimal sbAdjComAct;

    /** 公积金补差-公司_代缴 */
    @Excel(name = "公积金补差-公司_代缴")
    private BigDecimal phfAdjComAct;

    /** 残保金补差-公司_代缴 */
    @Excel(name = "残保金补差-公司_代缴")
    private BigDecimal cdAdjComAct;

    private String payrollBelong;

    public String getPayrollBelong() {
        return payrollBelong;
    }

    public void setPayrollBelong(String payrollBelong) {
        this.payrollBelong = payrollBelong;
    }

    public BigDecimal getCdAdjCom() {
        return cdAdjCom;
    }
    public void setCdAdjCom(BigDecimal cdAdjCom) {
        this.cdAdjCom = cdAdjCom;
    }

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

    public String getErId() { return erId; }
    public void setErId(String erId) { this.erId = erId; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getExchangeRate() { return exchangeRate; }
    public void setExchangeRate(String exchangeRate) { this.exchangeRate = exchangeRate; }

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

    public String getSocialBenefitsAddress() {
        return socialBenefitsAddress;
    }
    public void setSocialBenefitsAddress(String socialBenefitsAddress) {
        this.socialBenefitsAddress = socialBenefitsAddress;
    }

    public void setNetIncome(BigDecimal netIncome)
    {
        this.netIncome = netIncome;
    }
    public BigDecimal getNetIncome() 
    {
        return netIncome;
    }

    public void setExpense(BigDecimal expense) 
    {
        this.expense = expense;
    }
    public BigDecimal getExpense() 
    {
        return expense;
    }

    public void setForeignerAllowance(BigDecimal foreignerAllowance) 
    {
        this.foreignerAllowance = foreignerAllowance;
    }
    public BigDecimal getForeignerAllowance() 
    {
        return foreignerAllowance;
    }

    public void setIpPension(BigDecimal ipPension) 
    {
        this.ipPension = ipPension;
    }
    public BigDecimal getIpPension() 
    {
        return ipPension;
    }

    public void setIpMedical(BigDecimal ipMedical) 
    {
        this.ipMedical = ipMedical;
    }
    public BigDecimal getIpMedical() 
    {
        return ipMedical;
    }

    public void setIpUnemployment(BigDecimal ipUnemployment) 
    {
        this.ipUnemployment = ipUnemployment;
    }
    public BigDecimal getIpUnemployment() 
    {
        return ipUnemployment;
    }

    public void setIpHousingFund(BigDecimal ipHousingFund) 
    {
        this.ipHousingFund = ipHousingFund;
    }
    public BigDecimal getIpHousingFund() 
    {
        return ipHousingFund;
    }

    public void setIpAnnuity(BigDecimal ipAnnuity) 
    {
        this.ipAnnuity = ipAnnuity;
    }
    public BigDecimal getIpAnnuity() 
    {
        return ipAnnuity;
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

    public void setIpUnionFee(BigDecimal ipUnionFee) 
    {
        this.ipUnionFee = ipUnionFee;
    }
    public BigDecimal getIpUnionFee() 
    {
        return ipUnionFee;
    }

    public void setIIT(BigDecimal IIT) 
    {
        this.IIT = IIT;
    }
    public BigDecimal getIIT() 
    {
        return IIT;
    }

    public BigDecimal getSalaryIIT() {
        return salaryIIT;
    }
    public void setSalaryIIT(BigDecimal salaryIIT) {
        this.salaryIIT = salaryIIT;
    }

    public BigDecimal getAnnualBonusIIT() {
        return annualBonusIIT;
    }
    public void setAnnualBonusIIT(BigDecimal annualBonusIIT) {
        this.annualBonusIIT = annualBonusIIT;
    }

    public void setEpPension(BigDecimal epPension)
    {
        this.epPension = epPension;
    }
    public BigDecimal getEpPension() 
    {
        return epPension;
    }

    public void setEpMedical(BigDecimal epMedical) 
    {
        this.epMedical = epMedical;
    }
    public BigDecimal getEpMedical() 
    {
        return epMedical;
    }

    public void setEpUnemployment(BigDecimal epUnemployment) 
    {
        this.epUnemployment = epUnemployment;
    }
    public BigDecimal getEpUnemployment() 
    {
        return epUnemployment;
    }

    public void setMaternity(BigDecimal maternity) 
    {
        this.maternity = maternity;
    }
    public BigDecimal getMaternity() 
    {
        return maternity;
    }

    public void setWorkRelatedInjury(BigDecimal workRelatedInjury) 
    {
        this.workRelatedInjury = workRelatedInjury;
    }
    public BigDecimal getWorkRelatedInjury() 
    {
        return workRelatedInjury;
    }

    public void setDisability(BigDecimal disability) 
    {
        this.disability = disability;
    }
    public BigDecimal getDisability() 
    {
        return disability;
    }

    public void setEpHousingFund(BigDecimal epHousingFund) 
    {
        this.epHousingFund = epHousingFund;
    }
    public BigDecimal getEpHousingFund() 
    {
        return epHousingFund;
    }

    public void setEpHeatingFee(BigDecimal epHeatingFee) 
    {
        this.epHeatingFee = epHeatingFee;
    }
    public BigDecimal getEpHeatingFee() 
    {
        return epHeatingFee;
    }

    public void setSbAdjCom(BigDecimal sbAdjCom)
    {
        this.sbAdjCom = sbAdjCom;
    }
    public BigDecimal getSbAdjCom()
    {
        return sbAdjCom;
    }

    public void setPhfAdjCom(BigDecimal phfAdjCom)
    {
        this.phfAdjCom = phfAdjCom;
    }
    public BigDecimal getPhfAdjCom()
    {
        return phfAdjCom;
    }

    public void setEpAnnuity(BigDecimal epAnnuity) 
    {
        this.epAnnuity = epAnnuity;
    }
    public BigDecimal getEpAnnuity() 
    {
        return epAnnuity;
    }

    public void setEpUnionFee(BigDecimal epUnionFee) 
    {
        this.epUnionFee = epUnionFee;
    }
    public BigDecimal getEpUnionFee() 
    {
        return epUnionFee;
    }

    public void setCommercialInsurance(BigDecimal commercialInsurance) {
        this.commercialInsurance = commercialInsurance;
    }
    public BigDecimal getCommercialInsurance() 
    {
        return commercialInsurance;
    }

    public void setBusinessTax(BigDecimal businessTax) 
    {
        this.businessTax = businessTax;
    }
    public BigDecimal getBusinessTax() 
    {
        return businessTax;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }
    public BigDecimal getDeposit() 
    {
        return deposit;
    }

    public void setEmployerLiability(BigDecimal employerLiability) 
    {
        this.employerLiability = employerLiability;
    }
    public BigDecimal getEmployerLiability() 
    {
        return employerLiability;
    }

    public void setOfficeSeat(BigDecimal officeSeat) 
    {
        this.officeSeat = officeSeat;
    }
    public BigDecimal getOfficeSeat() 
    {
        return officeSeat;
    }

    public void setServiceFee(BigDecimal serviceFee) 
    {
        this.serviceFee = serviceFee;
    }
    public BigDecimal getServiceFeeTax() { return serviceFeeTax; }

    public void setServiceFeeTax(BigDecimal serviceFeeTax) { this.serviceFeeTax = serviceFeeTax; }
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

    public void setTotalCost(BigDecimal totalCost) 
    {
        this.totalCost = totalCost;
    }
    public BigDecimal getTotalCost() 
    {
        return totalCost;
    }

    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }
    public String getRemarks() 
    {
        return remarks;
    }

    public BigDecimal getPaySb() {
        return paySb;
    }
    public void setPaySb(BigDecimal paySb) {
        this.paySb = paySb;
    }

    public BigDecimal getPayHf() {
        return payHf;
    }
    public void setPayHf(BigDecimal payHf) {
        this.payHf = payHf;
    }

    public BigDecimal getPayIIt() {
        return payIIt;
    }
    public void setPayIIt(BigDecimal payIIt) {
        this.payIIt = payIIt;
    }

    public BigDecimal getNetSalary() {
        return netSalary;
    }
    public void setNetSalary(BigDecimal netSalary) {
        this.netSalary = netSalary;
    }

    public BigDecimal getGrossSalary() {
        return grossSalary;
    }
    public void setGrossSalary(BigDecimal grossSalary) {
        this.grossSalary = grossSalary;
    }

    public BigDecimal getSalaryBonus() {
        return salaryBonus;
    }
    public void setSalaryBonus(BigDecimal salaryBonus) {
        this.salaryBonus = salaryBonus;
    }

    public BigDecimal getTravelAllowance() {
        return travelAllowance;
    }
    public void setTravelAllowance(BigDecimal travelAllowance) {
        this.travelAllowance = travelAllowance;
    }

    public BigDecimal getAnnualBonus() {
        return annualBonus;
    }
    public void setAnnualBonus(BigDecimal annualBonus) {
        this.annualBonus = annualBonus;
    }

    public BigDecimal getTaxFreeItem() {
        return taxFreeItem;
    }
    public void setTaxFreeItem(BigDecimal taxFreeItem) {
        this.taxFreeItem = taxFreeItem;
    }

    public BigDecimal getSbAdjEeAct() {
        return sbAdjEeAct;
    }
    public void setSbAdjEeAct(BigDecimal sbAdjEeAct) {
        this.sbAdjEeAct = sbAdjEeAct;
    }

    public BigDecimal getPhfAdjEeAct() {
        return phfAdjEeAct;
    }
    public void setPhfAdjEeAct(BigDecimal phfAdjEeAct) {
        this.phfAdjEeAct = phfAdjEeAct;
    }

    public BigDecimal getSbAdjComAct() {
        return sbAdjComAct;
    }
    public void setSbAdjComAct(BigDecimal sbAdjComAct) {
        this.sbAdjComAct = sbAdjComAct;
    }

    public BigDecimal getPhfAdjComAct() {
        return phfAdjComAct;
    }
    public void setPhfAdjComAct(BigDecimal phfAdjComAct) {
        this.phfAdjComAct = phfAdjComAct;
    }

    public BigDecimal getCdAdjComAct() {
        return cdAdjComAct;
    }
    public void setCdAdjComAct(BigDecimal cdAdjComAct) {
        this.cdAdjComAct = cdAdjComAct;
    }

    public BigDecimal getOther() {
        return other;
    }

    public void setOther(BigDecimal other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("duration", getDuration())
            .append("erName", getErName())
            .append("erId", getErId())
            .append("currency", getCurrency())
            .append("exchangeRate", getExchangeRate())
            .append("department", getDepartment())
            .append("idNo", getIdNo())
            .append("name", getName())
            .append("netIncome", getNetIncome())
            .append("expense", getExpense())
            .append("foreignerAllowance", getForeignerAllowance())
            .append("ipPension", getIpPension())
            .append("ipMedical", getIpMedical())
            .append("ipUnemployment", getIpUnemployment())
            .append("ipHousingFund", getIpHousingFund())
            .append("ipAnnuity", getIpAnnuity())
            .append("sbAdjEe", getSbAdjEe())
            .append("phfAdjEe", getPhfAdjEe())
            .append("ipUnionFee", getIpUnionFee())
            .append("IIT", getIIT())
            .append("salaryIIT", getSalaryIIT())
            .append("annualBonusIIT", getAnnualBonusIIT())
            .append("epPension", getEpPension())
            .append("epMedical", getEpMedical())
            .append("epUnemployment", getEpUnemployment())
            .append("maternity", getMaternity())
            .append("workRelatedInjury", getWorkRelatedInjury())
            .append("disability", getDisability())
            .append("epHousingFund", getEpHousingFund())
            .append("epHeatingFee", getEpHeatingFee())
            .append("sbAdjCom", getSbAdjCom())
            .append("phfAdjCom", getPhfAdjCom())
            .append("epAnnuity", getEpAnnuity())
            .append("epUnionFee", getEpUnionFee())
            .append("commercialInsurance", getCommercialInsurance())
            .append("businessTax", getBusinessTax())
            .append("deposit", getDeposit())
            .append("employerLiability", getEmployerLiability())
            .append("officeSeat", getOfficeSeat())
            .append("serviceFee", getServiceFee())
            .append("serviceFeeTax", getServiceFeeTax())
            .append("valueAddedTax", getValueAddedTax())
            .append("totalCost", getTotalCost())
            .append("remarks", getRemarks())
            .append("paySb", getPaySb())
            .append("payHf", getPayHf())
            .append("payIIt", getPayIIt())
            .append("netSalary", getNetSalary())
            .append("grossSalary", getGrossSalary())
            .append("salaryBonus", getSalaryBonus())
            .append("travelAllowance", getTravelAllowance())
            .append("annualBonus", getAnnualBonus())
            .append("taxFreeItem", getTaxFreeItem())
            .append("sbAdjEeAct", getSbAdjEeAct())
            .append("phfAdjEeAct", getPhfAdjEeAct())
            .append("sbAdjComAct", getSbAdjComAct())
            .append("phfAdjComAct", getPhfAdjComAct())
            .append("cdAdjComAct", getCdAdjComAct())
            .toString();
    }
}
