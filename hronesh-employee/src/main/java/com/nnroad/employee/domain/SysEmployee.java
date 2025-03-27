package com.nnroad.employee.domain;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.nnroad.common.annotation.Excel;

/**
 * Employee object for sys_employee
 * 
 * Represents an employee associated with a client and vendor.
 * 
 * @author nick
 * @date 2024-10-11
 */
public class SysEmployee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Employee ID */
    private Long id;

    private String companyCode;

    private String employeeCode;

    private String employeeUuid;
    /** Client ID (foreign key linking to client table) */
    @Excel(name = "Client ID")
    private Long clientId;

    private String clientName;

    @Excel(name = "")
    private String localName;

    @Excel(dictType = "employee_status")
    private String status;

    private String vendorName;
    /**  */
    @Excel(name = "")
    private Long vendorId;

    private Object extraData;

    private Object contractData;

    private Object payrollCrmData;

    private Object payrollDcData;

    private Object offBoardInfoData;

    private Map<String, String> basicFiles;

    /** Employee's name */
    @Excel(name = "Employee Name")
    private String employeeName;

    /**  */
    @Excel(name = "", dictType = "id_type")
    private String idType;

    /**  */
    @Excel(name = "")
    private String idNumber;

    @Excel(name = "", dictType = "country")
    private String nationality;

    /**  */
    @Excel(name = "", dictType = "employee_sex")
    private String sex;

    /**  */
    @Excel(name = "", dictType = "organization")
    private String org;

    /** Employee's email address */
    @Excel(name = "Employee Email")
    private String employeeEmail;

    /** Employee's phone number */
    @Excel(name = "Employee Phone")
    private String employeePhone;

    /** Employee's work location */
    @Excel(name = "Location")
    private String location;

    /** Contract start date */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "Contract Start Date", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contractStartDate;

    /** Contract end date */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "Contract End Date", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contractEndDate;

    /** Record creation timestamp */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "Created At", width = 30, dateFormat = "yyyy-MM-dd")

    private Date createdAt;
    
    private String remark;

    private Integer businessTypeId;

    private String businessType;

	/** Record modification timestamp */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "Modified At", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifiedAt;

    public Map<String, String> getBasicFiles() {
        return basicFiles;
    }

    public void setBasicFiles(Map<String, String> basicFiles) {
        this.basicFiles = basicFiles;
    }

    public String getBasicFilesJson() {
        return basicFiles != null ? JSONObject.toJSONString(basicFiles) : null;
    }

    public void setBasicFilesJson(String basicFilesJson) {
        this.basicFiles = JSONObject.parseObject(basicFilesJson, Map.class);
    }

    public String getEmployeeUuid() {
        return employeeUuid;
    }

    public void setEmployeeUuid(String employeeUuid) {
        this.employeeUuid = employeeUuid;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }
    /**  */

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and setter methods
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

    public void setEmployeeName(String employeeName) 
    {
        this.employeeName = employeeName;
    }

    /**
	 * @return the extraData
	 */
	public Object getExtraData() {
		return extraData;
	}

	/**
	 * @param extraData the extraData to set
	 */
	public void setExtraData(Object extraData) {
		this.extraData = extraData;
	}

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Object getContractData() {
        return contractData;
    }

    public void setContractData(Object contractData) {
        this.contractData = contractData;
    }

    public Object getPayrollCrmData() {
        return payrollCrmData;
    }

    public void setPayrollCrmData(Object payrollCrmData) {
        this.payrollCrmData = payrollCrmData;
    }

    public Object getPayrollDcData() {
        return payrollDcData;
    }

    public void setPayrollDcData(Object payrollDcData) {
        this.payrollDcData = payrollDcData;
    }

    public Object getOffBoardInfoData() {
        return offBoardInfoData;
    }

    public void setOffBoardInfoData(Object offBoardInfoData) {
        this.offBoardInfoData = offBoardInfoData;
    }

	public String getEmployeeName() 
    {
        return employeeName;
    }

    public void setEmployeeEmail(String employeeEmail) 
    {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeEmail() 
    {
        return employeeEmail;
    }

    public void setEmployeePhone(String employeePhone) 
    {
        this.employeePhone = employeePhone;
    }

    public String getEmployeePhone() 
    {
        return employeePhone;
    }

    public void setVendorId(Long vendorId) 
    {
        this.vendorId = vendorId;
    }

    public Long getVendorId() 
    {
        return vendorId;
    }

    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }

    public void setContractStartDate(Date contractStartDate) 
    {
        this.contractStartDate = contractStartDate;
    }

    public Date getContractStartDate() 
    {
        return contractStartDate;
    }

    public void setContractEndDate(Date contractEndDate) 
    {
        this.contractEndDate = contractEndDate;
    }

    public Date getContractEndDate() 
    {
        return contractEndDate;
    }

    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Integer getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Integer businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
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
                .append("employeeCode", getEmployeeCode())
                .append("companyCode", getCompanyCode())
                .append("clientId", getClientId())
                .append("vendorId", getVendorId())
                .append("employeeName", getEmployeeName())
                .append("localName", getLocalName())
                .append("employeeEmail", getEmployeeEmail())
                .append("employeePhone", getEmployeePhone())
                .append("idType", getIdType())
                .append("idNumber", getIdNumber())
                .append("location", getLocation())
                .append("nationality", getNationality())
                .append("sex", getSex())
                .append("contractStartDate", getContractStartDate())
                .append("contractEndDate", getContractEndDate())
                .append("remark", getRemark())
                .append("status", getStatus())
                .append("org", getOrg())
                .append("createdAt", getCreatedAt())
                .append("modifiedAt", getModifiedAt())
                .append("extraData", getExtraData())
                .append("businessTypeId", getBusinessTypeId())
                .toString();
    }

}
