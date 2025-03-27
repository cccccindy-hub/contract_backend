package com.nnroad.client.mapper;

import java.util.List;
import com.nnroad.client.domain.ClientOtherServiceFeePayment;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2024-12-19
 */
public interface ClientOtherServiceFeePaymentMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public ClientOtherServiceFeePayment selectClientOtherServiceFeePaymentById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param clientOtherServiceFeePayment 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ClientOtherServiceFeePayment> selectClientOtherServiceFeePaymentList(ClientOtherServiceFeePayment clientOtherServiceFeePayment);

    /**
     * 新增【请填写功能名称】
     *
     * @param clientOtherServiceFeePayment 【请填写功能名称】
     * @return 结果
     */
    public int insertClientOtherServiceFeePayment(ClientOtherServiceFeePayment clientOtherServiceFeePayment);

    /**
     * 修改【请填写功能名称】
     *
     * @param clientOtherServiceFeePayment 【请填写功能名称】
     * @return 结果
     */
    public int updateClientOtherServiceFeePayment(ClientOtherServiceFeePayment clientOtherServiceFeePayment);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteClientOtherServiceFeePaymentById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClientOtherServiceFeePaymentByIds(Long[] ids);

    int insertBatch(List<ClientOtherServiceFeePayment> allPayments);

    void deleteClientOtherServiceFeePaymentByPid(Long clientOtherServiceFeeId);
}
