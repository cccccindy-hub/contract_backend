package com.nnroad.finance.model.form;

import cn.hutool.core.util.StrUtil;
import com.nnroad.finance.model.dto.DepositClientDetailDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DepositClientDetailCondition extends DepositClientDetailDto {

    private String month;
    private String monthMin;
    private String monthMax;
    private String dateMin;
    private String dateMax;

    public static DepositClientDetailCondition fromDepositForm(DepositForm form) {
        DepositClientDetailCondition condition = new DepositClientDetailCondition();
        condition.setClientCode(form.getClientCode());
        condition.setEmployeeCode(form.getEmployeeCode());
        condition.setType(form.getType());

        String dateRange = form.getDateRange();
        if (StrUtil.isNotBlank(dateRange)) {
            String[] temp = dateRange.replaceAll(" ", "").split("~");
            if (temp.length == 2) {
                condition.setDateMin(temp[0]);
                condition.setDateMax(temp[1]);
            }
        } else if(StrUtil.isNotBlank(form.getMonthSettle())) {
            condition.setMonth(form.getMonthSettle());
        }


        return condition;
    }
}
