package com.nnroad.finance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nnroad.finance.domain.BankBeginningBalance;

import java.util.List;

public interface IBankBeginningBalanceService extends IService<BankBeginningBalance> {

    List<BankBeginningBalance> queryPage(BankBeginningBalance balance);

    BankBeginningBalance detail(Long id);

    void add(BankBeginningBalance balance);

    void edit(BankBeginningBalance balance);

    void del(Long id);
}
