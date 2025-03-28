package com.nnroad.contract.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 contract_cis
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public class ContractCis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** string index */
    @Excel(name = "string index")
    private String stringIndex;

    /** number index */
    @Excel(name = "number index")
    private BigDecimal numIndex;

    /** Basic AD&D RMB */
    @Excel(name = "Basic AD&D RMB")
    private BigDecimal cis11;

    /** Standard AD&D RMB */
    @Excel(name = "Standard AD&D RMB")
    private BigDecimal cis12;

    /** Advanced AD&D */
    @Excel(name = "Advanced AD&D")
    private BigDecimal cis13;

    /** Premium AD&D */
    @Excel(name = "Premium AD&D")
    private BigDecimal cis14;

    /** Basic Public Transport AD&D */
    @Excel(name = "Basic Public Transport AD&D")
    private BigDecimal cis21;

    /** Standard Public Transport AD&D */
    @Excel(name = "Standard Public Transport AD&D")
    private BigDecimal cis22;

    /** Advanced Public Transport AD&D */
    @Excel(name = "Advanced Public Transport AD&D")
    private BigDecimal cis23;

    /** Premium Public Transport AD&D */
    @Excel(name = "Premium Public Transport AD&D")
    private String cis24;

    /** Basic Illness Death */
    @Excel(name = "Basic Illness Death")
    private BigDecimal cis31;

    /** Standard Illness Death */
    @Excel(name = "Standard Illness Death")
    private BigDecimal cis32;

    /** Advanced Illness Death */
    @Excel(name = "Advanced Illness Death")
    private BigDecimal cis33;

    /** Premium Illness Death */
    @Excel(name = "Premium Illness Death")
    private BigDecimal cis34;

    /** Critical Illness (35 diseases) Basic */
    @Excel(name = "Critical Illness (35 diseases) Basic")
    private BigDecimal cis41;

    /** Critical Illness (35 diseases) Standard */
    @Excel(name = "Critical Illness (35 diseases) Standard")
    private BigDecimal cis42;

    /** Critical Illness (35 diseases) Advanced */
    @Excel(name = "Critical Illness (35 diseases) Advanced")
    private BigDecimal cis43;

    /** Critical Illness (35 diseases) Premium */
    @Excel(name = "Critical Illness (35 diseases) Premium")
    private BigDecimal cis44;

    /** Accidental Medical Insurance Basic */
    @Excel(name = "Accidental Medical Insurance Basic")
    private BigDecimal cis51;

    /** Accidental Medical Insurance Standard */
    @Excel(name = "Accidental Medical Insurance Standard")
    private BigDecimal cis52;

    /** Accidental Medical Insurance Advanced */
    @Excel(name = "Accidental Medical Insurance Advanced")
    private BigDecimal cis53;

    /** Accidental Medical Insurance Premium */
    @Excel(name = "Accidental Medical Insurance Premium")
    private BigDecimal cis54;

    /** Outpatient/Inpatient (No Deductible) Sum Assured RMB 20,000 */
    @Excel(name = "Outpatient/Inpatient (No Deductible) Sum Assured RMB 20,000")
    private BigDecimal cis60;

    /** Outpatient % reimburse */
    @Excel(name = "Outpatient % reimburse")
    private BigDecimal cis61;

    /** Outpatient Sum Assured */
    @Excel(name = "Outpatient Sum Assured")
    private BigDecimal cis62;

    /** Inpatient % Reimbursement */
    @Excel(name = "Inpatient % Reimbursement")
    private BigDecimal cis63;

    /** Inpatient Sum Assured */
    @Excel(name = "Inpatient Sum Assured")
    private BigDecimal cis64;

    /** Reimburse 90% */
    @Excel(name = "Reimburse 90%")
    private BigDecimal cis65;

    /** Reimburse 100% */
    @Excel(name = "Reimburse 100%")
    private BigDecimal cis66;

    /** Reimburse 100% */
    @Excel(name = "Reimburse 100%")
    private BigDecimal cis67;

    /** Inpatient Allowance */
    @Excel(name = "Inpatient Allowance")
    private String cis70;

    /** Inpatient Allowance RMB 100 /day */
    @Excel(name = "Inpatient Allowance RMB 100 /day")
    private BigDecimal cis711;

    /** Inpatient Allowance Max. 180 days */
    @Excel(name = "Inpatient Allowance Max. 180 days")
    private BigDecimal cis712;

    /** Inpatient Allowance RMB 150 /day */
    @Excel(name = "Inpatient Allowance RMB 150 /day")
    private BigDecimal cis721;

    /** Inpatient Allowance Max. 180 days */
    @Excel(name = "Inpatient Allowance Max. 180 days")
    private BigDecimal cis722;

    /** Inpatient Allowance RMB 150 /day */
    @Excel(name = "Inpatient Allowance RMB 150 /day")
    private BigDecimal cis731;

    /** Inpatient Allowance Max. 180 days */
    @Excel(name = "Inpatient Allowance Max. 180 days")
    private BigDecimal cis732;

    /** Pricing/month/person RMB 230 */
    @Excel(name = "Pricing/month/person RMB 230")
    private BigDecimal cis741;

    /** Pricing/month/person RMB 270 */
    @Excel(name = "Pricing/month/person RMB 270")
    private BigDecimal cis742;

    /** Pricing/month/person RMB 310 */
    @Excel(name = "Pricing/month/person RMB 310")
    private BigDecimal cis743;

    /** Pricing/month/person RMB 465 */
    @Excel(name = "Pricing/month/person RMB 465")
    private BigDecimal cis744;

    /** Normal labor RMB 3,000 per time */
    @Excel(name = "Normal labor RMB 3,000 per time")
    private BigDecimal cis81;

    /** Normal labor */
    @Excel(name = "Normal labor")
    private String cis82;

    /** Spouse / Children B1 % Reimbursement 50% */
    @Excel(name = "Spouse / Children B1 % Reimbursement 50%")
    private BigDecimal cis91;

    /** Spouse / Children B1 Sum Assured 10,000 */
    @Excel(name = "Spouse / Children B1 Sum Assured 10,000")
    private BigDecimal cis92;

    /** Spouse / Children B1 RMB 155 per head per month */
    @Excel(name = "Spouse / Children B1 RMB 155 per head per month")
    private BigDecimal cis93;

    /** Spouse / Children B1 Children under 14 years old */
    @Excel(name = "Spouse / Children B1 Children under 14 years old")
    private Long cis94;

    /** B2 % Children medical Reimbursement 80% */
    @Excel(name = "B2 % Children medical Reimbursement 80%")
    private BigDecimal cis101;

    /** B2 Sum Assured 50,000 */
    @Excel(name = "B2 Sum Assured 50,000")
    private BigDecimal cis102;

    /** B2 Children under 14 years old */
    @Excel(name = "B2 Children under 14 years old")
    private Long cis103;

    /** B2 RMB 210 per head per month */
    @Excel(name = "B2 RMB 210 per head per month")
    private BigDecimal cis104;

    /** $column.columnComment */
    private String userId;

    public void setStringIndex(String stringIndex) 
    {
        this.stringIndex = stringIndex;
    }

    public String getStringIndex() 
    {
        return stringIndex;
    }
    public void setNumIndex(BigDecimal numIndex) 
    {
        this.numIndex = numIndex;
    }

    public BigDecimal getNumIndex() 
    {
        return numIndex;
    }
    public void setCis11(BigDecimal cis11) 
    {
        this.cis11 = cis11;
    }

    public BigDecimal getCis11() 
    {
        return cis11;
    }
    public void setCis12(BigDecimal cis12) 
    {
        this.cis12 = cis12;
    }

    public BigDecimal getCis12() 
    {
        return cis12;
    }
    public void setCis13(BigDecimal cis13) 
    {
        this.cis13 = cis13;
    }

    public BigDecimal getCis13() 
    {
        return cis13;
    }
    public void setCis14(BigDecimal cis14) 
    {
        this.cis14 = cis14;
    }

    public BigDecimal getCis14() 
    {
        return cis14;
    }
    public void setCis21(BigDecimal cis21) 
    {
        this.cis21 = cis21;
    }

    public BigDecimal getCis21() 
    {
        return cis21;
    }
    public void setCis22(BigDecimal cis22) 
    {
        this.cis22 = cis22;
    }

    public BigDecimal getCis22() 
    {
        return cis22;
    }
    public void setCis23(BigDecimal cis23) 
    {
        this.cis23 = cis23;
    }

    public BigDecimal getCis23() 
    {
        return cis23;
    }
    public void setCis24(String cis24) 
    {
        this.cis24 = cis24;
    }

    public String getCis24() 
    {
        return cis24;
    }
    public void setCis31(BigDecimal cis31) 
    {
        this.cis31 = cis31;
    }

    public BigDecimal getCis31() 
    {
        return cis31;
    }
    public void setCis32(BigDecimal cis32) 
    {
        this.cis32 = cis32;
    }

    public BigDecimal getCis32() 
    {
        return cis32;
    }
    public void setCis33(BigDecimal cis33) 
    {
        this.cis33 = cis33;
    }

    public BigDecimal getCis33() 
    {
        return cis33;
    }
    public void setCis34(BigDecimal cis34) 
    {
        this.cis34 = cis34;
    }

    public BigDecimal getCis34() 
    {
        return cis34;
    }
    public void setCis41(BigDecimal cis41) 
    {
        this.cis41 = cis41;
    }

    public BigDecimal getCis41() 
    {
        return cis41;
    }
    public void setCis42(BigDecimal cis42) 
    {
        this.cis42 = cis42;
    }

    public BigDecimal getCis42() 
    {
        return cis42;
    }
    public void setCis43(BigDecimal cis43) 
    {
        this.cis43 = cis43;
    }

    public BigDecimal getCis43() 
    {
        return cis43;
    }
    public void setCis44(BigDecimal cis44) 
    {
        this.cis44 = cis44;
    }

    public BigDecimal getCis44() 
    {
        return cis44;
    }
    public void setCis51(BigDecimal cis51) 
    {
        this.cis51 = cis51;
    }

    public BigDecimal getCis51() 
    {
        return cis51;
    }
    public void setCis52(BigDecimal cis52) 
    {
        this.cis52 = cis52;
    }

    public BigDecimal getCis52() 
    {
        return cis52;
    }
    public void setCis53(BigDecimal cis53) 
    {
        this.cis53 = cis53;
    }

    public BigDecimal getCis53() 
    {
        return cis53;
    }
    public void setCis54(BigDecimal cis54) 
    {
        this.cis54 = cis54;
    }

    public BigDecimal getCis54() 
    {
        return cis54;
    }
    public void setCis60(BigDecimal cis60) 
    {
        this.cis60 = cis60;
    }

    public BigDecimal getCis60() 
    {
        return cis60;
    }
    public void setCis61(BigDecimal cis61) 
    {
        this.cis61 = cis61;
    }

    public BigDecimal getCis61() 
    {
        return cis61;
    }
    public void setCis62(BigDecimal cis62) 
    {
        this.cis62 = cis62;
    }

    public BigDecimal getCis62() 
    {
        return cis62;
    }
    public void setCis63(BigDecimal cis63) 
    {
        this.cis63 = cis63;
    }

    public BigDecimal getCis63() 
    {
        return cis63;
    }
    public void setCis64(BigDecimal cis64) 
    {
        this.cis64 = cis64;
    }

    public BigDecimal getCis64() 
    {
        return cis64;
    }
    public void setCis65(BigDecimal cis65) 
    {
        this.cis65 = cis65;
    }

    public BigDecimal getCis65() 
    {
        return cis65;
    }
    public void setCis66(BigDecimal cis66) 
    {
        this.cis66 = cis66;
    }

    public BigDecimal getCis66() 
    {
        return cis66;
    }
    public void setCis67(BigDecimal cis67) 
    {
        this.cis67 = cis67;
    }

    public BigDecimal getCis67() 
    {
        return cis67;
    }
    public void setCis70(String cis70) 
    {
        this.cis70 = cis70;
    }

    public String getCis70() 
    {
        return cis70;
    }
    public void setCis711(BigDecimal cis711) 
    {
        this.cis711 = cis711;
    }

    public BigDecimal getCis711() 
    {
        return cis711;
    }
    public void setCis712(BigDecimal cis712) 
    {
        this.cis712 = cis712;
    }

    public BigDecimal getCis712() 
    {
        return cis712;
    }
    public void setCis721(BigDecimal cis721) 
    {
        this.cis721 = cis721;
    }

    public BigDecimal getCis721() 
    {
        return cis721;
    }
    public void setCis722(BigDecimal cis722) 
    {
        this.cis722 = cis722;
    }

    public BigDecimal getCis722() 
    {
        return cis722;
    }
    public void setCis731(BigDecimal cis731) 
    {
        this.cis731 = cis731;
    }

    public BigDecimal getCis731() 
    {
        return cis731;
    }
    public void setCis732(BigDecimal cis732) 
    {
        this.cis732 = cis732;
    }

    public BigDecimal getCis732() 
    {
        return cis732;
    }
    public void setCis741(BigDecimal cis741) 
    {
        this.cis741 = cis741;
    }

    public BigDecimal getCis741() 
    {
        return cis741;
    }
    public void setCis742(BigDecimal cis742) 
    {
        this.cis742 = cis742;
    }

    public BigDecimal getCis742() 
    {
        return cis742;
    }
    public void setCis743(BigDecimal cis743) 
    {
        this.cis743 = cis743;
    }

    public BigDecimal getCis743() 
    {
        return cis743;
    }
    public void setCis744(BigDecimal cis744) 
    {
        this.cis744 = cis744;
    }

    public BigDecimal getCis744() 
    {
        return cis744;
    }
    public void setCis81(BigDecimal cis81) 
    {
        this.cis81 = cis81;
    }

    public BigDecimal getCis81() 
    {
        return cis81;
    }
    public void setCis82(String cis82) 
    {
        this.cis82 = cis82;
    }

    public String getCis82() 
    {
        return cis82;
    }
    public void setCis91(BigDecimal cis91) 
    {
        this.cis91 = cis91;
    }

    public BigDecimal getCis91() 
    {
        return cis91;
    }
    public void setCis92(BigDecimal cis92) 
    {
        this.cis92 = cis92;
    }

    public BigDecimal getCis92() 
    {
        return cis92;
    }
    public void setCis93(BigDecimal cis93) 
    {
        this.cis93 = cis93;
    }

    public BigDecimal getCis93() 
    {
        return cis93;
    }
    public void setCis94(Long cis94) 
    {
        this.cis94 = cis94;
    }

    public Long getCis94() 
    {
        return cis94;
    }
    public void setCis101(BigDecimal cis101) 
    {
        this.cis101 = cis101;
    }

    public BigDecimal getCis101() 
    {
        return cis101;
    }
    public void setCis102(BigDecimal cis102) 
    {
        this.cis102 = cis102;
    }

    public BigDecimal getCis102() 
    {
        return cis102;
    }
    public void setCis103(Long cis103) 
    {
        this.cis103 = cis103;
    }

    public Long getCis103() 
    {
        return cis103;
    }
    public void setCis104(BigDecimal cis104) 
    {
        this.cis104 = cis104;
    }

    public BigDecimal getCis104() 
    {
        return cis104;
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
            .append("stringIndex", getStringIndex())
            .append("numIndex", getNumIndex())
            .append("cis11", getCis11())
            .append("cis12", getCis12())
            .append("cis13", getCis13())
            .append("cis14", getCis14())
            .append("cis21", getCis21())
            .append("cis22", getCis22())
            .append("cis23", getCis23())
            .append("cis24", getCis24())
            .append("cis31", getCis31())
            .append("cis32", getCis32())
            .append("cis33", getCis33())
            .append("cis34", getCis34())
            .append("cis41", getCis41())
            .append("cis42", getCis42())
            .append("cis43", getCis43())
            .append("cis44", getCis44())
            .append("cis51", getCis51())
            .append("cis52", getCis52())
            .append("cis53", getCis53())
            .append("cis54", getCis54())
            .append("cis60", getCis60())
            .append("cis61", getCis61())
            .append("cis62", getCis62())
            .append("cis63", getCis63())
            .append("cis64", getCis64())
            .append("cis65", getCis65())
            .append("cis66", getCis66())
            .append("cis67", getCis67())
            .append("cis70", getCis70())
            .append("cis711", getCis711())
            .append("cis712", getCis712())
            .append("cis721", getCis721())
            .append("cis722", getCis722())
            .append("cis731", getCis731())
            .append("cis732", getCis732())
            .append("cis741", getCis741())
            .append("cis742", getCis742())
            .append("cis743", getCis743())
            .append("cis744", getCis744())
            .append("cis81", getCis81())
            .append("cis82", getCis82())
            .append("cis91", getCis91())
            .append("cis92", getCis92())
            .append("cis93", getCis93())
            .append("cis94", getCis94())
            .append("cis101", getCis101())
            .append("cis102", getCis102())
            .append("cis103", getCis103())
            .append("cis104", getCis104())
            .append("userId", getUserId())
            .toString();
    }
}
