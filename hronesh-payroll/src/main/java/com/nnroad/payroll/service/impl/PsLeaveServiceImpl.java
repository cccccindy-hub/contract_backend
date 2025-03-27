package com.nnroad.payroll.service.impl;


import com.nnroad.common.utils.DateUtils;
import com.nnroad.common.utils.StringUtils;
import com.nnroad.payroll.domain.common.PsLeave;
import com.nnroad.payroll.mapper.PsLeaveMapper;
import com.nnroad.payroll.service.IPsLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * leaveService业务层处理
 * 
 * @author Aaron
 * @date 2021-11-30
 */
@Service
public class PsLeaveServiceImpl implements IPsLeaveService
{
    @Autowired
    private PsLeaveMapper psLeaveMapper;



    /**
     * 查询leave列表
     * 
     * @param psLeave leave
     * @return leave
     */
    @Override
    public List<PsLeave> selectPsLeaveList(PsLeave psLeave)
    {
        return psLeaveMapper.selectPsLeaveList(psLeave);
    }

    /**
     * 新增leave
     * 
     * @param psLeave leave
     * @return 结果
     */
    @Override
    public int insertPsLeave(PsLeave psLeave)
    {
        psLeave.setCreateTime(DateUtils.getNowDate());
        return psLeaveMapper.insertPsLeave(psLeave);
    }

    /**
     * 删除leave对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePsLeaveByIds(List<Long> ids)
    {
        return psLeaveMapper.deletePsLeaveByIds(ids);
    }

}
