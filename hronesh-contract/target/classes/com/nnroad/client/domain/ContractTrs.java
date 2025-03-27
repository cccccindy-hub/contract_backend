package com.nnroad.client.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 contract_trs
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public class ContractTrs extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** string index */
    @Excel(name = "string index")
    private String strIndex;

    /** number index */
    @Excel(name = "number index")
    private Long numIndex;

    /** Employee annual tax return – Mainland Income USD 300 /head/application */
    @Excel(name = "Employee annual tax return – Mainland Income USD 300 /head/application")
    private BigDecimal atr1;

    /** Employee annual tax return – Mainland + Oversea Income USD 500 /head/application */
    @Excel(name = "Employee annual tax return – Mainland + Oversea Income USD 500 /head/application")
    private BigDecimal atr2;

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
    public void setAtr1(BigDecimal atr1) 
    {
        this.atr1 = atr1;
    }

    public BigDecimal getAtr1() 
    {
        return atr1;
    }
    public void setAtr2(BigDecimal atr2) 
    {
        this.atr2 = atr2;
    }

    public BigDecimal getAtr2() 
    {
        return atr2;
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
            .append("atr1", getAtr1())
            .append("atr2", getAtr2())
            .append("userId", getUserId())
            .toString();
    }
}
