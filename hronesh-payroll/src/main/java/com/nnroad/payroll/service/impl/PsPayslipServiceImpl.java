package com.nnroad.payroll.service.impl;


import com.nnroad.common.core.domain.entity.SysUser;
import com.nnroad.common.core.text.Convert;
import com.nnroad.common.exception.BusinessException;
import com.nnroad.common.utils.*;
import com.nnroad.employee.domain.VEmployeeInfo;
import com.nnroad.employee.mapper.EmployeeInfoMapper;
import com.nnroad.payroll.constants.PayrollConstants;
import com.nnroad.payroll.domain.*;
import com.nnroad.payroll.domain.common.PsPayslip;
import com.nnroad.payroll.mapper.PsBasicInfoMapper;
import com.nnroad.payroll.mapper.PsPayslipMapper;
import com.nnroad.payroll.mapper.TEEPayslipMapper;
import com.nnroad.payroll.service.IPsPayslipService;
import com.nnroad.payroll.service.IPsStaffService;
import com.nnroad.payroll.service.ITEEPayslipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * ps_payslipService业务层处理
 *
 * @author Hrone
 * @date 2021-01-21
 */
@Service
public class PsPayslipServiceImpl implements IPsPayslipService {
    private static final Logger log = LoggerFactory.getLogger(PsPayslipServiceImpl.class);

    @Value("${sys.orgs}")
    private String orgs;

    @Autowired
    private PsPayslipMapper psPayslipMapper;

    @Autowired
    private IPsStaffService psStaffService;

    @Autowired
    private ITEEPayslipService teePayslipService;

    @Autowired
    private PsBasicInfoMapper psBasicInfoMapper;

    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    @Autowired
    private TEEPayslipMapper teePayslipMapper;


    /**
     * 查询ps_payslip
     *
     * @param id ps_payslipID
     * @return ps_payslip
     */
    @Override
    public PsPayslip selectPsPayslipById(Long id) {
        return psPayslipMapper.selectPsPayslipById(id);
    }


    /**
     * 新增ps_payslip
     *
     * @param psPayslip ps_payslip
     * @return 结果
     */
    public int insertPsPayslip(PsPayslip psPayslip) {
        return psPayslipMapper.insertPsPayslip(psPayslip);
    }

    /**
     * 修改ps_payslip
     *
     * @param psPayslip ps_payslip
     * @return 结果
     */
    public int updatePsPayslip(PsPayslip psPayslip) {
        return psPayslipMapper.updatePsPayslip(psPayslip);
    }


