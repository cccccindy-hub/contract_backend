package com.nnroad.payroll.mapper;

import com.nnroad.payroll.domain.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PsStaffMapper {
    Staff selectStaff(String eeCode);

    List<String> selectStaffByCodeAndIdNum(@Param("clientCode") String ClientCode, @Param("idNum") String IdNum);

    List<Staff> selectAllStaff();

}
