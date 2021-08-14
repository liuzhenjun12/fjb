package com.fjb.system.mapper;

import java.util.List;
import com.fjb.system.domain.FjbTown;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author liu
 * @date 2021-08-12
 */
public interface FjbTownMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param tid 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FjbTown selectFjbTownById(Integer tid);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param fjbTown 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FjbTown> selectFjbTownList(FjbTown fjbTown);

    /**
     * 新增【请填写功能名称】
     * 
     * @param fjbTown 【请填写功能名称】
     * @return 结果
     */
    public int insertFjbTown(FjbTown fjbTown);

    /**
     * 修改【请填写功能名称】
     * 
     * @param fjbTown 【请填写功能名称】
     * @return 结果
     */
    public int updateFjbTown(FjbTown fjbTown);

    /**
     * 删除【请填写功能名称】
     * 
     * @param tid 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFjbTownById(Integer tid);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param tids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFjbTownByIds(Integer[] tids);
}
