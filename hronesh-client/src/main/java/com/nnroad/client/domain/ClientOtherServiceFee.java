package com.nnroad.client.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 client_other_service_fee
 *
 * @author ruoyi
 * @date 2024-12-17
 */
public class ClientOtherServiceFee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long clientId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long employeeId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String clientName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String companyCode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String employeeName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String employeeCode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date period;

    @Excel(name = "数据拥有者")
    private String ownerIds;

    /**  服务费  */
    List<ClientOtherServiceFeePayment> otherServiceFee ;

    /**  其他代收代付 */
    List<ClientOtherServiceFeePayment> otherBehalfFee;

    List<ClientOtherServiceFeePayment> oneTimeService;

    ClientOtherServiceFeePayment delayFee;

    ClientOtherServiceFeePayment balance;

    ClientOtherServiceFeePayment yearlyManagementServiceFee;

    ClientOtherServiceFeePayment expenseClaimServiceFee;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String extraData;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date createdAt;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date modifiedAt;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setClientId(Long clientId)
    {
        this.clientId = clientId;
    }

    public Long getClientId()
    {
        return clientId;
    }
    public void setEmployeeId(Long employeeId)
    {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId()
    {
        return employeeId;
    }

    public void setClientName(String clientName)
    {
        this.clientName = clientName;
    }

    public String getClientName()
    {
        return clientName;
    }
    public void setCompanyCode(String companyCode)
    {
        this.companyCode = companyCode;
    }

    public String getCompanyCode()
    {
        return companyCode;
    }
    public void setEmployeeName(String employeeName)
    {
        this.employeeName = employeeName;
    }

    public String getEmployeeName()
    {
        return employeeName;
    }
    public void setEmployeeCode(String employeeCode)
    {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeCode()
    {
        return employeeCode;
    }
    public void setPeriod(Date period)
    {
        this.period = period;
    }

    public Date getPeriod()
    {
        return period;
    }
    public void setExtraData(String extraData)
    {
        this.extraData = extraData;
    }

    public String getExtraData()
    {
        return extraData;
    }

    public List<ClientOtherServiceFeePayment> getOtherServiceFee() {
        return otherServiceFee;
    }

    public void setOtherServiceFee(List<ClientOtherServiceFeePayment> otherServiceFee) {
        this.otherServiceFee = otherServiceFee;
    }


    public List<ClientOtherServiceFeePayment> getOtherBehalfFee() {
        return otherBehalfFee;
    }

    public void setOtherBehalfFee(List<ClientOtherServiceFeePayment> otherBehalfFee) {
        this.otherBehalfFee = otherBehalfFee;
    }

    public List<ClientOtherServiceFeePayment> getOneTimeService() {
        return oneTimeService;
    }

    public void setOneTimeService(List<ClientOtherServiceFeePayment> oneTimeService) {
        this.oneTimeService = oneTimeService;
    }

    public ClientOtherServiceFeePayment getDelayFee() {
        return delayFee;
    }

    public void setDelayFee(ClientOtherServiceFeePayment delayFee) {
        this.delayFee = delayFee;
    }

    public ClientOtherServiceFeePayment getBalance() {
        return balance;
    }

    public void setBalance(ClientOtherServiceFeePayment balance) {
        this.balance = balance;
    }

    public ClientOtherServiceFeePayment getYearlyManagementServiceFee() {
        return yearlyManagementServiceFee;
    }

    public void setYearlyManagementServiceFee(ClientOtherServiceFeePayment yearlyManagementServiceFee) {
        this.yearlyManagementServiceFee = yearlyManagementServiceFee;
    }

    public ClientOtherServiceFeePayment getExpenseClaimServiceFee() {
        return expenseClaimServiceFee;
    }

    public void setExpenseClaimServiceFee(ClientOtherServiceFeePayment expenseClaimServiceFee) {
        this.expenseClaimServiceFee = expenseClaimServiceFee;
    }

    public String getOwnerIds() {
        return ownerIds;
    }

    public void setOwnerIds(String ownerIds) {
        this.ownerIds = ownerIds;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("clientId", getClientId())
                .append("employeeId", getEmployeeId())
                .append("period", getPeriod())
                .append("extraData", getExtraData())
                .append("createdAt", getCreatedAt())
                .append("modifiedAt", getModifiedAt())
                .toString();
    }
}
