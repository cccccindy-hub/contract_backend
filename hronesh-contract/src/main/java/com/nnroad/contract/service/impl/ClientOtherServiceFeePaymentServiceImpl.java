package com.nnroad.client.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnroad.client.mapper.ClientOtherServiceFeePaymentMapper;
import com.nnroad.client.domain.ClientOtherServiceFeePayment;
import com.nnroad.client.service.IClientOtherServiceFeePaymentService;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author nnroad
 * @date 2024-12-19
 */
@Service
public class ClientOtherServiceFeePaymentServiceImpl implements IClientOtherServiceFeePaymentService
{
    @Autowired
    private ClientOtherServiceFeePaymentMapper clientOtherServiceFeePaymentMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public ClientOtherServiceFeePayment selectClientOtherServiceFeePaymentById(Long id)
    {
        return clientOtherServiceFeePaymentMapper.selectClientOtherServiceFeePaymentById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param clientOtherServiceFeePayment 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ClientOtherServiceFeePayment> selectClientOtherServiceFeePaymentList(ClientOtherServiceFeePayment clientOtherServiceFeePayment)
    {
        return clientOtherServiceFeePaymentMapper.selectClientOtherServiceFeePaymentList(clientOtherServiceFeePayment);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param clientOtherServiceFeePayment 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertClientOtherServiceFeePayment(ClientOtherServiceFeePayment clientOtherServiceFeePayment)
    {
        return clientOtherServiceFeePaymentMapper.insertClientOtherServiceFeePayment(clientOtherServiceFeePayment);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param clientOtherServiceFeePayment 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateClientOtherServiceFeePayment(ClientOtherServiceFeePayment clientOtherServiceFeePayment)
    {
        return clientOtherServiceFeePaymentMapper.updateClientOtherServiceFeePayment(clientOtherServiceFeePayment);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteClientOtherServiceFeePaymentByIds(Long[] ids)
    {
        return clientOtherServiceFeePaymentMapper.deleteClientOtherServiceFeePaymentByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteClientOtherServiceFeePaymentById(Long id)
    {
        return clientOtherServiceFeePaymentMapper.deleteClientOtherServiceFeePaymentById(id);
    }
}
