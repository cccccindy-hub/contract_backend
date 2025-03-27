package com.nnroad.payroll.mapper;


import com.nnroad.payroll.domain.common.PsBasicInfoHis;

import java.util.List;

/**
 * 基本信息历史Mapper接口
 * 
 * @author Aaron
 * @date 2021-12-13
 */

public interface PsBasicInfoHisMapper 
{
    /**
     * 新增基本信息历史
     * 
     * @param psBasicInfoHis 基本信息历史
     * @return 结果
     */
    public int insertPsBasicInfoHis(PsBasicInfoHis psBasicInfoHis);

    /**
     * 修改基本信息历史
     * 
     * @param psBasicInfoHis 基本信息历史
     * @return 结果
     */
    public int updatePsBasicInfoHis(PsBasicInfoHis psBasicInfoHis);


    /**
     * 检验时查询ps_basic_info列表
     *
     * @param psBasicInfoHis ps_basic_info
     * @return ps_basic_info集合
     */
    public List<PsBasicInfoHis> selectPsBasicInfoCheckList(PsBasicInfoHis psBasicInfoHis);
}
