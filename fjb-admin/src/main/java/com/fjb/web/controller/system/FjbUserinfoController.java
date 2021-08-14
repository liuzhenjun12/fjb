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
import com.fjb.system.domain.FjbUserinfo;
import com.fjb.system.service.IFjbUserinfoService;
import com.fjb.common.utils.poi.ExcelUtil;
import com.fjb.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author liu
 * @date 2021-07-30
 */
@RestController
@RequestMapping("/system/userinfo")
public class FjbUserinfoController extends BaseController
{
    @Autowired
    private IFjbUserinfoService fjbUserinfoService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:userinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(FjbUserinfo fjbUserinfo)
    {
        startPage();
        List<FjbUserinfo> list = fjbUserinfoService.selectFjbUserinfoList(fjbUserinfo);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:userinfo:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FjbUserinfo fjbUserinfo)
    {
        List<FjbUserinfo> list = fjbUserinfoService.selectFjbUserinfoList(fjbUserinfo);
        ExcelUtil<FjbUserinfo> util = new ExcelUtil<FjbUserinfo>(FjbUserinfo.class);
        return util.exportExcel(list, "userinfo");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:userinfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(fjbUserinfoService.selectFjbUserinfoById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:userinfo:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FjbUserinfo fjbUserinfo)
    {
        return toAjax(fjbUserinfoService.insertFjbUserinfo(fjbUserinfo));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:userinfo:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FjbUserinfo fjbUserinfo)
    {
        return toAjax(fjbUserinfoService.updateFjbUserinfo(fjbUserinfo));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:userinfo:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(fjbUserinfoService.deleteFjbUserinfoByIds(ids));
    }
}
