package com.nnroad.payroll.domain.common;

import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 变动信息信息对象 ps_basic_unfixed_info_his
 * 
 * @author Aaron
 * @date 2021-12-13
 */
public class PsBasicUnfixedInfoHis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** Duration */
    @Excel(name = "duration")
    private String duration;

    /** id no */
    @Excel(name = "id_no")
    private String idNo;

    /** name */
    @Excel(name = "name")
    private String name;

    /** English name */
    @Excel(name = "en_name")
    private String enName;

    /** Daily attendance days */
    @Excel(name = "daily_attendance_days")
    private BigDecimal dailyAttendanceDays;

    /** Monthly award */
    @Excel(name = "monthly_award")
    private BigDecimal monthlyAward;

    /** Quarter award */
    @Excel(name = "quarter_award")
    private BigDecimal quarterAward;

    /** Overtime 150 hours */
    @Excel(name = "overtime_150hours")
    private String overtime150hours;

    /** Overtime 200 hours */
    @Excel(name = "overtime_200hours")
    private String overtime200hours;

    /** Overtime 300 hours */
    @Excel(name = "overtime_300hours")
    private String overtime300hours;

    /** Full attendance day of the month */
    @Excel(name = "full_attendance_days")
    private BigDecimal fullAttendanceDays;

    /** Days of entry and leave */
    @Excel(name = "eal_days")
    private BigDecimal ealDays;

    /** Affair Leave days */
    @Excel(name = "affair_leave_days")
    private BigDecimal affairLeaveDays;

    /** Absent eeism days */
    @Excel(name = "absent_eeism_days")
    private BigDecimal absentEeismDays;

    /** Sick leave days */
    @Excel(name = "sick_leave_days")
    private BigDecimal sickLeaveDays;

    /** Sick leave deduction */
    @Excel(name = "sick_leave_deduction")
    private BigDecimal sickLeaveDeduction;

    /** Transportation */
    @Excel(name = "transportation_fee")
    private BigDecimal transportationFee;

    /** Communication fee */
    @Excel(name = "communication_fee")
    private BigDecimal communicationFee;

    /** Middle shift days */
    @Excel(name = "middle_shift_day")
    private BigDecimal middleShiftDay;

    /** Night shift days */
    @Excel(name = "night_shift_days")
    private BigDecimal nightShiftDays;

    /** High temperature days */
    @Excel(name = "high_temperature_days")
    private BigDecimal highTemperatureDays;

    /** One-time high temperature fee */
    @Excel(name = "oht_fee")
    private BigDecimal ohtFee;

    /** Hot working days */
    @Excel(name = "hot_working_days")
    private BigDecimal hotWorkingDays;

    /** Toxic and harmful working days */
    @Excel(name = "tahw_days")
    private BigDecimal tahwDays;

    /** Reissue */
    @Excel(name = "reissue")
    private BigDecimal reissue;

    /** Wage deduction */
    @Excel(name = "wage_deduction")
    private BigDecimal wageDeduction;

    /** Tax-free income2 */
    @Excel(name = "tax_free_income2")
    private BigDecimal taxFreeIncome2;

    /** 计税不发2 */
    @Excel(name = "tax_calculation")
    private BigDecimal taxCalculation;

    /** Company tax */
    @Excel(name = "company_tax2")
    private BigDecimal companyTax2;

    /** Expense */
    @Excel(name = "expense")
    private BigDecimal expense;

    /** Expenset 25% */
    @Excel(name = "expense_25")
    private BigDecimal expense25;

    /** After-tax reissue */
    @Excel(name = "after_tax_reissue")
    private BigDecimal afterTaxReissue;

    /** After-tax deduction */
    @Excel(name = "after_tax_deduction2")
    private BigDecimal afterTaxDeduction2;

    /** Individual tax adjustment */
    @Excel(name = "Individual_tax_adjustment")
    private BigDecimal individualTaxAdjustment;

    /** Annual Bonus/13th Salary */
    @Excel(name = "annual_bonus")
    private BigDecimal annualBonus;

    /** Remarks2 */
    @Excel(name = "remarks2")
    private String remarks2;

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
            .append("idNo", getIdNo())
            .append("name", getName())
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
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("groupIds", getGroupIds())
            .toString();
    }
}
