package com.nnroad.extraAttribute.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;

/**
 * @author nick
 * @date 2024-10-16
 */
public class SysExtraAttribute {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String type;

    private String subType;

    private Integer length;

    private Long parentId;

    private Boolean searchable;

    private Boolean hidden;

    private Boolean required;

    private Integer sortOrder;

    private List<String> options;

    private String stages;

    private Boolean eeOnboard;

    private String dictType;

    private Date createdAt;

    private Date updatedAt;

    private String tableName;

    private String parentName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    private List<SysExtraAttribute> children;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public List<SysExtraAttribute> getChildren() {
        return children;
    }

    public void setChildren(List<SysExtraAttribute> children) {
        this.children = children;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getStages() {
        return stages;
    }

    public void setStages(String stages) {
        this.stages = stages;
    }

    public void setEeOnboard(Boolean eeOnboard)
    {
        this.eeOnboard = eeOnboard;
    }

    public Boolean getEeOnboard()
    {
        return eeOnboard;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public  Boolean getSearchable()
    {
        return searchable;
    }

    public void setSearchable(Boolean searchable) {
        this.searchable = searchable;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean getRequired() {
        return required;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("type", getType())
                .append("length", getLength())
                .append("parentId", getParentId())
                .append("tableName", getTableName())
                .append("hidden", getHidden())
                .append("required", getRequired())
                .append("createdAt", getCreatedAt())
                .append("updatedAt", getUpdatedAt())
                .append("options", getOptions())
                .append("stages", getStages())
                .append("sortOrder", getSortOrder())
                .append("searchable", getSearchable())
                .append("dictType", getDictType())
                .append("eeOnboard", getEeOnboard())
                .append("subType", getSubType())
                .toString();
    }

}
