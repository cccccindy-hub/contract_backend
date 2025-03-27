package com.nnroad.client.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nnroad.client.domain.ClientBillinginfo;
import com.nnroad.client.domain.SysClient;
import com.nnroad.client.mapper.SysClientMapper;
import com.nnroad.client.service.ClientSyncService;
import com.nnroad.common.enums.DataSourceType;
import com.nnroad.common.utils.SecurityUtils;
import com.nnroad.framework.datasource.DynamicDataSourceContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnroad.client.mapper.ClientInfoMapper;
import com.nnroad.client.domain.ClientInfo;
import com.nnroad.client.service.ISysClientInfoService;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author nnroad
 * @date 2024-11-08
 */
@Service
public class SysClientInfoServiceImpl implements ISysClientInfoService
{
    @Autowired
    private ClientInfoMapper clientInfoMapper;

    @Autowired
    private SysClientMapper sysClientMapper;

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
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public ClientInfo selectClientInfoById(Long id, String tableName)
    {
        return clientInfoMapper.selectClientInfoById(id, tableName);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param clientInfo 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ClientInfo> selectClientInfoList(ClientInfo clientInfo)
    {
        return clientInfoMapper.selectClientInfoList(clientInfo);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param clientInfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertClientInfo(ClientInfo clientInfo)
    {
        SysClient c = sysClientMapper.selectSysClientById(clientInfo.getClientId());
        clientInfo.setCompanyCode(c.getCompanyCode());
        String userName = SecurityUtils.getUsername();
        clientInfo.setCreateBy(userName);
        clientSyncService.checkVendorSync(c, clientInfo.getTableName());
        clientSyncService.checkClientSync(c, clientInfo.getTableName());
        return clientInfoMapper.insertClientInfo(clientInfo);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param clientInfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateClientInfo(ClientInfo clientInfo)
    {
        SysClient c = sysClientMapper.selectSysClientById(clientInfo.getClientId());
        clientInfo.setCompanyCode(c.getCompanyCode());
        String userName = SecurityUtils.getUsername();
        clientInfo.setUpdateBy(userName);
        clientSyncService.checkVendorSync(c, clientInfo.getTableName());
        clientSyncService.checkClientSync(c, clientInfo.getTableName());
        return clientInfoMapper.updateClientInfo(clientInfo);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteClientInfoByIds(Long[] ids, String tableName)
    {
        return clientInfoMapper.deleteClientInfoByIds(ids, tableName);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteClientInfoById(Long id)
    {
        return clientInfoMapper.deleteClientInfoById(id);
    }

    @Override
    public ClientInfo getClientOtherSyncData(String org, String code, String tableName) {
        DataSourceType dataSourceType = DB_TO_DATASOURCE_MAP.get(org);
        if (dataSourceType != null) {
            DynamicDataSourceContextHolder.setDataSourceType(dataSourceType.name());
            return clientInfoMapper.selectClientInfoByCode(code, tableName);
        } else {
            return null;
        }
    }

    @Override
    public ClientInfo selectClientInfoByCode(String companyCode, String tableName) {
        return clientInfoMapper.selectClientInfoByCode(companyCode, tableName);
    }

    @Override
    public int updateClientInfoBySync(ClientInfo clientInfo) {
        return clientInfoMapper.updateClientInfo(clientInfo);
    }

    @Override
    public int insertClientInfoBySync(ClientInfo clientInfo) {
        return clientInfoMapper.insertClientInfo(clientInfo);
    }

    @Override
    public ClientBillinginfo getClientPayPassword(String erNo) {
        return clientInfoMapper.getClientPayPassword(erNo);
    }
}
