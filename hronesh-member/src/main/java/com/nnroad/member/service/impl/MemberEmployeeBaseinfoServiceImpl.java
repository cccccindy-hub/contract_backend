package com.nnroad.member.service.impl;

import java.util.List;

import com.nnroad.member.domain.MemberEmployeeOffboard;
import com.nnroad.member.domain.MemberEmployeeOtherinfo;
import com.nnroad.member.mapper.MemberEmployeeOffboardMapper;
import com.nnroad.member.mapper.MemberEmployeeOtherinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnroad.member.mapper.MemberEmployeeBaseinfoMapper;
import com.nnroad.member.domain.MemberEmployeeBaseinfo;
import com.nnroad.member.service.IMemberEmployeeBaseinfoService;
import org.springframework.transaction.annotation.Transactional;

/**
 * MemberEmployeeBaseinfoService业务层处理
 * 
 * @author hgr
 * @date 2024-12-26
 */
@Service
public class MemberEmployeeBaseinfoServiceImpl implements IMemberEmployeeBaseinfoService {
    @Autowired
    private MemberEmployeeBaseinfoMapper memberEmployeeBaseinfoMapper;

    @Autowired
    private MemberEmployeeOffboardMapper memberEmployeeOffboardMapper;

    @Autowired
    private MemberEmployeeOtherinfoMapper memberEmployeeOtherinfoMapper;

    /**
     * 查询
     */
    @Override
    public MemberEmployeeBaseinfo selectMemberEmployeeBaseinfoById(Long id) {
        return memberEmployeeBaseinfoMapper.selectMemberEmployeeBaseinfoById(id);
    }

    @Override
    public MemberEmployeeBaseinfo selectMemberEmployeeBaseinfoByEecode(String eeCode) {
        return memberEmployeeBaseinfoMapper.selectMemberEmployeeBaseinfoByEecode(eeCode);
    }

    /**
     * 查询MemberEmployeeBaseinfo列表
     * 
     * @param memberEmployeeBaseinfo
     * @return 集合
     */
    @Override
    public List<MemberEmployeeBaseinfo> selectMemberEmployeeBaseinfoList(MemberEmployeeBaseinfo memberEmployeeBaseinfo) {
        return memberEmployeeBaseinfoMapper.selectMemberEmployeeBaseinfoList(memberEmployeeBaseinfo);
    }

    /**
     * 新增
     * 
     * @param memberEmployeeBaseinfo
     * @return 结果
     */
    @Override
    public int insertMemberEmployeeBaseinfo(MemberEmployeeBaseinfo memberEmployeeBaseinfo) {
        return memberEmployeeBaseinfoMapper.insertMemberEmployeeBaseinfo(memberEmployeeBaseinfo);
    }

    /**
     * 修改
     * @param memberEmployeeBaseinfo
     * @return 结果
     */
    @Override
    public int updateMemberEmployeeBaseinfo(MemberEmployeeBaseinfo memberEmployeeBaseinfo) {
        return memberEmployeeBaseinfoMapper.updateMemberEmployeeBaseinfo(memberEmployeeBaseinfo);
    }

    /**
     * 离职
     * @param memberEmployeeOffboard
     * @return
     */
    @Transactional(rollbackFor = IllegalArgumentException.class)
    public int updateAndInsertOffboard(MemberEmployeeOffboard memberEmployeeOffboard) {
        MemberEmployeeBaseinfo employeeBaseinfo = memberEmployeeBaseinfoMapper.selectMemberEmployeeBaseinfoByEecode(memberEmployeeOffboard.getEeCode());
        if (employeeBaseinfo == null) {
            // 员工不存在，抛出IllegalArgumentException异常
            throw new IllegalArgumentException("员工编码为 " + memberEmployeeOffboard.getEeCode() + " 的员工不存在");
        }
        // 更新员工状态为离职
        employeeBaseinfo.setStatus("0");
        memberEmployeeBaseinfoMapper.updateMemberEmployeeBaseinfo(employeeBaseinfo);
        // 插入离职信息
        int result = memberEmployeeOffboardMapper.insertMemberEmployeeOffboard(memberEmployeeOffboard);
        if (result <= 0) {
            // 插入失败，由于@Transactional注解，事务将回滚，员工状态不会改变
            throw new RuntimeException("插入离职信息失败");
        }
        return result;
    }

    /**
     * 删除未入职
     */
    public int deleteMemberEmployeeBaseinfoByEecode(String eeCode) {
        int number = 0;
        number += memberEmployeeBaseinfoMapper.deleteMemberEmployeeBaseinfoByEecode(eeCode);
        number += memberEmployeeOtherinfoMapper.deleteMemberEmployeeOtherinfoByEecode(eeCode);
        return number;
    }

    /**
     * 批量删除
     *
     * @return 结果
     */
    @Override
    public int deleteMemberEmployeeBaseinfoByIds(Long[] ids) {
        return memberEmployeeBaseinfoMapper.deleteMemberEmployeeBaseinfoByIds(ids);
    }

    /**
     * 删除信息
     *
     * @return 结果
     */
    @Override
    public int deleteMemberEmployeeBaseinfoById(Long id) {
        return memberEmployeeBaseinfoMapper.deleteMemberEmployeeBaseinfoById(id);
    }
}
