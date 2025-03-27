package com.nnroad.payroll.service.impl;


import com.nnroad.common.utils.SecurityUtils;
import com.nnroad.common.utils.StringUtils;
import com.nnroad.framework.datasource.DynamicDataSourceContextHolder;
import com.nnroad.payroll.constants.PayrollSourceConstants;
import com.nnroad.payroll.domain.common.*;
import com.nnroad.payroll.domain.exportC.IITReport;
import com.nnroad.payroll.domain.exportC.StefaniniIITReport;
import com.nnroad.payroll.mapper.*;
import com.nnroad.payroll.service.IIITReportService;
import com.nnroad.payroll.service.ISendPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * iit_reportService业务层处理
 *
 * @author HROne
 * @date 2021-01-15
 */
@Service
public class SendPaymentImpl implements ISendPaymentService {

    @Autowired
    SendPaymentMapper sendPaymentMapper;

    @Autowired
    SendPaymentToTopFDIHKMapper sendPaymentToTopFDIHKMapper;
    @Autowired
    PsBasicInfoMapper psBasicInfoMapper;
    @Autowired
    PsCalculationResultMapper psCalculationResultMapper;
    @Autowired
    PsPayrollMapper psPayrollMapper;
    @Autowired
    PsPayslipMapper psPayslipMapper;
    @Autowired
    PsOpLogMapper psOpLogMapper;


    @Override
    public int SendPsBasicInfo(PsBasicInfo psBasicInfo,String servicePartB,String dataSource) {
        System.out.println("Current data source context: " + DynamicDataSourceContextHolder.getDataSourceType());
        int successNum = 0;
        /*查询当前数据库是否有数据*/
        PsBasicInfo basicInfo = psBasicInfoMapper.getPsBasicInfo(psBasicInfo);
        if ("HROneHK".equalsIgnoreCase(dataSource)) {
            /*查询目标数据库是否有数据*/
            PsBasicInfo BasicInfo = sendPaymentMapper.selectPsBasicInfo(psBasicInfo);
            if (BasicInfo == null) {
                sendPaymentMapper.insertPsBasicInfo(basicInfo);
                successNum++;
            } else {
                basicInfo.setUpdateBy(SecurityUtils.getUsername());
                basicInfo.setId(BasicInfo.getId());
                sendPaymentMapper.updatePsBasicInfo(basicInfo);
                successNum++;
            }
            return successNum;

        } else if ("TOPFDIHK".equalsIgnoreCase(dataSource)) {
            PsBasicInfo pbiList = sendPaymentToTopFDIHKMapper.selectPsBasicInfo(psBasicInfo);
            if (pbiList == null) {
                sendPaymentToTopFDIHKMapper.insertPsBasicInfo(basicInfo);
                successNum++;
            } else {
                basicInfo.setUpdateBy(SecurityUtils.getUsername());
                basicInfo.setId(pbiList.getId());
                sendPaymentToTopFDIHKMapper.updatePsBasicInfo(basicInfo);
                successNum++;
            }
            return successNum;
        } else {
            throw new IllegalArgumentException("Invalid DataSource: " + dataSource);
        }
    }


    @Override
    public int SendPsCalculationResult(PsCalculationResult psCalculationResult,String servicePartB,String dataSource) {
        int successNum = 0;
        /*本地*/
        PsCalculationResult calculationResult = psCalculationResultMapper.getPsCalculationResult(psCalculationResult);
        if ("HROneHK".equalsIgnoreCase(dataSource)) {
            /*目标*/
            PsCalculationResult result = sendPaymentMapper.selectPsCalculationResult(psCalculationResult);
            if (result==null) {
                sendPaymentMapper.insertPsCalculationResult(calculationResult);
                successNum++;
            } else {
                calculationResult.setUpdateBy(SecurityUtils.getUsername());
                calculationResult.setId(result.getId());
                sendPaymentMapper.updatePsCalculationResult(calculationResult);
                successNum++;
            }
            return successNum;
        } else if ("TOPFDIHK".equalsIgnoreCase(dataSource)) {
            PsCalculationResult result = sendPaymentToTopFDIHKMapper.selectPsCalculationResult(psCalculationResult);
            if (result==null) {
                sendPaymentToTopFDIHKMapper.insertPsCalculationResult(calculationResult);
                successNum++;
            } else {
                calculationResult.setUpdateBy(SecurityUtils.getUsername());
                calculationResult.setId(result.getId());
                sendPaymentToTopFDIHKMapper.updatePsCalculationResult(calculationResult);
                successNum++;

            }
            return successNum;
        } else {
            throw new IllegalArgumentException("Invalid DataSource: " + dataSource);
        }
    }

