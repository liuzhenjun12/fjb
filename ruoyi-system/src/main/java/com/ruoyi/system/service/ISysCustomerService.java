package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.SysCustomer;

/**
 * 客户管理Service接口
 *
 * @author ruoyi
 * @date 2021-03-29
 */
public interface ISysCustomerService
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
    public AjaxResult insertSysCustomer(SysCustomer sysCustomer);

    /**
     * 修改客户管理
     *
     * @param sysCustomer 客户管理
     * @return 结果
     */
    public int updateSysCustomer(SysCustomer sysCustomer);

    /**
     * 批量删除客户管理
     *
     * @param ids 需要删除的客户管理ID
     * @return 结果
     */
    public AjaxResult deleteSysCustomerByIds(Long[] ids);

    /**
     * 删除客户管理信息
     *
     * @param id 客户管理ID
     * @return 结果
     */
    public int deleteSysCustomerById(Long id);

    /**
     * 校验手机号码是否唯一
     *
     * @param customer 用户信息
     * @return 结果
     */
    public String checkPhoneUnique(SysCustomer customer);

}
