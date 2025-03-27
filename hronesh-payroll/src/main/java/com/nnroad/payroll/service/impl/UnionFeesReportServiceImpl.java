package com.nnroad.payroll.service.impl;

import com.nnroad.payroll.domain.exportC.UnionFeesReport;
import com.nnroad.payroll.mapper.UnionFeesReportMapper;
import com.nnroad.payroll.service.IUnionFeesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 工会费Service业务层处理
 *
 * @author Hrone
 * @date 2022-01-04
 */
@Service
public class UnionFeesReportServiceImpl implements IUnionFeesReportService {
    @Autowired
    private UnionFeesReportMapper unionFeesReportMapper;

//    @Value("${sys.dbCommon}")
//    private String dbCommon;

    /**
     * 查询工会费
     *
     * @param clients 工会费ID
     * @return 工会费
     */
    @Override
    public UnionFeesReport selectUnionFeesReportById(String clients) {
        return unionFeesReportMapper.selectUnionFeesReportById(clients);
    }

    /**
     * 查询工会费列表
     *
     * @param unionFeesReport 工会费
     * @return 工会费
     */
    @Override
    public List<UnionFeesReport> selectUnionFeesReportList(UnionFeesReport unionFeesReport) {
        Map<String, Object> params = unionFeesReport.getParams();
        // 数据权限筛选条件
//        PayrollUtils.paramSet(params, dbCommon, null);
        unionFeesReport.setParams(params);
        return unionFeesReportMapper.selectUnionFeesReportList(unionFeesReport);
    }

}
