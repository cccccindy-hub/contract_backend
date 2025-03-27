package com.nnroad.finance.service;


import com.nnroad.finance.model.dto.DepositClientDetailDto;
import com.nnroad.finance.model.form.DepositClientDetailCondition;
import com.nnroad.finance.model.vo.DepositClientDetailVo;

import java.util.List;

/**
 * 客户押金接口
 *
 * @author Hrone
 * @date 2021-09-22
 */
public interface IDepositClientDetailService {

    /**
     * 获得客户押金详细
     *
     * @param id 客户押金详细ID
     * @return 客户押金详细
     */
    public DepositClientDetailDto getDepositClientDetailById(Long id);

    /**
     * 获得客户押金详细列表
     *
     * @param condition 客户押金详细条件信息
     * @return 客户押金详细列表
     */
    public List<DepositClientDetailVo> getDepositClientDetailList(DepositClientDetailCondition condition);

    /**
     * 获得客户押金信息列表
     *
     * @param condition 客户押金履历条件信息
     * @return 客户押金信息列表
     */
    public List<DepositClientDetailDto> getDepositClientDetailListFromPayroll(DepositClientDetailCondition condition);

    /**
     * 创建客户押金详细
     *
     * @param depositClientDetailDto 客户押金详细
     */
    public int createDepositClientDetail(DepositClientDetailDto depositClientDetailDto);

    /**
     * 保存客户押金详细
     *
     * @param depositClientDetailDto 客户押金详细
     */
    public int saveDepositClientDetail(DepositClientDetailDto depositClientDetailDto);

    /**
     * 删除客户押金详细
     *
     * @param id 客户押金详细ID
     */
    public int deleteDepositClientDetailById(Long id);

    /**
     * 删除客户押金详细
     *
     * @param condition 客户押金详细条件信息
     */
    public int deleteDepositClientDetailBySettle(DepositClientDetailCondition condition);
}
