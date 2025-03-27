package com.nnroad.employee.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.enums.DataSourceType;
import com.nnroad.common.utils.SecurityUtils;
import com.nnroad.employee.domain.SysEmployee;
import com.nnroad.employee.mapper.EmployeeInfoMapper;
import com.nnroad.employee.mapper.SysEmployeeMapper;
import com.nnroad.employee.service.EmployeeInfoService;
import com.nnroad.employee.service.EmployeeSyncService;
import com.nnroad.employee.service.ISysEmployeeService;
import com.nnroad.framework.datasource.DynamicDataSourceContextHolder;
import com.nnroad.system.domain.SysNotice;
import com.nnroad.system.mapper.SysNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.nnroad.employee.domain.EmployeeInfo;
import com.nnroad.utils.ExtraAttributeUtils;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author nnroad
employeete 2024-11-13
 */
@Service
public class EmployeeInfoServiceImpl implements EmployeeInfoService
{
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    @Autowired
    private ExtraAttributeUtils extraAttributeUtils;

    @Autowired
    private SysEmployeeMapper employeeMapper;

    @Autowired
    private EmployeeSyncService employeeSyncService;


    @Autowired
    private SysNoticeMapper noticeMapper;

    @Value("${sys.company-name}")
    private String companyName;




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
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public EmployeeInfo selectEmployeeInfoById(Long id, String tableName)
    {
        return employeeInfoMapper.selectEmployeeInfoById(id, tableName);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param employeeInfo 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<EmployeeInfo> selectEmployeeInfoList(EmployeeInfo employeeInfo)
    {
        if (employeeInfo.getExtraData() != null) {
            Map<String, Object> processedExtraData = extraAttributeUtils.processExtraDataForSearching(employeeInfo.getExtraData());
            employeeInfo.setExtraData(processedExtraData);
        }
        return employeeInfoMapper.selectEmployeeInfoList(employeeInfo);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param employeeInfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertEmployeeInfo(EmployeeInfo employeeInfo)
    {
        SysEmployee e = employeeMapper.selectSysEmployeeById(employeeInfo.getEmployeeId());
        employeeInfo.setEmployeeCode(e.getEmployeeCode());
        String userName = SecurityUtils.getUsername();
        employeeInfo.setCreateBy(userName);
        int res = employeeInfoMapper.insertEmployeeInfo(employeeInfo);
        //check if employee info needs to sync to other vendor and client company
        employeeSyncService.checkVendorSync(e, employeeInfo.getTableName());
        employeeSyncService.checkClientSync(e, employeeInfo.getTableName());
        return res;
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param employeeInfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateEmployeeInfo(EmployeeInfo employeeInfo)
    {
        SysEmployee e = employeeMapper.selectSysEmployeeById(employeeInfo.getEmployeeId());
        employeeInfo.setEmployeeCode(e.getEmployeeCode());
        String userName = SecurityUtils.getUsername();
        employeeInfo.setUpdateBy(userName);
        int res = employeeInfoMapper.updateEmployeeInfo(employeeInfo);
        employeeSyncService.checkVendorSync(e, employeeInfo.getTableName());
        employeeSyncService.checkClientSync(e, employeeInfo.getTableName());
        return res;
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteEmployeeInfoByIds(Long[] ids, String tableName)
    {
        return employeeInfoMapper.deleteEmployeeInfoByIds(ids, tableName);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteEmployeeInfoById(Long id, String tableName)
    {
        return employeeInfoMapper.deleteEmployeeInfoById(id, tableName);
    }

    @Override
    public EmployeeInfo getEmployeeOtherSyncData(String org, String code, String tableName) {
        DataSourceType dataSourceType = DB_TO_DATASOURCE_MAP.get(org);
        if (dataSourceType != null) {
            DynamicDataSourceContextHolder.setDataSourceType(dataSourceType.name());
            return employeeInfoMapper.selectEmployeeInfoByCode(code, tableName);
        } else {
            return null;
        }
    }

    @Override
    public EmployeeInfo selectEmployeeInfoByCode(String employeeCode, String tableName) {
        return employeeInfoMapper.selectEmployeeInfoByCode(employeeCode, tableName);
    }

    @Override
    public int updateEmployeeInfoBySync(EmployeeInfo employeeInfo) {
        return employeeInfoMapper.updateEmployeeInfo(employeeInfo);
    }

    @Override
    public int insertEmployeeInfoBySync(EmployeeInfo employeeInfo) {
        return employeeInfoMapper.insertEmployeeInfo(employeeInfo);
    }
}
