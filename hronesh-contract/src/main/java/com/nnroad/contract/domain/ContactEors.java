package com.nnroad.contract.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 contact_eors
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public class ContactEors extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** section default:str */
    @Excel(name = "section default:str")
    private String strIndex;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal numIndex;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer EOR;

    /** 10% of total labor cost */
    @Excel(name = "10% of total labor cost")
    private String eor1;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal eor1Value;

    /** Minimum: USD 150/head/month */
    @Excel(name = "Minimum: USD 150/head/month")
    private BigDecimal eor2;

    /** Maximum: USD 1,000/head/month */
    @Excel(name = "Maximum: USD 1,000/head/month")
    private BigDecimal eor3;

    /** One-off Setup Fee */
    @Excel(name = "One-off Setup Fee")
    private BigDecimal eor4;

    /** deposit */
    @Excel(name = "deposit")
    private BigDecimal eor5;

    /** office seat */
    @Excel(name = "office seat")
    private BigDecimal eor6;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer flatFeeOption;

    /** Chinese normal */
    @Excel(name = "Chinese normal")
    private BigDecimal flatFee1;

    /** One-off Setup Fee */
    @Excel(name = "One-off Setup Fee")
    private BigDecimal flatFee2;

    /** Deposit */
    @Excel(name = "Deposit")
    private BigDecimal flatFee3;

    /** office seat */
    @Excel(name = "office seat")
    private BigDecimal flatFee4;

    /** USD 150 /time/workforce/half day */
    @Excel(name = "USD 150 /time/workforce/half day")
    private BigDecimal appa7;

    /** Medicare card application */
    @Excel(name = "Medicare card application")
    private BigDecimal appa81;

    /** One-time pension withdrawal */
    @Excel(name = "One-time pension withdrawal")
    private BigDecimal appa82;

    /** One-time medical insurance fund withdrawal */
    @Excel(name = "One-time medical insurance fund withdrawal")
    private BigDecimal appa83;

    /** USD 150 /time/workforce/half day */
    @Excel(name = "USD 150 /time/workforce/half day")
    private BigDecimal appa8N1;

    /** USD 20,000 per Employee per document */
    @Excel(name = "USD 20,000 per Employee per document")
    private BigDecimal other1;

    /** Client Name: To be added */
    @Excel(name = "Client Name: To be added")
    private String appb1;

    /** Full Name: To be added */
    @Excel(name = "Full Name: To be added")
    private String appb2;

    /** Nationality: To be added */
    @Excel(name = "Nationality: To be added")
    private String appb3;

    /** ID Number: To be added */
    @Excel(name = "ID Number: To be added")
    private String appb4;

    /** Title and Main Responsibilities: To be added */
    @Excel(name = "Title and Main Responsibilities: To be added")
    private String appb5;

    /** Employment/Engagement Period or EOR term designated by Party A: To be added */
    @Excel(name = "Employment/Engagement Period or EOR term designated by Party A: To be added")
    private String appb6;

    /** Working Type: Full-time / Part-time / Intern / Retiree */
    @Excel(name = "Working Type: Full-time / Part-time / Intern / Retiree")
    private String appb7;

    /** Probation Period and Probation Period Salary: months */
    @Excel(name = "Probation Period and Probation Period Salary: months")
    private String appb81;

    /** RMB: [ ] months and RMB [ ] per month */
    @Excel(name = "RMB: [ ] months and RMB [ ] per month")
    private String appb82;

    /** Working (Social Insurance) City */
    @Excel(name = "Working (Social Insurance) City")
    private String appb9;

    /** Monthly Gross Salary and Salary Months Per Year: month */
    @Excel(name = "Monthly Gross Salary and Salary Months Per Year: month")
    private String appb101;

    /** months per year: RMB [ ] per month and [ ] months per year */
    @Excel(name = "months per year: RMB [ ] per month and [ ] months per year")
    private String appb102;

    /** Social Insurance, Commercial Insurance(if any): To be added */
    @Excel(name = "Social Insurance, Commercial Insurance(if any): To be added")
    private String appb11;

    /** Working Hours and Vacation: To be added */
    @Excel(name = "Working Hours and Vacation: To be added")
    private String appb12;

    /** Commission/Bonus: To be added */
    @Excel(name = "Commission/Bonus: To be added")
    private String appb13;

    /** Others: To be added */
    @Excel(name = "Others: To be added")
    private String appb14;

    /** $column.columnComment */
    private String userId;

    public void setStrIndex(String strIndex) 
    {
        this.strIndex = strIndex;
    }

    public String getStrIndex() 
    {
        return strIndex;
    }
    public void setNumIndex(BigDecimal numIndex) 
    {
        this.numIndex = numIndex;
    }

    public BigDecimal getNumIndex() 
    {
        return numIndex;
    }
    public void setEOR(Integer EOR) 
    {
        this.EOR = EOR;
    }

    public Integer getEOR() 
    {
        return EOR;
    }
    public void setEor1(String eor1) 
    {
        this.eor1 = eor1;
    }

    public String getEor1() 
    {
        return eor1;
    }
    public void setEor1Value(BigDecimal eor1Value) 
    {
        this.eor1Value = eor1Value;
    }

    public BigDecimal getEor1Value() 
    {
        return eor1Value;
    }
    public void setEor2(BigDecimal eor2) 
    {
        this.eor2 = eor2;
    }

    public BigDecimal getEor2() 
    {
        return eor2;
    }
    public void setEor3(BigDecimal eor3) 
    {
        this.eor3 = eor3;
    }

    public BigDecimal getEor3() 
    {
        return eor3;
    }
    public void setEor4(BigDecimal eor4) 
    {
        this.eor4 = eor4;
    }

    public BigDecimal getEor4() 
    {
        return eor4;
    }
    public void setEor5(BigDecimal eor5) 
    {
        this.eor5 = eor5;
    }

    public BigDecimal getEor5() 
    {
        return eor5;
    }
    public void setEor6(BigDecimal eor6) 
    {
        this.eor6 = eor6;
    }

    public BigDecimal getEor6() 
    {
        return eor6;
    }
    public void setFlatFeeOption(Integer flatFeeOption) 
    {
        this.flatFeeOption = flatFeeOption;
    }

    public Integer getFlatFeeOption() 
    {
        return flatFeeOption;
    }
    public void setFlatFee1(BigDecimal flatFee1) 
    {
        this.flatFee1 = flatFee1;
    }

    public BigDecimal getFlatFee1() 
    {
        return flatFee1;
    }
    public void setFlatFee2(BigDecimal flatFee2) 
    {
        this.flatFee2 = flatFee2;
    }

    public BigDecimal getFlatFee2() 
    {
        return flatFee2;
    }
    public void setFlatFee3(BigDecimal flatFee3) 
    {
        this.flatFee3 = flatFee3;
    }

    public BigDecimal getFlatFee3() 
    {
        return flatFee3;
    }
    public void setFlatFee4(BigDecimal flatFee4) 
    {
        this.flatFee4 = flatFee4;
    }

    public BigDecimal getFlatFee4() 
    {
        return flatFee4;
    }
    public void setAppa7(BigDecimal appa7) 
    {
        this.appa7 = appa7;
    }

    public BigDecimal getAppa7() 
    {
        return appa7;
    }
    public void setAppa81(BigDecimal appa81) 
    {
        this.appa81 = appa81;
    }

    public BigDecimal getAppa81() 
    {
        return appa81;
    }
    public void setAppa82(BigDecimal appa82) 
    {
        this.appa82 = appa82;
    }

    public BigDecimal getAppa82() 
    {
        return appa82;
    }
    public void setAppa83(BigDecimal appa83) 
    {
        this.appa83 = appa83;
    }

    public BigDecimal getAppa83() 
    {
        return appa83;
    }
    public void setAppa8N1(BigDecimal appa8N1) 
    {
        this.appa8N1 = appa8N1;
    }

    public BigDecimal getAppa8N1() 
    {
        return appa8N1;
    }
    public void setOther1(BigDecimal other1) 
    {
        this.other1 = other1;
    }

    public BigDecimal getOther1() 
    {
        return other1;
    }
    public void setAppb1(String appb1) 
    {
        this.appb1 = appb1;
    }

    public String getAppb1() 
    {
        return appb1;
    }
    public void setAppb2(String appb2) 
    {
        this.appb2 = appb2;
    }

    public String getAppb2() 
    {
        return appb2;
    }
    public void setAppb3(String appb3) 
    {
        this.appb3 = appb3;
    }

    public String getAppb3() 
    {
        return appb3;
    }
    public void setAppb4(String appb4) 
    {
        this.appb4 = appb4;
    }

    public String getAppb4() 
    {
        return appb4;
    }
    public void setAppb5(String appb5) 
    {
        this.appb5 = appb5;
    }

    public String getAppb5() 
    {
        return appb5;
    }
    public void setAppb6(String appb6) 
    {
        this.appb6 = appb6;
    }

    public String getAppb6() 
    {
        return appb6;
    }
    public void setAppb7(String appb7) 
    {
        this.appb7 = appb7;
    }

    public String getAppb7() 
    {
        return appb7;
    }
    public void setAppb81(String appb81) 
    {
        this.appb81 = appb81;
    }

    public String getAppb81() 
    {
        return appb81;
    }
    public void setAppb82(String appb82) 
    {
        this.appb82 = appb82;
    }

    public String getAppb82() 
    {
        return appb82;
    }
    public void setAppb9(String appb9) 
    {
        this.appb9 = appb9;
    }

    public String getAppb9() 
    {
        return appb9;
    }
    public void setAppb101(String appb101) 
    {
        this.appb101 = appb101;
    }

    public String getAppb101() 
    {
        return appb101;
    }
    public void setAppb102(String appb102) 
    {
        this.appb102 = appb102;
    }

    public String getAppb102() 
    {
        return appb102;
    }
    public void setAppb11(String appb11) 
    {
        this.appb11 = appb11;
    }

    public String getAppb11() 
    {
        return appb11;
    }
    public void setAppb12(String appb12) 
    {
        this.appb12 = appb12;
    }

    public String getAppb12() 
    {
        return appb12;
    }
    public void setAppb13(String appb13) 
    {
        this.appb13 = appb13;
    }

    public String getAppb13() 
    {
        return appb13;
    }
    public void setAppb14(String appb14) 
    {
        this.appb14 = appb14;
    }

    public String getAppb14() 
    {
        return appb14;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("strIndex", getStrIndex())
            .append("numIndex", getNumIndex())
            .append("EOR", getEOR())
            .append("eor1", getEor1())
            .append("eor1Value", getEor1Value())
            .append("eor2", getEor2())
            .append("eor3", getEor3())
            .append("eor4", getEor4())
            .append("eor5", getEor5())
            .append("eor6", getEor6())
            .append("flatFeeOption", getFlatFeeOption())
            .append("flatFee1", getFlatFee1())
            .append("flatFee2", getFlatFee2())
            .append("flatFee3", getFlatFee3())
            .append("flatFee4", getFlatFee4())
            .append("appa7", getAppa7())
            .append("appa81", getAppa81())
            .append("appa82", getAppa82())
            .append("appa83", getAppa83())
            .append("appa8N1", getAppa8N1())
            .append("other1", getOther1())
            .append("appb1", getAppb1())
            .append("appb2", getAppb2())
            .append("appb3", getAppb3())
            .append("appb4", getAppb4())
            .append("appb5", getAppb5())
            .append("appb6", getAppb6())
            .append("appb7", getAppb7())
            .append("appb81", getAppb81())
            .append("appb82", getAppb82())
            .append("appb9", getAppb9())
            .append("appb101", getAppb101())
            .append("appb102", getAppb102())
            .append("appb11", getAppb11())
            .append("appb12", getAppb12())
            .append("appb13", getAppb13())
            .append("appb14", getAppb14())
            .append("userId", getUserId())
            .toString();
    }
}
