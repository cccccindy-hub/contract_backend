package com.nnroad.payroll.service;

import com.nnroad.payroll.domain.exportC.SocialBenefitReport;
import com.nnroad.payroll.domain.exportC.StefaniniSocialBenefitReport;

import java.util.List;
import java.util.Map;

/**
 * social_benefit_reportService接口
 *
 * @author hrone
 * @date 2021-01-19
 */
public interface ISocialBenefitReportService {
    /**
     * 查询social_benefit_report
     *
     * @param idNo social_benefit_reportID
     * @return social_benefit_report
     */
    public SocialBenefitReport selectSocialBenefitReportById(String idNo);

    /**
     * 查询social_benefit_report列表
     *
     * @param socialBenefitReport social_benefit_report
     * @return social_benefit_report集合
     */
    public List<SocialBenefitReport> selectSocialBenefitReportList(SocialBenefitReport socialBenefitReport);

    /**
     * 查询stefanini的social_benefit_report列表
     *
     * @param map 参数合集
     * @return social_benefit_report
     */
    public List<StefaniniSocialBenefitReport> selectStefaniniSocialBenefitReportList(Map<String, Object> map);

    /**
     * 新增social_benefit_report
     *
     * @param socialBenefitReport social_benefit_report
     * @return 结果
     */
    public int insertSocialBenefitReport(SocialBenefitReport socialBenefitReport);

    /**
     * 修改social_benefit_report
     *
     * @param socialBenefitReport social_benefit_report
     * @return 结果
     */
    public int updateSocialBenefitReport(SocialBenefitReport socialBenefitReport);

    /**
     * 批量删除social_benefit_report
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSocialBenefitReportByIds(String ids);

    /**
     * 删除social_benefit_report信息
     *
     * @param idNo social_benefit_reportID
     * @return 结果
     */
    public int deleteSocialBenefitReportById(String idNo);
}
