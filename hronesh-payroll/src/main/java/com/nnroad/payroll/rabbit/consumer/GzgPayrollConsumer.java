package com.nnroad.payroll.rabbit.consumer;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.nnroad.client.domain.SysClient;
import com.nnroad.client.mapper.SysClientMapper;
import com.nnroad.common.enums.ColumnEnum;
import com.nnroad.common.enums.SysOrgEnum;
import com.nnroad.common.enums.TableEnum;
import com.nnroad.common.exception.Asserts;
import com.nnroad.common.exception.BusinessException;
import com.nnroad.common.utils.ThirdAppTokenUtils;
import com.nnroad.employee.domain.EmployeeInfo;
import com.nnroad.employee.domain.SysEmployee;
import com.nnroad.employee.service.EmployeeInfoService;
import com.nnroad.employee.service.ISysEmployeeService;
import com.nnroad.payroll.domain.PayrollEntry;
import com.nnroad.payroll.domain.PsOpLog;
import com.nnroad.payroll.domain.common.*;
import com.nnroad.payroll.dto.CalculationRules;
import com.nnroad.payroll.dto.PayrollDto;
import com.nnroad.payroll.dto.PolicyDto;
import com.nnroad.payroll.mapper.*;
import com.nnroad.payroll.service.IPsOpLogService;
import com.nnroad.utils.ExtraAttributeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GzgPayrollConsumer {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GzgPayrollMapper gzgPayrollMapper;

    @Autowired
    private SysClientMapper clientMapper;

    @Autowired
    private ISysEmployeeService employeeService;

    @Autowired
    private EmployeeInfoService employeeInfoService;

    @Autowired
    private PsPayslipMapper psPayslipMapper;

    @Autowired
    private PsPayrollMapper psPayrollMapper;

    @Autowired
    private PsBasicInfoMapper psBasicInfoMapper;

    @Autowired
    private PsCalculationResultMapper psCalculationResultMapper;

    @Autowired
    private IPsOpLogService psOpLogService;

    @Autowired
    @Lazy
    private GzgPayrollConsumer payrollConsumer;

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.appId}")
    private String appId;

    @Value("${api.appKey}")
    private String appKey;

    @Value("${api.secretKey}")
    private String secretKey;

    @Value("${spring.profiles.active}")
    private String env;

    public void processMessage(String payrollId) {
        PayrollDto payrollDto = gzgPayrollMapper.selectByPayrollId(payrollId);
        if (payrollDto == null) {
            log.error("【工资哥工资单】不存在：payrollId-{}", payrollId);
            return;
        }
        String clientCodeName = payrollDto.getEmpNameCode();
        if (StrUtil.isBlank(clientCodeName)) {
            log.warn("【工资哥工资单】获取客户名称为空：payrollId-{}", payrollId);
            return;
        }
        String clientCode = clientCodeName.split("_")[0];
        SysClient client = clientMapper.selectBasicByClientCode(clientCode);
        if (client == null) {
            log.info("【工资哥工资单】无法获取到客户信息：payrollId:{}, ErName: {}", payrollId, clientCodeName);
            return;
        }
        if (!checkNeedConsumer(client)) {
            log.info("【工资哥工资单】判定不需要消费该消息：payrollId-{}, client-{}", payrollId, client);
            return;
        }
        payrollDto.setEmpCode(clientCode);
        payrollDto.setEmpName(client.getClientName());
        //工资数据
        List<PayrollEntry> payrollEntries = gzgPayrollMapper.selectByFpayroll(payrollId);
        if (CollUtil.isEmpty(payrollEntries)) {
            log.warn("【工资哥工资单】查询工资数据为空：payrollId: {}", payrollId);
            return;
        }
        //配置映射：Map<fid,code>
        Map<String, String> config = Optional.ofNullable(gzgPayrollMapper.selectFidAndFcode(payrollDto.getEmpId())).orElse(new ArrayList<>())
                .stream()
                .collect(Collectors.toMap(result -> result.get("fid"), result -> result.get("fcode"), (r1, r2) -> r1));
        //数据转换List<Map<code,value>>
        List<Map<String, Object>> payrolls = dataTransfer(payrollEntries, config);
        //数据校验
        boolean checkResult = checkFields(payrolls);
        if (!checkResult) {
            return;
        }

        payrollConsumer.gzgInsert(payrolls, payrollDto);
        log.info("【工资哥工资单】导入HROne成功 payrollId-{}", payrollId);
    }

    private boolean checkNeedConsumer(SysClient client) {
        String org = client.getOrg();
        return StrUtil.isNotBlank(org) && StrUtil.contains(SysOrgEnum.getBySystem(env), org);
    }

    private List<Map<String, Object>> dataTransfer(List<PayrollEntry> payrollEntries, Map<String, String> config) {
        return payrollEntries.stream()
                .map(entry -> doTransferPayrollEntry(entry, config))
                .collect(Collectors.toList());
    }

    private Map<String, Object> doTransferPayrollEntry(PayrollEntry entry, Map<String, String> config) {
        Map<String, Object> result = new HashMap<>();
        JSONObject data = JSONObject.parseObject(entry.getFdata());
        config.forEach((fid, code) -> result.put(code, Optional.ofNullable(data.getJSONObject(fid)).map(res -> res.get("value")).orElse(null)));

        return result;
    }

    private boolean checkFields(List<Map<String, Object>> entries) {
        boolean result = true;
        for (Map<String, Object> entry : entries) {
            if (ObjectUtil.isEmpty(entry.get("s0007"))) {
                result = false;
                log.error("【工资哥工资单】校验工资哥数据s0007字段数据为空");
                break;
            }
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public void gzgInsert(List<Map<String, Object>> payrolls, PayrollDto payroll) {
        Map<String, PolicyDto> policy = new HashMap<>();
        boolean isCoverInfoInserted = false;
        for (Map<String, Object> entry : payrolls) {
            if (!isCoverInfoInserted) {
                insertCoverInfo(entry, payroll);
                isCoverInfoInserted = true;
            }
            insertBasicInfo(entry, payroll);
            insertPayroll(entry, policy, payroll);
            insertCalculationResult(entry, policy, payroll);
            insertPayslip(entry, policy, payroll);
        }

        //插入日志
        PsOpLog pol = new PsOpLog();
        pol.setCreateBy("Gzg");
        pol.setCreateTime(new Date());
        pol.setRoleName("Gzg");
        pol.setImputFileName(payroll.getEmpName() + "_" + payroll.getEmpCode() + "_" + payroll.getPeriod());
        pol.setOpType("Gzg同步导入");
        psOpLogService.insertPsOpLog(pol);
    }

    private void insertPayslip(Map<String, Object> entry, Map<String, PolicyDto> policy, PayrollDto payroll) {
        PsPayslip psPayslip = new PsPayslip();
        String department = getValue(entry, "s0002");
        String duration = payroll.getPeriod();
        String idNo = getValue(entry, "s0007");
        String name = getValue(entry, "s0008");
        String enname = getValue(entry, "s0182");
        String basicSalary = getValue(entry, "s0111");
        String normalBonus = getValue(entry, "s0198");
        String annualBonus13thSalary = getValue(entry, "s0437");
        String allowanceSubsidy = getValue(entry, "s0277");
        String transportAllowance = getValue(entry, "s0255");
        String communicationAllowance = getValue(entry, "s0256");
        String mealAllowance = getValue(entry, "s0257");
        String birthdayGift = getValue(entry, "s0267");
        String otherAllowance = getValue(entry, "s0268");
        String otPayment = getValue(entry, "s0230");
        String socialBenefitsBasis = getValue(entry, "s0116");
        String housingFundBasis = getValue(entry, "s0161");
        String absence = getValue(entry, "s0281");
        String affairLeave = getValue(entry, "s0284");
        String absenteeism = getValue(entry, "s0287");
        String sickLeave = getValue(entry, "s0289");
        String annuity = getValue(entry, "s0404");
        String IIT = getValue(entry, "s0444");
        String expense = getValue(entry, "s0529");
        BigDecimal foreignersSubsistence = getValueBigDecimal(entry, "s0353");
        String yTDChildrenEducation = getValue(entry, "s0416");
        String yTDContinueEducation = getValue(entry, "s0418");
        String yTDHousingLoanInterest = getValue(entry, "s0420");
        String yTDHousingRental = getValue(entry, "s0422");
        String yTDCaringForTheElderly = getValue(entry, "s0424");
        String yTDChildCare = getValue(entry, "s0425");

        if (department != null && !department.isEmpty()) {
            psPayslip.setDepartment(department);
        }

        if (duration != null && !duration.isEmpty()) {
            psPayslip.setDuration(duration);
        }

        if (idNo != null && !idNo.isEmpty()) {
            psPayslip.setIdNo(idNo);
        }

        if (name != null && !name.isEmpty()) {
            if (enname != null && !enname.isEmpty()) {
                psPayslip.setName(name + " " + enname);
            } else {
                psPayslip.setName(name);
            }
        }


        if (basicSalary != null && !basicSalary.isEmpty()) {
            psPayslip.setBasicSalary(BigDecimal.valueOf(Double.parseDouble(basicSalary)));
        }
        if (normalBonus != null && !normalBonus.isEmpty()) {
            psPayslip.setNormalBonus(BigDecimal.valueOf(Double.parseDouble(normalBonus)));
        }

        if (annualBonus13thSalary != null && !annualBonus13thSalary.isEmpty()) {
            psPayslip.setAnnualBonus13thSalary(BigDecimal.valueOf(Double.parseDouble(annualBonus13thSalary)));
        }

        if (allowanceSubsidy != null && !allowanceSubsidy.isEmpty()) {
            psPayslip.setAllowanceSubsidy(BigDecimal.valueOf(Double.parseDouble(allowanceSubsidy)));
        }

        if (transportAllowance != null && !transportAllowance.isEmpty()) {
            psPayslip.setTransportAllowance(BigDecimal.valueOf(Double.parseDouble(transportAllowance)));
        }

        if (communicationAllowance != null && !communicationAllowance.isEmpty()) {
            psPayslip.setCommunicationAllowance(BigDecimal.valueOf(Double.parseDouble(communicationAllowance)));
        }

        if (mealAllowance != null && !mealAllowance.isEmpty()) {
            psPayslip.setMealAllowance(BigDecimal.valueOf(Double.parseDouble(mealAllowance)));
        }

        if (birthdayGift != null && !birthdayGift.isEmpty()) {
            psPayslip.setBirthdayGift(BigDecimal.valueOf(Double.parseDouble(birthdayGift)));
        }

        if (otherAllowance != null && !otherAllowance.isEmpty()) {
            psPayslip.setOtherAllowance(BigDecimal.valueOf(Double.parseDouble(otherAllowance)));
        }

        if (otPayment != null && !otPayment.isEmpty()) {
            psPayslip.setOtPayment(BigDecimal.valueOf(Double.parseDouble(otPayment)));
        }

        if (socialBenefitsBasis != null && !socialBenefitsBasis.isEmpty()) {
            psPayslip.setSocialBenefitsBasis(BigDecimal.valueOf(Double.parseDouble(socialBenefitsBasis)));
        }

        if (housingFundBasis != null && !housingFundBasis.isEmpty()) {
            psPayslip.setHousingFundBasis(BigDecimal.valueOf(Double.parseDouble(housingFundBasis)));
        }

        if (absence != null && !absence.isEmpty()) {
            psPayslip.setAbsence(BigDecimal.valueOf(Double.parseDouble(absence)));
        }

        if (affairLeave != null && !affairLeave.isEmpty()) {
            psPayslip.setAffairLeave(BigDecimal.valueOf(Double.parseDouble(affairLeave)));
        }

        if (absenteeism != null && !absenteeism.isEmpty()) {
            psPayslip.setAbsenteeism(BigDecimal.valueOf(Double.parseDouble(absenteeism)));
        }

        if (sickLeave != null && !sickLeave.isEmpty()) {
            psPayslip.setSickLeave(BigDecimal.valueOf(Double.parseDouble(sickLeave)));
        }

        if (annuity != null && !annuity.isEmpty()) {
            psPayslip.setAnnuity(BigDecimal.valueOf(Double.parseDouble(annuity)));
        }

        if (IIT != null && !IIT.isEmpty()) {
            psPayslip.setIIT(BigDecimal.valueOf(Double.parseDouble(IIT)));
        }


        if (expense != null && !expense.isEmpty()) {
            psPayslip.setExpense(BigDecimal.valueOf(Double.parseDouble(expense)));
        }

        psPayslip.setForeignersSubsistence(foreignersSubsistence);

        if (yTDChildrenEducation != null && !yTDChildrenEducation.isEmpty()) {
            psPayslip.setyTDChildrenEducation(yTDChildrenEducation);
        }

        if (yTDContinueEducation != null && !yTDContinueEducation.isEmpty()) {
            psPayslip.setyTDContinueEducation(yTDContinueEducation);
        }

        if (yTDHousingLoanInterest != null && !yTDHousingLoanInterest.isEmpty()) {
            psPayslip.setyTDHousingLoanInterest(yTDHousingLoanInterest);
        }

        if (yTDHousingRental != null && !yTDHousingRental.isEmpty()) {
            psPayslip.setyTDHousingRental(yTDHousingRental);
        }

        if (yTDCaringForTheElderly != null && !yTDCaringForTheElderly.isEmpty()) {
            psPayslip.setyTDCaringForTheElderly(yTDCaringForTheElderly);
        }

        if (yTDChildCare != null && !yTDChildCare.isEmpty()) {
            psPayslip.setyTDChildCare(yTDChildCare);
        }

        CalculationRules calculationRules = new CalculationRules();
        PolicyDto spPolicy = getPolicy(getValue(entry, "s0112"), duration, policy);
        if (spPolicy != null) {
            calculationRules.setSpAccuracy(spPolicy.getSpAccuracy());
            calculationRules.setSpRoundMode(Round(spPolicy.getSpRoundMode()));
        }
        PolicyDto pfPolicy = getPolicy(getValue(entry, "s0167"), duration, policy);
        if (pfPolicy != null) {
            calculationRules.setPfAccuracy(pfPolicy.getPfAccuracy());
            calculationRules.setPfRoundMode(Round(pfPolicy.getPfRoundMode()));
        }

        BigDecimal s0116 = getValueBigDecimal(entry, "s0116");
        BigDecimal s0117 = getValueBigDecimal(entry, "s0117");
        BigDecimal s0118 = getValueBigDecimal(entry, "s0118");
        BigDecimal s0120 = getValueBigDecimal(entry, "s0120");
        BigDecimal s0121 = getValueBigDecimal(entry, "s0121");
        BigDecimal s0122 = getValueBigDecimal(entry, "s0122");
        BigDecimal s0124 = getValueBigDecimal(entry, "s0124");
        BigDecimal s0125 = getValueBigDecimal(entry, "s0125");
        BigDecimal s0126 = getValueBigDecimal(entry, "s0126");
        BigDecimal s0161 = getValueBigDecimal(entry, "s0161");
        BigDecimal s0162 = getValueBigDecimal(entry, "s0162");
        BigDecimal s0163 = getValueBigDecimal(entry, "s0163");
        BigDecimal s0168 = getValueBigDecimal(entry, "s0168");
        BigDecimal s0169 = getValueBigDecimal(entry, "s0169");
        BigDecimal s0170 = getValueBigDecimal(entry, "s0170");
        BigDecimal s0129 = getValueBigDecimal(entry, "s0129");
        BigDecimal s0130 = getValueBigDecimal(entry, "s0130");
        BigDecimal s0131 = getValueBigDecimal(entry, "s0131");
        BigDecimal s0132 = getValueBigDecimal(entry, "s0132");
        BigDecimal s0133 = getValueBigDecimal(entry, "s0133");
        BigDecimal s0134 = getValueBigDecimal(entry, "s0134");
        BigDecimal s0165 = getValueBigDecimal(entry, "s0165");
        BigDecimal s0166 = getValueBigDecimal(entry, "s0166");
        BigDecimal s0524 = getValueBigDecimal(entry, "s0524");
        BigDecimal s0525 = getValueBigDecimal(entry, "s0525");
        BigDecimal s0136 = getValueBigDecimal(entry, "s0136");
        BigDecimal s0137 = getValueBigDecimal(entry, "s0137");
        BigDecimal s0138 = getValueBigDecimal(entry, "s0138");
        BigDecimal s0140 = getValueBigDecimal(entry, "s0140");
        BigDecimal s0141 = getValueBigDecimal(entry, "s0141");
        BigDecimal s0142 = getValueBigDecimal(entry, "s0142");
        BigDecimal s0144 = getValueBigDecimal(entry, "s0144");
        BigDecimal s0145 = getValueBigDecimal(entry, "s0145");
        BigDecimal s0146 = getValueBigDecimal(entry, "s0146");
        BigDecimal s0148 = getValueBigDecimal(entry, "s0148");
        BigDecimal s0149 = getValueBigDecimal(entry, "s0149");
        BigDecimal s0150 = getValueBigDecimal(entry, "s0150");
        BigDecimal s0152 = getValueBigDecimal(entry, "s0152");
        BigDecimal s0153 = getValueBigDecimal(entry, "s0153");
        BigDecimal s0154 = getValueBigDecimal(entry, "s0154");
        BigDecimal s0156 = getValueBigDecimal(entry, "s0156");
        BigDecimal s0157 = getValueBigDecimal(entry, "s0157");
        BigDecimal s0158 = getValueBigDecimal(entry, "s0158");
        BigDecimal s0175 = getValueBigDecimal(entry, "s0175");
        BigDecimal s0176 = getValueBigDecimal(entry, "s0176");
        BigDecimal s0177 = getValueBigDecimal(entry, "s0177");
        BigDecimal s0179 = getValueBigDecimal(entry, "s0179");
        BigDecimal s0180 = getValueBigDecimal(entry, "s0180");
        BigDecimal s0181 = getValueBigDecimal(entry, "s0181");
        BigDecimal S0472 = getValueBigDecimal(entry, "s0472");
        BigDecimal S0529 = getValueBigDecimal(entry, "s0529");
        BigDecimal s0351 = getValueBigDecimal(entry, "s0351");
        BigDecimal s0352 = getValueBigDecimal(entry, "s0352");
        BigDecimal s0343 = getValueBigDecimal(entry, "s0343");
        BigDecimal s0475 = getValueBigDecimal(entry, "s0475");
        BigDecimal s0336 = getValueBigDecimal(entry, "s0336");
        BigDecimal s0457 = getValueBigDecimal(entry, "s0457");
        BigDecimal s0468 = getValueBigDecimal(entry, "s0468");

        BigDecimal netIncome = S0472.subtract(S0529).subtract(s0351).subtract(s0352).subtract(foreignersSubsistence);
        BigDecimal Pension = s0116.multiply(s0117).add(s0118).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal Medical = s0120.multiply(s0121).add(s0122).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal Unemployment = s0124.multiply(s0125).add(s0126).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal HousingFund = (s0161.multiply(s0162).add(s0163).setScale(calculationRules.getPfAccuracy(), calculationRules.getPfRoundMode())).add(s0168.multiply(s0169).add(s0170).setScale(calculationRules.getPfAccuracy(), calculationRules.getPfRoundMode()));
        BigDecimal balanceCharge = s0129.subtract(s0130).add(s0131).subtract(s0132).add(s0133).subtract(s0134).add(s0165).subtract(s0166).add(s0524).subtract(s0525);
        BigDecimal pretaxAdjustment = s0475.subtract(s0336);
        BigDecimal afterTaxAdjustment = s0457.subtract(s0468);

        psPayslip.setNetIncome(netIncome);
        psPayslip.setPension(Pension);
        psPayslip.setMedical(Medical);
        psPayslip.setUnemployment(Unemployment);
        psPayslip.setHousingFund(HousingFund);
        psPayslip.setBalanceCharge(balanceCharge);
        psPayslip.setPretaxAdjustment(pretaxAdjustment);
        psPayslip.setAfterTaxAdjustment(afterTaxAdjustment);

        psPayslip.setGroupIds("7");

        PsPayslip pp = new PsPayslip();
        pp.setDuration(duration);
        pp.setIdNo(psPayslip.getIdNo());
        List<PsPayslip> psPayslips = psPayslipMapper.selectPsPayslipList(pp);
        if (CollUtil.isNotEmpty(psPayslips)) {
            psPayslip.setId(psPayslips.get(0).getId());
            psPayslip.setUpdateTime(new Date());
            psPayslipMapper.updatePsPayslip(psPayslip);
        } else {
            psPayslip.setCreateTime(new Date());
            psPayslipMapper.insertPsPayslip(psPayslip);
        }
    }

    private void insertCalculationResult(Map<String, Object> entry, Map<String, PolicyDto> policy, PayrollDto payroll) {
        PsCalculationResult psCalculationResult = new PsCalculationResult();
        String duration = payroll.getPeriod();
        String idNo = getValue(entry, "s0007");
        String name = getValue(entry, "s0008");
        String socialBenefitsAddress = getValue(entry, "s0112");
        String basicSalary = getValue(entry, "s0095");
        String floatingSalary = getValue(entry, "s0101");
        String annuity = getValue(entry, "s0404");
        String accumulatedChildEducation = getValue(entry, "s0416");
        String accumulatedContinuingEducation = getValue(entry, "s0418");
        String accumulatedHli = getValue(entry, "s0420");
        String accumulatedHousingRent = getValue(entry, "s0422");
        String accumulatedSfte = getValue(entry, "s0424");
        String quarterAward = getValue(entry, "s0184");
        String reissue = getValue(entry, "s0475");
        String wageDeduction = getValue(entry, "s0336");
        String expense = getValue(entry, "s0529");
        String afterTaxReissue = getValue(entry, "s0457");
        String annualBonus = getValue(entry, "s0437");
        String monthlyAward = getValue(entry, "s0183");
        String transportationPayment = getValue(entry, "s0255");
        String communicationPayment = getValue(entry, "s0256");
        String mealAllowance = getValue(entry, "s0257");
        String birthdayGift = getValue(entry, "s0267");
        String overtimeAward150 = getValue(entry, "s0213");
        String overtimeAward200 = getValue(entry, "s0216");
        String overtimeAward300 = getValue(entry, "s0219");
        String ealaDeduction = getValue(entry, "s0281");
        String alDeduction = getValue(entry, "s0284");
        String absenteeismDeduction = getValue(entry, "s0287");
        String slDeduction = getValue(entry, "s0289");
        String otherBenefits = getValue(entry, "s0268");
        String middleShiftPayment = getValue(entry, "s0236");
        String nightShiftPayment = getValue(entry, "s0239");
        String highTemperaturePayment = getValue(entry, "s0260");
        String hotWorkingPayment = getValue(entry, "s0263");
        String tahwPayment = getValue(entry, "s0266");
        String accumulatedIsiahf = getValue(entry, "s0414");
        String accumulatedBd = getValue(entry, "s0413");
        String annualBonusLlt = getValue(entry, "s0443");
        String income = getValue(entry, "s0470");
        String cost = getValue(entry, "s0471");
        String netIncome = getValue(entry, "s0472");
        String afterTaxDeduction = getValue(entry, "s0468");
        String fsAllowance = getValue(entry, "s0353");
        String preTaxIncome = getValue(entry, "s0368");
        String basicDeduction = getValue(entry, "s0388");
        String individualLlt = getValue(entry, "s0436");
        String taxCalculation = getValue(entry, "s0350");
        String otherBonus = getValue(entry, "s0188");
        String travelAllowance = getValue(entry, "s0271");
        String accumulatedChildCare = getValue(entry, "s0425");
        String entryDate = getValue(entry, "s0025");

        if (entry.containsKey("e001")) {
            String unionFee = getValue(entry, "e001");
            if (unionFee != null && !unionFee.isEmpty()) {
                psCalculationResult.setUnionFee(BigDecimal.valueOf(Double.parseDouble(unionFee)));
            }
        }
        if (entry.containsKey("e005")) {
            String employerLiability = getValue(entry, "e005");
            if (employerLiability != null && !employerLiability.isEmpty()) {
                psCalculationResult.setEmployerLiability(BigDecimal.valueOf(Double.parseDouble(employerLiability)));
            }
        }
        if (entry.containsKey("e006")) {
            String deposit = getValue(entry, "e006");
            if (deposit != null && !deposit.isEmpty()) {
                psCalculationResult.setDeposit(BigDecimal.valueOf(Double.parseDouble(deposit)));
            }
        }
        if (entry.containsKey("e007")) {
            String serviceFee = getValue(entry, "e007");
            if (serviceFee != null && !serviceFee.isEmpty()) {
                psCalculationResult.setServiceFee(BigDecimal.valueOf(Double.parseDouble(serviceFee)));
            }
        }
        if (entry.containsKey("e008")) {
            String valueAddedTax = getValue(entry, "e008");
            if (valueAddedTax != null && !valueAddedTax.isEmpty()) {
                psCalculationResult.setValueAddedTax(BigDecimal.valueOf(Double.parseDouble(valueAddedTax)));
            }
        }
        if (entry.containsKey("e019")) {
            String serviceFeeTax = getValue(entry, "e019");
            if (StrUtil.isNotBlank(serviceFeeTax)) {
                psCalculationResult.setServiceFeeTax(new BigDecimal(serviceFeeTax));
            }
        }

        if (duration != null && !duration.isEmpty()) {
            psCalculationResult.setDuration(duration);
        }
        psCalculationResult.setErName(payroll.getEmpName());

        if (idNo != null && !idNo.isEmpty()) {
            psCalculationResult.setIdNo(idNo);
        }

        if (name != null && !name.isEmpty()) {
            psCalculationResult.setName(name);
        }
        if (socialBenefitsAddress != null && !socialBenefitsAddress.isEmpty()) {
            psCalculationResult.setSocialBenefitsAddress(socialBenefitsAddress);
        }
        if (basicSalary != null && !basicSalary.isEmpty()) {
            psCalculationResult.setBasicSalary(BigDecimal.valueOf(Double.parseDouble(basicSalary)));
        }
        if (floatingSalary != null && !floatingSalary.isEmpty()) {
            psCalculationResult.setFloatingSalary(BigDecimal.valueOf(Double.parseDouble(floatingSalary)));
        }
        if (annuity != null && !annuity.isEmpty()) {
            psCalculationResult.setAnnuity(BigDecimal.valueOf(Double.parseDouble(annuity)));
        }
        if (accumulatedChildEducation != null && !accumulatedChildEducation.isEmpty()) {
            psCalculationResult.setAccumulatedChildEducation(BigDecimal.valueOf(Double.parseDouble(accumulatedChildEducation)));
        }
        if (accumulatedContinuingEducation != null && !accumulatedContinuingEducation.isEmpty()) {
            psCalculationResult.setAccumulatedContinuingEducation(BigDecimal.valueOf(Double.parseDouble(accumulatedContinuingEducation)));
        }
        if (accumulatedHli != null && !accumulatedHli.isEmpty()) {
            psCalculationResult.setAccumulatedHli(BigDecimal.valueOf(Double.parseDouble(accumulatedHli)));
        }
        if (accumulatedHousingRent != null && !accumulatedHousingRent.isEmpty()) {
            psCalculationResult.setAccumulatedHousingRent(BigDecimal.valueOf(Double.parseDouble(accumulatedHousingRent)));
        }
        if (accumulatedSfte != null && !accumulatedSfte.isEmpty()) {
            psCalculationResult.setAccumulatedSfte(BigDecimal.valueOf(Double.parseDouble(accumulatedSfte)));
        }
        if (quarterAward != null && !quarterAward.isEmpty()) {
            psCalculationResult.setQuarterAward(BigDecimal.valueOf(Double.parseDouble(quarterAward)));
        } else {
            psCalculationResult.setQuarterAward(BigDecimal.ZERO);  // 设置默认值为 0
        }

        if (reissue != null && !reissue.isEmpty()) {
            psCalculationResult.setReissue(BigDecimal.valueOf(Double.parseDouble(reissue)));
        }
        if (wageDeduction != null && !wageDeduction.isEmpty()) {
            psCalculationResult.setWageDeduction(BigDecimal.valueOf(Double.parseDouble(wageDeduction)));
        }
        if (afterTaxReissue != null && !afterTaxReissue.isEmpty()) {
            psCalculationResult.setAfterTaxReissue(BigDecimal.valueOf(Double.parseDouble(afterTaxReissue)));
        }
        if (annualBonus != null && !annualBonus.isEmpty()) {
            psCalculationResult.setAnnualBonus(BigDecimal.valueOf(Double.parseDouble(annualBonus)));
        }
        if (birthdayGift != null && !birthdayGift.isEmpty()) {
            psCalculationResult.setBirthdayGift(BigDecimal.valueOf(Double.parseDouble(birthdayGift)));
        }
        if (overtimeAward150 != null && !overtimeAward150.isEmpty()) {
            psCalculationResult.setOvertimeAward150(BigDecimal.valueOf(Double.parseDouble(overtimeAward150)));
        }
        if (overtimeAward200 != null && !overtimeAward200.isEmpty()) {
            psCalculationResult.setOvertimeAward200(BigDecimal.valueOf(Double.parseDouble(overtimeAward200)));
        }
        if (overtimeAward300 != null && !overtimeAward300.isEmpty()) {
            psCalculationResult.setOvertimeAward300(BigDecimal.valueOf(Double.parseDouble(overtimeAward300)));
        }
        if (expense != null && !expense.isEmpty()) {
            psCalculationResult.setExpense(BigDecimal.valueOf(Double.parseDouble(expense)));
        } else {
            psCalculationResult.setExpense(BigDecimal.ZERO);
        }
        psCalculationResult.setExpense25(BigDecimal.ZERO);
        if (monthlyAward != null && !monthlyAward.isEmpty()) {
            psCalculationResult.setMonthlyAward(BigDecimal.valueOf(Double.parseDouble(monthlyAward)));
        }

        if (transportationPayment != null && !transportationPayment.isEmpty()) {
            psCalculationResult.setTransportationPayment(BigDecimal.valueOf(Double.parseDouble(transportationPayment)));
        }

        if (communicationPayment != null && !communicationPayment.isEmpty()) {
            psCalculationResult.setCommunicationPayment(BigDecimal.valueOf(Double.parseDouble(communicationPayment)));
        }

        if (mealAllowance != null && !mealAllowance.isEmpty()) {
            psCalculationResult.setMealAllowance(BigDecimal.valueOf(Double.parseDouble(mealAllowance)));
        }
        if (ealaDeduction != null && !ealaDeduction.isEmpty()) {
            psCalculationResult.setEalaDeduction(BigDecimal.valueOf(Double.parseDouble(ealaDeduction)));
        }

        if (alDeduction != null && !alDeduction.isEmpty()) {
            psCalculationResult.setAlDeduction(BigDecimal.valueOf(Double.parseDouble(alDeduction)));
        }

        if (absenteeismDeduction != null && !absenteeismDeduction.isEmpty()) {
            psCalculationResult.setAbsenteeismDeduction(BigDecimal.valueOf(Double.parseDouble(absenteeismDeduction)));
        }

        if (slDeduction != null && !slDeduction.isEmpty()) {
            psCalculationResult.setSlDeduction(BigDecimal.valueOf(Double.parseDouble(slDeduction)));
        }

        if (otherBenefits != null && !otherBenefits.isEmpty()) {
            psCalculationResult.setOtherBenefits(BigDecimal.valueOf(Double.parseDouble(otherBenefits)));
        } else {
            psCalculationResult.setOtherBenefits(BigDecimal.ZERO);  // 设置默认值为 0
        }

        if (middleShiftPayment != null && !middleShiftPayment.isEmpty()) {
            psCalculationResult.setMiddleShiftPayment(BigDecimal.valueOf(Double.parseDouble(middleShiftPayment)));
        }

        if (nightShiftPayment != null && !nightShiftPayment.isEmpty()) {
            psCalculationResult.setNightShiftPayment(BigDecimal.valueOf(Double.parseDouble(nightShiftPayment)));
        }

        if (highTemperaturePayment != null && !highTemperaturePayment.isEmpty()) {
            psCalculationResult.setHighTemperaturePayment(BigDecimal.valueOf(Double.parseDouble(highTemperaturePayment)));
        }

        if (hotWorkingPayment != null && !hotWorkingPayment.isEmpty()) {
            psCalculationResult.setHotWorkingPayment(BigDecimal.valueOf(Double.parseDouble(hotWorkingPayment)));
        }

        if (tahwPayment != null && !tahwPayment.isEmpty()) {
            psCalculationResult.setTahwPayment(BigDecimal.valueOf(Double.parseDouble(tahwPayment)));
        }

        if (accumulatedIsiahf != null && !accumulatedIsiahf.isEmpty()) {
            psCalculationResult.setAccumulatedIsiahf(BigDecimal.valueOf(Double.parseDouble(accumulatedIsiahf)));
        }

        if (accumulatedBd != null && !accumulatedBd.isEmpty()) {
            psCalculationResult.setAccumulatedBd(BigDecimal.valueOf(Double.parseDouble(accumulatedBd)));
        }

        if (annualBonusLlt != null && !annualBonusLlt.isEmpty()) {
            psCalculationResult.setAnnualBonusLlt(BigDecimal.valueOf(Double.parseDouble(annualBonusLlt)));
        }

        if (income != null && !income.isEmpty()) {
            psCalculationResult.setIncome(BigDecimal.valueOf(Double.parseDouble(income)));
        }

        if (cost != null && !cost.isEmpty()) {
            psCalculationResult.setCost(BigDecimal.valueOf(Double.parseDouble(cost)));
        }

        if (netIncome != null && !netIncome.isEmpty()) {
            psCalculationResult.setNetIncome(BigDecimal.valueOf(Double.parseDouble(netIncome)));
        }

        if (afterTaxDeduction != null && !afterTaxDeduction.isEmpty()) {
            psCalculationResult.setAfterTaxDeduction(BigDecimal.valueOf(Double.parseDouble(afterTaxDeduction)));
        }

        if (fsAllowance != null && !fsAllowance.isEmpty()) {
            psCalculationResult.setFsAllowance(BigDecimal.valueOf(Double.parseDouble(fsAllowance)));
        }

        if (preTaxIncome != null && !preTaxIncome.isEmpty()) {
            psCalculationResult.setPreTaxIncome(BigDecimal.valueOf(Double.parseDouble(preTaxIncome)));
        }

        if (basicDeduction != null && !basicDeduction.isEmpty()) {
            psCalculationResult.setBasicDeduction(BigDecimal.valueOf(Double.parseDouble(basicDeduction)));
        }

        if (individualLlt != null && !individualLlt.isEmpty()) {
            psCalculationResult.setIndividualLlt(BigDecimal.valueOf(Double.parseDouble(individualLlt)));
        }

        if (taxCalculation != null && !taxCalculation.isEmpty()) {
            psCalculationResult.setTaxCalculation(BigDecimal.valueOf(Double.parseDouble(taxCalculation)));
        }

        if (otherBonus != null && !otherBonus.isEmpty()) {
            psCalculationResult.setOtherBonus(BigDecimal.valueOf(Double.parseDouble(otherBonus)));
        }

        if (travelAllowance != null && !travelAllowance.isEmpty()) {
            psCalculationResult.setTravelAllowance(BigDecimal.valueOf(Double.parseDouble(travelAllowance)));
        }

        if (accumulatedChildCare != null && !accumulatedChildCare.isEmpty()) {
            psCalculationResult.setAccumulatedChildCare(BigDecimal.valueOf(Double.parseDouble(accumulatedChildCare)));
        }

        if (entryDate != null && !entryDate.isEmpty()) {
            psCalculationResult.setEntryDate(DateUtil.parse(entryDate, "yyyy-MM-dd"));
        }

        CalculationRules calculationRules = new CalculationRules();
        PolicyDto spPolicy = getPolicy(getValue(entry, "s0112"), duration, policy);
        if (spPolicy != null) {
            calculationRules.setSpAccuracy(spPolicy.getSpAccuracy());
            calculationRules.setSpRoundMode(Round(spPolicy.getSpRoundMode()));
        }
        PolicyDto pfPolicy = getPolicy(getValue(entry, "s0167"), duration, policy);
        if (pfPolicy != null) {
            calculationRules.setPfAccuracy(pfPolicy.getPfAccuracy());
            calculationRules.setPfRoundMode(Round(pfPolicy.getPfRoundMode()));
        }

        BigDecimal s0116 = getValueBigDecimal(entry, "s0116");
        BigDecimal s0117 = getValueBigDecimal(entry, "s0117");
        BigDecimal s0118 = getValueBigDecimal(entry, "s0118");
        BigDecimal s0120 = getValueBigDecimal(entry, "s0120");
        BigDecimal s0121 = getValueBigDecimal(entry, "s0121");
        BigDecimal s0122 = getValueBigDecimal(entry, "s0122");
        BigDecimal s0124 = getValueBigDecimal(entry, "s0124");
        BigDecimal s0125 = getValueBigDecimal(entry, "s0125");
        BigDecimal s0126 = getValueBigDecimal(entry, "s0126");
        BigDecimal s0161 = getValueBigDecimal(entry, "s0161");
        BigDecimal s0162 = getValueBigDecimal(entry, "s0162");
        BigDecimal s0163 = getValueBigDecimal(entry, "s0163");
        BigDecimal s0168 = getValueBigDecimal(entry, "s0168");
        BigDecimal s0169 = getValueBigDecimal(entry, "s0169");
        BigDecimal s0170 = getValueBigDecimal(entry, "s0170");
        BigDecimal s0129 = getValueBigDecimal(entry, "s0129");
        BigDecimal s0130 = getValueBigDecimal(entry, "s0130");
        BigDecimal s0131 = getValueBigDecimal(entry, "s0131");
        BigDecimal s0132 = getValueBigDecimal(entry, "s0132");
        BigDecimal s0133 = getValueBigDecimal(entry, "s0133");
        BigDecimal s0134 = getValueBigDecimal(entry, "s0134");
        BigDecimal s0165 = getValueBigDecimal(entry, "s0165");
        BigDecimal s0166 = getValueBigDecimal(entry, "s0166");
        BigDecimal s0524 = getValueBigDecimal(entry, "s0524");
        BigDecimal s0525 = getValueBigDecimal(entry, "s0525");
        BigDecimal s0136 = getValueBigDecimal(entry, "s0136");
        BigDecimal s0137 = getValueBigDecimal(entry, "s0137");
        BigDecimal s0138 = getValueBigDecimal(entry, "s0138");
        BigDecimal s0140 = getValueBigDecimal(entry, "s0140");
        BigDecimal s0141 = getValueBigDecimal(entry, "s0141");
        BigDecimal s0142 = getValueBigDecimal(entry, "s0142");
        BigDecimal s0144 = getValueBigDecimal(entry, "s0144");
        BigDecimal s0145 = getValueBigDecimal(entry, "s0145");
        BigDecimal s0146 = getValueBigDecimal(entry, "s0146");
        BigDecimal s0148 = getValueBigDecimal(entry, "s0148");
        BigDecimal s0149 = getValueBigDecimal(entry, "s0149");
        BigDecimal s0150 = getValueBigDecimal(entry, "s0150");
        BigDecimal s0152 = getValueBigDecimal(entry, "s0152");
        BigDecimal s0153 = getValueBigDecimal(entry, "s0153");
        BigDecimal s0154 = getValueBigDecimal(entry, "s0154");
        BigDecimal s0156 = getValueBigDecimal(entry, "s0156");
        BigDecimal s0157 = getValueBigDecimal(entry, "s0157");
        BigDecimal s0158 = getValueBigDecimal(entry, "s0158");
        BigDecimal s0175 = getValueBigDecimal(entry, "s0175");
        BigDecimal s0176 = getValueBigDecimal(entry, "s0176");
        BigDecimal s0177 = getValueBigDecimal(entry, "s0177");
        BigDecimal s0179 = getValueBigDecimal(entry, "s0179");
        BigDecimal s0180 = getValueBigDecimal(entry, "s0180");
        BigDecimal s0181 = getValueBigDecimal(entry, "s0181");
        BigDecimal s0475 = getValueBigDecimal(entry, "s0475");
        BigDecimal s0336 = getValueBigDecimal(entry, "s0336");

        BigDecimal pension = s0116.multiply(s0117).add(s0118).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal medical = s0120.multiply(s0121).add(s0122).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal unemployment = s0124.multiply(s0125).add(s0126).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal housingFund = s0161.multiply(s0162).add(s0163).setScale(calculationRules.getPfAccuracy(), calculationRules.getPfRoundMode());
        BigDecimal steHousingFund = s0168.multiply(s0169).add(s0170).setScale(calculationRules.getPfAccuracy(), calculationRules.getPfRoundMode());
        BigDecimal socialBenefitsAdjustment = s0129.subtract(s0130).add(s0131).subtract(s0132).add(s0133).subtract(s0134);
        BigDecimal housingFundAdjustment = s0165.subtract(s0166).add(s0524).subtract(s0525);

        BigDecimal companyPension = s0136.multiply(s0137).add(s0138).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal companyMedical = s0140.multiply(s0141).add(s0142).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal companyUnemployment = s0144.multiply(s0145).add(s0146).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal companyMaternity = s0148.multiply(s0149).add(s0150).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal companyWri = s0152.multiply(s0153).add(s0154).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal companyDisability = s0156.multiply(s0157).add(s0158).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal companyHousingFund = s0175.multiply(s0176).add(s0177).setScale(calculationRules.getPfAccuracy(), calculationRules.getPfRoundMode());
        BigDecimal companyShf = s0179.multiply(s0180).add(s0181).setScale(calculationRules.getPfAccuracy(), calculationRules.getPfRoundMode());

        BigDecimal pretaxAdjustment = s0475.subtract(s0336);

        psCalculationResult.setPension(pension);
        psCalculationResult.setMedical(medical);
        psCalculationResult.setUnemployment(unemployment);
        psCalculationResult.setHousingFund(housingFund);
        psCalculationResult.setSteHousingFund(steHousingFund);
        psCalculationResult.setSocialBenefitsAdjustment(socialBenefitsAdjustment);
        psCalculationResult.setHousingFundAdjustment(housingFundAdjustment);
        psCalculationResult.setCompanyPension(companyPension);
        psCalculationResult.setCompanyMedical(companyMedical);
        psCalculationResult.setCompanyUnemployment(companyUnemployment);
        psCalculationResult.setCompanyMaternity(companyMaternity);
        psCalculationResult.setCompanyWri(companyWri);
        psCalculationResult.setCompanyDisability(companyDisability);
        psCalculationResult.setCompanyHousingFund(companyHousingFund);
        psCalculationResult.setCompanyShf(companyShf);
        psCalculationResult.setPretaxAdjustment(pretaxAdjustment);
        psCalculationResult.setCompanyLlt(BigDecimal.ZERO);
        psCalculationResult.setCompanyTax(BigDecimal.ZERO);


        psCalculationResult.setGroupIds("7");
        PsCalculationResult pcr = new PsCalculationResult();
        pcr.setDuration(duration);
        pcr.setIdNo(psCalculationResult.getIdNo());
        List<PsCalculationResult> results = psCalculationResultMapper.selectPsCalculationResultCheckList(pcr);

        if (CollUtil.isNotEmpty(results)) {
            psCalculationResult.setId(results.get(0).getId());
            psCalculationResult.setUpdateTime(new Date());
            psCalculationResultMapper.updatePsCalculationResult(psCalculationResult);
        } else {
            psCalculationResult.setCreateTime(new Date());
            psCalculationResultMapper.insertPsCalculationResult(psCalculationResult);
        }
    }

    private void insertPayroll(Map<String, Object> entry, Map<String, PolicyDto> policy, PayrollDto payroll) {
        PsPayroll psPayroll = new PsPayroll();
        String department = getValue(entry, "s0002");
        String duration = payroll.getPeriod();
        String idNo = getValue(entry, "s0007");
        String name = getValue(entry, "s0008");
        String enname = getValue(entry, "s0182");
        String expense = getValue(entry, "s0529");
        BigDecimal foreignerAllowance = getValueBigDecimal(entry, "s0353");
        String ipAnnuity = getValue(entry, "s0404");
        String IIT = getValue(entry, "s0444");
        String remarks = getValue(entry, "s0575");
        String epHeatingFee = getValue(entry, "s0254");
        String commercialInsurance = getValue(entry, "e009");
        if (commercialInsurance != null && !commercialInsurance.isEmpty()) {
            psPayroll.setCommercialInsurance(BigDecimal.valueOf(Double.parseDouble(commercialInsurance)));
        }
        if (entry.containsKey("e017")) {
            String totalCost = getValue(entry, "e017");
            if (totalCost != null && !totalCost.isEmpty()) {
                psPayroll.setTotalCost(BigDecimal.valueOf(Double.parseDouble(totalCost)));
            }
        }
        if (entry.containsKey("e001")) {
            String unionFee = getValue(entry, "e001");
            if (unionFee != null && !unionFee.isEmpty()) {
                psPayroll.setEpUnionFee(BigDecimal.valueOf(Double.parseDouble(unionFee)));
            }
        }
        if (entry.containsKey("e005")) {
            String employerLiability = getValue(entry, "e005");
            if (employerLiability != null && !employerLiability.isEmpty()) {
                psPayroll.setEmployerLiability(BigDecimal.valueOf(Double.parseDouble(employerLiability)));
            }
        }
        if (entry.containsKey("e006")) {
            String deposit = getValue(entry, "e006");
            if (deposit != null && !deposit.isEmpty()) {
                psPayroll.setDeposit(BigDecimal.valueOf(Double.parseDouble(deposit)));
            }
        }
        if (entry.containsKey("e007")) {
            String serviceFee = getValue(entry, "e007");
            if (serviceFee != null && !serviceFee.isEmpty()) {
                psPayroll.setServiceFee(BigDecimal.valueOf(Double.parseDouble(serviceFee)));
            }
        }
        if (entry.containsKey("e008")) {
            String valueAddedTax = getValue(entry, "e008");
            if (valueAddedTax != null && !valueAddedTax.isEmpty()) {
                psPayroll.setValueAddedTax(BigDecimal.valueOf(Double.parseDouble(valueAddedTax)));
            }
        }

        if (entry.containsKey("e019")) {
            String serviceFeeTax = getValue(entry, "e019");
            if (serviceFeeTax != null && !serviceFeeTax.isEmpty()) {
                psPayroll.setServiceFeeTax(new BigDecimal(serviceFeeTax));
            }
        }

        if (department != null && !department.isEmpty()) {
            psPayroll.setDepartment(department);
        }

        if (duration != null && !duration.isEmpty()) {
            psPayroll.setDuration(duration);
        }

        if (idNo != null && !idNo.isEmpty()) {
            psPayroll.setIdNo(idNo);
        }

        if (name != null && !name.isEmpty()) {
            if (enname != null && !enname.isEmpty()) {
                psPayroll.setName(name+" "+enname);
            }else {
                psPayroll.setName(name);
            }
        }


        if (expense != null && !expense.isEmpty()) {
            psPayroll.setExpense(BigDecimal.valueOf(Double.parseDouble(expense)));
        }

        psPayroll.setForeignerAllowance(foreignerAllowance);

        if (ipAnnuity != null && !ipAnnuity.isEmpty()) {
            psPayroll.setIpAnnuity(BigDecimal.valueOf(Double.parseDouble(ipAnnuity)));
        }

        if (IIT != null && !IIT.isEmpty()) {
            psPayroll.setIIT(BigDecimal.valueOf(Double.parseDouble(IIT)));
        }

        if (remarks != null && !remarks.isEmpty()) {
            psPayroll.setRemarks(remarks);
        }

        if (epHeatingFee != null && !epHeatingFee.isEmpty()) {
            psPayroll.setEpHeatingFee(BigDecimal.valueOf(Double.parseDouble(epHeatingFee)));
        }


        CalculationRules calculationRules = new CalculationRules();
        PolicyDto spPolicy = getPolicy(getValue(entry, "s0112"), duration, policy);
        if (spPolicy != null) {
            calculationRules.setSpAccuracy(spPolicy.getSpAccuracy());
            calculationRules.setSpRoundMode(Round(spPolicy.getSpRoundMode()));
        }
        PolicyDto pfPolicy = getPolicy(getValue(entry, "s0167"), duration, policy);
        if (pfPolicy != null) {
            calculationRules.setPfAccuracy(pfPolicy.getPfAccuracy());
            calculationRules.setPfRoundMode(Round(pfPolicy.getPfRoundMode()));
        }
        BigDecimal s0116 = getValueBigDecimal(entry, "s0116");
        BigDecimal s0117 = getValueBigDecimal(entry, "s0117");
        BigDecimal s0118 = getValueBigDecimal(entry, "s0118");
        BigDecimal s0120 = getValueBigDecimal(entry, "s0120");
        BigDecimal s0121 = getValueBigDecimal(entry, "s0121");
        BigDecimal s0122 = getValueBigDecimal(entry, "s0122");
        BigDecimal s0124 = getValueBigDecimal(entry, "s0124");
        BigDecimal s0125 = getValueBigDecimal(entry, "s0125");
        BigDecimal s0126 = getValueBigDecimal(entry, "s0126");
        BigDecimal s0161 = getValueBigDecimal(entry, "s0161");
        BigDecimal s0162 = getValueBigDecimal(entry, "s0162");
        BigDecimal s0163 = getValueBigDecimal(entry, "s0163");
        BigDecimal s0168 = getValueBigDecimal(entry, "s0168");
        BigDecimal s0169 = getValueBigDecimal(entry, "s0169");
        BigDecimal s0170 = getValueBigDecimal(entry, "s0170");
        BigDecimal s0129 = getValueBigDecimal(entry, "s0129");
        BigDecimal s0130 = getValueBigDecimal(entry, "s0130");
        BigDecimal s0131 = getValueBigDecimal(entry, "s0131");
        BigDecimal s0132 = getValueBigDecimal(entry, "s0132");
        BigDecimal s0133 = getValueBigDecimal(entry, "s0133");
        BigDecimal s0134 = getValueBigDecimal(entry, "s0134");
        BigDecimal s0165 = getValueBigDecimal(entry, "s0165");
        BigDecimal s0166 = getValueBigDecimal(entry, "s0166");
        BigDecimal s0524 = getValueBigDecimal(entry, "s0524");
        BigDecimal s0525 = getValueBigDecimal(entry, "s0525");
        BigDecimal s0136 = getValueBigDecimal(entry, "s0136");
        BigDecimal s0137 = getValueBigDecimal(entry, "s0137");
        BigDecimal s0138 = getValueBigDecimal(entry, "s0138");
        BigDecimal s0140 = getValueBigDecimal(entry, "s0140");
        BigDecimal s0141 = getValueBigDecimal(entry, "s0141");
        BigDecimal s0142 = getValueBigDecimal(entry, "s0142");
        BigDecimal s0144 = getValueBigDecimal(entry, "s0144");
        BigDecimal s0145 = getValueBigDecimal(entry, "s0145");
        BigDecimal s0146 = getValueBigDecimal(entry, "s0146");
        BigDecimal s0148 = getValueBigDecimal(entry, "s0148");
        BigDecimal s0149 = getValueBigDecimal(entry, "s0149");
        BigDecimal s0150 = getValueBigDecimal(entry, "s0150");
        BigDecimal s0152 = getValueBigDecimal(entry, "s0152");
        BigDecimal s0153 = getValueBigDecimal(entry, "s0153");
        BigDecimal s0154 = getValueBigDecimal(entry, "s0154");
        BigDecimal s0156 = getValueBigDecimal(entry, "s0156");
        BigDecimal s0157 = getValueBigDecimal(entry, "s0157");
        BigDecimal s0158 = getValueBigDecimal(entry, "s0158");
        BigDecimal s0175 = getValueBigDecimal(entry, "s0175");
        BigDecimal s0176 = getValueBigDecimal(entry, "s0176");
        BigDecimal s0177 = getValueBigDecimal(entry, "s0177");
        BigDecimal s0179 = getValueBigDecimal(entry, "s0179");
        BigDecimal s0180 = getValueBigDecimal(entry, "s0180");
        BigDecimal s0181 = getValueBigDecimal(entry, "s0181");
        BigDecimal S0472 = getValueBigDecimal(entry, "s0472");
        BigDecimal S0529 = getValueBigDecimal(entry, "s0529");
        BigDecimal s0351 = getValueBigDecimal(entry, "s0351");
        BigDecimal s0352 = getValueBigDecimal(entry, "s0352");
        BigDecimal s0343 = getValueBigDecimal(entry, "s0343");

        BigDecimal netIncome = S0472.subtract(S0529).subtract(s0351).subtract(s0352).subtract(foreignerAllowance);

        BigDecimal IpPension = s0116.multiply(s0117).add(s0118).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal IpMedical = s0120.multiply(s0121).add(s0122).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal IpUnemployment = s0124.multiply(s0125).add(s0126).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal ipHousingFund = (s0161.multiply(s0162).add(s0163).setScale(calculationRules.getPfAccuracy(), calculationRules.getPfRoundMode())).add(s0168.multiply(s0169).add(s0170).setScale(calculationRules.getPfAccuracy(), calculationRules.getPfRoundMode()));

        BigDecimal balanceCharge = s0129.subtract(s0130).add(s0131).subtract(s0132).add(s0133).subtract(s0134).add(s0165).subtract(s0166).add(s0524).subtract(s0525);
        BigDecimal epPension = s0136.multiply(s0137).add(s0138).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal epMedical = s0140.multiply(s0141).add(s0142).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal epUnemployment = s0144.multiply(s0145).add(s0146).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal maternity = s0148.multiply(s0149).add(s0150).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal workRelatedInjury = s0152.multiply(s0153).add(s0154).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal disability = s0156.multiply(s0157).add(s0158).setScale(calculationRules.getSpAccuracy(), calculationRules.getSpRoundMode());
        BigDecimal epHousingFund = (s0175.multiply(s0176).add(s0177).setScale(calculationRules.getPfAccuracy(), calculationRules.getPfRoundMode())).add(s0179.multiply(s0180).add(s0181).setScale(calculationRules.getPfAccuracy(), calculationRules.getPfRoundMode()));

        psPayroll.setNetIncome(netIncome);
        psPayroll.setIpPension(IpPension);
        psPayroll.setIpMedical(IpMedical);
        psPayroll.setIpUnemployment(IpUnemployment);
        psPayroll.setIpHousingFund(ipHousingFund);
        psPayroll.setBalanceCharge(balanceCharge);
        psPayroll.setEpPension(epPension);
        psPayroll.setEpMedical(epMedical);
        psPayroll.setEpUnemployment(epUnemployment);
        psPayroll.setMaternity(maternity);
        psPayroll.setWorkRelatedInjury(workRelatedInjury);
        psPayroll.setDisability(disability);
        psPayroll.setEpHousingFund(epHousingFund);
        psPayroll.setEpHousingFund(epHousingFund);

        psPayroll.setGroupIds("7");

        PsPayroll pp = new PsPayroll();
        pp.setDuration(duration);
        pp.setIdNo(psPayroll.getIdNo());
        List<PsPayroll> psPayrolls = psPayrollMapper.selectPsPayrollList(pp);
        if (CollUtil.isNotEmpty(psPayrolls)) {
            psPayroll.setId(psPayrolls.get(0).getId());
            psPayroll.setUpdateTime(new Date());
            psPayrollMapper.updatePsPayroll(psPayroll);
        } else {
            psPayroll.setCreateTime(new Date());
            psPayrollMapper.insertPsPayroll(psPayroll);
        }
    }

    private void insertBasicInfo(Map<String, Object> entry, PayrollDto payroll) {
        PsBasicInfo psBasicInfo = new PsBasicInfo();
        String durationValue = payroll.getPeriod();
        String basicSalaryvalue = getValue(entry, "s0095");
        String idNoValue = getValue(entry, "s0007");
        String nameValue = getValue(entry, "s0008");
        String enNameValue = getValue(entry, "s0182");
        String genderValue = getValue(entry, "s0009");
        String workAdressValue = getValue(entry, "s0112");
        String socialBenefitsAddressValue = getValue(entry, "s0112");
        String socialBenefitsAddressValue2 = getValue(entry, "s0112");
        String employmentNatureValue = getValue(entry, "s0011");
        String contractStarttimeValue = getValue(entry, "s0025");
        String leaveDateValue = getValue(entry, "s0026");
        String dailySalaryValue = getValue(entry, "s0061");
        String transportAllowanceValue = getValue(entry, "s0255");
        String communicationAllowanceValue = getValue(entry, "s0256");
        String mealAllowanceValue = getValue(entry, "s0257");
        String otherFixedAllowanceValue = getValue(entry, "s0258");
        String msaBaseValue = getValue(entry, "s0236");
        String nsaBaseValue = getValue(entry, "s0239");
        String hwaBaseValue = getValue(entry, "s0260");
        String hwhfBaseValue = getValue(entry, "s0264");
        String phfBaseValue = getValue(entry, "s0266");
        String fixedSubsidy = getValue(entry, "s0103");
        String fixedDeduction = getValue(entry, "s0104");
        String pensionBase = getValue(entry, "s0136");
        String medicalBase = getValue(entry, "s0140");
        String unemploymentBase = getValue(entry, "s0144");
        String maternityBase = getValue(entry, "s0148");
        String workRelatedInjuryBase = getValue(entry, "s0152");
        String disabilityBase = getValue(entry, "s0156");
        String housingFundBase = getValue(entry, "s0175");
        String shfBase = getValue(entry, "s0179");
        String personalPension = getValue(entry, "s0117");
        String personalMedical = getValue(entry, "s0121");
        String personalUnemployment = getValue(entry, "s0125");
        String personalHousingFund = getValue(entry, "s0162");
        String personalShfTe = getValue(entry, "s0169");
        String personalMedicalValue = getValue(entry, "s0122");
        String companyPensionValue = getValue(entry, "s0137");
        String companyMedicalValue = getValue(entry, "s0141");
        String companyUnemploymentValue = getValue(entry, "s0145");
        String companyMaternityValue = getValue(entry, "s0149");
        String companyWorkRelatedInjuryValue = getValue(entry, "s0153");
        String companyDisabilityValue = getValue(entry, "s0157");
        String companyHousingFundValue = getValue(entry, "s0176");
        String companyShfValue = getValue(entry, "s0180");
        String companyMedicalFixedNumberValue = getValue(entry, "s0142");
        String companyWriFixedNumberValue = getValue(entry, "s0154");
        String companyDisabilityFixedNumberValue = getValue(entry, "s0158");

        String fsAllowanceNoTaxValue = getValue(entry, "s0353");
        String taxNotIssuedValue = getValue(entry, "s0339");

        String taxFreeIncomeValue = getValue(entry, "s0356");
        String certificateTypeValue = getValue(entry, "s0012");
        String certificateNumberValue = getValue(entry, "s0013");
        String birthdayValue = getValue(entry, "s0014");
        String housingFundAccountValue = getValue(entry, "s0114");
        String bankAccountNameValue = getValue(entry, "s0021");
        String bankAccountValue = getValue(entry, "s0019");
        String bankValue = getValue(entry, "s0020");
        String nationalityValue = getValue(entry, "s0010");
        String departmentValue = getValue(entry, "s0002");
        String telephoneValue = getValue(entry, "s0015");
        String mailboxValue = getValue(entry, "s0016");
        String annualBonusInfoValue = getValue(entry, "s0437");
        String remarksValue = getValue(entry, "s0575");
        String commercialHealthInsuranceValue = getValue(entry, "s0405");
        String dailyAttendanceDaysValue = getValue(entry, "s0062");
        String monthlyAwardValue = getValue(entry, "s0183");
        String overtime150hoursValue = getValue(entry, "s0213");
        String overtime200hoursValue = getValue(entry, "s0216");
        String overtime300hoursValue = getValue(entry, "s0219");
        String fullAttendanceDaysValue = getValue(entry, "s0278");
        String ealDaysValue = getValue(entry, "s0281");
        String affairLeaveDaysValue = getValue(entry, "s0284");
        String absentEeismDaysValue = getValue(entry, "s0287");
        String sickLeaveDeductionValue = getValue(entry, "s0289");
        String transportationFeeValue = getValue(entry, "s0269");
        String communicationFeeValue = getValue(entry, "s0270");
        String ohtFeeValue = getValue(entry, "s0260");
        String pensionRepaymentValue1 = getValue(entry, "s0129");
        String pensionRepaymentValue2 = getValue(entry, "s0130");
        String medicalRepaymentValue1 = getValue(entry, "s0131");
        String medicalRepaymentValue2 = getValue(entry, "s0132");
        String unemploymentRepaymentValue1 = getValue(entry, "s0133");
        String unemploymentRepaymentValue2 = getValue(entry, "s0134");
        String housingFundPaymentValue1 = getValue(entry, "s0524");
        String housingFundPaymentValue2 = getValue(entry, "s0525");
        String housingFundPaymentValue3 = getValue(entry, "s0165");
        String housingFundPaymentValue4 = getValue(entry, "s0166");
        String taxCalculationValue = getValue(entry, "s0340");
        String afterTaxDeduction2Value = getValue(entry, "s0468");
        String cdRepaymentValue1 = getValue(entry, "s0515");
        String cdRepaymentValue2 = getValue(entry, "s0516");
        String birthdayGiftValue = getValue(entry, "s0267");
        String otherAllowanceValue = getValue(entry, "s0268");
        String heatingFeeValue = getValue(entry, "s0254");
        String costCenterValue = getValue(entry, "s0005");
        String actualPerformanceValue = getValue(entry, "s0171");
        String commissionValue = getValue(entry, "s0199");
        String otherBonusValue = getValue(entry, "s0188");
        String travelAllowanceValue = getValue(entry, "s0271");
        String accumulatedChildrenEducation = getValue(entry, "s0416");
        String accumulatedContinueEducation = getValue(entry, "s0418");
        String accumulatedHousingLoanInterest = getValue(entry, "s0420");
        String accumulatedHousingRental = getValue(entry, "s0422");
        String accumulatedCaringForTheElderly = getValue(entry, "s0424");
        String accumulatedChildCareValue = getValue(entry, "s0425");

        if (entry.containsKey("e009")) {
            String commercialInsuranceCost = getValue(entry, "e009");
            if (commercialInsuranceCost != null && !commercialInsuranceCost.isEmpty()) {
                psBasicInfo.setCommercialInsuranceCost(BigDecimal.valueOf(Double.parseDouble(commercialInsuranceCost)));
            }
        }
        if (entry.containsKey("e010")) {
            String taxPaymentPlace = getValue(entry, "e010");
            if (taxPaymentPlace != null && !taxPaymentPlace.isEmpty()) {
                psBasicInfo.setTaxPaymentPlace(taxPaymentPlace);
            }
        }
        if (entry.containsKey("e011")) {
            String csbRepayment = getValue(entry, "e011");
            if (csbRepayment != null && !csbRepayment.isEmpty()) {
                psBasicInfo.setCsbRepayment(BigDecimal.valueOf(Double.parseDouble(csbRepayment)));
            }
        }
        if (entry.containsKey("e012")) {
            String chfRepayment = getValue(entry, "e012");
            if (chfRepayment != null && !chfRepayment.isEmpty()) {
                psBasicInfo.setChfRepayment(BigDecimal.valueOf(Double.parseDouble(chfRepayment)));
            }
        }
        if (entry.containsKey("e013")) {
            String payrollBelong = getValue(entry, "e013");
            if (payrollBelong != null && !payrollBelong.isEmpty()) {
                psBasicInfo.setPayrollBelong(payrollBelong);
            }
        }
        if (entry.containsKey("e014")) {
            String paySBFlag = getValue(entry, "e014");
            if (paySBFlag != null && !paySBFlag.isEmpty()) {
                if ("是".equals(paySBFlag)) {
                    psBasicInfo.setPaySBFlag("Y");
                } else {
                    psBasicInfo.setPaySBFlag("N");
                }
            }
        }
        if (entry.containsKey("e015")) {
            String payHFFlag = getValue(entry, "e015");
            if (payHFFlag != null && !payHFFlag.isEmpty()) {
                if ("是".equals(payHFFlag)) {
                    psBasicInfo.setPayHFFlag("Y");
                } else {
                    psBasicInfo.setPayHFFlag("N");
                }
            }
        }
        if (entry.containsKey("e016")) {
            String payIITFlag = getValue(entry, "e016");
            if (payIITFlag != null && !payIITFlag.isEmpty()) {
                if ("是".equals(payIITFlag)) {
                    psBasicInfo.setPayIITFlag("Y");
                } else {
                    psBasicInfo.setPayIITFlag("N");
                }
            }
        }
        if (entry.containsKey("e018")) {
            String bankLocation = getValue(entry, "e018");
            if (bankLocation != null && !bankLocation.isEmpty()) {
                psBasicInfo.setBankLocation(bankLocation);
            }
        }

        SysEmployee clientEmployee = employeeService.selectClientEE(payroll.getEmpCode(), idNoValue);
        if (clientEmployee != null) {
            JSONObject extraData = ExtraAttributeUtils.readAsJson(clientEmployee.getExtraData());
            if (extraData != null) {
                psBasicInfo.setBillAddress(extraData.getString(ColumnEnum.EE_HOME_ADDR.getFId()));
                psBasicInfo.setPostcode(extraData.getString(ColumnEnum.EE_POST_CODE.getFId()));
                psBasicInfo.setResidenceType(extraData.getString(ColumnEnum.EE_REGISTERED_RESIDENCE_TYPE.getFId()));
                psBasicInfo.setResidenceAddress(extraData.getString(ColumnEnum.EE_RESIDENCE_ADDR.getFId()));
            }
        }

        EmployeeInfo eeContact = new EmployeeInfo();
        eeContact.setEmployeeCode(idNoValue);
        eeContact.setTableName(TableEnum.EMPLOYEE_CONTACT.getTabName());

        EmployeeInfo eeInfo = Optional.ofNullable(employeeInfoService.selectEmployeeInfoList(eeContact))
                .map(eeList -> eeList.get(0)).orElse(null);
        if (eeInfo != null) {
            JSONObject extraData = ExtraAttributeUtils.readAsJson(eeInfo.getExtraData());
            if (extraData != null) {
                psBasicInfo.setContractEndtime(extraData.getString(ColumnEnum.EE_CONTRACT_END_DATE.getFId()));
                psBasicInfo.setPreAfterTax(extraData.getString(ColumnEnum.EE_SALARY_BEFORE_OR_AFTER_TAX.getFId()));
                psBasicInfo.setPost(extraData.getString(ColumnEnum.EE_CONTRACT_POSITION.getFId()));
            }
        }

        eeContact.setTableName(TableEnum.EMPLOYEE_EE_PAYROLL_CRM.getTabName());
        eeInfo = Optional.ofNullable(employeeInfoService.selectEmployeeInfoList(eeContact))
                .map(eeList -> eeList.get(0)).orElse(null);
        if (eeInfo != null) {
            JSONObject extraData = ExtraAttributeUtils.readAsJson(eeInfo.getExtraData());
            if (extraData != null) {
                psBasicInfo.setClientType(extraData.getString(ColumnEnum.EE_SERVICE_TYPE.getFId()));
                psBasicInfo.setPayroll(extraData.getString(ColumnEnum.EE_IS_PAY_SLIPS.getFId()));
                psBasicInfo.setCommercialInsuranceLevel(extraData.getString(ColumnEnum.EE_BUSINESS_INSURANCE_LEVELS.getFId()));
            }
        }

        if (durationValue != null && !durationValue.isEmpty()) {
            psBasicInfo.setDuration(durationValue);
        }

        if (basicSalaryvalue != null && !basicSalaryvalue.isEmpty()) {
            psBasicInfo.setBasicSalary(BigDecimal.valueOf(Double.parseDouble(basicSalaryvalue)));
        }

        psBasicInfo.setErName(payroll.getEmpName());
        if (idNoValue != null && !idNoValue.isEmpty()) {
            psBasicInfo.setIdNo(idNoValue);
        }

        if (nameValue != null && !nameValue.isEmpty()) {
            psBasicInfo.setName(nameValue);
        }

        if (enNameValue != null && !enNameValue.isEmpty()) {
            psBasicInfo.setEnName(enNameValue);
        }
        if (genderValue != null && !genderValue.isEmpty()) {
            if ("男".equals(genderValue)) {
                psBasicInfo.setSex("Male");
            } else {
                psBasicInfo.setSex("Female");
            }
        }
        if (workAdressValue != null && !workAdressValue.isEmpty()) {
            psBasicInfo.setWorkAddress(workAdressValue);
        }

        if (socialBenefitsAddressValue != null && !socialBenefitsAddressValue.isEmpty()) {
            psBasicInfo.setSocialBenefitsAddress(socialBenefitsAddressValue);
        }

        if (socialBenefitsAddressValue2 != null && !socialBenefitsAddressValue2.isEmpty()) {
            psBasicInfo.setSocialBenefitsAddress(socialBenefitsAddressValue2);
        }

        if (employmentNatureValue != null && !employmentNatureValue.isEmpty()) {
            psBasicInfo.setEmploymentNature(employmentNatureValue);
        }

        if (contractStarttimeValue != null && !contractStarttimeValue.isEmpty()) {
            psBasicInfo.setContractStarttime(contractStarttimeValue);
        }


        if (leaveDateValue != null && !leaveDateValue.isEmpty()) {
            psBasicInfo.setLeaveDate(leaveDateValue);
        }


        if (dailySalaryValue != null && !dailySalaryValue.isEmpty()) {
            psBasicInfo.setDailySalary(BigDecimal.valueOf(Double.parseDouble(dailySalaryValue)));
        }

        if (transportAllowanceValue != null && !transportAllowanceValue.isEmpty()) {
            psBasicInfo.setTransportAllowance(BigDecimal.valueOf(Double.parseDouble(transportAllowanceValue)));
        }

        if (communicationAllowanceValue != null && !communicationAllowanceValue.isEmpty()) {
            psBasicInfo.setCommunicationAllowance(BigDecimal.valueOf(Double.parseDouble(communicationAllowanceValue)));
        }


        if (mealAllowanceValue != null && !mealAllowanceValue.isEmpty()) {
            psBasicInfo.setMealAllowance(BigDecimal.valueOf(Double.parseDouble(mealAllowanceValue)));
        }


        if (otherFixedAllowanceValue != null && !otherFixedAllowanceValue.isEmpty()) {
            psBasicInfo.setOtherFixedAllowance(BigDecimal.valueOf(Double.parseDouble(otherFixedAllowanceValue)));
        }


        if (msaBaseValue != null && !msaBaseValue.isEmpty()) {
            psBasicInfo.setMsaBase(BigDecimal.valueOf(Double.parseDouble(msaBaseValue)));
        }


        if (nsaBaseValue != null && !nsaBaseValue.isEmpty()) {
            psBasicInfo.setNsaBase(BigDecimal.valueOf(Double.parseDouble(nsaBaseValue)));
        }


        if (hwaBaseValue != null && !hwaBaseValue.isEmpty()) {
            psBasicInfo.setHwaBase(BigDecimal.valueOf(Double.parseDouble(hwaBaseValue)));
        }


        if (hwhfBaseValue != null && !hwhfBaseValue.isEmpty()) {
            psBasicInfo.setHwhfBase(BigDecimal.valueOf(Double.parseDouble(hwhfBaseValue)));
        }


        if (phfBaseValue != null && !phfBaseValue.isEmpty()) {
            psBasicInfo.setPhfBase(BigDecimal.valueOf(Double.parseDouble(phfBaseValue)));
        }

        if (fixedSubsidy != null && !fixedSubsidy.isEmpty()) {
            psBasicInfo.setFixedSubsidy(BigDecimal.valueOf(Double.parseDouble(fixedSubsidy)));
        }

        if (fixedDeduction != null && !fixedDeduction.isEmpty()) {
            psBasicInfo.setFixedDeduction(BigDecimal.valueOf(Double.parseDouble(fixedDeduction)));
        }

        if (pensionBase != null && !pensionBase.isEmpty()) {
            psBasicInfo.setPensionBase(BigDecimal.valueOf(Double.parseDouble(pensionBase)));
        }

        if (medicalBase != null && !medicalBase.isEmpty()) {
            psBasicInfo.setMedicalBase(BigDecimal.valueOf(Double.parseDouble(medicalBase)));
        }

        if (unemploymentBase != null && !unemploymentBase.isEmpty()) {
            psBasicInfo.setUnemploymentBase(BigDecimal.valueOf(Double.parseDouble(unemploymentBase)));
        }

        if (maternityBase != null && !maternityBase.isEmpty()) {
            psBasicInfo.setMaternityBase(BigDecimal.valueOf(Double.parseDouble(maternityBase)));
        }

        if (workRelatedInjuryBase != null && !workRelatedInjuryBase.isEmpty()) {
            psBasicInfo.setWorkRelatedInjuryBase(BigDecimal.valueOf(Double.parseDouble(workRelatedInjuryBase)));
        }

        if (disabilityBase != null && !disabilityBase.isEmpty()) {
            psBasicInfo.setDisabilityBase(BigDecimal.valueOf(Double.parseDouble(disabilityBase)));
        }

        if (housingFundBase != null && !housingFundBase.isEmpty()) {
            psBasicInfo.setHousingFundBase(BigDecimal.valueOf(Double.parseDouble(housingFundBase)));
        }

        if (shfBase != null && !shfBase.isEmpty()) {
            psBasicInfo.setShfBase(BigDecimal.valueOf(Double.parseDouble(shfBase)));
        }

        if (personalPension != null && !personalPension.isEmpty()) {
            psBasicInfo.setPersonalPension(personalPension);
        }

        if (personalMedical != null && !personalMedical.isEmpty()) {
            psBasicInfo.setPersonalMedical(personalMedical);
        }

        if (personalUnemployment != null && !personalUnemployment.isEmpty()) {
            psBasicInfo.setPersonalUnemployment(personalUnemployment);
        }

        if (personalHousingFund != null && !personalHousingFund.isEmpty()) {
            psBasicInfo.setPersonalHousingFund(personalHousingFund);
        }

        if (personalShfTe != null && !personalShfTe.isEmpty()) {
            psBasicInfo.setPersonalShfTe(personalShfTe);
        }

        if (personalMedicalValue != null && !personalMedicalValue.isEmpty()) {
            psBasicInfo.setPersonalMedicalFixedNumber(BigDecimal.valueOf(Double.parseDouble(personalMedicalValue)));
        }

        if (companyPensionValue != null && !companyPensionValue.isEmpty()) {
            psBasicInfo.setCompanyPension(companyPensionValue);
        }

        if (companyMedicalValue != null && !companyMedicalValue.isEmpty()) {
            psBasicInfo.setCompanyMedical(companyMedicalValue);
        }

        if (companyUnemploymentValue != null && !companyUnemploymentValue.isEmpty()) {
            psBasicInfo.setCompanyUnemployment(companyUnemploymentValue);
        }


        if (companyMaternityValue != null && !companyMaternityValue.isEmpty()) {
            psBasicInfo.setCompanyMaternity(companyMaternityValue);
        }


        if (companyWorkRelatedInjuryValue != null && !companyWorkRelatedInjuryValue.isEmpty()) {
            psBasicInfo.setCompanyWorkRelatedInjury(companyWorkRelatedInjuryValue);
        }


        if (companyDisabilityValue != null && !companyDisabilityValue.isEmpty()) {
            psBasicInfo.setCompanyDisability(companyDisabilityValue);
        }


        if (companyHousingFundValue != null && !companyHousingFundValue.isEmpty()) {
            psBasicInfo.setCompanyHousingFund(companyHousingFundValue);
        }


        if (companyShfValue != null && !companyShfValue.isEmpty()) {
            psBasicInfo.setCompanyShf(companyShfValue);
        }


        if (companyMedicalFixedNumberValue != null && !companyMedicalFixedNumberValue.isEmpty()) {
            psBasicInfo.setCompanyMedicalFixedNumber(
                    BigDecimal.valueOf(Double.parseDouble(companyMedicalFixedNumberValue))
            );
        }


        if (companyWriFixedNumberValue != null && !companyWriFixedNumberValue.isEmpty()) {
            psBasicInfo.setCompanyWriFixedNumber(
                    BigDecimal.valueOf(Double.parseDouble(companyWriFixedNumberValue))
            );
        }


        if (companyDisabilityFixedNumberValue != null && !companyDisabilityFixedNumberValue.isEmpty()) {
            psBasicInfo.setCompanyDisabilityFixedNumber(
                    BigDecimal.valueOf(Double.parseDouble(companyDisabilityFixedNumberValue))
            );
        }


        if (fsAllowanceNoTaxValue != null && !fsAllowanceNoTaxValue.isEmpty()) {

            psBasicInfo.setFsAllowance(
                    BigDecimal.valueOf(Double.parseDouble(fsAllowanceNoTaxValue))
            );
        }


        if (taxNotIssuedValue != null && !taxNotIssuedValue.isEmpty()) {
            psBasicInfo.setTaxNotIssued(
                    BigDecimal.valueOf(Double.parseDouble(taxNotIssuedValue))
            );
        }


        if (taxFreeIncomeValue != null && !taxFreeIncomeValue.isEmpty()) {
            psBasicInfo.setTaxFreeIncome(
                    BigDecimal.valueOf(Double.parseDouble(taxFreeIncomeValue))
            );
        }


        if (certificateTypeValue != null && !certificateTypeValue.isEmpty()) {
            psBasicInfo.setCertificateType(certificateTypeValue);
        }


        if (certificateNumberValue != null && !certificateNumberValue.isEmpty()) {
            psBasicInfo.setCertificateNumber(certificateNumberValue);
        }


        if (birthdayValue != null && !birthdayValue.isEmpty()) {
            psBasicInfo.setBirthday(birthdayValue);
        }


        if (housingFundAccountValue != null && !housingFundAccountValue.isEmpty()) {
            psBasicInfo.setHousingFundAccount(housingFundAccountValue);
        }


        if (bankAccountNameValue != null && !bankAccountNameValue.isEmpty()) {
            psBasicInfo.setBankAccounName(bankAccountNameValue);
        }


        if (bankAccountValue != null && !bankAccountValue.isEmpty()) {
            psBasicInfo.setBankAccount(bankAccountValue);
        }


        if (bankValue != null && !bankValue.isEmpty()) {
            psBasicInfo.setBank(bankValue);
        }


        if (departmentValue != null && !departmentValue.isEmpty()) {
            psBasicInfo.setDepartment(departmentValue);
        }


        if (nationalityValue != null && !nationalityValue.isEmpty()) {
            psBasicInfo.setNationality(nationalityValue);
        }


        if (telephoneValue != null && !telephoneValue.isEmpty()) {
            psBasicInfo.setTelephone(telephoneValue);
        }


        if (mailboxValue != null && !mailboxValue.isEmpty()) {
            psBasicInfo.setMailbox(mailboxValue);
        }


        if (annualBonusInfoValue != null && !annualBonusInfoValue.isEmpty()) {
            psBasicInfo.setAnnualBonusInfo(annualBonusInfoValue);
        }

//            psBasicInfo.setThirteenSalaryMonth();

        if (remarksValue != null && !remarksValue.isEmpty()) {
            psBasicInfo.setRemarks(remarksValue);
        }


        if (commercialHealthInsuranceValue != null && !commercialHealthInsuranceValue.isEmpty()) {
            psBasicInfo.setCommercialHealthInsurance(
                    BigDecimal.valueOf(Double.parseDouble(commercialHealthInsuranceValue))
            );
        }


        if (dailyAttendanceDaysValue != null && !dailyAttendanceDaysValue.isEmpty()) {
            psBasicInfo.setDailyAttendanceDays(
                    BigDecimal.valueOf(Double.parseDouble(dailyAttendanceDaysValue))
            );
        }


        if (monthlyAwardValue != null && !monthlyAwardValue.isEmpty()) {
            psBasicInfo.setMonthlyAward(
                    BigDecimal.valueOf(Double.parseDouble(monthlyAwardValue))
            );
        }


        if (overtime150hoursValue != null && !overtime150hoursValue.isEmpty()) {
            psBasicInfo.setOvertime150hours(overtime150hoursValue);
        }


        if (overtime200hoursValue != null && !overtime200hoursValue.isEmpty()) {
            psBasicInfo.setOvertime200hours(overtime200hoursValue);
        }


        if (overtime300hoursValue != null && !overtime300hoursValue.isEmpty()) {
            psBasicInfo.setOvertime300hours(overtime300hoursValue);
        }

        if (fullAttendanceDaysValue != null && !fullAttendanceDaysValue.isEmpty()) {
            psBasicInfo.setFullAttendanceDays(
                    BigDecimal.valueOf(Double.parseDouble(fullAttendanceDaysValue))
            );
        }

        if (ealDaysValue != null && !ealDaysValue.isEmpty()) {
            psBasicInfo.setEalDays(
                    BigDecimal.valueOf(Double.parseDouble(ealDaysValue))
            );
        }

        if (affairLeaveDaysValue != null && !affairLeaveDaysValue.isEmpty()) {
            psBasicInfo.setAffairLeaveDays(
                    BigDecimal.valueOf(Double.parseDouble(affairLeaveDaysValue))
            );
        }
        if (absentEeismDaysValue != null && !absentEeismDaysValue.isEmpty()) {
            psBasicInfo.setAbsentEeismDays(
                    BigDecimal.valueOf(Double.parseDouble(absentEeismDaysValue))
            );
        }

//            psBasicInfo.setSickLeaveDays();


        if (sickLeaveDeductionValue != null && !sickLeaveDeductionValue.isEmpty()) {
            psBasicInfo.setSickLeaveDeduction(
                    BigDecimal.valueOf(Double.parseDouble(sickLeaveDeductionValue))
            );
        }


        if (transportationFeeValue != null && !transportationFeeValue.isEmpty()) {
            psBasicInfo.setTransportationFee(
                    BigDecimal.valueOf(Double.parseDouble(transportationFeeValue))
            );
        }

        if (communicationFeeValue != null && !communicationFeeValue.isEmpty()) {
            psBasicInfo.setCommunicationFee(
                    BigDecimal.valueOf(Double.parseDouble(communicationFeeValue))
            );
        }


        if (ohtFeeValue != null && !ohtFeeValue.isEmpty()) {
            psBasicInfo.setOhtFee(
                    BigDecimal.valueOf(Double.parseDouble(ohtFeeValue))
            );
        }


        if (pensionRepaymentValue1 != null && !pensionRepaymentValue1.isEmpty() &&
                pensionRepaymentValue2 != null && !pensionRepaymentValue2.isEmpty()) {

            BigDecimal value1 = BigDecimal.valueOf(Double.parseDouble(pensionRepaymentValue1));
            BigDecimal value2 = BigDecimal.valueOf(Double.parseDouble(pensionRepaymentValue2));

            psBasicInfo.setPensionRepayment(value1.subtract(value2));
        }


        if (medicalRepaymentValue1 != null && !medicalRepaymentValue1.isEmpty() &&
                medicalRepaymentValue2 != null && !medicalRepaymentValue2.isEmpty()) {

            BigDecimal value1 = BigDecimal.valueOf(Double.parseDouble(medicalRepaymentValue1));
            BigDecimal value2 = BigDecimal.valueOf(Double.parseDouble(medicalRepaymentValue2));

            psBasicInfo.setMedicalRepayment(value1.subtract(value2));
        }

        if (unemploymentRepaymentValue1 != null && !unemploymentRepaymentValue1.isEmpty() &&
                unemploymentRepaymentValue2 != null && !unemploymentRepaymentValue2.isEmpty()) {

            BigDecimal value1 = BigDecimal.valueOf(Double.parseDouble(unemploymentRepaymentValue1));
            BigDecimal value2 = BigDecimal.valueOf(Double.parseDouble(unemploymentRepaymentValue2));

            psBasicInfo.setUnemploymentRepayment(value1.subtract(value2));
        }
        if (1 == 1) {
            BigDecimal value1 = new BigDecimal(housingFundPaymentValue1 != null && !housingFundPaymentValue1.isEmpty() ? housingFundPaymentValue1 : "0");
            BigDecimal value2 = new BigDecimal(housingFundPaymentValue2 != null && !housingFundPaymentValue2.isEmpty() ? housingFundPaymentValue2 : "0");
            BigDecimal value3 = new BigDecimal(housingFundPaymentValue3 != null && !housingFundPaymentValue3.isEmpty() ? housingFundPaymentValue3 : "0");
            BigDecimal value4 = new BigDecimal(housingFundPaymentValue4 != null && !housingFundPaymentValue4.isEmpty() ? housingFundPaymentValue4 : "0");

            psBasicInfo.setHousingFundPayment(value1.subtract(value2).add(value3.subtract(value4)));
        }
        if (taxCalculationValue != null && !taxCalculationValue.isEmpty()) {
            BigDecimal taxCalculation = BigDecimal.valueOf(Double.parseDouble(taxCalculationValue));
            psBasicInfo.setTaxCalculation(taxCalculation);
        }

        if (afterTaxDeduction2Value != null && !afterTaxDeduction2Value.isEmpty()) {
            BigDecimal afterTaxDeduction2 = BigDecimal.valueOf(Double.parseDouble(afterTaxDeduction2Value));
            psBasicInfo.setAfterTaxDeduction2(afterTaxDeduction2);
        }


        if (cdRepaymentValue1 != null && !cdRepaymentValue1.isEmpty() &&
                cdRepaymentValue2 != null && !cdRepaymentValue2.isEmpty()) {

            BigDecimal value1 = BigDecimal.valueOf(Double.parseDouble(cdRepaymentValue1));
            BigDecimal value2 = BigDecimal.valueOf(Double.parseDouble(cdRepaymentValue2));

            psBasicInfo.setCdRepayment(value1.subtract(value2));
        }

        if (birthdayGiftValue != null && !birthdayGiftValue.isEmpty()) {
            BigDecimal birthdayGift = BigDecimal.valueOf(Double.parseDouble(birthdayGiftValue));
            psBasicInfo.setBirthdayGift(birthdayGift);
        }


        if (otherAllowanceValue != null && !otherAllowanceValue.isEmpty()) {
            BigDecimal otherAllowance = BigDecimal.valueOf(Double.parseDouble(otherAllowanceValue));
            psBasicInfo.setOtherAllowance(otherAllowance);
        }


        if (heatingFeeValue != null && !heatingFeeValue.isEmpty()) {
            BigDecimal heatingFee = BigDecimal.valueOf(Double.parseDouble(heatingFeeValue));
            psBasicInfo.setHeatingFee(heatingFee);
        }


        if (costCenterValue != null && !costCenterValue.isEmpty()) {
            psBasicInfo.setCostCenter(costCenterValue);
        }


        if (actualPerformanceValue != null && !actualPerformanceValue.isEmpty()) {
            BigDecimal actualPerformance = BigDecimal.valueOf(Double.parseDouble(actualPerformanceValue));
            psBasicInfo.setActualPerformance(actualPerformance);
        }


        if (commissionValue != null && !commissionValue.isEmpty()) {
            BigDecimal commission = BigDecimal.valueOf(Double.parseDouble(commissionValue));
            psBasicInfo.setCommission(commission);
        }


        if (otherBonusValue != null && !otherBonusValue.isEmpty()) {
            BigDecimal otherBonus = BigDecimal.valueOf(Double.parseDouble(otherBonusValue));
            psBasicInfo.setOtherBonus(otherBonus);
        }


        if (travelAllowanceValue != null && !travelAllowanceValue.isEmpty()) {
            BigDecimal travelAllowance = BigDecimal.valueOf(Double.parseDouble(travelAllowanceValue));
            psBasicInfo.setTravelAllowance(travelAllowance);
        }


        if (accumulatedChildCareValue != null && !accumulatedChildCareValue.isEmpty()) {
            BigDecimal accumulatedChildCare = BigDecimal.valueOf(Double.parseDouble(accumulatedChildCareValue));
            psBasicInfo.setAccumulatedChildCare(accumulatedChildCare);
        }

        if (accumulatedChildrenEducation != null && !accumulatedChildrenEducation.isEmpty()) {
            BigDecimal accumulatedChildrenEducationValue = BigDecimal.valueOf(Double.parseDouble(accumulatedChildrenEducation));
            psBasicInfo.setAccumulatedChildEducation(accumulatedChildrenEducationValue);
        }

        if (accumulatedContinueEducation != null && !accumulatedContinueEducation.isEmpty()) {
            BigDecimal accumulatedContinueEducationValue = BigDecimal.valueOf(Double.parseDouble(accumulatedContinueEducation));
            psBasicInfo.setAccumulatedContinuingEducation(accumulatedContinueEducationValue);
        }

        if (accumulatedHousingLoanInterest != null && !accumulatedHousingLoanInterest.isEmpty()) {
            BigDecimal accumulatedHousingLoanInterestValue = BigDecimal.valueOf(Double.parseDouble(accumulatedHousingLoanInterest));
            psBasicInfo.setAccumulatedHli(accumulatedHousingLoanInterestValue);
        }

        if (accumulatedHousingRental != null && !accumulatedHousingRental.isEmpty()) {
            BigDecimal accumulatedHousingRentalValue = BigDecimal.valueOf(Double.parseDouble(accumulatedHousingRental));
            psBasicInfo.setAccumulatedHousingRent(accumulatedHousingRentalValue);

        }

        if (accumulatedCaringForTheElderly != null && !accumulatedCaringForTheElderly.isEmpty()) {
            BigDecimal accumulatedCaringForTheElderlyValue = BigDecimal.valueOf(Double.parseDouble(accumulatedCaringForTheElderly));
            psBasicInfo.setAccumulatedSfte(accumulatedCaringForTheElderlyValue);
        }


        psBasicInfo.setClientCode(payroll.getEmpCode());

        psBasicInfo.setGroupIds("7");

        // 验证是否存在这个基本信息
        PsBasicInfo pbi = new PsBasicInfo();
        pbi.setDuration(durationValue);
        pbi.setIdNo(psBasicInfo.getIdNo());

        List<PsBasicInfo> pbiList = psBasicInfoMapper.selectPsBasicInfoCheckList(pbi);
        if (CollUtil.isNotEmpty(pbiList)) {
            psBasicInfo.setId(pbiList.get(0).getId());
            psBasicInfo.setUpdateTime(new Date());
            psBasicInfoMapper.updatePsBasicInfo(pbi);
        } else {
            psBasicInfo.setCreateTime(new Date());
            psBasicInfoMapper.insertPsBasicInfo(psBasicInfo);
        }
    }

    private void insertCoverInfo(Map<String, Object> data, PayrollDto payroll) {
        PsCoverInfo psCoverInfo = new PsCoverInfo();
        String currency = "CNY";
        BigDecimal exchangeRate = BigDecimal.ONE;
        BigDecimal exchangeRate1 = new BigDecimal(0);
        psCoverInfo.setErNo(payroll.getEmpCode());
        psCoverInfo.setDuration(payroll.getPeriod());
        List<PsCoverInfo> psCoverInfoList = psOpLogService.selectPsCoverInfoList(psCoverInfo);
        if (CollUtil.isNotEmpty(psCoverInfoList)) {
            psOpLogService.deletePsCoverInfoByCondition(psCoverInfo);
        }
        if (data.containsKey("e002")) {
            exchangeRate1 = getValueBigDecimal(data, "e002");
        }
        if (data.containsKey("e004")) {
            currency = getValue(data, "e004");
        }

        if (currency != null && !currency.isEmpty()) {
            psCoverInfo.setCurrency(currency);
        }
        if (!Objects.equals(exchangeRate1, new BigDecimal(0))) {
            psCoverInfo.setExchangeRate(exchangeRate1);
        } else {
            psCoverInfo.setExchangeRate(exchangeRate);
        }
        psCoverInfo.setDelFlag("0");
        psOpLogService.insertPsCoverInfo(psCoverInfo);
    }

    public String getValue(Map<String, Object> data, String key) {
        Object o = data.get(key);
        if (o == null) {
            return null;
        } else if (o instanceof String) {
            return (String) o;
        } else if (o instanceof Collection) {
            return (String) ((Collection) o).stream()
                    .filter(Objects::nonNull)
                    .map(item -> ObjectUtil.toString(item))
                    .collect(Collectors.joining(","));
        }
        return o.toString();
    }

    public BigDecimal getValueBigDecimal(Map<String, Object> data, String key) {
        Object value = data.get(key);
        return value != null ? new BigDecimal(value.toString()) : new BigDecimal(0);
    }

    public String getCity(String input) {
        if (input == null) {
            return null;
        }
//        String cleanedString = input.replaceAll("\\[|\\]|\\s", "");

        // 使用下划线连接各个城市
        return input.replace(",", "_");
    }

    private RoundingMode Round(int pfRoundMode) {
        if (pfRoundMode == 1) {
            return RoundingMode.HALF_UP;
        } else if (pfRoundMode == 2) {
            return RoundingMode.CEILING;
        } else if (pfRoundMode == 3) {
            return RoundingMode.FLOOR;
        } else {
            return RoundingMode.HALF_UP;
        }
    }

    private PolicyDto getPolicy(String city, String duration, Map<String, PolicyDto> policy) {
        if (StrUtil.hasBlank(city, duration)) {
            return PolicyDto.getDefault();
        }
        city = getCity(city);
        String key = city + duration;

        PolicyDto dto = policy.get(key);
        if (dto != null) {
            return dto;
        }

        String params = "{\n" +
                "    \"attributes\":[\"config\"],\n" +
                "    \"where\": {\n" +
                "        \"period\": " + duration + ",\n" +
                "        \"inUsed\": true,\n" +
                "        \"fullNames\": [\n" +
                "            \"" + city + "\"\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        String result = getDataFromUrl(apiUrl, JSONObject.parseObject(params));
        if (StrUtil.isBlank(result)) {
            return PolicyDto.getDefault();
        }
        JSONObject data = JSONObject.parseObject(result);
        log.info("【工资哥工资单】获取城市政策信息：city:{}, forPeriod:{}, result:{}", city, duration, result);

        Integer code = data.getInteger("errcode");
        Asserts.isTrue(code != null && code.equals(0), "获取城市政策失败！");

        PolicyDto rules = Optional.ofNullable(data.getString("result"))
                .map(res -> {
                    List<JSONObject> configs = JSONArray.parseArray(res, JSONObject.class);
                    return CollUtil.isNotEmpty(configs) ? Optional.ofNullable(configs.get(0).getString("config"))
                            .map(c -> JSONObject.parseObject(c, PolicyDto.class))
                            .orElse(null) : null;
                })
                .orElse(null);
        if (rules == null) {
            rules = PolicyDto.getDefault();
        }
        policy.put(key, rules);

        return rules;
    }

    public String getDataFromUrl(String urlString, JSONObject requestBody) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("app_id", appId);
        headers.add("app_key", appKey);
        headers.add("app_token", ThirdAppTokenUtils.generateToken(appId, appKey, secretKey));

        headers.setContentType(MediaType.APPLICATION_JSON);

        // 创建带有请求头和请求体的 HttpEntity
        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

        try {
            // 发送 POST 请求并获取响应体
            ResponseEntity<String> response = restTemplate.exchange(urlString, HttpMethod.POST, entity, String.class);
            // 返回 JSON 响应体
            return response.getBody();
        } catch (Exception e) {
            // 处理异常
            log.error("【工资哥工资单】查询政策异常：", e);
            throw new BusinessException(e.getMessage());
        }

    }
}

