package com.nnroad.payroll.domain;


import com.nnroad.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * ps_op_log对象 ps_op_log
 * 
 * @author Hrone
 * @date 2021-01-17
 */
public class PsOpLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 操作ID */
    private Long opId;

    /** 角色名称 */
    private String roleName;

    /** 导入文件名 */
    private String imputFileName;

    /** 操作类型 */
    private String opType;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    private String groupIds;

    private String userId;

    public void setOpId(Long opId) 
    {
        this.opId = opId;
    }

    public Long getOpId() 
    {
        return opId;
    }
    public void setRoleName(String roleName) 
    {
        this.roleName = roleName;
    }

    public String getRoleName() 
    {
        return roleName;
    }
    public void setImputFileName(String imputFileName) 
    {
        this.imputFileName = imputFileName;
    }

    public String getImputFileName() 
    {
        return imputFileName;
    }
    public void setOpType(String opType) 
    {
        this.opType = opType;
    }

    public String getOpType() 
    {
        return opType;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("opId", getOpId())
            .append("roleName", getRoleName())
            .append("imputFileName", getImputFileName())
            .append("opType", getOpType())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
