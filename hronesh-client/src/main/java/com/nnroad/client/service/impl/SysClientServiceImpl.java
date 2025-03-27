package com.nnroad.client.service.impl;

import java.time.LocalDateTime;
import java.util.*;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnroad.client.domain.SysClient;
import com.nnroad.client.mapper.SysClientMapper;
import com.nnroad.client.service.ClientSyncService;
import com.nnroad.client.service.ISysClientService;
import com.nnroad.common.enums.DataSourceType;
import com.nnroad.common.utils.NnroadSequence;
import com.nnroad.common.utils.SecurityUtils;
import com.nnroad.framework.datasource.DynamicDataSourceContextHolder;
import com.nnroad.utils.ExtraAttributeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


/**
 * Service implementation for SysClient operations.
 *
 * @author nick
 * @date 2024-10-10
 */
@Service
public class SysClientServiceImpl implements ISysClientService {
    @Autowired
    private SysClientMapper sysClientMapper;

    @Autowired
    private ExtraAttributeUtils extraAttributeUtils;

    @Autowired
    private NnroadSequence sequence;

    @Autowired
    private ClientSyncService clientSyncService;

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
     * Retrieve a SysClient by its unique identifier.
     *
     * @param id The unique identifier of the SysClient.
     * @return The SysClient object associated with the given id, or null if not found.
     */
    @Override
    public SysClient selectSysClientById(Long id) {
        return sysClientMapper.selectSysClientById(id);
    }

    /**
     * Retrieve a list of SysClient objects.
     *
     * @param sysClient The criteria for querying clients.
     * @return A list of SysClient objects matching the criteria.
     */
    @Override
    public List<SysClient> selectSysClientList(SysClient sysClient)
    {
        if(sysClient.getExtraData() != null) {
            Map<String, Object> processedExtraData = extraAttributeUtils.processExtraDataForSearching(sysClient.getExtraData());
            sysClient.setExtraData(processedExtraData);
        }
        return sysClientMapper.selectSysClientList(sysClient);
    }

    /**
     * Add a new SysClient.
     *
     * @param sysClient The SysClient object to be added.
     * @return The result of the operation (e.g., number of rows affected).
     */
    @Override
    public int insertSysClient(SysClient sysClient) {
        if (extraAttributeUtils.validateData(sysClient.getExtraData(), "sys_client")) {
            String userName = SecurityUtils.getUsername();
            sysClient.setCreateBy(userName);
            return sysClientMapper.insertSysClient(sysClient);
        } else {
            throw new IllegalArgumentException("Invalid input data for extra attributes");
        }
    }

    @Override
    public int insertClientContract(Map<String, String> contractMap) {
        return sysClientMapper.insertClientContract(contractMap);
    }

    /**
     * Update an existing SysClient.
     *
     * @param sysClient The SysClient object containing updated data.
     * @return The result of the operation (e.g., number of rows affected).
     */
    @Override
    public int updateSysClient(SysClient sysClient) {
        if (extraAttributeUtils.validateData(sysClient.getExtraData(), "sys_client")) {
            //sync client data to internal vendor (e.g.HRSH, FDI)
            clientSyncService.checkVendorSync(sysClient, "sys_client");
            //sync client data to internal client (e.g.HRHK, TOPFDI)
            clientSyncService.checkClientSync(sysClient, "sys_client");
            String userName = SecurityUtils.getUsername();
            sysClient.setUpdateBy(userName);
            return sysClientMapper.updateSysClient(sysClient);
        } else {
            throw new IllegalArgumentException("Invalid input data for extra attributes.");
        }

    }

    /**
     * Batch delete SysClient objects.
     *
     * @param ids An array of unique identifiers for the SysClients to be deleted.
     * @return The result of the operation (e.g., number of rows affected).
     */
    @Override
    public int deleteSysClientByIds(Long[] ids) {
        return sysClientMapper.deleteSysClientByIds(ids);
    }

    /**
     * Delete a SysClient by its unique identifier.
     *
     * @param id The unique identifier of the SysClient to be deleted.
     * @return The result of the operation (e.g., number of rows affected).
     */
    @Override
    public int deleteSysClientById(Long id) {
        return sysClientMapper.deleteSysClientById(id);
    }

    @Override
    public List<SysClient> getClientByVendorId(Long vendorId) {
        return sysClientMapper.getClientByVendorId(vendorId);

    }

    @Override
    public SysClient selectBasicByClientCode(String clientCode) {
        return StrUtil.isNotBlank(clientCode) ? sysClientMapper.selectBasicByClientCode(clientCode) : null;
    }

