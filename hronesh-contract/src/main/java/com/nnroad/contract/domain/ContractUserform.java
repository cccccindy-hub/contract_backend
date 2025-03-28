// package com.ruoyi.system.domain; ？
package com.nnroad.contract.domain;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.nnroad.common.annotation.Excel;
import com.nnroad.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 contract_userform
 * 
 * @author ruoyi
 * @date 2025-03-25
 */
public class ContractUserform extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Unique identifier for the user (UUID format) */
    private String userId;

    /** Name of the user */
    @Excel(name = "Name of the user")
    private String userName;

    /** EORS index string */
    @Excel(name = "EORS index string")
    private String eorsStr;

    /** EORS index number */
    @Excel(name = "EORS index number")
    private Long eorsNum;

    /** RS index string */
    @Excel(name = "RS index string")
    private String rsStr;

    /** RS index number */
    @Excel(name = "RS index number")
    private Long rsNum;

    /** CIS index string */
    @Excel(name = "CIS index string")
    private String cisStr;

    /** CIS index number */
    @Excel(name = "CIS index number")
    private Long cisNum;

    /** TRS index string */
    @Excel(name = "TRS index string")
    private String trsStr;

    /** TRS index number */
    @Excel(name = "TRS index number")
    private Long trsNum;

    /** HRS index string */
    @Excel(name = "HRS index string")
    private String hrsStr;

    /** HRS index number */
    @Excel(name = "HRS index number")
    private Long hrsNum;

    /** IRS index string */
    @Excel(name = "IRS index string")
    private String irsStr;

    /** IRS index number */
    @Excel(name = "IRS index number")
    private Long irsNum;

    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setEorsStr(String eorsStr) 
    {
        this.eorsStr = eorsStr;
    }

    public String getEorsStr() 
    {
        return eorsStr;
    }
    public void setEorsNum(Long eorsNum) 
    {
        this.eorsNum = eorsNum;
    }

    public Long getEorsNum() 
    {
        return eorsNum;
    }
    public void setRsStr(String rsStr) 
    {
        this.rsStr = rsStr;
    }

    public String getRsStr() 
    {
        return rsStr;
    }
    public void setRsNum(Long rsNum) 
    {
        this.rsNum = rsNum;
    }

    public Long getRsNum() 
    {
        return rsNum;
    }
    public void setCisStr(String cisStr) 
    {
        this.cisStr = cisStr;
    }

    public String getCisStr() 
    {
        return cisStr;
    }
    public void setCisNum(Long cisNum) 
    {
        this.cisNum = cisNum;
    }

    public Long getCisNum() 
    {
        return cisNum;
    }
    public void setTrsStr(String trsStr) 
    {
        this.trsStr = trsStr;
    }

    public String getTrsStr() 
    {
        return trsStr;
    }
    public void setTrsNum(Long trsNum) 
    {
        this.trsNum = trsNum;
    }

    public Long getTrsNum() 
    {
        return trsNum;
    }
    public void setHrsStr(String hrsStr) 
    {
        this.hrsStr = hrsStr;
    }

    public String getHrsStr() 
    {
        return hrsStr;
    }
    public void setHrsNum(Long hrsNum) 
    {
        this.hrsNum = hrsNum;
    }

    public Long getHrsNum() 
    {
        return hrsNum;
    }
    public void setIrsStr(String irsStr) 
    {
        this.irsStr = irsStr;
    }

    public String getIrsStr() 
    {
        return irsStr;
    }
    public void setIrsNum(Long irsNum) 
    {
        this.irsNum = irsNum;
    }

    public Long getIrsNum() 
    {
        return irsNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("eorsStr", getEorsStr())
            .append("eorsNum", getEorsNum())
            .append("rsStr", getRsStr())
            .append("rsNum", getRsNum())
            .append("cisStr", getCisStr())
            .append("cisNum", getCisNum())
            .append("trsStr", getTrsStr())
            .append("trsNum", getTrsNum())
            .append("hrsStr", getHrsStr())
            .append("hrsNum", getHrsNum())
            .append("irsStr", getIrsStr())
            .append("irsNum", getIrsNum())
            .toString();
    }
}
