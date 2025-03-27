package com.nnroad.client.mapper;

import java.util.List;
import com.nnroad.client.domain.ClientOtherServiceFee;
import org.apache.ibatis.annotations.Param;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2024-12-17
 */
public interface ClientOtherServiceFeeMapper
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
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteClientOtherServiceFeeById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClientOtherServiceFeeByIds(Long[] ids);

    void insertBatchByImport(@Param("list") List<ClientOtherServiceFee> list);
}
