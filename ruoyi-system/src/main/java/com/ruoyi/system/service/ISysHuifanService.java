package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysHuifan;

/**
 * 回访记录Service接口
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
public interface ISysHuifanService 
{
    /**
     * 查询回访记录
     * 
     * @param id 回访记录ID
     * @return 回访记录
     */
    public SysHuifan selectSysHuifanById(Long id);

    /**
     * 查询回访记录列表
     * 
     * @param sysHuifan 回访记录
     * @return 回访记录集合
     */
    public List<SysHuifan> selectSysHuifanList(SysHuifan sysHuifan);

    /**
     * 新增回访记录
     * 
     * @param sysHuifan 回访记录
     * @return 结果
     */
    public int insertSysHuifan(SysHuifan sysHuifan);

    /**
     * 修改回访记录
     * 
     * @param sysHuifan 回访记录
     * @return 结果
     */
    public int updateSysHuifan(SysHuifan sysHuifan);

    /**
     * 批量删除回访记录
     * 
     * @param ids 需要删除的回访记录ID
     * @return 结果
     */
    public int deleteSysHuifanByIds(Long[] ids);

    /**
     * 删除回访记录信息
     * 
     * @param id 回访记录ID
     * @return 结果
     */
    public int deleteSysHuifanById(Long id);
}
