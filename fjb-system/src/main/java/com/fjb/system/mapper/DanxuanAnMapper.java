package com.fjb.system.mapper;

import java.util.List;
import com.fjb.system.domain.DanxuanAn;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author liu
 * @date 2021-08-30
 */
public interface DanxuanAnMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public DanxuanAn selectDanxuanAnById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param danxuanAn 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<DanxuanAn> selectDanxuanAnList(DanxuanAn danxuanAn);

    /**
     * 新增【请填写功能名称】
     * 
     * @param danxuanAn 【请填写功能名称】
     * @return 结果
     */
    public int insertDanxuanAn(DanxuanAn danxuanAn);

    /**
     * 修改【请填写功能名称】
     * 
     * @param danxuanAn 【请填写功能名称】
     * @return 结果
     */
    public int updateDanxuanAn(DanxuanAn danxuanAn);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteDanxuanAnById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDanxuanAnByIds(Long[] ids);
}
