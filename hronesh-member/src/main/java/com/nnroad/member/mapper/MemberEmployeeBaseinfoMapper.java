package com.nnroad.member.mapper;

import java.util.List;
import com.nnroad.member.domain.MemberEmployeeBaseinfo;
import com.nnroad.member.domain.MemberEmployeeOffboard;
import com.nnroad.member.domain.MemberEmployeeOtherinfo;

/**
 * MemberEmployeeBaseinfoMapper接口
 * 
 * @author hgr
 * @date 2024-12-26
 */
public interface MemberEmployeeBaseinfoMapper {
    /**
     * 查询 MemberEmployeeBaseinfo
     */
    public MemberEmployeeBaseinfo selectMemberEmployeeBaseinfoById(Long id);


    /**
     * 查询MemberEmployeeBaseinfo列表
     * 
     * @param memberEmployeeBaseinfo
     * @return 集合
     */
    public List<MemberEmployeeBaseinfo> selectMemberEmployeeBaseinfoList(MemberEmployeeBaseinfo memberEmployeeBaseinfo);

    /**
     * 新增
     * 
     * @param memberEmployeeBaseinfo
     * @return 结果
     */
    public int insertMemberEmployeeBaseinfo(MemberEmployeeBaseinfo memberEmployeeBaseinfo);

    /**
     * 修改
     * 
     * @param memberEmployeeBaseinfo
     * @return 结果
     */
    public int updateMemberEmployeeBaseinfo(MemberEmployeeBaseinfo memberEmployeeBaseinfo);

    /**
     * 离职
     */
    public MemberEmployeeBaseinfo selectMemberEmployeeBaseinfoByEecode(String eeCode);

    /**
     * 删除未入职
     */
    public int deleteMemberEmployeeBaseinfoByEecode(String eeCode);

    /**
     * 删除
     *
     * @return 结果
     */
    public int deleteMemberEmployeeBaseinfoById(Long id);

    /**
     * 批量删除kk
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMemberEmployeeBaseinfoByIds(Long[] ids);
}
