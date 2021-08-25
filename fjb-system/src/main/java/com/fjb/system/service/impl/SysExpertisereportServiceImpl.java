package com.fjb.system.service.impl;

import java.util.List;

import com.fjb.common.annotation.DataScope;
import com.fjb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fjb.system.mapper.SysExpertisereportMapper;
import com.fjb.system.domain.SysExpertisereport;
import com.fjb.system.service.ISysExpertisereportService;

/**
 * 假币鉴定Service业务层处理
 *
 * @author liu
 * @date 2021-08-23
 */
@Service
public class SysExpertisereportServiceImpl implements ISysExpertisereportService
{
    @Autowired
    private SysExpertisereportMapper sysExpertisereportMapper;

    /**
     * 查询假币鉴定
     *
     * @param id 假币鉴定ID
     * @return 假币鉴定
     */
    @Override
    public SysExpertisereport selectSysExpertisereportById(Integer id)
    {
        return sysExpertisereportMapper.selectSysExpertisereportById(id);
    }

    /**
     * 查询假币鉴定列表
     *
     * @param sysExpertisereport 假币鉴定
     * @return 假币鉴定
     */
    @Override
    @DataScope(deptAlias = "r")
    public List<SysExpertisereport> selectSysExpertisereportList(SysExpertisereport sysExpertisereport)
    {
        return sysExpertisereportMapper.selectSysExpertisereportList(sysExpertisereport);
    }

    /**
     * 新增假币鉴定
     *
     * @param sysExpertisereport 假币鉴定
     * @return 结果
     */
    @Override
    public int insertSysExpertisereport(SysExpertisereport sysExpertisereport)
    {
        sysExpertisereport.setCreateTime(DateUtils.getNowDate());
        return sysExpertisereportMapper.insertSysExpertisereport(sysExpertisereport);
    }

    /**
     * 修改假币鉴定
     *
     * @param sysExpertisereport 假币鉴定
     * @return 结果
     */
    @Override
    public int updateSysExpertisereport(SysExpertisereport sysExpertisereport)
    {
        sysExpertisereport.setUpdateTime(DateUtils.getNowDate());
        return sysExpertisereportMapper.updateSysExpertisereport(sysExpertisereport);
    }

    /**
     * 批量删除假币鉴定
     *
     * @param ids 需要删除的假币鉴定ID
     * @return 结果
     */
    @Override
    public int deleteSysExpertisereportByIds(Integer[] ids)
    {
        return sysExpertisereportMapper.deleteSysExpertisereportByIds(ids);
    }

    /**
     * 删除假币鉴定信息
     *
     * @param id 假币鉴定ID
     * @return 结果
     */
    @Override
    public int deleteSysExpertisereportById(Integer id)
    {
        return sysExpertisereportMapper.deleteSysExpertisereportById(id);
    }
}
