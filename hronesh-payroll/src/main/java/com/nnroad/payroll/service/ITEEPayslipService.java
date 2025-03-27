package com.nnroad.payroll.service;

import com.nnroad.payroll.domain.TEEPayslip;

import java.util.List;


public interface ITEEPayslipService {


    void insertAll(List<TEEPayslip> TEEPayslips);

    void insert(TEEPayslip payslipDTO);
}

