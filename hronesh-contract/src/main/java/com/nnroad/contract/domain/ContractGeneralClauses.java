package com.nnroad.contract.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 contract_general_clauses
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public class ContractGeneralClauses extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal C1;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long C21;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal C22;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String C3;

    /** $column.columnComment */
    private String userid;

    public void setC1(BigDecimal C1) 
    {
        this.C1 = C1;
    }

    public BigDecimal getC1() 
    {
        return C1;
    }
    public void setC21(Long C21) 
    {
        this.C21 = C21;
    }

    public Long getC21() 
    {
        return C21;
    }
    public void setC22(BigDecimal C22) 
    {
        this.C22 = C22;
    }

    public BigDecimal getC22() 
    {
        return C22;
    }
    public void setC3(String C3) 
    {
        this.C3 = C3;
    }

    public String getC3() 
    {
        return C3;
    }
    public void setUserid(String userid) 
    {
        this.userid = userid;
    }

    public String getUserid() 
    {
        return userid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("C1", getC1())
            .append("C21", getC21())
            .append("C22", getC22())
            .append("C3", getC3())
            .append("userid", getUserid())
            .toString();
    }
}

