package com.nnroad.payroll.service.impl;


import com.nnroad.client.domain.SysClient;
import com.nnroad.client.mapper.SysClientMapper;
import com.nnroad.common.core.domain.entity.SysUser;
import com.nnroad.common.exception.BusinessException;
import com.nnroad.common.utils.*;
import com.nnroad.payroll.constants.PayrollConstants;
import com.nnroad.payroll.domain.common.*;
import com.nnroad.payroll.mapper.*;
import com.nnroad.payroll.service.IPsBasicInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * ps_basic_infoService业务层处理
 * 
 * @author Hrone
 * @date 2021-01-17
 */
@Service
public class PsBasicInfoServiceImpl implements IPsBasicInfoService
{
    private static final Logger log = LoggerFactory.getLogger(PsBasicInfoServiceImpl.class);

    @Autowired
    private PsBasicInfoMapper psBasicInfoMapper;

    @Autowired
    private PsCalculationResultMapper psCalculationResultMapper;

    @Autowired
    private PsPayrollMapper psPayrollMapper;

    @Autowired
    private PsPayrollTaxMapper psPayrollTaxMapper;

    @Autowired
    private PsPayslipMapper psPayslipMapper;

    @Autowired
    private PsYearEndBonusMapper psYearEndBonusMapper;

    @Autowired
    private SysClientMapper clientMapper;

    /**
     * 新增ps_basic_info
     * 
     * @param psBasicInfo ps_basic_info
     * @return 结果
     */
    public int insertPsBasicInfo(PsBasicInfo psBasicInfo)
    {
        return psBasicInfoMapper.insertPsBasicInfo(psBasicInfo);
    }

    /**
     * 修改ps_basic_info
     * 
     * @param psBasicInfo ps_basic_info
     * @return 结果
     */

    public int updatePsBasicInfo(PsBasicInfo psBasicInfo)
    {
        return psBasicInfoMapper.updatePsBasicInfo(psBasicInfo);
    }


    @Override
    public void checkRequire(List<PsBasicInfo> psBasicInfoList) {
        // 删除数据全部为 null 的行
        psBasicInfoList.removeIf(this::isPsBasicInfoEmpty);

        // 如果列表为空，直接返回
        if (psBasicInfoList.isEmpty()) return;

        // 客户编号检查
        SysClient clientDtoDB = null;


        for (int i = 0; i < psBasicInfoList.size(); i++) {
            PsBasicInfo psBasicInfo = psBasicInfoList.get(i);

            if(StringUtils.isEmpty(psBasicInfo.getIdNo()) ||
                    StringUtils.isEmpty(psBasicInfo.getName()) ||
                    StringUtils.contains(psBasicInfo.getIdNo(), PayrollConstants.REF_STR) ||
                    StringUtils.contains(psBasicInfo.getName(), PayrollConstants.REF_STR)) {
                continue;
            }

            // 客户编号不为空检查
            if (StringUtils.isEmpty(psBasicInfo.getClientCode())) {
                throw new BusinessException(MessageFormat.format("{0}行{1}为空", i + 2, "客户编号"));
            }

            // 客户编号存在检查
            SysClient clientDto=new SysClient();
            clientDto.setCompanyCode(psBasicInfo.getClientCode());
            clientDtoDB = clientMapper.selectClient(clientDto);
            if (clientDtoDB == null) {
                throw new BusinessException(MessageFormat.format("{0}行{1}不存在", i + 2, "客户编号"));
            }
        }
    }


    // 判断 PsBasicInfo 是否为空的方法
    private boolean isPsBasicInfoEmpty(PsBasicInfo psBasicInfo) {
        if (psBasicInfo == null) {
            return true;
        }
        return StringUtils.isEmpty(psBasicInfo.getIdNo()) &&
                StringUtils.isEmpty(psBasicInfo.getName()) &&
                StringUtils.isEmpty(psBasicInfo.getClientCode());
    }
    /**
     * 删除ps_basic_info信息
     *
     * @param duration 年月
     * @param clientCodes 客户编号列表
     * @return 结果
     */
    @Override
    public void deletePaymentNotice(String duration, List<String> clientCodes) {
        if (StringUtils.isEmpty(duration)) return;

        PsBasicInfo pbi = new PsBasicInfo();
        pbi.setDuration(duration);
        for (String clientCode : clientCodes) {
            if (StringUtils.isEmpty(clientCode)) continue;

            pbi.setClientCode(clientCode);

            List<PsBasicInfo> pbiList = psBasicInfoMapper.selectPsBasicInfoList(pbi);
            for (PsBasicInfo dto : pbiList) {

                PsBasicInfo bi = new PsBasicInfo();
                bi.setDuration(duration);
                bi.setIdNo(dto.getIdNo());
                psBasicInfoMapper.deletePsBasicInfoByKey(bi);

                PsCalculationResult cr = new PsCalculationResult();
                cr.setDuration(duration);
                cr.setIdNo(dto.getIdNo());
                psCalculationResultMapper.deletePsCalculationResultByKey(cr);

                PsPayroll pr = new PsPayroll();
                pr.setDuration(duration);
                pr.setIdNo(dto.getIdNo());
                psPayrollMapper.deletePsPayrollByKey(pr);

                PsPayrollTax prt = new PsPayrollTax();
                prt.setDuration(duration);
                prt.setIdNo(dto.getIdNo());
                psPayrollTaxMapper.deletePsPayrollTaxByKey(prt);

                PsPayslip ps = new PsPayslip();
                ps.setDuration(duration);
                ps.setIdNo(dto.getIdNo());
                psPayslipMapper.deletePsPayslipByKey(ps);

                PsYearEndBonus yeb = new PsYearEndBonus();
                yeb.setDuration(duration);
                yeb.setIdNo(dto.getIdNo());
                psYearEndBonusMapper.deletePsYearEndBonusByKey(yeb);
            }
        }
    }

