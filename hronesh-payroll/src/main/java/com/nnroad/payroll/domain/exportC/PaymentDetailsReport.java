package com.nnroad.payroll.domain.exportC;

import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * payment_details_report对象 payment_details_report
 */
public class PaymentDetailsReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Payroll belong */
    @Excel(name = "发薪所属")
    private String payrollBelong;

    /** id no */
    @Excel(name = "HROne编号")
    private String hroneNo;

    /** Bank Account */
    @Excel(name = "银行账号")
    private String bankAccount;

    /** Bank account name */
    @Excel(name = "工资卡姓名")
    private String bankAccounName;

    /** bank */
    @Excel(name = "开户行")
    private String bank;

    /** Location of bank */
    @Excel(name = "开户行所在地")
    private String bankLocation;

    /**  */
    @Excel(name = "实发工资")
    private BigDecimal netIncome;

    /**  */
    @Excel(name = "报销款合计")
    private BigDecimal expense;

    /** Subsistence Allowance for Foreigners */
    @Excel(name = "外籍生活津贴")
    private BigDecimal fsAllowance;

    /** Employer  Name */
    @Excel(name = "客户名称")
    private String erName;

    /** Duration */
    @Excel(name = "期间月份")
    private String duration;

    /** client  type */
    @Excel(name = "客户类型")
    private String clientType;

    /** 发薪日 */
    @Excel(name = "发薪日")
    private String payDay;

    private String[] payDayList;
    public String[] getPayDayList() { return payDayList; }
    public void setPayDayList(String[] payDayList) { this.payDayList = payDayList; }

    public void setPayrollBelong(String payrollBelong)
    {
        this.payrollBelong = payrollBelong;
    }

    public String getPayrollBelong()
    {
        return payrollBelong;
    }
    public void setHroneNo(String hroneNo)
    {
        this.hroneNo = hroneNo;
    }

    public String getHroneNo()
    {
        return hroneNo;
    }
    public void setBankAccount(String bankAccount)
    {
        this.bankAccount = bankAccount;
    }

    public String getBankAccount()
    {
        return bankAccount;
    }
    public void setBankAccounName(String bankAccounName)
    {
        this.bankAccounName = bankAccounName;
    }

    public String getBankAccounName()
    {
        return bankAccounName;
    }
    public void setBank(String bank)
    {
        this.bank = bank;
    }

    public String getBank()
    {
        return bank;
    }
    public void setBankLocation(String bankLocation)
    {
        this.bankLocation = bankLocation;
    }

    public String getBankLocation()
    {
        return bankLocation;
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
    public void setFsAllowance(BigDecimal fsAllowance)
    {
        this.fsAllowance = fsAllowance;
    }

    public BigDecimal getFsAllowance()
    {
        return fsAllowance;
    }
    public void setErName(String erName)
    {
        this.erName = erName;
    }

    public String getErName()
    {
        return erName;
    }
    public void setDuration(String duration)
    {
        this.duration = duration;
    }

    public String getDuration()
    {
        return duration;
    }
    public void setClientType(String clientType)
    {
        this.clientType = clientType;
    }

    public String getClientType()
    {
        return clientType;
    }

    public String getPayDay() {
        return payDay;
    }
    public void setPayDay(String payDay) {
        this.payDay = payDay;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("payrollBelong", getPayrollBelong())
                .append("hroneNo", getHroneNo())
                .append("bankAccount", getBankAccount())
                .append("bankAccounName", getBankAccounName())
                .append("bank", getBank())
                .append("bankLocation", getBankLocation())
                .append("netIncome", getNetIncome())
                .append("expense", getExpense())
                .append("fsAllowance", getFsAllowance())
                .append("erName", getErName())
                .append("duration", getDuration())
                .append("clientType", getClientType())
                .append("payDay", getPayDay())
                .toString();
    }
}
