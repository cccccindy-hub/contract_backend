package com.nnroad.payroll.service.impl;
import com.nnroad.payroll.domain.exportC.EmployersLiabilityInsuranceReport;
import com.nnroad.payroll.mapper.EmployersLiabilityInsuranceReportMapper;
import com.nnroad.payroll.service.IEmployersLiabilityInsuranceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import com.nnroad.common.core.text.Convert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 雇主责任险Service业务层处理
 *
 */
@Service
public class EmployersLiabilityInsuranceReportReportServiceImpl implements IEmployersLiabilityInsuranceReportService {
    @Autowired
    private EmployersLiabilityInsuranceReportMapper employersLiabilityInsuranceReportMapper;

//    @Value("${sys.dbCommon}")
//    private String dbCommon;

    /**
     * 查询雇主责任险
     *
     * @param name 雇主责任险ID
     * @return 雇主责任险
     */
    @Override
    public EmployersLiabilityInsuranceReport selectEmployersLiabilityInsuranceReportById(String name) {
        return employersLiabilityInsuranceReportMapper.selectEmployersLiabilityInsuranceReportById(name);
    }

    /**
     * 查询雇主责任险列表
     *
     * @param employersLiabilityInsuranceReport 雇主责任险
     * @return 雇主责任险
     */
    @Override
    public List<EmployersLiabilityInsuranceReport> selectEmployersLiabilityInsuranceReportList(EmployersLiabilityInsuranceReport employersLiabilityInsuranceReport) {
        Map<String, Object> params = employersLiabilityInsuranceReport.getParams();
        // 数据权限筛选条件
//        PayrollUtils.paramSet(params, dbCommon, null);
        employersLiabilityInsuranceReport.setParams(params);
        return employersLiabilityInsuranceReportMapper.selectEmployersLiabilityInsuranceReportList(employersLiabilityInsuranceReport);
    }

    /**
     * 新增雇主责任险
     *
     * @param employersLiabilityInsuranceReport 雇主责任险
     * @return 结果
     */
    @Override
    public int insertEmployersLiabilityInsuranceReport(EmployersLiabilityInsuranceReport employersLiabilityInsuranceReport) {
        return employersLiabilityInsuranceReportMapper.insertEmployersLiabilityInsuranceReport(employersLiabilityInsuranceReport);
    }

    /**
     * 修改雇主责任险
     *
     * @param employersLiabilityInsuranceReport 雇主责任险
     * @return 结果
     */
    @Override
    public int updateEmployersLiabilityInsuranceReport(EmployersLiabilityInsuranceReport employersLiabilityInsuranceReport) {
        return employersLiabilityInsuranceReportMapper.updateEmployersLiabilityInsuranceReport(employersLiabilityInsuranceReport);
    }

    /**
     * 删除雇主责任险对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEmployersLiabilityInsuranceReportByIds(String ids) {
        return employersLiabilityInsuranceReportMapper.deleteEmployersLiabilityInsuranceReportByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除雇主责任险信息
     *
     * @param name 雇主责任险ID
     * @return 结果
     */
    @Override
    public int deleteEmployersLiabilityInsuranceReportById(String name) {
        return employersLiabilityInsuranceReportMapper.deleteEmployersLiabilityInsuranceReportById(name);
    }
}