    @Override
    public List<SysClient> selectBasicByClientName(String clientName) {

        return sysClientMapper.selectByClientName(clientName);
    }

    @Override
    public boolean checkCode(SysClient client, String prefix) {
        boolean ret = true;

        Map<String, Object> params = new HashMap<>();
        params.put("keyPrefix", prefix);
        client.setParams(params);

        int cnt = sysClientMapper.checkMaxCodeClient(client);
        if (cnt >0){
            ret = false;
        }
        return ret;
    }

    @Override
    public String resetAndGetCode(String prefix) {
        String maxCode =  sysClientMapper.getMaxCodeClient(prefix);

        String tempCode = prefix + "-" + maxCode.replace(prefix,"");

        return sequence.resetAndGetCode(tempCode, "-");
    }

    @Override
    public SysClient getClientSyncData(String org, String code) {
        DataSourceType dataSourceType = DB_TO_DATASOURCE_MAP.get(org);
        if (dataSourceType != null) {
            DynamicDataSourceContextHolder.setDataSourceType(dataSourceType.name());
            return sysClientMapper.selectBasicByClientCode(code);
        } else {
            return null;
        }
    }

    @Override
    public int updateClientBySync(SysClient sysClient) {
        return sysClientMapper.updateSysClient(sysClient);
    }

    @Override
    public List<Integer> selectTotal(LocalDateTime currentDate, String s, String dataSource) {
        List<Integer> leadsData = new ArrayList<>();
        DataSourceType dataSourceType = DB_TO_DATASOURCE_MAP.get(dataSource);
        if (dataSourceType != null) {
            DynamicDataSourceContextHolder.setDataSourceType(dataSourceType.name());
        }
        for (int i = 1; i < 13; i++) {
            LocalDateTime targetDateTime = currentDate.minusMonths(i);
            // 获取该月的最后一天的时间
            LocalDateTime endDate = targetDateTime.withDayOfMonth(targetDateTime.toLocalDate().lengthOfMonth()).withHour(23).withMinute(59).withSecond(59);
            LocalDateTime startDate = targetDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);

            LocalDateTime EtargetDateTime = currentDate.minusMonths(i+1);
            LocalDateTime EendDate = EtargetDateTime.withDayOfMonth(EtargetDateTime.toLocalDate().lengthOfMonth()).withHour(23).withMinute(59).withSecond(59);
            LocalDateTime EstartDate = EtargetDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
//
//            if ("existing".equals(s)){
//                leadsData.add(sysClientMapper.selectTotalExisting(startDate,endDate,isLicense));
//            }else if ("new".equals(s)){
//                leadsData.add(sysClientMapper.selectTotalNew(startDate,endDate,isLicense));
//            }else if ("exit".equals(s)){
//                leadsData.add(sysClientMapper.selectTotalExit(EstartDate,EendDate,isLicense)*-1);
            if ("existingHK".equals(s)){
                leadsData.add(sysClientMapper.selectExisting(startDate,endDate));
            }else if ("newHK".equals(s)){
                leadsData.add(sysClientMapper.selectNew(startDate,endDate));
            }else if ("exitHK".equals(s)){
                leadsData.add(sysClientMapper.selectExit(startDate,endDate,EstartDate,EendDate));
            }else if ("existingSHAndBJ".equals(s)){
                leadsData.add(sysClientMapper.selectExisting2(startDate,endDate));
            }else if ("newSHAndBJ".equals(s)){
                leadsData.add(sysClientMapper.selectNew2(startDate,endDate));
            }else if ("exitSHAndBJ".equals(s)){
                leadsData.add(sysClientMapper.selectExit2(startDate,endDate,EstartDate,EendDate));
            }else if ("existingTopFDI".equals(s)){
                leadsData.add(sysClientMapper.selectTopFDIExisting(startDate,endDate));
            }else if ("newTopFDI".equals(s)){
                leadsData.add(sysClientMapper.selectTopFDINew(startDate,endDate));
            }else if ("exitTopFDI".equals(s)){
                leadsData.add(sysClientMapper.selectTopFDIExit(startDate,endDate,EstartDate,EendDate));
            }else if ("existingFDI".equals(s)){
                leadsData.add(sysClientMapper.selectFDIExisting(startDate,endDate));
            }else if ("newFDI".equals(s)){
                leadsData.add(sysClientMapper.selectFDINew(startDate,endDate));
            }else if ("exitFDI".equals(s)) {
                leadsData.add(sysClientMapper.selectFDIExit(EstartDate, EendDate));
            }
        }


        return leadsData;
    }

    @Override
    public List<SysClient> getEmployerList() {
        return sysClientMapper.getEmployerList();
    }

}
