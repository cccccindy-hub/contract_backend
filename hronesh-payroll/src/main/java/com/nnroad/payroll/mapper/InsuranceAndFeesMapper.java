package com.nnroad.payroll.mapper;


import com.nnroad.payroll.domain.InsuranceAndFees;

import java.util.List;

/**
 * WorkInjuryInsuranceMapper接口
 */
public interface InsuranceAndFeesMapper
{
    /**
     * 查询WorkInjuryInsurance
     * 
     * @param duration WorkInjuryInsuranceID
     * @return WorkInjuryInsurance
     */
    public InsuranceAndFees selectInsuranceAndFeesById(String duration);

    /**
     * 查询InsuranceAndFees列表
     * 
     * @param insuranceAndFees InsuranceAndFees
     * @return InsuranceAndFees集合
     */
    public List<InsuranceAndFees> selectInsuranceAndFeesList(InsuranceAndFees insuranceAndFees);

    public List<InsuranceAndFees> selectInsuranceAndFeesNotBlankList(InsuranceAndFees insuranceAndFees);

}
