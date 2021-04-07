package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysCustomer;
import com.ruoyi.system.service.ISysCustomerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户管理Controller
 *
 * @author ruoyi
 * @date 2021-03-29
 */
@RestController
@RequestMapping("/system/customer")
public class SysCustomerController extends BaseController
{
    @Autowired
    private ISysCustomerService sysCustomerService;

    /**
     * 查询客户管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysCustomer sysCustomer)
    {
        startPage();
        List<SysCustomer> list = sysCustomerService.selectSysCustomerList(sysCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:customer:export')")
    @Log(title = "客户管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysCustomer sysCustomer)
    {
        List<SysCustomer> list = sysCustomerService.selectSysCustomerList(sysCustomer);
        ExcelUtil<SysCustomer> util = new ExcelUtil<SysCustomer>(SysCustomer.class);
        return util.exportExcel(list, "customer");
    }

    /**
     * 获取客户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysCustomerService.selectSysCustomerById(id));
    }

    /**
     * 新增客户管理
     */
    @PreAuthorize("@ss.hasPermi('system:customer:add')")
    @Log(title = "客户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCustomer sysCustomer)
    {
        if (UserConstants.NOT_UNIQUE.equals(sysCustomerService.checkPhoneUnique(sysCustomer)))
        {
            return AjaxResult.error("新增客户'" + sysCustomer.getName() + "'失败，手机号码已存在");
        }
        sysCustomer.setCreateBy(SecurityUtils.getUsername());
        return sysCustomerService.insertSysCustomer(sysCustomer);
    }

    /**
     * 修改客户管理
     */
    @PreAuthorize("@ss.hasPermi('system:customer:edit')")
    @Log(title = "客户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCustomer sysCustomer)
    {
        if (UserConstants.NOT_UNIQUE.equals(sysCustomerService.checkPhoneUnique(sysCustomer)))
        {
            return AjaxResult.error("新增客户'" + sysCustomer.getName() + "'失败，手机号码已存在");
        }
        sysCustomer.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(sysCustomerService.updateSysCustomer(sysCustomer));
    }

    /**
     * 删除客户管理
     */
    @PreAuthorize("@ss.hasPermi('system:customer:remove')")
    @Log(title = "客户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return sysCustomerService.deleteSysCustomerByIds(ids);
    }
}
