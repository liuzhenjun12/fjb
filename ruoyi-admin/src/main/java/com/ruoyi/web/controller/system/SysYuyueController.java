package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysYuyue;
import com.ruoyi.common.utils.StringUtils;
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
import com.ruoyi.system.service.ISysYuyueService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 预约Controller
 *
 * @author ruoyi
 * @date 2021-01-14
 */
@RestController
@RequestMapping("/system/yuyue")
public class SysYuyueController extends BaseController
{
    @Autowired
    private ISysYuyueService sysYuyueService;

    /**
     * 查询预约列表
     */
    @PreAuthorize("@ss.hasPermi('system:yuyue:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysYuyue sysYuyue)
    {
        List<SysYuyue> list=new ArrayList<>();
        if(StringUtils.isBlank(sysYuyue.getKaoshiTime())&&StringUtils.isBlank(sysYuyue.getBankNo())&&StringUtils.isBlank(sysYuyue.getCertifType())){
            return getDataTable(list);
        }
        startPage();
         list = sysYuyueService.selectSysYuyueList(sysYuyue);
        return getDataTable(list);
    }

    /**
     * 导出预约列表
     */
    @PreAuthorize("@ss.hasPermi('system:yuyue:export')")
    @Log(title = "预约", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysYuyue sysYuyue)
    {
        List<SysYuyue> list = sysYuyueService.selectSysYuyueList(sysYuyue);
        ExcelUtil<SysYuyue> util = new ExcelUtil<SysYuyue>(SysYuyue.class);
        return util.exportExcel(list, "yuyue");
    }

    /**
     * 获取预约详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:yuyue:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysYuyueService.selectSysYuyueById(id));
    }

    /**
     * 新增预约
     */
    @PreAuthorize("@ss.hasPermi('system:yuyue:add')")
    @Log(title = "预约", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysYuyue sysYuyue)
    {
        return toAjax(sysYuyueService.insertSysYuyue(sysYuyue));
    }

    /**
     * 修改预约
     */
    @PreAuthorize("@ss.hasPermi('system:yuyue:edit')")
    @Log(title = "预约", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysYuyue sysYuyue)
    {
        return toAjax(sysYuyueService.updateSysYuyue(sysYuyue));
    }

    /**
     * 删除预约
     */
    @PreAuthorize("@ss.hasPermi('system:yuyue:remove')")
    @Log(title = "预约", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysYuyueService.deleteSysYuyueByIds(ids));
    }
}
