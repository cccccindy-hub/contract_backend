package com.nnroad.payroll.service;


import com.nnroad.payroll.domain.exportC.PsPnTemplate;

import java.util.List;

/**
 * PN模板管理Service接口
 * 
 * @author Hrone
 * @date 2022-02-21
 */
public interface IPsPnTemplateService 
{
    /**
     * 查询PN模板管理
     * 
     * @param id PN模板管理ID
     * @return PN模板管理
     */
    public PsPnTemplate selectPsPnTemplateById(Long id);

    /**
     * 查询PN模板管理列表
     * 
     * @param psPnTemplate PN模板管理
     * @return PN模板管理集合
     */
    public List<PsPnTemplate> selectPsPnTemplateList(PsPnTemplate psPnTemplate);

    /**
     * 新增PN模板管理
     * 
     * @param psPnTemplate PN模板管理
     * @return 结果
     */
    public int insertPsPnTemplate(PsPnTemplate psPnTemplate);

    /**
     * 修改PN模板管理
     * 
     * @param psPnTemplate PN模板管理
     * @return 结果
     */
    public int updatePsPnTemplate(PsPnTemplate psPnTemplate);

    /**
     * 批量删除PN模板管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePsPnTemplateByIds(String ids);
}
