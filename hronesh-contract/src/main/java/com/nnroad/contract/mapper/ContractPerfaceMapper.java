package com.nnroad.contract.mapper;

import java.util.List;
import com.nnroad.contract.domain.ContractPerface;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public interface ContractPerfaceMapper 
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
     * 删除【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteContractPerfaceByUserId(Long userId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteContractPerfaceByUserIds(Long[] userIds);
}

