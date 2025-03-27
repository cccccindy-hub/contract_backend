package com.nnroad.payroll.service.impl;


import com.nnroad.common.core.domain.entity.SysUser;
import com.nnroad.common.exception.BusinessException;
import com.nnroad.common.utils.*;
import com.nnroad.payroll.constants.PayrollConstants;
import com.nnroad.payroll.domain.common.PsCalculationResultHis;
import com.nnroad.payroll.mapper.PsCalculationResultHisMapper;
import com.nnroad.payroll.service.IPsCalculationResultHisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

/**
 * 计算结果历史Service业务层处理
 * 
 * @author Aaron
 * @date 2021-12-13
 */
@Service
public class PsCalculationResultHisServiceImpl implements IPsCalculationResultHisService
{
    private static final Logger log = LoggerFactory.getLogger(PsCalculationResultHisServiceImpl.class);

    @Autowired
    private PsCalculationResultHisMapper psCalculationResultHisMapper;


    /**
     * 新增计算结果历史
     * 
     * @param psCalculationResultHis 计算结果历史
     * @return 结果
     */
    public int insertPsCalculationResultHis(PsCalculationResultHis psCalculationResultHis)
    {
        psCalculationResultHis.setCreateTime(DateUtils.getNowDate());
        return psCalculationResultHisMapper.insertPsCalculationResultHis(psCalculationResultHis);
    }

    /**
     * 修改计算结果历史
     * 
     * @param psCalculationResultHis 计算结果历史
     * @return 结果
     */
    public int updatePsCalculationResultHis(PsCalculationResultHis psCalculationResultHis)
    {
        psCalculationResultHis.setUpdateTime(DateUtils.getNowDate());
        return psCalculationResultHisMapper.updatePsCalculationResultHis(psCalculationResultHis);
    }


    /**
     * 导入ps_calculation_result信息
     * @param psCalculationResultList 计算结果数据
     * @param isUpdateSupport 是否更新
     * @param duration 年月
     * @param leaderId 数据权限拥有者
     * @return 结果
     */
    @Override
    public String importPsCalculationResult(List<PsCalculationResultHis> psCalculationResultList, boolean isUpdateSupport, String duration, String leaderId) {
        if (StringUtils.isNull(psCalculationResultList) || psCalculationResultList.size() == 0)
        {
            throw new BusinessException(MessageFormat.format(MessageUtils.message("msg.errer.data_sheet_not_exist"), MessageUtils.message("import.sheetname.ps_calculation_result")));
        }
        int index = 1;
        int failureNum = 0;
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String rtMsg = "";
//        SysUser sysUser = ShiroUtils.getSysUser();
        for (PsCalculationResultHis psCalculationResult : psCalculationResultList)
        {
            index = index + 1;
            try
            {
                if(StringUtils.isNotEmpty(psCalculationResult.getIdNo())&& StringUtils.isNotEmpty(psCalculationResult.getName())
                        && !StringUtils.contains(psCalculationResult.getIdNo(), PayrollConstants.REF_STR)&& !StringUtils.contains(psCalculationResult.getName(), PayrollConstants.REF_STR)){
                    // 验证是否存在这个基本信息
                    PsCalculationResultHis pcr = new PsCalculationResultHis();
                    pcr.setDuration(duration);
                    pcr.setIdNo(psCalculationResult.getIdNo());

                    List<PsCalculationResultHis> pcrList = psCalculationResultHisMapper.selectPsCalculationResultCheckList(pcr);
                    if (StringUtils.isEmpty(pcrList))
                    {
                        psCalculationResult.setDuration(duration);
//                        setAuthority(leaderId, sysUser, psCalculationResult);
//                        psCalculationResult.setCreateBy(ShiroUtils.getLoginName());
                        this.insertPsCalculationResultHis(psCalculationResult);
                        successNum++;
                    }
                    else{
                        if (isUpdateSupport)
                        {
                            psCalculationResult.setId(pcrList.get(0).getId());
                            psCalculationResult.setDuration(duration);
//                            setAuthority(leaderId, sysUser, psCalculationResult);
//                            psCalculationResult.setUpdateBy(ShiroUtils.getLoginName());
                            this.updatePsCalculationResultHis(psCalculationResult);
                            successNum++;
                        }
                        else
                        {
                            failureNum++;
                            failureMsg.append(MessageFormat.format(MessageUtils.message("msg.errer.row_id_no_import_dump_failure"), index));
                        }
                    }
                }else{
                    log.info(MessageFormat.format(MessageUtils.message("msg.errer.row_id_no_import_dump_empty"), index));
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = MessageFormat.format(MessageUtils.message("msg.errer.row_id_no_import_failure"), index);
                log.error(msg, e);
                //failureMsg.append(msg + ExceptionUtil.getExceptionColumn(e.getMessage()));
                failureMsg.append(msg).append(" Data exception for column [").append(MessageUtils.message(ExceptionUtil.getExceptionColumn(e.getMessage()))).append("] ");

            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, MessageFormat.format(MessageUtils.message("msg.import.sheet_failure_summary"), MessageUtils.message("import.sheetname.ps_calculation_result"), successNum, failureNum ));
            rtMsg = failureMsg.toString();
        }
        else
        {
            successMsg.insert(0, MessageFormat.format(MessageUtils.message("msg.import.sheet_success_summary"), MessageUtils.message("import.sheetname.ps_calculation_result"), successNum));
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
    private void setAuthority(String leaderId, SysUser sysUser, PsCalculationResultHis psCalculationResult) {

        // 设置权限组
        if (StringUtils.isNotEmpty(leaderId)) {
            psCalculationResult.setGroupIds(leaderId);
        }
    }
}
