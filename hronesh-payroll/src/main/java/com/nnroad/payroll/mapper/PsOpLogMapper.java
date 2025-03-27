package com.nnroad.payroll.mapper;


import com.nnroad.payroll.domain.PsOpLog;
import com.nnroad.payroll.domain.common.PsCoverInfo;

import java.util.List;

/**
 * ps_op_logMapper接口
 * 
 * @author Hrone
 * @date 2021-01-17
 */

public interface PsOpLogMapper 
{
    /**
     * 查询ps_op_log
     * 
     * @param opId ps_op_logID
     * @return ps_op_log
     */
    public PsOpLog selectPsOpLogById(Long opId);

    /**
     * 查询ps_op_log列表
     * 
     * @param psOpLog ps_op_log
     * @return ps_op_log集合
     */
    public List<PsOpLog> selectPsOpLogList(PsOpLog psOpLog);


    /**
     * 新增ps_op_log
     * 
     * @param psOpLog ps_op_log
     * @return 结果
     */
    public int insertPsOpLog(PsOpLog psOpLog);

    /**
     * 修改ps_op_log
     * 
     * @param psOpLog ps_op_log
     * @return 结果
     */
    public int updatePsOpLog(PsOpLog psOpLog);

    /**
     * 删除ps_op_log
     * 
     * @param opId ps_op_logID
     * @return 结果
     */
    public int deletePsOpLogById(Long opId);

    /**
     * 批量删除ps_op_log
     * 
     * @param opIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePsOpLogByIds(String[] opIds);

    /**
     * 新增payment notice封面信息
     *
     * @param psCoverInfo payment notice封面信息
     * @return 结果
     */
    public int insertPsCoverInfo(PsCoverInfo psCoverInfo);

    /**
     * 查询payment notice封面信息列表
     *
     * @param psCoverInfo payment notice封面信息
     * @return payment notice封面信息集合
     */
    public List<PsCoverInfo> selectPsCoverInfoList(PsCoverInfo psCoverInfo);

    public PsCoverInfo getPsCoverInfo(PsCoverInfo psCoverInfo);
    /**
     * 删除payment notice封面信息
     *
     * @param psCoverInfo payment notice封面信息信息
     * @return 结果
     */
    public int deletePsCoverInfoByCondition(PsCoverInfo psCoverInfo);
}
