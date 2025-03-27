package com.nnroad.system.mapper;

import java.util.List;
import com.nnroad.system.domain.SysTableUpdate;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-13
 */
public interface SysTableUpdateMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public SysTableUpdate selectSysTableUpdateById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysTableUpdate 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysTableUpdate> selectSysTableUpdateList(SysTableUpdate sysTableUpdate);

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysTableUpdate 【请填写功能名称】
     * @return 结果
     */
    public int insertSysTableUpdate(SysTableUpdate sysTableUpdate);


    /**
     * 修改【请填写功能名称】
     * 
     * @param sysTableUpdate 【请填写功能名称】
     * @return 结果
     */
    public int updateSysTableUpdate(SysTableUpdate sysTableUpdate);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteSysTableUpdateById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysTableUpdateByIds(Long[] ids);
}
