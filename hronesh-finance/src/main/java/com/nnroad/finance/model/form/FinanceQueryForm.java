package com.nnroad.finance.model.form;

import com.nnroad.common.annotation.Log;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class FinanceQueryForm {

    private String clientCode;

    private String startPeriod;

    private String endPeriod;

    private String startDate;

    private String endDate;

    private Map<String, Object> params;

    private List<Long> ids;
}
