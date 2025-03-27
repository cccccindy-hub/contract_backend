package com.nnroad.client.domain;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 client_other_service_fee_payment
 *
 * @author ruoyi
 * @date 2024-12-19
 */
public class ClientOtherServiceFeePayment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long clientOtherServiceFeeId;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String serviceName;

    /** 服务编码 */
    @Excel(name = "服务编码")
    private BigDecimal serviceCost;

    private Integer proofCount;

    /** 服务描述 */
    @Excel(name = "服务描述")
    private String serviceRemark;

    /** 服务描述 */
    @Excel(name = "服务描述")
    private Long paymentType;

    /** 货币类型 */
    @Excel(name = "货币类型")
    private String wantedCurrency;

    /** 目标货币类型 */
    @Excel(name = "目标货币类型")
    private String targetCurrency;

    /** 是否含税 */
    @Excel(name = "是否含税")
    private Boolean hasTax;

    /** 汇率 */
    @Excel(name = "汇率")
    private BigDecimal exchangeRate;

    private Double vatRate;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifiedAt;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setClientOtherServiceFeeId(Long clientOtherServiceFeeId)
    {
        this.clientOtherServiceFeeId = clientOtherServiceFeeId;
    }

    public Long getClientOtherServiceFeeId()
    {
        return clientOtherServiceFeeId;
    }
    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getServiceName()
    {
        return serviceName;
    }
    public void setServiceCost(BigDecimal serviceCost)
    {
        this.serviceCost = serviceCost;
    }

    public Integer getProofCount() {
        return proofCount;
    }

    public void setProofCount(Integer proofCount) {
        this.proofCount = proofCount;
    }

    public BigDecimal getServiceCost()
    {
        return serviceCost;
    }
    public void setServiceRemark(String serviceRemark)
    {
        this.serviceRemark = serviceRemark;
    }

    public String getServiceRemark()
    {
        return serviceRemark;
    }

    public void setPaymentType(Long paymentType)
    {
        this.paymentType = paymentType;
    }

    public Long getPaymentType()
    {
        return paymentType;
    }
    public void setWantedCurrency(String wantedCurrency)
    {
        this.wantedCurrency = wantedCurrency;
    }

    public String getWantedCurrency()
    {
        return wantedCurrency;
    }
    public void setTargetCurrency(String targetCurrency)
    {
        this.targetCurrency = targetCurrency;
    }

    public String getTargetCurrency()
    {
        return targetCurrency;
    }
    public void setHasTax(Boolean hasTax)
    {
        this.hasTax = hasTax;
    }

    public Boolean getHasTax()
    {
        return hasTax;
    }
    public void setExchangeRate(BigDecimal exchangeRate)
    {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getExchangeRate()
    {
        return exchangeRate;
    }
    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }
    public void setModifiedAt(Date modifiedAt)
    {
        this.modifiedAt = modifiedAt;
    }

    public Date getModifiedAt()
    {
        return modifiedAt;
    }

    public Double getVatRate() {
        return vatRate;
    }

    public void setVatRate(Double vatRate) {
        this.vatRate = vatRate;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("clientOtherServiceFeeId", getClientOtherServiceFeeId())
                .append("serviceName", getServiceName())
                .append("serviceCost", getServiceCost())
                .append("serviceRemark", getServiceRemark())
                .append("paymentType", getPaymentType())
                .append("wantedCurrency", getWantedCurrency())
                .append("targetCurrency", getTargetCurrency())
                .append("hasTax", getHasTax())
                .append("exchangeRate", getExchangeRate())
                .append("createBy", getCreateBy())
                .append("createdAt", getCreatedAt())
                .append("updateBy", getUpdateBy())
                .append("modifiedAt", getModifiedAt())
                .toString();
    }
}
