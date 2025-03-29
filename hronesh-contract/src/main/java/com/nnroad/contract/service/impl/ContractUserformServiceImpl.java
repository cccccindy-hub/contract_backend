package com.nnroad.contract.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnroad.contract.mapper.ContractUserformMapper;
import com.nnroad.contract.domain.ContractUserform;
import com.nnroad.contract.service.IContractUserformService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-29
 */
@Service
public class ContractUserformServiceImpl implements IContractUserformService 
{
    @Autowired
    private ContractUserformMapper contractUserformMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public ContractUserform selectContractUserformByUserId(String userId)
    {
        return contractUserformMapper.selectContractUserformByUserId(userId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contractUserform 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ContractUserform> selectContractUserformList(ContractUserform contractUserform)
    {
        return contractUserformMapper.selectContractUserformList(contractUserform);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param contractUserform 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertContractUserform(ContractUserform contractUserform)
    {
        return contractUserformMapper.insertContractUserform(contractUserform);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param contractUserform 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateContractUserform(ContractUserform contractUserform)
    {
        return contractUserformMapper.updateContractUserform(contractUserform);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteContractUserformByUserIds(String[] userIds)
    {
        return contractUserformMapper.deleteContractUserformByUserIds(userIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteContractUserformByUserId(String userId)
    {
        return contractUserformMapper.deleteContractUserformByUserId(userId);
    }
}
