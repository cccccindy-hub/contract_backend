package com.nnroad.contract.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnroad.contract.mapper.ContractEorsMapper;
import com.nnroad.contract.domain.ContractEors;
import com.nnroad.contract.service.IContractEorsService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
@Service
public class ContractEorsServiceImpl implements IContractEorsService 
{
    @Autowired
    private ContractEorsMapper ContractEorsMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public ContractEors selectContractEorsByUserId(String userId)
    {
        return ContractEorsMapper.selectContractEorsByUserId(userId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ContractEors 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ContractEors> selectContractEorsList(ContractEors ContractEors)
    {
        return ContractEorsMapper.selectContractEorsList(ContractEors);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ContractEors 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertContractEors(ContractEors ContractEors)
    {
        return ContractEorsMapper.insertContractEors(ContractEors);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ContractEors 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateContractEors(ContractEors ContractEors)
    {
        return ContractEorsMapper.updateContractEors(ContractEors);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteContractEorsByUserIds(String[] userIds)
    {
        return ContractEorsMapper.deleteContractEorsByUserIds(userIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteContractEorsByUserId(String userId)
    {
        return ContractEorsMapper.deleteContractEorsByUserId(userId);
    }
}
