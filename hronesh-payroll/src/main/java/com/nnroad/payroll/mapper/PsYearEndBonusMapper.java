package com.nnroad.payroll.mapper;


import com.nnroad.payroll.domain.common.PsYearEndBonus;

import java.util.List;

/**
 * ps_year_end_bonusMapper接口
 * 
 * @author Hrone
 * @date 2021-01-21
 */

public interface PsYearEndBonusMapper 
{
    /**
     * 删除ps_year_end_bonus
     *
     * @param psYearEndBonus ps_year_end_bonus
     * @return 结果
     */
    public int deletePsYearEndBonusByKey(PsYearEndBonus psYearEndBonus);
}
