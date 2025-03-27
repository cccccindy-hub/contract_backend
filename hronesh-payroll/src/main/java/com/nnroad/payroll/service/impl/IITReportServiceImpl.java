package com.nnroad.payroll.service.impl;


import com.nnroad.common.utils.StringUtils;
import com.nnroad.payroll.domain.exportC.IITReport;
import com.nnroad.payroll.domain.exportC.StefaniniIITReport;
import com.nnroad.payroll.mapper.IITReportMapper;
import com.nnroad.payroll.service.IIITReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * iit_reportService业务层处理
 *
 * @author HROne
 * @date 2021-01-15
 */
@Service
public class IITReportServiceImpl implements IIITReportService {

    @Autowired
    private IITReportMapper iitReportMapper;


    /**
     * 查询stefanini的iit_report列表
     *
     * @param map 参数合集
     * @return iit_report
     */
    @Override
    public List<StefaniniIITReport> selectStefaniniIitReportList(Map<String, Object> map) {
        return iitReportMapper.selectStefaniniIitReportList(map);
    }

    /**
     * 查询iit_report列表
     *
     * @param iitReport iit_report
     * @return iit_report
     */
    @Override
    public List<IITReport> selectIitReportList(IITReport iitReport) {
        Map<String, Object> params = iitReport.getParams();
//        String duration = iitReport.getParams().get("durationRange") == null
//                ? null : iitReport.getParams().get("durationRange").toString();
        String duration=iitReport.getDuration();
        if (StringUtils.isNotEmpty(duration)) {
            String[] durationArray = duration.split("~");
            String beginTime = durationArray[0];
            String endTime = durationArray[1];
            params.put("beginTime", beginTime.trim());
            params.put("endTime", endTime.trim());
        }
        // 数据权限筛选条件
//        PayrollUtils.paramSet(params, dbCommon, null);
        iitReport.setParams(params);
        List<IITReport> iitReports = iitReportMapper.selectIitReportList(iitReport);
        return iitReports;
    }


}
