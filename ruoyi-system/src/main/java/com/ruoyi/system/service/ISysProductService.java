package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysProduct;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2021-04-02
 */
public interface ISysProductService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public SysProduct selectSysProductById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param sysProduct 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysProduct> selectSysProductList(SysProduct sysProduct);

    /**
     * 新增【请填写功能名称】
     *
     * @param sysProduct 【请填写功能名称】
     * @return 结果
     */
    public AjaxResult insertSysProduct(SysProduct sysProduct);

    /**
     * 修改【请填写功能名称】
     *
     * @param sysProduct 【请填写功能名称】
     * @return 结果
     */
    public AjaxResult updateSysProduct(SysProduct sysProduct);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public AjaxResult deleteSysProductByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteSysProductById(Long id);
}
