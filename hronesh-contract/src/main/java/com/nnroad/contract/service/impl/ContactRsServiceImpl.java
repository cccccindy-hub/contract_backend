package com.nnroad.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnroad.system.mapper.ContactRsMapper;
import com.nnroad.system.domain.ContactRs;
import com.nnroad.system.service.IContactRsService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
@Service
public class ContactRsServiceImpl implements IContactRsService 
{
    @Autowired
    private ContactRsMapper contactRsMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public ContactRs selectContactRsByUserId(String userId)
    {
        return contactRsMapper.selectContactRsByUserId(userId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contactRs 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ContactRs> selectContactRsList(ContactRs contactRs)
    {
        return contactRsMapper.selectContactRsList(contactRs);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param contactRs 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertContactRs(ContactRs contactRs)
    {
        return contactRsMapper.insertContactRs(contactRs);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param contactRs 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateContactRs(ContactRs contactRs)
    {
        return contactRsMapper.updateContactRs(contactRs);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteContactRsByUserIds(String[] userIds)
    {
        return contactRsMapper.deleteContactRsByUserIds(userIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteContactRsByUserId(String userId)
    {
        return contactRsMapper.deleteContactRsByUserId(userId);
    }
}
