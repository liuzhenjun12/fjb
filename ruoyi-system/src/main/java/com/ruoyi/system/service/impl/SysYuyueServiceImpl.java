package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysYuyue;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysYuyueMapper;
import com.ruoyi.system.service.ISysYuyueService;

/**
 * 预约Service业务层处理
 *
 * @author ruoyi
 * @date 2021-01-14
 */
@Service
public class SysYuyueServiceImpl implements ISysYuyueService
{
    @Autowired
    private SysYuyueMapper sysYuyueMapper;

    /**
     * 查询预约
     *
     * @param id 预约ID
     * @return 预约
     */
    @Override
    public SysYuyue selectSysYuyueById(Long id)
    {
        return sysYuyueMapper.selectSysYuyueById(id);
    }

    /**
     * 查询预约列表
     *
     * @param sysYuyue 预约
     * @return 预约
     */
    @Override
    public List<SysYuyue> selectSysYuyueList(SysYuyue sysYuyue)
    {
        return sysYuyueMapper.selectSysYuyueList(sysYuyue);
    }

    /**
     * 新增预约
     *
     * @param sysYuyue 预约
     * @return 结果
     */
    @Override
    public int insertSysYuyue(SysYuyue sysYuyue)
    {
        sysYuyue.setCreateTime(DateUtils.getNowDate());
        return sysYuyueMapper.insertSysYuyue(sysYuyue);
    }

    /**
     * 修改预约
     *
     * @param sysYuyue 预约
     * @return 结果
     */
    @Override
    public int updateSysYuyue(SysYuyue sysYuyue)
    {
        sysYuyue.setUpdateTime(DateUtils.getNowDate());
        return sysYuyueMapper.updateSysYuyue(sysYuyue);
    }

    /**
     * 批量删除预约
     *
     * @param ids 需要删除的预约ID
     * @return 结果
     */
    @Override
    public int deleteSysYuyueByIds(Long[] ids)
    {
        return sysYuyueMapper.deleteSysYuyueByIds(ids);
    }

    /**
     * 删除预约信息
     *
     * @param id 预约ID
     * @return 结果
     */
    @Override
    public int deleteSysYuyueById(Long id)
    {
        return sysYuyueMapper.deleteSysYuyueById(id);
    }
}
