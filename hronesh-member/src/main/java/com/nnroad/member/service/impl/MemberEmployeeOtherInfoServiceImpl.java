package com.nnroad.member.service.impl;

import com.nnroad.member.domain.MemberEmployeeOtherinfo;
import com.nnroad.member.mapper.MemberEmployeeOtherinfoMapper;
import com.nnroad.member.service.IMemberEmployeeOtherinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberEmployeeOtherInfoServiceImpl implements IMemberEmployeeOtherinfoService {
    @Autowired
    private MemberEmployeeOtherinfoMapper memberEmployeeOtherinfoMapper;

    /**
     * 查询
     */
    @Override
    public MemberEmployeeOtherinfo selectMemberEmployeeOtherinfoById(Long id)
    {
        return memberEmployeeOtherinfoMapper.selectMemberEmployeeOtherinfoById(id);
    }

    @Override
    public MemberEmployeeOtherinfo selectMemberEmployeeOtherinfoByEecode(String eeCode) {
        return memberEmployeeOtherinfoMapper.selectMemberEmployeeOtherinfoByEecode(eeCode);
    }

    /**
     * 查询列表
     */
    @Override
    public List<MemberEmployeeOtherinfo> selectMemberEmployeeOtherinfoList(MemberEmployeeOtherinfo memberEmployeeOtherinfo)
    {
        return memberEmployeeOtherinfoMapper.selectMemberEmployeeOtherinfoList(memberEmployeeOtherinfo);
    }

    /**
     * 新增
     */
    @Override
    public int insertMemberEmployeeOtherinfo(MemberEmployeeOtherinfo memberEmployeeOtherinfo)
    {
        return memberEmployeeOtherinfoMapper.insertMemberEmployeeOtherinfo(memberEmployeeOtherinfo);
    }

    /**
     * 修改
     */
    @Override
    public int updateMemberEmployeeOtherinfo(MemberEmployeeOtherinfo memberEmployeeOtherinfo) {
        MemberEmployeeOtherinfo m= memberEmployeeOtherinfoMapper.selectMemberEmployeeOtherinfoByEecode(memberEmployeeOtherinfo.getEeCode());
        if (m == null) {
            memberEmployeeOtherinfoMapper.insertMemberEmployeeOtherinfo(memberEmployeeOtherinfo);
        }
        return memberEmployeeOtherinfoMapper.updateMemberEmployeeOtherinfo(memberEmployeeOtherinfo);

    }

    /**
     * 批量删除
     */
    @Override
    public int deleteMemberEmployeeOtherinfoByIds(Long[] ids)
    {
        return memberEmployeeOtherinfoMapper.deleteMemberEmployeeOtherinfoByIds(ids);
    }

    /**
     * 删除信息
     */
    @Override
    public int deleteMemberEmployeeOtherinfoById(Long id)
    {
        return memberEmployeeOtherinfoMapper.deleteMemberEmployeeOtherinfoById(id);
    }
}
