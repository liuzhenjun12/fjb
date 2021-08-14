package com.fjb.system.service;

import java.util.List;
import com.fjb.system.domain.FjbUserinfo;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author liu
 * @date 2021-07-30
 */
public interface IFjbUserinfoService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FjbUserinfo selectFjbUserinfoById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param fjbUserinfo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FjbUserinfo> selectFjbUserinfoList(FjbUserinfo fjbUserinfo);

    /**
     * 新增【请填写功能名称】
     * 
     * @param fjbUserinfo 【请填写功能名称】
     * @return 结果
     */
    public int insertFjbUserinfo(FjbUserinfo fjbUserinfo);

    /**
     * 修改【请填写功能名称】
     * 
     * @param fjbUserinfo 【请填写功能名称】
     * @return 结果
     */
    public int updateFjbUserinfo(FjbUserinfo fjbUserinfo);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFjbUserinfoByIds(Integer[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFjbUserinfoById(Integer id);
}
