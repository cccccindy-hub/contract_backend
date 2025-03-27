package com.nnroad.payroll.mapper;


import com.nnroad.payroll.domain.common.PsCalculationResult;

import java.util.List;

/**
 * ps_calculation_resultMapper接口
 * 
 * @author Hrone
 * @date 2021-01-17
 */
public interface PsCalculationResultMapper 
{

    /**
     * 新增ps_calculation_result
     * 
     * @param psCalculationResult ps_calculation_result
     * @return 结果
     */
    public int insertPsCalculationResult(PsCalculationResult psCalculationResult);

    /**
     * 修改ps_calculation_result
     * 
     * @param psCalculationResult ps_calculation_result
     * @return 结果
     */
    public int updatePsCalculationResult(PsCalculationResult psCalculationResult);

    /**
     * 删除ps_calculation_result
     *
     * @param psCalculationResult ps_calculation_result
     * @return 结果
     */
    public int deletePsCalculationResultByKey(PsCalculationResult psCalculationResult);

    /**
     * 校验时查询ps_calculation_result列表
     *
     * @param psCalculationResult ps_calculation_result
     * @return ps_calculation_result集合
     */
    public List<PsCalculationResult> selectPsCalculationResultCheckList(PsCalculationResult psCalculationResult);

    /**
     * 查询ps_calculation_result列表
     *
     * @param psCalculationResult ps_calculation_result
     * @return ps_calculation_result集合
     */
    public List<PsCalculationResult> selectPsCalculationResultList(PsCalculationResult psCalculationResult);

    public PsCalculationResult getPsCalculationResult(PsCalculationResult psCalculationResult);

}
