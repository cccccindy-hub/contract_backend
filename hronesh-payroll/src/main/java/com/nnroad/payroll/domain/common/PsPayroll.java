package com.nnroad.payroll.domain.common;

import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * ps_payroll对象 ps_payroll
 *
 * @author Hrone
 * @date 2021-01-21
 */
public class PsPayroll extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** payrollID */
    private Long id;

    private String clientCode;

    private String clientName;

    /** 部门 */
    @Excel(name = "Department 部门")
    private String department;

    /** 期间 */
    private String duration;

    /** 工号 */
    @Excel(name = "ID No. 工号")
    private String idNo;

    /** 姓名 */
    @Excel(name = "Name 姓名")
    private String name;

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
    @Excel(name = "Pension\n养老")
    private BigDecimal ipPension;

    /** 个人医疗 */
    @Excel(name = "Medical\n医疗")
    private BigDecimal ipMedical;

    /** 个人失业 */
    @Excel(name = "Unemployment\n失业")
    private BigDecimal ipUnemployment;

    /** 个人公积金 */
    @Excel(name = "Housing Fund\n公积金")
    private BigDecimal ipHousingFund;

    /** 个人年金 */
    @Excel(name = "Annuity\n年金")
    private BigDecimal ipAnnuity;

    /** 个人工会费 */
    @Excel(name = "Union Fee\n工会费")
    private BigDecimal ipUnionFee;

    /** 个税 */
    @Excel(name = "IIT\n个税")
    private BigDecimal IIT;

    /** 代缴费用补差 */
    @Excel(name = "Balance For Charge On Behalf\n代缴费用补差")
    private BigDecimal balanceCharge;

    /** 养老 */
    @Excel(name = "Pension\n养老")
    private BigDecimal epPension;

    /** 医疗 */
    @Excel(name = "Medical\n医疗")
    private BigDecimal epMedical;

    /** 失业 */
    @Excel(name = "Unemployment\n失业")
    private BigDecimal epUnemployment;

    /** 生育 */
    @Excel(name = "Maternity\n生育")
    private BigDecimal maternity;

    /** 工伤 */
    @Excel(name = "Work-Related-Injury\n工伤")
    private BigDecimal workRelatedInjury;

    /** 残保金 */
    @Excel(name = "Disability\n残保金")
    private BigDecimal disability;

    /** 公积金 */
    @Excel(name = "Housing Fund\n公积金")
    private BigDecimal epHousingFund;

    /** 年金 */
    @Excel(name = "Annuity\n年金")
    private BigDecimal epAnnuity;

    /** 工会费 */
    @Excel(name = "Union Fee\n工会费")
    private BigDecimal epUnionFee;

    /** 商业保险 */
    @Excel(name = "Commercial Insurance\n商业保险")
    private BigDecimal commercialInsurance;

    /** 其他福利 */
    @Excel(name = "Other Benefits\n其他福利")
    private BigDecimal otherBenefits;

    /** 服务费 */
    @Excel(name = "Service Fee\n服务费")
    private BigDecimal serviceFee;

    /** 报销款税额 */
    @Excel(name = "Business Tax for Expense\n报销款税额")
    private BigDecimal businessTax;

    /** 押金 */
    @Excel(name = "Deposit\n押金")
    private BigDecimal deposit;

    /** 雇主责任险 */
    @Excel(name = "Employer Liability Insurance\n雇主责任险")
    private BigDecimal employerLiability;

    /** 座位费 */
    @Excel(name = "Office Seat\n其他")
    private BigDecimal officeSeat;

    /** 其他 */
    @Excel(name = "Others\n其他")
    private BigDecimal others;

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

    /** 取暖费 */
    @Excel(name = "Heating Fee\n取暖费")
    private BigDecimal epHeatingFee;

    /** 免税不发项 */
    @Excel(name = "免税不发项")
    private BigDecimal taxFreeItem;


    private String groupIds;

    private String payrollSource;

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
    public void setBalanceCharge(BigDecimal balanceCharge) 
    {
        this.balanceCharge = balanceCharge;
    }

    public BigDecimal getBalanceCharge() 
    {
        return balanceCharge;
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
    public void setCommercialInsurance(BigDecimal commercialInsurance) 
    {
        this.commercialInsurance = commercialInsurance;
    }

    public BigDecimal getCommercialInsurance() 
    {
        return commercialInsurance;
    }
    public void setOtherBenefits(BigDecimal otherBenefits) 
    {
        this.otherBenefits = otherBenefits;
    }

    public BigDecimal getOtherBenefits() 
    {
        return otherBenefits;
    }
    public void setServiceFee(BigDecimal serviceFee) 
    {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getServiceFee() 
    {
        return serviceFee;
    }
    public void setBusinessTax(BigDecimal businessTax) 
    {
        this.businessTax = businessTax;
    }

    public BigDecimal getBusinessTax() 
    {
        return businessTax;
    }
    public void setDeposit(BigDecimal deposit) 
    {
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

    public BigDecimal getOthers() {
        return others;
    }
    public void setOthers(BigDecimal others) {
        this.others = others;
    }

    public BigDecimal getServiceFeeTax() { return serviceFeeTax; }
    public void setServiceFeeTax(BigDecimal serviceFeeTax) { this.serviceFeeTax = serviceFeeTax; }

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

    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public BigDecimal getEpHeatingFee() {
        return epHeatingFee;
    }
    public void setEpHeatingFee(BigDecimal epHeatingFee) {
        this.epHeatingFee = epHeatingFee;
    }

    public BigDecimal getTaxFreeItem() {
        return taxFreeItem;
    }
    public void setTaxFreeItem(BigDecimal taxFreeItem) {
        this.taxFreeItem = taxFreeItem;
    }

    public String getGroupIds() {
        return groupIds;
    }
    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public String getPayrollSource() {
        return payrollSource;
    }

    public void setPayrollSource(String payrollSource) {
        this.payrollSource = payrollSource;
    }

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("department", getDepartment())
            .append("duration", getDuration())
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
            .append("ipUnionFee", getIpUnionFee())
            .append("IIT", getIIT())
            .append("balanceCharge", getBalanceCharge())
            .append("epPension", getEpPension())
            .append("epMedical", getEpMedical())
            .append("epUnemployment", getEpUnemployment())
            .append("maternity", getMaternity())
            .append("workRelatedInjury", getWorkRelatedInjury())
            .append("disability", getDisability())
            .append("epHousingFund", getEpHousingFund())
            .append("epAnnuity", getEpAnnuity())
            .append("epUnionFee", getEpUnionFee())
            .append("commercialInsurance", getCommercialInsurance())
            .append("otherBenefits", getOtherBenefits())
            .append("serviceFee", getServiceFee())
            .append("businessTax", getBusinessTax())
            .append("deposit", getDeposit())
            .append("employerLiability", getEmployerLiability())
            .append("officeSeat", getOfficeSeat())
            .append("others", getOthers())
            .append("serviceFeeTax", getServiceFeeTax())
            .append("valueAddedTax", getValueAddedTax())
            .append("totalCost", getTotalCost())
            .append("remarks", getRemarks())
            .append("epHeatingFee", getEpHeatingFee())
            .append("taxFreeItem", getTaxFreeItem())
            .toString();
    }
}
