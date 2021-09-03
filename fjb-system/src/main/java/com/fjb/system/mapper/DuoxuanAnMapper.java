package com.fjb.system.mapper;

import com.fjb.system.domain.DuoxuanAn;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author liu
 * @date 2021-08-30
 */
public interface DuoxuanAnMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public DuoxuanAn selectDuoxuanAnById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param duoxuanAn 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<DuoxuanAn> selectDuoxuanAnList(DuoxuanAn duoxuanAn);

    /**
     * 新增【请填写功能名称】
     *
     * @param duoxuanAn 【请填写功能名称】
     * @return 结果
     */
    public int insertDuoxuanAn(DuoxuanAn duoxuanAn);

    /**
     * 修改【请填写功能名称】
     *
     * @param duoxuanAn 【请填写功能名称】
     * @return 结果
     */
    public int updateDuoxuanAn(DuoxuanAn duoxuanAn);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteDuoxuanAnById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDuoxuanAnByIds(Long[] ids);
}
