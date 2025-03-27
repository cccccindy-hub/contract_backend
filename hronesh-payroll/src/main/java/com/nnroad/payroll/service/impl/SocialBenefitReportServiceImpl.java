package com.nnroad.payroll.service.impl;


import com.nnroad.common.core.text.Convert;
import com.nnroad.common.utils.ShiroUtils;
import com.nnroad.common.utils.StringUtils;
import com.nnroad.payroll.domain.exportC.SocialBenefitReport;
import com.nnroad.payroll.domain.exportC.StefaniniSocialBenefitReport;
import com.nnroad.payroll.mapper.SocialBenefitReportMapper;
import com.nnroad.payroll.service.ISocialBenefitReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * social_benefit_reportService业务层处理
 *
 * @author hrone
 * @date 2021-01-19
 */
@Service
public class SocialBenefitReportServiceImpl implements ISocialBenefitReportService {

    @Autowired
    private SocialBenefitReportMapper socialBenefitReportMapper;

    /**
     * 查询social_benefit_report
     *
     * @param idNo social_benefit_reportID
     * @return social_benefit_report
     */
    @Override
    public SocialBenefitReport selectSocialBenefitReportById(String idNo) {
        return socialBenefitReportMapper.selectSocialBenefitReportById(idNo);
    }

    /**
     * 查询social_benefit_report列表
     *
     * @param socialBenefitReport social_benefit_report
     * @return social_benefit_report
     */
    @Override
    public List<SocialBenefitReport> selectSocialBenefitReportList(SocialBenefitReport socialBenefitReport) {
        String duration=socialBenefitReport.getDuration();
        if (StringUtils.isNotEmpty(duration)) {
            String[] durationArray = duration.split("~");
            String beginTime = durationArray[0];
            String endTime = durationArray[1];
            socialBenefitReport.getParams().put("beginTime", beginTime.trim());
            socialBenefitReport.getParams().put("endTime", endTime.trim());
        }
        // 数据权限控制需传入用户id，部门id
//        socialBenefitReport.getParams().put("userId", ShiroUtils.getUserId().toString());
//        socialBenefitReport.getParams().put("deptId", ShiroUtils.getSysUser().getDeptId());
        // 跨库查询需要配置数据库名
//        socialBenefitReport.getParams().put("dbCommon", dbCommon);
        return socialBenefitReportMapper.selectSocialBenefitReportList(socialBenefitReport);
    }

    /**
     * 查询stefanini的social_benefit_report列表
     *
     * @param map 参数合集
     * @return social_benefit_report
     */
    @Override
    public List<StefaniniSocialBenefitReport> selectStefaniniSocialBenefitReportList(Map<String, Object> map) {
        return socialBenefitReportMapper.selectStefaniniSocialBenefitReportList(map);
    }

    /**
     * 新增social_benefit_report
     *
     * @param socialBenefitReport social_benefit_report
     * @return 结果
     */
    @Override
    public int insertSocialBenefitReport(SocialBenefitReport socialBenefitReport) {
        return socialBenefitReportMapper.insertSocialBenefitReport(socialBenefitReport);
    }

    /**
     * 修改social_benefit_report
     *
     * @param socialBenefitReport social_benefit_report
     * @return 结果
     */
    @Override
    public int updateSocialBenefitReport(SocialBenefitReport socialBenefitReport) {
        return socialBenefitReportMapper.updateSocialBenefitReport(socialBenefitReport);
    }

    /**
     * 删除social_benefit_report对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSocialBenefitReportByIds(String ids) {
        return socialBenefitReportMapper.deleteSocialBenefitReportByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除social_benefit_report信息
     *
     * @param idNo social_benefit_reportID
     * @return 结果
     */
    @Override
    public int deleteSocialBenefitReportById(String idNo) {
        return socialBenefitReportMapper.deleteSocialBenefitReportById(idNo);
    }
}
