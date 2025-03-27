package com.nnroad.client.service.impl;

import com.nnroad.client.domain.SysClient;
import com.nnroad.client.service.ClientSyncService;
import com.nnroad.common.enums.DataSourceType;
import com.nnroad.common.utils.SecurityUtils;
import com.nnroad.employee.domain.EmployeeInfo;
import com.nnroad.employee.mapper.EmployeeInfoMapper;
import com.nnroad.framework.datasource.DynamicDataSourceContextHolder;
import com.nnroad.system.domain.SysNotice;
import com.nnroad.system.mapper.SysNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientSyncServiceImpl implements ClientSyncService {

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

    @Value("${employee.employee_contracts}")
    private String employeeContracts;

    @Value("${sys.orgs}")
    private String orgs;

    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    @Autowired
    private SysNoticeMapper noticeMapper;

    @Value("${sys.company-name}")
    private String companyName;

    @Override
    public void checkVendorSync(SysClient client, String tableType) {
        List<EmployeeInfo> contractInfoList = employeeInfoMapper.selectEmployeeInfoByCompanyCode(client.getCompanyCode(), "employee_contract");
        Set<String> employmentContractSet = new HashSet<>();
        String originalDataSource = DynamicDataSourceContextHolder.getDataSourceType();
        if (!contractInfoList.isEmpty()) {
            // Loop through each contractInfo
            for (EmployeeInfo contractInfo : contractInfoList) {
                // Get extra data for the current contractInfo
                Map<String, Object> extraData = contractInfo.getExtraData();
                if (extraData != null) {
                    // Get the value of Employment Contract
                    String employmentContract = (String) extraData.get("424");
                    // If the value exists, add it to the result list
                    if (employmentContract != null) {
                        employmentContractSet.add(employmentContract);
                    }
                }
            }
        }
        for (String employmentContract: employmentContractSet) {
            if (employmentContract != null && !employeeContracts.contains(employmentContract)) {
                DataSourceType dataSourceType = DB_TO_DATASOURCE_MAP.get(employmentContract);
                if (dataSourceType != null) {
                    DynamicDataSourceContextHolder.setDataSourceType(dataSourceType.name());
                    //send a notification to vendor
                    SysNotice notice = new SysNotice();
                    notice.setNoticeTitle(client.getClientName() + "(" + client.getCompanyCode() + ")" + " has been updated from " + client.getOrg());
                    notice.setNoticeType("1");
                    notice.setCode(client.getCompanyCode());
                    notice.setOrg(client.getOrg());
                    notice.setStatus("0");
                    notice.setDataType("Client");
                    notice.setTableType(tableType);
                    // set status to Not synchronized
                    notice.setStatus("3");
                    notice.setCreateBy(SecurityUtils.getUsername());
                    notice.setCreateTime(new Date());
                    noticeMapper.insertNotice(notice);
                }
            }
        }
        DynamicDataSourceContextHolder.setDataSourceType(originalDataSource);
    }


    @Override
    public void checkClientSync(SysClient client, String tableType) {
        String originalDataSource = DynamicDataSourceContextHolder.getDataSourceType();
        String org = client.getOrg();
        if(org != null && !orgs.contains(org)) {
            DataSourceType dataSourceType = DB_TO_DATASOURCE_MAP.get(org);
            if (dataSourceType != null) {
                DynamicDataSourceContextHolder.setDataSourceType(dataSourceType.name());
                //send a notification to client
                SysNotice notice = new SysNotice();
                notice.setNoticeTitle(client.getClientName() + "(" + client.getCompanyCode() + ")" + " has been updated from " + companyName);
                notice.setNoticeType("1");
                notice.setCode(client.getCompanyCode());
                notice.setOrg(client.getOrg());
                notice.setStatus("0");
                notice.setDataType("Client");
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