    /**
     * 导入ps_payslip信息
     *
     * @param psPayslipList   payslip数据
     * @param isUpdateSupport 是否更新
     * @param duration        年月
     * @return 结果
     */
    @Override
    public String importPsPayslip(List<PsPayslip> psPayslipList, boolean isUpdateSupport, String duration) {
        if (StringUtils.isNull(psPayslipList) || psPayslipList.isEmpty()) {
            throw new BusinessException(MessageFormat.format("{0} 文件无数据或者没有匹配的列！", "S-Payslip"));
        }
        int index = 6;
        int failureNum = 0;
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String rtMsg = "";
        for (PsPayslip psPayslip : psPayslipList) {
            index = index + 1;
            try {
                if (StringUtils.isNotEmpty(psPayslip.getIdNo()) && StringUtils.isNotEmpty(psPayslip.getName())
                        && !StringUtils.contains(psPayslip.getIdNo(), PayrollConstants.REF_STR) && !StringUtils.contains(psPayslip.getName(), PayrollConstants.REF_STR)) {
                    // 验证是否存在这个payslip
                    PsPayslip pp = new PsPayslip();
                    pp.setDuration(duration);
                    pp.setIdNo(psPayslip.getIdNo());

                    List<PsPayslip> ppList = psPayslipMapper.selectPsPayslipList(pp);
                    if (StringUtils.isEmpty(ppList)) {
                        psPayslip.setDuration(duration);
                        psPayslip.setCzFlag(this.setStatus(psPayslip));
//                        setAuthority(leaderId, sysUser, psPayslip);
                        psPayslip.setCreateBy(SecurityUtils.getUsername());
                        this.insertPsPayslip(psPayslip);
                        successNum++;
                    } else {
                        if (isUpdateSupport) {
                            psPayslip.setId(ppList.get(0).getId());
                            psPayslip.setDuration(duration);
                            psPayslip.setCzFlag(this.setStatus(psPayslip));
//                            setAuthority(leaderId, sysUser, psPayslip);
                            psPayslip.setUpdateBy(SecurityUtils.getUsername());
                            this.updatePsPayslip(psPayslip);
                            successNum++;
                        } else {
                            failureNum++;
                            failureMsg.append(MessageFormat.format(MessageUtils.message("msg.errer.row_id_no_import_dump_failure"), index));
                        }
                    }
                } else {
                    log.info(MessageFormat.format(MessageUtils.message("msg.errer.row_id_no_import_dump_empty"), index));
                }
            } catch (Exception e) {
                failureNum++;
                String msg = MessageFormat.format(MessageUtils.message("msg.errer.row_id_no_import_failure"), index);
                //failureMsg.append(msg + ExceptionUtil.getExceptionColumn(e.getMessage()));
                failureMsg.append(msg).append(" Data exception for column [").append(MessageUtils.message(ExceptionUtil.getExceptionColumn(e.getMessage()))).append("] ");
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, MessageFormat.format(MessageUtils.message("msg.import.sheet_failure_summary"), MessageUtils.message("import.sheetname.ps_payslip"), successNum, failureNum));
            rtMsg = failureMsg.toString();
        } else {
            successMsg.insert(0, MessageFormat.format(MessageUtils.message("msg.import.sheet_success_summary"), MessageUtils.message("import.sheetname.ps_payslip"), successNum));
            rtMsg = successMsg.toString();
        }
        return rtMsg;
    }


