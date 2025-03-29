package com.nnroad.contract.service;

import java.util.List;
import com.nnroad.contract.domain.ContractEors;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public interface IContractEorsService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public ContractEors selectContractEorsByUserId(String userId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ContractEors 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ContractEors> selectContractEorsList(ContractEors ContractEors);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ContractEors 【请填写功能名称】
     * @return 结果
     */
    public int insertContractEors(ContractEors ContractEors);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ContractEors 【请填写功能名称】
     * @return 结果
     */
    public int updateContractEors(ContractEors ContractEors);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteContractEorsByUserIds(String[] userIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteContractEorsByUserId(String userId);
}
