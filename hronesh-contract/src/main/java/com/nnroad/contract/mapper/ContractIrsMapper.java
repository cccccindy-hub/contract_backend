package com.nnroad.system.mapper;

import java.util.List;
import com.nnroad.system.domain.ContractIrs;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public interface ContractIrsMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public ContractIrs selectContractIrsByUserId(String userId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contractIrs 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ContractIrs> selectContractIrsList(ContractIrs contractIrs);

    /**
     * 新增【请填写功能名称】
     * 
     * @param contractIrs 【请填写功能名称】
     * @return 结果
     */
    public int insertContractIrs(ContractIrs contractIrs);

    /**
     * 修改【请填写功能名称】
     * 
     * @param contractIrs 【请填写功能名称】
     * @return 结果
     */
    public int updateContractIrs(ContractIrs contractIrs);

    /**
     * 删除【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteContractIrsByUserId(String userId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteContractIrsByUserIds(String[] userIds);
}
