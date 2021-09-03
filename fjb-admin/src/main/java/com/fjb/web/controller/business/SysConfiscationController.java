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
import com.fjb.system.domain.SysConfiscation;
import com.fjb.system.service.ISysConfiscationService;
import com.fjb.common.utils.poi.ExcelUtil;
import com.fjb.common.core.page.TableDataInfo;

/**
 * 没收凭证Controller
 *
 * @author liu
 * @date 2021-08-26
 */
@RestController
@RequestMapping("/business/confiscation")
public class SysConfiscationController extends BaseController
{
    @Autowired
    private ISysConfiscationService sysConfiscationService;

    /**
     * 查询没收凭证列表
     */
    @PreAuthorize("@ss.hasPermi('business:confiscation:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysConfiscation sysConfiscation)
    {
        startPage();
        List<SysConfiscation> list = sysConfiscationService.selectSysConfiscationList(sysConfiscation);
        return getDataTable(list);
    }

    /**
     * 导出没收凭证列表
     */
    @PreAuthorize("@ss.hasPermi('business:confiscation:export')")
    @Log(title = "没收凭证", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysConfiscation sysConfiscation)
    {
        List<SysConfiscation> list = sysConfiscationService.selectSysConfiscationList(sysConfiscation);
        ExcelUtil<SysConfiscation> util = new ExcelUtil<SysConfiscation>(SysConfiscation.class);
        return util.exportExcel(list, "confiscation");
    }

    /**
     * 获取没收凭证详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:confiscation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(sysConfiscationService.selectSysConfiscationById(id));
    }

    /**
     * 新增没收凭证
     */
    @PreAuthorize("@ss.hasPermi('business:confiscation:add')")
    @Log(title = "没收凭证", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysConfiscation sysConfiscation)
    {
        return toAjax(sysConfiscationService.insertSysConfiscation(sysConfiscation));
    }

    /**
     * 修改没收凭证
     */
    @PreAuthorize("@ss.hasPermi('business:confiscation:edit')")
    @Log(title = "没收凭证", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysConfiscation sysConfiscation)
    {
        return toAjax(sysConfiscationService.updateSysConfiscation(sysConfiscation));
    }

    /**
     * 删除没收凭证
     */
    @PreAuthorize("@ss.hasPermi('business:confiscation:remove')")
    @Log(title = "没收凭证", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(sysConfiscationService.deleteSysConfiscationByIds(ids));
    }
}
