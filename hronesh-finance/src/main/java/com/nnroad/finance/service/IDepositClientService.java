package com.nnroad.finance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nnroad.finance.domain.DepositClient;
import com.nnroad.finance.model.dto.DepositClientDetailDto;
import com.nnroad.finance.model.form.DepositClientCondition;
import com.nnroad.finance.model.form.DepositForm;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * 客户押金接口
 */
public interface IDepositClientService extends IService<DepositClient> {

    /**
     * 获得客户押金合计
     *
     * @return 客户押金合计
     */
    public BigDecimal getDepositTotal();

    /**
     * 获得客户押金信息列表
     *
     * @param condition 客户押金条件信息
     * @return 客户押金信息列表
     */
    public List<DepositClient> getDepositClientList(DepositClientCondition condition);

    /**
     * 添加客户押金详细
     *
     * @param depositClientDetailDto 客户押金详细
     */
    public void addDepositClientDetail(DepositClientDetailDto depositClientDetailDto);

    /**
     * 更新客户押金详细
     *
     * @param depositClientDetailDto 客户押金详细
     */
    public void editDepositClientDetail(DepositClientDetailDto depositClientDetailDto);

    /**
     * 移除客户押金详细
     *
     * @param id 客户押金详细标识
     */
    public void removeDepositClientDetail(Long id);

    /**
     * 结算客户账单押金
     *
     * @param clientCode 客户编号
     */
    public void settleDepositClient(String clientCode);

    /**
     * 刷新客户剩余押金
     *
     * @param clientCode 客户编号
     */
    public void refreshDeposit(String clientCode);

    String importData(List<DepositClientDetailDto> list, boolean updateSupport, String operName);

    void export(DepositClientCondition condition, HttpServletResponse response);
}
