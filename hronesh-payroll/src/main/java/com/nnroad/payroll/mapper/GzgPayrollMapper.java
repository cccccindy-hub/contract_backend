package com.nnroad.payroll.mapper;

import com.nnroad.common.annotation.DataSource;
import com.nnroad.common.enums.DataSourceType;
import com.nnroad.payroll.domain.PayrollEntry;
import com.nnroad.payroll.dto.PayrollDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@DataSource(value = DataSourceType.PAYSLIP_CAL)
public interface GzgPayrollMapper {

    PayrollDto selectByPayrollId(@Param("payrollId") String payrollId);

    List<PayrollEntry> selectByFpayroll(String fpayroll);

    String selectDuration(String fpayroll);

    String selectErName(String fpayroll);

    List<Map<String, String>> selectFidAndFcode(int femployer);

    int selectByFid(String fpayroll);
}
