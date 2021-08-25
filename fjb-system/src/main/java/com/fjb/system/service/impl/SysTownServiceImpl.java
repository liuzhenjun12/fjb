package com.fjb.system.service.impl;

import java.util.List;

import com.fjb.common.core.domain.entity.SysTown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fjb.system.mapper.SysTownMapper;
import com.fjb.system.service.ISysTownService;

/**
 * 县区Service业务层处理
 *
 * @author liu
 * @date 2021-08-16
 */
@Service
public class SysTownServiceImpl implements ISysTownService
{
    @Autowired
    private SysTownMapper sysTownMapper;

    /**
     * 查询县区
     *
     * @param tid 县区ID
     * @return 县区
     */
    @Override
    public SysTown selectSysTownById(Integer tid)
    {
        return sysTownMapper.selectSysTownById(tid);
    }

    /**
     * 查询县区列表
     *
     * @param sysTown 县区
     * @return 县区
     */
    @Override
    public List<SysTown> selectSysTownList(SysTown sysTown)
    {
        return sysTownMapper.selectSysTownList(sysTown);
    }

    /**
     * 新增县区
     *
     * @param sysTown 县区
     * @return 结果
     */
    @Override
    public int insertSysTown(SysTown sysTown)
    {
        return sysTownMapper.insertSysTown(sysTown);
    }

    /**
     * 修改县区
     *
     * @param sysTown 县区
     * @return 结果
     */
    @Override
    public int updateSysTown(SysTown sysTown)
    {
        return sysTownMapper.updateSysTown(sysTown);
    }

    /**
     * 批量删除县区
     *
     * @param tids 需要删除的县区ID
     * @return 结果
     */
    @Override
    public int deleteSysTownByIds(Integer[] tids)
    {
        return sysTownMapper.deleteSysTownByIds(tids);
    }

    /**
     * 删除县区信息
     *
     * @param tid 县区ID
     * @return 结果
     */
    @Override
    public int deleteSysTownById(Integer tid)
    {
        return sysTownMapper.deleteSysTownById(tid);
    }
}
