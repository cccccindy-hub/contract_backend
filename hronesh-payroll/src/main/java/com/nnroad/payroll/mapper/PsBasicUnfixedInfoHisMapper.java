package com.nnroad.payroll.mapper;


import com.nnroad.payroll.domain.common.PsBasicInfoHis;
import com.nnroad.payroll.domain.common.PsBasicUnfixedInfoHis;

import java.util.List;

/**
 * 变动信息信息Mapper接口
 * 
 * @author Aaron
 * @date 2021-12-13
 */

public interface PsBasicUnfixedInfoHisMapper 
{

    /**
     * 新增变动信息信息
     * 
     * @param psBasicUnfixedInfoHis 变动信息信息
     * @return 结果
     */
    public int insertPsBasicUnfixedInfoHis(PsBasicUnfixedInfoHis psBasicUnfixedInfoHis);

    /**
     * 修改变动信息信息
     * 
     * @param psBasicUnfixedInfoHis 变动信息信息
     * @return 结果
     */
    public int updatePsBasicUnfixedInfoHis(PsBasicUnfixedInfoHis psBasicUnfixedInfoHis);


    /**
     * 检验时查询ps_basic_info列表
     *
     * @param psBasicUnfixedInfoHis ps_basic_info
     * @return ps_basic_info集合
     */
    public List<PsBasicInfoHis> selectPsBasicUnfixedInfoCheckList(PsBasicUnfixedInfoHis psBasicUnfixedInfoHis);
}
