package com.nnroad.lead.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * ClientLeads对象 cmgt_leads
 */
public class ClientLeads extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    private List<String> CompanyNameList;
    private List<String> bdConsultantList;
    private List<String> LocationList;
    private List<String> serviceLocationList;
    private List<String> qualifiedLeadsList;
    private List<String> leadStatusList;
    private List<String> memberNameCodeList;
    /** Lead Code*/
    @Excel(name = "html.client.lead.leadCode")
    private  String leadCode;

    /** Company Name */
    @Excel(name = "html.client.leads.companyName")
    private String companyName;

    /** Location*/
    @Excel(name = "html.client.leads.location")
    private String location;

    /** Service Region */
    @Excel(name = "html.client.leads.serviceLocation")
    private String serviceLocation;

    @Excel(name = "html.client.leads.cityLocation")
    private String cityLocation;

    /** Main Service/Other Service */
    @Excel(name = "html.client.leads.service")
    private String service;

    /** BD Owner (direct BD leads) */
    @Excel(name = "html.client.leads.bdOwner")
    private String bdOwner;

    /** BD Consultant */
    @Excel(name = "html.client.leads.bdConsultant")
    private String bdConsultant;

    private String bdId;

    /** Qualified Leads - Y/N */
    @Excel(name = "html.client.leads.qualifiedLeads")
    private String qualifiedLeads;

    /** 行业 */
    @Excel(name = "html.client.industry")
    private String industry;

    /** Source*/
    @Excel(name = "html.client.leads.channel")
    private String channel;

    @Excel(name = "html.client.leads.contactTitle")
    private String contactTitle;
    /** Contact Name*/
    @Excel(name = "html.client.leads.contactName")
    private String contactName;

    /** Email*/
    @Excel(name = "html.client.leads.contactEmail")
    private String contactEmail;

    /** Phone Number */
    @Excel(name = "html.client.leads.contactNumber")
    private String contactNumber;

    /** Lead Status – Ongoing, Converted, Archive, On-hold (SC Review)??? */
    @Excel(name = "html.client.leads.leadStatus")
    private String leadStatus;

    /** Inquiry Receive Date*/
    @Excel(name = "html.client.leads.inquiryReceivedDate",dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inquiryReceivedDate;

    /** Proposal Sent Date*/
    @Excel(name = "html.client.leads.proposalSentDate",dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date proposalSentDate;

    /** Сontract Sent Date */
    @Excel(name = "html.client.leads.contractSentDate",dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date contractSentDate;

    /** Сontract Signed Date */
    @Excel(name = "html.client.leads.contractSignedDate",dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date contractSignedDate;

    @Excel(name = "html.client.leads.convertedOrArchiveDate",dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date convertedDate;

    @Excel(name = "html.client.leads.nextFollowupTime",dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date nextFollowupTime;

    /** Remark */
    @Excel(name = "html.client.leads.remark")
    private String remark;

    @Excel(name = "html.client.leads.archiveDate",dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date archivedDate;

    @Excel(name = "html.client.leads.archiveCategory")
    private String archiveCategory;

    @Excel(name = "html.client.leads.archiveReason")
    private String archiveReason;
    @Excel(name = "membermgt.memberName")
    private String memberCode;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date holdDate;

    private String readIds;

    private String operationIds;

    private String deleteFlag;

    //For TOP Report Countries
    private int position;
    private int serviceLocationCount;

    private int locationCount;

    private String entryDateBegin;

    private String entryDateEnd;

    public String getEntryDateBegin() {
        return entryDateBegin;
    }

    public void setEntryDateBegin(String entryDateBegin) {
        this.entryDateBegin = entryDateBegin;
    }

    public String getEntryDateEnd() {
        return entryDateEnd;
    }

    public void setEntryDateEnd(String entryDateEnd) {
        this.entryDateEnd = entryDateEnd;
    }

    public int getServiceLocationCount() {
        return serviceLocationCount;
    }

    public void setServiceLocationCount(int serviceLocationCount) {
        this.serviceLocationCount = serviceLocationCount;
    }

    public int getLocationCount() {
        return locationCount;
    }

    public void setLocationCount(int locationCount) {
        this.locationCount = locationCount;
    }

    public ClientLeads() {

    }

    public Date getArchivedDate() {
        return archivedDate;
    }

    public void setArchivedDate(Date archivedDate) {
        this.archivedDate = archivedDate;
    }

    public ClientLeads(ClientLeads another) {
        this.id = another.id;
        this.leadCode = another.leadCode;
        this.companyName = another.companyName;
        this.location = another.location;
        this.serviceLocation = another.serviceLocation;
        this.cityLocation = another.cityLocation;
        this.service = another.service;
        this.bdOwner = another.bdOwner;
        this.bdConsultant = another.bdConsultant;
        this.bdId = another.bdId;
        this.qualifiedLeads = another.qualifiedLeads;
        this.industry = another.industry;
        this.channel = another.channel;
        this.contactName = another.contactName;
        this.contactEmail = another.contactEmail;
        this.contactNumber = another.contactNumber;
        this.leadStatus = another.leadStatus;
        this.inquiryReceivedDate = another.inquiryReceivedDate;
        this.proposalSentDate = another.proposalSentDate;
        this.contractSentDate = another.contractSentDate;
        this.contractSignedDate = another.contractSignedDate;
        this.convertedDate = another.convertedDate;
        this.nextFollowupTime = another.nextFollowupTime;
        this.remark = another.remark;
        this.archiveCategory = another.archiveCategory;
        this.archiveReason = another.archiveReason;
        this.memberCode = another.memberCode;
        this.readIds = another.readIds;
        this.operationIds = another.operationIds;
        this.deleteFlag = another.deleteFlag;
        this.holdDate = another.holdDate;
        //ParentClass
        this.setSearchValue(another.getSearchValue());
        this.setCreateBy(another.getCreateBy());
        this.setCreateTime(another.getCreateTime());
        this.setUpdateBy(another.getUpdateBy());
        this.setUpdateTime(another.getUpdateTime());
        this.setRemark(another.getRemark());


    }

    public ClientLeads(long serialVersionUID, Long id, String leadCode, String companyName, String location, String serviceLocation, String cityLocation, String service, String bdOwner, String bdConsultant, String bdId, String qualifiedLeads, String industry, String channel, String contactTitle, String contactName, String contactEmail, String contactNumber, String leadStatus, Date inquiryReceivedDate, Date proposalSentDate, Date contractSentDate, Date contractSignedDate, Date convertedDate, Date nextFollowupTime, String remark, Date archiveDate, String archiveCategory, String archiveReason, String memberCode, String readIds, String operationIds, String deleteFlag, int position,Date holdDate) {

        this.id = id;
        this.leadCode = leadCode;
        this.companyName = companyName;
        this.location = location;
        this.serviceLocation = serviceLocation;
        this.cityLocation = cityLocation;
        this.service = service;
        this.bdOwner = bdOwner;
        this.bdConsultant = bdConsultant;
        this.bdId = bdId;
        this.qualifiedLeads = qualifiedLeads;
        this.industry = industry;
        this.channel = channel;
        this.contactTitle = contactTitle;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactNumber = contactNumber;
        this.leadStatus = leadStatus;
        this.inquiryReceivedDate = inquiryReceivedDate;
        this.proposalSentDate = proposalSentDate;
        this.contractSentDate = contractSentDate;
        this.contractSignedDate = contractSignedDate;
        this.convertedDate = convertedDate;
        this.nextFollowupTime = nextFollowupTime;
        this.remark = remark;
        this.archivedDate = archivedDate;
        this.archiveCategory = archiveCategory;
        this.archiveReason = archiveReason;
        this.memberCode = memberCode;
        this.readIds = readIds;
        this.operationIds = operationIds;
        this.deleteFlag = deleteFlag;
        this.position = position;
        this.holdDate = holdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeadCode() { return leadCode; }

    public void setLeadCode(String leadCode) { this.leadCode = leadCode;  }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public String getCityLocation() { return cityLocation; }

    public void setCityLocation(String cityLocation) { this.cityLocation = cityLocation; }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getBdOwner() {
        return bdOwner;
    }

    public void setBdOwner(String bdOwner) {
        this.bdOwner = bdOwner;
    }

    public String getBdConsultant() {
        return bdConsultant;
    }

    public void setBdConsultant(String bdConsultant) {
        this.bdConsultant = bdConsultant;
    }

    public String getQualifiedLeads() {
        return qualifiedLeads;
    }

    public void setQualifiedLeads(String qualifiedLeads) {
        this.qualifiedLeads = qualifiedLeads;
    }

    public String getIndustry() { return industry; }

    public void setIndustry(String industry) { this.industry = industry; }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getLeadStatus() {
        return leadStatus;
    }

    public void setLeadStatus(String leadStatus) {
        this.leadStatus = leadStatus;
    }

    public Date getInquiryReceivedDate() {
        return inquiryReceivedDate;
    }

    public void setInquiryReceivedDate(Date inquiryReceivedDate) {
        this.inquiryReceivedDate = inquiryReceivedDate;
    }

    public Date getProposalSentDate() {
        return proposalSentDate;
    }

    public void setProposalSentDate(Date proposalSentDate) {
        this.proposalSentDate = proposalSentDate;
    }

    public Date getContractSentDate() {
        return contractSentDate;
    }

    public void setContractSentDate(Date contractSentDate) {
        this.contractSentDate = contractSentDate;
    }

    public Date getContractSignedDate() {
        return contractSignedDate;
    }

    public void setContractSignedDate(Date contractSignedDate) {
        this.contractSignedDate = contractSignedDate;
    }

    public String getBdId() {
        return bdId;
    }

    public void setBdId(String bdId) {
        this.bdId = bdId;
    }

    public List<String> getCompanyNameList() {
        return CompanyNameList;
    }

    public void setCompanyNameList(List<String> CompanyNameList) {
        this.CompanyNameList = CompanyNameList;
    }


    public List<String> getBdConsultantList() {
        return bdConsultantList;
    }

    public void setBdConsultantList(List<String> bdConsultantList) {
        this.bdConsultantList = bdConsultantList;
    }

    public List<String> getLocationList() {
        return LocationList;
    }

    public void setLocationList(List<String> LocationList) {
        this.LocationList = LocationList;
    }


    public List<String> getServiceLocationList() {
        return serviceLocationList;
    }

    public void setServiceLocationList(List<String> serviceLocationList) {
        this.serviceLocationList = serviceLocationList;
    }

    public List<String> getqualifiedLeadsList() {
        return qualifiedLeadsList;
    }

    public void setqualifiedLeadsList(List<String> qualifiedLeadsList) {
        this.qualifiedLeadsList = qualifiedLeadsList;
    }


    public List<String> getleadStatusList() {
        return leadStatusList;
    }

    public void setleadStatusList(List<String> leadStatusList) {
        this.leadStatusList = leadStatusList;
    }

    public List<String> getmemberNameCodeList() {
        return memberNameCodeList;
    }

    public void setmemberNameCodeList(List<String> memberNameCodeList) {
        this.memberNameCodeList = memberNameCodeList;
    }
    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getConvertedDate() {
        return convertedDate;
    }

    public void setConvertedDate(Date convertedDate) {
        this.convertedDate = convertedDate;
    }

    public String getArchiveReason() {
        return archiveReason;
    }

    public void setArchiveReason(String archiveReason) {
        this.archiveReason = archiveReason;
    }

    public String getArchiveCategory() {
        return archiveCategory;
    }

    public void setArchiveCategory(String archiveCategory) {
        this.archiveCategory = archiveCategory;
    }

    public String getReadIds() {
        return readIds;
    }

    public void setReadIds(String readIds) {
        this.readIds = readIds;
    }

    public String getOperationIds() {
        return operationIds;
    }

    public void setOperationIds(String operationIds) {
        this.operationIds = operationIds;
    }

    public Date getNextFollowupTime() {
        return nextFollowupTime;
    }

    public void setNextFollowupTime(Date nextFollowupTime) {
        this.nextFollowupTime = nextFollowupTime;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    //For Top Countries Report
    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("leadCode", getLeadCode())
            .append("inquiryReceivedDate", getInquiryReceivedDate())
            .append("proposalSentDate", getProposalSentDate())
            .append("contractSentDate", getContractSentDate())
            .append("contractSignedDate", getContractSignedDate())
            .append("serviceLocation", getServiceLocation())
            .append("cityLocation", getCityLocation())
            .append("service", getService())
            .append("bdOwner", getBdOwner())
            .append("bdConsultant", getBdConsultant())
            .append("bdId", getBdId())
            .append("qualifiedLeads", getQualifiedLeads())
            .append("leadStatus", getLeadStatus())
            .append("contactTitle", getContactTitle())
            .append("contactName", getContactName())
            .append("contactEmail", getContactEmail())
            .append("contactNumber", getContactNumber())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("serviceLocation", getServiceLocation())
            .append("location", getLocation())
            .append("industry", getIndustry())
            .append("channel", getChannel())
            .append("remark", getRemark())
            .append("convertedDate", getConvertedDate())
            .append("archiveReason", getArchiveReason())
            .append("archiveCategory", getArchiveCategory())
            .append("operationIds", getOperationIds())
            .append("position",getPosition())
            .append("holdDate",getHoldDate())
            .toString();
    }


    public Date getHoldDate() {
        return holdDate;
    }

    public void setHoldDate(Date holdDate) {
        this.holdDate = holdDate;
    }
}
