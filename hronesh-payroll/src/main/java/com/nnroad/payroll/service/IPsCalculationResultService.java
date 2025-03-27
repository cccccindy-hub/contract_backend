package com.nnroad.payroll.service;


import com.nnroad.payroll.domain.common.PsCalculationResult;

import java.util.List;

/**
 * ps_calculation_resultService接口
 * 
 * @author Hrone
 * @date 2021-01-17
 */
public interface IPsCalculationResultService 
{
    /**
     * 导入ps_calculation_result信息
     * @param psCalculationResultList 计算结果数据
     * @param isUpdateSupport 是否更新
     * @param duration 年月
     * @return 结果
     */
    public String importPsCalculationResult(List<PsCalculationResult> psCalculationResultList, boolean isUpdateSupport, String duration);

    /**
     * 查询ps_calculation_result列表
     *
     * @param psCalculationResult ps_calculation_result
     * @return ps_calculation_result集合
     */
    public List<PsCalculationResult> selectPsCalculationResultList(PsCalculationResult psCalculationResult);


}
