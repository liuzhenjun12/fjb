package com.fjb.web.controller.system;

import java.util.List;

import com.fjb.common.core.domain.entity.SysCity;
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
import com.fjb.common.annotation.Log;
import com.fjb.common.core.controller.BaseController;
import com.fjb.common.core.domain.AjaxResult;
import com.fjb.common.enums.BusinessType;
import com.fjb.system.service.ISysCityService;
import com.fjb.common.utils.poi.ExcelUtil;

/**
 * 城市名称Controller
 *
 * @author liu
 * @date 2021-08-10
 */
@RestController
@RequestMapping("/system/city")
public class SysCityController extends BaseController
{
    @Autowired
    private ISysCityService sysCityService;

    /**
     * 查询城市名称列表
     */
    @PreAuthorize("@ss.hasPermi('system:city:list')")
    @GetMapping("/list")
    public AjaxResult list(SysCity sysCity)
    {
        List<SysCity> list = sysCityService.selectSysCityList(sysCity);
        return AjaxResult.success(list);
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(SysCity sysCity)
    {
        List<SysCity> list = sysCityService.selectSysCityList(sysCity);
        return AjaxResult.success(sysCityService.buildCityTreeSelect(list));
    }




    /**
     * 导出城市名称列表
     */
    @PreAuthorize("@ss.hasPermi('system:city:export')")
    @Log(title = "城市名称", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysCity sysCity)
    {
        List<SysCity> list = sysCityService.selectSysCityList(sysCity);
        ExcelUtil<SysCity> util = new ExcelUtil<SysCity>(SysCity.class);
        return util.exportExcel(list, "city");
    }

    /**
     * 获取城市名称详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:city:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysCityService.selectSysCityById(id));
    }

    /**
     * 新增城市名称
     */
    @PreAuthorize("@ss.hasPermi('system:city:add')")
    @Log(title = "城市名称", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCity sysCity)
    {
        return toAjax(sysCityService.insertSysCity(sysCity));
    }

    /**
     * 修改城市名称
     */
    @PreAuthorize("@ss.hasPermi('system:city:edit')")
    @Log(title = "城市名称", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCity sysCity)
    {
        return toAjax(sysCityService.updateSysCity(sysCity));
    }

    /**
     * 删除城市名称
     */
    @PreAuthorize("@ss.hasPermi('system:city:remove')")
    @Log(title = "城市名称", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysCityService.deleteSysCityByIds(ids));
    }
}
