package com.nnroad.finance.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nnroad.common.exception.Asserts;
import com.nnroad.finance.domain.BankBeginningBalance;
import com.nnroad.finance.mapper.BankBeginningBalanceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class IBankBeginningBalanceServiceImpl extends ServiceImpl<BankBeginningBalanceMapper, BankBeginningBalance>
        implements IBankBeginningBalanceService {


    @Override
    public List<BankBeginningBalance> queryPage(BankBeginningBalance balance) {
        return lambdaQuery()
                .eq(StrUtil.isNotBlank(balance.getBankAccount()), BankBeginningBalance::getBankAccount, balance.getBankAccount())
                .eq(StrUtil.isNotBlank(balance.getCurrency()), BankBeginningBalance::getCurrency, balance.getCurrency())
                .list();
    }

    @Override
    public BankBeginningBalance detail(Long id) {
        return getById(id);
    }

    @Override
    public void add(BankBeginningBalance balance) {
        Asserts.isTrue(notExist(balance), "Data with the same account and currency already exists");

        save(balance);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(BankBeginningBalance balance) {
        Asserts.isTrue(notExist(balance), "Data with the same account and currency already exists");

        saveOrUpdate(balance);
    }

    @Override
    public void del(Long id) {
        baseMapper.deleteById(id);
    }

    private boolean notExist(BankBeginningBalance balance) {
        return lambdaQuery()
                .eq(BankBeginningBalance::getBankAccount, balance.getBankAccount())
                .eq(BankBeginningBalance::getCurrency, balance.getCurrency())
                .ne(balance.getId() != null, BankBeginningBalance::getId, balance.getId())
                .count() == 0;
    }
}
