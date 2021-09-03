package com.fjb.web.controller.fake;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.fjb.common.core.domain.FilePathVo;
import com.fjb.common.core.domain.entity.SysUser;
import com.fjb.common.utils.SecurityUtils;
import com.fjb.common.utils.StringUtils;
import com.fjb.system.domain.TiDanxuanAn;
import com.fjb.system.service.ITiDanxuanAnService;
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
import com.fjb.system.domain.TiDanxuan;
import com.fjb.system.service.ITiDanxuanService;
import com.fjb.common.utils.poi.ExcelUtil;
import com.fjb.common.core.page.TableDataInfo;

/**
 * 单选Controller
 *
 * @author liu
 * @date 2021-08-28
 */
@RestController
@RequestMapping("/fake/tiku")
public class TiDanxuanController extends BaseController
{
    @Autowired
    private ITiDanxuanService tiDanxuanService;
    @Autowired
    private ITiDanxuanAnService anService;

    /**
     * 查询单选列表
     */
    @PreAuthorize("@ss.hasPermi('fake:tiku:list')")
    @GetMapping("/list")
    public TableDataInfo list(TiDanxuan tiDanxuan)
    {
        startPage();
        List<TiDanxuan> list = tiDanxuanService.selectTiDanxuanList(tiDanxuan);
        if(!list.isEmpty()){
            for(TiDanxuan t:list){
                TiDanxuanAn an=new TiDanxuanAn();
                an.setQid(t.getId());
                List<TiDanxuanAn> ans=anService.selectTiDanxuanAnList(an);
                t.setAns(ans);
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出单选列表
     */
    @PreAuthorize("@ss.hasPermi('fake:tiku:export')")
    @Log(title = "题库", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TiDanxuan tiDanxuan)
    {
        List<TiDanxuan> list = tiDanxuanService.selectTiDanxuanList(tiDanxuan);
        ExcelUtil<TiDanxuan> util = new ExcelUtil<TiDanxuan>(TiDanxuan.class);
        return util.exportExcel(list, "danxuan");
    }

    /**
     * 获取单选详细信息
     */
    @PreAuthorize("@ss.hasPermi('fake:tiku:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        TiDanxuan danxuan=tiDanxuanService.selectTiDanxuanById(id);
        TiDanxuanAn an=new TiDanxuanAn();
        an.setQid(danxuan.getId());
        List<TiDanxuanAn> ans=anService.selectTiDanxuanAnList(an);
        danxuan.setAns(ans);
        return AjaxResult.success(danxuan);
    }

    /**
     * 新增单选
     */
    @PreAuthorize("@ss.hasPermi('fake:tiku:add')")
    @Log(title = "题库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TiDanxuan tiDanxuan)
    {
        List<TiDanxuanAn> list=null;
        System.out.println(tiDanxuan.toString());
        if(StringUtils.isNotNull(tiDanxuan.getTitlepic())){
            tiDanxuan.setTitlepic(StringUtils.substringAfter(tiDanxuan.getTitlepic(), "/profile"));
        }
        if(StringUtils.isNotNull(tiDanxuan.getRemark())){
            list=JSON.parseArray(tiDanxuan.getRemark(),TiDanxuanAn.class);
            System.out.println(list.size()+"==============");
            tiDanxuan.setRemark("");
            tiDanxuan.setCreateBy(SecurityUtils.getUsername());
        }
        int i=tiDanxuanService.insertTiDanxuan(tiDanxuan);
        if(i>0){
            if(!list.isEmpty()){
               for(TiDanxuanAn t:list){
                   t.setQpic(StringUtils.substringAfter(t.getQpic(), "/profile"));
                   t.setQid(tiDanxuan.getId());
                   anService.insertTiDanxuanAn(t);
               }
            }
            return AjaxResult.success();
        }else {
            return AjaxResult.error();
        }
    }

    /**
     * 修改单选
     */
    @PreAuthorize("@ss.hasPermi('fake:tiku:edit')")
    @Log(title = "题库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TiDanxuan tiDanxuan)
    {
        return toAjax(tiDanxuanService.updateTiDanxuan(tiDanxuan));
    }

    /**
     * 删除单选
     */
    @PreAuthorize("@ss.hasPermi('fake:tiku:remove')")
    @Log(title = "题库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return toAjax(tiDanxuanService.deleteTiDanxuanById(id));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('fake:tiku:edit')")
    @Log(title = "题库", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody TiDanxuan tiDanxuan)
    {
        tiDanxuan.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(tiDanxuanService.updateUserStatus(tiDanxuan));
    }
}
