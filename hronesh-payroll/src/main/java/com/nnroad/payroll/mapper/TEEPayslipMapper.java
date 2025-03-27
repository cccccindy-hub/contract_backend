package com.nnroad.payroll.mapper;

import com.nnroad.common.annotation.DataSource;
import com.nnroad.common.enums.DataSourceType;
import com.nnroad.payroll.domain.TEEPayslip;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DataSource(value = DataSourceType.PAYSLIP_CAL)
public interface TEEPayslipMapper {

    @DataSource(value = DataSourceType.PAYSLIP_CAL)
    void insertAll(List<TEEPayslip> payslipDTOs);

    @Insert(" INSERT INTO t_ee_payslip(fcert_name,fcert_no,femployer_name,fperiod,femployer, fdata)values (#{fcertName}, #{fcertNo}, #{femployerName},#{fperiod},#{femployer},CAST(#{fdata} AS JSON))")
    void insert(TEEPayslip payslipDTO);
}
