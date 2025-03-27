package com.nnroad.finance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nnroad.finance.domain.HroneAccount;
import com.nnroad.finance.model.form.FinanceQueryForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HroneAccountMapper extends BaseMapper<HroneAccount> {

    List<HroneAccount> accountQuickImport(@Param("form") FinanceQueryForm form);

    List<HroneAccount> vendorQuickImport(@Param("form") FinanceQueryForm form);
}
