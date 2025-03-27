package com.nnroad.employee.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;

import java.util.Map;

/**
 * employee_info
 *
 * @author nnroad
 * @date 2024-11-13
 */
public class EmployeeInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    private String employeeCode;
    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long employeeId;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Map<String, Object> extraData;

    private String localName;

    private String tableName;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setEmployeeId(Long employeeId)
    {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId()
    {
        return employeeId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getLocalName() {
        return localName;
    }

    public Map<String, Object> getExtraData() {
        return extraData;
    }

    public void setExtraData(Map<String, Object> extraData) {
        this.extraData = extraData;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("employeeId", getEmployeeId())
                .append("extraData", getExtraData())
                .toString();
    }
}
