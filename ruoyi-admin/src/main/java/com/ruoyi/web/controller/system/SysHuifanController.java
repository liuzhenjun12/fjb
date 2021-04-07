package com.ruoyi.web.controller.system;

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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysHuifan;
import com.ruoyi.system.service.ISysHuifanService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 回访记录Controller
 *
 * @author ruoyi
 * @date 2021-03-30
 */
@RestController
@RequestMapping("/system/huifan")
public class SysHuifanController extends BaseController
{
    @Autowired
    private ISysHuifanService sysHuifanService;

    /**
     * 查询回访记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:huifan:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysHuifan sysHuifan)
    {
        startPage();
        List<SysHuifan> list = sysHuifanService.selectSysHuifanList(sysHuifan);
        return getDataTable(list);
    }

    /**
     * 导出回访记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:huifan:export')")
    @Log(title = "回访记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysHuifan sysHuifan)
    {
        List<SysHuifan> list = sysHuifanService.selectSysHuifanList(sysHuifan);
        ExcelUtil<SysHuifan> util = new ExcelUtil<SysHuifan>(SysHuifan.class);
        return util.exportExcel(list, "huifan");
    }

    /**
     * 获取回访记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:huifan:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        System.out.println(id+"====>id");
        return AjaxResult.success(sysHuifanService.selectSysHuifanById(id));
    }

    /**
     * 新增回访记录
     */
    @PreAuthorize("@ss.hasPermi('system:huifan:add')")
    @Log(title = "回访记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysHuifan sysHuifan)
    {
        return toAjax(sysHuifanService.insertSysHuifan(sysHuifan));
    }

    /**
     * 修改回访记录
     */
    @PreAuthorize("@ss.hasPermi('system:huifan:edit')")
    @Log(title = "回访记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysHuifan sysHuifan)
    {
        return toAjax(sysHuifanService.updateSysHuifan(sysHuifan));
    }

    /**
     * 删除回访记录
     */
    @PreAuthorize("@ss.hasPermi('system:huifan:remove')")
    @Log(title = "回访记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysHuifanService.deleteSysHuifanByIds(ids));
    }
}
