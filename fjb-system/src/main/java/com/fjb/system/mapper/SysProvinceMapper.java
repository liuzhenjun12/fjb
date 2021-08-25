package com.fjb.system.mapper;

import com.fjb.common.core.domain.entity.SysProvince;

import java.util.List;

/**
 * 省份Mapper接口
 *
 * @author liu
 * @date 2021-08-16
 */
public interface SysProvinceMapper
{
    /**
     * 查询省份
     *
     * @param proid 省份ID
     * @return 省份
     */
    public SysProvince selectSysProvinceById(Integer proid);

    /**
     * 查询省份列表
     *
     * @param sysProvince 省份
     * @return 省份集合
     */
    public List<SysProvince> selectSysProvinceList(SysProvince sysProvince);

    /**
     * 新增省份
     *
     * @param sysProvince 省份
     * @return 结果
     */
    public int insertSysProvince(SysProvince sysProvince);

    /**
     * 修改省份
     *
     * @param sysProvince 省份
     * @return 结果
     */
    public int updateSysProvince(SysProvince sysProvince);

    /**
     * 删除省份
     *
     * @param proid 省份ID
     * @return 结果
     */
    public int deleteSysProvinceById(Integer proid);

    /**
     * 批量删除省份
     *
     * @param proids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysProvinceByIds(Integer[] proids);
}
