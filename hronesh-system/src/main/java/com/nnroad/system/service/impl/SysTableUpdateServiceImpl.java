package com.nnroad.system.service.impl;

import java.util.List;
import com.nnroad.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnroad.system.mapper.SysTableUpdateMapper;
import com.nnroad.system.domain.SysTableUpdate;
import com.nnroad.system.service.ISysTableUpdateService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-13
 */
@Service
public class SysTableUpdateServiceImpl implements ISysTableUpdateService 
{
    @Autowired
    private SysTableUpdateMapper sysTableUpdateMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public SysTableUpdate selectSysTableUpdateById(Long id)
    {
        return sysTableUpdateMapper.selectSysTableUpdateById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysTableUpdate 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysTableUpdate> selectSysTableUpdateList(SysTableUpdate sysTableUpdate)
    {
        return sysTableUpdateMapper.selectSysTableUpdateList(sysTableUpdate);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysTableUpdate 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertSysTableUpdate(SysTableUpdate sysTableUpdate)
    {
        sysTableUpdate.setCreateTime(DateUtils.getNowDate());
        return sysTableUpdateMapper.insertSysTableUpdate(sysTableUpdate);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysTableUpdate 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSysTableUpdate(SysTableUpdate sysTableUpdate)
    {
        sysTableUpdate.setUpdateTime(DateUtils.getNowDate());
        return sysTableUpdateMapper.updateSysTableUpdate(sysTableUpdate);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysTableUpdateByIds(Long[] ids)
    {
        return sysTableUpdateMapper.deleteSysTableUpdateByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysTableUpdateById(Long id)
    {
        return sysTableUpdateMapper.deleteSysTableUpdateById(id);
    }
}
