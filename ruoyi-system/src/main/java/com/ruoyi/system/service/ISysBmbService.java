package com.ruoyi.system.service;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysBmb;

/**
 * 报名Service接口
 *
 * @author ruoyi
 * @date 2020-11-29
 */
public interface ISysBmbService
{
    /**
     * 查询报名
     * @param id 报名ID
     * @return 报名
     */
    public SysBmb selectSysBmbById(Long id);

    /**
     * 查询报名列表
     *
     * @param sysBmb 报名
     * @return 报名集合
     */
    public List<SysBmb> selectSysBmbList(SysBmb sysBmb);

    /**
     * 分组查询列表
     * @param pici
     * @return
     */
    public List<SysBmb> selectSysBmbListGroup(String pici);

    /**
     * 新增报名
     *
     * @param sysBmb 报名
     * @return 结果
     */
    public int insertSysBmb(SysBmb sysBmb);

    /**
     * 修改报名
     *
     * @param sysBmb 报名
     * @return 结果
     */
    public int updateSysBmb(SysBmb sysBmb);

    /**
     * 批量删除报名
     *
     * @param ids 需要删除的报名ID
     * @return 结果
     */
    public int deleteSysBmbByIds(Long[] ids);

    /**
     * 通过id数组查询
     * @param ids 报名表id集合
     * @return
     */
    public List<SysBmb>  selectSysBmbByIds(Long[] ids);

    /**
     * 删除报名信息
     *
     * @param id 报名ID
     * @return 结果
     */
    public int deleteSysBmbById(Long id);

    /**
     * 导入报名表
     * @param bmbList 报名表
     * @param updateSupport 是否覆盖
     * @param operName 操作人
     * @param ancestors 祖级列表
     * @return 结果
     */
    String importBmb(List<SysBmb> bmbList, boolean updateSupport, String operName, String kaoshirq, Long deptId, String pici, String avatarUrl, String ancestors,Long shengId);

    /**
     * 自动录入
     * @param list 报名表
     * @return 结果
     */
    AjaxResult luru(List<SysBmb> list, String operName,Long userid);

    /**
     * 是否完成状态修改
     * @param sysBmb 报名表
     * @return 结果
     */
    int updateSfwcStatus(SysBmb sysBmb);

    /**
     * 修改照片
     * @param bmb 报名表
     * @param operName 操作人
     * @param userId 操作人id
     * @return 结果
     */
    AjaxResult updateAvatar(SysBmb bmb, String operName, Long userId) throws IOException;

    /**
     * 导出统计明细
     * @param pici 批次
     * @return 结果
     */
    AjaxResult exportCount(String pici);

    /**从测评中心下载成绩
     *
     * @param pici 批次
     * @param deptId 机构id
     * @return 结果
     */
    AjaxResult downloadChengji(String pici, Long deptId) throws IOException;
}
