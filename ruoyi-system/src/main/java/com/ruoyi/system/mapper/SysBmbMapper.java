package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysBmb;

/**
 * 报名Mapper接口
 *
 * @author ruoyi
 * @date 2020-11-29
 */
public interface SysBmbMapper
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
     * 删除报名
     *
     * @param id 报名ID
     * @return 结果
     */
    public int deleteSysBmbById(Long id);

    /**
     * 批量删除报名
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysBmbByIds(Long[] ids);

    /**
     * 批量查询
     * @param ids
     * @return
     */
    public List<SysBmb> selectSysBmbByIds(Long[] ids);
}
