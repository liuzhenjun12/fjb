package com.fjb.system.service;

import com.fjb.common.core.domain.entity.SysTown;

import java.util.List;

/**
 * 县区Service接口
 *
 * @author liu
 * @date 2021-08-16
 */
public interface ISysTownService
{
    /**
     * 查询县区
     *
     * @param tid 县区ID
     * @return 县区
     */
    public SysTown selectSysTownById(Integer tid);

    /**
     * 查询县区列表
     *
     * @param sysTown 县区
     * @return 县区集合
     */
    public List<SysTown> selectSysTownList(SysTown sysTown);

    /**
     * 新增县区
     *
     * @param sysTown 县区
     * @return 结果
     */
    public int insertSysTown(SysTown sysTown);

    /**
     * 修改县区
     *
     * @param sysTown 县区
     * @return 结果
     */
    public int updateSysTown(SysTown sysTown);

    /**
     * 批量删除县区
     *
     * @param tids 需要删除的县区ID
     * @return 结果
     */
    public int deleteSysTownByIds(Integer[] tids);

    /**
     * 删除县区信息
     *
     * @param tid 县区ID
     * @return 结果
     */
    public int deleteSysTownById(Integer tid);
}
