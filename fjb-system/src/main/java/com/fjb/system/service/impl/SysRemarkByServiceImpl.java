package com.fjb.system.service.impl;

import java.util.List;

import com.fjb.system.domain.SysRemarkBy;
import com.fjb.system.mapper.SysRemarkByMapper;
import com.fjb.system.service.ISysRemarkByService;
import com.fjb.common.constant.UserConstants;
import com.fjb.common.utils.DateUtils;
import com.fjb.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fjb.system.mapper.SysRemarkByMapper;
import com.fjb.system.domain.SysRemarkBy;
import com.fjb.system.service.ISysRemarkByService;

/**
 * 评论人员Service业务层处理
 *
 * @author fjb
 * @date 2021-04-19
 */
@Service
public class SysRemarkByServiceImpl implements ISysRemarkByService
{
    @Autowired
    private SysRemarkByMapper sysRemarkByMapper;

    /**
     * 查询评论人员
     *
     * @param id 评论人员ID
     * @return 评论人员
     */
    @Override
    public SysRemarkBy selectSysRemarkByById(Long id)
    {
        return sysRemarkByMapper.selectSysRemarkByById(id);
    }

    /**
     * 查询评论人员列表
     *
     * @param sysRemarkBy 评论人员
     * @return 评论人员
     */
    @Override
    public List<SysRemarkBy> selectSysRemarkByList(SysRemarkBy sysRemarkBy)
    {
        return sysRemarkByMapper.selectSysRemarkByList(sysRemarkBy);
    }

    /**
     * 新增评论人员
     *
     * @param sysRemarkBy 评论人员
     * @return 结果
     */
    @Override
    public int insertSysRemarkBy(SysRemarkBy sysRemarkBy)
    {
        return sysRemarkByMapper.insertSysRemarkBy(sysRemarkBy);
    }

    /**
     * 修改评论人员
     *
     * @param sysRemarkBy 评论人员
     * @return 结果
     */
    @Override
    public int updateSysRemarkBy(SysRemarkBy sysRemarkBy)
    {
        return sysRemarkByMapper.updateSysRemarkBy(sysRemarkBy);
    }

    @Override
    public String checkPemarkUnique(SysRemarkBy sysRemarkBy) {
        int count = sysRemarkByMapper.checkPemarkUnique(sysRemarkBy.getUserId(),sysRemarkBy.getPtType());
        if (count > 0)
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkPtNameUnique(SysRemarkBy sysRemarkBy) {
        Long Id = StringUtils.isNull(sysRemarkBy.getId()) ? -1L : sysRemarkBy.getId();
        SysRemarkBy info = sysRemarkByMapper.checkPtNameUnique(sysRemarkBy.getPtName());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != Id.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 批量删除评论人员
     *
     * @param ids 需要删除的评论人员ID
     * @return 结果
     */
    @Override
    public int deleteSysRemarkByByIds(Long[] ids)
    {
        return sysRemarkByMapper.deleteSysRemarkByByIds(ids);
    }

    /**
     * 删除评论人员信息
     *
     * @param id 评论人员ID
     * @return 结果
     */
    @Override
    public int deleteSysRemarkByById(Long id)
    {
        return sysRemarkByMapper.deleteSysRemarkByById(id);
    }

    @Override
    public SysRemarkBy selectByPtname(String ptName) {
        return sysRemarkByMapper.selectByPtname(ptName);
    }
}
