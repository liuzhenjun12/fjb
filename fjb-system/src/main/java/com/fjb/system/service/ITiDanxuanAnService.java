package com.fjb.system.service;

import java.util.List;
import com.fjb.system.domain.TiDanxuanAn;

/**
 * 单选答案Service接口
 * 
 * @author liu
 * @date 2021-08-28
 */
public interface ITiDanxuanAnService 
{
    /**
     * 查询单选答案
     * 
     * @param id 单选答案ID
     * @return 单选答案
     */
    public TiDanxuanAn selectTiDanxuanAnById(Long id);

    /**
     * 查询单选答案列表
     * 
     * @param tiDanxuanAn 单选答案
     * @return 单选答案集合
     */
    public List<TiDanxuanAn> selectTiDanxuanAnList(TiDanxuanAn tiDanxuanAn);

    /**
     * 新增单选答案
     * 
     * @param tiDanxuanAn 单选答案
     * @return 结果
     */
    public int insertTiDanxuanAn(TiDanxuanAn tiDanxuanAn);

    /**
     * 修改单选答案
     * 
     * @param tiDanxuanAn 单选答案
     * @return 结果
     */
    public int updateTiDanxuanAn(TiDanxuanAn tiDanxuanAn);

    /**
     * 批量删除单选答案
     * 
     * @param ids 需要删除的单选答案ID
     * @return 结果
     */
    public int deleteTiDanxuanAnByIds(Long[] ids);

    /**
     * 删除单选答案信息
     * 
     * @param id 单选答案ID
     * @return 结果
     */
    public int deleteTiDanxuanAnById(Long id);
}
