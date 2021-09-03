package com.fjb.system.service.impl;

import java.util.List;

import com.fjb.common.annotation.DataScope;
import com.fjb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fjb.system.mapper.SysConfiscationMapper;
import com.fjb.system.domain.SysConfiscation;
import com.fjb.system.service.ISysConfiscationService;

/**
 * 没收凭证Service业务层处理
 *
 * @author liu
 * @date 2021-08-26
 */
@Service
public class SysConfiscationServiceImpl implements ISysConfiscationService
{
    @Autowired
    private SysConfiscationMapper sysConfiscationMapper;

    /**
     * 查询没收凭证
     *
     * @param id 没收凭证ID
     * @return 没收凭证
     */
    @Override
    public SysConfiscation selectSysConfiscationById(Integer id)
    {
        return sysConfiscationMapper.selectSysConfiscationById(id);
    }

    /**
     * 查询没收凭证列表
     *
     * @param sysConfiscation 没收凭证
     * @return 没收凭证
     */
    @Override
    @DataScope(deptAlias = "r")
    public List<SysConfiscation> selectSysConfiscationList(SysConfiscation sysConfiscation)
    {
        return sysConfiscationMapper.selectSysConfiscationList(sysConfiscation);
    }

    /**
     * 新增没收凭证
     *
     * @param sysConfiscation 没收凭证
     * @return 结果
     */
    @Override
    public int insertSysConfiscation(SysConfiscation sysConfiscation)
    {
        sysConfiscation.setCreateTime(DateUtils.getNowDate());
        return sysConfiscationMapper.insertSysConfiscation(sysConfiscation);
    }

    /**
     * 修改没收凭证
     *
     * @param sysConfiscation 没收凭证
     * @return 结果
     */
    @Override
    public int updateSysConfiscation(SysConfiscation sysConfiscation)
    {
        sysConfiscation.setUpdateTime(DateUtils.getNowDate());
        return sysConfiscationMapper.updateSysConfiscation(sysConfiscation);
    }

    /**
     * 批量删除没收凭证
     *
     * @param ids 需要删除的没收凭证ID
     * @return 结果
     */
    @Override
    public int deleteSysConfiscationByIds(Integer[] ids)
    {
        return sysConfiscationMapper.deleteSysConfiscationByIds(ids);
    }

    /**
     * 删除没收凭证信息
     *
     * @param id 没收凭证ID
     * @return 结果
     */
    @Override
    public int deleteSysConfiscationById(Integer id)
    {
        return sysConfiscationMapper.deleteSysConfiscationById(id);
    }
}
