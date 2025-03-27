package com.nnroad.calendar.service.impl;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.nnroad.calendar.domain.SysCalendar;
import com.nnroad.calendar.mapper.SysCalendarMapper;
import com.nnroad.calendar.service.ISysCalendarService;
import com.nnroad.client.domain.ClientInfo;
import com.nnroad.client.domain.SysClient;
import com.nnroad.client.mapper.ClientInfoMapper;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.enums.DataSourceType;
import com.nnroad.common.utils.MailTemplate;
import com.nnroad.common.utils.MessageUtils;
import com.nnroad.employee.constants.enums.EEStatus;
import com.nnroad.employee.domain.EmployeeInfo;
import com.nnroad.employee.domain.SysEmployee;
import com.nnroad.employee.mapper.EmployeeInfoMapper;
import com.nnroad.employee.mapper.SysEmployeeMapper;
import com.nnroad.extraAttribute.domain.SysExtraAttribute;
import com.nnroad.framework.datasource.DynamicDataSourceContextHolder;
import com.nnroad.framework.web.domain.server.Sys;
import com.nnroad.system.constants.MailEnum;
import com.nnroad.system.constants.SysConstants;
import com.nnroad.system.domain.SysEmailSend;
import com.nnroad.system.service.ISysMailService;
import com.nnroad.utils.ExtraAttributeUtils;
import com.nnroad.utils.ExcelImporter;
import com.nnroad.vendor.domain.SysVendor;
import com.nnroad.vendor.mapper.SysVendorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nnroad.extraAttribute.mapper.SysExtraAttributeMapper;
import com.nnroad.client.mapper.SysClientMapper;

import com.nnroad.timeline.mapper.SysTimelineMapper;
import com.nnroad.timeline.domain.SysTimeline;
import com.nnroad.timeline.service.ISysTimelineService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nnroad.common.utils.DateUtils;
import org.springframework.web.multipart.MultipartFile;

import static java.lang.Integer.parseInt;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author Haoming
 * @date 2024-10-23
 */
@Service
public class SysCalendarServiceImpl implements ISysCalendarService 
{
    @Autowired
    private SysCalendarMapper sysCalendarMapper;

    // Create a mapping of db names to DataSourceType values
    private static final Map<String, DataSourceType> DB_TO_DATASOURCE_MAP = new HashMap<>();
    static {
        DB_TO_DATASOURCE_MAP.put("HRSH", DataSourceType.HROneSH);
        DB_TO_DATASOURCE_MAP.put("HRBJ", DataSourceType.HROneBJ);
        DB_TO_DATASOURCE_MAP.put("HRHK", DataSourceType.HROneHK);
        DB_TO_DATASOURCE_MAP.put("FDI", DataSourceType.FDI);
        DB_TO_DATASOURCE_MAP.put("Top FDI", DataSourceType.TOPFDIHK);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysTimeline 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysCalendar> selectSysCalendarList(SysCalendar sysCalendar)
    {
        return sysCalendarMapper.selectSysCalendarList(sysCalendar);
    }

}
