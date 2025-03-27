package com.nnroad.employee.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;

/**
 * VIEW对象 v_employee_info
 *
 * @author ruoyi
 * @date 2025-01-13
 */
public class VEmployeeInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 员工编号 */
    @Excel(name = "员工编号")
    private String eeCode;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String eeName;

    /** 客户编号 */
    @Excel(name = "客户编号")
    private String clientCode;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String clientName;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idNumber;

    /** Email */
    @Excel(name = "Email")
    private String email;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String mobile;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String idType;

    /** 银行账号 */
    @Excel(name = "银行账号")
    private String bankAccountNumber;

    /** 支行 */
    @Excel(name = "支行")
    private String bankOfAccountSubbranch;

    /** 账号持有人 */
    @Excel(name = "账号持有人")
    private String accountHolder;

    /** 生日 */
    @Excel(name = "生日")
    private String birthday;

    /** 社保城市 */
    @Excel(name = "社保城市")
    private String socialSecurityCity;

    /** 社保开始月份 */
    @Excel(name = "社保开始月份")
    private String socialSecurityStartingMonth;

    /** 入职日期 */
    @Excel(name = "入职日期")
    private String entryDate;

    /** 税前/税后 */
    @Excel(name = "税前/税后")
    private String salaryBeforeOrAfterTax;

    /** 职位 */
    @Excel(name = "职位")
    private String position;

    /** 配偶险 */
    @Excel(name = "配偶险")
    private String spouseInsurance;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String childrensInsurance;

    /** 社保状态 */
    @Excel(name = "社保状态")
    private String socialSecurityStatus;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String socialSecurityPersonalBase;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String providentFundPersonalBase;

    /** 商业保险等级 */
    @Excel(name = "商业保险等级")
    private String businessInsuranceLevels;

    /** 商业保险开始月份 */
    @Excel(name = "商业保险开始月份")
    private String businessInsuranceStartingMonth;

    /** 是否雇主责任险 */
    @Excel(name = "是否雇主责任险")
    private String employersLiabilityInsurance;

    /** 是否工会费 */
    @Excel(name = "是否工会费")
    private String unionFees;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String personalTaxAccountAreaName;

    /** 商业保险是否新增 */
    @Excel(name = "商业保险是否新增")
    private String commercialInsuranceIsAdded;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String providentFundAccountNumber;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String vender;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String isGreenAllowance;

    public void setEeCode(String eeCode)
    {
        this.eeCode = eeCode;
    }

    public String getEeCode()
    {
        return eeCode;
    }
    public void setEeName(String eeName)
    {
        this.eeName = eeName;
    }

    public String getEeName()
    {
        return eeName;
    }
    public void setClientCode(String clientCode)
    {
        this.clientCode = clientCode;
    }

    public String getClientCode()
    {
        return clientCode;
    }
    public void setClientName(String clientName)
    {
        this.clientName = clientName;
    }

    public String getClientName()
    {
        return clientName;
    }
    public void setIdNumber(String idNumber)
    {
        this.idNumber = idNumber;
    }

    public String getIdNumber()
    {
        return idNumber;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getMobile()
    {
        return mobile;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setIdType(String idType)
    {
        this.idType = idType;
    }

    public String getIdType()
    {
        return idType;
    }
    public void setBankAccountNumber(String bankAccountNumber)
    {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankAccountNumber()
    {
        return bankAccountNumber;
    }
    public void setBankOfAccountSubbranch(String bankOfAccountSubbranch)
    {
        this.bankOfAccountSubbranch = bankOfAccountSubbranch;
    }

    public String getBankOfAccountSubbranch()
    {
        return bankOfAccountSubbranch;
    }
    public void setAccountHolder(String accountHolder)
    {
        this.accountHolder = accountHolder;
    }

    public String getAccountHolder()
    {
        return accountHolder;
    }
    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public String getBirthday()
    {
        return birthday;
    }
    public void setSocialSecurityCity(String socialSecurityCity)
    {
        this.socialSecurityCity = socialSecurityCity;
    }

    public String getSocialSecurityCity()
    {
        return socialSecurityCity;
    }
    public void setSocialSecurityStartingMonth(String socialSecurityStartingMonth)
    {
        this.socialSecurityStartingMonth = socialSecurityStartingMonth;
    }

    public String getSocialSecurityStartingMonth()
    {
        return socialSecurityStartingMonth;
    }
    public void setEntryDate(String entryDate)
    {
        this.entryDate = entryDate;
    }

    public String getEntryDate()
    {
        return entryDate;
    }
    public void setSalaryBeforeOrAfterTax(String salaryBeforeOrAfterTax)
    {
        this.salaryBeforeOrAfterTax = salaryBeforeOrAfterTax;
    }

    public String getSalaryBeforeOrAfterTax()
    {
        return salaryBeforeOrAfterTax;
    }
    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getPosition()
    {
        return position;
    }
    public void setSpouseInsurance(String spouseInsurance)
    {
        this.spouseInsurance = spouseInsurance;
    }

    public String getSpouseInsurance()
    {
        return spouseInsurance;
    }
    public void setChildrensInsurance(String childrensInsurance)
    {
        this.childrensInsurance = childrensInsurance;
    }

    public String getChildrensInsurance()
    {
        return childrensInsurance;
    }
    public void setSocialSecurityStatus(String socialSecurityStatus)
    {
        this.socialSecurityStatus = socialSecurityStatus;
    }

    public String getSocialSecurityStatus()
    {
        return socialSecurityStatus;
    }
    public void setSocialSecurityPersonalBase(String socialSecurityPersonalBase)
    {
        this.socialSecurityPersonalBase = socialSecurityPersonalBase;
    }

    public String getSocialSecurityPersonalBase()
    {
        return socialSecurityPersonalBase;
    }
    public void setProvidentFundPersonalBase(String providentFundPersonalBase)
    {
        this.providentFundPersonalBase = providentFundPersonalBase;
    }

    public String getProvidentFundPersonalBase()
    {
        return providentFundPersonalBase;
    }
    public void setBusinessInsuranceLevels(String businessInsuranceLevels)
    {
        this.businessInsuranceLevels = businessInsuranceLevels;
    }

    public String getBusinessInsuranceLevels()
    {
        return businessInsuranceLevels;
    }
    public void setBusinessInsuranceStartingMonth(String businessInsuranceStartingMonth)
    {
        this.businessInsuranceStartingMonth = businessInsuranceStartingMonth;
    }

    public String getBusinessInsuranceStartingMonth()
    {
        return businessInsuranceStartingMonth;
    }
    public void setEmployersLiabilityInsurance(String employersLiabilityInsurance)
    {
        this.employersLiabilityInsurance = employersLiabilityInsurance;
    }

    public String getEmployersLiabilityInsurance()
    {
        return employersLiabilityInsurance;
    }
    public void setUnionFees(String unionFees)
    {
        this.unionFees = unionFees;
    }

    public String getUnionFees()
    {
        return unionFees;
    }
    public void setPersonalTaxAccountAreaName(String personalTaxAccountAreaName)
    {
        this.personalTaxAccountAreaName = personalTaxAccountAreaName;
    }

    public String getPersonalTaxAccountAreaName()
    {
        return personalTaxAccountAreaName;
    }
    public void setCommercialInsuranceIsAdded(String commercialInsuranceIsAdded)
    {
        this.commercialInsuranceIsAdded = commercialInsuranceIsAdded;
    }

    public String getCommercialInsuranceIsAdded()
    {
        return commercialInsuranceIsAdded;
    }
    public void setProvidentFundAccountNumber(String providentFundAccountNumber)
    {
        this.providentFundAccountNumber = providentFundAccountNumber;
    }

    public String getProvidentFundAccountNumber()
    {
        return providentFundAccountNumber;
    }
    public void setVender(String vender)
    {
        this.vender = vender;
    }

    public String getVender()
    {
        return vender;
    }
    public void setIsGreenAllowance(String isGreenAllowance)
    {
        this.isGreenAllowance = isGreenAllowance;
    }

    public String getIsGreenAllowance()
    {
        return isGreenAllowance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("eeCode", getEeCode())
                .append("eeName", getEeName())
                .append("clientCode", getClientCode())
                .append("clientName", getClientName())
                .append("idNumber", getIdNumber())
                .append("email", getEmail())
                .append("mobile", getMobile())
                .append("status", getStatus())
                .append("idType", getIdType())
                .append("bankAccountNumber", getBankAccountNumber())
                .append("bankOfAccountSubbranch", getBankOfAccountSubbranch())
                .append("accountHolder", getAccountHolder())
                .append("birthday", getBirthday())
                .append("socialSecurityCity", getSocialSecurityCity())
                .append("socialSecurityStartingMonth", getSocialSecurityStartingMonth())
                .append("entryDate", getEntryDate())
                .append("salaryBeforeOrAfterTax", getSalaryBeforeOrAfterTax())
                .append("position", getPosition())
                .append("spouseInsurance", getSpouseInsurance())
                .append("childrensInsurance", getChildrensInsurance())
                .append("socialSecurityStatus", getSocialSecurityStatus())
                .append("socialSecurityPersonalBase", getSocialSecurityPersonalBase())
                .append("providentFundPersonalBase", getProvidentFundPersonalBase())
                .append("businessInsuranceLevels", getBusinessInsuranceLevels())
                .append("businessInsuranceStartingMonth", getBusinessInsuranceStartingMonth())
                .append("employersLiabilityInsurance", getEmployersLiabilityInsurance())
                .append("unionFees", getUnionFees())
                .append("personalTaxAccountAreaName", getPersonalTaxAccountAreaName())
                .append("commercialInsuranceIsAdded", getCommercialInsuranceIsAdded())
                .append("providentFundAccountNumber", getProvidentFundAccountNumber())
                .append("vender", getVender())
                .append("isGreenAllowance", getIsGreenAllowance())
                .toString();
    }
}
