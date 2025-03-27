package com.nnroad.datacenter.service;

import com.nnroad.datacenter.domain.DCDictData;
import com.nnroad.datacenter.domain.DCTableColumn;
import com.nnroad.datacenter.domain.DCTableConfig;

import java.util.List;
import java.util.Map;

public interface IDCTableConfigService {

    List<List<DCTableColumn>> getListColumns(Long tableId, int tempFlag);

    public List<DCTableConfig> selectDCTableConfigList(DCTableConfig dcTableConfig);

    Map<String, List<DCDictData>> getDictList(List<String> dictList);

    String formateDBName(String name);
}
