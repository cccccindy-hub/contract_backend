package com.nnroad.vendor.mapper;

import com.nnroad.vendor.domain.SysServiceFee;

import java.util.List;
import com.nnroad.vendor.domain.SysServiceFee;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author nnroad
 * @date 2024-10-24
 */
public interface SysServiceFeeMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public SysServiceFee selectSysServiceFeeById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysServiceFee 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysServiceFee> selectSysServiceFeeList(SysServiceFee sysServiceFee);

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysServiceFee 【请填写功能名称】
     * @return 结果
     */
    public int insertSysServiceFee(SysServiceFee sysServiceFee);

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysServiceFee 【请填写功能名称】
     * @return 结果
     */
    public int updateSysServiceFee(SysServiceFee sysServiceFee);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteSysServiceFeeById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysServiceFeeByIds(Long[] ids);
}
