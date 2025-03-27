package com.nnroad.payroll.service;



import com.nnroad.payroll.domain.common.PsBasicInfo;

import java.util.List;

/**
 * ps_basic_infoService接口
 * 
 * @author Hrone
 * @date 2021-01-17
 */
public interface IPsBasicInfoService 
{


    /**
     * 检查ps_basic_info导入数据
     *
     * @param psBasicInfoList ps_basic_info数据
     */
    public void checkRequire(List<PsBasicInfo> psBasicInfoList);

    /**
     * 删除ps_basic_info信息
     *
     * @param duration 年月
     * @param clientCodes 客户编号列表
     * @return 结果
     */
    public void deletePaymentNotice(String duration, List<String> clientCodes);

    /**
     * 导入ps_basic_info信息
     * @param psBasicInfoList 基本信息数据
     * @param isUpdateSupport 是否更新
     * @param duration 年月
     * @return 结果
     */
    public String importPsBasicInfo(List<PsBasicInfo> psBasicInfoList, boolean isUpdateSupport, String duration);


    public String SelectRealName(String IdNo,String duration);

    /**
     * 查询ps_basic_info列表
     *
     * @param psBasicInfo ps_basic_info
     * @return ps_basic_info集合
     */
    public List<PsBasicInfo> selectPsBasicInfoList(PsBasicInfo psBasicInfo);


}
