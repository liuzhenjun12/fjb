package com.fjb.system.mapper;

import com.fjb.common.core.domain.entity.SysCity;

import java.util.List;

/**
 * 城市Mapper接口
 *
 * @author liu
 * @date 2021-08-16
 */
public interface SysCityMapper
{
    /**
     * 查询城市
     *
     * @param cid 城市ID
     * @return 城市
     */
    public SysCity selectSysCityById(Integer cid);

    /**
     * 查询城市列表
     *
     * @param sysCity 城市
     * @return 城市集合
     */
    public List<SysCity> selectSysCityList(SysCity sysCity);

    /**
     * 新增城市
     *
     * @param sysCity 城市
     * @return 结果
     */
    public int insertSysCity(SysCity sysCity);

    /**
     * 修改城市
     *
     * @param sysCity 城市
     * @return 结果
     */
    public int updateSysCity(SysCity sysCity);

    /**
     * 删除城市
     *
     * @param cid 城市ID
     * @return 结果
     */
    public int deleteSysCityById(Integer cid);

    /**
     * 批量删除城市
     *
     * @param cids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysCityByIds(Integer[] cids);
}
