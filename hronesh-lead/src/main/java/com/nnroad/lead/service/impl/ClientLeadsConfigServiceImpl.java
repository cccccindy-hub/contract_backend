package com.nnroad.lead.service.impl;

import com.nnroad.lead.domain.ClientLeadsConfig;
import com.nnroad.lead.mapper.ClientLeadsConfigMapper;
import com.nnroad.lead.service.IClientLeadsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientLeadsConfigServiceImpl implements IClientLeadsConfigService {

    @Autowired
    private ClientLeadsConfigMapper clientLeadsConfigMapper;

    @Override
    public int add(ClientLeadsConfig clientLeadsConfig) {
        clientLeadsConfig.setStatus("1");
        return clientLeadsConfigMapper.insertClientLeadsConfig(clientLeadsConfig);
    }

    @Override
    public int updateClientLeadsConfig(ClientLeadsConfig clientLeadsConfig) {
        return clientLeadsConfigMapper.updateClientLeadsConfig(clientLeadsConfig);
    }

    @Override
    public int deleteClientLeadsById(Long id) {
        return clientLeadsConfigMapper.deleteClientLeadsById(id);
    }

    @Override
    public List<ClientLeadsConfig> selectClientLeadsConfigList(ClientLeadsConfig clientLeadsConfig) {
        return clientLeadsConfigMapper.selectClientLeadsConfigList(clientLeadsConfig);
    }

    @Override
    public Map<String, List<ClientLeadsConfig>> getClientLeadsConfigs(List<String> columns) {
        ClientLeadsConfig clientLeadsConfig = new ClientLeadsConfig();
        clientLeadsConfig.setDeleteFlag("0");
        return getClientLeadsConfigs(columns, clientLeadsConfig);
    }

    @Override
    public Map<String, List<ClientLeadsConfig>> getClientLeadsConfigs(List<String> columns, ClientLeadsConfig clientLeadsConfig) {
        // 设置列名并查询配置列表
        clientLeadsConfig.setColumnName(String.join(",", columns));
        List<ClientLeadsConfig> clientLeadsConfigs = clientLeadsConfigMapper.selectClientLeadsConfigList(clientLeadsConfig);
        return clientLeadsConfigs.stream().collect(Collectors.groupingBy(ClientLeadsConfig :: getColumnName));
    }

    @Override
    public List<ClientLeadsConfig> selectClientLeadsConfigList(String columnName, String configName, String deleteFlag) {
        ClientLeadsConfig clientLeadsConfig = new ClientLeadsConfig();
        clientLeadsConfig.setColumnName(columnName);
        clientLeadsConfig.setName(configName);
        clientLeadsConfig.setDeleteFlag(deleteFlag);
        return selectClientLeadsConfigList(clientLeadsConfig);
    }

    @Override
    public String selectOneClientLeadsConfigId(String columnName, String name) {
        ClientLeadsConfig clientLeadsConfig = new ClientLeadsConfig();
        clientLeadsConfig.setColumnName(columnName);
        clientLeadsConfig.setName(name);

        List<ClientLeadsConfig> list = selectClientLeadsConfigList(clientLeadsConfig);
        if(!list.isEmpty()) {
            ClientLeadsConfig clc = list.get(0);
            return clc.getId().toString();
        }
        return "";
    }
}
