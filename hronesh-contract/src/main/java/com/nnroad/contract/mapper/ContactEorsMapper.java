package com.nnroad.system.mapper;

import java.util.List;
import com.nnroad.system.domain.ContactEors;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public interface ContactEorsMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public ContactEors selectContactEorsByUserId(String userId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contactEors 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ContactEors> selectContactEorsList(ContactEors contactEors);

    /**
     * 新增【请填写功能名称】
     * 
     * @param contactEors 【请填写功能名称】
     * @return 结果
     */
    public int insertContactEors(ContactEors contactEors);

    /**
     * 修改【请填写功能名称】
     * 
     * @param contactEors 【请填写功能名称】
     * @return 结果
     */
    public int updateContactEors(ContactEors contactEors);

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
