package com.nnroad.payroll.service.impl;

import com.nnroad.common.annotation.DataSource;
import com.nnroad.common.enums.DataSourceType;
import com.nnroad.payroll.domain.TEEPayslip;
import com.nnroad.payroll.mapper.TEEPayslipMapper;
import com.nnroad.payroll.service.ITEEPayslipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TEEPayslipServiceImpl implements ITEEPayslipService {
    @Autowired
    private TEEPayslipMapper teePayslipMapper;

    @Override
    @Transactional
    @DataSource(value = DataSourceType.PAYSLIP_CAL)
    public void insertAll(List<TEEPayslip> TEEPayslips) {
        teePayslipMapper.insertAll(TEEPayslips);
    }


    @Override
    public void insert(TEEPayslip payslipDTO){
        teePayslipMapper.insert(payslipDTO);
    }
}
