package com.nnroad.member.service;

import com.nnroad.member.domain.MemberEmployeeOtherinfo;

import java.util.List;

public interface IMemberEmployeeOtherinfoService {
    /**
     * 查询
     */
    public MemberEmployeeOtherinfo selectMemberEmployeeOtherinfoById(Long id);

    public MemberEmployeeOtherinfo selectMemberEmployeeOtherinfoByEecode(String eeCode);

    /**
     * 查询列表
     */
    public List<MemberEmployeeOtherinfo> selectMemberEmployeeOtherinfoList(MemberEmployeeOtherinfo memberEmployeeOtherinfo);

    /**
     * 新增
     */
    public int insertMemberEmployeeOtherinfo(MemberEmployeeOtherinfo memberEmployeeOtherinfo);

    /**
     * 修改
     */
    public int updateMemberEmployeeOtherinfo(MemberEmployeeOtherinfo memberEmployeeOtherinfo);

    /**
     * 批量删除
     */
    public int deleteMemberEmployeeOtherinfoByIds(Long[] ids);

    /**
     * 删除信息
     */
    public int deleteMemberEmployeeOtherinfoById(Long id);
}
