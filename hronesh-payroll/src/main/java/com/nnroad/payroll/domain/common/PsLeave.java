package com.nnroad.payroll.domain.common;


import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * leave对象 ps_leave
 * 
 * @author Aaron
 * @date 2021-11-30
 */
public class PsLeave extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 员工编号 */
    @Excel(name = "员工编号")
    private String eeNo;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String eeName;

    /** 假期类型 */
    @Excel(name = "假期类型")
    private String leaveName;

    /** 总数 */
    @Excel(name = "总数")
    private BigDecimal total;

    /** 已使用 */
    @Excel(name = "已使用")
    private BigDecimal used;

    /** 剩余 */
//    @Excel(name = "剩余")
    private BigDecimal remaining;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setEeNo(String eeNo)
    {
        this.eeNo = eeNo;
    }

    public String getEeNo()
    {
        return eeNo;
    }
    public void setEeName(String eeName)
    {
        this.eeName = eeName;
    }

    public String getEeName()
    {
        return eeName;
    }
    public void setLeaveName(String leaveName)
    {
        this.leaveName = leaveName;
    }

    public String getLeaveName()
    {
        return leaveName;
    }
    public void setTotal(BigDecimal total)
    {
        this.total = total;
    }

    public BigDecimal getTotal()
    {
        return total;
    }
    public void setUsed(BigDecimal used)
    {
        this.used = used;
    }

    public BigDecimal getUsed()
    {
        return used;
    }
    public void setRemaining(BigDecimal remaining)
    {
        this.remaining = remaining;
    }

    public BigDecimal getRemaining()
    {
        return remaining;
    }
    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    public String getRemarks()
    {
        return remarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("eeNo", getEeNo())
                .append("eeName", getEeName())
                .append("leaveName", getLeaveName())
                .append("total", getTotal())
                .append("used", getUsed())
                .append("remaining", getRemaining())
                .append("remarks", getRemarks())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
