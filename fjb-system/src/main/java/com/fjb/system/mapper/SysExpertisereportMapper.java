package com.fjb.system.mapper;

import java.util.List;
import com.fjb.system.domain.SysExpertisereport;

/**
 * 假币鉴定Mapper接口
 *
 * @author liu
 * @date 2021-08-23
 */
public interface SysExpertisereportMapper
{
    /**
     * 查询假币鉴定
     *
     * @param id 假币鉴定ID
     * @return 假币鉴定
     */
    public SysExpertisereport selectSysExpertisereportById(Long id);

    /**
     * 查询假币鉴定列表
     *
     * @param sysExpertisereport 假币鉴定
     * @return 假币鉴定集合
     */
    public List<SysExpertisereport> selectSysExpertisereportList(SysExpertisereport sysExpertisereport);

    /**
     * 新增假币鉴定
     *
     * @param sysExpertisereport 假币鉴定
     * @return 结果
     */
    public int insertSysExpertisereport(SysExpertisereport sysExpertisereport);

    /**
     * 修改假币鉴定
     *
     * @param sysExpertisereport 假币鉴定
     * @return 结果
     */
    public int updateSysExpertisereport(SysExpertisereport sysExpertisereport);

    /**
     * 删除假币鉴定
     *
     * @param id 假币鉴定ID
     * @return 结果
     */
    public int deleteSysExpertisereportById(Long id);

    /**
     * 批量删除假币鉴定
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysExpertisereportByIds(Long[] ids);

    /**
     * 通过机构id查询鉴定编号
     * @param deptId
     * @return
     */
    public String selectNumberByDeptid(Long deptId);

    /**
     * 获取父id
     * @param id
     * @return
     */
    public int getParentNum(Long id);

    /**
     * 查看是否有类似的数据可合并为同一批次
     * @param sysExpertisereport
     * @return
     */
    SysExpertisereport selectParentId(SysExpertisereport sysExpertisereport);
}
