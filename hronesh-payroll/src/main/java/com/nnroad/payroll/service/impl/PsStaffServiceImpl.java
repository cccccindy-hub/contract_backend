package com.nnroad.payroll.service.impl;


import com.nnroad.payroll.domain.Staff;
import com.nnroad.payroll.mapper.PsStaffMapper;
import com.nnroad.payroll.service.IPsStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PsStaffServiceImpl implements IPsStaffService {

    @Autowired
    private PsStaffMapper psStaffMapper;

    @Override
    public Staff selectStaff(String idNo) {

        return psStaffMapper.selectStaff(idNo);
    }

    @Override
    public List<String> selectStaffByCodeAndIdNum(String clientCode, String idNum) {
        return psStaffMapper.selectStaffByCodeAndIdNum(clientCode,idNum);
    }

    @Override
    public List<Staff> selectAllStaff() {
        return psStaffMapper.selectAllStaff();
    }
}
