package com.nnroad.payroll.service;


import com.nnroad.payroll.domain.common.PsBasicInfoHis;

import java.util.List;

/**
 * 基本信息历史Service接口
 * 
 * @author Aaron
 * @date 2021-12-13
 */
public interface IPsBasicInfoHisService 
{

    /**
     * 导入ps_basic_info信息
     * @param psBasicInfoList 基本信息数据
     * @param isUpdateSupport 是否更新
     * @param duration 年月
     * @param leaderId 数据权限拥有者
     * @return 结果
     */
    public String importPsBasicInfo(List<PsBasicInfoHis> psBasicInfoList, boolean isUpdateSupport, String duration, String leaderId);
}
