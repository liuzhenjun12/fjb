package com.fjb.system.service;

import java.util.List;
import com.fjb.system.domain.SysConfiscation;

/**
 * 没收凭证Service接口
 * 
 * @author liu
 * @date 2021-08-26
 */
public interface ISysConfiscationService 
{
    /**
     * 查询没收凭证
     * 
     * @param id 没收凭证ID
     * @return 没收凭证
     */
    public SysConfiscation selectSysConfiscationById(Integer id);

    /**
     * 查询没收凭证列表
     * 
     * @param sysConfiscation 没收凭证
     * @return 没收凭证集合
     */
    public List<SysConfiscation> selectSysConfiscationList(SysConfiscation sysConfiscation);

    /**
     * 新增没收凭证
     * 
     * @param sysConfiscation 没收凭证
     * @return 结果
     */
    public int insertSysConfiscation(SysConfiscation sysConfiscation);

    /**
     * 修改没收凭证
     * 
     * @param sysConfiscation 没收凭证
     * @return 结果
     */
    public int updateSysConfiscation(SysConfiscation sysConfiscation);

    /**
     * 批量删除没收凭证
     * 
     * @param ids 需要删除的没收凭证ID
     * @return 结果
     */
    public int deleteSysConfiscationByIds(Integer[] ids);

    /**
     * 删除没收凭证信息
     * 
     * @param id 没收凭证ID
     * @return 结果
     */
    public int deleteSysConfiscationById(Integer id);
}