    @Override
    public int SendPsPayroll(PsPayroll psPayroll,PsPayslip psPayslip,String servicePartB,String dataSource) {
        int successNum = 0;
        PsPayroll payroll = psPayrollMapper.getPsPayroll(psPayroll);
        PsPayslip payslip=psPayslipMapper.getPsPayslip(psPayslip);
        payroll.setIpPension(payslip.getPension());
        payroll.setIpMedical(payslip.getMedical());
        payroll.setIpUnemployment(payslip.getUnemployment());
        payroll.setIpHousingFund(payslip.getHousingFund());
        payroll.setIpAnnuity(payslip.getAnnuity());
        payroll.setIpUnionFee(payslip.getUnionFee());

        if ("HROneHK".equalsIgnoreCase(dataSource)) {
            PsPayroll pr = sendPaymentMapper.selectPsPayroll(psPayroll);
            PsPayroll vendor = sendPaymentMapper.selectVendorPsPayroll(psPayroll);

            if (vendor==null) {
                payroll.setCreateBy(SecurityUtils.getUsername());
                payroll.setPayrollSource(servicePartB);
                sendPaymentMapper.insertVendorPsPayroll(payroll);
                successNum++;
            } else {
                payroll.setUpdateBy(SecurityUtils.getUsername());
                payroll.setPayrollSource(servicePartB);
                payroll.setId(vendor.getId());
                sendPaymentMapper.updateVendorPsPayroll(payroll);
                successNum++;
            }

            if (pr == null) {
                payroll.setCreateBy(SecurityUtils.getUsername());
                payroll.setPayrollSource(servicePartB);
                sendPaymentMapper.insertPsPayroll(payroll);
                successNum++;
            } else {
                // 如果 pr 存在，更新 PsPayroll
                payroll.setUpdateBy(SecurityUtils.getUsername());
                payroll.setPayrollSource(servicePartB);
                payroll.setId(pr.getId());
                sendPaymentMapper.updatePsPayroll(payroll);
                successNum++;
            }

            return successNum;
        } else if ("TOPFDIHK".equalsIgnoreCase(dataSource)) {

            PsPayroll pr = sendPaymentToTopFDIHKMapper.selectPsPayroll(psPayroll);
            PsPayroll vendor = sendPaymentToTopFDIHKMapper.selectVendorPsPayroll(psPayroll);

            if (vendor==null) {
                payroll.setCreateBy(SecurityUtils.getUsername());
                payroll.setPayrollSource(servicePartB);
                sendPaymentToTopFDIHKMapper.insertVendorPsPayroll(payroll);
                successNum++;
            } else {
                payroll.setUpdateBy(SecurityUtils.getUsername());
                payroll.setPayrollSource(servicePartB);
                payroll.setId(vendor.getId());
                sendPaymentToTopFDIHKMapper.updateVendorPsPayroll(payroll);
                successNum++;
            }

            if (pr == null) {
                payroll.setCreateBy(SecurityUtils.getUsername());
                payroll.setPayrollSource(servicePartB);
                sendPaymentToTopFDIHKMapper.insertPsPayroll(payroll);
                successNum++;
            } else {
                // 如果 pr 存在，更新 PsPayroll
                payroll.setUpdateBy(SecurityUtils.getUsername());
                payroll.setPayrollSource(servicePartB);
                payroll.setId(pr.getId());
                sendPaymentToTopFDIHKMapper.updatePsPayroll(payroll);
                successNum++;
            }

            return successNum;
        } else {
            throw new IllegalArgumentException("Invalid DataSource: " + dataSource);
        }
    }

