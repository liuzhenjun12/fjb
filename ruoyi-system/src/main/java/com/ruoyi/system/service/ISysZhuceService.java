package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysZhuce;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2020-12-28
 */
public interface ISysZhuceService
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
     * 批量删除【请填写功能名称】
     *
     * @param zcIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteSysZhuceByIds(Long[] zcIds);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param zcId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteSysZhuceById(Long zcId);

    /**
     * 导入注册表
     * @param sysZhuce
     * @param operName
     * @param userId
     * @return
     */
    public AjaxResult importZc(SysZhuce sysZhuce, String operName, Long userId) throws IOException;

    /**
     * 审核注册表
     * @param is
     * @param operName
     * @return
     */
    public AjaxResult appver(String ids, boolean is, String operName,Long deptId,String exmaId,String pici) throws ParseException, IOException;

    /**
     * 核对
     * @param pici
     * @param operName
     * @param deptId
     * @return
     */
    AjaxResult hedui(String pici, String operName, Long deptId);
}
