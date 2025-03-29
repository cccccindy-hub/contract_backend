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
    private ContractEorsMapper contactEorsMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public ContractEors selectContactEorsByUserId(String userId)
    {
        return contactEorsMapper.selectContactEorsByUserId(userId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contactEors 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ContractEors> selectContactEorsList(ContractEors contactEors)
    {
        return contactEorsMapper.selectContactEorsList(contactEors);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param contactEors 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertContactEors(ContractEors contactEors)
    {
        return contactEorsMapper.insertContactEors(contactEors);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param contactEors 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateContactEors(ContractEors contactEors)
    {
        return contactEorsMapper.updateContactEors(contactEors);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteContactEorsByUserIds(String[] userIds)
    {
        return contactEorsMapper.deleteContactEorsByUserIds(userIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteContactEorsByUserId(String userId)
    {
        return contactEorsMapper.deleteContactEorsByUserId(userId);
    }
}
