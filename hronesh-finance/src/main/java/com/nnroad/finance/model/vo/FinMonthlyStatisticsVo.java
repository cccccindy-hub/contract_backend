package com.nnroad.finance.model.vo;

import com.nnroad.finance.domain.HroneAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class FinMonthlyStatisticsVo extends HroneAccount implements Serializable {
    private static final long serialVersionUID = -51818777277502423L;

    private String serviceType;

    private String actualDate;
}
