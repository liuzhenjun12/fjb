package com.fjb.web.controller.system;

import java.util.List;

import com.fjb.common.constant.UserConstants;
import com.fjb.common.core.domain.model.LoginUser;
import com.fjb.common.utils.DateUtils;
import com.fjb.common.utils.ServletUtils;
import com.fjb.framework.web.service.TokenService;
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
import com.fjb.system.domain.SysRemarkBy;
import com.fjb.system.service.ISysRemarkByService;
import com.fjb.common.utils.poi.ExcelUtil;
import com.fjb.common.core.page.TableDataInfo;

/**
 * 评论人员Controller
 *
 * @date 2021-04-19
 */
@RestController
@RequestMapping("/system/by")
public class SysRemarkByController extends BaseController
{
    @Autowired
    private ISysRemarkByService sysRemarkByService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询评论人员列表
     */
    @PreAuthorize("@ss.hasPermi('system:by:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysRemarkBy sysRemarkBy)
    {
        startPage();
        List<SysRemarkBy> list = sysRemarkByService.selectSysRemarkByList(sysRemarkBy);
        return getDataTable(list);
    }

    /**
     * 导出评论人员列表
     */
    @PreAuthorize("@ss.hasPermi('system:by:export')")
    @Log(title = "导出评论人员", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysRemarkBy sysRemarkBy)
    {
        List<SysRemarkBy> list = sysRemarkByService.selectSysRemarkByList(sysRemarkBy);
        ExcelUtil<SysRemarkBy> util = new ExcelUtil<SysRemarkBy>(SysRemarkBy.class);
        return util.exportExcel(list, "by");
    }

    /**
     * 获取评论人员详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:by:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysRemarkByService.selectSysRemarkByById(id));
    }

    /**
     * 新增评论人员
     */
    @PreAuthorize("@ss.hasPermi('system:by:add')")
    @Log(title = "新增评论人员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysRemarkBy sysRemarkBy)
    {
        if (UserConstants.NOT_UNIQUE.equals(sysRemarkByService.checkPemarkUnique(sysRemarkBy)))
        {
            return AjaxResult.error("新增评论人员'" + sysRemarkBy.getUserName() + "'失败，账号已存在");
        }
        if(UserConstants.NOT_UNIQUE.equals(sysRemarkByService.checkPtNameUnique(sysRemarkBy)))
        {
            return AjaxResult.error("新增评论人员'" + sysRemarkBy.getUserName() + "'失败，平台账号已存在");
        }
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        sysRemarkBy.setCreateBy(operName);
        sysRemarkBy.setCreateTime(DateUtils.getNowDate());
        return toAjax(sysRemarkByService.insertSysRemarkBy(sysRemarkBy));
    }

    /**
     * 修改评论人员
     */
    @PreAuthorize("@ss.hasPermi('system:by:edit')")
    @Log(title = "修改评论人员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysRemarkBy sysRemarkBy)
    {
        if(UserConstants.NOT_UNIQUE.equals(sysRemarkByService.checkPtNameUnique(sysRemarkBy)))
        {
            return AjaxResult.error("修改评论人员'" + sysRemarkBy.getUserName() + "'失败，平台账号已存在");
        }
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        sysRemarkBy.setUpdateBy(operName);
        sysRemarkBy.setUpdateTime(DateUtils.getNowDate());
        return toAjax(sysRemarkByService.updateSysRemarkBy(sysRemarkBy));
    }

    /**
     * 删除评论人员
     */
    @PreAuthorize("@ss.hasPermi('system:by:remove')")
    @Log(title = "删除评论人员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysRemarkByService.deleteSysRemarkByByIds(ids));
    }
}
