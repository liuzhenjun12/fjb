package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysGuangaoMapper;
import com.ruoyi.system.domain.SysGuangao;
import com.ruoyi.system.service.ISysGuangaoService;

/**
 * 网站评论Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-13
 */
@Service("guangaoService")
public class SysGuangaoServiceImpl implements ISysGuangaoService
{
    @Autowired
    private SysGuangaoMapper sysGuangaoMapper;

    /**
     * 查询网站评论
     *
     * @param id 网站评论ID
     * @return 网站评论
     */
    @Override
    public SysGuangao selectSysGuangaoById(Long id)
    {
        return sysGuangaoMapper.selectSysGuangaoById(id);
    }

    /**
     * 查询网站评论列表
     *
     * @param sysGuangao 网站评论
     * @return 网站评论
     */
    @Override
    public List<SysGuangao> selectSysGuangaoList(SysGuangao sysGuangao)
    {
        return sysGuangaoMapper.selectSysGuangaoList(sysGuangao);
    }

    /**
     * 新增网站评论
     *
     * @param sysGuangao 网站评论
     * @return 结果
     */
    @Override
    public int insertSysGuangao(SysGuangao sysGuangao)
    {
        sysGuangao.setCreateTime(DateUtils.getNowDate());
        return sysGuangaoMapper.insertSysGuangao(sysGuangao);
    }

    /**
     * 修改网站评论
     *
     * @param sysGuangao 网站评论
     * @return 结果
     */
    @Override
    public int updateSysGuangao(SysGuangao sysGuangao)
    {
        sysGuangao.setUpdateTime(DateUtils.getNowDate());
        return sysGuangaoMapper.updateSysGuangao(sysGuangao);
    }

    /**
     * 批量删除网站评论
     *
     * @param ids 需要删除的网站评论ID
     * @return 结果
     */
    @Override
    public int deleteSysGuangaoByIds(Long[] ids)
    {
        return sysGuangaoMapper.deleteSysGuangaoByIds(ids);
    }

    /**
     * 删除网站评论信息
     *
     * @param id 网站评论ID
     * @return 结果
     */
    @Override
    public int deleteSysGuangaoById(Long id)
    {
        return sysGuangaoMapper.deleteSysGuangaoById(id);
    }

    @Override
    public int checkName(String name) {
        System.out.println(name+"=============sd");
        return sysGuangaoMapper.checkName(name);
    }
}
