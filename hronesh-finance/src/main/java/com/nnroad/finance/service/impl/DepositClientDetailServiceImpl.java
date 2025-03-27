package com.nnroad.finance.service.impl;

import com.nnroad.finance.mapper.deposit.DepositClientDetailMapper;
import com.nnroad.finance.model.dto.DepositClientDetailDto;
import com.nnroad.finance.model.form.DepositClientDetailCondition;
import com.nnroad.finance.model.vo.DepositClientDetailVo;
import com.nnroad.finance.service.IDepositClientDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 客户押金业务层处理
 * 
 * @author Lucio
 * @date 2021-09-13
 */
@Service
public class DepositClientDetailServiceImpl implements IDepositClientDetailService {

    @Autowired
    private DepositClientDetailMapper depositClientDetailMapper;

    /**
     * 获得客户押金详细
     * @param id 客户押金详细ID
     * @return 客户押金详细
     */
    public DepositClientDetailDto getDepositClientDetailById(Long id) {
        return depositClientDetailMapper.selectDepositClientDetailById(id);
    }

    /**
     * 获得客户押金详细列表
     * @param condition 客户押金详细条件信息
     * @return 客户押金详细列表
     */
    public List<DepositClientDetailVo> getDepositClientDetailList(DepositClientDetailCondition condition) {
        return depositClientDetailMapper.selectDepositClientDetailList(condition);
    }

    /**
     * 获得客户押金信息列表
     * @param condition 客户押金履历条件信息
     * @return 客户押金信息列表
     */
    public List<DepositClientDetailDto> getDepositClientDetailListFromPayroll(DepositClientDetailCondition condition) {
        return depositClientDetailMapper.selectClientDepositDetailFromPayroll(condition);
    }

    /**
     * 创建客户押金详细
     * @param depositClientDetailDto 客户押金详细
     */
    public int createDepositClientDetail(DepositClientDetailDto depositClientDetailDto) {
        if (depositClientDetailDto.getRate().doubleValue() > 0) {
            depositClientDetailDto.setAmountCurrency(depositClientDetailDto.getAmount().divide(depositClientDetailDto.getRate(),2,BigDecimal.ROUND_CEILING));
        } else {
            depositClientDetailDto.setAmountCurrency(BigDecimal.ZERO);
        }
        return depositClientDetailMapper.insertDepositClientDetail(depositClientDetailDto);
    }

    /**
     * 保存客户押金详细
     * @param depositClientDetailDto 客户押金详细
     */
    public int saveDepositClientDetail(DepositClientDetailDto depositClientDetailDto) {
        if (depositClientDetailDto.getRate().doubleValue() > 0) {
            depositClientDetailDto.setAmountCurrency(depositClientDetailDto.getAmount().divide(depositClientDetailDto.getRate(), 2,BigDecimal.ROUND_CEILING));
        } else {
            depositClientDetailDto.setAmountCurrency(BigDecimal.ZERO);
        }
        return depositClientDetailMapper.updateDepositClientDetail(depositClientDetailDto);
    }

    /**
     * 删除客户押金详细
     * @param id 客户押金详细ID
     */
    public int deleteDepositClientDetailById(Long id) {
        return depositClientDetailMapper.deleteDepositClientDetail(id);
    }

    /**
     * 删除客户押金详细
     * @param condition 客户押金详细条件信息
     */
    public int deleteDepositClientDetailBySettle(DepositClientDetailCondition condition) {
        return depositClientDetailMapper.deleteDepositClientDetailBySettle(condition);
    }
}