package com.fjb.system.mapper;

import java.util.List;
import com.fjb.system.domain.Danxuan;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author liu
 * @date 2021-08-28
 */
public interface DanxuanMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public Danxuan selectDanxuanById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param danxuan 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Danxuan> selectDanxuanList(Danxuan danxuan);

    /**
     * 新增【请填写功能名称】
     * 
     * @param danxuan 【请填写功能名称】
     * @return 结果
     */
    public int insertDanxuan(Danxuan danxuan);

    /**
     * 修改【请填写功能名称】
     * 
     * @param danxuan 【请填写功能名称】
     * @return 结果
     */
    public int updateDanxuan(Danxuan danxuan);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteDanxuanById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDanxuanByIds(Long[] ids);
}
