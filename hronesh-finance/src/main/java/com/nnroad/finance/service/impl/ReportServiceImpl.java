package com.nnroad.finance.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.nnroad.common.enums.ColumnEnum;
import com.nnroad.common.exception.Asserts;
import com.nnroad.common.utils.PageUtils;
import com.nnroad.extraAttribute.domain.SysExtraAttribute;
import com.nnroad.extraAttribute.service.ISysExtraAttributeService;
import com.nnroad.finance.domain.HroneAccount;
import com.nnroad.finance.mapper.HroneAccountMapper;
import com.nnroad.finance.mapper.ReportMapper;
import com.nnroad.finance.model.dto.BankMonthlyReportDto;
import com.nnroad.finance.model.form.BankMonthlyReportForm;
import com.nnroad.finance.model.form.FinMonthlyStatisticsPageForm;
import com.nnroad.finance.model.form.FinanceQueryForm;
import com.nnroad.finance.model.vo.FinMonthlyStatisticsVo;
import com.nnroad.finance.service.IReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportServiceImpl implements IReportService {

    private final ReportMapper reportMapper;

    private final HroneAccountMapper accountMapper;

    private final ISysExtraAttributeService attributeService;

    @Override
    public List<BankMonthlyReportDto> monthlyReports(BankMonthlyReportForm form) {

        return reportMapper.monthlyReports(form);
    }

    @Override
    public List<FinMonthlyStatisticsVo> finMonthlyStatistics(FinMonthlyStatisticsPageForm form, boolean export) {
        if (!export) {
            PageUtils.startPage();
        }
        return reportMapper.finMonthlyStatistics(form, ColumnEnum.ER_CONTRACT_SERVICE_TYPE.getFId());
    }

    @Override
    public List<HroneAccount> quickImportList(FinanceQueryForm form) {
        String[] vendorNames = Optional.ofNullable(form.getParams().get("vendorNames")).map(remark -> ((String) remark).split(",")).orElse(null);
        form.getParams().put("vendorNames", vendorNames);
        return ObjectUtil.equals(form.getParams().get("type"), "1") ? accountMapper.accountQuickImport(form) :
                accountMapper.vendorQuickImport(form);
    }
}
