package com.nnroad.finance.service.impl;

import com.nnroad.finance.mapper.deposit.DepositEmployeeMapper;
import com.nnroad.finance.model.form.DepositEmployeeCondition;
import com.nnroad.finance.model.vo.DepositEmployeeVo;
import com.nnroad.finance.service.IDepositEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 客户押金业务层处理
 *
 * @author Lucio
 * @date 2021-09-13
 */
@Service
public class DepositEmployeeServiceImpl implements IDepositEmployeeService {

    @Autowired
    private DepositEmployeeMapper depositEmployeeMapper;

//    @Autowired
//    private ClientEmployeeMapper clientEmployeeMapper;

    /**
     * 获得客户雇员押金信息列表
     *
     * @param condition 客户雇员押金条件信息
     * @return 客户雇员押金信息列表
     */
    @Override
    public List<DepositEmployeeVo> getDepositEmployeeList(DepositEmployeeCondition condition) {
        return depositEmployeeMapper.getDepositEmployeeList(condition);
    }

    /**
     * 刷新雇员剩余押金
     *
     * @param employeeCode 雇员编号
     */
    public void refreshDeposit(String employeeCode) {
        /*SysUser user = SecurityUtils.getLoginUser().getUser();

        DepositEmployeeDto dto = depositEmployeeMapper.selectDepositEmployee(employeeCode);
        if (dto == null) {
            ClientEmployeeDto clientEmployeeDto = new ClientEmployeeDto();
            clientEmployeeDto.setCode(employeeCode);
            clientEmployeeDto = clientEmployeeMapper.selectClientEmployee(clientEmployeeDto);

            if (clientEmployeeDto != null) {
                dto = new DepositEmployeeDto();
                dto.setEmployeeCode(employeeCode);
                dto.setClientCode(clientEmployeeDto.getClientCode());
                dto.setAmount(BigDecimal.ZERO);
                dto.setCreateBy(user.getLoginName());
                dto.setUpdateBy(user.getLoginName());

                depositEmployeeMapper.insertDepositEmployee(dto);
            }
        }
        dto = new DepositEmployeeDto();
        dto.setEmployeeCode(employeeCode);
        dto.setUpdateBy(user.getLoginName());
        depositEmployeeMapper.refreshDeposit(dto);*/
    }
}