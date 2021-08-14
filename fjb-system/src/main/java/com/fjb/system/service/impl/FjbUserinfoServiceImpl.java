package com.fjb.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fjb.system.mapper.FjbUserinfoMapper;
import com.fjb.system.domain.FjbUserinfo;
import com.fjb.system.service.IFjbUserinfoService;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author liu
 * @date 2021-07-30
 */
@Service
public class FjbUserinfoServiceImpl implements IFjbUserinfoService
{
    @Autowired
    private FjbUserinfoMapper fjbUserinfoMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FjbUserinfo selectFjbUserinfoById(Integer id)
    {
        return fjbUserinfoMapper.selectFjbUserinfoById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param fjbUserinfo 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FjbUserinfo> selectFjbUserinfoList(FjbUserinfo fjbUserinfo)
    {
        return fjbUserinfoMapper.selectFjbUserinfoList(fjbUserinfo);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param fjbUserinfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFjbUserinfo(FjbUserinfo fjbUserinfo)
    {
        return fjbUserinfoMapper.insertFjbUserinfo(fjbUserinfo);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param fjbUserinfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFjbUserinfo(FjbUserinfo fjbUserinfo)
    {
        return fjbUserinfoMapper.updateFjbUserinfo(fjbUserinfo);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFjbUserinfoByIds(Integer[] ids)
    {
        return fjbUserinfoMapper.deleteFjbUserinfoByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFjbUserinfoById(Integer id)
    {
        return fjbUserinfoMapper.deleteFjbUserinfoById(id);
    }
}
