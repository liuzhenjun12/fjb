package com.fjb.system.service.impl;

import java.util.List;

import com.fjb.common.core.domain.entity.SysCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fjb.system.mapper.SysCityMapper;
import com.fjb.system.service.ISysCityService;

/**
 * 城市Service业务层处理
 *
 * @author liu
 * @date 2021-08-16
 */
@Service
public class SysCityServiceImpl implements ISysCityService
{
    @Autowired
    private SysCityMapper sysCityMapper;

    /**
     * 查询城市
     *
     * @param cid 城市ID
     * @return 城市
     */
    @Override
    public SysCity selectSysCityById(Integer cid)
    {
        return sysCityMapper.selectSysCityById(cid);
    }

    /**
     * 查询城市列表
     *
     * @param sysCity 城市
     * @return 城市
     */
    @Override
    public List<SysCity> selectSysCityList(SysCity sysCity)
    {
        return sysCityMapper.selectSysCityList(sysCity);
    }

    /**
     * 新增城市
     *
     * @param sysCity 城市
     * @return 结果
     */
    @Override
    public int insertSysCity(SysCity sysCity)
    {
        return sysCityMapper.insertSysCity(sysCity);
    }

    /**
     * 修改城市
     *
     * @param sysCity 城市
     * @return 结果
     */
    @Override
    public int updateSysCity(SysCity sysCity)
    {
        return sysCityMapper.updateSysCity(sysCity);
    }

    /**
     * 批量删除城市
     *
     * @param cids 需要删除的城市ID
     * @return 结果
     */
    @Override
    public int deleteSysCityByIds(Integer[] cids)
    {
        return sysCityMapper.deleteSysCityByIds(cids);
    }

    /**
     * 删除城市信息
     *
     * @param cid 城市ID
     * @return 结果
     */
    @Override
    public int deleteSysCityById(Integer cid)
    {
        return sysCityMapper.deleteSysCityById(cid);
    }
}
