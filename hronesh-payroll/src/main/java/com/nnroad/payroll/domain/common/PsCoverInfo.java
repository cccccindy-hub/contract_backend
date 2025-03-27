package com.nnroad.payroll.domain.common;

import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * payment notice封面信息对象 ps_cover_info
 * 
 * @author Hrone
 * @date 2021-07-07
 */
public class PsCoverInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物理ID */
    private Long id;

    /** 周期 */
    @Excel(name = "周期")
    private String duration;

    /** 雇主编号 */
    @Excel(name = "雇主编号")
    private String erNo;

    /** 雇主名称 */
    @Excel(name = "雇主名称")
    private String erName;

    /** 货币 */
    @Excel(name = "货币")
    private String currency;

    /** 汇率 */
    @Excel(name = "汇率")
    private BigDecimal exchangeRate;

    /** 银行信息-英文 */
    @Excel(name = "银行信息-英文")
    private String bankInfoEn;

    /** 银行信息-中文 */
    @Excel(name = "银行信息-中文")
    private String bankInfoCn;

    /** 权限组 */
    private String groupIds;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

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

    public void setErNo(String erNo) 
    {
        this.erNo = erNo;
    }
    public String getErNo() 
    {
        return erNo;
    }

    public void setErName(String erName) 
    {
        this.erName = erName;
    }
    public String getErName() 
    {
        return erName;
    }

    public void setCurrency(String currency) 
    {
        this.currency = currency;
    }
    public String getCurrency() 
    {
        return currency;
    }

    public void setExchangeRate(BigDecimal exchangeRate) 
    {
        this.exchangeRate = exchangeRate;
    }
    public BigDecimal getExchangeRate() 
    {
        return exchangeRate;
    }

    public String getBankInfoEn() {
        return bankInfoEn;
    }
    public void setBankInfoEn(String bankInfoEn) {
        this.bankInfoEn = bankInfoEn;
    }

    public String getBankInfoCn() {
        return bankInfoCn;
    }
    public void setBankInfoCn(String bankInfoCn) {
        this.bankInfoCn = bankInfoCn;
    }

    public void setGroupIds(String groupIds)
    {
        this.groupIds = groupIds;
    }
    public String getGroupIds() 
    {
        return groupIds;
    }

    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }
    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("duration", getDuration())
            .append("erNo", getErNo())
            .append("erName", getErName())
            .append("currency", getCurrency())
            .append("exchangeRate", getExchangeRate())
            .append("bankInfoEn", getBankInfoEn())
            .append("bankInfoCn", getBankInfoCn())
            .append("groupIds", getGroupIds())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
