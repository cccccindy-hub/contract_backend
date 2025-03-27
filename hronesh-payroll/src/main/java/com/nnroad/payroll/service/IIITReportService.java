package com.nnroad.payroll.service;




import com.nnroad.payroll.domain.exportC.IITReport;
import com.nnroad.payroll.domain.exportC.StefaniniIITReport;

import java.util.List;
import java.util.Map;

/**
 * iit_reportService接口
 * 
 * @author HROne
 * @date 2021-01-15
 */
public interface IIITReportService
{
    /**
     * 查询stefanini的iit_report列表
     *
     * @param map 参数合集
     * @return iit_report
     */
    public List<StefaniniIITReport> selectStefaniniIitReportList(Map<String, Object> map);

    /**
     * 查询iit_report列表
     *
     * @param iitReport iit_report
     * @return iit_report集合
     */
    public List<IITReport> selectIitReportList(IITReport iitReport);


}
