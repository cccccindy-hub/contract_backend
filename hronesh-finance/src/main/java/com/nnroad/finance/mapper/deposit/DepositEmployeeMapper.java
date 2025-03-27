package com.nnroad.finance.mapper.deposit;

import com.nnroad.finance.model.dto.DepositEmployeeDto;
import com.nnroad.finance.model.form.DepositEmployeeCondition;
import com.nnroad.finance.model.vo.DepositEmployeeVo;

import java.util.List;

/**
 * 客户押金映射
 * 
 * @author Lucio
 * @date 2021-05-13
 */
public interface DepositEmployeeMapper {

    /**
     * 获得客户雇员押金信息
     * @param employeeCode 客户雇员编号
     */
    public DepositEmployeeDto selectDepositEmployee(String employeeCode);

    /**
     * 获得客户雇员押金信息列表
     * @param condition 客户雇员押金条件信息
     * @return 客户雇员押金信息列表
     */
    public List<DepositEmployeeVo> getDepositEmployeeList(DepositEmployeeCondition condition);

    /**
     * 刷新客户雇员剩余押金
     * @param depositEmployeeDto 客户雇员押金信息
     */
    public void insertDepositEmployee(DepositEmployeeDto depositEmployeeDto);

    /**
     * 刷新雇员剩余押金
     * @param depositEmployeeDto 客户雇员押金信息
     */
    public void refreshDeposit(DepositEmployeeDto depositEmployeeDto);
}