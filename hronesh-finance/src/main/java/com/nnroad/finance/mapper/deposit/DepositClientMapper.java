package com.nnroad.finance.mapper.deposit;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nnroad.finance.domain.DepositClient;
import com.nnroad.finance.model.form.DepositClientCondition;

import java.math.BigDecimal;
import java.util.List;

/**
 * 客户押金映射
 */
public interface DepositClientMapper extends BaseMapper<DepositClient> {

    /**
     * 获得客户押金合计
     *
     * @return 客户押金合计
     */
    public BigDecimal getDepositTotal();

    /**
     * 获得客户押金信息
     *
     * @param clientCode 客户编号
     */
    public DepositClient selectDepositClient(String clientCode);

    /**
     * 获得客户押金信息列表
     *
     * @param condition 客户押金条件信息
     * @return 客户押金信息列表
     */
    public List<DepositClient> selectDepositClientList(DepositClientCondition condition);

    /**
     * 刷新客户剩余押金
     *
     * @param DepositClient 客户押金信息
     */
    public void insertDepositClient(DepositClient DepositClient);

    /**
     * 刷新客户剩余押金
     *
     * @param DepositClient 客户押金信息
     */
    public void refreshDeposit(DepositClient DepositClient);
}