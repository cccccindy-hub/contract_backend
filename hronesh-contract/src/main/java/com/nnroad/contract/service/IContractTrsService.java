package com.nnroad.contract.service;

import java.util.List;
import com.nnroad.contract.domain.ContractTrs;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public interface IContractTrsService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public ContractTrs selectContractTrsByUserId(String userId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contractTrs 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ContractTrs> selectContractTrsList(ContractTrs contractTrs);

    /**
     * 新增【请填写功能名称】
     * 
     * @param contractTrs 【请填写功能名称】
     * @return 结果
     */
    public int insertContractTrs(ContractTrs contractTrs);

    /**
     * 修改【请填写功能名称】
     * 
     * @param contractTrs 【请填写功能名称】
     * @return 结果
     */
    public int updateContractTrs(ContractTrs contractTrs);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteContractTrsByUserIds(String[] userIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteContractTrsByUserId(String userId);
}
