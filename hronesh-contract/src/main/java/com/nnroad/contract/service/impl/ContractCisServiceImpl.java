package com.nnroad.contract.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnroad.contract.mapper.ContractCisMapper;
import com.nnroad.contract.domain.ContractCis;
import com.nnroad.contract.service.IContractCisService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
@Service
public class ContractCisServiceImpl implements IContractCisService 
{
    @Autowired
    private ContractCisMapper contractCisMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public ContractCis selectContractCisByUserId(String userId)
    {
        return contractCisMapper.selectContractCisByUserId(userId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contractCis 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ContractCis> selectContractCisList(ContractCis contractCis)
    {
        return contractCisMapper.selectContractCisList(contractCis);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param contractCis 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertContractCis(ContractCis contractCis)
    {
        return contractCisMapper.insertContractCis(contractCis);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param contractCis 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateContractCis(ContractCis contractCis)
    {
        return contractCisMapper.updateContractCis(contractCis);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteContractCisByUserIds(String[] userIds)
    {
        return contractCisMapper.deleteContractCisByUserIds(userIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteContractCisByUserId(String userId)
    {
        return contractCisMapper.deleteContractCisByUserId(userId);
    }
}
