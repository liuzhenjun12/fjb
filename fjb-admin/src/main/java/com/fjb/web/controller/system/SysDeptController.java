package com.fjb.web.controller.system;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.fjb.common.core.domain.entity.SysUser;
import com.fjb.common.core.text.UserList;
import com.fjb.common.core.text.UserSql;
import com.fjb.system.domain.FjbBanktype;
import com.fjb.system.domain.FjbUserinfo;
import com.fjb.system.mapper.FjbBanktypeMapper;
import com.fjb.system.mapper.FjbUserinfoMapper;
import com.fjb.system.service.ISysUserService;
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
import com.fjb.common.core.domain.entity.SysDept;
import com.fjb.common.enums.BusinessType;
import com.fjb.common.utils.SecurityUtils;
import com.fjb.common.utils.StringUtils;
import com.fjb.system.service.ISysDeptService;

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
    private FjbBanktypeMapper banktypeMapper;
    @Autowired
    private FjbUserinfoMapper userinfoMapper;
    @Autowired
    private ISysUserService userService;

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
    public AjaxResult list(SysDept dept)
    {
        dept.setDeptType("1");
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
            return AjaxResult.error("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
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
            return AjaxResult.error("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        else if (dept.getParentId().equals(dept.getDeptId()))
        {
            return AjaxResult.error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        }
        else if (StringUtils.equals(UserConstants.DEPT_DISABLE, dept.getStatus())
                && deptService.selectNormalChildrenDeptById(dept.getDeptId()) > 0)
        {
            return AjaxResult.error("该部门包含未停用的子部门！");
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
     * 操作数据库
     */
    @PreAuthorize("@ss.hasPermi('system:dept:sql')")
    @GetMapping(value = "/sql/{deptId}")
    public AjaxResult sql(@PathVariable Long deptId) throws IOException {
        //导入县级银行
//       SysDept dept=new SysDept();
//       dept.setDeptType("3");
//       dept.setSizeId(101);
//       List<SysDept> list=deptService.selectDeptList(dept);
//       System.out.println("SysDeptsize==>"+list.size());
//       int jj=0;
//       for(SysDept J:list){
//           SysDept parent=deptService.selectDeptById(J.getParentId());
//           FjbUserinfo userinfo=new FjbUserinfo();
//           userinfo.setCityid(Integer.parseInt(parent.getJigouCode()));
//           userinfo.setTownid(Integer.parseInt(J.getJigouCode()));
//           userinfo.setRoleid(10);
//           userinfo.setIsdelete(1);
//           List<FjbUserinfo> list1=userinfoMapper.selectFjbUserinfoListGroup(userinfo);
//           int i=0;
//           for(FjbUserinfo S:list1){
//               i+=1;
//               FjbBanktype banktype=banktypeMapper.selectFjbBanktypeById(S.getBanktypeid());
//               SysDept d=new SysDept();
//               d.setParentId(J.getDeptId());
//               d.setCreateBy("admin");
//               d.setDeptType("4");
//               d.setStatus("0");
//               d.setDelFlag("0");
//               d.setOrderNum((i)+"");
//               d.setDeptName(banktype.getBanktypeName());
//               d.setAncestors(J.getAncestors()+","+J.getDeptId());
//               d.setJigouCode(S.getBanktypeid().toString());
//               d.setJianCheng(banktype.getBanktypeName());
//               deptService.insertDept(d);
//           }
//       }
        //导入网点
//        SysDept dept=new SysDept();
//        dept.setDeptType("4");
//        dept.setSizeId(101);
//        List<SysDept> list=deptService.selectDeptList(dept);
//        System.out.println("size==>"+list.size());
//        int jj=0;
//        for(SysDept J:list){
//            SysDept parent=deptService.selectDeptById(J.getParentId());
//            SysDept ppent=deptService.selectDeptById(parent.getParentId());
//            FjbUserinfo userinfo=new FjbUserinfo();
//            userinfo.setCityid(Integer.parseInt(ppent.getJigouCode()));
//            userinfo.setTownid(Integer.parseInt(parent.getJigouCode()));
////            userinfo.setRoleid(10);
//            userinfo.setIsdelete(1);
//            userinfo.setBanktypeid(Integer.parseInt(J.getJigouCode()));
//            List<FjbUserinfo> userinfos=userinfoMapper.selectFjbUserinfoList(userinfo);
//            if(userinfos.isEmpty()){
//                System.out.println(J.getDeptName()+"用户表不存在;"+J.getDeptId());
//                continue;
//            }
//            jj+=userinfos.size();
//            for(int i=0;i<userinfos.size();i++){
//                SysDept d=new SysDept();
//                d.setAddress(userinfos.get(i).getBranchaddress());
//                d.setDeptName(userinfos.get(i).getBranchname());
//                d.setJianCheng(userinfos.get(i).getBankname());
//                d.setJigouCode(userinfos.get(i).getBranchcode());
//                d.setWangCode(userinfos.get(i).getBranchfirm());
//                d.setWangType("1");
//                d.setYinCode(userinfos.get(i).getSealCoding());
//                d.setStatus("0");
//                d.setCreateBy("admin");
//                d.setShi(ppent.getJianCheng());
//                d.setXian(parent.getDeptName());
//                d.setHang(J.getDeptName());
//                d.setParentId(J.getDeptId());
//                d.setAncestors(J.getAncestors()+","+J.getDeptId());
//                if(!StringUtils.isEmpty(userinfos.get(i).getBranchlongitude())&&!StringUtils.isEmpty(userinfos.get(i).getBranchlatitude())) {
//                    if (userinfos.get(i).getBranchlongitude().length() > 10) {
//                        d.setJinDu(userinfos.get(i).getBranchlongitude().substring(0, 10));
//                    } else {
//                        d.setJinDu(userinfos.get(i).getBranchlongitude());
//                    }
//                    if (userinfos.get(i).getBranchlatitude().length() > 10) {
//                        d.setWeiDu(userinfos.get(i).getBranchlatitude().substring(0, 10));
//                    } else {
//                        d.setWeiDu(userinfos.get(i).getBranchlatitude());
//                    }
//                }
//                d.setOrderNum((i+1)+"");
//                d.setDeptType("5");
//                d.setDelFlag("0");
//               deptService.insertDept(d);
//            }
//        }
//        System.out.println("size=="+jj);
        //导入用户角色4
        SysDept parent=deptService.selectDeptById((long) 1297);
                    FjbUserinfo userinfo=new FjbUserinfo();
            userinfo.setRoleid(3);
            userinfo.setIsdelete(1);
            List<FjbUserinfo> userinfos=userinfoMapper.selectFjbUserinfoList(userinfo);
            for(FjbUserinfo F:userinfos){
                SysUser u=new SysUser();
                u.setDeptId(parent.getDeptId());
                u.setUserName(F.getUsername());
                u.setPassword(F.getPassword());
                u.setNickName(F.getUsername());
                u.setPwdupdatedate(F.getPwdupdatedate());
                Long[] roleIds=new Long[1];
                Long[] postIds=new Long[1];
                roleIds[0]=2L;
                postIds[0]=1L;
                u.setRoleIds(roleIds);
                u.setPostIds(postIds);
                userService.insertUser(u);
            }

        return AjaxResult.success();
    }
}
