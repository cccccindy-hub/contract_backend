// package com.ruoyi.system.mapper;
package com.nnroad.contract.mapper;
import java.util.List;
import com.nnroad.contract.domain.ContractUserform;
import com.nnroad.common.annotation.DataSource;
import com.nnroad.common.enums.DataSourceType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-25
 */
public interface ContractUserformMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public ContractUserform selectContractUserformByUserId(String userId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contractUserform 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ContractUserform> selectContractUserformList(ContractUserform contractUserform);

    /**
     * 新增【请填写功能名称】
     * 
     * @param contractUserform 【请填写功能名称】
     * @return 结果
     */
    public int insertContractUserform(ContractUserform contractUserform);

    /**
     * 修改【请填写功能名称】
     * 
     * @param contractUserform 【请填写功能名称】
     * @return 结果
     */
    public int updateContractUserform(ContractUserform contractUserform);

    /**
     * 删除【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteContractUserformByUserId(String userId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteContractUserformByUserIds(String[] userIds);
}
