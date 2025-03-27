package com.nnroad.payroll.domain.exportC;


import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 其他费用
 * 
 * @author Hrone
 * @date 2021-06-21
 */
public class SOtherFeeReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    Long id;
    /** 期间 */
    private String duration;

    /** 雇主id */
    private String erId;

    /** 客户名 */
    private String erName;

    /** 服务名称 */
    private String serviceName;

    /** 服务类型 */
    private int paymentType;

    /** 服务费用 */
    private BigDecimal serviceCost;

    /** 服务费税 */
    private BigDecimal serviceTax;


    public void setDuration(String duration) { this.duration = duration; }
    public String getDuration() { return duration; }

    public String getErId() { return erId; }
    public void setErId(String erId) { this.erId = erId; }

    public void setErName(String erName) { this.erName = erName; }
    public String getErName() { return erName; }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public int getPaymentType() { return paymentType; }
    public void setPaymentType(int paymentType) { this.paymentType = paymentType; }

    public BigDecimal getServiceCost() { return serviceCost; }
    public void setServiceCost(BigDecimal serviceCost) { this.serviceCost = serviceCost; }

    public BigDecimal getServiceTax() {
        return serviceTax;
    }
    public void setServiceTax(BigDecimal serviceTax) {
        this.serviceTax = serviceTax;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("duration", getDuration())
            .append("erId", getErId())
            .append("erName", getErName())
            .append("serviceName", getServiceName())
            .append("paymentType", getPaymentType())
            .append("serviceCost", getServiceCost())
            .append("serviceTax", getServiceTax())
            .toString();
    }
}
