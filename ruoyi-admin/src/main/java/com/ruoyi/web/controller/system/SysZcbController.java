package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.SysBmb;
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
import com.ruoyi.system.domain.SysZcb;
import com.ruoyi.system.service.ISysZcbService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 注册Controller
 *
 * @author ruoyi
 * @date 2020-11-30
 */
@RestController
@RequestMapping("/system/zcb")
public class SysZcbController extends BaseController
{
    @Autowired
    private ISysZcbService sysZcbService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询注册列表
     */
    @PreAuthorize("@ss.hasPermi('system:zcb:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysZcb sysZcb)
    {
        startPage();
        List<SysZcb> list = sysZcbService.selectSysZcbList(sysZcb);
        return getDataTable(list);
    }

    /**
     * 导出注册列表
     */
    @PreAuthorize("@ss.hasPermi('system:zcb:export')")
    @Log(title = "导出注册列表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysZcb sysZcb)
    {
        List<SysZcb> list = sysZcbService.selectSysZcbList(sysZcb);
        ExcelUtil<SysZcb> util = new ExcelUtil<SysZcb>(SysZcb.class);
        return util.exportExcel(list, "zcb");
    }

    /**
     * 注册表导入
     * @param file
     * @param updateSupport
     * @return
     * @throws Exception
     */
    @Log(title = "注册表导入", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:zcb:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysZcb> util = new ExcelUtil<SysZcb>(SysZcb.class);
        List<SysZcb> bmbList = util.importExcel(file.getInputStream());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        String message = sysZcbService.importZcb(bmbList, updateSupport, operName);
        return AjaxResult.success(message);
    }
    /**
     * 报名表模板
     * @return
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
        ExcelUtil<SysZcb> util = new ExcelUtil<SysZcb>(SysZcb.class);
        return util.importTemplateExcel("注册表数据");
    }
    /**
     * 获取注册详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:zcb:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysZcbService.selectSysZcbById(id));
    }

    /**
     * 新增注册
     */
    @PreAuthorize("@ss.hasPermi('system:zcb:add')")
    @Log(title = "新增注册", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysZcb sysZcb)
    {
        return toAjax(sysZcbService.insertSysZcb(sysZcb));
    }

    /**
     * 修改注册
     */
    @PreAuthorize("@ss.hasPermi('system:zcb:edit')")
    @Log(title = "修改注册", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysZcb sysZcb)
    {
        return toAjax(sysZcbService.updateSysZcb(sysZcb));
    }

    /**
     * 删除注册
     */
    @PreAuthorize("@ss.hasPermi('system:zcb:remove')")
    @Log(title = "删除注册", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysZcbService.deleteSysZcbByIds(ids));
    }
}
