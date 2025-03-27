package com.nnroad.lead.mapper;


import com.nnroad.lead.domain.ClientLeadsConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientLeadsConfigMapper {
    int insertClientLeadsConfig(ClientLeadsConfig clientLeadsConfig);

    int updateClientLeadsConfig(ClientLeadsConfig clientLeadsConfig);

    int deleteClientLeadsById(Long id);

    List<ClientLeadsConfig> selectClientLeadsConfigList(ClientLeadsConfig clientLeadsConfig);
}
