package com.nnroad.payroll.mapper;

import com.nnroad.payroll.domain.exportC.PsCityBasicSalary;

import java.util.List;

/**
 * cityBasicSalaryMapper接口
 * 
 * @author Aaron
 * @date 2022-01-05
 */
public interface PsCityBasicSalaryMapper 
{
    /**
     * 查询cityBasicSalary
     * 
     * @param id cityBasicSalaryID
     * @return cityBasicSalary
     */
    public PsCityBasicSalary selectPsCityBasicSalaryById(Long id);

    /**
     * 查询cityBasicSalary列表
     * 
     * @param psCityBasicSalary cityBasicSalary
     * @return cityBasicSalary集合
     */
    public List<PsCityBasicSalary> selectPsCityBasicSalaryList(PsCityBasicSalary psCityBasicSalary);

    /**
     * 新增cityBasicSalary
     * 
     * @param psCityBasicSalary cityBasicSalary
     * @return 结果
     */
    public int insertPsCityBasicSalary(PsCityBasicSalary psCityBasicSalary);

    /**
     * 修改cityBasicSalary
     * 
     * @param psCityBasicSalary cityBasicSalary
     * @return 结果
     */
    public int updatePsCityBasicSalary(PsCityBasicSalary psCityBasicSalary);

    /**
     * 删除cityBasicSalary
     * 
     * @param id cityBasicSalaryID
     * @return 结果
     */
    public int deletePsCityBasicSalaryById(Long id);

    /**
     * 批量删除cityBasicSalary
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePsCityBasicSalaryByIds(String[] ids);
}
