package com.fjb.system.mapper;

import java.util.List;
import com.fjb.system.domain.Duoxuan;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author liu
 * @date 2021-08-30
 */
public interface DuoxuanMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public Duoxuan selectDuoxuanById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param duoxuan 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Duoxuan> selectDuoxuanList(Duoxuan duoxuan);

    /**
     * 新增【请填写功能名称】
     * 
     * @param duoxuan 【请填写功能名称】
     * @return 结果
     */
    public int insertDuoxuan(Duoxuan duoxuan);

    /**
     * 修改【请填写功能名称】
     * 
     * @param duoxuan 【请填写功能名称】
     * @return 结果
     */
    public int updateDuoxuan(Duoxuan duoxuan);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteDuoxuanById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDuoxuanByIds(Long[] ids);
}
