package com.fjb.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fjb.system.mapper.TiDanxuanAnMapper;
import com.fjb.system.domain.TiDanxuanAn;
import com.fjb.system.service.ITiDanxuanAnService;

/**
 * 单选答案Service业务层处理
 *
 * @author liu
 * @date 2021-08-28
 */
@Service
public class TiDanxuanAnServiceImpl implements ITiDanxuanAnService
{
    @Autowired
    private TiDanxuanAnMapper tiDanxuanAnMapper;

    /**
     * 查询单选答案
     *
     * @param id 单选答案ID
     * @return 单选答案
     */
    @Override
    public TiDanxuanAn selectTiDanxuanAnById(Long id)
    {
        return tiDanxuanAnMapper.selectTiDanxuanAnById(id);
    }

    /**
     * 查询单选答案列表
     *
     * @param tiDanxuanAn 单选答案
     * @return 单选答案
     */
    @Override
    public List<TiDanxuanAn> selectTiDanxuanAnList(TiDanxuanAn tiDanxuanAn)
    {
        return tiDanxuanAnMapper.selectTiDanxuanAnList(tiDanxuanAn);
    }

    /**
     * 新增单选答案
     *
     * @param tiDanxuanAn 单选答案
     * @return 结果
     */
    @Override
    public int insertTiDanxuanAn(TiDanxuanAn tiDanxuanAn)
    {
        return tiDanxuanAnMapper.insertTiDanxuanAn(tiDanxuanAn);
    }

    /**
     * 修改单选答案
     *
     * @param tiDanxuanAn 单选答案
     * @return 结果
     */
    @Override
    public int updateTiDanxuanAn(TiDanxuanAn tiDanxuanAn)
    {
        return tiDanxuanAnMapper.updateTiDanxuanAn(tiDanxuanAn);
    }

    /**
     * 批量删除单选答案
     *
     * @param ids 需要删除的单选答案ID
     * @return 结果
     */
    @Override
    public int deleteTiDanxuanAnByIds(Long[] ids)
    {
        return tiDanxuanAnMapper.deleteTiDanxuanAnByIds(ids);
    }

    /**
     * 删除单选答案信息
     *
     * @param id 单选答案ID
     * @return 结果
     */
    @Override
    public int deleteTiDanxuanAnById(Long id)
    {
        return tiDanxuanAnMapper.deleteTiDanxuanAnById(id);
    }
}
