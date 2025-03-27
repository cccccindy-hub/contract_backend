package com.nnroad.payroll.service;

import com.nnroad.payroll.domain.TEEPayslip;
import com.nnroad.payroll.domain.common.*;

import java.util.List;


public interface ISendPaymentService {


    int SendPsBasicInfo(PsBasicInfo psBasicInfo,String servicePartB,String dataSource);

    int SendPsCalculationResult(PsCalculationResult psCalculationResult,String servicePartB,String dataSource);

    int SendPsPayroll(PsPayroll psPayroll,PsPayslip psPayslip ,String servicePartB,String dataSource);

    int SendPsPayslip(PsPayslip psPayslip,String servicePartB,String dataSource);

    int SendPsCoverInfo(PsCoverInfo psCoverInfo,String servicePartB,String dataSource);

}

