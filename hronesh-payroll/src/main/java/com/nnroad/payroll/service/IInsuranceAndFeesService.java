package com.nnroad.payroll.service;

import com.nnroad.payroll.domain.InsuranceAndFees;

import java.util.List;

/**
 * InsuranceAndFeesService接口

 */
public interface IInsuranceAndFeesService
{
    /**
     * 查询InsuranceAndFees
     * 
     * @param duration InsuranceAndFeesID
     * @return InsuranceAndFees
     */
    public InsuranceAndFees selectInsuranceAndFeesById(String duration);

    /**
     * 查询InsuranceAndFees列表
     * 
     * @param insuranceAndFees InsuranceAndFees
     * @return InsuranceAndFees集合
     */
    public List<InsuranceAndFees> selectInsuranceAndFeesList(InsuranceAndFees insuranceAndFees);

}
