package com.nnroad.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnroad.system.mapper.ContractHrsMapper;
import com.nnroad.system.domain.ContractHrs;
import com.nnroad.system.service.IContractHrsService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
@Service
public class ContractHrsServiceImpl implements IContractHrsService 
{
    @Autowired
    private ContractHrsMapper contractHrsMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public ContractHrs selectContractHrsByUserId(String userId)
    {
        return contractHrsMapper.selectContractHrsByUserId(userId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contractHrs 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ContractHrs> selectContractHrsList(ContractHrs contractHrs)
    {
        return contractHrsMapper.selectContractHrsList(contractHrs);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param contractHrs 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertContractHrs(ContractHrs contractHrs)
    {
        return contractHrsMapper.insertContractHrs(contractHrs);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param contractHrs 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateContractHrs(ContractHrs contractHrs)
    {
        return contractHrsMapper.updateContractHrs(contractHrs);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteContractHrsByUserIds(String[] userIds)
    {
        return contractHrsMapper.deleteContractHrsByUserIds(userIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteContractHrsByUserId(String userId)
    {
        return contractHrsMapper.deleteContractHrsByUserId(userId);
    }
}

