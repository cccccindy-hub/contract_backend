package com.nnroad.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 contact_rs
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public class ContactRs extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** section number */
    @Excel(name = "section number")
    private String indexColumn;

    /** string index */
    @Excel(name = "string index")
    private String stringIndex;

    /** recruitment agency service fee 20 percentage */
    @Excel(name = "recruitment agency service fee 20 percentage")
    private BigDecimal rs21;

    /** USD */
    @Excel(name = "USD")
    private BigDecimal rs22;

    /** recruitment agency service fee delays 0.2% percentage */
    @Excel(name = "recruitment agency service fee delays 0.2% percentage")
    private BigDecimal rs6;

    /** recruitment agency service candidate 24  */
    @Excel(name = "recruitment agency service candidate 24 ")
    private Long rs7;

    /** $column.columnComment */
    private String userId;

    public void setIndexColumn(String indexColumn) 
    {
        this.indexColumn = indexColumn;
    }

    public String getIndexColumn() 
    {
        return indexColumn;
    }
    public void setStringIndex(String stringIndex) 
    {
        this.stringIndex = stringIndex;
    }

    public String getStringIndex() 
    {
        return stringIndex;
    }
    public void setRs21(BigDecimal rs21) 
    {
        this.rs21 = rs21;
    }

    public BigDecimal getRs21() 
    {
        return rs21;
    }
    public void setRs22(BigDecimal rs22) 
    {
        this.rs22 = rs22;
    }

    public BigDecimal getRs22() 
    {
        return rs22;
    }
    public void setRs6(BigDecimal rs6) 
    {
        this.rs6 = rs6;
    }

    public BigDecimal getRs6() 
    {
        return rs6;
    }
    public void setRs7(Long rs7) 
    {
        this.rs7 = rs7;
    }

    public Long getRs7() 
    {
        return rs7;
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
            .append("indexColumn", getIndexColumn())
            .append("stringIndex", getStringIndex())
            .append("rs21", getRs21())
            .append("rs22", getRs22())
            .append("rs6", getRs6())
            .append("rs7", getRs7())
            .append("userId", getUserId())
            .toString();
    }
}
