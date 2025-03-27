package com.nnroad.timeline.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.system.domain.SysTableUpdate;
import com.nnroad.timeline.domain.SysTimeline;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author Haoming
 * @date 2024-10-23
 */
public interface ISysTimelineService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public SysTimeline selectSysTimelineById(Long id);

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
    public AjaxResult insertSysTimeline(SysTimeline sysTimeline);

    /**
     * 修改 external vendor【请填写功能名称】
     * 
     * @param sysTimeline 【请填写功能名称】
     * @return 结果
     */
    public int updateSysExternalVendorTimeline (SysTimeline sysTimeline);

    /**
     * 修改【请填写功能名称】
     *
     * @param sysTimeline 【请填写功能名称】
     * @return 结果
     */
    public AjaxResult updateSysTimeline(SysTimeline sysTimeline);

    /**
     * 修改【请填写功能名称】
     *
     * @param sysTimeline 【请填写功能名称】
     * @return 结果
     */
    public AjaxResult updateSysTimelineByColumn(SysTimeline sysTimeline);

    public AjaxResult updateMultipleSysTimelineByColumn(List<SysTimeline> sysTimelines);

    //public void sendUpdatedEntries(List<SysTimeline>);


    //public List<SysTimeline> getUpdatedEntries();

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteSysTimelineByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteSysTimelineById(Long id);


    /**
     * 查询【请填写功能名称】列表
     *
     * @param sysTimeline 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysTimeline> selectExternalVendorSysTimelineList(SysTimeline sysTimeline);

    public List<SysTableUpdate> selectSysTimelinePendingChanges();

    public AjaxResult acceptSysTimelinePendingChanges();

    public List<Map<String, Object>> getTimelineClientList();
}
