package com.nnroad.payroll.service.impl;


import com.nnroad.common.core.text.Convert;
import com.nnroad.common.utils.DateUtils;
import com.nnroad.common.utils.StringUtils;
import com.nnroad.payroll.domain.exportC.PsPnExportLog;
import com.nnroad.payroll.mapper.PsPnExportLogMapper;
import com.nnroad.payroll.service.IPsPnExportLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * pn账单导出记录Service业务层处理
 *
 * @author Hrone
 * @date 2022-08-02
 */
@Service
public class PsPnExportLogServiceImpl implements IPsPnExportLogService {
    @Autowired
    private PsPnExportLogMapper psPnExportLogMapper;

    /**
     * 查询pn账单导出记录
     *
     * @param id pn账单导出记录ID
     * @return pn账单导出记录
     */
    @Override
    public PsPnExportLog selectPsPnExportLogById(Long id) {
        return psPnExportLogMapper.selectPsPnExportLogById(id);
    }

    /**
     * 查询pn账单导出记录列表
     *
     * @param psPnExportLog pn账单导出记录
     * @return pn账单导出记录
     */
    @Override
    public List<PsPnExportLog> selectPsPnExportLogList(PsPnExportLog psPnExportLog) {
        String duration = psPnExportLog.getParams().get("durationRange") == null
                ? null : psPnExportLog.getParams().get("durationRange").toString();
        if (StringUtils.isNotEmpty(duration)) {
            String[] durationArray = duration.split("~");
            String beginTime = durationArray[0];
            String endTime = durationArray[1];
            psPnExportLog.getParams().put("beginTime", beginTime.trim());
            psPnExportLog.getParams().put("endTime", endTime.trim());
        }
        return psPnExportLogMapper.selectPsPnExportLogList(psPnExportLog);
    }

    /**
     * 新增pn账单导出记录
     *
     * @param psPnExportLog pn账单导出记录
     * @return 结果
     */
    @Override
    public int insertPsPnExportLog(PsPnExportLog psPnExportLog) {
        psPnExportLog.setCreateTime(DateUtils.getNowDate());
        return psPnExportLogMapper.insertPsPnExportLog(psPnExportLog);
    }

    /**
     * 修改pn账单导出记录
     *
     * @param psPnExportLog pn账单导出记录
     * @return 结果
     */
    @Override
    public int updatePsPnExportLog(PsPnExportLog psPnExportLog) {
        psPnExportLog.setUpdateTime(DateUtils.getNowDate());
        return psPnExportLogMapper.updatePsPnExportLog(psPnExportLog);
    }

    /**
     * 删除pn账单导出记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePsPnExportLogByIds(String ids) {
        return psPnExportLogMapper.deletePsPnExportLogByIds(Convert.toStrArray(ids));
    }

}
