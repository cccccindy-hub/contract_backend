package com.nnroad.payroll.domain.common;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * ps_basic_info对象 ps_basic_info
 *
 */
public class PsBasicInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * Duration
     */
    @Excel(name = "期间")
    private String duration;

    /**
     * client  short
     */
    @Excel(name = "客户简称")
    private String erName;

    /**
     * id no
     */
    @Excel(name = "工号")
    private String idNo;

    /**
     * name
     */
    @Excel(name = "姓名")
    private String name;

    /**
     * English name
     */
    @Excel(name = "英文名")
    private String enName;

    /**
     * sex
     */
    @Excel(name = "性别")
    private String sex;

    /**
     * client  type
     */
    @Excel(name = "客户类型")
    private String clientType;

    /**
     * work address
     */
    @Excel(name = "工作地")
    private String workAddress;

    /**
     * social benefits address
     */
    @Excel(name = "社保缴纳地")
    private String socialBenefitsAddress;

    /**
     * housing fund address
     */
    @Excel(name = "公积金缴纳地")
    private String housingFundAddress;

    /**
     * employment nature
     */
    @Excel(name = "用工性质")
    private String employmentNature;

    /**
     * service start time
     */
    @Excel(name = "服务起始日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String serviceStarttime;

    /**
     * contract address
     */
    @Excel(name = "合同所属地")
    private String contractAddress;

    /**
     * contract starttime
     */
    @Excel(name = "合同起始日")
    private String contractStarttime;

    /**
     * contract endtime
     */
    @Excel(name = "合同终止日")
    private String contractEndtime;

    /**
     * leave date
     */
    @Excel(name = "离职日期")
    private String leaveDate;

    /**
     * 税前/税后
     */
    @Excel(name = "税前/税后")
    private String preAfterTax;

    /**
     * basic salary
     */
    @Excel(name = "基本工资")
    private BigDecimal basicSalary;

    /**
     * daily salary
     */
    @Excel(name = "日薪")
    private BigDecimal dailySalary;

    /**
     * floating salary
     */
    @Excel(name = "浮动工资")
    private BigDecimal floatingSalary;

    /**
     * transport  allowance
     */
    @Excel(name = "交通津贴")
    private BigDecimal transportAllowance;

    /**
     * communication allowance
     */
    @Excel(name = "通讯津贴")
    private BigDecimal communicationAllowance;

    @Excel(name = "餐食津贴")
    private BigDecimal mealAllowance;

    /**
     * other fixed allowance
     */
    @Excel(name = "其他固定津贴")
    private BigDecimal otherFixedAllowance;

    /**
     * Basis for middle shift allowance Calculation
     */
    @Excel(name = "中班津贴基数")
    private BigDecimal msaBase;

    /**
     * Basis for night shift allowance  Calculation
     */
    @Excel(name = "夜班津贴基数")
    private BigDecimal nsaBase;

    /**
     * Basis for hot weather allowance  Calculation
     */
    @Excel(name = "高温津贴基数")
    private BigDecimal hwaBase;

    /**
     * Basis for hot weather health food  Calculation
     */
    @Excel(name = "高温作业保健食品费基数")
    private BigDecimal hwhfBase;

    /**
     * Basis for poisonous health food  Calculation
     */
    @Excel(name = "有毒有害保健食品费基数")
    private BigDecimal phfBase;

    /**
     * only child birthday
     */
    @Excel(name = "独生子女出生日期")
    private BigDecimal onlyChildBirthday;

    /**
     * Basis for only child allowance cardinality
     */
    @Excel(name = "独生子女津贴基数")
    private BigDecimal ocaBase;

    /**
     * fixed subsidy
     */
    @Excel(name = "固定补")
    private BigDecimal fixedSubsidy;

    /**
     * fixed deduction
     */
    @Excel(name = "固定扣")
    private BigDecimal fixedDeduction;

    /**
     * Pension base
     */
    @Excel(name = "养老基数")
    private BigDecimal pensionBase;

    /**
     * Medical base
     */
    @Excel(name = "医疗基数")
    private BigDecimal medicalBase;

    /**
     * Unemployment base
     */
    @Excel(name = "失业基数")
    private BigDecimal unemploymentBase;

    /**
     * Maternity base
     */
    @Excel(name = "生育基数")
    private BigDecimal maternityBase;

    /**
     * Work-Related-Injury base
     */
    @Excel(name = "工伤基数")
    private BigDecimal workRelatedInjuryBase;

    /**
     * Disability base
     */
    @Excel(name = "残保金基数")
    private BigDecimal disabilityBase;

    /**
     * Housing Fund Base
     */
    @Excel(name = "公积金基数")
    private BigDecimal housingFundBase;

    /**
     * Supplementary Housing Fund base
     */
    @Excel(name = "补充公积金基数")
    private BigDecimal shfBase;

    /**
     * Personal pension%
     */
    @Excel(name = "个人养老%")
    private String personalPension;

    /**
     * Personal medical%
     */
    @Excel(name = "个人医疗%")
    private String personalMedical;

    /**
     * Personal unemployment%
     */
    @Excel(name = "个人失业%")
    private String personalUnemployment;

    /**
     * Personal Housing Fund%
     */
    @Excel(name = "个人公积金%")
    private String personalHousingFund;

    /**
     * Personal Supplementary Provident Fund% (tax exempt)
     */
    @Excel(name = "个人补充公积金%（免税）")
    private String personalShfTe;

    /**
     * Personal Supplementary Provident Fund% (not exempt from tax)
     */
    @Excel(name = "个人补充公积金%（不免税）")
    private String personalShfNte;

    /**
     * Personal medical fixed number
     */
    @Excel(name = "个人医疗固定数")
    private BigDecimal personalMedicalFixedNumber;

    /**
     * Company pension%
     */
    @Excel(name = "公司养老%")
    private String companyPension;

    /**
     * Company medical%
     */
    @Excel(name = "公司医疗%")
    private String companyMedical;

    /**
     * Company unemployment%
     */
    @Excel(name = "公司失业%")
    private String companyUnemployment;

    /**
     * Company maternity%
     */
    @Excel(name = "公司生育%")
    private String companyMaternity;

    /**
     * Company Work-Related-Injury%
     */
    @Excel(name = "公司工伤%")
    private String companyWorkRelatedInjury;

    /**
     * Company disability insurance fund%
     */
    @Excel(name = "公司残保金%")
    private String companyDisability;

    /**
     * Company Housing Fund%
     */
    @Excel(name = "公司公积金%")
    private String companyHousingFund;

    /**
     * Company supplementary Housing Fund%
     */
    @Excel(name = "公司补充公积金%")
    private String companyShf;

    /**
     * Company medical fixed number
     */
    @Excel(name = "公司医疗固定数")
    private BigDecimal companyMedicalFixedNumber;

    /**
     * Fixed number of company injuries
     */
    @Excel(name = "公司工伤固定数")
    private BigDecimal companyWriFixedNumber;

    /**
     * Fixed number of company disability insurance
     */
    @Excel(name = "公司残保固定数")
    private BigDecimal companyDisabilityFixedNumber;

    /**
     * Annuity%
     */
    @Excel(name = "年金%")
    private String annuity;

    /**
     * Union fee
     */
    @Excel(name = "工会费")
    private BigDecimal unionFee;

    /**
     * 计税不发
     */
    @Excel(name = "商业保险计税不发")
    private BigDecimal taxNotIssued;

    /**
     * 公司负税1
     */
    @Excel(name = "公司负税1")
    private BigDecimal companyTax;

    /**
     * Subsistence Allowance for Foreigners (tax exempt)
     */
    @Excel(name = "外籍生活津贴（免税）")
    private BigDecimal fsAllowance;

    /**
     * 税后扣1
     */
    @Excel(name = "税后扣1")
    private BigDecimal afterTaxDeduction;

    /**
     * 免税收入1
     */
    @Excel(name = "免税收入1")
    private BigDecimal taxFreeIncome;

    /**
     * type of certificate
     */
    @Excel(name = "证件类型")
    private String certificateType;

    /**
     * certificate number
     */
    @Excel(name = "证件号码")
    private String certificateNumber;

    /**
     * birthday
     */
    @Excel(name = "生日")
    private String birthday;

    /**
     * Housing Fund Account
     */
    @Excel(name = "公积金账号")
    private String housingFundAccount;

    /**
     * Bank account name
     */
    @Excel(name = "银行户名")
    private String bankAccounName;

    /**
     * Bank Account
     */
    @Excel(name = "银行账号")
    private String bankAccount;

    /**
     * bank
     */
    @Excel(name = "银行")
    private String bank;

    /**
     * department
     */
    @Excel(name = "部门")
    private String department;

    /**
     * post
     */
    @Excel(name = "岗位")
    private String post;

    /**
     * Nationality/ethnicity
     */
    @Excel(name = "国籍/民族")
    private String nationality;

    /**
     * Nationality code
     */
    @Excel(name = "国籍代码")
    private String nationalityCode;

    /**
     * Payroll
     */
    @Excel(name = "工资单")
    private String payroll;

    /**
     * Telephone / Cell phone
     */
    @Excel(name = "电话/手机")
    private String telephone;

    /**
     * mailbox
     */
    @Excel(name = "邮箱")
    private String mailbox;

    /**
     * Billing address
     */
    @Excel(name = "账单邮寄地址")
    private String billAddress;

    /**
     * Postcode
     */
    @Excel(name = "邮编")
    private String postcode;

    /**
     * Residence type
     */
    @Excel(name = "户口性质")
    private String residenceType;

    /**
     * Residence address
     */
    @Excel(name = "户籍地址")
    private String residenceAddress;

    /**
     * Total cost of commercial insurance
     */
    @Excel(name = "商业保险总费用")
    private BigDecimal commercialInsuranceCost;

    /**
     * Commercial insurance level
     */
    @Excel(name = "商业险级别")
    private String commercialInsuranceLevel;

    /**
     * Spouse/Children Insurance
     */
    @Excel(name = "配偶/子女险")
    private BigDecimal spouseChildrenInsurance;

    /**
     * Green allowance
     */
    @Excel(name = "绿色津贴")
    private BigDecimal greenAllowance;

    /**
     * Physical examination
     */
    @Excel(name = "体检")
    private BigDecimal physicalExamination;

    /**
     * File management fee
     */
    @Excel(name = "档案管理费")
    private BigDecimal fileManagementFee;

    /**
     * 13th Salary/Annual Bonus information
     */
    @Excel(name = "13薪/年终奖信息")
    private String annualBonusInfo;

    /**
     * 13 salary month
     */
    @Excel(name = "13薪月份")
    private BigDecimal thirteenSalaryMonth;

    /**
     * Remarks
     */
    @Excel(name = "备注1")
    private String remarks;

    /**
     * 个税缴纳地
     */
    @Excel(name = "个税缴纳地")
    private String taxPaymentPlace;

    /**
     * 免税不发项
     */
    @Excel(name = "免税不发项")
    private BigDecimal taxFreeItem;

    /**
     * Accumulated child education
     */
    @Excel(name = "累计子女教育")
    private BigDecimal accumulatedChildEducation;

    /**
     * Accumulated continuing education
     */
    @Excel(name = "累计继续教育")
    private BigDecimal accumulatedContinuingEducation;

    /**
     * Accumulated housing loan interest
     */
    @Excel(name = "累计住房贷款利息")
    private BigDecimal accumulatedHli;

    /**
     * Accumulated housing rent
     */
    @Excel(name = "累计住房租金")
    private BigDecimal accumulatedHousingRent;

    /**
     * Accumulated support for the elderly
     */
    @Excel(name = "累计赡养老人")
    private BigDecimal accumulatedSfte;

    /**
     * Commercial health insurance
     */
    @Excel(name = "商业健康保险")
    private BigDecimal commercialHealthInsurance;

    /**
     * Tax deferred pension insurance
     */
    @Excel(name = "税延养老保险")
    private BigDecimal tdpInsurance;

    /**
     * Other tax-free deductions (donations)
     */
    @Excel(name = "其他免税扣除（捐款）")
    private BigDecimal otherTaxFreeDeductions;

    /**
     * Daily attendance days
     */
    @Excel(name = "日薪出勤天数")
    private BigDecimal dailyAttendanceDays;

    /**
     * Monthly award
     */
    @Excel(name = "月奖")
    private BigDecimal monthlyAward;

    /**
     * Quarter award
     */
    @Excel(name = "季奖")
    private BigDecimal quarterAward;

    /**
     * Overtime 150 hours
     */
    @Excel(name = "加班小时150")
    private String overtime150hours;

    /**
     * Overtime 200 hours
     */
    @Excel(name = "加班小时200")
    private String overtime200hours;

    /**
     * Overtime 300 hours
     */
    @Excel(name = "加班小时300")
    private String overtime300hours;

    /**
     * Full attendance day of the month
     */
    @Excel(name = "当月全勤日")
    private BigDecimal fullAttendanceDays;

    /**
     * Days of entry and leave
     */
    @Excel(name = "入离职缺勤天数")
    private BigDecimal ealDays;

    /**
     * Affair Leave days
     */
    @Excel(name = "事假天数")
    private BigDecimal affairLeaveDays;

    /**
     * Absent eeism days
     */
    @Excel(name = "旷工天数")
    private BigDecimal absentEeismDays;

    /**
     * Sick leave days
     */
    @Excel(name = "病假天数")
    private BigDecimal sickLeaveDays;

    /**
     * Sick leave deduction
     */
    @Excel(name = "扣病假工资")
    private BigDecimal sickLeaveDeduction;

    /**
     * Transportation
     */
    @Excel(name = "交通费")
    private BigDecimal transportationFee;

    /**
     * Communication fee
     */
    @Excel(name = "通讯费")
    private BigDecimal communicationFee;

    /**
     * Middle shift days
     */
    @Excel(name = "中班天数")
    private BigDecimal middleShiftDay;

    /**
     * Night shift days
     */
    @Excel(name = "夜班天数")
    private BigDecimal nightShiftDays;

    /**
     * High temperature days
     */
    @Excel(name = "高温天数")
    private BigDecimal highTemperatureDays;

    /**
     * One-time high temperature fee
     */
    @Excel(name = "一次性高温费")
    private BigDecimal ohtFee;

    /**
     * Hot working days
     */
    @Excel(name = "高温作业天数")
    private BigDecimal hotWorkingDays;

    /**
     * Toxic and harmful working days
     */
    @Excel(name = "有毒有害作业天数")
    private BigDecimal tahwDays;

    /**
     * Reissue
     */
    @Excel(name = "补发")
    private BigDecimal reissue;

    /**
     * Wage deduction
     */
    @Excel(name = "补扣")
    private BigDecimal wageDeduction;

    /**
     * Pension repayment
     */
    @Excel(name = "养老补缴")
    private BigDecimal pensionRepayment;

    /**
     * Medical repayment
     */
    @Excel(name = "医疗补缴")
    private BigDecimal medicalRepayment;

    /**
     * Unemployment repayment
     */
    @Excel(name = "失业补缴")
    private BigDecimal unemploymentRepayment;

    /**
     * Housing fund repayment
     */
    @Excel(name = "公积金补缴")
    private BigDecimal housingFundPayment;

    /**
     * Tax-free income2
     */
    @Excel(name = "免税收入2")
    private BigDecimal taxFreeIncome2;

    /**
     * 计税不发2
     */
    @Excel(name = "计税不发2")
    private BigDecimal taxCalculation;

    /**
     * Company tax
     */
    @Excel(name = "公司负税2")
    private BigDecimal companyTax2;

    /**
     * Expense
     */
    @Excel(name = "报销款")
    private BigDecimal expense;

    /**
     * Expenset 25%
     */
    @Excel(name = "报销款25%")
    private BigDecimal expense25;

    /**
     * After-tax reissue
     */
    @Excel(name = "税后补")
    private BigDecimal afterTaxReissue;

    /**
     * After-tax deduction
     */
    @Excel(name = "税后扣2")
    private BigDecimal afterTaxDeduction2;

    /**
     * Individual tax adjustment
     */
    @Excel(name = "个税调整")
    private BigDecimal individualTaxAdjustment;

    /**
     * Annual Bonus/13th Salary
     */
    @Excel(name = "13薪/年终奖")
    private BigDecimal annualBonus;

    /**
     * Remarks2
     */
    @Excel(name = "备注2")
    private String remarks2;

    /**
     * Company Social Benefits repayment
     */
    @Excel(name = "公司社保补缴")
    private BigDecimal csbRepayment;

    /**
     * Company Housing Fund repayment
     */
    @Excel(name = "公司公积金补缴")
    private BigDecimal chfRepayment;

    /**
     * Company Disability repayment
     */
    @Excel(name = "残保金补缴")
    private BigDecimal cdRepayment;

    /**
     * Payroll belong
     */
    @Excel(name = "发薪所属")
    private String payrollBelong;

    /**
     * Basic deduction adjustment
     */
    // @Excel(name = "basic_deduction_adjustment")
    private BigDecimal basicDeductionAdjustment;

    /**
     * Location of bank
     */
    @Excel(name = "开户行所在地")
    private String bankLocation;

    /**
     * 离职补偿金
     */
    @Excel(name = "离职补偿金")
    private BigDecimal severance;

    /**
     * 生日礼金
     */
    @Excel(name = "生日礼金")
    private BigDecimal birthdayGift;

    @Excel(name = "其他津贴")
    private BigDecimal otherAllowance;

    /**
     * 取暖费
     */
    @Excel(name = "取暖费")
    private BigDecimal heatingFee;

    /**
     * 是否代缴社保
     */
    @Excel(name = "是否代缴社保")
    private String paySBFlag;

    /**
     * 是否代缴公积金
     */
    @Excel(name = "是否代缴公积金")
    private String payHFFlag;

    /**
     * 是否代缴个税
     */
    @Excel(name = "是否代缴个税")
    private String payIITFlag;

    /**
     * 客户编号
     */
    @Excel(name = "客户编号")
    private String clientCode;

    /**
     * 客户员工工号
     */
    @Excel(name = "客户员工工号")
    private String employeeNo;

    /**
     * 成本中心/项目编号
     */
    @Excel(name = "成本中心/项目编号")
    private String costCenter;

    /**
     * 实际绩效
     */
    @Excel(name = "实际绩效")
    private BigDecimal actualPerformance;

    /**
     * 绩效奖金基数
     */
    @Excel(name = "绩效奖金基数")
    private BigDecimal performanceBonusBase;

    /**
     * 提成
     */
    @Excel(name = "提成")
    private BigDecimal commission;

    /**
     * 其他奖金
     */
    @Excel(name = "其他奖金")
    private BigDecimal otherBonus;

    /**
     * 税前调整
     */
    @Excel(name = "税前调整")
    private BigDecimal pretaxAdjustment;

    /**
     * 离职补偿金/赔偿金
     */
    @Excel(name = "离职补偿金/赔偿金")
    private BigDecimal indemnity;

    /**
     * 离职补偿金/赔偿金免税额
     */
    @Excel(name = "离职补偿金/赔偿金免税额")
    private BigDecimal indemnityAllowance;

    /**
     * 补偿计算年限
     */
    @Excel(name = "补偿计算年限")
    private String compensationCalculationYear;

    /**
     * 股权激励
     */
    @Excel(name = "股权激励")
    private BigDecimal shareIncentives;

    /**
     * 出差补贴
     */
    @Excel(name = "出差补贴")
    private BigDecimal travelAllowance;

    /**
     * 累计婴幼儿照护费用
     */
    @Excel(name = "累计婴幼儿照护费用")
    private BigDecimal accumulatedChildCare;

    private String groupIds;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setErName(String erName) {
        this.erName = erName;
    }

    public String getErName() {
        return erName;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getEnName() {
        return enName;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getClientType() {
        return clientType;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setSocialBenefitsAddress(String socialBenefitsAddress) {
        this.socialBenefitsAddress = socialBenefitsAddress;
    }

    public String getSocialBenefitsAddress() {
        return socialBenefitsAddress;
    }

    public void setHousingFundAddress(String housingFundAddress) {
        this.housingFundAddress = housingFundAddress;
    }

    public String getHousingFundAddress() {
        return housingFundAddress;
    }

    public void setEmploymentNature(String employmentNature) {
        this.employmentNature = employmentNature;
    }

    public String getEmploymentNature() {
        return employmentNature;
    }

    public void setServiceStarttime(String serviceStarttime) {
        this.serviceStarttime = serviceStarttime;
    }

    public String getServiceStarttime() {
        return serviceStarttime;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractStarttime(String contractStarttime) {
        this.contractStarttime = contractStarttime;
    }

    public String getContractStarttime() {
        return contractStarttime;
    }

    public void setContractEndtime(String contractEndtime) {
        this.contractEndtime = contractEndtime;
    }

    public String getContractEndtime() {
        return contractEndtime;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setPreAfterTax(String preAfterTax) {
        this.preAfterTax = preAfterTax;
    }

    public String getPreAfterTax() {
        return preAfterTax;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setDailySalary(BigDecimal dailySalary) {
        this.dailySalary = dailySalary;
    }

    public BigDecimal getDailySalary() {
        return dailySalary;
    }

    public void setFloatingSalary(BigDecimal floatingSalary) {
        this.floatingSalary = floatingSalary;
    }

    public BigDecimal getFloatingSalary() {
        return floatingSalary;
    }

    public void setTransportAllowance(BigDecimal transportAllowance) {
        this.transportAllowance = transportAllowance;
    }

    public BigDecimal getTransportAllowance() {
        return transportAllowance;
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

    public BigDecimal getCommunicationAllowance() {
        return communicationAllowance;
    }

    public void setOtherFixedAllowance(BigDecimal otherFixedAllowance) {
        this.otherFixedAllowance = otherFixedAllowance;
    }

    public BigDecimal getOtherFixedAllowance() {
        return otherFixedAllowance;
    }

    public void setMsaBase(BigDecimal msaBase) {
        this.msaBase = msaBase;
    }

    public BigDecimal getMsaBase() {
        return msaBase;
    }

    public void setNsaBase(BigDecimal nsaBase) {
        this.nsaBase = nsaBase;
    }

    public BigDecimal getNsaBase() {
        return nsaBase;
    }

    public void setHwaBase(BigDecimal hwaBase) {
        this.hwaBase = hwaBase;
    }

    public BigDecimal getHwaBase() {
        return hwaBase;
    }

    public void setHwhfBase(BigDecimal hwhfBase) {
        this.hwhfBase = hwhfBase;
    }

    public BigDecimal getHwhfBase() {
        return hwhfBase;
    }

    public void setPhfBase(BigDecimal phfBase) {
        this.phfBase = phfBase;
    }

    public BigDecimal getPhfBase() {
        return phfBase;
    }

    public void setOnlyChildBirthday(BigDecimal onlyChildBirthday) {
        this.onlyChildBirthday = onlyChildBirthday;
    }

    public BigDecimal getOnlyChildBirthday() {
        return onlyChildBirthday;
    }

    public void setOcaBase(BigDecimal ocaBase) {
        this.ocaBase = ocaBase;
    }

    public BigDecimal getOcaBase() {
        return ocaBase;
    }

    public void setFixedSubsidy(BigDecimal fixedSubsidy) {
        this.fixedSubsidy = fixedSubsidy;
    }

    public BigDecimal getFixedSubsidy() {
        return fixedSubsidy;
    }

    public void setFixedDeduction(BigDecimal fixedDeduction) {
        this.fixedDeduction = fixedDeduction;
    }

    public BigDecimal getFixedDeduction() {
        return fixedDeduction;
    }

    public void setPensionBase(BigDecimal pensionBase) {
        this.pensionBase = pensionBase;
    }

    public BigDecimal getPensionBase() {
        return pensionBase;
    }

    public void setMedicalBase(BigDecimal medicalBase) {
        this.medicalBase = medicalBase;
    }

    public BigDecimal getMedicalBase() {
        return medicalBase;
    }

    public void setUnemploymentBase(BigDecimal unemploymentBase) {
        this.unemploymentBase = unemploymentBase;
    }

    public BigDecimal getUnemploymentBase() {
        return unemploymentBase;
    }

    public void setMaternityBase(BigDecimal maternityBase) {
        this.maternityBase = maternityBase;
    }

    public BigDecimal getMaternityBase() {
        return maternityBase;
    }

    public void setWorkRelatedInjuryBase(BigDecimal workRelatedInjuryBase) {
        this.workRelatedInjuryBase = workRelatedInjuryBase;
    }

    public BigDecimal getWorkRelatedInjuryBase() {
        return workRelatedInjuryBase;
    }

    public void setDisabilityBase(BigDecimal disabilityBase) {
        this.disabilityBase = disabilityBase;
    }

    public BigDecimal getDisabilityBase() {
        return disabilityBase;
    }

    public void setHousingFundBase(BigDecimal housingFundBase) {
        this.housingFundBase = housingFundBase;
    }

    public BigDecimal getHousingFundBase() {
        return housingFundBase;
    }

    public void setShfBase(BigDecimal shfBase) {
        this.shfBase = shfBase;
    }

    public BigDecimal getShfBase() {
        return shfBase;
    }

    public void setPersonalPension(String personalPension) {
        this.personalPension = personalPension;
    }

    public String getPersonalPension() {
        return personalPension;
    }

    public void setPersonalMedical(String personalMedical) {
        this.personalMedical = personalMedical;
    }

    public String getPersonalMedical() {
        return personalMedical;
    }

    public void setPersonalUnemployment(String personalUnemployment) {
        this.personalUnemployment = personalUnemployment;
    }

    public String getPersonalUnemployment() {
        return personalUnemployment;
    }

    public void setPersonalHousingFund(String personalHousingFund) {
        this.personalHousingFund = personalHousingFund;
    }

    public String getPersonalHousingFund() {
        return personalHousingFund;
    }

    public void setPersonalShfTe(String personalShfTe) {
        this.personalShfTe = personalShfTe;
    }

    public String getPersonalShfTe() {
        return personalShfTe;
    }

    public void setPersonalShfNte(String personalShfNte) {
        this.personalShfNte = personalShfNte;
    }

    public String getPersonalShfNte() {
        return personalShfNte;
    }

    public void setPersonalMedicalFixedNumber(BigDecimal personalMedicalFixedNumber) {
        this.personalMedicalFixedNumber = personalMedicalFixedNumber;
    }

    public BigDecimal getPersonalMedicalFixedNumber() {
        return personalMedicalFixedNumber;
    }

    public void setCompanyPension(String companyPension) {
        this.companyPension = companyPension;
    }

    public String getCompanyPension() {
        return companyPension;
    }

    public void setCompanyMedical(String companyMedical) {
        this.companyMedical = companyMedical;
    }

    public String getCompanyMedical() {
        return companyMedical;
    }

    public void setCompanyUnemployment(String companyUnemployment) {
        this.companyUnemployment = companyUnemployment;
    }

    public String getCompanyUnemployment() {
        return companyUnemployment;
    }

    public void setCompanyMaternity(String companyMaternity) {
        this.companyMaternity = companyMaternity;
    }

    public String getCompanyMaternity() {
        return companyMaternity;
    }

    public void setCompanyWorkRelatedInjury(String companyWorkRelatedInjury) {
        this.companyWorkRelatedInjury = companyWorkRelatedInjury;
    }

    public String getCompanyWorkRelatedInjury() {
        return companyWorkRelatedInjury;
    }

    public void setCompanyDisability(String companyDisability) {
        this.companyDisability = companyDisability;
    }

    public String getCompanyDisability() {
        return companyDisability;
    }

    public void setCompanyHousingFund(String companyHousingFund) {
        this.companyHousingFund = companyHousingFund;
    }

    public String getCompanyHousingFund() {
        return companyHousingFund;
    }

    public void setCompanyShf(String companyShf) {
        this.companyShf = companyShf;
    }

    public String getCompanyShf() {
        return companyShf;
    }

    public void setCompanyMedicalFixedNumber(BigDecimal companyMedicalFixedNumber) {
        this.companyMedicalFixedNumber = companyMedicalFixedNumber;
    }

    public BigDecimal getCompanyMedicalFixedNumber() {
        return companyMedicalFixedNumber;
    }

    public void setCompanyWriFixedNumber(BigDecimal companyWriFixedNumber) {
        this.companyWriFixedNumber = companyWriFixedNumber;
    }

    public BigDecimal getCompanyWriFixedNumber() {
        return companyWriFixedNumber;
    }

    public void setCompanyDisabilityFixedNumber(BigDecimal companyDisabilityFixedNumber) {
        this.companyDisabilityFixedNumber = companyDisabilityFixedNumber;
    }

    public BigDecimal getCompanyDisabilityFixedNumber() {
        return companyDisabilityFixedNumber;
    }

    public void setAnnuity(String annuity) {
        this.annuity = annuity;
    }

    public String getAnnuity() {
        return annuity;
    }

    public void setUnionFee(BigDecimal unionFee) {
        this.unionFee = unionFee;
    }

    public BigDecimal getUnionFee() {
        return unionFee;
    }

    public void setTaxNotIssued(BigDecimal taxNotIssued) {
        this.taxNotIssued = taxNotIssued;
    }

    public BigDecimal getTaxNotIssued() {
        return taxNotIssued;
    }

    public void setCompanyTax(BigDecimal companyTax) {
        this.companyTax = companyTax;
    }

    public BigDecimal getCompanyTax() {
        return companyTax;
    }

    public void setFsAllowance(BigDecimal fsAllowance) {
        this.fsAllowance = fsAllowance;
    }

    public BigDecimal getFsAllowance() {
        return fsAllowance;
    }

    public void setAfterTaxDeduction(BigDecimal afterTaxDeduction) {
        this.afterTaxDeduction = afterTaxDeduction;
    }

    public BigDecimal getAfterTaxDeduction() {
        return afterTaxDeduction;
    }

    public void setTaxFreeIncome(BigDecimal taxFreeIncome) {
        this.taxFreeIncome = taxFreeIncome;
    }

    public BigDecimal getTaxFreeIncome() {
        return taxFreeIncome;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setHousingFundAccount(String housingFundAccount) {
        this.housingFundAccount = housingFundAccount;
    }

    public String getHousingFundAccount() {
        return housingFundAccount;
    }

    public void setBankAccounName(String bankAccounName) {
        this.bankAccounName = bankAccounName;
    }

    public String getBankAccounName() {
        return bankAccounName;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBank() {
        return bank;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPost() {
        return post;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationalityCode(String nationalityCode) {
        this.nationalityCode = nationalityCode;
    }

    public String getNationalityCode() {
        return nationalityCode;
    }

    public void setPayroll(String payroll) {
        this.payroll = payroll;
    }

    public String getPayroll() {
        return payroll;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setBillAddress(String billAddress) {
        this.billAddress = billAddress;
    }

    public String getBillAddress() {
        return billAddress;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setResidenceType(String residenceType) {
        this.residenceType = residenceType;
    }

    public String getResidenceType() {
        return residenceType;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setCommercialInsuranceCost(BigDecimal commercialInsuranceCost) {
        this.commercialInsuranceCost = commercialInsuranceCost;
    }

    public BigDecimal getCommercialInsuranceCost() {
        return commercialInsuranceCost;
    }

    public void setCommercialInsuranceLevel(String commercialInsuranceLevel) {
        this.commercialInsuranceLevel = commercialInsuranceLevel;
    }

    public String getCommercialInsuranceLevel() {
        return commercialInsuranceLevel;
    }

    public void setSpouseChildrenInsurance(BigDecimal spouseChildrenInsurance) {
        this.spouseChildrenInsurance = spouseChildrenInsurance;
    }

    public BigDecimal getSpouseChildrenInsurance() {
        return spouseChildrenInsurance;
    }

    public void setGreenAllowance(BigDecimal greenAllowance) {
        this.greenAllowance = greenAllowance;
    }

    public BigDecimal getGreenAllowance() {
        return greenAllowance;
    }

    public void setPhysicalExamination(BigDecimal physicalExamination) {
        this.physicalExamination = physicalExamination;
    }

    public BigDecimal getPhysicalExamination() {
        return physicalExamination;
    }

    public void setFileManagementFee(BigDecimal fileManagementFee) {
        this.fileManagementFee = fileManagementFee;
    }

    public BigDecimal getFileManagementFee() {
        return fileManagementFee;
    }

    public void setAnnualBonusInfo(String annualBonusInfo) {
        this.annualBonusInfo = annualBonusInfo;
    }

    public String getAnnualBonusInfo() {
        return annualBonusInfo;
    }

    public void setThirteenSalaryMonth(BigDecimal thirteenSalaryMonth) {
        this.thirteenSalaryMonth = thirteenSalaryMonth;
    }

    public BigDecimal getThirteenSalaryMonth() {
        return thirteenSalaryMonth;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setTaxPaymentPlace(String taxPaymentPlace) {
        this.taxPaymentPlace = taxPaymentPlace;
    }

    public String getTaxPaymentPlace() {
        return taxPaymentPlace;
    }

    public void setAccumulatedChildEducation(BigDecimal accumulatedChildEducation) {
        this.accumulatedChildEducation = accumulatedChildEducation;
    }

    public BigDecimal getAccumulatedChildEducation() {
        return accumulatedChildEducation;
    }

    public void setAccumulatedContinuingEducation(BigDecimal accumulatedContinuingEducation) {
        this.accumulatedContinuingEducation = accumulatedContinuingEducation;
    }

    public BigDecimal getAccumulatedContinuingEducation() {
        return accumulatedContinuingEducation;
    }

    public void setAccumulatedHli(BigDecimal accumulatedHli) {
        this.accumulatedHli = accumulatedHli;
    }

    public BigDecimal getAccumulatedHli() {
        return accumulatedHli;
    }

    public void setAccumulatedHousingRent(BigDecimal accumulatedHousingRent) {
        this.accumulatedHousingRent = accumulatedHousingRent;
    }

    public BigDecimal getAccumulatedHousingRent() {
        return accumulatedHousingRent;
    }

    public void setAccumulatedSfte(BigDecimal accumulatedSfte) {
        this.accumulatedSfte = accumulatedSfte;
    }

    public BigDecimal getAccumulatedSfte() {
        return accumulatedSfte;
    }

    public void setCommercialHealthInsurance(BigDecimal commercialHealthInsurance) {
        this.commercialHealthInsurance = commercialHealthInsurance;
    }

    public BigDecimal getCommercialHealthInsurance() {
        return commercialHealthInsurance;
    }

    public void setTdpInsurance(BigDecimal tdpInsurance) {
        this.tdpInsurance = tdpInsurance;
    }

    public BigDecimal getTdpInsurance() {
        return tdpInsurance;
    }

    public void setOtherTaxFreeDeductions(BigDecimal otherTaxFreeDeductions) {
        this.otherTaxFreeDeductions = otherTaxFreeDeductions;
    }

    public BigDecimal getOtherTaxFreeDeductions() {
        return otherTaxFreeDeductions;
    }

    public void setDailyAttendanceDays(BigDecimal dailyAttendanceDays) {
        this.dailyAttendanceDays = dailyAttendanceDays;
    }

    public BigDecimal getDailyAttendanceDays() {
        return dailyAttendanceDays;
    }

    public void setMonthlyAward(BigDecimal monthlyAward) {
        this.monthlyAward = monthlyAward;
    }

    public BigDecimal getMonthlyAward() {
        return monthlyAward;
    }

    public void setQuarterAward(BigDecimal quarterAward) {
        this.quarterAward = quarterAward;
    }

    public BigDecimal getQuarterAward() {
        return quarterAward;
    }

    public void setOvertime150hours(String overtime150hours) {
        this.overtime150hours = overtime150hours;
    }

    public String getOvertime150hours() {
        return overtime150hours;
    }

    public void setOvertime200hours(String overtime200hours) {
        this.overtime200hours = overtime200hours;
    }

    public String getOvertime200hours() {
        return overtime200hours;
    }

    public void setOvertime300hours(String overtime300hours) {
        this.overtime300hours = overtime300hours;
    }

    public String getOvertime300hours() {
        return overtime300hours;
    }

    public void setFullAttendanceDays(BigDecimal fullAttendanceDays) {
        this.fullAttendanceDays = fullAttendanceDays;
    }

    public BigDecimal getFullAttendanceDays() {
        return fullAttendanceDays;
    }

    public void setEalDays(BigDecimal ealDays) {
        this.ealDays = ealDays;
    }

    public BigDecimal getEalDays() {
        return ealDays;
    }

    public void setAffairLeaveDays(BigDecimal affairLeaveDays) {
        this.affairLeaveDays = affairLeaveDays;
    }

    public BigDecimal getAffairLeaveDays() {
        return affairLeaveDays;
    }

    public void setAbsentEeismDays(BigDecimal absentEeismDays) {
        this.absentEeismDays = absentEeismDays;
    }

    public BigDecimal getAbsentEeismDays() {
        return absentEeismDays;
    }

    public void setSickLeaveDays(BigDecimal sickLeaveDays) {
        this.sickLeaveDays = sickLeaveDays;
    }

    public BigDecimal getSickLeaveDays() {
        return sickLeaveDays;
    }

    public void setSickLeaveDeduction(BigDecimal sickLeaveDeduction) {
        this.sickLeaveDeduction = sickLeaveDeduction;
    }

    public BigDecimal getSickLeaveDeduction() {
        return sickLeaveDeduction;
    }

    public void setTransportationFee(BigDecimal transportationFee) {
        this.transportationFee = transportationFee;
    }

    public BigDecimal getTransportationFee() {
        return transportationFee;
    }

    public void setCommunicationFee(BigDecimal communicationFee) {
        this.communicationFee = communicationFee;
    }

    public BigDecimal getCommunicationFee() {
        return communicationFee;
    }

    public void setMiddleShiftDay(BigDecimal middleShiftDay) {
        this.middleShiftDay = middleShiftDay;
    }

    public BigDecimal getMiddleShiftDay() {
        return middleShiftDay;
    }

    public void setNightShiftDays(BigDecimal nightShiftDays) {
        this.nightShiftDays = nightShiftDays;
    }

    public BigDecimal getNightShiftDays() {
        return nightShiftDays;
    }

    public void setHighTemperatureDays(BigDecimal highTemperatureDays) {
        this.highTemperatureDays = highTemperatureDays;
    }

    public BigDecimal getHighTemperatureDays() {
        return highTemperatureDays;
    }

    public void setOhtFee(BigDecimal ohtFee) {
        this.ohtFee = ohtFee;
    }

    public BigDecimal getOhtFee() {
        return ohtFee;
    }

    public void setHotWorkingDays(BigDecimal hotWorkingDays) {
        this.hotWorkingDays = hotWorkingDays;
    }

    public BigDecimal getHotWorkingDays() {
        return hotWorkingDays;
    }

    public void setTahwDays(BigDecimal tahwDays) {
        this.tahwDays = tahwDays;
    }

    public BigDecimal getTahwDays() {
        return tahwDays;
    }

    public void setReissue(BigDecimal reissue) {
        this.reissue = reissue;
    }

    public BigDecimal getReissue() {
        return reissue;
    }

    public void setWageDeduction(BigDecimal wageDeduction) {
        this.wageDeduction = wageDeduction;
    }

    public BigDecimal getWageDeduction() {
        return wageDeduction;
    }

    public void setPensionRepayment(BigDecimal pensionRepayment) {
        this.pensionRepayment = pensionRepayment;
    }

    public BigDecimal getPensionRepayment() {
        return pensionRepayment;
    }

    public void setMedicalRepayment(BigDecimal medicalRepayment) {
        this.medicalRepayment = medicalRepayment;
    }

    public BigDecimal getMedicalRepayment() {
        return medicalRepayment;
    }

    public void setUnemploymentRepayment(BigDecimal unemploymentRepayment) {
        this.unemploymentRepayment = unemploymentRepayment;
    }

    public BigDecimal getUnemploymentRepayment() {
        return unemploymentRepayment;
    }

    public void setHousingFundPayment(BigDecimal housingFundPayment) {
        this.housingFundPayment = housingFundPayment;
    }

    public BigDecimal getHousingFundPayment() {
        return housingFundPayment;
    }

    public void setTaxFreeIncome2(BigDecimal taxFreeIncome2) {
        this.taxFreeIncome2 = taxFreeIncome2;
    }

    public BigDecimal getTaxFreeIncome2() {
        return taxFreeIncome2;
    }

    public BigDecimal getTaxCalculation() {
        return taxCalculation;
    }

    public void setTaxCalculation(BigDecimal taxCalculation) {
        this.taxCalculation = taxCalculation;
    }

    public void setCompanyTax2(BigDecimal companyTax2) {
        this.companyTax2 = companyTax2;
    }

    public BigDecimal getCompanyTax2() {
        return companyTax2;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense25(BigDecimal expense25) {
        this.expense25 = expense25;
    }

    public BigDecimal getExpense25() {
        return expense25;
    }

    public void setAfterTaxReissue(BigDecimal afterTaxReissue) {
        this.afterTaxReissue = afterTaxReissue;
    }

    public BigDecimal getAfterTaxReissue() {
        return afterTaxReissue;
    }

    public void setAfterTaxDeduction2(BigDecimal afterTaxDeduction2) {
        this.afterTaxDeduction2 = afterTaxDeduction2;
    }

    public BigDecimal getAfterTaxDeduction2() {
        return afterTaxDeduction2;
    }

    public void setIndividualTaxAdjustment(BigDecimal individualTaxAdjustment) {
        this.individualTaxAdjustment = individualTaxAdjustment;
    }

    public BigDecimal getIndividualTaxAdjustment() {
        return individualTaxAdjustment;
    }

    public void setAnnualBonus(BigDecimal annualBonus) {
        this.annualBonus = annualBonus;
    }

    public BigDecimal getAnnualBonus() {
        return annualBonus;
    }

    public void setRemarks2(String remarks2) {
        this.remarks2 = remarks2;
    }

    public String getRemarks2() {
        return remarks2;
    }

    public void setCsbRepayment(BigDecimal csbRepayment) {
        this.csbRepayment = csbRepayment;
    }

    public BigDecimal getCsbRepayment() {
        return csbRepayment;
    }

    public void setChfRepayment(BigDecimal chfRepayment) {
        this.chfRepayment = chfRepayment;
    }

    public BigDecimal getChfRepayment() {
        return chfRepayment;
    }

    public void setCdRepayment(BigDecimal cdRepayment) {
        this.cdRepayment = cdRepayment;
    }

    public BigDecimal getCdRepayment() {
        return cdRepayment;
    }

    public void setBasicDeductionAdjustment(BigDecimal basicDeductionAdjustment) {
        this.basicDeductionAdjustment = basicDeductionAdjustment;
    }

    public BigDecimal getBasicDeductionAdjustment() {
        return basicDeductionAdjustment;
    }

    public void setPayrollBelong(String payrollBelong) {
        this.payrollBelong = payrollBelong;
    }

    public String getPayrollBelong() {
        return payrollBelong;
    }

    public void setBankLocation(String bankLocation) {
        this.bankLocation = bankLocation;
    }

    public String getBankLocation() {
        return bankLocation;
    }

    public BigDecimal getSeverance() {
        return severance;
    }

    public void setSeverance(BigDecimal severance) {
        this.severance = severance;
    }

    public BigDecimal getBirthdayGift() {
        return birthdayGift;
    }

    public void setBirthdayGift(BigDecimal birthdayGift) {
        this.birthdayGift = birthdayGift;
    }

    public BigDecimal getHeatingFee() {
        return heatingFee;
    }

    public void setHeatingFee(BigDecimal heatingFee) {
        this.heatingFee = heatingFee;
    }

    public String getPaySBFlag() {
        return paySBFlag;
    }

    public void setPaySBFlag(String paySBFlag) {
        this.paySBFlag = paySBFlag;
    }

    public String getPayHFFlag() {
        return payHFFlag;
    }

    public void setPayHFFlag(String payHFFlag) {
        this.payHFFlag = payHFFlag;
    }

    public String getPayIITFlag() {
        return payIITFlag;
    }

    public void setPayIITFlag(String payIITFlag) {
        this.payIITFlag = payIITFlag;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public BigDecimal getTaxFreeItem() {
        return taxFreeItem;
    }

    public void setTaxFreeItem(BigDecimal taxFreeItem) {
        this.taxFreeItem = taxFreeItem;
    }

    public BigDecimal getActualPerformance() {
        return actualPerformance;
    }

    public void setActualPerformance(BigDecimal actualPerformance) {
        this.actualPerformance = actualPerformance;
    }

    public BigDecimal getPerformanceBonusBase() {
        return performanceBonusBase;
    }

    public void setPerformanceBonusBase(BigDecimal performanceBonusBase) {
        this.performanceBonusBase = performanceBonusBase;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getOtherBonus() {
        return otherBonus;
    }

    public void setOtherBonus(BigDecimal otherBonus) {
        this.otherBonus = otherBonus;
    }

    public BigDecimal getPretaxAdjustment() {
        return pretaxAdjustment;
    }

    public void setPretaxAdjustment(BigDecimal pretaxAdjustment) {
        this.pretaxAdjustment = pretaxAdjustment;
    }

    public BigDecimal getIndemnity() {
        return indemnity;
    }

    public void setIndemnity(BigDecimal indemnity) {
        this.indemnity = indemnity;
    }

    public BigDecimal getIndemnityAllowance() {
        return indemnityAllowance;
    }

    public void setIndemnityAllowance(BigDecimal indemnityAllowance) {
        this.indemnityAllowance = indemnityAllowance;
    }

    public String getCompensationCalculationYear() {
        return compensationCalculationYear;
    }

    public void setCompensationCalculationYear(String compensationCalculationYear) {
        this.compensationCalculationYear = compensationCalculationYear;
    }

    public BigDecimal getShareIncentives() {
        return shareIncentives;
    }

    public void setShareIncentives(BigDecimal shareIncentives) {
        this.shareIncentives = shareIncentives;
    }

    public BigDecimal getTravelAllowance() {
        return travelAllowance;
    }

    public void setTravelAllowance(BigDecimal travelAllowance) {
        this.travelAllowance = travelAllowance;
    }

    public BigDecimal getAccumulatedChildCare() {
        return accumulatedChildCare;
    }

    public void setAccumulatedChildCare(BigDecimal accumulatedChildCare) {
        this.accumulatedChildCare = accumulatedChildCare;
    }

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public BigDecimal getOtherAllowance() {
        return otherAllowance;
    }

    public void setOtherAllowance(BigDecimal otherAllowance) {
        this.otherAllowance = otherAllowance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
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
                .append("basicDeductionAdjustment", getBasicDeductionAdjustment())
                .append("payrollBelong", getPayrollBelong())
                .append("bankLocation", getBankLocation())
                .append("severance", getSeverance())
                .append("birthdayGift", getBirthdayGift())
                .append("heatingFee", getHeatingFee())
                .append("paySBFlag", getPaySBFlag())
                .append("payHFFlag", getPayHFFlag())
                .append("payIITFlag", getPayIITFlag())
                .append("clientCode", getClientCode())
                .append("employeeNo", getEmployeeNo())
                .append("costCenter", getCostCenter())
                .append("taxFreeItem", getTaxFreeItem())
                .append("otherBonus", getOtherBonus())
                .append("pretaxAdjustment", getPretaxAdjustment())
                .append("indemnity", getIndemnity())
                .append("indemnityAllowance", getIndemnityAllowance())
                .append("compensationCalculationYear", getCompensationCalculationYear())
                .append("shareIncentives", getShareIncentives())
                .append("travelAllowance", getTravelAllowance())
                .append("accumulatedChildCare", getAccumulatedChildCare())
                .append("otherAllowance", getOtherAllowance())
                .toString();
    }
}
