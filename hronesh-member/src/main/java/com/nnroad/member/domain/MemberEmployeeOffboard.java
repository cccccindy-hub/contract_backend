package com.nnroad.member.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;

public class MemberEmployeeOffboard  extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 员工编号 */
    @Excel(name = "员工编号")
    private String eeCode;

    /** 离职日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "离职日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date offboardDate;

    /** 离职原因 */
    @Excel(name = "离职原因")
    private String offboardReason;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setEeCode(String eeCode)
    {
        this.eeCode = eeCode;
    }

    public String getEeCode()
    {
        return eeCode;
    }
    public void setOffboardDate(Date offboardDate)
    {
        this.offboardDate = offboardDate;
    }

    public Date getOffboardDate()
    {
        return offboardDate;
    }
    public void setOffboardReason(String offboardReason)
    {
        this.offboardReason = offboardReason;
    }

    public String getOffboardReason()
    {
        return offboardReason;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("eeCode", getEeCode())
                .append("offboardDate", getOffboardDate())
                .append("offboardReason", getOffboardReason())
                .toString();
    }
}
