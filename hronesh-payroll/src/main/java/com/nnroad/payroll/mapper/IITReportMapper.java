package com.nnroad.payroll.mapper;


import com.nnroad.payroll.domain.exportC.IITReport;
import com.nnroad.payroll.domain.exportC.StefaniniIITReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * iit_reportMapper接口
 * 
 * @author HROne
 * @date 2021-01-15
 */

@Mapper
public interface IITReportMapper
{
    /**
     * 查询iit_report列表
     *
     * @param map 参数合集
     * @return iit_report集合
     */
    public List<StefaniniIITReport> selectStefaniniIitReportList(@Param("params") Map<String, Object> map);

    /**
     * 查询iit_report列表
     *
     * @param iitReport iit_report
     * @return iit_report集合
     */
    public List<IITReport> selectIitReportList(IITReport iitReport);




}
