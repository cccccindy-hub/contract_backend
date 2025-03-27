package com.nnroad.employee.service;

import com.nnroad.employee.domain.EmployeeInfo;
import com.nnroad.employee.domain.SysEmployee;

public interface EmployeeSyncService {

    void checkVendorSync(SysEmployee employee, String tableType);

    void checkClientSync(SysEmployee employee, String tableType);
}
