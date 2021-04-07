package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysProduct;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2021-04-02
 */
public interface SysProductMapper
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
    public int insertSysProduct(SysProduct sysProduct);

    /**
     * 修改【请填写功能名称】
     *
     * @param sysProduct 【请填写功能名称】
     * @return 结果
     */
    public int updateSysProduct(SysProduct sysProduct);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteSysProductById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysProductByIds(Long[] ids);

    /**
     * 通过客户id统计产品
     *
     */
    public int countByCid(Long cid);
}
