package com.nnroad.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 contract_hrs
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public class ContractHrs extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** string index */
    @Excel(name = "string index")
    private String strIndex;

    /** number index */
    @Excel(name = "number index")
    private Long numIndex;

    /** Human Resource Standard Templates Employment Contract: USD 600 /version */
    @Excel(name = "Human Resource Standard Templates Employment Contract: USD 600 /version")
    private BigDecimal hrs11;

    /** Human Resource Standard Templates Employee Handbook: USD 3,000 /version */
    @Excel(name = "Human Resource Standard Templates Employee Handbook: USD 3,000 /version")
    private BigDecimal hrs12;

    /** Human Resource Standard Templates Other contracts or agreements: USD 300 /version */
    @Excel(name = "Human Resource Standard Templates Other contracts or agreements: USD 300 /version")
    private BigDecimal hrs13;

    /** Human Resource Standard Templates to USD 600 /version */
    @Excel(name = "Human Resource Standard Templates to USD 600 /version")
    private BigDecimal hrs14;

    /** Severance pays Calculation USD 300 /head */
    @Excel(name = "Severance pays Calculation USD 300 /head")
    private BigDecimal hrs21;

    /** Perennial Advisory USD 8,000 /year */
    @Excel(name = "Perennial Advisory USD 8,000 /year")
    private BigDecimal hrs22;

    /** Perennial Advisory Service hours exceed 25 hours: USD 400 /hour */
    @Excel(name = "Perennial Advisory Service hours exceed 25 hours: USD 400 /hour")
    private BigDecimal hrs23;

    /** Bilingual Legal Documents USD 400 /hour */
    @Excel(name = "Bilingual Legal Documents USD 400 /hour")
    private BigDecimal hrs24;

    /** Human Resources Consultation Projects Consultant USD 400 /hour */
    @Excel(name = "Human Resources Consultation Projects Consultant USD 400 /hour")
    private BigDecimal hrs25;

    /** Client Customized Service Assistant: USD 100 /hour */
    @Excel(name = "Client Customized Service Assistant: USD 100 /hour")
    private BigDecimal hrs26;

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
    public void setNumIndex(Long numIndex) 
    {
        this.numIndex = numIndex;
    }

    public Long getNumIndex() 
    {
        return numIndex;
    }
    public void setHrs11(BigDecimal hrs11) 
    {
        this.hrs11 = hrs11;
    }

    public BigDecimal getHrs11() 
    {
        return hrs11;
    }
    public void setHrs12(BigDecimal hrs12) 
    {
        this.hrs12 = hrs12;
    }

    public BigDecimal getHrs12() 
    {
        return hrs12;
    }
    public void setHrs13(BigDecimal hrs13) 
    {
        this.hrs13 = hrs13;
    }

    public BigDecimal getHrs13() 
    {
        return hrs13;
    }
    public void setHrs14(BigDecimal hrs14) 
    {
        this.hrs14 = hrs14;
    }

    public BigDecimal getHrs14() 
    {
        return hrs14;
    }
    public void setHrs21(BigDecimal hrs21) 
    {
        this.hrs21 = hrs21;
    }

    public BigDecimal getHrs21() 
    {
        return hrs21;
    }
    public void setHrs22(BigDecimal hrs22) 
    {
        this.hrs22 = hrs22;
    }

    public BigDecimal getHrs22() 
    {
        return hrs22;
    }
    public void setHrs23(BigDecimal hrs23) 
    {
        this.hrs23 = hrs23;
    }

    public BigDecimal getHrs23() 
    {
        return hrs23;
    }
    public void setHrs24(BigDecimal hrs24) 
    {
        this.hrs24 = hrs24;
    }

    public BigDecimal getHrs24() 
    {
        return hrs24;
    }
    public void setHrs25(BigDecimal hrs25) 
    {
        this.hrs25 = hrs25;
    }

    public BigDecimal getHrs25() 
    {
        return hrs25;
    }
    public void setHrs26(BigDecimal hrs26) 
    {
        this.hrs26 = hrs26;
    }

    public BigDecimal getHrs26() 
    {
        return hrs26;
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
            .append("hrs11", getHrs11())
            .append("hrs12", getHrs12())
            .append("hrs13", getHrs13())
            .append("hrs14", getHrs14())
            .append("hrs21", getHrs21())
            .append("hrs22", getHrs22())
            .append("hrs23", getHrs23())
            .append("hrs24", getHrs24())
            .append("hrs25", getHrs25())
            .append("hrs26", getHrs26())
            .append("userId", getUserId())
            .toString();
    }
}
