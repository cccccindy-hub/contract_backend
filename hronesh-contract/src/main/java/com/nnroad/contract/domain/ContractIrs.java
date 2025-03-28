package com.nnroad.contract.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 contract_irs
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public class ContractIrs extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** string index */
    @Excel(name = "string index")
    private String strIndex;

    /** number index */
    @Excel(name = "number index")
    private Long numIndex;

    /** Domestic citizenship, education information: USD 50 /person */
    @Excel(name = "Domestic citizenship, education information: USD 50 /person")
    private BigDecimal rcs21;

    /** Foreign identity, education information from outside Mainland China: USD 100 /person */
    @Excel(name = "Foreign identity, education information from outside Mainland China: USD 100 /person")
    private BigDecimal rcs22;

    /** Work experience: USD 100 /each company */
    @Excel(name = "Work experience: USD 100 /each company")
    private BigDecimal rcs23;

    /** Criminal Record Check USD 300 /Submission */
    @Excel(name = "Criminal Record Check USD 300 /Submission")
    private BigDecimal rcs24;

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
    public void setRcs21(BigDecimal rcs21) 
    {
        this.rcs21 = rcs21;
    }

    public BigDecimal getRcs21() 
    {
        return rcs21;
    }
    public void setRcs22(BigDecimal rcs22) 
    {
        this.rcs22 = rcs22;
    }

    public BigDecimal getRcs22() 
    {
        return rcs22;
    }
    public void setRcs23(BigDecimal rcs23) 
    {
        this.rcs23 = rcs23;
    }

    public BigDecimal getRcs23() 
    {
        return rcs23;
    }
    public void setRcs24(BigDecimal rcs24) 
    {
        this.rcs24 = rcs24;
    }

    public BigDecimal getRcs24() 
    {
        return rcs24;
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
            .append("rcs21", getRcs21())
            .append("rcs22", getRcs22())
            .append("rcs23", getRcs23())
            .append("rcs24", getRcs24())
            .append("userId", getUserId())
            .toString();
    }
}
