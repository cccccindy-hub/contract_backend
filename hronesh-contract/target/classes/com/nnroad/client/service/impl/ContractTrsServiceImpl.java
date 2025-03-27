package com.nnroad.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnroad.system.mapper.ContractTrsMapper;
import com.nnroad.system.domain.ContractTrs;
import com.nnroad.system.service.IContractTrsService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
@Service
public class ContractTrsServiceImpl implements IContractTrsService 
{
    @Autowired
    private ContractTrsMapper contractTrsMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public ContractTrs selectContractTrsByUserId(String userId)
    {
        return contractTrsMapper.selectContractTrsByUserId(userId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contractTrs 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ContractTrs> selectContractTrsList(ContractTrs contractTrs)
    {
        return contractTrsMapper.selectContractTrsList(contractTrs);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param contractTrs 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertContractTrs(ContractTrs contractTrs)
    {
        return contractTrsMapper.insertContractTrs(contractTrs);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param contractTrs 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateContractTrs(ContractTrs contractTrs)
    {
        return contractTrsMapper.updateContractTrs(contractTrs);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteContractTrsByUserIds(String[] userIds)
    {
        return contractTrsMapper.deleteContractTrsByUserIds(userIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteContractTrsByUserId(String userId)
    {
        return contractTrsMapper.deleteContractTrsByUserId(userId);
    }
}
