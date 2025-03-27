package com.nnroad.payroll.service.impl;


import com.nnroad.common.core.domain.entity.SysUser;
import com.nnroad.common.exception.BusinessException;
import com.nnroad.common.utils.*;
import com.nnroad.payroll.constants.PayrollConstants;
import com.nnroad.payroll.domain.common.PsCalculationResult;
import com.nnroad.payroll.mapper.PsCalculationResultMapper;
import com.nnroad.payroll.service.IPsCalculationResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * ps_calculation_resultService业务层处理
 * 
 * @author Hrone
 * @date 2021-01-17
 */
@Service
public class PsCalculationResultServiceImpl implements IPsCalculationResultService
{
    private static final Logger log = LoggerFactory.getLogger(PsCalculationResultServiceImpl.class);


    @Autowired
    private PsCalculationResultMapper psCalculationResultMapper;



    /**
     * 查询ps_calculation_result列表
     *
     * @param psCalculationResult ps_calculation_result
     * @return ps_calculation_result
     */
    @Override
    public List<PsCalculationResult> selectPsCalculationResultList(PsCalculationResult psCalculationResult)
    {
        Map<String, Object> params  = psCalculationResult.getParams();
//        Long userId = SecurityUtils.getUserId();
//        params.put("userId", userId);
        String durationRange = params.get("duration") != null ? params.get("duration").toString() : "";
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
        // 数据权限筛选条件
        return psCalculationResultMapper.selectPsCalculationResultList(psCalculationResult);
    }


    /**
     * 新增ps_calculation_result
     * 
     * @param psCalculationResult ps_calculation_result
     * @return 结果
     */
    public int insertPsCalculationResult(PsCalculationResult psCalculationResult)
    {
        return psCalculationResultMapper.insertPsCalculationResult(psCalculationResult);
    }

    /**
     * 修改ps_calculation_result
     * 
     * @param psCalculationResult ps_calculation_result
     * @return 结果
     */
    public int updatePsCalculationResult(PsCalculationResult psCalculationResult)
    {
        return psCalculationResultMapper.updatePsCalculationResult(psCalculationResult);
    }

    /**
     * 导入ps_calculation_result信息
     * @param psCalculationResultList 计算结果数据
     * @param isUpdateSupport 是否更新
     * @param duration 年月
     * @return 结果
     */
    @Override
    public String importPsCalculationResult(List<PsCalculationResult> psCalculationResultList, boolean isUpdateSupport, String duration) {
        if (StringUtils.isNull(psCalculationResultList) || psCalculationResultList.size() == 0)
        {
            throw new BusinessException(MessageFormat.format("{0} 文件无数据或者没有匹配的列！", "计算结果"));
        }
        int index = 1;
        int failureNum = 0;
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String rtMsg = "";
//        SysUser sysUser = ShiroUtils.getSysUser();
        for (PsCalculationResult psCalculationResult : psCalculationResultList)
        {
            index = index + 1;
            try
            {
                if(StringUtils.isNotEmpty(psCalculationResult.getIdNo())&& StringUtils.isNotEmpty(psCalculationResult.getName())
                        && !StringUtils.contains(psCalculationResult.getIdNo(), PayrollConstants.REF_STR)&& !StringUtils.contains(psCalculationResult.getName(), PayrollConstants.REF_STR)){
                    // 验证是否存在这个基本信息
                    PsCalculationResult pcr = new PsCalculationResult();
                    pcr.setDuration(duration);
                    pcr.setIdNo(psCalculationResult.getIdNo());

                    List<PsCalculationResult> pcrList = psCalculationResultMapper.selectPsCalculationResultCheckList(pcr);
                    if (StringUtils.isEmpty(pcrList))
                    {
                        psCalculationResult.setDuration(duration);
//                        setAuthority(leaderId, sysUser, psCalculationResult);
//                        psCalculationResult.setCreateBy(ShiroUtils.getLoginName());
                        this.insertPsCalculationResult(psCalculationResult);
                        successNum++;
                    }
                    else{
                        if (isUpdateSupport)
                        {
                            psCalculationResult.setId(pcrList.get(0).getId());
                            psCalculationResult.setDuration(duration);
//                            setAuthority(leaderId, sysUser, psCalculationResult);
//                            psCalculationResult.setUpdateBy(ShiroUtils.getLoginName());
                            this.updatePsCalculationResult(psCalculationResult);
                            successNum++;
                        }
                        else
                        {
                            failureNum++;
                            failureMsg.append(MessageFormat.format("<br/>excel的{0}行工号和姓名已存在", index));
                        }
                    }
                }else{
                    log.info(MessageFormat.format("<br/>excel的{0}行工号或者姓名为空", index));
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = MessageFormat.format("<br/>excel的{0}行数据导入失败", index);
                log.error(msg, e);
                failureMsg.append(msg).append(" Data exception for column [").append(ExceptionUtil.getExceptionColumn(e.getMessage())).append("] ");
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, MessageFormat.format("sheet[{0}] 数据导入已完成，成功{1}条, 失败{2}条。错误如下：", "计算结果", successNum, failureNum ));
            rtMsg = failureMsg.toString();
        }
        else
        {
            successMsg.insert(0, MessageFormat.format("sheet[{0}] 数据导入已完成，成功{1}条, 失败{2}条。错误如下：", "计算结果", successNum));
            rtMsg = successMsg.toString();
        }
        return rtMsg;
    }

    /**
     *  计算结果表更新
     * @param leaderId 数据拥有者
     * @param sysUser 当前用户
     * @param psCalculationResult 计算结果数据
     */
    private void setAuthority(String leaderId, SysUser sysUser, PsCalculationResult psCalculationResult) {
//        if(leaderId.length() == 0){
//            if(sysUser.isAdmin()){
//                psCalculationResult.setGroupIds(ShiroUtils.getUserId().toString());
//            }else{
//                psCalculationResult.setGroupIds("1," + ShiroUtils.getUserId().toString());
//            }
//
//        }else{
//            if(sysUser.isAdmin()){
//                psCalculationResult.setGroupIds(leaderId + "," + ShiroUtils.getUserId().toString());
//            }else{
//                psCalculationResult.setGroupIds(leaderId + ",1," + ShiroUtils.getUserId().toString());
//            }
//        }
        // 设置权限组
        if (StringUtils.isNotEmpty(leaderId)) {
            psCalculationResult.setGroupIds(leaderId);
        }
    }
}
