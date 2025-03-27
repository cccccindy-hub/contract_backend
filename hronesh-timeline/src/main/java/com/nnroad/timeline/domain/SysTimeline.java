package com.nnroad.timeline.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】对象 sys_timeline
 * 
 * @author ruoyi
 * @date 2024-10-23
 */
public class SysTimeline extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    private int year;

    private int month;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long clientId;
    
    private String clientName;

    private String clientCompanyCode;

    private List<String> clientCompanyCodeArray;


    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String crm;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String dc;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String vrm;

    private String vendorName;

    private String vendorCompanyCode;


	/** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String service;

    private String sortBy;
    
    private String sortOrder;

    private String uuid;

    private String OrganizationName;

    private List<String> timelineType;




    /**  */
    @Excel(name = "")
    private Map<String, Object> extraData;

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
    
    public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
    public void setCrm(String crm) 
    {
        this.crm = crm;
    }

    public String getCrm() 
    {
        return crm;
    }
    public void setDc(String dc) 
    {
        this.dc = dc;
    }

    public String getDc() 
    {
        return dc;
    }
    public void setVrm(String vrm) 
    {
        this.vrm = vrm;
    }

    public String getVrm() 
    {
        return vrm;
    }

    public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
    public void setService(String service) 
    {
        this.service = service;
    }

    public String getService() 
    {
        return service;
    }

    public void setExtraData(Map<String, Object> extraData)
    {
        this.extraData = extraData;
    }

    public Map<String, Object> getExtraData()
    {
        return extraData;
    }
    
    public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	
	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}


	
	

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("clientId", getClientId())
            .append("crm", getCrm())
            .append("dc", getDc())
            .append("vrm", getVrm())
            .append("service", getService())
            .append("extraData", getExtraData())
            .toString();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getClientCompanyCode() {
        return clientCompanyCode;
    }

    public void setClientCompanyCode(String clientCompanyCode) {
        this.clientCompanyCode = clientCompanyCode;
    }

    public String getVendorCompanyCode() {
        return vendorCompanyCode;
    }

    public void setVendorCompanyCode(String vendorCompanyCode) {
        this.vendorCompanyCode = vendorCompanyCode;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public List<String> getTimelineType() {
        return timelineType;
    }

    public void setTimelineType(List<String> timelineType) {
        this.timelineType = timelineType;
    }

    public List<String> getClientCompanyCodeArray() {
        return clientCompanyCodeArray;
    }

    public void setClientCompanyCodeArray(List<String> clientCompanyCodeArray) {
        this.clientCompanyCodeArray = clientCompanyCodeArray;
    }
}
