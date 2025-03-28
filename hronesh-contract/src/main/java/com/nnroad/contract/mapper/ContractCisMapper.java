package com.nnroad.system.mapper;

import java.util.List;
import com.nnroad.system.domain.ContractCis;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public interface ContractCisMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public ContractCis selectContractCisByUserId(String userId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contractCis 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ContractCis> selectContractCisList(ContractCis contractCis);

    /**
     * 新增【请填写功能名称】
     * 
     * @param contractCis 【请填写功能名称】
     * @return 结果
     */
    public int insertContractCis(ContractCis contractCis);

    /**
     * 修改【请填写功能名称】
     * 
     * @param contractCis 【请填写功能名称】
     * @return 结果
     */
    public int updateContractCis(ContractCis contractCis);

    /**
     * 删除【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteContractCisByUserId(String userId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteContractCisByUserIds(String[] userIds);
}
