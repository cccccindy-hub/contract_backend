package com.nnroad.payroll.service.impl;


import com.nnroad.employee.domain.VEmployeeInfo;
import com.nnroad.employee.mapper.EmployeeInfoMapper;
import com.nnroad.employee.mapper.SysEmployeeMapper;
import com.nnroad.payroll.domain.ClientEmployeeCalculate;
import com.nnroad.payroll.domain.Staff;
import com.nnroad.payroll.domain.common.PsBasicInfo;
import com.nnroad.payroll.domain.common.PsCoverInfo;
import com.nnroad.payroll.domain.common.PsPayroll;
import com.nnroad.payroll.domain.common.PsPayslip;
import com.nnroad.payroll.mapper.*;
import com.nnroad.payroll.service.IClientEmployeeCalculateService;
import com.nnroad.payroll.service.IPsStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.PrivateKey;
import java.util.List;

@Service
public class ClientEmployeeCalculateServiceImpl implements IClientEmployeeCalculateService {

    @Autowired
    private ClientEmployeeCalculateMapper clientEmployeeMapper;
    @Autowired
    private PsPayrollMapper psPayrollMapper;
    @Autowired
    private PsOpLogMapper psOpLogMapper;
    @Autowired
    private PsBasicInfoMapper psBasicInfoMapper;

    @Autowired
    private PsPayslipMapper psPayslipMapper;

    @Autowired
    private SysEmployeeMapper sysEmployeeMapper;

    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    @Override
    public int selectCalculate(String idNO, PsPayroll pp) {

        PsPayroll psPayroll=psPayrollMapper.getVendorPsPayrollByIdnoAndduration(idNO,pp.getDuration());

        VEmployeeInfo vEmployeeInfo=employeeInfoMapper.selectVEmployeeInfoByEeCode(idNO);
        PsPayslip psPayslip = psPayslipMapper.selectPsPayslipByDurationAndeeCode(idNO, pp.getDuration());

        if (psPayslip == null) {
            throw new IllegalArgumentException("未找到对应的工资单记录！");
        }
        // 获取 AnnualBonus13thSalary 字段值
        BigDecimal annualBonus13thSalary = psPayslip.getAnnualBonus13thSalary();

            // 判断是否为 0
        BigDecimal rate = (annualBonus13thSalary != null && annualBonus13thSalary.compareTo(BigDecimal.ZERO) == 0)
                ? BigDecimal.ONE
                : new BigDecimal("2");

        //商业保险计算
        String totalCommericalInsurance=sysEmployeeMapper.selectTotalCommericalInsurance(idNO);
        // 检查是否为实际的 null、"null" 字符串或空字符串，统一转换为 BigDecimal.ZERO
        BigDecimal totalCommercialInsuranceValue = (totalCommericalInsurance == null
                || totalCommericalInsurance.trim().isEmpty()
                || "null".equalsIgnoreCase(totalCommericalInsurance.trim()))
                ? BigDecimal.ZERO
                : new BigDecimal(totalCommericalInsurance.trim());

        psPayroll.setCommercialInsurance(totalCommercialInsuranceValue);

        BigDecimal employercost = psPayroll.getNetIncome()
                .add(psPayroll.getForeignerAllowance())
                .add(psPayroll.getIpPension())
                .add(psPayroll.getIpMedical())
                .add(psPayroll.getIpUnemployment())
                .add(psPayroll.getIpHousingFund())
                .add(psPayroll.getIpAnnuity())
                .add(psPayroll.getIpUnionFee())
                .add(psPayroll.getIIT())
                .add(psPayroll.getBalanceCharge());

        BigDecimal finalEmployerLiability;
        if ("Yes".equalsIgnoreCase(vEmployeeInfo.getEmployersLiabilityInsurance())) {
           finalEmployerLiability = employercost
                    .multiply(BigDecimal.valueOf(0.008))
                    .setScale(2, RoundingMode.HALF_UP);
        } else {
            finalEmployerLiability = BigDecimal.ZERO;
        }
        psPayroll.setEmployerLiability(finalEmployerLiability);


        BigDecimal finalUnionFee;
        if ("Yes".equalsIgnoreCase(vEmployeeInfo.getUnionFees())) {
           finalUnionFee =employercost
                    .multiply(BigDecimal.valueOf(0.02))
                    .setScale(2, RoundingMode.HALF_UP);
        } else {
            finalUnionFee = BigDecimal.ZERO;
        }
        psPayroll.setEpUnionFee(finalUnionFee);


        BigDecimal laborCost = psPayroll.getNetIncome()
                .add(psPayroll.getExpense())
                .add(psPayroll.getForeignerAllowance())
                .add(psPayroll.getIpPension())
                .add(psPayroll.getIpMedical())
                .add(psPayroll.getIpUnemployment())
                .add(psPayroll.getIpHousingFund())
                .add(psPayroll.getIpAnnuity())
                .add(psPayroll.getIpUnionFee())
                .add(psPayroll.getCommercialInsurance())
                .add(psPayroll.getIIT())
                .add(psPayroll.getBalanceCharge())
                .add(psPayroll.getEpPension())
                .add(psPayroll.getEpMedical())
                .add(psPayroll.getEpUnemployment())
                .add(psPayroll.getMaternity())
                .add(psPayroll.getWorkRelatedInjury())
                .add(psPayroll.getDisability())
                .add(psPayroll.getEpHousingFund())
                .add(psPayroll.getEpAnnuity())
                .add(psPayroll.getEpUnionFee())
                .add(psPayroll.getOtherBenefits())
                .add(psPayroll.getOthers())
                .add(psPayroll.getBusinessTax())
                .add(psPayroll.getEmployerLiability());

        ClientEmployeeCalculate clientEmployeeCalculate=clientEmployeeMapper.selectCalculate(idNO);
        if (clientEmployeeCalculate == null) {
            throw new IllegalArgumentException("未找到对应的客户服务费配置信息！");
        }

        String currency= clientEmployeeCalculate.getServiceFeeCurrency();
        if (currency == null) {
            throw new IllegalArgumentException("Service Fee Currency is null");  // 抛出异常
        }

        PsCoverInfo psCoverInfo = new PsCoverInfo();
        psCoverInfo.setDuration(psPayroll.getDuration());
        psCoverInfo.setErNo(clientEmployeeCalculate.getClientCode());


        List<PsCoverInfo> coverInfoList = psOpLogMapper.selectPsCoverInfoList(psCoverInfo);
        BigDecimal exchangeRate;

        if (!"CNY".equalsIgnoreCase(currency)) {
            exchangeRate = coverInfoList.stream()
                    .filter(coverInfo -> currency.equalsIgnoreCase(coverInfo.getCurrency()))
                    .map(PsCoverInfo::getExchangeRate)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("未找到 currency 为 " + currency + " 的汇率信息！"));
        } else {
            exchangeRate = BigDecimal.ONE; // 如果是 CNY，汇率为 1
        }

