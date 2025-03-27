package com.nnroad.payroll.domain.common;


import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * client对象 client_info
 *
 * @author Aaron
 * @date 2022-04-01
 */
public class Client extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Client ID */
    private Long clientId;

    /** 客户编号 */
    private String clientCode;

    /** 客户名称 */
    private String clientName;

    /** 总部地址 */
    @Excel(name = "html.client.hqLocation")
    private String location;

    /** 状态 */
    private Long status;

    /** 行业 */
    private String industry;

    /** 客户名称(英文) */
    private String clientNameEn;

    /** 客户名称(当地) */
    private String clientNameLocal;

    /** 主要/日常联系人 */
    private String contact;

    /** 联系人邮箱 */
    private String contactEmail;

    /** 读取权限 */
    private String readIds;

    /** 编辑权限 */
    private String operationIds;

    /** Delete flag (0 means existence 2 means deletion) */
    private String delFlag;

    public void setClientId(Long clientId)
    {
        this.clientId = clientId;
    }

    public Long getClientId()
    {
        return clientId;
    }
    public void setClientCode(String clientCode)
    {
        this.clientCode = clientCode;
    }

    public String getClientCode()
    {
        return clientCode;
    }
    public void setClientName(String clientName)
    {
        this.clientName = clientName;
    }

    public String getClientName()
    {
        return clientName;
    }
    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getLocation()
    {
        return location;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setIndustry(String industry)
    {
        this.industry = industry;
    }

    public String getIndustry()
    {
        return industry;
    }
    public void setClientNameEn(String clientNameEn)
    {
        this.clientNameEn = clientNameEn;
    }

    public String getClientNameEn()
    {
        return clientNameEn;
    }
    public void setClientNameLocal(String clientNameLocal)
    {
        this.clientNameLocal = clientNameLocal;
    }

    public String getClientNameLocal()
    {
        return clientNameLocal;
    }
    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public String getContact()
    {
        return contact;
    }
    public void setContactEmail(String contactEmail)
    {
        this.contactEmail = contactEmail;
    }

    public String getContactEmail()
    {
        return contactEmail;
    }
    public void setReadIds(String readIds)
    {
        this.readIds = readIds;
    }

    public String getReadIds()
    {
        return readIds;
    }
    public void setOperationIds(String operationIds)
    {
        this.operationIds = operationIds;
    }

    public String getOperationIds()
    {
        return operationIds;
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
                .append("clientId", getClientId())
                .append("clientCode", getClientCode())
                .append("clientName", getClientName())
                .append("location", getLocation())
                .append("status", getStatus())
                .append("industry", getIndustry())
                .append("clientNameEn", getClientNameEn())
                .append("clientNameLocal", getClientNameLocal())
                .append("contact", getContact())
                .append("contactEmail", getContactEmail())
                .append("readIds", getReadIds())
                .append("operationIds", getOperationIds())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
