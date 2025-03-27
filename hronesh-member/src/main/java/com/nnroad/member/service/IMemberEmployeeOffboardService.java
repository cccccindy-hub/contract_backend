package com.nnroad.member.service;

import com.nnroad.member.domain.MemberEmployeeOffboard;

import java.util.List;

public interface IMemberEmployeeOffboardService {
    /**
     * 查询
     */
    public MemberEmployeeOffboard selectMemberEmployeeOffboardById(Long id);

    public MemberEmployeeOffboard selectMemberEmployeeOffboardByEecode(String eeCode);

    /**
     * 查询列表
     */
    public List<MemberEmployeeOffboard> selectMemberEmployeeOffboardList(MemberEmployeeOffboard memberEmployeeOffboard);

    /**
     * 新增
     */
    public int insertMemberEmployeeOffboard(MemberEmployeeOffboard memberEmployeeOffboard);

    /**
     * 修改
     */
    public int updateMemberEmployeeOffboard(MemberEmployeeOffboard memberEmployeeOffboard);

    /**
     * 批量删除
     */
    public int deleteMemberEmployeeOffboardByIds(Long[] ids);

    /**
     * 删除
     */
    public int deleteMemberEmployeeOffboardById(Long id);
}
