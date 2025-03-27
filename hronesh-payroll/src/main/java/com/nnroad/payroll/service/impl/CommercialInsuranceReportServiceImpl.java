package com.nnroad.payroll.service.impl;

import com.nnroad.common.core.text.Convert;
import com.nnroad.payroll.domain.exportC.CommercialInsuranceReport;
import com.nnroad.payroll.mapper.CommercialInsuranceReportMapper;
import com.nnroad.payroll.service.ICommercialInsuranceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * commercial_insurance_reportService业务层处理
 *
 * @author Hrone
 * @date 2021-12-31
 */
@Service
public class CommercialInsuranceReportServiceImpl implements ICommercialInsuranceReportService {
    @Autowired
    private CommercialInsuranceReportMapper commercialInsuranceReportMapper;

//    @Value("${sys.dbCommon}")
//    private String dbCommon;

    /**
     * 查询commercial_insurance_report
     *
     * @param employeeName commercial_insurance_reportID
     * @return commercial_insurance_report
     */
    @Override
    public CommercialInsuranceReport selectCommercialInsuranceReportById(String employeeName) {
        return commercialInsuranceReportMapper.selectCommercialInsuranceReportById(employeeName);
    }

    /**
     * 查询commercial_insurance_report列表
     *
     * @param commercialInsuranceReport commercial_insurance_report
     * @return commercial_insurance_report
     */
    @Override
    public List<CommercialInsuranceReport> selectCommercialInsuranceReportList(CommercialInsuranceReport commercialInsuranceReport) {
        Map<String, Object> params = commercialInsuranceReport.getParams();
        // 数据权限筛选条件
//        PayrollUtils.paramSet(params, dbCommon, null);
        commercialInsuranceReport.setParams(params);
        return commercialInsuranceReportMapper.selectCommercialInsuranceReportList(commercialInsuranceReport);
    }

    /**
     * 新增commercial_insurance_report
     *
     * @param commercialInsuranceReport commercial_insurance_report
     * @return 结果
     */
    @Override
    public int insertCommercialInsuranceReport(CommercialInsuranceReport commercialInsuranceReport) {
        return commercialInsuranceReportMapper.insertCommercialInsuranceReport(commercialInsuranceReport);
    }

    /**
     * 修改commercial_insurance_report
     *
     * @param commercialInsuranceReport commercial_insurance_report
     * @return 结果
     */
    @Override
    public int updateCommercialInsuranceReport(CommercialInsuranceReport commercialInsuranceReport) {
        return commercialInsuranceReportMapper.updateCommercialInsuranceReport(commercialInsuranceReport);
    }

    /**
     * 删除commercial_insurance_report对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCommercialInsuranceReportByIds(String ids) {
        return commercialInsuranceReportMapper.deleteCommercialInsuranceReportByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除commercial_insurance_report信息
     *
     * @param employeeName commercial_insurance_reportID
     * @return 结果
     */
    @Override
    public int deleteCommercialInsuranceReportById(String employeeName) {
        return commercialInsuranceReportMapper.deleteCommercialInsuranceReportById(employeeName);
    }
}
