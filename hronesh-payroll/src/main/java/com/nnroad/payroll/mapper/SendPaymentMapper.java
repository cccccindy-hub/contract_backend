package com.nnroad.payroll.mapper;


import com.nnroad.common.annotation.DataSource;
import com.nnroad.common.enums.DataSourceType;
import com.nnroad.payroll.domain.common.*;

import java.util.List;


@DataSource(value = DataSourceType.HROneHK)
public interface SendPaymentMapper
{

//    public List<PsBasicInfo> selectPsBasicInfoCheckList(PsBasicInfo psBasicInfo);
    public PsBasicInfo selectPsBasicInfo(PsBasicInfo psBasicInfo);
    public int insertPsBasicInfo(PsBasicInfo psBasicInfo);
    public int updatePsBasicInfo(PsBasicInfo psBasicInfo);


    /*检查PsCalculationResult*/
//    public List<PsCalculationResult> selectPsCalculationResultCheckList(PsCalculationResult psCalculationResult);
    public PsCalculationResult selectPsCalculationResult(PsCalculationResult psCalculationResult);
    public int insertPsCalculationResult(PsCalculationResult psCalculationResult);
    public int updatePsCalculationResult(PsCalculationResult psCalculationResult);


    /*检查PsPayroll*/
//    public List<PsPayroll> selectPsPayrollList(PsPayroll psPayroll);
    public PsPayroll selectPsPayroll(PsPayroll psPayroll);
    public PsPayroll selectVendorPsPayroll(PsPayroll psPayroll);
    public int insertPsPayroll(PsPayroll psPayroll);
    public int insertVendorPsPayroll(PsPayroll psPayroll);
    public int updatePsPayroll(PsPayroll psPayroll);
    public int updateVendorPsPayroll(PsPayroll psPayroll);

    /*检查PsPayslip*/
//    public List<PsPayslip> selectPsPayslipList(PsPayslip psPayslip);
    public PsPayslip selectPsPayslip(PsPayslip psPayslip);
    public int insertPsPayslip(PsPayslip psPayslip);
    public int updatePsPayslip(PsPayslip psPayslip);

    /*检查PsPayslip*/
    public List<PsCoverInfo> selectPsCoverInfoList(PsCoverInfo psCoverInfo);
    public int insertPsCoverInfo(PsCoverInfo psCoverInfo);
    public int deletePsCoverInfoByCondition(PsCoverInfo psCoverInfo);

}
