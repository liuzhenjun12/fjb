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
import com.fjb.system.domain.SysMoney;
import com.fjb.system.service.ISysMoneyService;
import com.fjb.common.utils.poi.ExcelUtil;
import com.fjb.common.core.page.TableDataInfo;

/**
 * 货币标准Controller
 *
 * @author liu
 * @date 2021-08-27
 */
@RestController
@RequestMapping("/business/money")
public class SysMoneyController extends BaseController
{
    @Autowired
    private ISysMoneyService sysMoneyService;

    /**
     * 查询货币标准列表
     */
    @PreAuthorize("@ss.hasPermi('business:money:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysMoney sysMoney)
    {
        startPage();
        List<SysMoney> list = sysMoneyService.selectSysMoneyList(sysMoney);
        return getDataTable(list);
    }

    /**
     * 导出货币标准列表
     */
    @PreAuthorize("@ss.hasPermi('business:money:export')")
    @Log(title = "货币标准", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysMoney sysMoney)
    {
        List<SysMoney> list = sysMoneyService.selectSysMoneyList(sysMoney);
        ExcelUtil<SysMoney> util = new ExcelUtil<SysMoney>(SysMoney.class);
        return util.exportExcel(list, "money");
    }

    /**
     * 获取货币标准详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:money:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(sysMoneyService.selectSysMoneyById(id));
    }

    /**
     * 新增货币标准
     */
    @PreAuthorize("@ss.hasPermi('business:money:add')")
    @Log(title = "货币标准", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysMoney sysMoney)
    {
        return toAjax(sysMoneyService.insertSysMoney(sysMoney));
    }

    /**
     * 修改货币标准
     */
    @PreAuthorize("@ss.hasPermi('business:money:edit')")
    @Log(title = "货币标准", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysMoney sysMoney)
    {
        return toAjax(sysMoneyService.updateSysMoney(sysMoney));
    }

    /**
     * 删除货币标准
     */
    @PreAuthorize("@ss.hasPermi('business:money:remove')")
    @Log(title = "货币标准", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(sysMoneyService.deleteSysMoneyByIds(ids));
    }
}
