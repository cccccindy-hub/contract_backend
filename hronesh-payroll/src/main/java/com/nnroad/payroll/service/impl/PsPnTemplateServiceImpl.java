package com.nnroad.payroll.service.impl;


import com.nnroad.common.core.text.Convert;
import com.nnroad.common.utils.DateUtils;
import com.nnroad.payroll.domain.exportC.PsPnTemplate;
import com.nnroad.payroll.mapper.PsPnTemplateMapper;
import com.nnroad.payroll.service.IPsPnTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PN模板管理Service业务层处理
 */
@Service
public class PsPnTemplateServiceImpl implements IPsPnTemplateService
{
    @Autowired
    private PsPnTemplateMapper psPnTemplateMapper;

    /**
     * 查询PN模板管理
     * 
     * @param id PN模板管理ID
     * @return PN模板管理
     */
    @Override
    public PsPnTemplate selectPsPnTemplateById(Long id)
    {
        return psPnTemplateMapper.selectPsPnTemplateById(id);
    }

    /**
     * 查询PN模板管理列表
     * 
     * @param psPnTemplate PN模板管理
     * @return PN模板管理
     */
    @Override
    public List<PsPnTemplate> selectPsPnTemplateList(PsPnTemplate psPnTemplate)
    {
        return psPnTemplateMapper.selectPsPnTemplateList(psPnTemplate);
    }

    /**
     * 新增PN模板管理
     * 
     * @param psPnTemplate PN模板管理
     * @return 结果
     */
    @Override
    public int insertPsPnTemplate(PsPnTemplate psPnTemplate)
    {
        psPnTemplate.setCreateTime(DateUtils.getNowDate());
        return psPnTemplateMapper.insertPsPnTemplate(psPnTemplate);
    }

    /**
     * 修改PN模板管理
     * 
     * @param psPnTemplate PN模板管理
     * @return 结果
     */
    @Override
    public int updatePsPnTemplate(PsPnTemplate psPnTemplate)
    {
        psPnTemplate.setUpdateTime(DateUtils.getNowDate());
        return psPnTemplateMapper.updatePsPnTemplate(psPnTemplate);
    }

    /**
     * 删除PN模板管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePsPnTemplateByIds(String ids)
    {
        return psPnTemplateMapper.deletePsPnTemplateByIds(Convert.toStrArray(ids));
    }

}
