package com.nnroad.payroll.service;


import com.nnroad.payroll.domain.common.PsBasicUnfixedInfoHis;

import java.util.List;

/**
 * 变动信息信息Service接口
 * 
 * @author Aaron
 * @date 2021-12-13
 */
public interface IPsBasicUnfixedInfoHisService 
{

    String importPsBasicUnfixedInfo(List<PsBasicUnfixedInfoHis> psBasicUnfixedInfoHisList, boolean updateSupportBat, String duration, String groupIdsBat);
}
