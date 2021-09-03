package com.fjb.web.controller.fake;

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
import com.fjb.system.domain.TiDanxuanAn;
import com.fjb.system.service.ITiDanxuanAnService;
import com.fjb.common.utils.poi.ExcelUtil;
import com.fjb.common.core.page.TableDataInfo;

/**
 * 答案Controller
 *
 * @author liu
 * @date 2021-08-28
 */
@RestController
@RequestMapping("/fake/tiku/an")
public class TiDanxuanAnController extends BaseController
{
    @Autowired
    private ITiDanxuanAnService tiDanxuanAnService;

    /**
     * 查询单选答案列表
     */
    @PreAuthorize("@ss.hasPermi('fake:danxuan:list')")
    @GetMapping("/list")
    public TableDataInfo list(TiDanxuanAn tiDanxuanAn)
    {
        startPage();
        List<TiDanxuanAn> list = tiDanxuanAnService.selectTiDanxuanAnList(tiDanxuanAn);
        return getDataTable(list);
    }

    /**
     * 导出单选答案列表
     */
    @PreAuthorize("@ss.hasPermi('fake:danxuan:export')")
    @Log(title = "答案", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TiDanxuanAn tiDanxuanAn)
    {
        List<TiDanxuanAn> list = tiDanxuanAnService.selectTiDanxuanAnList(tiDanxuanAn);
        ExcelUtil<TiDanxuanAn> util = new ExcelUtil<TiDanxuanAn>(TiDanxuanAn.class);
        return util.exportExcel(list, "an");
    }

    /**
     * 获取单选答案详细信息
     */
    @PreAuthorize("@ss.hasPermi('fake:danxuan:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tiDanxuanAnService.selectTiDanxuanAnById(id));
    }

    /**
     * 新增单选答案
     */
    @PreAuthorize("@ss.hasPermi('fake:danxuan:add')")
    @Log(title = "答案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TiDanxuanAn tiDanxuanAn)
    {
        return toAjax(tiDanxuanAnService.insertTiDanxuanAn(tiDanxuanAn));
    }

    /**
     * 修改单选答案
     */
    @PreAuthorize("@ss.hasPermi('fake:danxuan:edit')")
    @Log(title = "答案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TiDanxuanAn tiDanxuanAn)
    {
        return toAjax(tiDanxuanAnService.updateTiDanxuanAn(tiDanxuanAn));
    }

    /**
     * 删除单选答案
     */
    @PreAuthorize("@ss.hasPermi('fake:danxuan:remove')")
    @Log(title = "答案", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tiDanxuanAnService.deleteTiDanxuanAnByIds(ids));
    }
}
