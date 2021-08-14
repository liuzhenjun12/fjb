package com.fjb.system.service;

import java.util.List;

import com.fjb.system.domain.SysRemarkBy;
import com.fjb.system.domain.SysRemarkBy;
import org.apache.ibatis.annotations.Param;

/**
 * 评论人员Service接口
 *
 * @author fjb
 * @date 2021-04-19
 */
public interface ISysRemarkByService
{
    /**
     * 查询评论人员
     *
     * @param id 评论人员ID
     * @return 评论人员
     */
    public SysRemarkBy selectSysRemarkByById(Long id);

    /**
     * 查询评论人员列表
     *
     * @param sysRemarkBy 评论人员
     * @return 评论人员集合
     */
    public List<SysRemarkBy> selectSysRemarkByList(SysRemarkBy sysRemarkBy);

    /**
     * 新增评论人员
     *
     * @param sysRemarkBy 评论人员
     * @return 结果
     */
    public int insertSysRemarkBy(SysRemarkBy sysRemarkBy);

    /**
     * 修改评论人员
     *
     * @param sysRemarkBy 评论人员
     * @return 结果
     */
    public int updateSysRemarkBy(SysRemarkBy sysRemarkBy);

    /**
     * 检查评论人员是否唯一
     * @param sysRemarkBy
     * @return
     */
    public String checkPemarkUnique(SysRemarkBy sysRemarkBy);

    /**
     * 检查平台账号是否唯一
     * @param sysRemarkBy
     * @return
     */
    public String checkPtNameUnique(SysRemarkBy sysRemarkBy);

    /**
     * 批量删除评论人员
     *
     * @param ids 需要删除的评论人员ID
     * @return 结果
     */
    public int deleteSysRemarkByByIds(Long[] ids);

    /**
     * 删除评论人员信息
     *
     * @param id 评论人员ID
     * @return 结果
     */
    public int deleteSysRemarkByById(Long id);

    /**
     * 通过平台名称查询
     * @param ptName
     * @return
     */
    public SysRemarkBy selectByPtname(String ptName);
}
