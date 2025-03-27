package com.nnroad.calendar.domain;

import com.nnroad.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 calendar_global_config
 * 
 * @author ruoyi
 * @date 2024-10-23
 */
public class SysCalendar extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    private int year;

    private int month;

    private int day;

    private int dayOfWeek;
    
    private String dayType;

    private String dayDesc;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDayType() {
        return dayType;
    }

    public void setDayType(String dayType) {
        this.dayType = dayType;
    }

    public String getDayDesc() {
        return dayDesc;
    }

    public void setDayDesc(String dayDesc) {
        this.dayDesc = dayDesc;
    }
}
