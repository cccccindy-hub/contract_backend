package com.nnroad.payroll.service;


import com.nnroad.payroll.domain.exportC.PsPnExportLog;

import java.util.List;

/**
 * pn账单导出记录Service接口
 * 
 * @author Hrone
 * @date 2022-08-02
 */
public interface IPsPnExportLogService 
{
    /**
     * 查询pn账单导出记录
     * 
     * @param id pn账单导出记录ID
     * @return pn账单导出记录
     */
    public PsPnExportLog selectPsPnExportLogById(Long id);

    /**
     * 查询pn账单导出记录列表
     * 
     * @param psPnExportLog pn账单导出记录
     * @return pn账单导出记录集合
     */
    public List<PsPnExportLog> selectPsPnExportLogList(PsPnExportLog psPnExportLog);

    /**
     * 新增pn账单导出记录
     * 
     * @param psPnExportLog pn账单导出记录
     * @return 结果
     */
    public int insertPsPnExportLog(PsPnExportLog psPnExportLog);

    /**
     * 修改pn账单导出记录
     * 
     * @param psPnExportLog pn账单导出记录
     * @return 结果
     */
    public int updatePsPnExportLog(PsPnExportLog psPnExportLog);

    /**
     * 批量删除pn账单导出记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePsPnExportLogByIds(String ids);
}
