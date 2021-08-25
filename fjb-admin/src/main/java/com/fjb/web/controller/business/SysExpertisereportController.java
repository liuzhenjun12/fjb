package com.fjb.web.controller.business;

import java.util.List;
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
import com.fjb.system.domain.SysExpertisereport;
import com.fjb.system.service.ISysExpertisereportService;
import com.fjb.common.utils.poi.ExcelUtil;
import com.fjb.common.core.page.TableDataInfo;

/**
 * 假币鉴定Controller
 *
 * @author liu
 * @date 2021-08-23
 */
@RestController
@RequestMapping("/business/expertisereport")
public class SysExpertisereportController extends BaseController
{
    @Autowired
    private ISysExpertisereportService sysExpertisereportService;

    /**
     * 查询假币鉴定列表
     */
    @PreAuthorize("@ss.hasPermi('business:expertisereport:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysExpertisereport sysExpertisereport)
    {
        startPage();
        List<SysExpertisereport> list = sysExpertisereportService.selectSysExpertisereportList(sysExpertisereport);
        return getDataTable(list);
    }

    /**
     * 导出假币鉴定列表
     */
    @PreAuthorize("@ss.hasPermi('business:expertisereport:export')")
    @Log(title = "假币鉴定", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysExpertisereport sysExpertisereport)
    {
        List<SysExpertisereport> list = sysExpertisereportService.selectSysExpertisereportList(sysExpertisereport);
        ExcelUtil<SysExpertisereport> util = new ExcelUtil<SysExpertisereport>(SysExpertisereport.class);
        return util.exportExcel(list, "expertisereport");
    }

    /**
     * 获取假币鉴定详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:expertisereport:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(sysExpertisereportService.selectSysExpertisereportById(id));
    }

    /**
     * 新增假币鉴定
     */
    @PreAuthorize("@ss.hasPermi('business:expertisereport:add')")
    @Log(title = "假币鉴定", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysExpertisereport sysExpertisereport)
    {
        return toAjax(sysExpertisereportService.insertSysExpertisereport(sysExpertisereport));
    }

    /**
     * 修改假币鉴定
     */
    @PreAuthorize("@ss.hasPermi('business:expertisereport:edit')")
    @Log(title = "假币鉴定", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysExpertisereport sysExpertisereport)
    {
        return toAjax(sysExpertisereportService.updateSysExpertisereport(sysExpertisereport));
    }

    /**
     * 删除假币鉴定
     */
    @PreAuthorize("@ss.hasPermi('business:expertisereport:remove')")
    @Log(title = "假币鉴定", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(sysExpertisereportService.deleteSysExpertisereportByIds(ids));
    }
}
