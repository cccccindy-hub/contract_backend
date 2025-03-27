package com.nnroad.payroll.mapper;

import com.nnroad.payroll.domain.ClientEmployeeCalculate;
import com.nnroad.payroll.domain.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ClientEmployeeCalculateMapper {
    public ClientEmployeeCalculate selectCalculate(@Param("eeCode")String eeCode);

}
