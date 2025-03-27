package com.nnroad.payroll.domain.exportC;

import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * AverageSalaryReport对象 average_salary_report

 */
public class AverageSalaryReport extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 期间名称 */
    @Excel(name = "期间名称")
    private String duration;

    /** 公司编码 */
    @Excel(name = "公司编码")
    private String erNo;

    /** 公司简称 */
    @Excel(name = "公司简称")
    private String erName;

    /** 员工编号 */
    @Excel(name = "=员工编号")
    private String eeNo;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String eeName;

    /** 员工英文名称 */
    @Excel(name = "员工英文名称")
    private String eeNameEn;

    /** 工作地点 */
    @Excel(name = "工作地")
    private String workAddress;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String certificateNumber;

    /** 雇佣日期 */
    @Excel(name = "雇佣日期")
    private String contractStarttime;

    /** 离职日期 */
    @Excel(name = "离职日期")
    private String leaveDate;

    /** 税前/税后 */
    @Excel(name = "v")
    private String preAfterTax;

    /** 基本工资 */
    @Excel(name = "基本工资", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal basicSalary;

    /** 基本工资（税后推税前） */
    @Excel(name = "基本工资（税后推税前）", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal basicSalaryResult;

    /** 交通津贴 */
    @Excel(name = "交通津贴", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal transportationPayment;

    /** 通讯津贴 */
    @Excel(name = "通讯津贴", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal communicationPayment;

    /** 其他津贴 */
    @Excel(name = "其他津贴", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal otherBenefits;

    /** 加班费 */
    @Excel(name = "加班费", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal overtimeAwardSum;

    /** 病假扣款 */
    @Excel(name = "病假扣款", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal slDeduction;

    /**  事假扣款*/
    @Excel(name = "事假扣款", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal alDeduction;

    /** 旷工扣款 */
    @Excel(name = "旷工扣款", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal absenteeismDeduction;

    /** 月奖 */
    @Excel(name = "月奖", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal monthlyAward;

    /** 季奖 */
    @Excel(name = "季奖", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal quarterAward;

    /** 其他税前应发 */
    @Excel(name = "其他税前应发", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal reissue;

    /** 其他税前应扣 */
    @Excel(name = "其他税前应扣", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal otherPretaxDeduction;

    /** 税前收入合计 */
    @Excel(name = "税前收入合计", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal pretaxIncomeSum;

    /** 计税不发薪 */
    @Excel(name = "计税不发薪", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal taxCalculation;

    /** 商业保险计税不发 */
    @Excel(name = "商业保险计税不发", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal taxNotIssued;

    /** 养老保险-员工 */
    @Excel(name = "养老保险-员工", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal pension;

    /** 医疗保险-员工 */
    @Excel(name = "医疗保险-员工", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal medical;

    /** 失业保险-员工 */
    @Excel(name = "失业保险-员工", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal unemployment;

    /** 社保调整-员工 */
    @Excel(name = "社保调整-员工", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal socialBenefitsAdjustment;

    /** 住房公积金-员工 */
    @Excel(name = "住房公积金-员工", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal housingFund;

    /** 补充住房公积金-员工 */
    @Excel(name = "补充住房公积金-员工", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal steHousingFund;

    /** 公积金调整-员工 */
    @Excel(name = "公积金调整-员工", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal housingFundAdjustment;

    /** 其他公司负税收入 */
    @Excel(name = "其他公司负税收入", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal companyTax;

    /** 年终奖/13薪 */
    @Excel(name = "年终奖/13薪", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal annualBonus;

    /** 外籍生活津贴 */
    @Excel(name = "外籍生活津贴", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal fsAllowance;

    /** 入离职缺勤扣 */
    @Excel(name = "入离职缺勤扣", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal ealaDeduction;

    /** 实发工资 */
    @Excel(name = "实发工资", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal netIncome;

    /** 社平工资 */
    @Excel(name = "社平工资", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal socialWage;

    /** 最低工资 */
    @Excel(name = "最低工资", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal minimumWage;

    /** 异常标示 */
    @Excel(name = "异常标示", cellType = Excel.ColumnType.NUMERIC)
    private int abnormalFlg;

    /** 社保缴纳地 */
    @Excel(name = "社保缴纳地")
    private String sbAddress;

    /** 其他奖金 */
    @Excel(name = "其他奖金", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal otherBonus;

    /** 用工性质 */
    @Excel(name = "excel.avgSalaryReport.employmentNature")
    private String employmentNature;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String certificateType;

    /** 权限组 */
    private String groupIds;

    /** 税前调整 */
    @Excel(name = "税前调整", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal preTaxAdjustment;

    /** 高温费 */
    @Excel(name = "高温费", cellType = Excel.ColumnType.NUMERIC)
    private BigDecimal highTemperaturePayment;

    public BigDecimal getPreTaxAdjustment() {
        return preTaxAdjustment;
    }

    public void setPreTaxAdjustment(BigDecimal preTaxAdjustment) {
        this.preTaxAdjustment = preTaxAdjustment;
    }

    public BigDecimal getHighTemperaturePayment() {
        return highTemperaturePayment;
    }

    public void setHighTemperaturePayment(BigDecimal highTemperaturePayment) {
        this.highTemperaturePayment = highTemperaturePayment;
    }


    public BigDecimal getOtherBonus() {
        return otherBonus;
    }

    public void setOtherBonus(BigDecimal otherBonus) {
        this.otherBonus = otherBonus;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setErNo(String erNo) {
        this.erNo = erNo;
    }

    public String getErNo() {
        return erNo;
    }

    public void setErName(String erName) {
        this.erName = erName;
    }

    public String getErName() {
        return erName;
    }

    public void setEeNo(String eeNo) {
        this.eeNo = eeNo;
    }

    public String getEeNo() {
        return eeNo;
    }

    public void setEeName(String eeName) {
        this.eeName = eeName;
    }

    public String getEeName() {
        return eeName;
    }

    public void setEeNameEn(String eeNameEn) {
        this.eeNameEn = eeNameEn;
    }

    public String getEeNameEn() {
        return eeNameEn;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setContractStarttime(String contractStarttime) {
        this.contractStarttime = contractStarttime;
    }

    public String getContractStarttime() {
        return contractStarttime;
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

    public void setBasicSalaryResult(BigDecimal basicSalaryResult) {
        this.basicSalaryResult = basicSalaryResult;
    }

    public BigDecimal getBasicSalaryResult() {
        return basicSalaryResult;
    }

    public void setTransportationPayment(BigDecimal transportationPayment) {
        this.transportationPayment = transportationPayment;
    }

    public BigDecimal getTransportationPayment() {
        return transportationPayment;
    }

    public void setCommunicationPayment(BigDecimal communicationPayment) {
        this.communicationPayment = communicationPayment;
    }

    public BigDecimal getCommunicationPayment() {
        return communicationPayment;
    }

    public void setOtherBenefits(BigDecimal otherBenefits) {
        this.otherBenefits = otherBenefits;
    }

    public BigDecimal getOtherBenefits() {
        return otherBenefits;
    }

    public void setOvertimeAwardSum(BigDecimal overtimeAwardSum) {
        this.overtimeAwardSum = overtimeAwardSum;
    }

    public BigDecimal getOvertimeAwardSum() {
        return overtimeAwardSum;
    }

    public void setSlDeduction(BigDecimal slDeduction) {
        this.slDeduction = slDeduction;
    }

    public BigDecimal getSlDeduction() {
        return slDeduction;
    }

    public void setAlDeduction(BigDecimal alDeduction) {
        this.alDeduction = alDeduction;
    }

    public BigDecimal getAlDeduction() {
        return alDeduction;
    }

    public void setAbsenteeismDeduction(BigDecimal absenteeismDeduction) {
        this.absenteeismDeduction = absenteeismDeduction;
    }

    public BigDecimal getAbsenteeismDeduction() {
        return absenteeismDeduction;
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

    public void setReissue(BigDecimal reissue) {
        this.reissue = reissue;
    }

    public BigDecimal getReissue() {
        return reissue;
    }

    public void setOtherPretaxDeduction(BigDecimal otherPretaxDeduction) {
        this.otherPretaxDeduction = otherPretaxDeduction;
    }

    public BigDecimal getOtherPretaxDeduction() {
        return otherPretaxDeduction;
    }

    public void setPretaxIncomeSum(BigDecimal pretaxIncomeSum) {
        this.pretaxIncomeSum = pretaxIncomeSum;
    }

    public BigDecimal getPretaxIncomeSum() {
        return pretaxIncomeSum;
    }

    public void setTaxCalculation(BigDecimal taxCalculation) {
        this.taxCalculation = taxCalculation;
    }

    public BigDecimal getTaxCalculation() {
        return taxCalculation;
    }

    public void setTaxNotIssued(BigDecimal taxNotIssued) {
        this.taxNotIssued = taxNotIssued;
    }

    public BigDecimal getTaxNotIssued() {
        return taxNotIssued;
    }

    public void setPension(BigDecimal pension) {
        this.pension = pension;
    }

    public BigDecimal getPension() {
        return pension;
    }

    public void setMedical(BigDecimal medical) {
        this.medical = medical;
    }

    public BigDecimal getMedical() {
        return medical;
    }

    public void setUnemployment(BigDecimal unemployment) {
        this.unemployment = unemployment;
    }

    public BigDecimal getUnemployment() {
        return unemployment;
    }

    public void setSocialBenefitsAdjustment(BigDecimal socialBenefitsAdjustment) {
        this.socialBenefitsAdjustment = socialBenefitsAdjustment;
    }

    public BigDecimal getSocialBenefitsAdjustment() {
        return socialBenefitsAdjustment;
    }

    public void setHousingFund(BigDecimal housingFund) {
        this.housingFund = housingFund;
    }

    public BigDecimal getHousingFund() {
        return housingFund;
    }

    public void setSteHousingFund(BigDecimal steHousingFund) {
        this.steHousingFund = steHousingFund;
    }

    public BigDecimal getSteHousingFund() {
        return steHousingFund;
    }

    public void setHousingFundAdjustment(BigDecimal housingFundAdjustment) {
        this.housingFundAdjustment = housingFundAdjustment;
    }

    public BigDecimal getHousingFundAdjustment() {
        return housingFundAdjustment;
    }

    public void setCompanyTax(BigDecimal companyTax) {
        this.companyTax = companyTax;
    }

    public BigDecimal getCompanyTax() {
        return companyTax;
    }

    public void setAnnualBonus(BigDecimal annualBonus) {
        this.annualBonus = annualBonus;
    }

    public BigDecimal getAnnualBonus() {
        return annualBonus;
    }

    public void setFsAllowance(BigDecimal fsAllowance) {
        this.fsAllowance = fsAllowance;
    }

    public BigDecimal getFsAllowance() {
        return fsAllowance;
    }

    public void setNetIncome(BigDecimal netIncome) {
        this.netIncome = netIncome;
    }

    public BigDecimal getNetIncome() {
        return netIncome;
    }

    public void setSocialWage(BigDecimal socialWage) {
        this.socialWage = socialWage;
    }

    public BigDecimal getSocialWage() {
        return socialWage;
    }

    public BigDecimal getEalaDeduction() {
        return ealaDeduction;
    }

    public void setEalaDeduction(BigDecimal ealaDeduction) {
        this.ealaDeduction = ealaDeduction;
    }

    public String getSbAddress() {
        return sbAddress;
    }

    public void setSbAddress(String sbAddress) {
        this.sbAddress = sbAddress;
    }

    public String getEmploymentNature() {
        return employmentNature;
    }

    public void setEmploymentNature(String employmentNature) {
        this.employmentNature = employmentNature;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public String getGroupIds() {
        return groupIds;
    }

    public BigDecimal getMinimumWage() {
        return minimumWage;
    }

    public void setMinimumWage(BigDecimal minimumWage) {
        this.minimumWage = minimumWage;
    }

    public int getAbnormalFlg() {
        return abnormalFlg;
    }

    public void setAbnormalFlg(int abnormalFlg) {
        this.abnormalFlg = abnormalFlg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("duration", getDuration())
                .append("erNo", getErNo())
                .append("erName", getErName())
                .append("eeNo", getEeNo())
                .append("eeName", getEeName())
                .append("eeNameEn", getEeNameEn())
                .append("workAddress", getWorkAddress())
                .append("certificateNumber", getCertificateNumber())
                .append("contractStarttime", getContractStarttime())
                .append("leaveDate", getLeaveDate())
                .append("preAfterTax", getPreAfterTax())
                .append("basicSalary", getBasicSalary())
                .append("basicSalaryResult", getBasicSalaryResult())
                .append("transportationPayment", getTransportationPayment())
                .append("communicationPayment", getCommunicationPayment())
                .append("otherBenefits", getOtherBenefits())
                .append("overtimeAwardSum", getOvertimeAwardSum())
                .append("slDeduction", getSlDeduction())
                .append("alDeduction", getAlDeduction())
                .append("absenteeismDeduction", getAbsenteeismDeduction())
                .append("monthlyAward", getMonthlyAward())
                .append("quarterAward", getQuarterAward())
                .append("reissue", getReissue())
                .append("otherPretaxDeduction", getOtherPretaxDeduction())
                .append("pretaxIncomeSum", getPretaxIncomeSum())
                .append("taxCalculation", getTaxCalculation())
                .append("taxNotIssued", getTaxNotIssued())
                .append("pension", getPension())
                .append("medical", getMedical())
                .append("unemployment", getUnemployment())
                .append("socialBenefitsAdjustment", getSocialBenefitsAdjustment())
                .append("housingFund", getHousingFund())
                .append("steHousingFund", getSteHousingFund())
                .append("housingFundAdjustment", getHousingFundAdjustment())
                .append("companyTax", getCompanyTax())
                .append("annualBonus", getAnnualBonus())
                .append("fsAllowance", getFsAllowance())
                .append("netIncome", getNetIncome())
                .append("ealaDeduction", getEalaDeduction())
                .append("socialWage", getSocialWage())
                .append("sbAddress", getSbAddress())
                .append("employmentNature", getEmploymentNature())
                .append("certificateType", getCertificateType())
                .append("groupIds", getGroupIds())
                .toString();
    }
}
