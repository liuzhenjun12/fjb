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
import com.ruoyi.system.domain.SysGuangao;
import com.ruoyi.system.service.ISysGuangaoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 网站评论Controller
 *
 * @author ruoyi
 * @date 2021-04-13
 */
@RestController
@RequestMapping("/system/guangao")
public class SysGuangaoController extends BaseController
{
    @Autowired
    private ISysGuangaoService sysGuangaoService;

    /**
     * 查询网站评论列表
     */
    @PreAuthorize("@ss.hasPermi('system:guangao:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysGuangao sysGuangao)
    {
        startPage();
        List<SysGuangao> list = sysGuangaoService.selectSysGuangaoList(sysGuangao);
        return getDataTable(list);
    }

    /**
     * 导出网站评论列表
     */
    @PreAuthorize("@ss.hasPermi('system:guangao:export')")
    @Log(title = "网站评论", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysGuangao sysGuangao)
    {
        List<SysGuangao> list = sysGuangaoService.selectSysGuangaoList(sysGuangao);
        ExcelUtil<SysGuangao> util = new ExcelUtil<SysGuangao>(SysGuangao.class);
        return util.exportExcel(list, "guangao");
    }

    /**
     * 获取网站评论详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:guangao:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysGuangaoService.selectSysGuangaoById(id));
    }

    /**
     * 新增网站评论
     */
    @PreAuthorize("@ss.hasPermi('system:guangao:add')")
    @Log(title = "网站评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysGuangao sysGuangao)
    {
        return toAjax(sysGuangaoService.insertSysGuangao(sysGuangao));
    }

    /**
     * 修改网站评论
     */
    @PreAuthorize("@ss.hasPermi('system:guangao:edit')")
    @Log(title = "网站评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysGuangao sysGuangao)
    {
        return toAjax(sysGuangaoService.updateSysGuangao(sysGuangao));
    }

    /**
     * 删除网站评论
     */
    @PreAuthorize("@ss.hasPermi('system:guangao:remove')")
    @Log(title = "网站评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysGuangaoService.deleteSysGuangaoByIds(ids));
    }
}
