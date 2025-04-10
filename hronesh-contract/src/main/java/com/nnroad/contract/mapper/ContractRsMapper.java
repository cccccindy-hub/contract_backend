package com.nnroad.contract.mapper;

import java.util.List;
import com.nnroad.contract.domain.ContractRs;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public interface ContractRsMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public ContractRs selectContactRsByUserId(String userId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contactRs 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ContractRs> selectContactRsList(ContractRs contactRs);

    /**
     * 新增【请填写功能名称】
     * 
     * @param contactRs 【请填写功能名称】
     * @return 结果
     */
    public int insertContactRs(ContractRs contactRs);

    /**
     * 修改【请填写功能名称】
     * 
     * @param contactRs 【请填写功能名称】
     * @return 结果
     */
    public int updateContactRs(ContractRs contactRs);

    /**
     * 删除【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteContactRsByUserId(String userId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteContactRsByUserIds(String[] userIds);
}
