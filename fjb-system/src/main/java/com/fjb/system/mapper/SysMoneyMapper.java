package com.fjb.system.mapper;

import java.util.List;
import com.fjb.system.domain.SysMoney;

/**
 * 货币标准Mapper接口
 * 
 * @author liu
 * @date 2021-08-27
 */
public interface SysMoneyMapper 
{
    /**
     * 查询货币标准
     * 
     * @param id 货币标准ID
     * @return 货币标准
     */
    public SysMoney selectSysMoneyById(Integer id);

    /**
     * 查询货币标准列表
     * 
     * @param sysMoney 货币标准
     * @return 货币标准集合
     */
    public List<SysMoney> selectSysMoneyList(SysMoney sysMoney);

    /**
     * 新增货币标准
     * 
     * @param sysMoney 货币标准
     * @return 结果
     */
    public int insertSysMoney(SysMoney sysMoney);

    /**
     * 修改货币标准
     * 
     * @param sysMoney 货币标准
     * @return 结果
     */
    public int updateSysMoney(SysMoney sysMoney);

    /**
     * 删除货币标准
     * 
     * @param id 货币标准ID
     * @return 结果
     */
    public int deleteSysMoneyById(Integer id);

    /**
     * 批量删除货币标准
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysMoneyByIds(Integer[] ids);
}
