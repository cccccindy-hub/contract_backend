package com.nnroad.payroll.service;


import com.nnroad.payroll.domain.ClientEmployeeCalculate;
import com.nnroad.payroll.domain.common.PsPayroll;

import java.util.List;

/**
 * ps_payrollService接口
 * 
 * @author Hrone
 * @date 2021-01-21
 */
public interface IClientEmployeeCalculateService
{
    public int selectCalculate(String idNO,PsPayroll psPayroll);


}
