package com.nnroad.finance.model.form;

import com.nnroad.finance.domain.DepositClient;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DepositClientCondition extends DepositClient {
    private String monthSettleStart;
    private String monthSettleEnd;

    private String[] clientCodeList;
}
