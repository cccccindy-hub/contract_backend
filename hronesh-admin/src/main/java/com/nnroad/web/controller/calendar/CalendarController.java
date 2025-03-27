package com.nnroad.web.controller.calendar;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.nnroad.calendar.domain.SysCalendar;
import com.nnroad.calendar.service.ISysCalendarService;
import com.nnroad.client.domain.SysClient;
import com.nnroad.client.service.ISysClientService;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.timeline.domain.SysTimeline;
import com.nnroad.timeline.service.ISysTimelineService;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/system/calendar")
public class CalendarController extends BaseController
{
    @Autowired
    private ISysCalendarService sysCalendarService;

    @Autowired
    private ISysClientService sysClientService;
    
    @Autowired
    private ISysTimelineService sysTimelineService;
    
    /**
     * 查询【请填写功能名称】列表
     */
    @GetMapping("/list4Calendar")
    public TableDataInfo list(SysCalendar sysCalendar)
    {
        startPage();
        List<SysCalendar> list = sysCalendarService.selectSysCalendarList(sysCalendar);

        return getDataTable(list);
    }
    
    @GetMapping("/list4Client")
    public TableDataInfo list(SysClient sysClient) {
        startPage();
        List<SysClient> list = sysClientService.selectSysClientList(sysClient);
        return getDataTable(list);
    }
    
    @GetMapping("/list4Timeline")
    public TableDataInfo list(SysTimeline sysTimeline)
    {
        startPage();
        List<SysTimeline> list = sysTimelineService.selectSysTimelineList(sysTimeline);

        return getDataTable(list);
    }
    
}
