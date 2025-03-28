package com.nnroad.client.domain;

import java.util.List;
import com.nnroad.system.domain.ContractTrs;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public interface ContractTrsMapper 
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
     * 删除【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteContractTrsByUserId(String userId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteContractTrsByUserIds(String[] userIds);
}