        BigDecimal result = null;
        switch (clientEmployeeCalculate.getServiceFeeType().toLowerCase()) {
            case "flat": // 公式 A
                result= new BigDecimal(clientEmployeeCalculate.getServiceFeeValue());
                result = result.multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);
                psPayroll.setServiceFee(result.multiply(rate));
                break;

            case "percentage": // 公式 B

                BigDecimal percentage = new BigDecimal(clientEmployeeCalculate.getServiceFeeValue()).divide(BigDecimal.valueOf(100));
                BigDecimal finalResult = laborCost.multiply(percentage).setScale(2, RoundingMode.HALF_UP);
                // 确保 MinValue 和 MaxValue 字符串不为空，并转换为 BigDecimal 进行计算
                BigDecimal minValue;
                BigDecimal maxValue;

                try {
                    // 转换 MinValue，如果为空、无效或为字符串 "null"，设置默认值 0
                    String minValueStr = clientEmployeeCalculate.getMinValue();
                    if (minValueStr != null && !minValueStr.trim().isEmpty() && !"null".equalsIgnoreCase(minValueStr)) {
                        minValue = new BigDecimal(minValueStr).multiply(exchangeRate);
                    } else {
                        minValue = BigDecimal.ZERO; // 默认值为 0
                    }

                    // 转换 MaxValue，如果为空、无效或为字符串 "null"，设置默认值 999999
                    String maxValueStr = clientEmployeeCalculate.getMaxValue();
                    if (maxValueStr != null && !maxValueStr.trim().isEmpty() && !"null".equalsIgnoreCase(maxValueStr)) {
                        maxValue = new BigDecimal(maxValueStr).multiply(exchangeRate);
                    } else {
                        maxValue = new BigDecimal("999999999");
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("MinValue 或 MaxValue 不是有效的数字！", e);
                }

                // 判断 result 是否在范围内
                if (finalResult.compareTo(minValue) < 0) {
                    // 如果 result 小于 minValue
                    result = minValue;
                } else if (finalResult.compareTo(maxValue) > 0) {
                    // 如果 result 大于 maxValue
                    result = maxValue;
                } else {
                    // 如果 result 在范围内
                    result = finalResult;
                }
                psPayroll.setServiceFee(result.multiply(rate));
                break;
            default:
                psPayroll.setServiceFee(psPayroll.getServiceFee().multiply(rate));
                throw new IllegalArgumentException("未定义的计算类型: " +clientEmployeeCalculate.getServiceFeeType());
        }

        BigDecimal valueAddedTax = laborCost
                .add(psPayroll.getServiceFee()) // 累加服务费
                .subtract(psPayroll.getDeposit());
        BigDecimal finalValueAddedTax = valueAddedTax.
                multiply(BigDecimal.valueOf(0.0677))
                .setScale(2, RoundingMode.HALF_UP);
        psPayroll.setValueAddedTax(finalValueAddedTax);

        BigDecimal totalCost=laborCost.add(psPayroll.getServiceFee())
                        .add(psPayroll.getDeposit()).add(psPayroll.getValueAddedTax());
        psPayroll.setTotalCost(totalCost.setScale(2,RoundingMode.HALF_UP));

        psPayroll.setId(pp.getId());
        return psPayrollMapper.updatePsPayroll(psPayroll);
    }
}
