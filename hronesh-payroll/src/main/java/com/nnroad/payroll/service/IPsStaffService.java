package com.nnroad.payroll.service;

import com.nnroad.payroll.domain.Staff;

import java.util.List;

public interface IPsStaffService {
    Staff selectStaff(String idNo);

    List<String> selectStaffByCodeAndIdNum(String clientCode, String idNum);

    List<Staff> selectAllStaff();

}
