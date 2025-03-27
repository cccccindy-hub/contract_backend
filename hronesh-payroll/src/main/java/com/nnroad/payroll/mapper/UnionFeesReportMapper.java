package com.nnroad.payroll.mapper;


import com.nnroad.payroll.domain.exportC.UnionFeesReport;

import java.util.List;

/**
 * 工会费Mapper接口
 * 
 * @author Hrone
 * @date 2022-01-04
 */

public interface UnionFeesReportMapper
{
    /**
     * 查询工会费
     * 
     * @param clients 工会费ID
     * @return 工会费
     */
    public UnionFeesReport selectUnionFeesReportById(String clients);

    /**
     * 查询工会费列表
     * 
     * @param unionFeesReport 工会费
     * @return 工会费集合
     */
    public List<UnionFeesReport> selectUnionFeesReportList(UnionFeesReport unionFeesReport);

}
