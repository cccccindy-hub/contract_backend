package com.nnroad.quartz.service.impl;

import com.nnroad.common.utils.DateUtils;
import com.nnroad.employee.constants.enums.EEStatus;
import com.nnroad.employee.domain.SysEmployee;
import com.nnroad.employee.mapper.EmployeeInfoMapper;
import com.nnroad.employee.mapper.SysEmployeeMapper;
import com.nnroad.quartz.service.ISysEmployeeTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service
public class SysEmployeeTaskServiceImpl implements ISysEmployeeTaskService {

    @Autowired
    SysEmployeeMapper employeeMapper;

    @Override
    public int updateEmployeeStatus() {
        SysEmployee employeeDto = new SysEmployee();
        //get all active employees
        employeeDto.setStatus(EEStatus.ONGOING.getStatus());
        List<SysEmployee> employeeDtoList = employeeMapper.selectSysEmployeeList(employeeDto);
        int count=0;
        for (SysEmployee employee : employeeDtoList) {
            // get employee off board date from extra data
            if (((Map<String, Object>)employee.getExtraData()).get("230") != null){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String todayDate = dateFormat.format(DateUtils.getNowDate());
                String offboardDate = (String) ((Map<String, Object>)employee.getExtraData()).get("230");
                // Extract the first 10 characters (yyyy-MM-dd)
                if (offboardDate.length() >= 10) {
                    offboardDate = offboardDate.substring(0, 10);
                }
                //off board employee if off board date is greater than current date
                if (offboardDate.compareTo(todayDate)<0){
                    employee.setStatus(EEStatus.OFF_BOARD.getStatus());
                    count=+employeeMapper.updateSysEmployee(employee);
                }
            }
        }
        return count;
    }
}
