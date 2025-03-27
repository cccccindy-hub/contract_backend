package com.nnroad.payroll.service.impl;


import com.nnroad.common.core.domain.entity.SysUser;
import com.nnroad.common.utils.*;
import com.nnroad.payroll.constants.PayrollConstants;
import com.nnroad.payroll.domain.common.PsBasicInfoHis;
import com.nnroad.payroll.domain.common.PsBasicUnfixedInfoHis;
import com.nnroad.payroll.mapper.PsBasicUnfixedInfoHisMapper;
import com.nnroad.payroll.service.IPsBasicUnfixedInfoHisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

/**
 * 变动信息信息Service业务层处理
 * 
 * @author Aaron
 * @date 2021-12-13
 */
@Service
public class PsBasicUnfixedInfoHisServiceImpl implements IPsBasicUnfixedInfoHisService
{
    private static final Logger log = LoggerFactory.getLogger(PsBasicUnfixedInfoHisServiceImpl.class);


    @Autowired
    private PsBasicUnfixedInfoHisMapper psBasicUnfixedInfoHisMapper;



    /**
     * 新增变动信息信息
     * 
     * @param psBasicUnfixedInfoHis 变动信息信息
     * @return 结果
     */
    public int insertPsBasicUnfixedInfoHis(PsBasicUnfixedInfoHis psBasicUnfixedInfoHis)
    {
        psBasicUnfixedInfoHis.setCreateTime(DateUtils.getNowDate());
        return psBasicUnfixedInfoHisMapper.insertPsBasicUnfixedInfoHis(psBasicUnfixedInfoHis);
    }

    /**
     * 修改变动信息信息
     * 
     * @param psBasicUnfixedInfoHis 变动信息信息
     * @return 结果
     */

    public int updatePsBasicUnfixedInfoHis(PsBasicUnfixedInfoHis psBasicUnfixedInfoHis)
    {
        psBasicUnfixedInfoHis.setUpdateTime(DateUtils.getNowDate());
        return psBasicUnfixedInfoHisMapper.updatePsBasicUnfixedInfoHis(psBasicUnfixedInfoHis);
    }




    @Override
    public String importPsBasicUnfixedInfo(List<PsBasicUnfixedInfoHis> psBasicUnfixedInfoHisList, boolean isUpdateSupport, String duration, String leaderId) {
        if (StringUtils.isNull(psBasicUnfixedInfoHisList) || psBasicUnfixedInfoHisList.size() == 0) {
            return "";
        }
        int index = 1;
        int failureNum = 0;
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String rtMsg = "";
//        SysUser sysUser = ShiroUtils.getSysUser();
        for (PsBasicUnfixedInfoHis psBasicUnfixedInfo : psBasicUnfixedInfoHisList) {
            index = index + 1;
            try {
                if(StringUtils.isNotEmpty(psBasicUnfixedInfo.getIdNo()) && StringUtils.isNotEmpty(psBasicUnfixedInfo.getName())
                        && !StringUtils.contains(psBasicUnfixedInfo.getIdNo(), PayrollConstants.REF_STR) && !StringUtils.contains(psBasicUnfixedInfo.getName(), PayrollConstants.REF_STR)) {

                    // 验证是否存在这个基本信息
                    PsBasicUnfixedInfoHis pbi = new PsBasicUnfixedInfoHis();
                    pbi.setDuration(duration);
                    pbi.setIdNo(psBasicUnfixedInfo.getIdNo());

                    List<PsBasicInfoHis> pbiList = psBasicUnfixedInfoHisMapper.selectPsBasicUnfixedInfoCheckList(pbi);
                    if (StringUtils.isEmpty(pbiList)) {
                        psBasicUnfixedInfo.setDuration(duration);
//                        setAuthority(leaderId, sysUser, psBasicUnfixedInfo);
//                        psBasicUnfixedInfo.setCreateBy(ShiroUtils.getLoginName());
                        this.insertPsBasicUnfixedInfoHis(psBasicUnfixedInfo);
                        successNum++;
                    } else {
                        if (isUpdateSupport) {
                            psBasicUnfixedInfo.setDuration(duration);
//                            setAuthority(leaderId, sysUser, psBasicUnfixedInfo);
//                            psBasicUnfixedInfo.setUpdateBy(ShiroUtils.getLoginName());
                            this.updatePsBasicUnfixedInfoHis(psBasicUnfixedInfo);
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
                failureMsg.append(msg).append(" Data exception for column [").append(MessageUtils.message(ExceptionUtil.getExceptionColumn(e.getMessage()))).append("] ");
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, MessageFormat.format(MessageUtils.message("msg.import.sheet_failure_summary"), MessageUtils.message("import.sheetname.ps_basic_info") ,successNum, failureNum));
            rtMsg = failureMsg.toString();
        } else {
            successMsg.insert(0, MessageFormat.format(MessageUtils.message("msg.import.sheet_success_summary"), MessageUtils.message("import.sheetname.ps_basic_info") ,successNum));
            rtMsg = successMsg.toString();
        }
        return rtMsg;
    }

    /**
     *  计算结果表更新
     * @param leaderId 数据拥有者
     * @param sysUser 当前用户
     * @param psBasicUnfixedInfoHis 计算结果数据
     */
    private void setAuthority(String leaderId, SysUser sysUser, PsBasicUnfixedInfoHis psBasicUnfixedInfoHis) {

        // 设置权限组
        if (StringUtils.isNotEmpty(leaderId)) {
            psBasicUnfixedInfoHis.setGroupIds(leaderId);
        }
    }
}