    /**
     * 设置工资单出账状态
     *
     * @param psPayslip
     * @return 返回状态（0成功或者1失败）
     */
    private String setStatus(PsPayslip psPayslip) {
        //基本工资
        //日常奖金
        //年终奖/13薪
        //津贴/补贴
        //加班费
        //公司负税项
        //缴养老
        //缴医疗
        //缴失业
        //缴公积金
        //入离职缺勤扣
        //扣事假
        //扣旷工
        //扣病假
        //缴年金
        //工会费
        //其他
        //个税
        //实发
        if (BigDecimal.ZERO.equals(psPayslip.getAnnualBonus13thSalary())
                && BigDecimal.ZERO.equals(psPayslip.getBasicSalary())
                && BigDecimal.ZERO.equals(psPayslip.getNormalBonus())
                && BigDecimal.ZERO.equals(psPayslip.getAllowanceSubsidy())
                && BigDecimal.ZERO.equals(psPayslip.getOtPayment())
                && BigDecimal.ZERO.equals(psPayslip.getAffordTax())
                && BigDecimal.ZERO.equals(psPayslip.getPension())
                && BigDecimal.ZERO.equals(psPayslip.getMedical())
                && BigDecimal.ZERO.equals(psPayslip.getUnemployment())
                && BigDecimal.ZERO.equals(psPayslip.getHousingFund())
                && BigDecimal.ZERO.equals(psPayslip.getAbsence())
                && BigDecimal.ZERO.equals(psPayslip.getAffairLeave())
                && BigDecimal.ZERO.equals(psPayslip.getAbsenteeism())
                && BigDecimal.ZERO.equals(psPayslip.getSickLeave())
                && BigDecimal.ZERO.equals(psPayslip.getAnnuity())
                && BigDecimal.ZERO.equals(psPayslip.getUnionFee())
                && BigDecimal.ZERO.equals(psPayslip.getOther())
                && BigDecimal.ZERO.equals(psPayslip.getIIT())
                && BigDecimal.ZERO.equals(psPayslip.getNetIncome())
        ) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * 工资单表更新
     *
     * @param leaderId  数据拥有者
     * @param sysUser   当前用户
     * @param psPayslip 工资单数据
     */
    private void setAuthority(String leaderId, SysUser sysUser, PsPayslip psPayslip) {
//        if(leaderId.length() == 0){
//            if(sysUser.isAdmin()){
//                psPayslip.setGroupIds(ShiroUtils.getUserId().toString());
//            }else{
//                psPayslip.setGroupIds("1," + ShiroUtils.getUserId().toString());
//            }
//
//        }else{
//            if(sysUser.isAdmin()){
//                psPayslip.setGroupIds(leaderId + "," + ShiroUtils.getUserId().toString());
//            }else{
//                psPayslip.setGroupIds(leaderId + ",1," + ShiroUtils.getUserId().toString());
//            }
//        }
        // 设置权限组
        if (StringUtils.isNotEmpty(leaderId)) {
            psPayslip.setGroupIds(leaderId);
        }
    }


    /**
     * 查询ps_payslip列表
     *
     * @param psPayslip ps_payslip
     * @return ps_payslip
     */
    @Override
    public List<PsPayslip> selectPsPayslipList1(PsPayslip psPayslip) {
        Map<String, Object> params = psPayslip.getParams();
        String durationRange = params.get("duration") != null ? params.get("duration").toString() : "";
        if (!StringUtils.isEmpty(durationRange)) {
            String[] durationArray = durationRange.split("~");
            String beginTime = durationArray[0];
            String endTime = durationArray[1];
            params.put("beginTime", beginTime.trim());
            params.put("endTime", endTime.trim());
            psPayslip.setDuration(null);
        }
        // 发薪日支持多选
        if (StringUtils.isNotEmpty(psPayslip.getPayDay())) {
            String[] payDayArray = psPayslip.getPayDay().split(",");
            String payDatStr = "";
            for (int i = 0; i < payDayArray.length; i++) {
                payDatStr += "'" + payDayArray[i] + "'";
                if (i < (payDayArray.length - 1)) {
                    payDatStr += ",";
                }
            }
            psPayslip.setPayDay(payDatStr);
        }
        // 数据权限筛选条件
//        PayrollUtils.paramSet(params, dbCommon, null);
        psPayslip.setParams(params);
        return psPayslipMapper.selectPsPayslipList1(psPayslip);
    }

    /**
     * 查询ps_payslip列表
     *
     * @param psPayslip ps_payslip
     * @return ps_payslip
     */
    @Override
    public List<PsPayslip> selectPsPayslipListByEmployeeOrg(PsPayslip psPayslip) {
        Map<String, Object> params = psPayslip.getParams();
        String durationRange = params.get("duration") != null ? params.get("duration").toString() : "";
        if (!StringUtils.isEmpty(durationRange)) {
            String[] durationArray = durationRange.split("~");
            String beginTime = durationArray[0];
            String endTime = durationArray[1];
            params.put("beginTime", beginTime.trim());
            params.put("endTime", endTime.trim());
        }
        // 发薪日支持多选
        if (StringUtils.isNotEmpty(psPayslip.getPayDay())) {
            String[] payDayArray = psPayslip.getPayDay().split(",");
            String payDatStr = "";
            for (int i = 0; i < payDayArray.length; i++) {
                payDatStr += "'" + payDayArray[i] + "'";
                if (i < (payDayArray.length - 1)) {
                    payDatStr += ",";
                }
            }
            psPayslip.setPayDay(payDatStr);
        }
//        如果筛选开放状态，则只筛选本地数据
        if (StringUtils.isNotEmpty(psPayslip.getKfFlag())){
            if (orgs != null && !orgs.trim().isEmpty()) {
                String[] orgArray = orgs.split(",\\s*");
                List<String> orgList = Arrays.asList(orgArray);
                psPayslip.setOrgList(orgList);
            }
        }
        // 数据权限筛选条件
//        PayrollUtils.paramSet(params, dbCommon, null);
        psPayslip.setParams(params);
        return psPayslipMapper.selectPsPayslipListByEmployeeOrg(psPayslip);
    }

    /**
     * 获取我的工资单信息
     *
     * @param myPsPayslipDto 检索条件
     * @return 结果
     */
    @Override
    public MyPsPayslip getMyPayslipData(MyPsPayslipDto myPsPayslipDto) {
        return psPayslipMapper.getMyPayslipData(myPsPayslipDto);
    }


    /**
     * 开放查询ps_payslip信息
     *
     * @param ids 信息id
     * @return 结果
     */
    @Override
    public String openQueryPsPayslipByIds(String ids) {
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        int successNum = 0;
        int failureNum = 0;
        try {
            psPayslipMapper.openQueryPsPayslipByIds(Convert.toStrArray(ids));
            //插入
            List<PsPayslip> payslips=psPayslipMapper.selectPsPayslipByIds(Convert.toStrArray(ids));
            List<TEEPayslip> TEEPayslips =new ArrayList<>();
            for (PsPayslip payslip : payslips) {
                VEmployeeInfo vEmployeeInfo=employeeInfoMapper.selectVEmployeeInfoByEeCode(payslip.getIdNo());

//                Staff staff = psStaffService.selectStaff(payslip.getIdNo());
                if (vEmployeeInfo == null) {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、员工编号为:" + payslip.getIdNo() + "  "+payslip.getName()+"员工未入职！");
                } else {
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、员工编号为:" + payslip.getIdNo() + "  "+payslip.getName()+ "员工数据正确！");
                }
            }
            if(failureNum!=0){
                throw new BusinessException(failureMsg.toString());
            }
            for (PsPayslip payslip : payslips) {
                Salary salary = new Salary();
                VEmployeeInfo vEmployeeInfo=employeeInfoMapper.selectVEmployeeInfoByEeCode(payslip.getIdNo());
//                Staff staff = psStaffService.selectStaff(payslip.getIdNo());
                BeanUtils.copyProperties(payslip,salary);
                TEEPayslip payslipDTO=new TEEPayslip();
                payslipDTO.setFemployerName(payslip.getClientName());
                if (vEmployeeInfo.getEeName().isEmpty()){
                    String Name=psBasicInfoMapper.selectRealName(payslip.getIdNo(),payslip.getDuration());
                    payslipDTO.setFcertName(Name.trim());
                }else {
                    payslipDTO.setFcertName(vEmployeeInfo.getEeName().trim());
                }
                payslipDTO.setFperiod(payslip.getDuration());
                payslipDTO.setIdNo(payslip.getIdNo());
                salary.setName(payslipDTO.getFcertName());
                payslipDTO.setFcertNo(vEmployeeInfo.getIdNumber().trim());
                payslipDTO.setFemployer(payslip.getClientCode());
                payslipDTO.setPhone(vEmployeeInfo.getMobile());
                payslipDTO.setFemail(vEmployeeInfo.getEmail());
                if (payslip.getClientCode().contains("CCN1294")){
                    String fata=salary.toString()+",{\"id\": \"taxCalculation\", \"seq\": 41, \"code\": \"taxCalculation\",\"name\": \"计税不发\", \"ename\": \"Tax Calculation\",\"value\": "+payslip.getTaxCalculation()+
                            "}";
                    payslipDTO.setFdata("["+fata+"]");
                }else {
                    payslipDTO.setFdata("["+salary.toString()+"]");
                }

                payslipDTO.setFconfirmedAt(System.currentTimeMillis());
                TEEPayslips.add(payslipDTO);
            }
            try {
               teePayslipService.insertAll(TEEPayslips);
            } catch (Exception e) {
                throw new BusinessException("Error inserting payslips: " + e.getMessage());
            }
            successMsg.insert(0, "恭喜您，同步成功！共"+successNum+"数据:");
            return successMsg.toString();
        } catch (Exception e) {
            psPayslipMapper.closeQueryPsPayslipByIds(Convert.toStrArray(ids));
            failureMsg.insert(0, "很抱歉，导入失败！数据不完整!共"+failureNum+"数据错误:");
            throw new BusinessException(failureMsg.toString());
        }
    }



    /**
     * 查询ps_payslip列表
     *
     * @param psPayslip ps_payslip
     * @return ps_payslip
     */
    @Override
    public List<PsPayslip> selectPsPayslipList(PsPayslip psPayslip) {
        return psPayslipMapper.selectPsPayslipList(psPayslip);
    }

}
