package com.nnroad.member.service;

import java.util.List;
import com.nnroad.member.domain.MemberEmployeeBaseinfo;
import com.nnroad.member.domain.MemberEmployeeOffboard;
import com.nnroad.member.domain.MemberEmployeeOtherinfo;

/**
 * MemberEmployeeBaseinfoService接口
 * 
 * @author hgr
 * @date 2024-12-26
 */
public interface IMemberEmployeeBaseinfoService 
{
    /**
     * 查询
     */
    public MemberEmployeeBaseinfo selectMemberEmployeeBaseinfoById(Long id);

    public MemberEmployeeBaseinfo selectMemberEmployeeBaseinfoByEecode(String eeCode);

    /**
     * 查询
     */
    public List<MemberEmployeeBaseinfo> selectMemberEmployeeBaseinfoList(MemberEmployeeBaseinfo memberEmployeeBaseinfo);

    /**
     * 新增
     */
    public int insertMemberEmployeeBaseinfo(MemberEmployeeBaseinfo memberEmployeeBaseinfo);

    /**
     * 修改
     */
    public int updateMemberEmployeeBaseinfo(MemberEmployeeBaseinfo memberEmployeeBaseinfo);

    /**
     * 离职
     */
    public int updateAndInsertOffboard(MemberEmployeeOffboard memberEmployeeOffboard);

    /**
     * 未入职
     */
    public int deleteMemberEmployeeBaseinfoByEecode(String eeCode);

    /**
     * 批量删除
     *
     * @return 结果
     */
    public int deleteMemberEmployeeBaseinfoByIds(Long[] ids);

    /**
     * 删除
     *
     * @return 结果
     */
    public int deleteMemberEmployeeBaseinfoById(Long id);
}
