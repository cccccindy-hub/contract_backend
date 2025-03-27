package com.nnroad.payroll.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.nnroad.common.core.text.Convert;
import com.nnroad.common.utils.StringUtils;
import com.nnroad.payroll.domain.exportC.AverageSalaryReport;
import com.nnroad.payroll.domain.exportC.PsCityBasicSalary;
import com.nnroad.payroll.mapper.AverageSalaryReportMapper;
import com.nnroad.payroll.mapper.PsCityBasicSalaryMapper;
import com.nnroad.payroll.service.IAverageSalaryReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.nnroad.common.utils.DateUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * AverageSalaryReportService业务层处理
 *
 */
@Service
public class AverageSalaryReportServiceImpl implements IAverageSalaryReportService
{
    private static BigDecimal ZERO = new BigDecimal("0.00");

    @Autowired
    private AverageSalaryReportMapper averageSalaryReportMapper;

    @Autowired
    private PsCityBasicSalaryMapper psCityBasicSalaryMapper;

    /**
     * 查询AverageSalaryReport
     * 
     * @param duration AverageSalaryReportID
     * @return AverageSalaryReport
     */
    @Override
    public AverageSalaryReport selectAverageSalaryReportById(String duration)
    {
        return averageSalaryReportMapper.selectAverageSalaryReportById(duration);
    }

