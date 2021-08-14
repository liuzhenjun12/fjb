package com.fjb.web.controller.system;

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
import com.fjb.system.domain.SysRemarkNum;
import com.fjb.system.service.ISysRemarkNumService;
import com.fjb.common.utils.poi.ExcelUtil;
import com.fjb.common.core.page.TableDataInfo;

/**
 * 评论数量Controller
 *
 * @date 2021-04-20
 */
@RestController
@RequestMapping("/system/num")
public class SysRemarkNumController extends BaseController
{
    @Autowired
    private ISysRemarkNumService sysRemarkNumService;

    /**
     * 查询评论数量列表
     */
    @PreAuthorize("@ss.hasPermi('system:num:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysRemarkNum sysRemarkNum)
    {
        System.out.println(sysRemarkNum.toString());
        startPage();
        List<SysRemarkNum> list = sysRemarkNumService.selectSysRemarkNumList(sysRemarkNum);
        return getDataTable(list);
    }

    /**
     * 导出评论数量列表
     */
    @PreAuthorize("@ss.hasPermi('system:num:export')")
    @Log(title = "评论数量", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysRemarkNum sysRemarkNum)
    {
        List<SysRemarkNum> list = sysRemarkNumService.selectSysRemarkNumList(sysRemarkNum);
        ExcelUtil<SysRemarkNum> util = new ExcelUtil<SysRemarkNum>(SysRemarkNum.class);
        return util.exportExcel(list, "num");
    }

    /**
     * 获取评论数量详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:num:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysRemarkNumService.selectSysRemarkNumById(id));
    }

    /**
     * 新增评论数量
     */
    @PreAuthorize("@ss.hasPermi('system:num:add')")
    @Log(title = "评论数量", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysRemarkNum sysRemarkNum)
    {
        return sysRemarkNumService.insertSysRemarkNum(sysRemarkNum);
    }

    /**
     * 修改评论数量
     */
    @PreAuthorize("@ss.hasPermi('system:num:edit')")
    @Log(title = "评论数量", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysRemarkNum sysRemarkNum)
    {
        return sysRemarkNumService.updateSysRemarkNum(sysRemarkNum);
    }

    /**
     * 删除评论数量
     */
    @PreAuthorize("@ss.hasPermi('system:num:remove')")
    @Log(title = "评论数量", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return sysRemarkNumService.deleteSysRemarkNumByIds(ids);
    }
}
