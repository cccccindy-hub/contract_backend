package com.nnroad.employee.mapper;

import com.nnroad.common.annotation.DataSource;
import com.nnroad.common.enums.DataSourceType;
import com.nnroad.employee.domain.SysEmployee;

@DataSource(value = DataSourceType.HROneHK)
public interface getDataSyncFromHRHKMapper {

    public SysEmployee getEmployeeInfoByCode(String code);
}
