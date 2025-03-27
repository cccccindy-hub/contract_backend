package com.nnroad.member.service.impl;

import com.nnroad.member.domain.MemberEmployeeOffboard;
import com.nnroad.member.mapper.MemberEmployeeOffboardMapper;
import com.nnroad.member.service.IMemberEmployeeOffboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MemberEmployeeOffboardServiceImpl implements IMemberEmployeeOffboardService {

    @Autowired
    private MemberEmployeeOffboardMapper memberEmployeeOffboardMapper;

    @Override
    public MemberEmployeeOffboard selectMemberEmployeeOffboardById(Long id) {
        return memberEmployeeOffboardMapper.selectMemberEmployeeOffboardById(id);
    }

    @Override
    public MemberEmployeeOffboard selectMemberEmployeeOffboardByEecode(String eeCode) {
        return memberEmployeeOffboardMapper.selectMemberEmployeeOffboardByEecode(eeCode);
    }

    @Override
    public List<MemberEmployeeOffboard> selectMemberEmployeeOffboardList(MemberEmployeeOffboard memberEmployeeOffboard) {
        return memberEmployeeOffboardMapper.selectMemberEmployeeOffboardList(memberEmployeeOffboard);
    }

    @Override
    public int insertMemberEmployeeOffboard(MemberEmployeeOffboard memberEmployeeOffboard) {
        return memberEmployeeOffboardMapper.insertMemberEmployeeOffboard(memberEmployeeOffboard);
    }

    @Override
    public int updateMemberEmployeeOffboard(MemberEmployeeOffboard memberEmployeeOffboard) {
        return memberEmployeeOffboardMapper.updateMemberEmployeeOffboard(memberEmployeeOffboard);
    }

    @Override
    public int deleteMemberEmployeeOffboardByIds(Long[] ids) {
        return memberEmployeeOffboardMapper.deleteMemberEmployeeOffboardByIds(ids);
    }

    @Override
    public int deleteMemberEmployeeOffboardById(Long id) {
        return memberEmployeeOffboardMapper.deleteMemberEmployeeOffboardById(id);
    }
}
