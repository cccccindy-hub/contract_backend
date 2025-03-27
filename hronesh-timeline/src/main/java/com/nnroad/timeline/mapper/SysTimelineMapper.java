package com.nnroad.timeline.mapper;

import java.util.List;
import java.util.Map;

import com.nnroad.timeline.domain.SysTimeline;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author Haoming
 * @date 2024-10-23
 */
public interface SysTimelineMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public SysTimeline selectSysTimelineById(Long id);

    public List<SysTimeline> selectSysTimelineByIds(@Param("list") List<Long> ids);
    /**
     * 查询【请填写功能名称】
     *
     * @param sysTimeline 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public SysTimeline selectSysTimelineByUuid(SysTimeline sysTimeline);


    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysTimeline 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysTimeline> selectSysTimelineList(SysTimeline sysTimeline);

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysTimeline 【请填写功能名称】
     * @return 结果
     */
    public int insertSysTimeline(SysTimeline sysTimeline);

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysTimeline 【请填写功能名称】
     * @return 结果
     */
    public int updateSysTimeline(SysTimeline sysTimeline);

    /**
     * 修改【请填写功能名称】
     *
     * @param sysTimeline 【请填写功能名称】
     * @return 结果
     */
    public int updateSysTimelineByColumn(SysTimeline sysTimeline);

    public int batchUpdateSysTimelineByColumn(@Param("list") List<SysTimeline> sysTimelines);
    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteSysTimelineById(Long id);

    /**
     * 删除【请填写功能名称】
     *
     * @param uuid 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteSysTimelineByUuid(String uuid);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysTimelineByIds(Long[] ids);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param sysTimeline 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysTimeline> selectSysExternalVendorTimelineList(SysTimeline sysTimeline);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param companyCode 客户ID
     * @return 【请填写功能名称】集合
     */
    public String getServiceTypeBByClientCompanyCode(String companyCode);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param clientCode 客户ID
     * @return 【请填写功能名称】集合
     */
    public List<String> getEmployeeEmploymentContractsByClientCompanyCode(String clientCode);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param sysTimeline 客户ID
     * @return 【请填写功能名称】集合
     */
    public List<String> getExistingVendorsFromTimeline(SysTimeline sysTimeline);

    /**
     * 查询【请填写功能名称】列表
     *
     * @return return unique clients from timeline
     */
    public List<Map<String, Object>> getTimelineClientList();
}
