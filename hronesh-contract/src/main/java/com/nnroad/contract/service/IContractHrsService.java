package com.nnroad.contract.service;

import java.util.List;
import com.nnroad.contract.domain.ContractHrs;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public interface IContractHrsService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public ContractHrs selectContractHrsByUserId(String userId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contractHrs 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ContractHrs> selectContractHrsList(ContractHrs contractHrs);

    /**
     * 新增【请填写功能名称】
     * 
     * @param contractHrs 【请填写功能名称】
     * @return 结果
     */
    public int insertContractHrs(ContractHrs contractHrs);

    /**
     * 修改【请填写功能名称】
     * 
     * @param contractHrs 【请填写功能名称】
     * @return 结果
     */
    public int updateContractHrs(ContractHrs contractHrs);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteContractHrsByUserIds(String[] userIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteContractHrsByUserId(String userId);
}
