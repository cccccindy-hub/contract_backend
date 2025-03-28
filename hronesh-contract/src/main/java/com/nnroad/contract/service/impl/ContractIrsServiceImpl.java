package com.nnroad.contract.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnroad.contract.mapper.ContractIrsMapper;
import com.nnroad.contract.domain.ContractIrs;
import com.nnroad.contract.service.IContractIrsService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
@Service
public class ContractIrsServiceImpl implements IContractIrsService 
{
    @Autowired
    private ContractIrsMapper contractIrsMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public ContractIrs selectContractIrsByUserId(String userId)
    {
        return contractIrsMapper.selectContractIrsByUserId(userId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contractIrs 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ContractIrs> selectContractIrsList(ContractIrs contractIrs)
    {
        return contractIrsMapper.selectContractIrsList(contractIrs);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param contractIrs 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertContractIrs(ContractIrs contractIrs)
    {
        return contractIrsMapper.insertContractIrs(contractIrs);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param contractIrs 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateContractIrs(ContractIrs contractIrs)
    {
        return contractIrsMapper.updateContractIrs(contractIrs);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteContractIrsByUserIds(String[] userIds)
    {
        return contractIrsMapper.deleteContractIrsByUserIds(userIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteContractIrsByUserId(String userId)
    {
        return contractIrsMapper.deleteContractIrsByUserId(userId);
    }
}
