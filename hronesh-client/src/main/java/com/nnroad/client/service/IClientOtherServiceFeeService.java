package com.nnroad.client.service;

import java.util.List;
import com.nnroad.client.domain.ClientOtherServiceFee;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2024-12-17
 */
public interface IClientOtherServiceFeeService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public ClientOtherServiceFee selectClientOtherServiceFeeById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param clientOtherServiceFee 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ClientOtherServiceFee> selectClientOtherServiceFeeList(ClientOtherServiceFee clientOtherServiceFee);

    /**
     * 新增【请填写功能名称】
     *
     * @param clientOtherServiceFee 【请填写功能名称】
     * @return 结果
     */
    public int insertClientOtherServiceFee(ClientOtherServiceFee clientOtherServiceFee);

    /**
     * 修改【请填写功能名称】
     *
     * @param clientOtherServiceFee 【请填写功能名称】
     * @return 结果
     */
    public int updateClientOtherServiceFee(ClientOtherServiceFee clientOtherServiceFee);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteClientOtherServiceFeeByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteClientOtherServiceFeeById(Long id);

    /**
     * Task to automatically generate every month Other Service Fee data
     */
    public int generateOtherServiceFee();
}
