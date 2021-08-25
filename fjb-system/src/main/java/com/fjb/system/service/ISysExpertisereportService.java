package com.fjb.system.service;

import java.util.List;
import com.fjb.system.domain.SysExpertisereport;

/**
 * 假币鉴定Service接口
 * 
 * @author liu
 * @date 2021-08-23
 */
public interface ISysExpertisereportService 
{
    /**
     * 查询假币鉴定
     * 
     * @param id 假币鉴定ID
     * @return 假币鉴定
     */
    public SysExpertisereport selectSysExpertisereportById(Integer id);

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
     * 批量删除假币鉴定
     * 
     * @param ids 需要删除的假币鉴定ID
     * @return 结果
     */
    public int deleteSysExpertisereportByIds(Integer[] ids);

    /**
     * 删除假币鉴定信息
     * 
     * @param id 假币鉴定ID
     * @return 结果
     */
    public int deleteSysExpertisereportById(Integer id);
}
