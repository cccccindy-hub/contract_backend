package com.nnroad.quartz.task;

import com.nnroad.quartz.service.ISysEmployeeTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.nnroad.common.constant.ScheduleConstants;
import org.springframework.stereotype.Component;

@Component("employeeTask")
public class EmployeeTask {

    @Value("${sys.quartzMode}")
    private String quartzMode;

    @Autowired
    ISysEmployeeTaskService employeeTaskService;

    public void updateOffBoardEmployeeStatus() {
        if (quartzMode.equals(ScheduleConstants.MODE_ENABLE)) {
            int count = employeeTaskService.updateEmployeeStatus();
            System.out.println("已修改" + count + "条雇员信息！");
        }
    }
}
