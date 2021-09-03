package com.fjb.system.mapper;

import java.util.List;
import com.fjb.system.domain.Panduan;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author liu
 * @date 2021-08-30
 */
public interface PanduanMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public Panduan selectPanduanById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param panduan 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Panduan> selectPanduanList(Panduan panduan);

    /**
     * 新增【请填写功能名称】
     * 
     * @param panduan 【请填写功能名称】
     * @return 结果
     */
    public int insertPanduan(Panduan panduan);

    /**
     * 修改【请填写功能名称】
     * 
     * @param panduan 【请填写功能名称】
     * @return 结果
     */
    public int updatePanduan(Panduan panduan);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deletePanduanById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePanduanByIds(Long[] ids);
}
