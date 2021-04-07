package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysYuyue;

import java.util.List;

/**
 * 预约Mapper接口
 *
 * @author ruoyi
 * @date 2021-01-14
 */
public interface SysYuyueMapper
{
    /**
     * 查询预约
     *
     * @param id 预约ID
     * @return 预约
     */
    public SysYuyue selectSysYuyueById(Long id);

    /**
     * 查询预约列表
     *
     * @param sysYuyue 预约
     * @return 预约集合
     */
    public List<SysYuyue> selectSysYuyueList(SysYuyue sysYuyue);

    /**
     * 新增预约
     *
     * @param sysYuyue 预约
     * @return 结果
     */
    public int insertSysYuyue(SysYuyue sysYuyue);

    /**
     * 修改预约
     *
     * @param sysYuyue 预约
     * @return 结果
     */
    public int updateSysYuyue(SysYuyue sysYuyue);

    /**
     * 删除预约
     *
     * @param id 预约ID
     * @return 结果
     */
    public int deleteSysYuyueById(Long id);

    /**
     * 批量删除预约
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysYuyueByIds(Long[] ids);
}
