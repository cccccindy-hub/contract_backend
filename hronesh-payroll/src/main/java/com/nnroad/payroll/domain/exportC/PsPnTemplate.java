package com.nnroad.payroll.domain.exportC;


import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * PN模板管理对象 ps_pn_template
 * 
 * @author Hrone
 * @date 2022-02-21
 */
public class PsPnTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 模板名 */
    private String templateName;

    /** 客户编号 */
    private String clientCode;

    /** 客户名称 */
    private String clientName;

    /** 状态 */
    private String status;

    /** 描述 */
    private String description;

    /** 文件名 */
    private String fileName;

    /** 文件地址 */
    private String fileAddress;

    /** 文件类型 */
    private String fileType;

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

    public void setTemplateName(String templateName) 
    {
        this.templateName = templateName;
    }
    public String getTemplateName() 
    {
        return templateName;
    }

    public String getClientCode() {
        return clientCode;
    }
    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getClientName() {
        return clientName;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getStatus() 
    {
        return status;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }
    public String getDescription() 
    {
        return description;
    }

    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }
    public String getFileName() 
    {
        return fileName;
    }

    public void setFileAddress(String fileAddress) 
    {
        this.fileAddress = fileAddress;
    }
    public String getFileAddress() 
    {
        return fileAddress;
    }

    public void setFileType(String fileType) 
    {
        this.fileType = fileType;
    }
    public String getFileType() 
    {
        return fileType;
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
            .append("templateName", getTemplateName())
            .append("clientCode", getClientCode())
            .append("clientName", getClientName())
            .append("status", getStatus())
            .append("description", getDescription())
            .append("fileName", getFileName())
            .append("fileAddress", getFileAddress())
            .append("fileType", getFileType())
            .append("groupIds", getGroupIds())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
