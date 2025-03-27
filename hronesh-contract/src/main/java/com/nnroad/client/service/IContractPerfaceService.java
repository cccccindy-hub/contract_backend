package com.nnroad.client.domain;

import java.util.List;
import com.nnroad.client.domain.ContractPerface;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public interface IContractPerfaceService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public ContractPerface selectContractPerfaceByUserId(Long userId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contractPerface 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ContractPerface> selectContractPerfaceList(ContractPerface contractPerface);

    /**
     * 新增【请填写功能名称】
     * 
     * @param contractPerface 【请填写功能名称】
     * @return 结果
     */
    public int insertContractPerface(ContractPerface contractPerface);

    /**
     * 修改【请填写功能名称】
     * 
     * @param contractPerface 【请填写功能名称】
     * @return 结果
     */
    public int updateContractPerface(ContractPerface contractPerface);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteContractPerfaceByUserIds(Long[] userIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteContractPerfaceByUserId(Long userId);
}
