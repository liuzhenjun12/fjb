package com.fjb.system.service.impl;

import java.util.List;

import com.fjb.system.domain.SysRemarkBy;
import com.fjb.system.domain.SysRemarkNum;
import com.fjb.system.mapper.SysRemarkByMapper;
import com.fjb.system.mapper.SysRemarkNumMapper;
import com.fjb.system.service.ISysRemarkNumService;
import com.fjb.common.core.domain.AjaxResult;
import com.fjb.common.utils.DateUtils;
import com.fjb.common.utils.SecurityUtils;
import com.fjb.system.domain.SysRemarkBy;
import com.fjb.system.mapper.SysRemarkByMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fjb.system.mapper.SysRemarkNumMapper;
import com.fjb.system.domain.SysRemarkNum;
import com.fjb.system.service.ISysRemarkNumService;

/**
 * 评论数量Service业务层处理
 *
 * @author fjb
 * @date 2021-04-20
 */
@Service
public class SysRemarkNumServiceImpl implements ISysRemarkNumService
{
    @Autowired
    private SysRemarkNumMapper sysRemarkNumMapper;
    @Autowired
    private SysRemarkByMapper sysRemarkByMapper;

    /**
     * 查询评论数量
     *
     * @param id 评论数量ID
     * @return 评论数量
     */
    @Override
    public SysRemarkNum selectSysRemarkNumById(Long id)
    {
        return sysRemarkNumMapper.selectSysRemarkNumById(id);
    }

    /**
     * 查询评论数量列表
     *
     * @param sysRemarkNum 评论数量
     * @return 评论数量
     */
    @Override
    public List<SysRemarkNum> selectSysRemarkNumList(SysRemarkNum sysRemarkNum)
    {
        return sysRemarkNumMapper.selectSysRemarkNumList(sysRemarkNum);
    }

    /**
     * 新增评论数量
     *
     * @param sysRemarkNum 评论数量
     * @return 结果
     */
    @Override
    public AjaxResult insertSysRemarkNum(SysRemarkNum sysRemarkNum)
    {
       SysRemarkBy by=sysRemarkByMapper.selectByUserid(sysRemarkNum.getUserId(),sysRemarkNum.getType());
       if(by==null){
           return AjaxResult.error("评论人员不存在");
       }
       String name=SecurityUtils.getUsername();
       by.setUpdateBy(name);
       by.setUpdateTime(DateUtils.getNowDate());
       by.setContentNum(by.getContentNum()+sysRemarkNum.getNum());
       sysRemarkByMapper.updateSysRemarkBy(by);
       sysRemarkNum.setCreateBy(name);
        sysRemarkNum.setCreateTime(DateUtils.getNowDate());
        sysRemarkNumMapper.insertSysRemarkNum(sysRemarkNum);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult zhinsert(SysRemarkNum sysRemarkNum) {
        sysRemarkNumMapper.insertSysRemarkNum(sysRemarkNum);
        return AjaxResult.success();
    }

    /**
     * 修改评论数量
     *
     * @param sysRemarkNum 评论数量
     * @return 结果
     */
    @Override
    public AjaxResult updateSysRemarkNum(SysRemarkNum sysRemarkNum)
    {
        SysRemarkBy by=sysRemarkByMapper.selectByUserid(sysRemarkNum.getUserId(),sysRemarkNum.getType());
        if(by==null){
            return AjaxResult.error("评论人员不存在");
        }
        SysRemarkNum bian=sysRemarkNumMapper.selectSysRemarkNumById(sysRemarkNum.getId());
        if(bian==null){
            return AjaxResult.error("评论数量不存在");
        }
        String name=SecurityUtils.getUsername();
        by.setUpdateTime(DateUtils.getNowDate());
        by.setUpdateBy(name);
        by.setContentNum(by.getContentNum()-bian.getNum()+sysRemarkNum.getNum());
        sysRemarkByMapper.updateSysRemarkBy(by);
        sysRemarkNum.setUpdateBy(name);
        sysRemarkNum.setUpdateTime(DateUtils.getNowDate());
        sysRemarkNumMapper.updateSysRemarkNum(sysRemarkNum);
        return AjaxResult.success();
    }

    /**
     * 批量删除评论数量
     *
     * @param ids 需要删除的评论数量ID
     * @return 结果
     */
    @Override
    public AjaxResult deleteSysRemarkNumByIds(Long[] ids)
    {
        for(Long I:ids){
            SysRemarkNum bian=sysRemarkNumMapper.selectSysRemarkNumById(I);
            if(bian==null){
                return AjaxResult.error("评论数量不存在");
            }
            SysRemarkBy by=sysRemarkByMapper.selectByUserid(bian.getUserId(),bian.getType());
            if(by==null){
                return AjaxResult.error("评论人员不存在");
            }
            by.setUpdateBy(SecurityUtils.getUsername());
            by.setUpdateTime(DateUtils.getNowDate());
            by.setContentNum(by.getContentNum()-bian.getNum());
            sysRemarkByMapper.updateSysRemarkBy(by);
        }
        int i=sysRemarkNumMapper.deleteSysRemarkNumByIds(ids);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 删除评论数量信息
     *
     * @param id 评论数量ID
     * @return 结果
     */
    @Override
    public int deleteSysRemarkNumById(Long id)
    {
        return sysRemarkNumMapper.deleteSysRemarkNumById(id);
    }
}
