package com.ruoyi.web.controller.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
import org.apache.commons.lang.StringUtils;
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
import com.ruoyi.system.domain.SysBmb;
import com.ruoyi.system.service.ISysBmbService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 报名Controller
 *
 * @author ruoyi
 * @date 2020-11-29
 */

@RestController
@RequestMapping("/system/bmb")
public class SysBmbController extends BaseController
{
    @Autowired
    private ISysBmbService sysBmbService;

    @Autowired
    private TokenService tokenService;
    /**
     * 查询报名列表
     */
    @PreAuthorize("@ss.hasPermi('system:bmb:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysBmb sysBmb)
    {
        List<SysBmb> list=new ArrayList<>();
        if(StringUtils.isBlank(sysBmb.getName())&&StringUtils.isBlank(sysBmb.getIdcard())&&StringUtils.isBlank(sysBmb.getBukao())
                &&StringUtils.isBlank(sysBmb.getSfwc())&&
        sysBmb.getKaoshiTime()==null&&StringUtils.isBlank(sysBmb.getPici())&&sysBmb.getDeptId()==null){
            return getDataTable(list);
        }
        startPage();
        list = sysBmbService.selectSysBmbList(sysBmb);
        return getDataTable(list);
    }

    /**
     * 导出报名列表
     */
    @PreAuthorize("@ss.hasPermi('system:bmb:export')")
    @Log(title = "报名表导出", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysBmb sysBmb)
    {
        List<SysBmb> list = sysBmbService.selectSysBmbList(sysBmb);
        ExcelUtil<SysBmb> util = new ExcelUtil<SysBmb>(SysBmb.class);
        return util.exportExcel(list, "报名表");
    }

    /**
     * 导出统计报表
     */
    @PreAuthorize("@ss.hasPermi('system:bmb:export')")
    @Log(title = "导出统计报表", businessType = BusinessType.EXPORT)
    @GetMapping("/exportCount")
    public AjaxResult exportCount(String pici)
    {
        return sysBmbService.exportCount(pici);
    }

    @Log(title = "报名表导入", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:bmb:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport, String kaoshiTime,  Long deptId, String pici,String avatarUrl,String ancestors) throws Exception
    {
       //System.out.println(updateSupport+","+kaoshiTime+","+deptId+","+pici+","+pici+","+avatarUrl+","+ancestors);
        if(!updateSupport){
            if(StringUtils.isBlank(kaoshiTime)||StringUtils.isBlank(pici)||StringUtils.isBlank(avatarUrl)||deptId==null){
                return AjaxResult.error("提交数据不全");
            }
        }else {
            if(deptId==null){
                return AjaxResult.error("机构名称不能为空");
            }
            if (StringUtils.isBlank(pici)){
                return AjaxResult.error("批次不能为空");
            }
        }
        ExcelUtil<SysBmb> util = new ExcelUtil<SysBmb>(SysBmb.class);
        List<SysBmb> bmbList = util.importExcel(file.getInputStream());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        String message = sysBmbService.importBmb(bmbList, updateSupport, operName,kaoshiTime,deptId,pici,avatarUrl,ancestors);
        return AjaxResult.success(message);
    }
    /**
     * 报名表模板
     * @return
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
        ExcelUtil<SysBmb> util = new ExcelUtil<SysBmb>(SysBmb.class);
        return util.importTemplateExcel("报名表数据");
    }
    /**
     * 获取报名详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:bmb:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysBmbService.selectSysBmbById(id));
    }

    /**
     * 新增报名
     */
    @PreAuthorize("@ss.hasPermi('system:bmb:add')")
    @Log(title = "报名表新增", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysBmb sysBmb)
    {
        System.out.println(sysBmb.toString());
        return toAjax(sysBmbService.insertSysBmb(sysBmb));
    }

    /**
     * 修改报名
     */
    @PreAuthorize("@ss.hasPermi('system:bmb:edit')")
    @Log(title = "报名表修改", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysBmb sysBmb)
    {
        return toAjax(sysBmbService.updateSysBmb(sysBmb));
    }

    /**
     * 删除报名
     */
    @PreAuthorize("@ss.hasPermi('system:bmb:remove')")
    @Log(title = "报名表删除", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysBmbService.deleteSysBmbByIds(ids));
    }

    /**
     * 自动录入
     */
    @PreAuthorize("@ss.hasPermi('system:bmb:luru')")
    @Log(title = "自动录入", businessType = BusinessType.INSERT)
    @GetMapping("/luru/{ids}")
    public AjaxResult luru(@PathVariable Long[] ids)
    {
        List<SysBmb> list = sysBmbService.selectSysBmbByIds(ids);
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        String message = sysBmbService.luru(list,operName,loginUser.getUser().getUserId());
        return AjaxResult.success(message);
    }

    /**
     * 修改照片
     */
    @PreAuthorize("@ss.hasPermi('system:bmb:luru')")
    @Log(title = "修改照片", businessType = BusinessType.UPDATE)
    @GetMapping("/updateAvatar/{id}")
    public AjaxResult updateAvatar(@PathVariable Long id) throws IOException {
        SysBmb bmb = sysBmbService.selectSysBmbById(id);
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        return sysBmbService.updateAvatar(bmb,operName,loginUser.getUser().getUserId());
    }

    /**
     * 是否完成状态修改
     */
    @PreAuthorize("@ss.hasPermi('system:bmb:edit')")
    @Log(title = "是否完成状态修改", businessType = BusinessType.UPDATE)
    @PutMapping("/changeSfwc")
    public AjaxResult changeSfwc(@RequestBody SysBmb sysBmb)
    {
        sysBmb.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(sysBmbService.updateSfwcStatus(sysBmb));
    }

    /**
     * 是否补考状态修改
     */
    @PreAuthorize("@ss.hasPermi('system:bmb:edit')")
    @Log(title = "是否完成状态修改", businessType = BusinessType.UPDATE)
    @PutMapping("/changeBukao")
    public AjaxResult changeBukao(@RequestBody SysBmb sysBmb)
    {
        sysBmb.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(sysBmbService.updateSfwcStatus(sysBmb));
    }
}
