package com.nnroad.vendor.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nnroad.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("vendor_bill")
public class VendorBill extends BaseEntity {

    // 主键ID
    @TableId(type = IdType.AUTO)
    private Long id;

    // 供应商名称
    @NotBlank(message = "供应商名称不能为空")
    @ExcelProperty("供应商名称")
    private String vendorName;

    // 客户代码
    @ExcelProperty("客户编号")
    @NotBlank(message = "客户不能为空")
    private String clientCode;

    // 客户名称
    @NotBlank(message = "客户不能为空")
    @ExcelProperty("客户简称")
    private String clientName;

    // 薪资期间
    @NotBlank(message = "薪资期间不能为空")
    @ExcelProperty("期间")
    private String forPeriod;

    // 工作地点
    @ExcelProperty("工作地")
    private String workplace;

    // 员工姓名
    @ExcelProperty("姓名")
    private String eeName;

    // 员工代码
    @ExcelProperty("员工编码")
    private String eeCode;

    // 身份证号码
    @ExcelProperty("身份证号码")
    private String idNumber;

    // 基本工资
    @ExcelProperty("基本工资")
    private BigDecimal baseSalary;

    // 奖金/津贴
    @ExcelProperty("奖金/津贴")
    private BigDecimal bonus;

    // 13薪/年终奖
    @ExcelProperty("13薪/年终奖")
    private BigDecimal yearEndBonus;

    // 加班费
    @ExcelProperty("加班费")
    private BigDecimal otPayment;

    // 入离职缺勤扣
    @ExcelProperty("入离职缺勤扣")
    private BigDecimal absence;

    // 补发
    @ExcelProperty("补发")
    private BigDecimal reissue;

    // 补扣
    @ExcelProperty("补扣")
    private BigDecimal deduction;

    // 公司付税项
    @ExcelProperty("公司付税项")
    private BigDecimal clientTaxPaid;

    // 离职补偿金
    @ExcelProperty("离职补偿金")
    private BigDecimal resignationCompensation;

    // 实发
    @ExcelProperty("员工实得")
    private BigDecimal netIncome;

    //商业保险（计税不发）
    @ExcelProperty("商业保险（计税不发）")
    private BigDecimal commercialInsurance;

    // 报销
    @ExcelProperty("报销款")
    private BigDecimal expense;

    @ExcelProperty({"个人部分", "养老"})
    private BigDecimal eePension;

    // 员工医疗金额
    @ExcelProperty({"个人部分", "医疗"})
    private BigDecimal eeMedical;

    // 员工失业金额
    @ExcelProperty({"个人部分", "失业"})
    private BigDecimal eeUnemployment;

    // 员工公积金金额
    @ExcelProperty({"个人部分", "公积金"})
    private BigDecimal eeHousingFund;

    // 个税
    @ExcelProperty({"个人部分", "个税"})
    @TableField("IIT")
    private BigDecimal iit;

    // 社保补缴员工金额
    @ExcelProperty({"个人部分", "社保补缴"})
    private BigDecimal eeSocialSecuritySupplement;

    // 公积金补缴员工金额
    @ExcelProperty({"个人部分", "公积金补缴"})
    private BigDecimal eeHousingFundSupplement;

    // 养老公司金额
    @ExcelProperty({"雇主部分", "养老"})
    private BigDecimal erPension;

    // 医疗公司金额
    @ExcelProperty({"雇主部分", "医疗"})
    private BigDecimal erMedical;

    // 失业公司金额
    @ExcelProperty({"雇主部分", "失业"})
    private BigDecimal erUnemployment;

    // 生育公司金额
    @ExcelProperty({"雇主部分", "生育"})
    private BigDecimal maternity;

    // 工伤公司金额
    @ExcelProperty({"雇主部分", "工伤"})
    private BigDecimal workRelatedInjury;

    // 残保金
    @ExcelProperty({"雇主部分", "残保金"})
    private BigDecimal disability;

    // 公积金公司金额
    @ExcelProperty({"雇主部分", "公积金"})
    private BigDecimal erHousingFund;

    // 社保补缴公司金额
    @ExcelProperty({"雇主部分", "社保补缴"})
    private BigDecimal erSocialSecuritySupplement;

    // 公积金补缴公司金额
    @ExcelProperty({"雇主部分", "公积金补缴"})
    private BigDecimal erHousingFundSupplement;

    // 服务费
    @ExcelProperty({"雇主部分", "服务费"})
    private BigDecimal serviceFee;

    // 税费
    @ExcelProperty({"雇主部分", "服务费税"})
    private BigDecimal taxFee;

    // 报销税额
    @ExcelProperty({"雇主部分", "报销款税额"})
    private BigDecimal expenseTaxFee;

    // 雇主责任险
    @ExcelProperty({"雇主部分", "雇主责任险"})
    private BigDecimal employerLiability;

    // 其他
    @ExcelProperty({"雇主部分", "其他"})
    private BigDecimal other;

    // 总成本
    @ExcelProperty("总成本")
    private BigDecimal totalCost;

    // 备注
    @ExcelProperty("备注")
    private String remark1;
}