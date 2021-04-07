package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysZhuce;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2020-12-28
 */
public interface SysZhuceMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param zcId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public SysZhuce selectSysZhuceById(Long zcId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param sysZhuce 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysZhuce> selectSysZhuceList(SysZhuce sysZhuce);

    /**
     * 新增【请填写功能名称】
     *
     * @param sysZhuce 【请填写功能名称】
     * @return 结果
     */
    public int insertSysZhuce(SysZhuce sysZhuce);

    /**
     * 修改【请填写功能名称】
     *
     * @param sysZhuce 【请填写功能名称】
     * @return 结果
     */
    public int updateSysZhuce(SysZhuce sysZhuce);

    /**
     * 删除【请填写功能名称】
     *
     * @param zcId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteSysZhuceById(Long zcId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param zcIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysZhuceByIds(Long[] zcIds);

    /**
     * 批量查询【请填写功能名称】列表
     *
     * @return 【请填写功能名称】集合
     */
    public List<SysZhuce> selectSysZhuceByIds(Long[] zcIds);

    /**
     * 修改审核状态
     * @param status
     * @param attr
     * @param pici
     * @param deptId
     * @return
     */
    public int updateStatus(@Param("status") String status,@Param("attr") Long[] attr,@Param("pici") String pici,@Param("deptId") Long deptId);
}
