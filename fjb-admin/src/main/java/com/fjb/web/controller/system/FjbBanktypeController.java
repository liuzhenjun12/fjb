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
import com.fjb.system.domain.FjbBanktype;
import com.fjb.system.service.IFjbBanktypeService;
import com.fjb.common.utils.poi.ExcelUtil;
import com.fjb.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author liu
 * @date 2021-07-30
 */
@RestController
@RequestMapping("/system/banktype")
public class FjbBanktypeController extends BaseController
{
    @Autowired
    private IFjbBanktypeService fjbBanktypeService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:banktype:list')")
    @GetMapping("/list")
    public TableDataInfo list(FjbBanktype fjbBanktype)
    {
        startPage();
        List<FjbBanktype> list = fjbBanktypeService.selectFjbBanktypeList(fjbBanktype);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:banktype:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FjbBanktype fjbBanktype)
    {
        List<FjbBanktype> list = fjbBanktypeService.selectFjbBanktypeList(fjbBanktype);
        ExcelUtil<FjbBanktype> util = new ExcelUtil<FjbBanktype>(FjbBanktype.class);
        return util.exportExcel(list, "banktype");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:banktype:query')")
    @GetMapping(value = "/{banktypeId}")
    public AjaxResult getInfo(@PathVariable("banktypeId") Integer banktypeId)
    {
        return AjaxResult.success(fjbBanktypeService.selectFjbBanktypeById(banktypeId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:banktype:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FjbBanktype fjbBanktype)
    {
        return toAjax(fjbBanktypeService.insertFjbBanktype(fjbBanktype));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:banktype:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FjbBanktype fjbBanktype)
    {
        return toAjax(fjbBanktypeService.updateFjbBanktype(fjbBanktype));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:banktype:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{banktypeIds}")
    public AjaxResult remove(@PathVariable Integer[] banktypeIds)
    {
        return toAjax(fjbBanktypeService.deleteFjbBanktypeByIds(banktypeIds));
    }
}
