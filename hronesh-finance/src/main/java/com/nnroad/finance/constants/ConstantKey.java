package com.nnroad.finance.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface ConstantKey {


    String ACCOUNT_EXPORT_NAME = "HROne上海账套_%s";

    String ACCOUNT_QUICK_IMPORT_NAME = "account_quick_import_%s";

    String DEPOSIT_CLIENT_EXPORT_NAME = "Deposit_Client_%s";

    String DEPOSIT_CLIENT_EE_EXPORT_NAME = "Deposit_Employee_%s";

    String BANK_MONTHLY_EXPORT_NAME = "Bank_Monthly_Report_%s";

    String STATISTICS_MONTHLY_EXPORT_NAME = "Statistics_Monthly_Report_%s";

    String INVOICE_EXPORT_NAME = "异地供应商账单%s";

    List<String> ENTRY_IGNORE_FILES = new ArrayList<>(Arrays.asList("searchValue", "createBy", "createTime", "updateBy", "updateTime", "remark", "params"));

    List<String> FINANCE_STATISTICS_IGNORE_FILES = Arrays.asList("searchValue", "createBy", "createTime", "updateBy", "updateTime",
            "remark", "params", "date", "serviceName", "checkType", "bank", "bankAccount", "wantedCurrency", "fixedRemark", "remark1");
}
