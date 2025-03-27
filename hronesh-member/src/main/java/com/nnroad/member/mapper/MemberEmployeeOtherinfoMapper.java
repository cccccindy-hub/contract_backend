package com.nnroad.member.mapper;

import com.nnroad.member.domain.MemberEmployeeOtherinfo;

import java.util.List;

public interface MemberEmployeeOtherinfoMapper {
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
     * 删除未入职
     */
    public int deleteMemberEmployeeOtherinfoByEecode(String eeCode);

    /**
     * 删除
     */
    public int deleteMemberEmployeeOtherinfoById(Long id);

    /**
     * 批量删除
     */
    public int deleteMemberEmployeeOtherinfoByIds(Long[] ids);
}
