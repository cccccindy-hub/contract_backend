package com.nnroad.lead.service;



import com.nnroad.lead.domain.ClientLeadsConfig;

import java.util.List;
import java.util.Map;

public interface IClientLeadsConfigService {
    int add(ClientLeadsConfig clientLeadsConfig);

    int updateClientLeadsConfig(ClientLeadsConfig clientLeadsConfig);

    int deleteClientLeadsById(Long id);

    List<ClientLeadsConfig> selectClientLeadsConfigList(ClientLeadsConfig clientLeadsConfig);
    List<ClientLeadsConfig> selectClientLeadsConfigList(String columnName, String configName, String deleteFlag);

    Map<String, List<ClientLeadsConfig>> getClientLeadsConfigs(List<String> leadsConfigsList);

    Map<String, List<ClientLeadsConfig>> getClientLeadsConfigs(List<String> leadsConfigsList, ClientLeadsConfig clientLeadsConfig);

    String selectOneClientLeadsConfigId(String columnName, String name);
}
