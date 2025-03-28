
package com.nnroad.contract.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 contract_perface
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public class ContractPerface extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long userId;

    /** Client No. (必填) */
    @Excel(name = "Client No. (必填)")
    private String clientNo;

    /** Date */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "Date", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /** Company Legal Name */
    @Excel(name = "Company Legal Name")
    private String companyName;

    /** Incorporated in which City and Country */
    @Excel(name = "Incorporated in which City and Country")
    private String location;

    /** Official Website */
    @Excel(name = "Official Website")
    private String website;

    /** Company Headquarter Phone Number */
    @Excel(name = "Company Headquarter Phone Number")
    private String companyNumber;

    /** Company Official Email Address */
    @Excel(name = "Company Official Email Address")
    private String email;

    /** Contact Person of Client */
    @Excel(name = "Contact Person of Client")
    private String personName;

    /** Office Email Address */
    @Excel(name = "Office Email Address")
    private String personEmail;

    /** Phone Number */
    @Excel(name = "Phone Number")
    private String personNumber;

    /** Office Address */
    @Excel(name = "Office Address")
    private String personAddress;

    /** Beneficiary’s Bank */
    @Excel(name = "Beneficiary’s Bank")
    private String bank;

    /** SWIFT BIC */
    @Excel(name = "SWIFT BIC")
    private String SWIFT;

    /** Beneficiary */
    @Excel(name = "Beneficiary")
    private String beneficiary;

    /** Bank Account Number */
    @Excel(name = "Bank Account Number")
    private String bankAccountNumber;

    /** Bank Address */
    @Excel(name = "Bank Address")
    private String bankAddress;

    /** 选填 */
    @Excel(name = "选填")
    private String hroneCompanyLimited;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hongKong;

    /** Official Website of HROne */
    @Excel(name = "Official Website of HROne")
    private String hroneWebsite;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hronePhone;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hroneEmail;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hroneContactPerson;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hroneContactEmail;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hroneContactNumber;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hroneContactAddress;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hroneBank;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hroneSwift;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hroneBeneficiary;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hroneBankAccountNumber;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hroneBankAddress;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String serviceType;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer employerOfRecordService;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer recruitmentService;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer commercialInsuranceService;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer employeeAnnualTaxReturnService;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer humanResourceConsultingService;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer individualReferenceCheckService;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String clientSignature;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date clientDate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hroneSignature;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date hroneDate;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setClientNo(String clientNo) 
    {
        this.clientNo = clientNo;
    }

    public String getClientNo() 
    {
        return clientNo;
    }
    public void setDate(Date date) 
    {
        this.date = date;
    }

    public Date getDate() 
    {
        return date;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }
    public void setWebsite(String website) 
    {
        this.website = website;
    }

    public String getWebsite() 
    {
        return website;
    }
    public void setCompanyNumber(String companyNumber) 
    {
        this.companyNumber = companyNumber;
    }

    public String getCompanyNumber() 
    {
        return companyNumber;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setPersonName(String personName) 
    {
        this.personName = personName;
    }

    public String getPersonName() 
    {
        return personName;
    }
    public void setPersonEmail(String personEmail) 
    {
        this.personEmail = personEmail;
    }

    public String getPersonEmail() 
    {
        return personEmail;
    }
    public void setPersonNumber(String personNumber) 
    {
        this.personNumber = personNumber;
    }

    public String getPersonNumber() 
    {
        return personNumber;
    }
    public void setPersonAddress(String personAddress) 
    {
        this.personAddress = personAddress;
    }

    public String getPersonAddress() 
    {
        return personAddress;
    }
    public void setBank(String bank) 
    {
        this.bank = bank;
    }

    public String getBank() 
    {
        return bank;
    }
    public void setSWIFT(String SWIFT) 
    {
        this.SWIFT = SWIFT;
    }

    public String getSWIFT() 
    {
        return SWIFT;
    }
    public void setBeneficiary(String beneficiary) 
    {
        this.beneficiary = beneficiary;
    }

    public String getBeneficiary() 
    {
        return beneficiary;
    }
    public void setBankAccountNumber(String bankAccountNumber) 
    {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankAccountNumber() 
    {
        return bankAccountNumber;
    }
    public void setBankAddress(String bankAddress) 
    {
        this.bankAddress = bankAddress;
    }

    public String getBankAddress() 
    {
        return bankAddress;
    }
    public void setHroneCompanyLimited(String hroneCompanyLimited) 
    {
        this.hroneCompanyLimited = hroneCompanyLimited;
    }

    public String getHroneCompanyLimited() 
    {
        return hroneCompanyLimited;
    }
    public void setHongKong(String hongKong) 
    {
        this.hongKong = hongKong;
    }

    public String getHongKong() 
    {
        return hongKong;
    }
    public void setHroneWebsite(String hroneWebsite) 
    {
        this.hroneWebsite = hroneWebsite;
    }

    public String getHroneWebsite() 
    {
        return hroneWebsite;
    }
    public void setHronePhone(String hronePhone) 
    {
        this.hronePhone = hronePhone;
    }

    public String getHronePhone() 
    {
        return hronePhone;
    }
    public void setHroneEmail(String hroneEmail) 
    {
        this.hroneEmail = hroneEmail;
    }

    public String getHroneEmail() 
    {
        return hroneEmail;
    }
    public void setHroneContactPerson(String hroneContactPerson) 
    {
        this.hroneContactPerson = hroneContactPerson;
    }

    public String getHroneContactPerson() 
    {
        return hroneContactPerson;
    }
    public void setHroneContactEmail(String hroneContactEmail) 
    {
        this.hroneContactEmail = hroneContactEmail;
    }

    public String getHroneContactEmail() 
    {
        return hroneContactEmail;
    }
    public void setHroneContactNumber(String hroneContactNumber) 
    {
        this.hroneContactNumber = hroneContactNumber;
    }

    public String getHroneContactNumber() 
    {
        return hroneContactNumber;
    }
    public void setHroneContactAddress(String hroneContactAddress) 
    {
        this.hroneContactAddress = hroneContactAddress;
    }

    public String getHroneContactAddress() 
    {
        return hroneContactAddress;
    }
    public void setHroneBank(String hroneBank) 
    {
        this.hroneBank = hroneBank;
    }

    public String getHroneBank() 
    {
        return hroneBank;
    }
    public void setHroneSwift(String hroneSwift) 
    {
        this.hroneSwift = hroneSwift;
    }

    public String getHroneSwift() 
    {
        return hroneSwift;
    }
    public void setHroneBeneficiary(String hroneBeneficiary) 
    {
        this.hroneBeneficiary = hroneBeneficiary;
    }

    public String getHroneBeneficiary() 
    {
        return hroneBeneficiary;
    }
    public void setHroneBankAccountNumber(String hroneBankAccountNumber) 
    {
        this.hroneBankAccountNumber = hroneBankAccountNumber;
    }

    public String getHroneBankAccountNumber() 
    {
        return hroneBankAccountNumber;
    }
    public void setHroneBankAddress(String hroneBankAddress) 
    {
        this.hroneBankAddress = hroneBankAddress;
    }

    public String getHroneBankAddress() 
    {
        return hroneBankAddress;
    }
    public void setServiceType(String serviceType) 
    {
        this.serviceType = serviceType;
    }

    public String getServiceType() 
    {
        return serviceType;
    }
    public void setEmployerOfRecordService(Integer employerOfRecordService) 
    {
        this.employerOfRecordService = employerOfRecordService;
    }

    public Integer getEmployerOfRecordService() 
    {
        return employerOfRecordService;
    }
    public void setRecruitmentService(Integer recruitmentService) 
    {
        this.recruitmentService = recruitmentService;
    }

    public Integer getRecruitmentService() 
    {
        return recruitmentService;
    }
    public void setCommercialInsuranceService(Integer commercialInsuranceService) 
    {
        this.commercialInsuranceService = commercialInsuranceService;
    }

    public Integer getCommercialInsuranceService() 
    {
        return commercialInsuranceService;
    }
    public void setEmployeeAnnualTaxReturnService(Integer employeeAnnualTaxReturnService) 
    {
        this.employeeAnnualTaxReturnService = employeeAnnualTaxReturnService;
    }

    public Integer getEmployeeAnnualTaxReturnService() 
    {
        return employeeAnnualTaxReturnService;
    }
    public void setHumanResourceConsultingService(Integer humanResourceConsultingService) 
    {
        this.humanResourceConsultingService = humanResourceConsultingService;
    }

    public Integer getHumanResourceConsultingService() 
    {
        return humanResourceConsultingService;
    }
    public void setIndividualReferenceCheckService(Integer individualReferenceCheckService) 
    {
        this.individualReferenceCheckService = individualReferenceCheckService;
    }

    public Integer getIndividualReferenceCheckService() 
    {
        return individualReferenceCheckService;
    }
    public void setClientSignature(String clientSignature) 
    {
        this.clientSignature = clientSignature;
    }

    public String getClientSignature() 
    {
        return clientSignature;
    }
    public void setClientDate(Date clientDate) 
    {
        this.clientDate = clientDate;
    }

    public Date getClientDate() 
    {
        return clientDate;
    }
    public void setHroneSignature(String hroneSignature) 
    {
        this.hroneSignature = hroneSignature;
    }

    public String getHroneSignature() 
    {
        return hroneSignature;
    }
    public void setHroneDate(Date hroneDate) 
    {
        this.hroneDate = hroneDate;
    }

    public Date getHroneDate() 
    {
        return hroneDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("clientNo", getClientNo())
            .append("date", getDate())
            .append("companyName", getCompanyName())
            .append("location", getLocation())
            .append("website", getWebsite())
            .append("companyNumber", getCompanyNumber())
            .append("email", getEmail())
            .append("personName", getPersonName())
            .append("personEmail", getPersonEmail())
            .append("personNumber", getPersonNumber())
            .append("personAddress", getPersonAddress())
            .append("bank", getBank())
            .append("SWIFT", getSWIFT())
            .append("beneficiary", getBeneficiary())
            .append("bankAccountNumber", getBankAccountNumber())
            .append("bankAddress", getBankAddress())
            .append("hroneCompanyLimited", getHroneCompanyLimited())
            .append("hongKong", getHongKong())
            .append("hroneWebsite", getHroneWebsite())
            .append("hronePhone", getHronePhone())
            .append("hroneEmail", getHroneEmail())
            .append("hroneContactPerson", getHroneContactPerson())
            .append("hroneContactEmail", getHroneContactEmail())
            .append("hroneContactNumber", getHroneContactNumber())
            .append("hroneContactAddress", getHroneContactAddress())
            .append("hroneBank", getHroneBank())
            .append("hroneSwift", getHroneSwift())
            .append("hroneBeneficiary", getHroneBeneficiary())
            .append("hroneBankAccountNumber", getHroneBankAccountNumber())
            .append("hroneBankAddress", getHroneBankAddress())
            .append("serviceType", getServiceType())
            .append("employerOfRecordService", getEmployerOfRecordService())
            .append("recruitmentService", getRecruitmentService())
            .append("commercialInsuranceService", getCommercialInsuranceService())
            .append("employeeAnnualTaxReturnService", getEmployeeAnnualTaxReturnService())
            .append("humanResourceConsultingService", getHumanResourceConsultingService())
            .append("individualReferenceCheckService", getIndividualReferenceCheckService())
            .append("clientSignature", getClientSignature())
            .append("clientDate", getClientDate())
            .append("hroneSignature", getHroneSignature())
            .append("hroneDate", getHroneDate())
            .toString();
    }
}
