package com.nnroad.finance.service;

import com.nnroad.finance.model.form.DepositEmployeeCondition;
import com.nnroad.finance.model.vo.DepositEmployeeVo;

import java.util.List;

/**
 * 客户押金接口
 * 
 * @author Hrone
 * @date 2021-09-22
 */
public interface IDepositEmployeeService {

    /**
     * 获得客户雇员押金信息列表
     * @param condition 客户雇员押金条件信息
     * @return 客户雇员押金信息列表
     */
    public List<DepositEmployeeVo> getDepositEmployeeList(DepositEmployeeCondition condition);

    /**
     * 刷新雇员剩余押金
     * @param employeeCode 雇员编号
     */
    public void refreshDeposit(String employeeCode);
}
