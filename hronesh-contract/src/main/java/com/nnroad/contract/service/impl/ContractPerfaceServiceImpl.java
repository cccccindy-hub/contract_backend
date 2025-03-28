package com.nnroad.contract.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnroad.contract.mapper.ContractPerfaceMapper;
import com.nnroad.contract.domain.ContractPerface;
import com.nnroad.contract.service.IContractPerfaceService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
@Service
public class ContractPerfaceServiceImpl implements IContractPerfaceService 
{
    @Autowired
    private ContractPerfaceMapper contractPerfaceMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public ContractPerface selectContractPerfaceByUserId(Long userId)
    {
        return contractPerfaceMapper.selectContractPerfaceByUserId(userId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contractPerface 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ContractPerface> selectContractPerfaceList(ContractPerface contractPerface)
    {
        return contractPerfaceMapper.selectContractPerfaceList(contractPerface);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param contractPerface 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertContractPerface(ContractPerface contractPerface)
    {
        return contractPerfaceMapper.insertContractPerface(contractPerface);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param contractPerface 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateContractPerface(ContractPerface contractPerface)
    {
        return contractPerfaceMapper.updateContractPerface(contractPerface);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteContractPerfaceByUserIds(Long[] userIds)
    {
        return contractPerfaceMapper.deleteContractPerfaceByUserIds(userIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteContractPerfaceByUserId(Long userId)
    {
        return contractPerfaceMapper.deleteContractPerfaceByUserId(userId);
    }
}
