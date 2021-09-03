package com.fjb.web.controller.business;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.fjb.common.config.FjbConfig;
import com.fjb.common.core.domain.FilePathVo;
import com.fjb.common.core.domain.entity.SysDept;
import com.fjb.common.utils.SecurityUtils;
import com.fjb.common.utils.StringUtils;
import com.fjb.common.utils.file.FileUploadUtils;
import com.fjb.common.utils.file.FileUtils;
import com.fjb.system.domain.SysReceiptseizure;
import com.fjb.system.service.ISysDeptService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fjb.common.annotation.Log;
import com.fjb.common.core.controller.BaseController;
import com.fjb.common.core.domain.AjaxResult;
import com.fjb.common.enums.BusinessType;
import com.fjb.system.domain.SysExpertisereport;
import com.fjb.system.service.ISysExpertisereportService;
import com.fjb.common.utils.poi.ExcelUtil;
import com.fjb.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 假币鉴定Controller
 *
 * @author liu
 * @date 2021-08-23
 */
@RestController
@RequestMapping("/business/expertisereport")
public class SysExpertisereportController extends BaseController
{
    @Autowired
    private ISysExpertisereportService sysExpertisereportService;
    @Autowired
    private ISysDeptService deptService;
    /**
     * 查询假币鉴定列表
     */
    @PreAuthorize("@ss.hasPermi('business:expertisereport:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysExpertisereport sysExpertisereport)
    {
        startPage();
        List<SysExpertisereport> list = sysExpertisereportService.selectSysExpertisereportList(sysExpertisereport);
        return getDataTable(list);
    }

    /**
     * 导出假币鉴定列表
     */
    @PreAuthorize("@ss.hasPermi('business:expertisereport:export')")
    @Log(title = "假币鉴定", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysExpertisereport sysExpertisereport)
    {
        List<SysExpertisereport> list = sysExpertisereportService.selectSysExpertisereportList(sysExpertisereport);
        ExcelUtil<SysExpertisereport> util = new ExcelUtil<SysExpertisereport>(SysExpertisereport.class);
        return util.exportExcel(list, "expertisereport");
    }

    /**
     * 获取假币鉴定详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:expertisereport:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysExpertisereportService.selectSysExpertisereportById(id));
    }

    /**
     * 新增假币鉴定
     */
    @PreAuthorize("@ss.hasPermi('business:expertisereport:add')")
    @Log(title = "假币鉴定", businessType = BusinessType.INSERT)
    @PostMapping(value = "/add")
    public AjaxResult add(@RequestParam("file") MultipartFile[] file,SysExpertisereport sysExpertisereport) throws IOException {
        if(StringUtils.isNotEmpty(file)) {
            Long id= Long.valueOf(sysExpertisereport.getDeptId());
            SysDept dept=deptService.selectDeptById(id);
            List<FilePathVo> filePathVos = new ArrayList<>();
            for (MultipartFile s : file) {
                String avatar = FileUploadUtils.upload(FjbConfig.getUploadPath()+"/鉴定/"+dept.getJianCheng(), s);
                FilePathVo f = new FilePathVo();
                f.setName(avatar.substring(avatar.lastIndexOf("/") + 1, avatar.length()));
                f.setUrl(avatar);
                filePathVos.add(f);
            }
            if (!filePathVos.isEmpty()) {
                sysExpertisereport.setImgList(JSON.toJSONString(filePathVos));
                Date date=new Date();
                String sdate = new SimpleDateFormat("yyyyMMdd").format(date);
                sdate=sdate.substring(2, sdate.length());
                String bh=sysExpertisereportService.selectNumberByDeptid(sysExpertisereport.getDeptId());
                if(StringUtils.isNotEmpty(bh)){
                    int parseInt = Integer.parseInt(bh.substring(bh.length() - 5));
                    DecimalFormat decimalFormat = new DecimalFormat("000000");
                    String numFormat= decimalFormat .format(parseInt+1);
                    sysExpertisereport.setNumber(sdate+numFormat);
                }else {
                    sysExpertisereport.setNumber(sdate+"000001");
                }
                if("网页".equals(sysExpertisereport.getDataSource())){
                    SysExpertisereport rt=sysExpertisereportService.selectParentId(sysExpertisereport);
                    if(StringUtils.isNotNull(rt)){
                        sysExpertisereport.setParentId(rt.getId());
                    }else {
                        sysExpertisereport.setParentId((long) 0);
                    }
                }
                return toAjax(sysExpertisereportService.insertSysExpertisereport(sysExpertisereport));
            } else {
                return AjaxResult.error("上传图片失败，请重新提交");
            }
        }else {
            return AjaxResult.error("上传图片不能为空");
        }
    }

    /**
     * 修改假币鉴定
     */
    @PreAuthorize("@ss.hasPermi('business:expertisereport:edit')")
    @Log(title = "假币鉴定", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/update")
    public AjaxResult edit(@RequestParam("file") MultipartFile[] file, SysExpertisereport sysExpertisereport) throws IOException {
        SysExpertisereport re = sysExpertisereportService.selectSysExpertisereportById(sysExpertisereport.getId());
        if (file.length > 0) {
            if ("add".equals(sysExpertisereport.getStatus())) {
                Long id= Long.valueOf(sysExpertisereport.getDeptId());
                SysDept dept=deptService.selectDeptById(id);
                String avatar = FileUploadUtils.upload(FjbConfig.getUploadPath()+"/鉴定/"+dept.getJianCheng(), file[0]);
                List<FilePathVo> filePathVos=new ArrayList<>();
                if(StringUtils.isNotEmpty(re.getImgList())&&!"[]".equals(re.getImgList())) {
                    filePathVos = JSON.parseArray(re.getImgList(), FilePathVo.class);
                }
                FilePathVo f = new FilePathVo();
                f.setName(avatar.substring(avatar.lastIndexOf("/") + 1, avatar.length()));
                f.setUrl(avatar);
                filePathVos.add(f);
                re.setImgList(JSON.toJSONString(filePathVos));
                int rows = sysExpertisereportService.updateSysExpertisereport(re);
                return rows > 0 ? AjaxResult.success(f) : AjaxResult.error();
            }
        } else {
            if ("del".equals(sysExpertisereport.getStatus())) {
                String localPath = FjbConfig.getProfile();
                List<FilePathVo> filePathVos = JSON.parseArray(re.getImgList(), FilePathVo.class);
                Iterator<FilePathVo> iterator = filePathVos.iterator();
                while (iterator.hasNext()){
                    FilePathVo str = iterator.next();
                    if (sysExpertisereport.getImgList().equals(str.getName())) {
                        String filePath = localPath + StringUtils.substringAfter(str.getUrl(), "/profile");
                        FileUtils.deleteFile(filePath);
                        iterator.remove();
                    }
                }
                re.setImgList(JSON.toJSONString(filePathVos));
                return toAjax(sysExpertisereportService.updateSysExpertisereport(re));
            } else {
                int row=sysExpertisereportService.getParentNum(sysExpertisereport.getId());
                if(row>0){
                    SysExpertisereport r=new SysExpertisereport();
                    r.setParentId(sysExpertisereport.getId());
                    List<SysExpertisereport> rs=sysExpertisereportService.selectSysExpertisereportList(r);
                    if(!rs.isEmpty()){
                        for(SysExpertisereport y:rs){
                            y.setIdentifydate(sysExpertisereport.getIdentifydate());
                            y.setHolderTelephone(sysExpertisereport.getHolderTelephone());
                            y.setHolderName(sysExpertisereport.getHolderName());
                            y.setHolderIdcard(sysExpertisereport.getHolderIdcard());
                            y.setOriginalcffi(sysExpertisereport.getOriginalcffi());
                            sysExpertisereportService.updateSysExpertisereport(y);
                        }
                    }
                }
                return toAjax(sysExpertisereportService.updateSysExpertisereport(sysExpertisereport));
            }
        }
        return AjaxResult.error();
    }

    /**
     * 删除假币鉴定
     */
    @PreAuthorize("@ss.hasPermi('business:expertisereport:remove')")
    @Log(title = "假币鉴定", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        int row=sysExpertisereportService.getParentNum(id);
        if(row>0){
            return AjaxResult.error("请先删除关联的数据");
        }
        return toAjax(sysExpertisereportService.deleteSysExpertisereportById(id));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('business:expertisereport:edit')")
    @Log(title = "假币鉴定", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysExpertisereport sysExpertisereport)
    {
        sysExpertisereport.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(sysExpertisereportService.updateExpertisereportStatus(sysExpertisereport));
    }

    /**
     * 获取绑定个数
     */
    @PreAuthorize("@ss.hasPermi('business:expertisereport:query')")
    @GetMapping(value = "/parentNum/{id}")
    public AjaxResult getParentNum(@PathVariable("id") Long id)
    {
        int row=sysExpertisereportService.getParentNum(id);
        return AjaxResult.success(row);
    }
}
