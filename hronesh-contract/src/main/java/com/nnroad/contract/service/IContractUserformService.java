// package com.ruoyi.system.service;
package com.nnroad.client.service;

import com.nnroad.client.domain.SysClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import com.nnroad.client.domain.ContractUserform; // Ensure this is the correct package for ContractUserform
import java.util.List;


/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2025-03-25
 */
public interface IContractUserformService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public ContractUserform selectContractUserformByUserId(String userId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contractUserform 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ContractUserform> selectContractUserformList(ContractUserform contractUserform);

    /**
     * 新增【请填写功能名称】
     * 
     * @param contractUserform 【请填写功能名称】
     * @return 结果
     */
    public int insertContractUserform(ContractUserform contractUserform);

    /**
     * 修改【请填写功能名称】
     * 
     * @param contractUserform 【请填写功能名称】
     * @return 结果
     */
    public int updateContractUserform(ContractUserform contractUserform);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteContractUserformByUserIds(String[] userIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteContractUserformByUserId(String userId);
}