    /**
     * 导入ps_basic_info信息
     * @param psBasicInfoList 基本信息数据
     * @param isUpdateSupport 是否更新
     * @param duration 年月
     * @return 结果
     */
    @Override
    public String importPsBasicInfo(List<PsBasicInfo> psBasicInfoList, boolean isUpdateSupport, String duration) {
        if (StringUtils.isNull(psBasicInfoList) || psBasicInfoList.isEmpty()) {
            throw new BusinessException(MessageFormat.format("{0} 文件无数据或者没有匹配的列！", "基本信息"));
        }
        int index = 1;
        int failureNum = 0;
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String rtMsg = "";
        for (PsBasicInfo psBasicInfo : psBasicInfoList) {
            index = index + 1;
            try {
                if(StringUtils.isNotEmpty(psBasicInfo.getIdNo()) && StringUtils.isNotEmpty(psBasicInfo.getName())
                        && !StringUtils.contains(psBasicInfo.getIdNo(), PayrollConstants.REF_STR) && !StringUtils.contains(psBasicInfo.getName(), PayrollConstants.REF_STR)) {

                    // 验证是否存在这个基本信息
                    PsBasicInfo pbi = new PsBasicInfo();
                    pbi.setDuration(duration);
                    pbi.setIdNo(psBasicInfo.getIdNo());

                    List<PsBasicInfo> pbiList = psBasicInfoMapper.selectPsBasicInfoCheckList(pbi);
                    if (StringUtils.isEmpty(pbiList)) {
                        psBasicInfo.setDuration(duration);
//                        setAuthority(leaderId, sysUser, psBasicInfo);
                        psBasicInfo.setCreateBy(SecurityUtils.getUsername());
                        this.insertPsBasicInfo(psBasicInfo);
                        successNum++;
                    } else {
                        if (isUpdateSupport) {
                            psBasicInfo.setId(pbiList.get(0).getId());
                            psBasicInfo.setDuration(duration);
//                            setAuthority(leaderId, sysUser, psBasicInfo);
                            psBasicInfo.setUpdateBy(SecurityUtils.getUsername());
                            this.updatePsBasicInfo(psBasicInfo);
                            successNum++;
                        } else {
                            failureNum++;
                            failureMsg.append(MessageFormat.format("<br/>excel的{0}行工号和姓名已存在", index));
                        }
                    }
                } else {
                    log.info(MessageFormat.format("<br/>excel的{0}行工号或者姓名为空", index));
                }
            } catch (Exception e) {
                failureNum++;
                String msg = MessageFormat.format("<br/>excel的{0}行数据导入失败", index);
                failureMsg.append(msg).append(" Data exception for column [").append(ExceptionUtil.getExceptionColumn(e.getMessage())).append("] ");
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, MessageFormat.format("sheet[{0}] 数据导入已完成，成功{1}条, 失败{2}条。错误如下：","基本信息" ,successNum, failureNum));
            rtMsg = failureMsg.toString();
        } else {
            successMsg.insert(0, MessageFormat.format("sheet[{0}] 数据导入已完成，成功{1}条。","基本信息" ,successNum));
            rtMsg = successMsg.toString();
        }
        return rtMsg;
    }

    /**
     *  基本信息表更新
     *
     * @param sysUser 当前用户
     * @param psBasicInfo 基本信息数据
     */
    private void setAuthority(String leaderId, SysUser sysUser, PsBasicInfo psBasicInfo) {
//        if(leaderId.length() == 0){
//            if(sysUser.isAdmin()){
//                psBasicInfo.setGroupIds(ShiroUtils.getUserId().toString());
//            }else{
//                psBasicInfo.setGroupIds("1," + ShiroUtils.getUserId().toString());
//            }
//        }else{
//            if(sysUser.isAdmin()){
//                psBasicInfo.setGroupIds(leaderId + "," + ShiroUtils.getUserId().toString());
//            }else{
//                psBasicInfo.setGroupIds(leaderId + ",1," + ShiroUtils.getUserId().toString());
//            }
//        }
        // 设置权限组
        if (StringUtils.isNotEmpty(leaderId)) {
            psBasicInfo.setGroupIds(leaderId);
        }
    }


    public String SelectRealName(String idNo, String duration){
       return psBasicInfoMapper.selectRealName(idNo,duration);
    }

    /**
     * 查询ps_basic_info列表
     *
     * @param psBasicInfo ps_basic_info
     * @return ps_basic_info
     */
    @Override
    public List<PsBasicInfo> selectPsBasicInfoList(PsBasicInfo psBasicInfo)
    {
        Map<String, Object> params  = psBasicInfo.getParams();
        String durationRange = psBasicInfo.getDuration();
        if (!StringUtils.isEmpty(durationRange)) {
            // 检查是否包含 "~"
            if (durationRange.contains("~")) {
                String[] durationArray = durationRange.split("~");
                String beginTime = durationArray[0].trim();
                String endTime = durationArray[1].trim();
                params.put("beginTime", beginTime);
                params.put("endTime", endTime);
            } else {
                // 只有单个时间点，将其视为 beginTime 和 endTime 相同
                String singleTime = durationRange.trim();
                params.put("beginTime", singleTime);
                params.put("endTime", singleTime);
            }

        }
        return psBasicInfoMapper.selectPsBasicInfoList(psBasicInfo);
    }

}
