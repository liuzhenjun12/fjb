package com.fjb.system.service.impl;

import java.util.List;

import com.fjb.system.domain.TiDanxuanAn;
import com.fjb.system.mapper.TiDanxuanAnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fjb.system.mapper.TiDanxuanMapper;
import com.fjb.system.domain.TiDanxuan;
import com.fjb.system.service.ITiDanxuanService;

/**
 * 单选Service业务层处理
 *
 * @author liu
 * @date 2021-08-28
 */
@Service
public class TiDanxuanServiceImpl implements ITiDanxuanService
{
    @Autowired
    private TiDanxuanMapper tiDanxuanMapper;
    @Autowired
    private TiDanxuanAnMapper anMapper;

    /**
     * 查询单选
     *
     * @param id 单选ID
     * @return 单选
     */
    @Override
    public TiDanxuan selectTiDanxuanById(Long id)
    {
        return tiDanxuanMapper.selectTiDanxuanById(id);
    }

    /**
     * 查询单选列表
     *
     * @param tiDanxuan 单选
     * @return 单选
     */
    @Override
    public List<TiDanxuan> selectTiDanxuanList(TiDanxuan tiDanxuan)
    {
        return tiDanxuanMapper.selectTiDanxuanList(tiDanxuan);
    }

    /**
     * 新增单选
     *
     * @param tiDanxuan 单选
     * @return 结果
     */
    @Override
    public int insertTiDanxuan(TiDanxuan tiDanxuan)
    {
        return tiDanxuanMapper.insertTiDanxuan(tiDanxuan);
    }

    /**
     * 修改单选
     *
     * @param tiDanxuan 单选
     * @return 结果
     */
    @Override
    public int updateTiDanxuan(TiDanxuan tiDanxuan)
    {
        return tiDanxuanMapper.updateTiDanxuan(tiDanxuan);
    }

    /**
     * 批量删除单选
     *
     * @param ids 需要删除的单选ID
     * @return 结果
     */
    @Override
    public int deleteTiDanxuanByIds(Long[] ids)
    {
        return tiDanxuanMapper.deleteTiDanxuanByIds(ids);
    }

    /**
     * 删除单选信息
     *
     * @param id 单选ID
     * @return 结果
     */
    @Override
    public int deleteTiDanxuanById(Long id)
    {
        TiDanxuanAn an=new TiDanxuanAn();
        an.setQid(id);
        List<TiDanxuanAn> list=anMapper.selectTiDanxuanAnList(an);
        if(!list.isEmpty()){
            for(TiDanxuanAn A:list){
                anMapper.deleteTiDanxuanAnById(A.getId());
            }
        }
        return tiDanxuanMapper.deleteTiDanxuanById(id);
    }

    @Override
    public int updateUserStatus(TiDanxuan tiDanxuan) {
        return tiDanxuanMapper.updateTiDanxuan(tiDanxuan);
    }
}
