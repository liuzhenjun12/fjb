package com.fjb.system.mapper;

import com.fjb.common.core.domain.entity.SysCity;

import java.util.List;

/**
 * 城市名称Mapper接口
 *
 * @author liu
 * @date 2021-08-10
 */
public interface SysCityMapper
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
     * 通过机构代码查询
     * @param code
     * @return
     */
    public SysCity selectByCode(String code);

    /**
     * 删除城市名称
     *
     * @param id 城市名称ID
     * @return 结果
     */
    public int deleteSysCityById(Long id);

    /**
     * 批量删除城市名称
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysCityByIds(Long[] ids);
}
