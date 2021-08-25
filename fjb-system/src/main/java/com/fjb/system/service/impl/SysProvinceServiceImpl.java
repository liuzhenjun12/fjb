package com.fjb.system.service.impl;

import java.util.List;

import com.fjb.common.core.domain.entity.SysProvince;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fjb.system.mapper.SysProvinceMapper;
import com.fjb.system.service.ISysProvinceService;

/**
 * 省份Service业务层处理
 *
 * @author liu
 * @date 2021-08-16
 */
@Service
public class SysProvinceServiceImpl implements ISysProvinceService
{
    @Autowired
    private SysProvinceMapper sysProvinceMapper;

    /**
     * 查询省份
     *
     * @param proid 省份ID
     * @return 省份
     */
    @Override
    public SysProvince selectSysProvinceById(Integer proid)
    {
        return sysProvinceMapper.selectSysProvinceById(proid);
    }

    /**
     * 查询省份列表
     *
     * @param sysProvince 省份
     * @return 省份
     */
    @Override
    public List<SysProvince> selectSysProvinceList(SysProvince sysProvince)
    {
        return sysProvinceMapper.selectSysProvinceList(sysProvince);
    }

    /**
     * 新增省份
     *
     * @param sysProvince 省份
     * @return 结果
     */
    @Override
    public int insertSysProvince(SysProvince sysProvince)
    {
        return sysProvinceMapper.insertSysProvince(sysProvince);
    }

    /**
     * 修改省份
     *
     * @param sysProvince 省份
     * @return 结果
     */
    @Override
    public int updateSysProvince(SysProvince sysProvince)
    {
        return sysProvinceMapper.updateSysProvince(sysProvince);
    }

    /**
     * 批量删除省份
     *
     * @param proids 需要删除的省份ID
     * @return 结果
     */
    @Override
    public int deleteSysProvinceByIds(Integer[] proids)
    {
        return sysProvinceMapper.deleteSysProvinceByIds(proids);
    }

    /**
     * 删除省份信息
     *
     * @param proid 省份ID
     * @return 结果
     */
    @Override
    public int deleteSysProvinceById(Integer proid)
    {
        return sysProvinceMapper.deleteSysProvinceById(proid);
    }
}
