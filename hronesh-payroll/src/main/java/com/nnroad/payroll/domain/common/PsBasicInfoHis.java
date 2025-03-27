package com.nnroad.payroll.domain.common;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 基本信息历史对象 ps_basic_info_his
 * 
 * @author Aaron
 * @date 2021-12-13
 */
public class PsBasicInfoHis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** Duration */
    @Excel(name = "excel.basicinfo.duration")
    private String duration;

    /** client  short */
    @Excel(name = "excel.basicinfo.er_name")
    private String erName;

    /** id no */
    @Excel(name = "excel.basicinfo.id_no")
    private String idNo;

    /** name */
    @Excel(name = "excel.basicinfo.name")
    private String name;

    /** English name */
    @Excel(name = "excel.basicinfo.en_name")
    private String enName;

    /** sex */
    @Excel(name = "excel.basicinfo.sex")
    private String sex;

    /** client  type */
    @Excel(name = "excel.basicinfo.client_type")
    private String clientType;

    /** work address */
    @Excel(name = "excel.basicinfo.work_address")
    private String workAddress;

    /** social benefits address */
    @Excel(name = "excel.basicinfo.social_benefits_address")
    private String socialBenefitsAddress;

    /** housing fund address */
    @Excel(name = "excel.basicinfo.housing_fund_address")
    private String housingFundAddress;

    /** employment nature */
    @Excel(name = "excel.basicinfo.employment_nature")
    private String employmentNature;

    /** service start time */
    @Excel(name = "excel.basicinfo.service_starttime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String serviceStarttime;

    /** contract address */
    @Excel(name = "excel.basicinfo.contract_address")
    private String contractAddress;

    /** contract starttime */
    @Excel(name = "excel.basicinfo.contract_starttime")
    private String contractStarttime;

    /** contract endtime */
    @Excel(name = "excel.basicinfo.contract_endtime")
    private String contractEndtime;

    /** leave date */
    @Excel(name = "excel.basicinfo.leave_date")
    private String leaveDate;

    /** 税前/税后 */
    @Excel(name = "excel.basicinfo.pre_after_tax")
    private String preAfterTax;

    /** basic salary */
    @Excel(name = "excel.basicinfo.basic_salary")
    private BigDecimal basicSalary;

    /** daily salary */
    @Excel(name = "excel.basicinfo.daily_salary")
    private BigDecimal dailySalary;

    /** floating salary */
    @Excel(name = "excel.basicinfo.floating_salary")
    private BigDecimal floatingSalary;

    /** transport  allowance */
    @Excel(name = "excel.basicinfo.transport_allowance")
    private BigDecimal transportAllowance;

    /** communication allowance */
    @Excel(name = "excel.basicinfo.communication_allowance")
    private BigDecimal communicationAllowance;

    /** other fixed allowance */
    @Excel(name = "excel.basicinfo.other_fixed_allowance")
    private BigDecimal otherFixedAllowance;

    /** Basis for middle shift allowance Calculation */
    @Excel(name = "excel.basicinfo.msa_base")
    private BigDecimal msaBase;

    /** Basis for night shift allowance  Calculation */
    @Excel(name = "excel.basicinfo.nsa_base")
    private BigDecimal nsaBase;

    /** Basis for hot weather allowance  Calculation */
    @Excel(name = "excel.basicinfo.hwa_base")
    private BigDecimal hwaBase;

    /** Basis for hot weather health food  Calculation */
    @Excel(name = "excel.basicinfo.hwhf_base")
    private BigDecimal hwhfBase;

    /** Basis for poisonous health food  Calculation */
    @Excel(name = "excel.basicinfo.phf_base")
    private BigDecimal phfBase;

    /** only child birthday */
    @Excel(name = "excel.basicinfo.only_child_birthday")
    private BigDecimal onlyChildBirthday;

    /** Basis for only child allowance cardinality */
    @Excel(name = "excel.basicinfo.oca_base")
    private BigDecimal ocaBase;

    /** fixed subsidy */
    @Excel(name = "excel.basicinfo.fixed_subsidy")
    private BigDecimal fixedSubsidy;

    /** fixed deduction */
    @Excel(name = "excel.basicinfo.fixed_deduction")
    private BigDecimal fixedDeduction;

    /** Pension base */
    @Excel(name = "excel.basicinfo.pension_base")
    private BigDecimal pensionBase;

    /** Medical base */
    @Excel(name = "excel.basicinfo.medical_base")
    private BigDecimal medicalBase;

    /** Unemployment base */
    @Excel(name = "excel.basicinfo.unemployment_base")
    private BigDecimal unemploymentBase;

    /** Maternity base */
    @Excel(name = "excel.basicinfo.maternity_base")
    private BigDecimal maternityBase;

    /** Work-Related-Injury base */
    @Excel(name = "excel.basicinfo.work_related_injury_base")
    private BigDecimal workRelatedInjuryBase;

    /** Disability base */
    @Excel(name = "excel.basicinfo.disability_base")
    private BigDecimal disabilityBase;

    /** Housing Fund Base */
    @Excel(name = "excel.basicinfo.housing_fund_base")
    private BigDecimal housingFundBase;

    /** Supplementary Housing Fund base */
    @Excel(name = "excel.basicinfo.shf_base")
    private BigDecimal shfBase;

    /** Personal pension% */
    @Excel(name = "excel.basicinfo.personal_pension")
    private String personalPension;

    /** Personal medical% */
    @Excel(name = "excel.basicinfo.personal_medical")
    private String personalMedical;

    /** Personal unemployment% */
    @Excel(name = "excel.basicinfo.personal_unemployment")
    private String personalUnemployment;

    /** Personal Housing Fund% */
    @Excel(name = "excel.basicinfo.personal_housing_fund")
    private String personalHousingFund;

    /** Personal Supplementary Provident Fund% (tax exempt) */
    @Excel(name = "excel.basicinfo.personal_shf_te")
    private String personalShfTe;

    /** Personal Supplementary Provident Fund% (not exempt from tax) */
    @Excel(name = "excel.basicinfo.personal_shf_nte")
    private String personalShfNte;

    /** Personal medical fixed number */
    @Excel(name = "excel.basicinfo.personal_medical_fixed_number")
    private BigDecimal personalMedicalFixedNumber;

    /** Company pension% */
    @Excel(name = "excel.basicinfo.company_pension")
    private String companyPension;

    /** Company medical% */
    @Excel(name = "excel.basicinfo.company_medical")
    private String companyMedical;

    /** Company unemployment% */
    @Excel(name = "excel.basicinfo.company_unemployment")
    private String companyUnemployment;

    /** Company maternity% */
    @Excel(name = "excel.basicinfo.company_maternity")
    private String companyMaternity;

    /** Company Work-Related-Injury% */
    @Excel(name = "excel.basicinfo.company_work_related_injury")
    private String companyWorkRelatedInjury;

    /** Company disability insurance fund% */
    @Excel(name = "excel.basicinfo.company_disability")
    private String companyDisability;

    /** Company Housing Fund% */
    @Excel(name = "excel.basicinfo.company_housing_fund")
    private String companyHousingFund;

    /** Company supplementary Housing Fund% */
    @Excel(name = "excel.basicinfo.company_shf")
    private String companyShf;

    /** Company medical fixed number */
    @Excel(name = "excel.basicinfo.company_medical_fixed_number")
    private BigDecimal companyMedicalFixedNumber;

    /** Fixed number of company injuries */
    @Excel(name = "excel.basicinfo.company_wri_fixed_number")
    private BigDecimal companyWriFixedNumber;

    /** Fixed number of company disability insurance */
    @Excel(name = "excel.basicinfo.company_disability_fixed_number")
    private BigDecimal companyDisabilityFixedNumber;

    /** Annuity% */
    @Excel(name = "excel.basicinfo.basic_info_annuity")
    private String annuity;

    /** Union fee */
    @Excel(name = "excel.basicinfo.union_fee")
    private BigDecimal unionFee;

    /** 计税不发 */
    @Excel(name = "excel.basicinfo.tax_not_issued")
    private BigDecimal taxNotIssued;

    /** 公司负税1 */
    @Excel(name = "excel.basicinfo.company_tax")
    private BigDecimal companyTax;

    /** Subsistence Allowance for Foreigners (tax exempt) */
    @Excel(name = "excel.basicinfo.fs_allowance_no_tax")
    private BigDecimal fsAllowance;

    /** 税后扣1 */
    @Excel(name = "excel.basicinfo.after_tax_deduction")
    private BigDecimal afterTaxDeduction;

    /** 免税收入1 */
    @Excel(name = "excel.basicinfo.tax_free_income")
    private BigDecimal taxFreeIncome;

    /** type of certificate */
    @Excel(name = "excel.basicinfo.certificate_type")
    private String certificateType;

    /** certificate number */
    @Excel(name = "excel.basicinfo.certificate_number")
    private String certificateNumber;

    /** birthday */
    @Excel(name = "excel.basicinfo.birthday")
    private String birthday;

    /** Housing Fund Account */
    @Excel(name = "excel.basicinfo.housing_fund_account")
    private String housingFundAccount;

    /** Bank account name */
    @Excel(name = "excel.basicinfo.bank_accoun_name")
    private String bankAccounName;

    /** Bank Account */
    @Excel(name = "excel.basicinfo.bank_account")
    private String bankAccount;

    /** bank */
    @Excel(name = "excel.basicinfo.bank")
    private String bank;

    /** department */
    @Excel(name = "excel.basicinfo.department")
    private String department;

    /** post */
    @Excel(name = "excel.basicinfo.post")
    private String post;

    /** Nationality/ethnicity */
    @Excel(name = "excel.basicinfo.nationality")
    private String nationality;

    /** Nationality code */
    @Excel(name = "excel.basicinfo.nationality_code")
    private String nationalityCode;

    /** Payroll */
    @Excel(name = "excel.basicinfo.payroll")
    private String payroll;

    /** Telephone / Cell phone */
    @Excel(name = "excel.basicinfo.telephone")
    private String telephone;

    /** mailbox */
    @Excel(name = "excel.basicinfo.mailbox")
    private String mailbox;

    /** Billing address */
    @Excel(name = "excel.basicinfo.bill_address")
    private String billAddress;

    /** Postcode */
    @Excel(name = "excel.basicinfo.postcode")
    private String postcode;

    /** Residence type */
    @Excel(name = "excel.basicinfo.residence_type")
    private String residenceType;

    /** Residence address */
    @Excel(name = "excel.basicinfo.residence_address")
    private String residenceAddress;

    /** Total cost of commercial insurance */
    @Excel(name = "excel.basicinfo.commercial_insurance_cost")
    private BigDecimal commercialInsuranceCost;

    /** Commercial insurance level */
    @Excel(name = "excel.basicinfo.commercial_insurance_level")
    private String commercialInsuranceLevel;

    /** Spouse/Children Insurance */
    @Excel(name = "excel.basicinfo.spouse_children_insurance")
    private BigDecimal spouseChildrenInsurance;

    /** Green allowance */
    @Excel(name = "excel.basicinfo.green_allowance")
    private BigDecimal greenAllowance;

    /** Physical examination */
    @Excel(name = "excel.basicinfo.physical_examination")
    private BigDecimal physicalExamination;

    /** File management fee */
    @Excel(name = "excel.basicinfo.file_management_fee")
    private BigDecimal fileManagementFee;

    /** 13th Salary/Annual Bonus information */
    @Excel(name = "excel.basicinfo.annual_bonus_info")
    private String annualBonusInfo;

    /** 13 salary month */
    @Excel(name = "excel.basicinfo.thirteen_salary_month")
    private BigDecimal thirteenSalaryMonth;

    /** Remarks */
    @Excel(name = "excel.basicinfo.remarks")
    private String remarks;

    /** 个税缴纳地 */
    @Excel(name = "excel.basicinfo.tax_payment_place")
    private String taxPaymentPlace;

    /** 免税不发项 */
    @Excel(name = "excel.basicinfo.tax_free_item")
    private BigDecimal taxFreeItem;

    /** Accumulated child education */
    @Excel(name = "excel.basicinfo.accumulated_child_education")
    private BigDecimal accumulatedChildEducation;

    /** Accumulated continuing education */
    @Excel(name = "excel.basicinfo.accumulated_continuing_education")
    private BigDecimal accumulatedContinuingEducation;

    /** Accumulated housing loan interest */
    @Excel(name = "excel.basicinfo.accumulated_hli")
    private BigDecimal accumulatedHli;

    /** Accumulated housing rent */
    @Excel(name = "excel.basicinfo.accumulated_housing_rent")
    private BigDecimal accumulatedHousingRent;

    /** Accumulated support for the elderly */
    @Excel(name = "excel.basicinfo.accumulated_sfte")
    private BigDecimal accumulatedSfte;

    /** Commercial health insurance */
    @Excel(name = "excel.basicinfo.commercial_health_insurance")
    private BigDecimal commercialHealthInsurance;

    /** Tax deferred pension insurance */
    @Excel(name = "excel.basicinfo.tdp_insurance")
    private BigDecimal tdpInsurance;

    /** Other tax-free deductions (donations) */
    @Excel(name = "excel.basicinfo.other_tax_free_deductions")
    private BigDecimal otherTaxFreeDeductions;

    /** Daily attendance days */
    @Excel(name = "excel.basicinfo.daily_attendance_days")
    private BigDecimal dailyAttendanceDays;

    /** Monthly award */
    @Excel(name = "excel.basicinfo.monthly_award")
    private BigDecimal monthlyAward;

    /** Quarter award */
    @Excel(name = "excel.basicinfo.quarter_award")
    private BigDecimal quarterAward;

    /** Overtime 150 hours */
    @Excel(name = "excel.basicinfo.overtime_150hours")
    private String overtime150hours;

    /** Overtime 200 hours */
    @Excel(name = "excel.basicinfo.overtime_200hours")
    private String overtime200hours;

    /** Overtime 300 hours */
    @Excel(name = "excel.basicinfo.overtime_300hours")
    private String overtime300hours;

    /** Full attendance day of the month */
    @Excel(name = "excel.basicinfo.full_attendance_days")
    private BigDecimal fullAttendanceDays;

    /** Days of entry and leave */
    @Excel(name = "excel.basicinfo.eal_days")
    private BigDecimal ealDays;

    /** Affair Leave days */
    @Excel(name = "excel.basicinfo.affair_leave_days")
    private BigDecimal affairLeaveDays;

    /** Absent eeism days */
    @Excel(name = "excel.basicinfo.absent_eeism_days")
    private BigDecimal absentEeismDays;

    /** Sick leave days */
    @Excel(name = "excel.basicinfo.sick_leave_days")
    private BigDecimal sickLeaveDays;

    /** Sick leave deduction */
    @Excel(name = "excel.basicinfo.sick_leave_deduction")
    private BigDecimal sickLeaveDeduction;

    /** Transportation */
    @Excel(name = "excel.basicinfo.transportation_fee")
    private BigDecimal transportationFee;

    /** Communication fee */
    @Excel(name = "excel.basicinfo.communication_fee")
    private BigDecimal communicationFee;

    /** Middle shift days */
    @Excel(name = "excel.basicinfo.middle_shift_day")
    private BigDecimal middleShiftDay;

    /** Night shift days */
    @Excel(name = "excel.basicinfo.night_shift_days")
    private BigDecimal nightShiftDays;

    /** High temperature days */
    @Excel(name = "excel.basicinfo.high_temperature_days")
    private BigDecimal highTemperatureDays;

    /** One-time high temperature fee */
    @Excel(name = "excel.basicinfo.oht_fee")
    private BigDecimal ohtFee;

    /** Hot working days */
    @Excel(name = "excel.basicinfo.hot_working_days")
    private BigDecimal hotWorkingDays;

    /** Toxic and harmful working days */
    @Excel(name = "excel.basicinfo.tahw_days")
    private BigDecimal tahwDays;

    /** Reissue */
    @Excel(name = "excel.basicinfo.reissue")
    private BigDecimal reissue;

    /** Wage deduction */
    @Excel(name = "excel.basicinfo.wage_deduction")
    private BigDecimal wageDeduction;

    /** Pension repayment */
    @Excel(name = "excel.basicinfo.pension_repayment")
    private BigDecimal pensionRepayment;

    /** Medical repayment */
    @Excel(name = "excel.basicinfo.medical_repayment")
    private BigDecimal medicalRepayment;

    /** Unemployment repayment */
    @Excel(name = "excel.basicinfo.unemployment_repayment")
    private BigDecimal unemploymentRepayment;

    /** Housing fund repayment */
    @Excel(name = "excel.basicinfo.housing_fund_payment")
    private BigDecimal housingFundPayment;

    /** Tax-free income2 */
    @Excel(name = "excel.basicinfo.tax_free_income2")
    private BigDecimal taxFreeIncome2;

    /** 计税不发2 */
    @Excel(name = "excel.basicinfo.tax_calculation")
    private BigDecimal taxCalculation;

    /** Company tax */
    @Excel(name = "excel.basicinfo.company_tax2")
    private BigDecimal companyTax2;

    /** Expense */
    @Excel(name = "excel.basicinfo.expense")
    private BigDecimal expense;

    /** Expenset 25% */
    @Excel(name = "excel.basicinfo.expense_25")
    private BigDecimal expense25;

    /** After-tax reissue */
    @Excel(name = "excel.basicinfo.after_tax_reissue")
    private BigDecimal afterTaxReissue;

    /** After-tax deduction */
    @Excel(name = "excel.basicinfo.after_tax_deduction2")
    private BigDecimal afterTaxDeduction2;

    /** Individual tax adjustment */
    @Excel(name = "excel.basicinfo.Individual_tax_adjustment")
    private BigDecimal individualTaxAdjustment;

    /** Annual Bonus/13th Salary */
    @Excel(name = "excel.basicinfo.annual_bonus")
    private BigDecimal annualBonus;

    /** Remarks2 */
    @Excel(name = "excel.basicinfo.remarks2")
    private String remarks2;

    /** Company Social Benefits repayment */
    @Excel(name = "excel.basicinfo.csb_repayment")
    private BigDecimal csbRepayment;

    /** Company Housing Fund repayment */
    @Excel(name = "excel.basicinfo.chf_repayment")
    private BigDecimal chfRepayment;

    /** Company Disability repayment */
    @Excel(name = "excel.basicinfo.cd_repayment")
    private BigDecimal cdRepayment;

    /** Payroll belong */
    @Excel(name = "excel.basicinfo.payroll_belong")
    private String payrollBelong;

    /** Basic deduction adjustment */
    // @Excel(name = "basic_deduction_adjustment")
    private BigDecimal basicDeductionAdjustment;

    /** Location of bank */
    @Excel(name = "excel.basicinfo.bank_location")
    private String bankLocation;

    /** 离职补偿金 */
    @Excel(name = "excel.basicinfo.severance")
    private BigDecimal severance;

    /** 生日礼金 */
    @Excel(name = "excel.basicinfo.birthdayGift")
    private BigDecimal birthdayGift;

    /** 取暖费 */
    @Excel(name = "excel.basicinfo.heatingFee")
    private BigDecimal heatingFee;

    /** 是否代缴社保 */
    @Excel(name = "excel.basicinfo.paySBFlag")
    private String paySbFlag;

    /** 是否代缴公积金 */
    @Excel(name = "excel.basicinfo.payHFFlag")
    private String payHfFlag;

    /** 是否代缴个税 */
    @Excel(name = "excel.basicinfo.payIITFlag")
    private String payIitFlag;

    /** 是否代缴个税 */
    @Excel(name = "excel.basicinfo.payIITFlag2")
    private String payIitFlag2;

    /** 客户编号 */
    @Excel(name = "excel.basicinfo.clientCode")
    private String clientCode;

    /** 客户员工工号 */
    @Excel(name = "excel.basicinfo.employeeNo")
    private String employeeNo;

    /** 成本中心/项目编号 */
    @Excel(name = "excel.basicinfo.costCenter")
    private String costCenter;

    /** 实际绩效 */
    @Excel(name = "excel.basicinfo.actualPerformance")
    private BigDecimal actualPerformance;

    /** 绩效奖金基数 */
    @Excel(name = "excel.basicinfo.performanceBonusBase")
    private BigDecimal performanceBonusBase;

    /** 提成 */
    @Excel(name = "excel.basicinfo.commission")
    private BigDecimal commission;

    /** 权限组 */
    private String groupIds;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
    public void setEnName(String enName) 
    {
        this.enName = enName;
    }

    public String getEnName() 
    {
        return enName;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setClientType(String clientType) 
    {
        this.clientType = clientType;
    }

    public String getClientType() 
    {
        return clientType;
    }
    public void setWorkAddress(String workAddress) 
    {
        this.workAddress = workAddress;
    }

    public String getWorkAddress() 
    {
        return workAddress;
    }
    public void setSocialBenefitsAddress(String socialBenefitsAddress) 
    {
        this.socialBenefitsAddress = socialBenefitsAddress;
    }

    public String getSocialBenefitsAddress() 
    {
        return socialBenefitsAddress;
    }
    public void setHousingFundAddress(String housingFundAddress) 
    {
        this.housingFundAddress = housingFundAddress;
    }

    public String getHousingFundAddress() 
    {
        return housingFundAddress;
    }
    public void setEmploymentNature(String employmentNature) 
    {
        this.employmentNature = employmentNature;
    }

    public String getEmploymentNature() 
    {
        return employmentNature;
    }
    public void setServiceStarttime(String serviceStarttime) 
    {
        this.serviceStarttime = serviceStarttime;
    }

    public String getServiceStarttime() 
    {
        return serviceStarttime;
    }
    public void setContractAddress(String contractAddress) 
    {
        this.contractAddress = contractAddress;
    }

    public String getContractAddress() 
    {
        return contractAddress;
    }
    public void setContractStarttime(String contractStarttime) 
    {
        this.contractStarttime = contractStarttime;
    }

    public String getContractStarttime() 
    {
        return contractStarttime;
    }
    public void setContractEndtime(String contractEndtime) 
    {
        this.contractEndtime = contractEndtime;
    }

    public String getContractEndtime() 
    {
        return contractEndtime;
    }
    public void setLeaveDate(String leaveDate) 
    {
        this.leaveDate = leaveDate;
    }

    public String getLeaveDate() 
    {
        return leaveDate;
    }
    public void setPreAfterTax(String preAfterTax) 
    {
        this.preAfterTax = preAfterTax;
    }

    public String getPreAfterTax() 
    {
        return preAfterTax;
    }
    public void setBasicSalary(BigDecimal basicSalary) 
    {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getBasicSalary() 
    {
        return basicSalary;
    }
    public void setDailySalary(BigDecimal dailySalary) 
    {
        this.dailySalary = dailySalary;
    }

    public BigDecimal getDailySalary() 
    {
        return dailySalary;
    }
    public void setFloatingSalary(BigDecimal floatingSalary) 
    {
        this.floatingSalary = floatingSalary;
    }

    public BigDecimal getFloatingSalary() 
    {
        return floatingSalary;
    }
    public void setTransportAllowance(BigDecimal transportAllowance) 
    {
        this.transportAllowance = transportAllowance;
    }

    public BigDecimal getTransportAllowance() 
    {
        return transportAllowance;
    }
    public void setCommunicationAllowance(BigDecimal communicationAllowance) 
    {
        this.communicationAllowance = communicationAllowance;
    }

    public BigDecimal getCommunicationAllowance() 
    {
        return communicationAllowance;
    }
    public void setOtherFixedAllowance(BigDecimal otherFixedAllowance) 
    {
        this.otherFixedAllowance = otherFixedAllowance;
    }

    public BigDecimal getOtherFixedAllowance() 
    {
        return otherFixedAllowance;
    }
    public void setMsaBase(BigDecimal msaBase) 
    {
        this.msaBase = msaBase;
    }

    public BigDecimal getMsaBase() 
    {
        return msaBase;
    }
    public void setNsaBase(BigDecimal nsaBase) 
    {
        this.nsaBase = nsaBase;
    }

    public BigDecimal getNsaBase() 
    {
        return nsaBase;
    }
    public void setHwaBase(BigDecimal hwaBase) 
    {
        this.hwaBase = hwaBase;
    }

    public BigDecimal getHwaBase() 
    {
        return hwaBase;
    }
    public void setHwhfBase(BigDecimal hwhfBase) 
    {
        this.hwhfBase = hwhfBase;
    }

    public BigDecimal getHwhfBase() 
    {
        return hwhfBase;
    }
    public void setPhfBase(BigDecimal phfBase) 
    {
        this.phfBase = phfBase;
    }

    public BigDecimal getPhfBase() 
    {
        return phfBase;
    }
    public void setOnlyChildBirthday(BigDecimal onlyChildBirthday) 
    {
        this.onlyChildBirthday = onlyChildBirthday;
    }

    public BigDecimal getOnlyChildBirthday() 
    {
        return onlyChildBirthday;
    }
    public void setOcaBase(BigDecimal ocaBase) 
    {
        this.ocaBase = ocaBase;
    }

    public BigDecimal getOcaBase() 
    {
        return ocaBase;
    }
    public void setFixedSubsidy(BigDecimal fixedSubsidy) 
    {
        this.fixedSubsidy = fixedSubsidy;
    }

    public BigDecimal getFixedSubsidy() 
    {
        return fixedSubsidy;
    }
    public void setFixedDeduction(BigDecimal fixedDeduction) 
    {
        this.fixedDeduction = fixedDeduction;
    }

    public BigDecimal getFixedDeduction() 
    {
        return fixedDeduction;
    }
    public void setPensionBase(BigDecimal pensionBase) 
    {
        this.pensionBase = pensionBase;
    }

    public BigDecimal getPensionBase() 
    {
        return pensionBase;
    }
    public void setMedicalBase(BigDecimal medicalBase) 
    {
        this.medicalBase = medicalBase;
    }

    public BigDecimal getMedicalBase() 
    {
        return medicalBase;
    }
    public void setUnemploymentBase(BigDecimal unemploymentBase) 
    {
        this.unemploymentBase = unemploymentBase;
    }

    public BigDecimal getUnemploymentBase() 
    {
        return unemploymentBase;
    }
    public void setMaternityBase(BigDecimal maternityBase) 
    {
        this.maternityBase = maternityBase;
    }

    public BigDecimal getMaternityBase() 
    {
        return maternityBase;
    }
    public void setWorkRelatedInjuryBase(BigDecimal workRelatedInjuryBase) 
    {
        this.workRelatedInjuryBase = workRelatedInjuryBase;
    }

    public BigDecimal getWorkRelatedInjuryBase() 
    {
        return workRelatedInjuryBase;
    }
    public void setDisabilityBase(BigDecimal disabilityBase) 
    {
        this.disabilityBase = disabilityBase;
    }

    public BigDecimal getDisabilityBase() 
    {
        return disabilityBase;
    }
    public void setHousingFundBase(BigDecimal housingFundBase) 
    {
        this.housingFundBase = housingFundBase;
    }

    public BigDecimal getHousingFundBase() 
    {
        return housingFundBase;
    }
    public void setShfBase(BigDecimal shfBase) 
    {
        this.shfBase = shfBase;
    }

    public BigDecimal getShfBase() 
    {
        return shfBase;
    }
    public void setPersonalPension(String personalPension) 
    {
        this.personalPension = personalPension;
    }

    public String getPersonalPension() 
    {
        return personalPension;
    }
    public void setPersonalMedical(String personalMedical) 
    {
        this.personalMedical = personalMedical;
    }

    public String getPersonalMedical() 
    {
        return personalMedical;
    }
    public void setPersonalUnemployment(String personalUnemployment) 
    {
        this.personalUnemployment = personalUnemployment;
    }

    public String getPersonalUnemployment() 
    {
        return personalUnemployment;
    }
    public void setPersonalHousingFund(String personalHousingFund) 
    {
        this.personalHousingFund = personalHousingFund;
    }

    public String getPersonalHousingFund() 
    {
        return personalHousingFund;
    }
    public void setPersonalShfTe(String personalShfTe) 
    {
        this.personalShfTe = personalShfTe;
    }

    public String getPersonalShfTe() 
    {
        return personalShfTe;
    }
    public void setPersonalShfNte(String personalShfNte) 
    {
        this.personalShfNte = personalShfNte;
    }

    public String getPersonalShfNte() 
    {
        return personalShfNte;
    }
    public void setPersonalMedicalFixedNumber(BigDecimal personalMedicalFixedNumber) 
    {
        this.personalMedicalFixedNumber = personalMedicalFixedNumber;
    }

    public BigDecimal getPersonalMedicalFixedNumber() 
    {
        return personalMedicalFixedNumber;
    }
    public void setCompanyPension(String companyPension) 
    {
        this.companyPension = companyPension;
    }

    public String getCompanyPension() 
    {
        return companyPension;
    }
    public void setCompanyMedical(String companyMedical) 
    {
        this.companyMedical = companyMedical;
    }

    public String getCompanyMedical() 
    {
        return companyMedical;
    }
    public void setCompanyUnemployment(String companyUnemployment) 
    {
        this.companyUnemployment = companyUnemployment;
    }

    public String getCompanyUnemployment() 
    {
        return companyUnemployment;
    }
    public void setCompanyMaternity(String companyMaternity) 
    {
        this.companyMaternity = companyMaternity;
    }

    public String getCompanyMaternity() 
    {
        return companyMaternity;
    }
    public void setCompanyWorkRelatedInjury(String companyWorkRelatedInjury) 
    {
        this.companyWorkRelatedInjury = companyWorkRelatedInjury;
    }

    public String getCompanyWorkRelatedInjury() 
    {
        return companyWorkRelatedInjury;
    }
    public void setCompanyDisability(String companyDisability) 
    {
        this.companyDisability = companyDisability;
    }

    public String getCompanyDisability() 
    {
        return companyDisability;
    }
    public void setCompanyHousingFund(String companyHousingFund) 
    {
        this.companyHousingFund = companyHousingFund;
    }

    public String getCompanyHousingFund() 
    {
        return companyHousingFund;
    }
    public void setCompanyShf(String companyShf) 
    {
        this.companyShf = companyShf;
    }

    public String getCompanyShf() 
    {
        return companyShf;
    }
    public void setCompanyMedicalFixedNumber(BigDecimal companyMedicalFixedNumber) 
    {
        this.companyMedicalFixedNumber = companyMedicalFixedNumber;
    }

    public BigDecimal getCompanyMedicalFixedNumber() 
    {
        return companyMedicalFixedNumber;
    }
    public void setCompanyWriFixedNumber(BigDecimal companyWriFixedNumber) 
    {
        this.companyWriFixedNumber = companyWriFixedNumber;
    }

    public BigDecimal getCompanyWriFixedNumber() 
    {
        return companyWriFixedNumber;
    }
    public void setCompanyDisabilityFixedNumber(BigDecimal companyDisabilityFixedNumber) 
    {
        this.companyDisabilityFixedNumber = companyDisabilityFixedNumber;
    }

    public BigDecimal getCompanyDisabilityFixedNumber() 
    {
        return companyDisabilityFixedNumber;
    }
    public void setAnnuity(String annuity) 
    {
        this.annuity = annuity;
    }

    public String getAnnuity() 
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
    public void setTaxNotIssued(BigDecimal taxNotIssued) 
    {
        this.taxNotIssued = taxNotIssued;
    }

    public BigDecimal getTaxNotIssued() 
    {
        return taxNotIssued;
    }
    public void setCompanyTax(BigDecimal companyTax) 
    {
        this.companyTax = companyTax;
    }

    public BigDecimal getCompanyTax() 
    {
        return companyTax;
    }
    public void setFsAllowance(BigDecimal fsAllowance) 
    {
        this.fsAllowance = fsAllowance;
    }

    public BigDecimal getFsAllowance() 
    {
        return fsAllowance;
    }
    public void setAfterTaxDeduction(BigDecimal afterTaxDeduction) 
    {
        this.afterTaxDeduction = afterTaxDeduction;
    }

    public BigDecimal getAfterTaxDeduction() 
    {
        return afterTaxDeduction;
    }
    public void setTaxFreeIncome(BigDecimal taxFreeIncome) 
    {
        this.taxFreeIncome = taxFreeIncome;
    }

    public BigDecimal getTaxFreeIncome() 
    {
        return taxFreeIncome;
    }
    public void setCertificateType(String certificateType) 
    {
        this.certificateType = certificateType;
    }

    public String getCertificateType() 
    {
        return certificateType;
    }
    public void setCertificateNumber(String certificateNumber) 
    {
        this.certificateNumber = certificateNumber;
    }

    public String getCertificateNumber() 
    {
        return certificateNumber;
    }
    public void setBirthday(String birthday) 
    {
        this.birthday = birthday;
    }

    public String getBirthday() 
    {
        return birthday;
    }
    public void setHousingFundAccount(String housingFundAccount) 
    {
        this.housingFundAccount = housingFundAccount;
    }

    public String getHousingFundAccount() 
    {
        return housingFundAccount;
    }
    public void setBankAccounName(String bankAccounName) 
    {
        this.bankAccounName = bankAccounName;
    }

    public String getBankAccounName() 
    {
        return bankAccounName;
    }
    public void setBankAccount(String bankAccount) 
    {
        this.bankAccount = bankAccount;
    }

    public String getBankAccount() 
    {
        return bankAccount;
    }
    public void setBank(String bank) 
    {
        this.bank = bank;
    }

    public String getBank() 
    {
        return bank;
    }
    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public String getDepartment() 
    {
        return department;
    }
    public void setPost(String post) 
    {
        this.post = post;
    }

    public String getPost() 
    {
        return post;
    }
    public void setNationality(String nationality) 
    {
        this.nationality = nationality;
    }

    public String getNationality() 
    {
        return nationality;
    }
    public void setNationalityCode(String nationalityCode) 
    {
        this.nationalityCode = nationalityCode;
    }

    public String getNationalityCode() 
    {
        return nationalityCode;
    }
    public void setPayroll(String payroll) 
    {
        this.payroll = payroll;
    }

    public String getPayroll() 
    {
        return payroll;
    }
    public void setTelephone(String telephone) 
    {
        this.telephone = telephone;
    }

    public String getTelephone() 
    {
        return telephone;
    }
    public void setMailbox(String mailbox) 
    {
        this.mailbox = mailbox;
    }

    public String getMailbox() 
    {
        return mailbox;
    }
    public void setBillAddress(String billAddress) 
    {
        this.billAddress = billAddress;
    }

    public String getBillAddress() 
    {
        return billAddress;
    }
    public void setPostcode(String postcode) 
    {
        this.postcode = postcode;
    }

    public String getPostcode() 
    {
        return postcode;
    }
    public void setResidenceType(String residenceType) 
    {
        this.residenceType = residenceType;
    }

    public String getResidenceType() 
    {
        return residenceType;
    }
    public void setResidenceAddress(String residenceAddress) 
    {
        this.residenceAddress = residenceAddress;
    }

    public String getResidenceAddress() 
    {
        return residenceAddress;
    }
    public void setCommercialInsuranceCost(BigDecimal commercialInsuranceCost) 
    {
        this.commercialInsuranceCost = commercialInsuranceCost;
    }

    public BigDecimal getCommercialInsuranceCost() 
    {
        return commercialInsuranceCost;
    }
    public void setCommercialInsuranceLevel(String commercialInsuranceLevel) 
    {
        this.commercialInsuranceLevel = commercialInsuranceLevel;
    }

    public String getCommercialInsuranceLevel() 
    {
        return commercialInsuranceLevel;
    }
    public void setSpouseChildrenInsurance(BigDecimal spouseChildrenInsurance) 
    {
        this.spouseChildrenInsurance = spouseChildrenInsurance;
    }

    public BigDecimal getSpouseChildrenInsurance() 
    {
        return spouseChildrenInsurance;
    }
    public void setGreenAllowance(BigDecimal greenAllowance) 
    {
        this.greenAllowance = greenAllowance;
    }

    public BigDecimal getGreenAllowance() 
    {
        return greenAllowance;
    }
    public void setPhysicalExamination(BigDecimal physicalExamination) 
    {
        this.physicalExamination = physicalExamination;
    }

    public BigDecimal getPhysicalExamination() 
    {
        return physicalExamination;
    }
    public void setFileManagementFee(BigDecimal fileManagementFee) 
    {
        this.fileManagementFee = fileManagementFee;
    }

    public BigDecimal getFileManagementFee() 
    {
        return fileManagementFee;
    }
    public void setAnnualBonusInfo(String annualBonusInfo) 
    {
        this.annualBonusInfo = annualBonusInfo;
    }

    public String getAnnualBonusInfo() 
    {
        return annualBonusInfo;
    }
    public void setThirteenSalaryMonth(BigDecimal thirteenSalaryMonth) 
    {
        this.thirteenSalaryMonth = thirteenSalaryMonth;
    }

    public BigDecimal getThirteenSalaryMonth() 
    {
        return thirteenSalaryMonth;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setTaxPaymentPlace(String taxPaymentPlace) 
    {
        this.taxPaymentPlace = taxPaymentPlace;
    }

    public String getTaxPaymentPlace() 
    {
        return taxPaymentPlace;
    }
    public void setTaxFreeItem(BigDecimal taxFreeItem) 
    {
        this.taxFreeItem = taxFreeItem;
    }

    public BigDecimal getTaxFreeItem() 
    {
        return taxFreeItem;
    }
    public void setAccumulatedChildEducation(BigDecimal accumulatedChildEducation) 
    {
        this.accumulatedChildEducation = accumulatedChildEducation;
    }

    public BigDecimal getAccumulatedChildEducation() 
    {
        return accumulatedChildEducation;
    }
    public void setAccumulatedContinuingEducation(BigDecimal accumulatedContinuingEducation) 
    {
        this.accumulatedContinuingEducation = accumulatedContinuingEducation;
    }

    public BigDecimal getAccumulatedContinuingEducation() 
    {
        return accumulatedContinuingEducation;
    }
    public void setAccumulatedHli(BigDecimal accumulatedHli) 
    {
        this.accumulatedHli = accumulatedHli;
    }

    public BigDecimal getAccumulatedHli() 
    {
        return accumulatedHli;
    }
    public void setAccumulatedHousingRent(BigDecimal accumulatedHousingRent) 
    {
        this.accumulatedHousingRent = accumulatedHousingRent;
    }

    public BigDecimal getAccumulatedHousingRent() 
    {
        return accumulatedHousingRent;
    }
    public void setAccumulatedSfte(BigDecimal accumulatedSfte) 
    {
        this.accumulatedSfte = accumulatedSfte;
    }

    public BigDecimal getAccumulatedSfte() 
    {
        return accumulatedSfte;
    }
    public void setCommercialHealthInsurance(BigDecimal commercialHealthInsurance) 
    {
        this.commercialHealthInsurance = commercialHealthInsurance;
    }

    public BigDecimal getCommercialHealthInsurance() 
    {
        return commercialHealthInsurance;
    }
    public void setTdpInsurance(BigDecimal tdpInsurance) 
    {
        this.tdpInsurance = tdpInsurance;
    }

    public BigDecimal getTdpInsurance() 
    {
        return tdpInsurance;
    }
    public void setOtherTaxFreeDeductions(BigDecimal otherTaxFreeDeductions) 
    {
        this.otherTaxFreeDeductions = otherTaxFreeDeductions;
    }

    public BigDecimal getOtherTaxFreeDeductions() 
    {
        return otherTaxFreeDeductions;
    }
    public void setDailyAttendanceDays(BigDecimal dailyAttendanceDays) 
    {
        this.dailyAttendanceDays = dailyAttendanceDays;
    }

    public BigDecimal getDailyAttendanceDays() 
    {
        return dailyAttendanceDays;
    }
    public void setMonthlyAward(BigDecimal monthlyAward) 
    {
        this.monthlyAward = monthlyAward;
    }

    public BigDecimal getMonthlyAward() 
    {
        return monthlyAward;
    }
    public void setQuarterAward(BigDecimal quarterAward) 
    {
        this.quarterAward = quarterAward;
    }

    public BigDecimal getQuarterAward() 
    {
        return quarterAward;
    }
    public void setOvertime150hours(String overtime150hours) 
    {
        this.overtime150hours = overtime150hours;
    }

    public String getOvertime150hours() 
    {
        return overtime150hours;
    }
    public void setOvertime200hours(String overtime200hours) 
    {
        this.overtime200hours = overtime200hours;
    }

    public String getOvertime200hours() 
    {
        return overtime200hours;
    }
    public void setOvertime300hours(String overtime300hours) 
    {
        this.overtime300hours = overtime300hours;
    }

    public String getOvertime300hours() 
    {
        return overtime300hours;
    }
    public void setFullAttendanceDays(BigDecimal fullAttendanceDays) 
    {
        this.fullAttendanceDays = fullAttendanceDays;
    }

    public BigDecimal getFullAttendanceDays() 
    {
        return fullAttendanceDays;
    }
    public void setEalDays(BigDecimal ealDays) 
    {
        this.ealDays = ealDays;
    }

    public BigDecimal getEalDays() 
    {
        return ealDays;
    }
    public void setAffairLeaveDays(BigDecimal affairLeaveDays) 
    {
        this.affairLeaveDays = affairLeaveDays;
    }

    public BigDecimal getAffairLeaveDays() 
    {
        return affairLeaveDays;
    }
    public void setAbsentEeismDays(BigDecimal absentEeismDays) 
    {
        this.absentEeismDays = absentEeismDays;
    }

    public BigDecimal getAbsentEeismDays() 
    {
        return absentEeismDays;
    }
    public void setSickLeaveDays(BigDecimal sickLeaveDays) 
    {
        this.sickLeaveDays = sickLeaveDays;
    }

    public BigDecimal getSickLeaveDays() 
    {
        return sickLeaveDays;
    }
    public void setSickLeaveDeduction(BigDecimal sickLeaveDeduction) 
    {
        this.sickLeaveDeduction = sickLeaveDeduction;
    }

    public BigDecimal getSickLeaveDeduction() 
    {
        return sickLeaveDeduction;
    }
    public void setTransportationFee(BigDecimal transportationFee) 
    {
        this.transportationFee = transportationFee;
    }

    public BigDecimal getTransportationFee() 
    {
        return transportationFee;
    }
    public void setCommunicationFee(BigDecimal communicationFee) 
    {
        this.communicationFee = communicationFee;
    }

    public BigDecimal getCommunicationFee() 
    {
        return communicationFee;
    }
    public void setMiddleShiftDay(BigDecimal middleShiftDay) 
    {
        this.middleShiftDay = middleShiftDay;
    }

    public BigDecimal getMiddleShiftDay() 
    {
        return middleShiftDay;
    }
    public void setNightShiftDays(BigDecimal nightShiftDays) 
    {
        this.nightShiftDays = nightShiftDays;
    }

    public BigDecimal getNightShiftDays() 
    {
        return nightShiftDays;
    }
    public void setHighTemperatureDays(BigDecimal highTemperatureDays) 
    {
        this.highTemperatureDays = highTemperatureDays;
    }

    public BigDecimal getHighTemperatureDays() 
    {
        return highTemperatureDays;
    }
    public void setOhtFee(BigDecimal ohtFee) 
    {
        this.ohtFee = ohtFee;
    }

    public BigDecimal getOhtFee() 
    {
        return ohtFee;
    }
    public void setHotWorkingDays(BigDecimal hotWorkingDays) 
    {
        this.hotWorkingDays = hotWorkingDays;
    }

    public BigDecimal getHotWorkingDays() 
    {
        return hotWorkingDays;
    }
    public void setTahwDays(BigDecimal tahwDays) 
    {
        this.tahwDays = tahwDays;
    }

    public BigDecimal getTahwDays() 
    {
        return tahwDays;
    }
    public void setReissue(BigDecimal reissue) 
    {
        this.reissue = reissue;
    }

    public BigDecimal getReissue() 
    {
        return reissue;
    }
    public void setWageDeduction(BigDecimal wageDeduction) 
    {
        this.wageDeduction = wageDeduction;
    }

    public BigDecimal getWageDeduction() 
    {
        return wageDeduction;
    }
    public void setPensionRepayment(BigDecimal pensionRepayment) 
    {
        this.pensionRepayment = pensionRepayment;
    }

    public BigDecimal getPensionRepayment() 
    {
        return pensionRepayment;
    }
    public void setMedicalRepayment(BigDecimal medicalRepayment) 
    {
        this.medicalRepayment = medicalRepayment;
    }

    public BigDecimal getMedicalRepayment() 
    {
        return medicalRepayment;
    }
    public void setUnemploymentRepayment(BigDecimal unemploymentRepayment) 
    {
        this.unemploymentRepayment = unemploymentRepayment;
    }

    public BigDecimal getUnemploymentRepayment() 
    {
        return unemploymentRepayment;
    }
    public void setHousingFundPayment(BigDecimal housingFundPayment) 
    {
        this.housingFundPayment = housingFundPayment;
    }

    public BigDecimal getHousingFundPayment() 
    {
        return housingFundPayment;
    }
    public void setTaxFreeIncome2(BigDecimal taxFreeIncome2) 
    {
        this.taxFreeIncome2 = taxFreeIncome2;
    }

    public BigDecimal getTaxFreeIncome2() 
    {
        return taxFreeIncome2;
    }
    public void setTaxCalculation(BigDecimal taxCalculation) 
    {
        this.taxCalculation = taxCalculation;
    }

    public BigDecimal getTaxCalculation() 
    {
        return taxCalculation;
    }
    public void setCompanyTax2(BigDecimal companyTax2) 
    {
        this.companyTax2 = companyTax2;
    }

    public BigDecimal getCompanyTax2() 
    {
        return companyTax2;
    }
    public void setExpense(BigDecimal expense) 
    {
        this.expense = expense;
    }

    public BigDecimal getExpense() 
    {
        return expense;
    }
    public void setExpense25(BigDecimal expense25) 
    {
        this.expense25 = expense25;
    }

    public BigDecimal getExpense25() 
    {
        return expense25;
    }
    public void setAfterTaxReissue(BigDecimal afterTaxReissue) 
    {
        this.afterTaxReissue = afterTaxReissue;
    }

    public BigDecimal getAfterTaxReissue() 
    {
        return afterTaxReissue;
    }
    public void setAfterTaxDeduction2(BigDecimal afterTaxDeduction2) 
    {
        this.afterTaxDeduction2 = afterTaxDeduction2;
    }

    public BigDecimal getAfterTaxDeduction2() 
    {
        return afterTaxDeduction2;
    }
    public void setIndividualTaxAdjustment(BigDecimal individualTaxAdjustment) 
    {
        this.individualTaxAdjustment = individualTaxAdjustment;
    }

    public BigDecimal getIndividualTaxAdjustment() 
    {
        return individualTaxAdjustment;
    }
    public void setAnnualBonus(BigDecimal annualBonus) 
    {
        this.annualBonus = annualBonus;
    }

    public BigDecimal getAnnualBonus() 
    {
        return annualBonus;
    }
    public void setRemarks2(String remarks2) 
    {
        this.remarks2 = remarks2;
    }

    public String getRemarks2() 
    {
        return remarks2;
    }
    public void setCsbRepayment(BigDecimal csbRepayment) 
    {
        this.csbRepayment = csbRepayment;
    }

    public BigDecimal getCsbRepayment() 
    {
        return csbRepayment;
    }
    public void setChfRepayment(BigDecimal chfRepayment) 
    {
        this.chfRepayment = chfRepayment;
    }

    public BigDecimal getChfRepayment() 
    {
        return chfRepayment;
    }
    public void setCdRepayment(BigDecimal cdRepayment) 
    {
        this.cdRepayment = cdRepayment;
    }

    public BigDecimal getCdRepayment() 
    {
        return cdRepayment;
    }
    public void setPayrollBelong(String payrollBelong) 
    {
        this.payrollBelong = payrollBelong;
    }

    public String getPayrollBelong() 
    {
        return payrollBelong;
    }
    public void setBasicDeductionAdjustment(BigDecimal basicDeductionAdjustment) 
    {
        this.basicDeductionAdjustment = basicDeductionAdjustment;
    }

    public BigDecimal getBasicDeductionAdjustment() 
    {
        return basicDeductionAdjustment;
    }
    public void setBankLocation(String bankLocation) 
    {
        this.bankLocation = bankLocation;
    }

    public String getBankLocation() 
    {
        return bankLocation;
    }
    public void setSeverance(BigDecimal severance) 
    {
        this.severance = severance;
    }

    public BigDecimal getSeverance() 
    {
        return severance;
    }
    public void setBirthdayGift(BigDecimal birthdayGift) 
    {
        this.birthdayGift = birthdayGift;
    }

    public BigDecimal getBirthdayGift() 
    {
        return birthdayGift;
    }
    public void setHeatingFee(BigDecimal heatingFee) 
    {
        this.heatingFee = heatingFee;
    }

    public BigDecimal getHeatingFee() 
    {
        return heatingFee;
    }
    public void setPaySbFlag(String paySbFlag)
    {
        this.paySbFlag = paySbFlag;
    }

    public String getPaySbFlag() 
    {
        return paySbFlag;
    }
    public void setPayHfFlag(String payHfFlag) 
    {
        this.payHfFlag = payHfFlag;
    }

    public String getPayHfFlag() 
    {
        return payHfFlag;
    }
    public void setPayIitFlag(String payIitFlag) 
    {
        this.payIitFlag = payIitFlag;
    }

    public String getPayIitFlag() 
    {
        return payIitFlag2;
    }
    public void setPayIitFlag2(String payIitFlag2) 
    {
        this.payIitFlag2 = payIitFlag2;
    }

    public String getPayIitFlag2() 
    {
        return payIitFlag2;
    }
    public void setClientCode(String clientCode) 
    {
        this.clientCode = clientCode;
    }

    public String getClientCode() 
    {
        return clientCode;
    }
    public void setEmployeeNo(String employeeNo) 
    {
        this.employeeNo = employeeNo;
    }

    public String getEmployeeNo() 
    {
        return employeeNo;
    }
    public void setCostCenter(String costCenter) 
    {
        this.costCenter = costCenter;
    }

    public String getCostCenter() 
    {
        return costCenter;
    }
    public void setActualPerformance(BigDecimal actualPerformance) 
    {
        this.actualPerformance = actualPerformance;
    }

    public BigDecimal getActualPerformance() 
    {
        return actualPerformance;
    }
    public void setPerformanceBonusBase(BigDecimal performanceBonusBase) 
    {
        this.performanceBonusBase = performanceBonusBase;
    }

    public BigDecimal getPerformanceBonusBase() 
    {
        return performanceBonusBase;
    }
    public void setCommission(BigDecimal commission) 
    {
        this.commission = commission;
    }

    public BigDecimal getCommission() 
    {
        return commission;
    }
    public void setGroupIds(String groupIds) 
    {
        this.groupIds = groupIds;
    }

    public String getGroupIds() 
    {
        return groupIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("duration", getDuration())
            .append("erName", getErName())
            .append("idNo", getIdNo())
            .append("name", getName())
            .append("enName", getEnName())
            .append("sex", getSex())
            .append("clientType", getClientType())
            .append("workAddress", getWorkAddress())
            .append("socialBenefitsAddress", getSocialBenefitsAddress())
            .append("housingFundAddress", getHousingFundAddress())
            .append("employmentNature", getEmploymentNature())
            .append("serviceStarttime", getServiceStarttime())
            .append("contractAddress", getContractAddress())
            .append("contractStarttime", getContractStarttime())
            .append("contractEndtime", getContractEndtime())
            .append("leaveDate", getLeaveDate())
            .append("preAfterTax", getPreAfterTax())
            .append("basicSalary", getBasicSalary())
            .append("dailySalary", getDailySalary())
            .append("floatingSalary", getFloatingSalary())
            .append("transportAllowance", getTransportAllowance())
            .append("communicationAllowance", getCommunicationAllowance())
            .append("otherFixedAllowance", getOtherFixedAllowance())
            .append("msaBase", getMsaBase())
            .append("nsaBase", getNsaBase())
            .append("hwaBase", getHwaBase())
            .append("hwhfBase", getHwhfBase())
            .append("phfBase", getPhfBase())
            .append("onlyChildBirthday", getOnlyChildBirthday())
            .append("ocaBase", getOcaBase())
            .append("fixedSubsidy", getFixedSubsidy())
            .append("fixedDeduction", getFixedDeduction())
            .append("pensionBase", getPensionBase())
            .append("medicalBase", getMedicalBase())
            .append("unemploymentBase", getUnemploymentBase())
            .append("maternityBase", getMaternityBase())
            .append("workRelatedInjuryBase", getWorkRelatedInjuryBase())
            .append("disabilityBase", getDisabilityBase())
            .append("housingFundBase", getHousingFundBase())
            .append("shfBase", getShfBase())
            .append("personalPension", getPersonalPension())
            .append("personalMedical", getPersonalMedical())
            .append("personalUnemployment", getPersonalUnemployment())
            .append("personalHousingFund", getPersonalHousingFund())
            .append("personalShfTe", getPersonalShfTe())
            .append("personalShfNte", getPersonalShfNte())
            .append("personalMedicalFixedNumber", getPersonalMedicalFixedNumber())
            .append("companyPension", getCompanyPension())
            .append("companyMedical", getCompanyMedical())
            .append("companyUnemployment", getCompanyUnemployment())
            .append("companyMaternity", getCompanyMaternity())
            .append("companyWorkRelatedInjury", getCompanyWorkRelatedInjury())
            .append("companyDisability", getCompanyDisability())
            .append("companyHousingFund", getCompanyHousingFund())
            .append("companyShf", getCompanyShf())
            .append("companyMedicalFixedNumber", getCompanyMedicalFixedNumber())
            .append("companyWriFixedNumber", getCompanyWriFixedNumber())
            .append("companyDisabilityFixedNumber", getCompanyDisabilityFixedNumber())
            .append("annuity", getAnnuity())
            .append("unionFee", getUnionFee())
            .append("taxNotIssued", getTaxNotIssued())
            .append("companyTax", getCompanyTax())
            .append("fsAllowance", getFsAllowance())
            .append("afterTaxDeduction", getAfterTaxDeduction())
            .append("taxFreeIncome", getTaxFreeIncome())
            .append("certificateType", getCertificateType())
            .append("certificateNumber", getCertificateNumber())
            .append("birthday", getBirthday())
            .append("housingFundAccount", getHousingFundAccount())
            .append("bankAccounName", getBankAccounName())
            .append("bankAccount", getBankAccount())
            .append("bank", getBank())
            .append("department", getDepartment())
            .append("post", getPost())
            .append("nationality", getNationality())
            .append("nationalityCode", getNationalityCode())
            .append("payroll", getPayroll())
            .append("telephone", getTelephone())
            .append("mailbox", getMailbox())
            .append("billAddress", getBillAddress())
            .append("postcode", getPostcode())
            .append("residenceType", getResidenceType())
            .append("residenceAddress", getResidenceAddress())
            .append("commercialInsuranceCost", getCommercialInsuranceCost())
            .append("commercialInsuranceLevel", getCommercialInsuranceLevel())
            .append("spouseChildrenInsurance", getSpouseChildrenInsurance())
            .append("greenAllowance", getGreenAllowance())
            .append("physicalExamination", getPhysicalExamination())
            .append("fileManagementFee", getFileManagementFee())
            .append("annualBonusInfo", getAnnualBonusInfo())
            .append("thirteenSalaryMonth", getThirteenSalaryMonth())
            .append("remarks", getRemarks())
            .append("taxPaymentPlace", getTaxPaymentPlace())
            .append("taxFreeItem", getTaxFreeItem())
            .append("accumulatedChildEducation", getAccumulatedChildEducation())
            .append("accumulatedContinuingEducation", getAccumulatedContinuingEducation())
            .append("accumulatedHli", getAccumulatedHli())
            .append("accumulatedHousingRent", getAccumulatedHousingRent())
            .append("accumulatedSfte", getAccumulatedSfte())
            .append("commercialHealthInsurance", getCommercialHealthInsurance())
            .append("tdpInsurance", getTdpInsurance())
            .append("otherTaxFreeDeductions", getOtherTaxFreeDeductions())
            .append("dailyAttendanceDays", getDailyAttendanceDays())
            .append("monthlyAward", getMonthlyAward())
            .append("quarterAward", getQuarterAward())
            .append("overtime150hours", getOvertime150hours())
            .append("overtime200hours", getOvertime200hours())
            .append("overtime300hours", getOvertime300hours())
            .append("fullAttendanceDays", getFullAttendanceDays())
            .append("ealDays", getEalDays())
            .append("affairLeaveDays", getAffairLeaveDays())
            .append("absentEeismDays", getAbsentEeismDays())
            .append("sickLeaveDays", getSickLeaveDays())
            .append("sickLeaveDeduction", getSickLeaveDeduction())
            .append("transportationFee", getTransportationFee())
            .append("communicationFee", getCommunicationFee())
            .append("middleShiftDay", getMiddleShiftDay())
            .append("nightShiftDays", getNightShiftDays())
            .append("highTemperatureDays", getHighTemperatureDays())
            .append("ohtFee", getOhtFee())
            .append("hotWorkingDays", getHotWorkingDays())
            .append("tahwDays", getTahwDays())
            .append("reissue", getReissue())
            .append("wageDeduction", getWageDeduction())
            .append("pensionRepayment", getPensionRepayment())
            .append("medicalRepayment", getMedicalRepayment())
            .append("unemploymentRepayment", getUnemploymentRepayment())
            .append("housingFundPayment", getHousingFundPayment())
            .append("taxFreeIncome2", getTaxFreeIncome2())
            .append("taxCalculation", getTaxCalculation())
            .append("companyTax2", getCompanyTax2())
            .append("expense", getExpense())
            .append("expense25", getExpense25())
            .append("afterTaxReissue", getAfterTaxReissue())
            .append("afterTaxDeduction2", getAfterTaxDeduction2())
            .append("individualTaxAdjustment", getIndividualTaxAdjustment())
            .append("annualBonus", getAnnualBonus())
            .append("remarks2", getRemarks2())
            .append("csbRepayment", getCsbRepayment())
            .append("chfRepayment", getChfRepayment())
            .append("cdRepayment", getCdRepayment())
            .append("payrollBelong", getPayrollBelong())
            .append("basicDeductionAdjustment", getBasicDeductionAdjustment())
            .append("bankLocation", getBankLocation())
            .append("severance", getSeverance())
            .append("birthdayGift", getBirthdayGift())
            .append("heatingFee", getHeatingFee())
            .append("paySbFlag", getPaySbFlag())
            .append("payHfFlag", getPayHfFlag())
            .append("payIitFlag", getPayIitFlag())
            .append("payIitFlag2", getPayIitFlag2())
            .append("clientCode", getClientCode())
            .append("employeeNo", getEmployeeNo())
            .append("costCenter", getCostCenter())
            .append("actualPerformance", getActualPerformance())
            .append("performanceBonusBase", getPerformanceBonusBase())
            .append("commission", getCommission())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("groupIds", getGroupIds())
            .toString();
    }
}
