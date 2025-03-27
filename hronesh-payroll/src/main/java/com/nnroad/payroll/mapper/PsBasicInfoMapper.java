package com.nnroad.payroll.mapper;


import com.nnroad.payroll.domain.common.PsBasicInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ps_basic_infoMapper接口
 * 
 * @author Hrone
 * @date 2021-01-17
 */

public interface PsBasicInfoMapper 
{

    /**
     * 查询ps_basic_info列表
     * 
     * @param psBasicInfo ps_basic_info
     * @return ps_basic_info集合
     */
    public List<PsBasicInfo> selectPsBasicInfoList(PsBasicInfo psBasicInfo);

    public PsBasicInfo getPsBasicInfo(PsBasicInfo psBasicInfo);


    /**
     * 新增ps_basic_info
     * 
     * @param psBasicInfo ps_basic_info
     * @return 结果
     */
    public int insertPsBasicInfo(PsBasicInfo psBasicInfo);

    /**
     * 修改ps_basic_info
     * 
     * @param psBasicInfo ps_basic_info
     * @return 结果
     */
    public int updatePsBasicInfo(PsBasicInfo psBasicInfo);

    /**
     * 删除ps_basic_info
     *
     * @param psBasicInfo ps_basic_info
     * @return 结果
     */
    public int deletePsBasicInfoByKey(PsBasicInfo psBasicInfo);

    /**
     * 检验时查询ps_basic_info列表
     *
     * @param psBasicInfo ps_basic_info
     * @return ps_basic_info集合
     */
    public List<PsBasicInfo> selectPsBasicInfoCheckList(PsBasicInfo psBasicInfo);

   public String selectRealName(@Param("eeCode") String idNo, @Param("duration") String duration);
}
