package com.fjb.web.controller.system;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.fjb.common.core.domain.entity.*;
import com.fjb.common.core.text.UserList;
import com.fjb.common.core.text.UserSql;
import com.fjb.system.domain.*;
import com.fjb.system.mapper.FjbBanktypeMapper;
import com.fjb.system.mapper.FjbUserinfoMapper;
import com.fjb.system.service.*;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fjb.common.annotation.Log;
import com.fjb.common.constant.UserConstants;
import com.fjb.common.core.controller.BaseController;
import com.fjb.common.core.domain.AjaxResult;
import com.fjb.common.enums.BusinessType;
import com.fjb.common.utils.SecurityUtils;
import com.fjb.common.utils.StringUtils;

/**
 * 部门信息
 *
 */
@RestController
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController
{
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private ISysProvinceService provinceService;
    @Autowired
    private ISysCityService cityService;
    @Autowired
    private ISysTownService townService;

    /**
     * 获取全部部门列表
     */
    @PreAuthorize("@ss.hasPermi('system:dept:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll()
    {
        SysDept dept=new SysDept();
        List<SysDept> depts = deptService.selectDeptList(dept);
        return AjaxResult.success(depts);
    }

    /**
     * 获取上级部门列表
     */
    @PreAuthorize("@ss.hasPermi('system:dept:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        SysDept dept=new SysDept();
        dept.setParentId((long) 0);
        List<SysDept> depts = deptService.selectDeptList(dept);
        for(SysDept S:depts){
            S.setHasChildren(true);
        }
        return AjaxResult.success(depts);
    }

    /**
     * 查询下级部门列表（排除节点）
     */
    @PreAuthorize("@ss.hasPermi('system:dept:list')")
    @GetMapping("/next/{deptId}")
    public AjaxResult nextDept(@PathVariable(value = "deptId") Long deptId)
    {
        SysDept dept=new SysDept();
        dept.setParentId(deptId);
        List<SysDept> depts = deptService.selectDeptList(dept);
        for(SysDept S:depts){
            if(deptService.hasChildByDeptId(S.getDeptId())){
                S.setHasChildren(true);
            }else {
                S.setHasChildren(false);
            }
        }
        return AjaxResult.success(depts);
    }

    /**
     * 查询部门列表（排除节点）
     */
    @PreAuthorize("@ss.hasPermi('system:dept:list')")
    @GetMapping("/list/exclude/{deptId}")
    public AjaxResult excludeChild(@PathVariable(value = "deptId", required = false) Long deptId)
    {
        SysDept dept=new SysDept();
        List<SysDept> depts = deptService.selectDeptList(dept);
        Iterator<SysDept> it = depts.iterator();
        while (it.hasNext())
        {
            SysDept d = (SysDept) it.next();
            if (d.getDeptId().intValue() == deptId
                    || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), deptId + ""))
            {
                it.remove();
            }
        }
        return AjaxResult.success(depts);
    }

    /**
     * 根据部门编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:dept:query')")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable Long deptId)
    {
        return AjaxResult.success(deptService.selectDeptById(deptId));
    }

    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(SysDept dept)
    {
        List<SysDept> depts = deptService.selectDeptList(dept);
        return AjaxResult.success(deptService.buildDeptTreeSelect(depts));
    }

    /**
     * 获取部门级联下拉列表
     */
    @GetMapping("/cascadeselect")
    public AjaxResult cascadeselect(SysDept dept)
    {
        System.out.println(dept.toString());
        List<SysDept> depts = deptService.selectDeptList(dept);
        return AjaxResult.success(deptService.buildCascadeselect(depts));
    }

    /**
     * 加载对应角色部门列表树
     */
    @GetMapping(value = "/roleDeptTreeselect/{roleId}")
    public AjaxResult roleDeptTreeselect(@PathVariable("roleId") Long roleId)
    {
        SysDept dept=new SysDept();
        List<SysDept> depts = deptService.selectDeptList(dept);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("checkedKeys", deptService.selectDeptListByRoleId(roleId));
        ajax.put("depts", deptService.buildDeptTreeSelect(depts));
        return ajax;
    }

    /**
     * 新增部门
     */
    @PreAuthorize("@ss.hasPermi('system:dept:add')")
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysDept dept)
    {
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept)))
        {
            return AjaxResult.error("新增机构'" + dept.getDeptName() + "'失败，机构名称已存在");
        }
        if(UserConstants.NOT_UNIQUE.equals(deptService.checkDeptCodeUnique(dept))){
            return AjaxResult.error("新增机构'" + dept.getJigouCode() + "'失败，机构代码已存在");
        }
        dept.setCreateBy(SecurityUtils.getUsername());
        return toAjax(deptService.insertDept(dept));
    }

    /**
     * 修改部门
     */
    @PreAuthorize("@ss.hasPermi('system:dept:edit')")
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysDept dept)
    {
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept)))
        {
            return AjaxResult.error("修改机构'" + dept.getDeptName() + "'失败，机构名称已存在");
        }
        else if (dept.getParentId().equals(dept.getDeptId()))
        {
            return AjaxResult.error("修改机构'" + dept.getDeptName() + "'失败，上级机构不能是自己");
        }
        else if (StringUtils.equals(UserConstants.DEPT_DISABLE, dept.getStatus())
                && deptService.selectNormalChildrenDeptById(dept.getDeptId()) > 0)
        {
            return AjaxResult.error("该机构包含未停用的子机构！");
        }else if(UserConstants.NOT_UNIQUE.equals(deptService.checkDeptCodeUnique(dept))){
            return AjaxResult.error("修改机构'" + dept.getDeptName() + "'失败，机构代码已存在");
        }
        dept.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(deptService.updateDept(dept));
    }

    /**
     * 删除部门
     */
    @PreAuthorize("@ss.hasPermi('system:dept:remove')")
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deptId}")
    public AjaxResult remove(@PathVariable Long deptId)
    {
        if (deptService.hasChildByDeptId(deptId))
        {
            return AjaxResult.error("存在下级部门,不允许删除");
        }
        if (deptService.checkDeptExistUser(deptId))
        {
            return AjaxResult.error("部门存在用户,不允许删除");
        }
        return toAjax(deptService.deleteDeptById(deptId));
    }

    /**
     * 查询省名称列表
     */
    @PreAuthorize("@ss.hasPermi('system:dept:add')")
    @GetMapping("/province/list")
    public AjaxResult provinceList(SysProvince province)
    {
        List<SysProvince> list = provinceService.selectSysProvinceList(province);
        return AjaxResult.success(list);
    }

    /**
     * 查询城市名称列表
     */
    @PreAuthorize("@ss.hasPermi('system:dept:add')")
    @GetMapping("/city/list")
    public AjaxResult cityList(SysCity sysCity)
    {
        List<SysCity> list = cityService.selectSysCityList(sysCity);
        return AjaxResult.success(list);
    }

    /**
     * 查询县名称列表
     */
    @PreAuthorize("@ss.hasPermi('system:dept:add')")
    @GetMapping("/town/list")
    public AjaxResult townList(SysTown town)
    {
        List<SysTown> list = townService.selectSysTownList(town);
        return AjaxResult.success(list);
    }
}