    @Override
    public int SendPsPayslip(PsPayslip psPayslip,String servicePartB,String dataSource) {
        int successNum = 0;

        PsPayslip payslip = psPayslipMapper.getPsPayslip(psPayslip);
        payslip.setOrg(servicePartB);
        if ("HROneHK".equalsIgnoreCase(dataSource)) {
//            List<PsPayslip> pbiList = sendPaymentMapper.selectPsPayslipList(psPayslip);
            PsPayslip psPayslip1 = sendPaymentMapper.selectPsPayslip(psPayslip);
            if (psPayslip1==null) {
                sendPaymentMapper.insertPsPayslip(payslip);
                successNum++;
            } else {
                payslip.setUpdateBy(SecurityUtils.getUsername());
                payslip.setId(psPayslip1.getId());
                sendPaymentMapper.updatePsPayslip(payslip);
                successNum++;
            }
            return successNum;
        } else if ("TOPFDIHK".equalsIgnoreCase(dataSource)) {
            PsPayslip psPayslip1 = sendPaymentToTopFDIHKMapper.selectPsPayslip(psPayslip);
            if (psPayslip1==null) {
                sendPaymentToTopFDIHKMapper.insertPsPayslip(payslip);
                successNum++;
            } else {
                payslip.setUpdateBy(SecurityUtils.getUsername());
                payslip.setId(psPayslip1.getId());
                sendPaymentToTopFDIHKMapper.updatePsPayslip(payslip);
                successNum++;
            }
            return successNum;
        } else {
            throw new IllegalArgumentException("Invalid DataSource: " + dataSource);
        }
    }
    @Override
    public int SendPsCoverInfo(PsCoverInfo psCoverInfo, String servicePartB,String dataSource) {
        int successNum = 0;

        // 查询coverList
        List<PsCoverInfo> coverList = psOpLogMapper.selectPsCoverInfoList(psCoverInfo);
        if ("HROneHK".equalsIgnoreCase(dataSource)) {
            List<PsCoverInfo> infoList = sendPaymentMapper.selectPsCoverInfoList(psCoverInfo);
            // 如果infoList不为空，先删除符合条件的数据
            if (infoList != null && !infoList.isEmpty()) {
                for (PsCoverInfo coverInfo : infoList) {
                    sendPaymentMapper.deletePsCoverInfoByCondition(coverInfo);
                }
            }

            // 遍历插入coverList中的数据
            if (coverList != null && !coverList.isEmpty()) {
                for (PsCoverInfo coverInfo : coverList) {
                    sendPaymentMapper.insertPsCoverInfo(coverInfo);
                    successNum++;
                }
            }
            return successNum;
        } else if ("TOPFDIHK".equalsIgnoreCase(dataSource)) {
            List<PsCoverInfo> infoList = sendPaymentToTopFDIHKMapper.selectPsCoverInfoList(psCoverInfo);
            // 如果infoList不为空，先删除符合条件的数据
            if (infoList != null && !infoList.isEmpty()) {
                for (PsCoverInfo coverInfo : infoList) {
                    sendPaymentToTopFDIHKMapper.deletePsCoverInfoByCondition(coverInfo);
                }
            }

            // 遍历插入coverList中的数据
            if (coverList != null && !coverList.isEmpty()) {
                for (PsCoverInfo coverInfo : coverList) {
                    sendPaymentToTopFDIHKMapper.insertPsCoverInfo(coverInfo);
                    successNum++;
                }
            }

            return successNum;
        } else {
            throw new IllegalArgumentException("Invalid DataSource: " + dataSource);
        }
    }

}
