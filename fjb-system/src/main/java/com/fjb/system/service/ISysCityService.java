package com.fjb.system.service;

import java.util.List;

import com.fjb.common.core.domain.TreeSelect;
import com.fjb.common.core.domain.entity.SysCity;

/**
 * 城市名称Service接口
 *
 * @author liu
 * @date 2021-08-10
 */
public interface ISysCityService
{
    /**
     * 查询城市名称
     *
     * @param id 城市名称ID
     * @return 城市名称
     */
    public SysCity selectSysCityById(Long id);

    /**
     * 查询城市名称列表
     *
     * @param sysCity 城市名称
     * @return 城市名称集合
     */
    public List<SysCity> selectSysCityList(SysCity sysCity);

    /**
     * 新增城市名称
     *
     * @param sysCity 城市名称
     * @return 结果
     */
    public int insertSysCity(SysCity sysCity);

    /**
     * 修改城市名称
     *
     * @param sysCity 城市名称
     * @return 结果
     */
    public int updateSysCity(SysCity sysCity);

    /**
     * 批量删除城市名称
     *
     * @param ids 需要删除的城市名称ID
     * @return 结果
     */
    public int deleteSysCityByIds(Long[] ids);

    /**
     * 删除城市名称信息
     *
     * @param id 城市名称ID
     * @return 结果
     */
    public int deleteSysCityById(Long id);


    public List<TreeSelect> buildCityTreeSelect(List<SysCity> list);
}
