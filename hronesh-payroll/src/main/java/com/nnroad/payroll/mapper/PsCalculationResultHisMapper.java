package com.nnroad.payroll.mapper;


import com.nnroad.payroll.domain.common.PsCalculationResultHis;

import java.util.List;

/**
 * 计算结果历史Mapper接口
 * 
 * @author Aaron
 * @date 2021-12-13
 */

public interface PsCalculationResultHisMapper 
{
    /**
     * 新增计算结果历史
     * 
     * @param psCalculationResultHis 计算结果历史
     * @return 结果
     */
    public int insertPsCalculationResultHis(PsCalculationResultHis psCalculationResultHis);

    /**
     * 修改计算结果历史
     * 
     * @param psCalculationResultHis 计算结果历史
     * @return 结果
     */
    public int updatePsCalculationResultHis(PsCalculationResultHis psCalculationResultHis);

    /**
     * 校验时查询ps_calculation_result列表
     *
     * @param psCalculationResult ps_calculation_result
     * @return ps_calculation_result集合
     */
    public List<PsCalculationResultHis> selectPsCalculationResultCheckList(PsCalculationResultHis psCalculationResult);
}
