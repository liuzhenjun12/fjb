package com.fjb.system.service;

import java.util.List;
import com.fjb.system.domain.TiDanxuan;

/**
 * 单选Service接口
 *
 * @author liu
 * @date 2021-08-28
 */
public interface ITiDanxuanService
{
    /**
     * 查询单选
     *
     * @param id 单选ID
     * @return 单选
     */
    public TiDanxuan selectTiDanxuanById(Long id);

    /**
     * 查询单选列表
     *
     * @param tiDanxuan 单选
     * @return 单选集合
     */
    public List<TiDanxuan> selectTiDanxuanList(TiDanxuan tiDanxuan);

    /**
     * 新增单选
     *
     * @param tiDanxuan 单选
     * @return 结果
     */
    public int insertTiDanxuan(TiDanxuan tiDanxuan);

    /**
     * 修改单选
     *
     * @param tiDanxuan 单选
     * @return 结果
     */
    public int updateTiDanxuan(TiDanxuan tiDanxuan);

    /**
     * 批量删除单选
     *
     * @param ids 需要删除的单选ID
     * @return 结果
     */
    public int deleteTiDanxuanByIds(Long[] ids);

    /**
     * 删除单选信息
     *
     * @param id 单选ID
     * @return 结果
     */
    public int deleteTiDanxuanById(Long id);

    int updateUserStatus(TiDanxuan tiDanxuan);
}
