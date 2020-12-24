package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysZcb;

/**
 * 注册Mapper接口
 * 
 * @author ruoyi
 * @date 2020-11-30
 */
public interface SysZcbMapper 
{
    /**
     * 查询注册
     * 
     * @param id 注册ID
     * @return 注册
     */
    public SysZcb selectSysZcbById(Long id);

    /**
     * 查询注册列表
     * 
     * @param sysZcb 注册
     * @return 注册集合
     */
    public List<SysZcb> selectSysZcbList(SysZcb sysZcb);

    /**
     * 新增注册
     * 
     * @param sysZcb 注册
     * @return 结果
     */
    public int insertSysZcb(SysZcb sysZcb);

    /**
     * 修改注册
     * 
     * @param sysZcb 注册
     * @return 结果
     */
    public int updateSysZcb(SysZcb sysZcb);

    /**
     * 删除注册
     * 
     * @param id 注册ID
     * @return 结果
     */
    public int deleteSysZcbById(Long id);

    /**
     * 批量删除注册
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysZcbByIds(Long[] ids);
}
