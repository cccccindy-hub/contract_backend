package com.nnroad.payroll.service;



import com.nnroad.payroll.domain.common.PsLeave;

import java.util.List;

/**
 * leaveService接口
 * 
 * @author Aaron
 * @date 2021-11-30
 */
public interface IPsLeaveService 
{


    /**
     * 查询leave列表
     * 
     * @param psLeave leave
     * @return leave集合
     */
    public List<PsLeave> selectPsLeaveList(PsLeave psLeave);

    /**
     * 新增leave
     * 
     * @param psLeave leave
     * @return 结果
     */
    public int insertPsLeave(PsLeave psLeave);



    /**
     * 批量删除leave
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePsLeaveByIds(List<Long> ids);



}
