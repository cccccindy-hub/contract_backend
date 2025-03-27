package com.nnroad.finance.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("hrone_account_vendor_import")
public class HroneAccountVendorImport implements Serializable {
    private static final long serialVersionUID = -48228303829527683L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 日期
     */
    private Date date;

    /**
     * 商业保险服务费
     */
    private BigDecimal commercialInsuanceServiceFee;

    /**
     * 服务费
     */
    private BigDecimal serviceFee;

    /**
     * 延迟费
     */
    private BigDecimal delayFee;

    /**
     * 工资和其他费用
     */
    private BigDecimal salaryAndOhter;

    /**
     * 工资
     */
    private BigDecimal salary;

    /**
     * 个税
     */
    private BigDecimal payrollIit;

    /**
     * 商业保险
     */
    private BigDecimal commercialInsurance;

    /**
     * 其他支付
     */
    private BigDecimal otherPayment;

    /**
     * 存款
     */
    private BigDecimal deposit;

    /**
     * 薪资财务服务费
     */
    private BigDecimal payrollServiceFee;

    /**
     * 社会保险
     */
    private BigDecimal payrollSocialInsurance;

    /**
     * 健康检查
     */
    private BigDecimal healthCheck;

    /**
     * 报销
     */
    private BigDecimal expenseClaim;

    /**
     * 报销个税
     */
    private BigDecimal expenseIit;

    /**
     * 报销税金
     */
    private BigDecimal taxForExpense;

    /**
     * 货币兑换
     */
    private BigDecimal currencyExchange;

    /**
     * 税费
     */
    private BigDecimal taxes;

    /**
     * EOR服务费
     */
    private BigDecimal eorServiceFee;

    /**
     * 签证户口和居住许可
     */
    private BigDecimal visahukouResidencePermit;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 社会保险费用
     */
    private BigDecimal expenseSocialInsurance;

    /**
     * 报销服务费
     */
    private BigDecimal expenseClaimServiceFee;

    /**
     * 借贷类型
     */
    private String checkType;

    /**
     * 社会保险偿还
     */
    private BigDecimal socialInsuranceRepayment;

    /**
     * 工资的其他支付
     */
    private BigDecimal salaryOtherPayment;

    /**
     * 工会费
     */
    private BigDecimal unionFee;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 招聘相关其他费用
     */
    private BigDecimal recruitmentOther;

    /**
     * 循环费用的税金
     */
    private BigDecimal taxForRecurringFee;

    /**
     * 客户姓名
     */
    private String clientName;

    /**
     * 公积金
     */
    private BigDecimal expenseHousingFund;

    /**
     * 预付款
     */
    private BigDecimal advancePayment;

    /**
     * 薪资公积金
     */
    private BigDecimal payrollHousingFund;

    /**
     * 雇主责任
     */
    private BigDecimal employerLiability;

    /**
     * 背景调查
     */
    private BigDecimal referenceCheck;

    /**
     * 银行费用
     */
    private BigDecimal bankFee;

    /**
     * 客户代码
     */
    private String clientCode;

    /**
     * 利息收入
     */
    private BigDecimal interestsIncome;

    /**
     * 公积金偿还
     */
    private BigDecimal housingFundRepayment;

    /**
     * 公司注册
     */
    private BigDecimal companyFormation;

    /**
     * 所属期间
     */
    private Date forPeriod;

    /**
     * 残疾保险
     */
    private BigDecimal disabilityInsurance;

    /**
     * 开户费用
     */
    private BigDecimal openAc;

    /**
     * 市场业务开发
     */
    private BigDecimal marketingBd;

    /**
     * 办公空间
     */
    private BigDecimal otherServiceOfficeSpace;

    /**
     * 办公用品
     */
    private BigDecimal officeSupplies;

    /**
     * 银行
     */
    private String bank;

    /**
     * 残疾保险偿还
     */
    private BigDecimal disabilityInsuranceRepayment;

    /**
     * 银行账户
     */
    private String bankAccount;

    /**
     * 在线政策书
     */
    private BigDecimal onlinePolicybook;

    /**
     * 其他费用
     */
    private BigDecimal payrollOther;

    /**
     * 邮寄费用
     */
    private BigDecimal mailing;

    /**
     * 所需货币
     */
    private String wantedCurrency;

    /**
     * 其他服务费用
     */
    private BigDecimal otherServiceFee;

    /**
     * 办公空间费用
     */
    private BigDecimal expenseOfficeSpace;

    /**
     * 其他服务费用的税金
     */
    private BigDecimal taxForOtherServiceFee;
    /**
     * 银行内货币
     */
    private String inBankCurrency;
    /**
     * 电费
     */
    private BigDecimal electricity;
    /**
     * 电话费用
     */
    private BigDecimal phoneCalls;
    /**
     * 银行内金额
     */
    private BigDecimal inBankAmount;
    /**
     * 出差本地出租车费用
     */
    private BigDecimal businessTripLocalTaxi;
    /**
     * IT维护费用
     */
    private BigDecimal itMaintenance;
    /**
     * 加班申请
     */
    private BigDecimal otClaim;
    /**
     * 税金
     */
    private BigDecimal tax;
    /**
     * 银行转账
     */
    private BigDecimal bankTransfer;
    /**
     * 其他
     */
    private BigDecimal other;
    /**
     * 固定备注
     */
    private String fixedRemark;
    /**
     * 备注1
     */
    private String remark1;
    /**
     * 人数
     */
    private BigDecimal numberOfPeople;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 排序键
     */
    private BigDecimal sortKey;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
}