package com.fjb.system.mapper;

import java.util.List;

import com.fjb.system.domain.SysRemarkNum;
import com.fjb.system.domain.SysRemarkNum;

/**
 * 评论数量Mapper接口
 *
 * @author fjb
 * @date 2021-04-20
 */
public interface SysRemarkNumMapper
{
    /**
     * 查询评论数量
     *
     * @param id 评论数量ID
     * @return 评论数量
     */
    public SysRemarkNum selectSysRemarkNumById(Long id);

    /**
     * 查询评论数量列表
     *
     * @param sysRemarkNum 评论数量
     * @return 评论数量集合
     */
    public List<SysRemarkNum> selectSysRemarkNumList(SysRemarkNum sysRemarkNum);

    /**
     * 新增评论数量
     *
     * @param sysRemarkNum 评论数量
     * @return 结果
     */
    public int insertSysRemarkNum(SysRemarkNum sysRemarkNum);

    /**
     * 修改评论数量
     *
     * @param sysRemarkNum 评论数量
     * @return 结果
     */
    public int updateSysRemarkNum(SysRemarkNum sysRemarkNum);

    /**
     * 删除评论数量
     *
     * @param id 评论数量ID
     * @return 结果
     */
    public int deleteSysRemarkNumById(Long id);

    /**
     * 批量删除评论数量
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysRemarkNumByIds(Long[] ids);
}
