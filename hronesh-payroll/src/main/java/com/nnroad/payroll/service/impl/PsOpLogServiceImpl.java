package com.nnroad.payroll.service.impl;


import com.nnroad.common.core.text.Convert;
import com.nnroad.common.utils.DateUtils;
import com.nnroad.common.utils.SecurityUtils;
import com.nnroad.common.utils.ShiroUtils;
import com.nnroad.payroll.domain.PsOpLog;
import com.nnroad.payroll.domain.common.PsCoverInfo;
import com.nnroad.payroll.mapper.PsOpLogMapper;
import com.nnroad.payroll.service.IPsOpLogService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ps_op_logService业务层处理
 *
 * @author Hrone
 * @date 2021-01-17
 */
@Service
public class PsOpLogServiceImpl implements IPsOpLogService {
    @Autowired
    private PsOpLogMapper psOpLogMapper;


    /**
     * 查询ps_op_log
     *
     * @param opId ps_op_logID
     * @return ps_op_log
     */
    @Override
    public PsOpLog selectPsOpLogById(Long opId) {
        return psOpLogMapper.selectPsOpLogById(opId);
    }

    /**
     * 查询ps_op_log列表
     *
     * @param psOpLog ps_op_log
     * @return ps_op_log
     */
    @Override
    public List<PsOpLog> selectPsOpLogList(PsOpLog psOpLog) {
        Map<String, Object> params = psOpLog.getParams();
        psOpLog.setParams(params);
        return psOpLogMapper.selectPsOpLogList(psOpLog);
    }

    /**
     * 新增ps_op_log
     *
     * @param psOpLog ps_op_log
     * @return 结果
     */
    @Override
    public int insertPsOpLog(PsOpLog psOpLog) {
        psOpLog.setCreateTime(DateUtils.getNowDate());
//        psOpLog.setCreateBy(ShiroUtils.getLoginName());
        return psOpLogMapper.insertPsOpLog(psOpLog);
    }

    /**
     * 修改ps_op_log
     *
     * @param psOpLog ps_op_log
     * @return 结果
     */
    @Override
    public int updatePsOpLog(PsOpLog psOpLog) {
        psOpLog.setUpdateTime(DateUtils.getNowDate());
        psOpLog.setUpdateBy(SecurityUtils.getUsername());
        return psOpLogMapper.updatePsOpLog(psOpLog);
    }

    /**
     * 删除ps_op_log对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePsOpLogByIds(String ids) {
        return psOpLogMapper.deletePsOpLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除ps_op_log信息
     *
     * @param opId ps_op_logID
     * @return 结果
     */
    @Override
    public int deletePsOpLogById(Long opId) {
        return psOpLogMapper.deletePsOpLogById(opId);
    }

    /**
     * 查询payment notice封面信息列表
     *
     * @param psCoverInfo payment notice封面信息
     * @return payment notice封面信息
     */
    @Override
    public List<PsCoverInfo> selectPsCoverInfoList(PsCoverInfo psCoverInfo) {
        return psOpLogMapper.selectPsCoverInfoList(psCoverInfo);
    }

    /**
     * 新增payment notice封面信息
     *
     * @param psCoverInfo payment notice封面信息
     * @return 结果
     */
    @Override
    public int insertPsCoverInfo(PsCoverInfo psCoverInfo) {
        psCoverInfo.setCreateTime(DateUtils.getNowDate());
        return psOpLogMapper.insertPsCoverInfo(psCoverInfo);
    }

    /**
     * 删除payment notice封面信息
     *
     * @param psCoverInfo payment notice封面信息
     * @return 结果
     */
    @Override
    public int deletePsCoverInfoByCondition(PsCoverInfo psCoverInfo) {
        return psOpLogMapper.deletePsCoverInfoByCondition(psCoverInfo);
    }
}
