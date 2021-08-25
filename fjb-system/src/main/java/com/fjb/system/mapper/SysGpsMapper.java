package com.fjb.system.mapper;

import java.util.List;
import com.fjb.system.domain.SysGps;

/**
 * 经纬度Mapper接口
 * 
 * @author liu
 * @date 2021-08-24
 */
public interface SysGpsMapper 
{
    /**
     * 查询经纬度
     * 
     * @param id 经纬度ID
     * @return 经纬度
     */
    public SysGps selectSysGpsById(Long id);

    /**
     * 查询经纬度列表
     * 
     * @param sysGps 经纬度
     * @return 经纬度集合
     */
    public List<SysGps> selectSysGpsList(SysGps sysGps);

    /**
     * 新增经纬度
     * 
     * @param sysGps 经纬度
     * @return 结果
     */
    public int insertSysGps(SysGps sysGps);

    /**
     * 修改经纬度
     * 
     * @param sysGps 经纬度
     * @return 结果
     */
    public int updateSysGps(SysGps sysGps);

    /**
     * 删除经纬度
     * 
     * @param id 经纬度ID
     * @return 结果
     */
    public int deleteSysGpsById(Long id);

    /**
     * 批量删除经纬度
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysGpsByIds(Long[] ids);
}
