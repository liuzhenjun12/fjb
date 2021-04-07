package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.SysProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysCustomerMapper;
import com.ruoyi.system.domain.SysCustomer;
import com.ruoyi.system.service.ISysCustomerService;

/**
 * 客户管理Service业务层处理
 *
 * @author ruoyi
 * @date 2021-03-29
 */
@Service
public class SysCustomerServiceImpl implements ISysCustomerService
{
    @Autowired
    private SysCustomerMapper sysCustomerMapper;
    @Autowired
    private SysProductMapper sysProductMapper;

    /**
     * 查询客户管理
     *
     * @param id 客户管理ID
     * @return 客户管理
     */
    @Override
    public SysCustomer selectSysCustomerById(Long id)
    {
        return sysCustomerMapper.selectSysCustomerById(id);
    }

    /**
     * 查询客户管理列表
     *
     * @param sysCustomer 客户管理
     * @return 客户管理
     */
    @Override
    public List<SysCustomer> selectSysCustomerList(SysCustomer sysCustomer)
    {
        return sysCustomerMapper.selectSysCustomerList(sysCustomer);
    }

    /**
     * 新增客户管理
     *
     * @param sysCustomer 客户管理
     * @return 结果
     */
    @Override
    public AjaxResult insertSysCustomer(SysCustomer sysCustomer)
    {
        sysCustomer.setCreateBy(SecurityUtils.getUsername());
        sysCustomer.setCreateTime(DateUtils.getNowDate());
        int i= sysCustomerMapper.insertSysCustomer(sysCustomer);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 修改客户管理
     *
     * @param sysCustomer 客户管理
     * @return 结果
     */
    @Override
    public int updateSysCustomer(SysCustomer sysCustomer)
    {
        sysCustomer.setUpdateBy(SecurityUtils.getUsername());
        sysCustomer.setUpdateTime(DateUtils.getNowDate());
        return sysCustomerMapper.updateSysCustomer(sysCustomer);
    }

    /**
     * 批量删除客户管理
     *
     * @param ids 需要删除的客户管理ID
     * @return 结果
     */
    @Override
    public AjaxResult deleteSysCustomerByIds(Long[] ids)
    {
        for (Long dictId : ids)
        {
            if (sysProductMapper.countByCid(dictId) > 0)
            {
                return AjaxResult.error("请先删除购买产品数据");
            }
        }
        int count = sysCustomerMapper.deleteSysCustomerByIds(ids);
        if(count>0){
            return AjaxResult.success();
        }else {
            return AjaxResult.error();
        }
    }

    /**
     * 删除客户管理信息
     *
     * @param id 客户管理ID
     * @return 结果
     */
    @Override
    public int deleteSysCustomerById(Long id)
    {
        return sysCustomerMapper.deleteSysCustomerById(id);
    }

    /**
     * 检查客户手机是否唯一
     * @param customer 用户信息
     * @return
     */
    @Override
    public String checkPhoneUnique(SysCustomer customer) {
        Long id = StringUtils.isNull(customer.getId()) ? -1L : customer.getId();
        SysCustomer info = sysCustomerMapper.checkPhoneUnique(customer.getPhone());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != id.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
