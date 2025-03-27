package com.nnroad.finance.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.nnroad.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("bank_account_beginning_balance")
@EqualsAndHashCode(callSuper = true)
public class BankBeginningBalance extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Date date;

    @NotBlank(message = "Service Name can not blank")
    private String serviceName;

    private Date forPeriod;

//    @NotBlank(message = "Bank can not blank")
    private String bank;

//    @NotBlank(message = "Bank Account can not blank")
    private String bankAccount;

    @NotBlank(message = "Currency can not blank")
    private String currency;

    @NotNull(message = "Beginning Balance can not null")
    private BigDecimal beginningBalance;

    private Integer status;
}
