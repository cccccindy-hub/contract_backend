package com.nnroad.payroll.service.impl;

import com.nnroad.common.core.domain.entity.SysUser;
import com.nnroad.common.exception.BusinessException;
import com.nnroad.common.utils.*;
import com.nnroad.payroll.constants.PayrollConstants;
import com.nnroad.payroll.domain.common.PsBasicInfoHis;
import com.nnroad.payroll.mapper.PsBasicInfoHisMapper;
import com.nnroad.payroll.service.IPsBasicInfoHisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

/**
 * 基本信息历史Service业务层处理
 * 
 * @author Aaron
 * @date 2021-12-13
 */
@Service
public class PsBasicInfoHisServiceImpl implements IPsBasicInfoHisService
{

    private static final Logger log = LoggerFactory.getLogger(PsBasicInfoHisServiceImpl.class);

    @Autowired
    private PsBasicInfoHisMapper psBasicInfoHisMapper;



    public int insertPsBasicInfoHis(PsBasicInfoHis psBasicInfoHis)
    {
        psBasicInfoHis.setCreateTime(DateUtils.getNowDate());
        return psBasicInfoHisMapper.insertPsBasicInfoHis(psBasicInfoHis);
    }

    /**
     * 修改基本信息历史
     *
     * @param psBasicInfoHis 基本信息历史
     * @return 结果
     */

    public int updatePsBasicInfoHis(PsBasicInfoHis psBasicInfoHis)
    {
        psBasicInfoHis.setUpdateTime(DateUtils.getNowDate());
        return psBasicInfoHisMapper.updatePsBasicInfoHis(psBasicInfoHis);
    }

    @Override
    public String importPsBasicInfo(List<PsBasicInfoHis> psBasicInfoList, boolean isUpdateSupport, String duration, String leaderId) {
        if (StringUtils.isNull(psBasicInfoList) || psBasicInfoList.size() == 0) {
            throw new BusinessException(MessageFormat.format(MessageUtils.message("msg.errer.data_sheet_not_exist"), MessageUtils.message("import.sheetname.ps_basic_info")));
        }
        int index = 1;
        int failureNum = 0;
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String rtMsg = "";
//        SysUser sysUser = ShiroUtils.getSysUser();
        for (PsBasicInfoHis psBasicInfo : psBasicInfoList) {
            index = index + 1;
            try {
                if(StringUtils.isNotEmpty(psBasicInfo.getIdNo()) && StringUtils.isNotEmpty(psBasicInfo.getName())
                        && !StringUtils.contains(psBasicInfo.getIdNo(), PayrollConstants.REF_STR) && !StringUtils.contains(psBasicInfo.getName(), PayrollConstants.REF_STR)) {

                    // 验证是否存在这个基本信息
                    PsBasicInfoHis pbi = new PsBasicInfoHis();
                    pbi.setDuration(duration);
                    pbi.setIdNo(psBasicInfo.getIdNo());

                    List<PsBasicInfoHis> pbiList = psBasicInfoHisMapper.selectPsBasicInfoCheckList(pbi);
                    if (StringUtils.isEmpty(pbiList)) {
                        psBasicInfo.setDuration(duration);
//                        setAuthority(leaderId, sysUser, psBasicInfo);
//                        psBasicInfo.setCreateBy(ShiroUtils.getLoginName());
                        this.insertPsBasicInfoHis(psBasicInfo);
                        successNum++;
                    } else {
                        if (isUpdateSupport) {
                            psBasicInfo.setId(pbiList.get(0).getId());
                            psBasicInfo.setDuration(duration);
//                            setAuthority(leaderId, sysUser, psBasicInfo);
//                            psBasicInfo.setUpdateBy(ShiroUtils.getLoginName());
                            this.updatePsBasicInfoHis(psBasicInfo);
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
                log.error(msg, e);
                failureMsg.append(msg).append(" Data exception for column [").append(MessageUtils.message(ExceptionUtil.getExceptionColumn(e.getMessage()))).append("] ");
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
     *  基本信息表更新
     * @param leaderId 数据拥有者
     * @param sysUser 当前用户
     * @param psBasicInfo 基本信息数据
     */
    private void setAuthority(String leaderId, SysUser sysUser, PsBasicInfoHis psBasicInfo) {

        // 设置权限组
        if (StringUtils.isNotEmpty(leaderId)) {
            psBasicInfo.setGroupIds(leaderId);
        }
    }
}
