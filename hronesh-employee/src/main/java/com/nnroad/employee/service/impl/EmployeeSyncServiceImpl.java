package com.nnroad.employee.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnroad.common.enums.DataSourceType;
import com.nnroad.common.utils.SecurityUtils;
import com.nnroad.employee.domain.EmployeeInfo;
import com.nnroad.employee.domain.SysEmployee;
import com.nnroad.employee.mapper.EmployeeInfoMapper;
import com.nnroad.employee.service.EmployeeSyncService;
import com.nnroad.framework.datasource.DynamicDataSourceContextHolder;
import com.nnroad.system.domain.SysNotice;
import com.nnroad.system.mapper.SysNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class EmployeeSyncServiceImpl implements EmployeeSyncService {

    @Autowired
    private SysNoticeMapper noticeMapper;

    @Value("${sys.company-name}")
    private String companyName;

    @Value("${employee.employee_contracts}")
    private String employeeContracts;

    @Value("${sys.orgs}")
    private String orgs;

    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    // Create a mapping of db names to DataSourceType values
    private static final Map<String, DataSourceType> DB_TO_DATASOURCE_MAP = new HashMap<>();
    static {
        DB_TO_DATASOURCE_MAP.put("HRSH", DataSourceType.HROneSH);
        DB_TO_DATASOURCE_MAP.put("HRBJ", DataSourceType.HROneBJ);
        DB_TO_DATASOURCE_MAP.put("HRHK", DataSourceType.HROneHK);
        DB_TO_DATASOURCE_MAP.put("FDI", DataSourceType.FDI);
        DB_TO_DATASOURCE_MAP.put("Top FDI", DataSourceType.TOPFDIHK);
        DB_TO_DATASOURCE_MAP.put("HROne SH", DataSourceType.HROneSH);
        DB_TO_DATASOURCE_MAP.put("HROne BJ", DataSourceType.HROneBJ);
    }


    @Override
    public void checkVendorSync(SysEmployee employee, String tableType) {
        EmployeeInfo contractInfo = employeeInfoMapper.selectEmployeeInfoByCode(employee.getEmployeeCode(), "employee_contract");
        if(contractInfo != null) {
            Map<String, Object> extraData = contractInfo.getExtraData();
            if (extraData != null) {
                //get employment contract
                String employmentContract = (String) extraData.get("424");

                String originalDataSource = DynamicDataSourceContextHolder.getDataSourceType();

                if (employmentContract != null && !employeeContracts.contains(employmentContract)) {

                    DataSourceType dataSourceType = DB_TO_DATASOURCE_MAP.get(employmentContract);

                    if (dataSourceType != null) {
                        DynamicDataSourceContextHolder.setDataSourceType(dataSourceType.name());
                        //send a notification to vendor
                        SysNotice notice = new SysNotice();
                        notice.setNoticeTitle(employee.getLocalName() + "(" + employee.getEmployeeCode() + ")" + " has been updated from " + employee.getOrg());
                        notice.setNoticeType("1");
                        notice.setCode(employee.getEmployeeCode());
                        notice.setOrg(employee.getOrg());
                        notice.setStatus("0");
                        notice.setDataType("Employee");
                        notice.setTableType(tableType);
                        // set status to Not synchronized
                        notice.setStatus("3");
                        notice.setCreateBy(SecurityUtils.getUsername());
                        notice.setCreateTime(new Date());
                        noticeMapper.insertNotice(notice);
                    }
                    DynamicDataSourceContextHolder.setDataSourceType(originalDataSource);
                }
            }
        }

    }

    @Override
    public void checkClientSync(SysEmployee employee, String tableType) {
        String originalDataSource = DynamicDataSourceContextHolder.getDataSourceType();
        String org = employee.getOrg();
        if(org != null && !orgs.contains(org)) {
            DataSourceType dataSourceType = DB_TO_DATASOURCE_MAP.get(org);
            if (dataSourceType != null) {
                DynamicDataSourceContextHolder.setDataSourceType(dataSourceType.name());
                //send a notification to vendor
                SysNotice notice = new SysNotice();
                notice.setNoticeTitle(employee.getLocalName() + "(" + employee.getEmployeeCode() + ")" + " has been updated from " + companyName);
                notice.setNoticeType("1");
                notice.setCode(employee.getEmployeeCode());
                notice.setOrg(companyName);
                notice.setStatus("0");
                notice.setDataType("Employee");
                notice.setTableType(tableType);
                // set status to Not synchronized
                notice.setStatus("3");
                notice.setCreateBy(SecurityUtils.getUsername());
                notice.setCreateTime(new Date());
                noticeMapper.insertNotice(notice);
            }
            DynamicDataSourceContextHolder.setDataSourceType(originalDataSource);
        }
    }
}
