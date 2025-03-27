package com.nnroad.calendar.mapper;

import java.util.List;

import com.nnroad.calendar.domain.SysCalendar;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author Haoming
 * @date 2024-10-23
 */
public interface SysCalendarMapper 
{
    /**
     * 查询【请填写功能名称】
     *
     * @param sysCalendar 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
//    public SysCalendar selectSingleSysCalendar(SysCalendar sysCalendar);


    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysCalendar 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysCalendar> selectSysCalendarList(SysCalendar sysCalendar);

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysCalendar 【请填写功能名称】
     * @return 结果
     */
//    public int insertSysCalendar(SysCalendar sysCalendar);

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysCalendar 【请填写功能名称】
     * @return 结果
     */
//    public int updateSysCalendar(SysCalendar sysCalendar);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
//    public int deleteSysCalendarById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
//    public int deleteSysCalendarByIds(Long[] ids);

}
