package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysGuangao;

/**
 * 网站评论Service接口
 *
 * @author ruoyi
 * @date 2021-04-13
 */
public interface ISysGuangaoService
{
    /**
     * 查询网站评论
     *
     * @param id 网站评论ID
     * @return 网站评论
     */
    public SysGuangao selectSysGuangaoById(Long id);

    /**
     * 查询网站评论列表
     *
     * @param sysGuangao 网站评论
     * @return 网站评论集合
     */
    public List<SysGuangao> selectSysGuangaoList(SysGuangao sysGuangao);

    /**
     * 新增网站评论
     *
     * @param sysGuangao 网站评论
     * @return 结果
     */
    public int insertSysGuangao(SysGuangao sysGuangao);

    /**
     * 修改网站评论
     *
     * @param sysGuangao 网站评论
     * @return 结果
     */
    public int updateSysGuangao(SysGuangao sysGuangao);

    /**
     * 批量删除网站评论
     *
     * @param ids 需要删除的网站评论ID
     * @return 结果
     */
    public int deleteSysGuangaoByIds(Long[] ids);

    /**
     * 删除网站评论信息
     *
     * @param id 网站评论ID
     * @return 结果
     */
    public int deleteSysGuangaoById(Long id);

    public int checkName(String name);
}
