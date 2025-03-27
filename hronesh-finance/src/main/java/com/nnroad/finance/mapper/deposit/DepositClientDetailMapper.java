package com.nnroad.finance.mapper.deposit;


import com.nnroad.finance.model.dto.DepositClientDetailDto;
import com.nnroad.finance.model.form.DepositClientDetailCondition;
import com.nnroad.finance.model.vo.DepositClientDetailVo;

import java.util.List;

/**
 * 客户押金映射
 * 
 * @author Lucio
 * @date 2021-05-13
 */
public interface DepositClientDetailMapper {

    /**
     * 获得客户押金详细
     * @param id 客户押金详细ID
     * @return 客户押金详细
     */
    public DepositClientDetailDto selectDepositClientDetailById(Long id);

    /**
     * 获得客户押金详细列表
     * @param condition 客户押金详细条件信息
     * @return 客户押金详细列表
     */
    public List<DepositClientDetailVo> selectDepositClientDetailList(DepositClientDetailCondition condition);

    /**
     * 获得客户押金信息列表
     * @param condition 客户押金履历条件信息
     * @return 客户押金信息列表
     */
    public List<DepositClientDetailDto> selectClientDepositDetailFromPayroll(DepositClientDetailCondition condition);

    /**
     * 创建客户押金详细
     * @param depositClientDetailDto 客户押金详细
     */
    public int insertDepositClientDetail(DepositClientDetailDto depositClientDetailDto);

    /**
     * 更新客户押金详细
     * @param depositClientDetailDto 客户押金详细
     */
    public int updateDepositClientDetail(DepositClientDetailDto depositClientDetailDto);

    /**
     * 删除客户押金详细
     * @param id 客户押金详细ID
     */
    public int deleteDepositClientDetail(Long id);

    /**
     * 删除客户押金详细
     * @param condition 客户押金详细条件信息
     */
    public int deleteDepositClientDetailBySettle(DepositClientDetailCondition condition);

    /**
     * 获得hk客户押金信息列表
     * @param condition 客户押金履历条件信息
     * @return 客户押金信息列表
     */
    public List<DepositClientDetailDto> selectClientDepositDetailFromHk(DepositClientDetailCondition condition);

    List<DepositClientDetailDto> selectDepositClientDetailDto(DepositClientDetailDto depositClientDetailDto);
}