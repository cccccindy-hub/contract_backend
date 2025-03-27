package com.nnroad.payroll.domain.exportC;


import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * pn账单导出记录对象 ps_pn_export_log
 * 
 * @author Hrone
 * @date 2022-08-02
 */
@Setter
@Getter
public class PsPnExportLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 操作ID */
    private Long id;

    /** 期间月份 */
    @Excel(name = "期间月份")
    private String duration;

    /** 客户编号 */
    @Excel(name = "客户编号")
    private String erNo;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String erName;

    /** 导出文件名 */
    @Excel(name = "导出文件名")
    private String fileName;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String fileAddress;

    /** 报表检查状态 */
    @Excel(name = "报表检查状态")
    private Long fileStatus;

    /** 是否为供应商报表 */
    private Long fileSource;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 权限组 */
    @Excel(name = "权限组")
    private String groupIds;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("duration", getDuration())
            .append("erNo", getErNo())
            .append("erName", getErName())
            .append("fileName", getFileName())
            .append("fileStatus", getFileStatus())
            .append("fileAddress", getFileAddress())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("groupIds", getGroupIds())
            .toString();
    }
}
