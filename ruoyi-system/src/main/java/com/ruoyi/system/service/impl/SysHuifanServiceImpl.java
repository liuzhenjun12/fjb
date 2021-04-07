package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysHuifanMapper;
import com.ruoyi.system.domain.SysHuifan;
import com.ruoyi.system.service.ISysHuifanService;

/**
 * 回访记录Service业务层处理
 *
 * @author ruoyi
 * @date 2021-03-30
 */
@Service
public class SysHuifanServiceImpl implements ISysHuifanService
{
    @Autowired
    private SysHuifanMapper sysHuifanMapper;

    /**
     * 查询回访记录
     *
     * @param id 回访记录ID
     * @return 回访记录
     */
    @Override
    public SysHuifan selectSysHuifanById(Long id)
    {
        return sysHuifanMapper.selectSysHuifanById(id);
    }

    /**
     * 查询回访记录列表
     *
     * @param sysHuifan 回访记录
     * @return 回访记录
     */
    @Override
    public List<SysHuifan> selectSysHuifanList(SysHuifan sysHuifan)
    {
        return sysHuifanMapper.selectSysHuifanList(sysHuifan);
    }

    /**
     * 新增回访记录
     *
     * @param sysHuifan 回访记录
     * @return 结果
     */
    @Override
    public int insertSysHuifan(SysHuifan sysHuifan)
    {
        sysHuifan.setCreateBy(SecurityUtils.getUsername());
        sysHuifan.setCreateTime(DateUtils.getNowDate());
        return sysHuifanMapper.insertSysHuifan(sysHuifan);
    }

    /**
     * 修改回访记录
     *
     * @param sysHuifan 回访记录
     * @return 结果
     */
    @Override
    public int updateSysHuifan(SysHuifan sysHuifan)
    {
        sysHuifan.setUpdateBy(SecurityUtils.getUsername());
        sysHuifan.setUpdateTime(DateUtils.getNowDate());
        return sysHuifanMapper.updateSysHuifan(sysHuifan);
    }

    /**
     * 批量删除回访记录
     *
     * @param ids 需要删除的回访记录ID
     * @return 结果
     */
    @Override
    public int deleteSysHuifanByIds(Long[] ids)
    {
        return sysHuifanMapper.deleteSysHuifanByIds(ids);
    }

    /**
     * 删除回访记录信息
     *
     * @param id 回访记录ID
     * @return 结果
     */
    @Override
    public int deleteSysHuifanById(Long id)
    {
        return sysHuifanMapper.deleteSysHuifanById(id);
    }
}
