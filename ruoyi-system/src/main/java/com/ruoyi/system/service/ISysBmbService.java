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
     *
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
     * @param ids
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
     * @param bmbList
     * @param updateSupport
     * @param operName
     * @param ancestors
     * @return
     */
    String importBmb(List<SysBmb> bmbList, boolean updateSupport, String operName, String kaoshirq, Long deptId, String pici, String avatarUrl, String ancestors);

    /**
     * 自动录入
     * @param list
     * @return
     */
    String luru(List<SysBmb> list, String operName,Long userid);

    /**
     * 是否完成状态修改
     * @param sysBmb
     * @return
     */
    int updateSfwcStatus(SysBmb sysBmb);

    /**
     * 修改照片
     * @param bmb
     * @param operName
     * @param userId
     * @return
     */
    AjaxResult updateAvatar(SysBmb bmb, String operName, Long userId) throws IOException;

    AjaxResult exportCount(String pici);
}