    /**
     * 查询AverageSalaryReport列表
     * 
     * @param averageSalaryReport AverageSalaryReport
     * @return AverageSalaryReport
     */
    @Override
    public List<AverageSalaryReport> selectAverageSalaryReportList(AverageSalaryReport averageSalaryReport)
    {
        Map<String, Object> params = averageSalaryReport.getParams();
        String duration = averageSalaryReport.getDuration();
        if (StringUtils.isNotEmpty(duration)) {
            String[] durationArray = duration.split("~");
            String beginTime = durationArray[0];
            String endTime = durationArray[1];
            params.put("beginTime", beginTime.trim());
            params.put("endTime", endTime.trim());
        }

//        // 数据权限筛选条件
//        PayrollUtils.paramSet(params,dbCommon,null);

        List<AverageSalaryReport> dataList = averageSalaryReportMapper.selectAverageSalaryReportList(averageSalaryReport);

        for(AverageSalaryReport data :dataList) {

            // 交社保金额
            BigDecimal pension =data.getPension()==null?ZERO:data.getPension();
            BigDecimal medical =data.getMedical()==null?ZERO:data.getMedical();
            BigDecimal unemployment =data.getUnemployment()==null?ZERO:data.getUnemployment();
            BigDecimal socialBenefits = pension.add(medical).add(unemployment);
            BigDecimal annualBonus = data.getAnnualBonus()==null?ZERO:data.getAnnualBonus();
            BigDecimal basicSalary = data.getBasicSalary()==null?ZERO:data.getBasicSalary();
            BigDecimal basicSalaryResult = data.getBasicSalaryResult()==null?ZERO:data.getBasicSalaryResult();

            // 入离职的情况1

            if(!NumberUtil.equals(ZERO,data.getEalaDeduction())){
                // 税前
                if("税后".equals(data.getPreAfterTax())) {
                    // 交了社保
                    if(!NumberUtil.equals(ZERO,socialBenefits)){
                        data.setSocialWage(basicSalaryResult.add(annualBonus));
                    } else {
                        data.setSocialWage(ZERO);
                    }
                } else {
                    // 交了社保
                    if(!NumberUtil.equals(ZERO,socialBenefits)){
                        if(data.getBasicSalary() != null){
                            data.setSocialWage(basicSalary.add(annualBonus));
                        }
                    } else {
                        data.setSocialWage(ZERO);
                    }
                }
            }

            // 入离职的情况2
            // 税前
            if("税后".equals(data.getPreAfterTax())) {
                if(data.getSocialWage() != null && data.getSocialWage().compareTo(basicSalaryResult) < 0){
                    // 当月是入职
                    if(data.getContractStarttime() != null && data.getContractStarttime().startsWith(DateUtils.dateFormat(data.getDuration()))) {
                        data.setSocialWage(basicSalaryResult);
                    }
                    // 当月是离职
                    if(data.getLeaveDate()!=null && data.getLeaveDate().startsWith(DateUtils.dateFormat(data.getDuration()))) {
                        data.setSocialWage(basicSalaryResult);
                    }
                }
            } else {
                if(data.getSocialWage() != null && data.getSocialWage().compareTo(basicSalary) < 0){
                    // 当月是入职
                    if(data.getContractStarttime() != null && data.getContractStarttime().startsWith(DateUtils.dateFormat(data.getDuration()))) {
                        data.setSocialWage(basicSalary);
                    }
                    // 当月是离职
                    if(data.getLeaveDate()!=null && data.getLeaveDate().startsWith(DateUtils.dateFormat(data.getDuration()))) {
                        data.setSocialWage(basicSalary);
                    }
                }
            }


            // 月度工资收入如低于最低工资的80%
            PsCityBasicSalary psCityBasicSalary = new PsCityBasicSalary();
            psCityBasicSalary.setCity(data.getSbAddress());
            psCityBasicSalary.setDuration(data.getDuration());
            List<PsCityBasicSalary> cityBasicSalaryList  = psCityBasicSalaryMapper.selectPsCityBasicSalaryList(psCityBasicSalary);
            if(cityBasicSalaryList != null  && cityBasicSalaryList.size() > 0){
                Long cityBasicSalary = cityBasicSalaryList.get(0).getBasicSalary().longValue();
                BigDecimal netIncome =data.getNetIncome()==null?ZERO:data.getNetIncome();
                BigDecimal alDeduction =  data.getAlDeduction()==null?ZERO:data.getAlDeduction();
                if(netIncome.longValue() < 0.8 * cityBasicSalary && alDeduction.longValue() < 0.2 * cityBasicSalary){
                    // 交了社保
                    if(!NumberUtil.equals(ZERO,socialBenefits)){
                        data.setSocialWage(new BigDecimal(0.8 * cityBasicSalary));
                    } else {
                        data.setSocialWage(ZERO);
                    }
                }

                data.setMinimumWage(new BigDecimal( cityBasicSalary));
                if(data.getSocialWage() != null){
                    if (data.getSocialWage().compareTo(data.getMinimumWage()) < 0) {
                        data.setAbnormalFlg(1);
                    } else {
                        data.setAbnormalFlg(0);
                    }
                }
            } else {
                data.setAbnormalFlg(1);
            }
        }

        return dataList;
    }

    /**
     * 获取当月天数
     *
     * @param year
     * @param month
     * @return
     */
    private int getDaysByYearMonth(int year, int month)
    {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 新增AverageSalaryReport
     * 
     * @param averageSalaryReport AverageSalaryReport
     * @return 结果
     */
    @Override
    public int insertAverageSalaryReport(AverageSalaryReport averageSalaryReport)
    {
        return averageSalaryReportMapper.insertAverageSalaryReport(averageSalaryReport);
    }

    /**
     * 修改AverageSalaryReport
     * 
     * @param averageSalaryReport AverageSalaryReport
     * @return 结果
     */
    @Override
    public int updateAverageSalaryReport(AverageSalaryReport averageSalaryReport)
    {
        return averageSalaryReportMapper.updateAverageSalaryReport(averageSalaryReport);
    }

    /**
     * 删除AverageSalaryReport对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAverageSalaryReportByIds(String ids)
    {
        return averageSalaryReportMapper.deleteAverageSalaryReportByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除AverageSalaryReport信息
     * 
     * @param duration AverageSalaryReportID
     * @return 结果
     */
    @Override
    public int deleteAverageSalaryReportById(String duration)
    {
        return averageSalaryReportMapper.deleteAverageSalaryReportById(duration);
    }
}
