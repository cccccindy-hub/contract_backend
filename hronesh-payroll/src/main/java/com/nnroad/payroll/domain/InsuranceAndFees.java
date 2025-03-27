package com.nnroad.payroll.domain;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * VIEW对象 工会费/工伤保险/雇主责任险_list
 *
 */
public class InsuranceAndFees extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 期间 */
    @Excel(name = "期间")
    private String duration;

    /** 工号 */
    @Excel(name = "工号")
    private String idNo;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 客户编号 */
    @Excel(name = "客户编号")
    private String clientCode;

    /** 客户简称 */
    @Excel(name = "客户简称")
    private String erName;

    /** 工会费 */
    @Excel(name = "工会费")
    private BigDecimal epUnionFee;

    /** 商业保险 */
    @Excel(name = "商业保险")
    private BigDecimal commercialInsurance;

    /** 雇主责任险 */
    @Excel(name = "雇主责任险")
    private BigDecimal employerLiability;

    private String[] clientCodeList;
    private String[] eeCodeList;

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
    public void setClientCode(String clientCode)
    {
        this.clientCode = clientCode;
    }

    public String getClientCode()
    {
        return clientCode;
    }
    public void setErName(String erName)
    {
        this.erName = erName;
    }

    public String getErName()
    {
        return erName;
    }
    public void setEpUnionFee(BigDecimal epUnionFee)
    {
        this.epUnionFee = epUnionFee;
    }

    public BigDecimal getEpUnionFee()
    {
        return epUnionFee;
    }
    public void setCommercialInsurance(BigDecimal commercialInsurance)
    {
        this.commercialInsurance = commercialInsurance;
    }

    public BigDecimal getCommercialInsurance()
    {
        return commercialInsurance;
    }
    public void setEmployerLiability(BigDecimal employerLiability)
    {
        this.employerLiability = employerLiability;
    }

    public BigDecimal getEmployerLiability()
    {
        return employerLiability;
    }

    public String[] getClientCodeList() {
        return clientCodeList;
    }

    public void setClientCodeList(String[] clientCodeList) {
        this.clientCodeList = clientCodeList;
    }

    public String[] getEeCodeList() {
        return eeCodeList;
    }

    public void setEeCodeList(String[] eeCodeList) {
        this.eeCodeList = eeCodeList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("duration", getDuration())
                .append("idNo", getIdNo())
                .append("name", getName())
                .append("clientCode", getClientCode())
                .append("erName", getErName())
                .append("epUnionFee", getEpUnionFee())
                .append("commercialInsurance", getCommercialInsurance())
                .append("employerLiability", getEmployerLiability())
                .toString();
    }
}
