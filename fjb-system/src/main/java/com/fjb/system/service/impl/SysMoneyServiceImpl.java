package com.fjb.system.service.impl;

import java.util.List;
import com.fjb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fjb.system.mapper.SysMoneyMapper;
import com.fjb.system.domain.SysMoney;
import com.fjb.system.service.ISysMoneyService;

/**
 * 货币标准Service业务层处理
 *
 * @author liu
 * @date 2021-08-27
 */
@Service
public class SysMoneyServiceImpl implements ISysMoneyService
{
    @Autowired
    private SysMoneyMapper sysMoneyMapper;

    /**
     * 查询货币标准
     *
     * @param id 货币标准ID
     * @return 货币标准
     */
    @Override
    public SysMoney selectSysMoneyById(Integer id)
    {
        return sysMoneyMapper.selectSysMoneyById(id);
    }

    /**
     * 查询货币标准列表
     *
     * @param sysMoney 货币标准
     * @return 货币标准
     */
    @Override
    public List<SysMoney> selectSysMoneyList(SysMoney sysMoney)
    {
        return sysMoneyMapper.selectSysMoneyList(sysMoney);
    }

    /**
     * 新增货币标准
     *
     * @param sysMoney 货币标准
     * @return 结果
     */
    @Override
    public int insertSysMoney(SysMoney sysMoney)
    {
        sysMoney.setCreateTime(DateUtils.getNowDate());
        return sysMoneyMapper.insertSysMoney(sysMoney);
    }

    /**
     * 修改货币标准
     *
     * @param sysMoney 货币标准
     * @return 结果
     */
    @Override
    public int updateSysMoney(SysMoney sysMoney)
    {
        sysMoney.setUpdateTime(DateUtils.getNowDate());
        return sysMoneyMapper.updateSysMoney(sysMoney);
    }

    /**
     * 批量删除货币标准
     *
     * @param ids 需要删除的货币标准ID
     * @return 结果
     */
    @Override
    public int deleteSysMoneyByIds(Integer[] ids)
    {
        return sysMoneyMapper.deleteSysMoneyByIds(ids);
    }

    /**
     * 删除货币标准信息
     *
     * @param id 货币标准ID
     * @return 结果
     */
    @Override
    public int deleteSysMoneyById(Integer id)
    {
        return sysMoneyMapper.deleteSysMoneyById(id);
    }
}
