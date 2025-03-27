package com.nnroad.payroll.service;


import com.nnroad.payroll.domain.common.PsCalculationResultHis;

import java.util.List;

/**
 * 计算结果历史Service接口
 * 
 * @author Aaron
 * @date 2021-12-13
 */
public interface IPsCalculationResultHisService 
{

    /**
     * 导入ps_calculation_result信息
     * @param psCalculationResultList 计算结果数据
     * @param isUpdateSupport 是否更新
     * @param duration 年月
     * @param leaderId 数据权限拥有者
     * @return 结果
     */
    public String importPsCalculationResult(List<PsCalculationResultHis> psCalculationResultList, boolean isUpdateSupport, String duration, String leaderId);

}
