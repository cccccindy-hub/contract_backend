package com.nnroad.contract.mapper;

import java.util.List;
import com.nnroad.contract.domain.ContractEors;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public interface ContractEorsMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public ContractEors selectContactEorsByUserId(String userId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contactEors 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ContractEors> selectContactEorsList(ContractEors contactEors);

    /**
     * 新增【请填写功能名称】
     * 
     * @param contactEors 【请填写功能名称】
     * @return 结果
     */
    public int insertContactEors(ContractEors contactEors);

    /**
     * 修改【请填写功能名称】
     * 
     * @param contactEors 【请填写功能名称】
     * @return 结果
     */
    public int updateContactEors(ContractEors contactEors);

    /**
     * 删除【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteContactEorsByUserId(String userId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteContactEorsByUserIds(String[] userIds);
}
