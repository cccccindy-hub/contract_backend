package com.nnroad.payroll.mapper;


import com.nnroad.payroll.domain.common.PsPayrollTax;

import java.util.List;

/**
 * ps_payroll_taxMapper接口
 * 
 * @author Hrone
 * @date 2021-01-21
 */

public interface PsPayrollTaxMapper 
{

    /**
     * 删除ps_payroll_tax
     *
     * @param psPayrollTax ps_payroll_tax
     * @return 结果
     */
    public int deletePsPayrollTaxByKey(PsPayrollTax psPayrollTax);
}
