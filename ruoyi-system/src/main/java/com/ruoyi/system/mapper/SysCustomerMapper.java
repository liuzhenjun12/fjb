package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.SysCustomer;

/**
 * 客户管理Mapper接口
 *
 * @date 2021-03-29
 */
public interface SysCustomerMapper
{
    /**
     * 查询客户管理
     *
     * @param id 客户管理ID
     * @return 客户管理
     */
    public SysCustomer selectSysCustomerById(Long id);

    /**
     * 查询客户管理列表
     *
     * @param sysCustomer 客户管理
     * @return 客户管理集合
     */
    public List<SysCustomer> selectSysCustomerList(SysCustomer sysCustomer);

    /**
     * 新增客户管理
     *
     * @param sysCustomer 客户管理
     * @return 结果
     */
    public int insertSysCustomer(SysCustomer sysCustomer);

    /**
     * 修改客户管理
     *
     * @param sysCustomer 客户管理
     * @return 结果
     */
    public int updateSysCustomer(SysCustomer sysCustomer);

    /**
     * 删除客户管理
     *
     * @param id 客户管理ID
     * @return 结果
     */
    public int deleteSysCustomerById(Long id);

    /**
     * 批量删除客户管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysCustomerByIds(Long[] ids);

    /**
     * 校验手机号码是否唯一
     *
     * @param phone 手机号码
     * @return 结果
     */
    public SysCustomer checkPhoneUnique(String phone);
}
