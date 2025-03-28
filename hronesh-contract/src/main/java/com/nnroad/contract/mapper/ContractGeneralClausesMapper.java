package com.nnroad.contract.mapper;

import java.util.List;
import com.nnroad.contract.domain.ContractGeneralClauses;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public interface ContractGeneralClausesMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param userid 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public ContractGeneralClauses selectContractGeneralClausesByUserid(String userid);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contractGeneralClauses 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ContractGeneralClauses> selectContractGeneralClausesList(ContractGeneralClauses contractGeneralClauses);

    /**
     * 新增【请填写功能名称】
     * 
     * @param contractGeneralClauses 【请填写功能名称】
     * @return 结果
     */
    public int insertContractGeneralClauses(ContractGeneralClauses contractGeneralClauses);

    /**
     * 修改【请填写功能名称】
     * 
     * @param contractGeneralClauses 【请填写功能名称】
     * @return 结果
     */
    public int updateContractGeneralClauses(ContractGeneralClauses contractGeneralClauses);

    /**
     * 删除【请填写功能名称】
     * 
     * @param userid 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteContractGeneralClausesByUserid(String userid);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteContractGeneralClausesByUserids(String[] userids);
}

