package com.nnroad.finance.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nnroad.common.core.domain.BaseEntity;
import com.nnroad.common.utils.SecurityUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("hrone_account")
@EqualsAndHashCode(callSuper = false)
public class HroneAccount extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -51818777277502423L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    @ExcelIgnore
    private Long id;
    /**
     * 日期
     */
    @ExcelProperty("date")
    @DateTimeFormat("yyyy-MM-dd")
    @NotNull(message = "Date cannot be empty")
    private Date date;
    /**
     * 服务名称
     */
    @ExcelProperty("Service Name")
    private String serviceName;
    /**
     * 借贷类型
     */
    @ExcelProperty("Credit/Debit")
    private String checkType;
    /**
     * 客户姓名
     */
    @ExcelProperty("Beneficiary Name")
    private String clientName;
    /**
     * 客户代码
     */
    @ExcelProperty("Beneficiary Code")
    private String clientCode;
    /**
     * 所属期间
     */
    @ExcelProperty("For Period")
    @DateTimeFormat("yyyy-MM-dd")
    @NotNull(message = "For Period cannot be empty")
    private Date forPeriod;
    /**
     * 银行
     */
    @ExcelProperty("Bank")
    private String bank;
    /**
     * 银行账户
     */
    @ExcelProperty("Bank Account")
    private String bankAccount;
    /**
     * 所需货币
     */
    @ExcelProperty("Wanted Currency")
    private String wantedCurrency;
    /**
     * 银行内货币
     */
    @ExcelProperty("In-bank Currency")
    private String inBankCurrency;
    /**
     * 银行内金额
     */
    @ExcelProperty("In-bank Amount")
    private BigDecimal inBankAmount;
    /**
     * 工资
     */
    @ExcelProperty({"Payroll & EOR", "Salary & Other Payment to EE", "salary"})
    private BigDecimal salary;
    /**
     * 报销
     */
    @ExcelProperty({"Payroll & EOR", "Salary & Other Payment to EE", "Expense Claim"})
    private BigDecimal expenseClaim;
    /**
     * 工资的其他支付 RE
     */
    @ExcelProperty({"Payroll & EOR", "Salary & Other Payment to EE", "Other Payment"})
    private BigDecimal salaryOtherPayment;
    /**
     * 个税 RE
     */
    @ExcelProperty({"Payroll & EOR", "Mandatory Contribution", "IIT"})
    private BigDecimal payrollIit;
    /**
     * 社会保险 RE
     */
    @ExcelProperty({"Payroll & EOR", "Mandatory Contribution", "Social Insurance"})
    private BigDecimal payrollSocialInsurance;
    /**
     * 社会保险偿还
     */
    @ExcelProperty({"Payroll & EOR", "Mandatory Contribution", "Social Insurance Repayment"})
    private BigDecimal socialInsuranceRepayment;
    /**
     * 薪资公积金 RE
     */
    @ExcelProperty({"Payroll & EOR", "Mandatory Contribution", "Housing Fund"})
    private BigDecimal payrollHousingFund;
    /**
     * 公积金偿还
     */
    @ExcelProperty({"Payroll & EOR", "Mandatory Contribution", "Housing Fund Repayment"})
    private BigDecimal housingFundRepayment;
    /**
     * 残疾保险
     */
    @ExcelProperty({"Payroll & EOR", "Mandatory Contribution", "Disability Insurance"})
    private BigDecimal disabilityInsurance;
    /**
     * 残疾保险偿还
     */
    @ExcelProperty({"Payroll & EOR", "Mandatory Contribution", "Disability Insurance Repayment"})
    private BigDecimal disabilityInsuranceRepayment;
    /**
     * 其他费用 RE
     */
    @ExcelProperty({"Payroll & EOR", "Mandatory Contribution", "Other"})
    private BigDecimal payrollOther;
    /**
     * 商业保险
     */
    @ExcelProperty({"Payroll & EOR", "Other Payment for EE", "Commercial Insurance"})
    private BigDecimal commercialInsurance;
    /**
     * 健康检查
     */
    @ExcelProperty({"Payroll & EOR", "Other Payment for EE", "Health Check"})
    private BigDecimal healthCheck;
    /**
     * 工会费
     */
    @ExcelProperty({"Payroll & EOR", "Other Payment for EE", "Union Fee"})
    private BigDecimal unionFee;
    /**
     * 雇主责任
     */
    @ExcelProperty({"Payroll & EOR", "Other Payment for EE", "Employer Liability"})
    private BigDecimal employerLiability;
    /**
     * 其他支付 RE
     */
    @ExcelProperty(value = {"Payroll & EOR", "Other payment", "Other Payment1"})
    private BigDecimal otherPayment;
    /**
     * 报销税金
     */
    @ExcelProperty({"Payroll & EOR", "Other payment", "tax for expense"})
    private BigDecimal taxForExpense;
    /**
     * 存款
     */
    @ExcelProperty({"Payroll & EOR", "Deposit", "deposit"})
    private BigDecimal deposit;
    /**
     * 预付款
     */
    @ExcelProperty({"Payroll & EOR", "Deposit", "Advance Payment"})
    private BigDecimal advancePayment;
    /**
     * 薪资财务服务费
     */
    @ExcelProperty({"Payroll & EOR", "Recurring fee", "Payroll service fee"})
    private BigDecimal payrollServiceFee;
    /**
     * EOR服务费
     */
    @ExcelProperty({"Payroll & EOR", "Recurring fee", "EOR service fee"})
    private BigDecimal eorServiceFee;
    /**
     * 循环费用的税金
     */
    @ExcelProperty({"Payroll & EOR", "Recurring fee", "Tax for Recurring fee"})
    private BigDecimal taxForRecurringFee;
    /**
     * 商业保险服务费
     */
    @ExcelProperty({"Other service", "Commercial Insuance Service fee"})
    private BigDecimal commercialInsuanceServiceFee;
    /**
     * 签证户口和居住许可
     */
    @ExcelProperty({"Other service", "Visa/Hukou Residence Permit"})
    private BigDecimal visahukouResidencePermit;
    /**
     * 报销服务费
     */
    @ExcelProperty({"Other service", "Expense claim service fee"})
    private BigDecimal expenseClaimServiceFee;
    /**
     * 背景调查
     */
    @ExcelProperty({"Other service", "Reference Check"})
    private BigDecimal referenceCheck;
    /**
     * 公司注册
     */
    @ExcelProperty({"Other service", "Company formation"})
    private BigDecimal companyFormation;
    /**
     * 开户费用
     */
    @ExcelProperty({"Other service", "Open A/C"})
    private BigDecimal openAc;
    /**
     * 办公空间 RE
     */
    @ExcelProperty({"Other service", "Office Space"})
    private BigDecimal otherServiceOfficeSpace;
    /**
     * 在线政策书
     */
    @ExcelProperty({"Other service", "Online Policy/Book"})
    private BigDecimal onlinePolicybook;
    /**
     * 其他服务费用
     */
    @ExcelProperty({"Other service", "Other service fee"})
    private BigDecimal otherServiceFee;
    /**
     * 其他服务费用的税金
     */
    @ExcelProperty({"Other service", "Tax for other Service Fee"})
    private BigDecimal taxForOtherServiceFee;
    /**
     * 服务费
     */
    @ExcelProperty({"Recruitment", "service fee"})
    private BigDecimal serviceFee;
    /**
     * 税费
     */
    @ExcelProperty({"Recruitment", "taxes"})
    private BigDecimal taxes;
    /**
     * 招聘相关其他费用
     */
    @ExcelProperty({"Recruitment", "Recruitment other"})
    private BigDecimal recruitmentOther;
    /**
     * 延迟费
     */
    @ExcelProperty({"Other Revenue", "Delay fee"})
    private BigDecimal delayFee;
    /**
     * 货币兑换
     */
    @ExcelProperty({"Other Revenue", "Currency Exchange"})
    private BigDecimal currencyExchange;
    /**
     * 余额
     */
    @ExcelProperty({"Other Revenue", "Balance"})
    private BigDecimal balance;
    /**
     * 利息收入
     */
    @ExcelProperty({"Other Revenue", "Interests Income"})
    private BigDecimal interestsIncome;

    /**
     * 工资和其他费用
     */
    @ExcelProperty({"Expense", "Salary And Ohter"})
    private BigDecimal salaryAndOhter;
    /**
     * 报销个税 RE
     */
    @ExcelProperty({"Expense", "IIT1"})
    private BigDecimal expenseIit;
    /**
     * 社会保险费用 RE
     */
    @ExcelProperty({"Expense", "Social Insurance1"})
    private BigDecimal expenseSocialInsurance;
    /**
     * 公积金 RE
     */
    @ExcelProperty({"Expense", "Housing Fund1"})
    private BigDecimal expenseHousingFund;
    /**
     * 银行费用
     */
    @ExcelProperty({"Expense", "Bank fee"})
    private BigDecimal bankFee;
    /**
     * 市场业务开发
     */
    @ExcelProperty({"Expense", "Marketing/BD"})
    private BigDecimal marketingBd;
    /**
     * 办公用品
     */
    @ExcelProperty({"Expense", "Office Supplies"})
    private BigDecimal officeSupplies;
    /**
     * 邮寄费用
     */
    @ExcelProperty({"Expense", "Mailing"})
    private BigDecimal mailing;
    /**
     * 办公空间费用 RE
     */
    @ExcelProperty({"Expense", "Office space1"})
    private BigDecimal expenseOfficeSpace;
    /**
     * 电费
     */
    @ExcelProperty({"Expense", "Electricity"})
    private BigDecimal electricity;
    /**
     * 电话费用
     */
    @ExcelProperty({"Expense", "phoneCalls"})
    private BigDecimal phoneCalls;
    /**
     * 出差本地出租车费用
     */
    @ExcelProperty({"Expense", "Business Trip Local Taxi"})
    private BigDecimal businessTripLocalTaxi;
    /**
     * IT维护费用
     */
    @ExcelProperty({"Expense", "IT maintenance"})
    private BigDecimal itMaintenance;
    /**
     * 加班申请
     */
    @ExcelProperty({"Expense", "OT Claim"})
    private BigDecimal otClaim;
    /**
     * 税金
     */
    @ExcelProperty({"Expense", "Tax"})
    private BigDecimal tax;
    /**
     * 银行转账
     */
    @ExcelProperty({"Expense", "Bank Transfer"})
    private BigDecimal bankTransfer;
    /**
     * 其他 RE
     */
    @ExcelProperty({"Expense", "Other1"})
    private BigDecimal other;
    /**
     * 固定备注
     */
    @ExcelProperty({"Expense", "Fixed Remark"})
    private String fixedRemark;
    /**
     * 备注1
     */
    @ExcelProperty({"Expense", "Remark"})
    private String remark1;
    /**
     * 人数
     */
    @ExcelProperty({"Expense", "Number of people"})
    private BigDecimal numberOfPeople;

    /**
     * 状态
     */
    @ExcelIgnore
    private Integer status;
    /**
     * 排序键
     */
    @ExcelIgnore
    private BigDecimal sortKey;

    public void initAuthor(boolean update) {
        String username = SecurityUtils.getUsername();
        Date now = new Date();
        if (!update) {
            this.setCreateBy(username);
            this.setCreateTime(now);
        }
        this.setUpdateBy(username);
        this.setUpdateTime(now);
    }
}
