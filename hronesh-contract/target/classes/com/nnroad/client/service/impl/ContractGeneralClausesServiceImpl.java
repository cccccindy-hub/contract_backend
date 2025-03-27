package com.nnroad.client.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnroad.system.mapper.ContractGeneralClausesMapper;
import com.nnroad.system.domain.ContractGeneralClauses;
import com.nnroad.system.service.IContractGeneralClausesService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
@Service
public class ContractGeneralClausesServiceImpl implements IContractGeneralClausesService 
{
    @Autowired
    private ContractGeneralClausesMapper contractGeneralClausesMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param userid 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public ContractGeneralClauses selectContractGeneralClausesByUserid(String userid)
    {
        return contractGeneralClausesMapper.selectContractGeneralClausesByUserid(userid);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contractGeneralClauses 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ContractGeneralClauses> selectContractGeneralClausesList(ContractGeneralClauses contractGeneralClauses)
    {
        return contractGeneralClausesMapper.selectContractGeneralClausesList(contractGeneralClauses);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param contractGeneralClauses 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertContractGeneralClauses(ContractGeneralClauses contractGeneralClauses)
    {
        return contractGeneralClausesMapper.insertContractGeneralClauses(contractGeneralClauses);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param contractGeneralClauses 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateContractGeneralClauses(ContractGeneralClauses contractGeneralClauses)
    {
        return contractGeneralClausesMapper.updateContractGeneralClauses(contractGeneralClauses);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteContractGeneralClausesByUserids(String[] userids)
    {
        return contractGeneralClausesMapper.deleteContractGeneralClausesByUserids(userids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userid 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteContractGeneralClausesByUserid(String userid)
    {
        return contractGeneralClausesMapper.deleteContractGeneralClausesByUserid(userid);
    }
}
