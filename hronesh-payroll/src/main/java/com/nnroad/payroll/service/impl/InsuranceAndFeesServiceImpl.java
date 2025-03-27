package com.nnroad.payroll.service.impl;

import com.nnroad.common.core.text.Convert;
import com.nnroad.common.utils.StringUtils;
import com.nnroad.payroll.domain.InsuranceAndFees;
import com.nnroad.payroll.mapper.InsuranceAndFeesMapper;
import com.nnroad.payroll.service.IInsuranceAndFeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * WorkInjuryInsuranceService业务层处理
 * 
 * @author Hrone
 * @date 2022-08-23
 */
@Service
public class InsuranceAndFeesServiceImpl implements IInsuranceAndFeesService
{
    @Autowired
    private InsuranceAndFeesMapper insuranceAndFeesMapper;

    /**
     * 查询WorkInjuryInsurance
     * 
     * @param duration WorkInjuryInsuranceID
     * @return WorkInjuryInsurance
     */
    @Override
    public InsuranceAndFees selectInsuranceAndFeesById(String duration)
    {
        return insuranceAndFeesMapper.selectInsuranceAndFeesById(duration);
    }

    /**
     * 查询InsuranceAndFees列表
     * 
     * @param insuranceAndFees InsuranceAndFees
     * @return InsuranceAndFees
     */
    @Override
    public List<InsuranceAndFees> selectInsuranceAndFeesList(InsuranceAndFees insuranceAndFees)
    {
        Map<String, Object> params  = insuranceAndFees.getParams();
        String duration = insuranceAndFees.getDuration();
        if (StringUtils.isNotEmpty(duration)) {
            String[] durationArray = duration.split("~");
            String beginTime = durationArray[0];
            String endTime = durationArray[1];
            params.put("beginTime", beginTime.trim());
            params.put("endTime", endTime.trim());
        }
        // 数据权限筛选条件
//        PayrollUtils.paramSet(params,dbCommon,null);
        insuranceAndFees.setParams(params);

        if (StringUtils.isNotEmpty(insuranceAndFees.getClientCode())) {
            insuranceAndFees.setClientCodeList(Convert.toStrArray(insuranceAndFees.getClientCode()));
        }
        if (StringUtils.isNotEmpty(insuranceAndFees.getIdNo())) {
            insuranceAndFees.setEeCodeList(Convert.toStrArray(insuranceAndFees.getIdNo()));
        }

        List<InsuranceAndFees> list =insuranceAndFeesMapper.selectInsuranceAndFeesList(insuranceAndFees);
        return list;
    }

}
