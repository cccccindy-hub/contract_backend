package com.nnroad.system.mapper;

import com.nnroad.system.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 实体表 数据层
 *
 * @author Hrone
 */
public interface SysAuthMapper
{
    /**
     * 查询数据库表列表
     *
     * @param table 表信息
     * @return 表列表
     */
    public List<SysTable> selectTableList(SysTable table);

    /**
     * 查询数据库表字段列表
     *
     * @param map 数据库名,表名
     */
    public List<SysTableField> selectTableFieldList(@Param("params") Map<String, Object> map);

    /**
     * 查询业务表数据(多个)
     *
     * @param sql 复制sql文
     * @return 结果
     */
    public List<Map<String, Object>> getDataList(@Param("sql") String sql);

    /**
     * 查询业务表数据件数(count)
     *
     * @param sql 复制sql文
     * @return 结果
     */
    public Long getDataCount(@Param("sql") String sql);

    /**
     * 更新业务表GroupIds
     *
     * @param sql 更新sql文
     * @return 结果
     */
    public int updateGroupIds(@Param("sql") String sql);

}

