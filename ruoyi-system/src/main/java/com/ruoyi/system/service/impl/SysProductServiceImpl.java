package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.SysCustomer;
import com.ruoyi.system.mapper.SysCustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysProductMapper;
import com.ruoyi.system.domain.SysProduct;
import com.ruoyi.system.service.ISysProductService;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-02
 */
@Service
public class SysProductServiceImpl implements ISysProductService
{
    @Autowired
    private SysProductMapper sysProductMapper;
    @Autowired
    private SysCustomerMapper sysCustomerMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public SysProduct selectSysProductById(Long id)
    {
        return sysProductMapper.selectSysProductById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param sysProduct 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysProduct> selectSysProductList(SysProduct sysProduct)
    {
        return sysProductMapper.selectSysProductList(sysProduct);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param sysProduct 【请填写功能名称】
     * @return 结果
     */
    @Override
    public AjaxResult insertSysProduct(SysProduct sysProduct)
    {
        SysCustomer customer=sysCustomerMapper.selectSysCustomerById(sysProduct.getCid());
        if(customer==null){
            return AjaxResult.error("客户不存在");
        }
        try {
            customer.setCishu(customer.getCishu() + 1);
            customer.setMoney(customer.getMoney() + sysProduct.getMoney());
            customer.setNum(customer.getNum() + sysProduct.getNum());
            customer.setUpdateBy(SecurityUtils.getUsername());
            customer.setUpdateTime(DateUtils.getNowDate());
            sysCustomerMapper.updateSysCustomer(customer);
            sysProduct.setCreateBy(SecurityUtils.getUsername());
            sysProduct.setCreateTime(DateUtils.getNowDate());
            sysProductMapper.insertSysProduct(sysProduct);
            return AjaxResult.success();
        }catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param sysProduct 【请填写功能名称】
     * @return 结果
     */
    @Override
    public AjaxResult updateSysProduct(SysProduct sysProduct)
    {
        SysCustomer customer=sysCustomerMapper.selectSysCustomerById(sysProduct.getCid());
        if(customer==null){
            return AjaxResult.error("客户不存在");
        }
        SysProduct product=sysProductMapper.selectSysProductById(sysProduct.getId());
        if(product==null){
            return AjaxResult.error("产品不存在");
        }
        try {
            customer.setMoney(customer.getMoney() - product.getMoney()+sysProduct.getMoney());
            customer.setNum(customer.getNum()- product.getNum() + sysProduct.getNum());
            customer.setUpdateTime(DateUtils.getNowDate());
            customer.setUpdateBy(SecurityUtils.getUsername());
            sysCustomerMapper.updateSysCustomer(customer);
            sysProduct.setUpdateTime(DateUtils.getNowDate());
            sysProduct.setUpdateBy(SecurityUtils.getUsername());
            sysProductMapper.updateSysProduct(sysProduct);
            return AjaxResult.success();
        }catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public AjaxResult deleteSysProductByIds(Long[] ids)
    {
        for(Long I:ids){
          SysProduct product=sysProductMapper.selectSysProductById(I);
          if(product==null){
              return AjaxResult.error("删除产品id有误");
          }
          SysCustomer customer=sysCustomerMapper.selectSysCustomerById(product.getCid());
          if(customer==null){
              return AjaxResult.error("删除产品客户不存在");
          }
          customer.setUpdateBy(SecurityUtils.getUsername());
          customer.setUpdateTime(DateUtils.getNowDate());
          customer.setNum(customer.getNum()-product.getNum());
          customer.setCishu(customer.getCishu()-1);
          customer.setMoney(customer.getMoney()-product.getMoney());
          sysCustomerMapper.updateSysCustomer(customer);
        }
        int i=sysProductMapper.deleteSysProductByIds(ids);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteSysProductById(Long id)
    {
        return sysProductMapper.deleteSysProductById(id);
    }
}
