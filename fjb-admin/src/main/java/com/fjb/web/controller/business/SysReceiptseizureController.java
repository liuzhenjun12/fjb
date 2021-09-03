package com.fjb.web.controller.business;

import com.fjb.common.annotation.Log;
import com.fjb.common.config.FjbConfig;
import com.fjb.common.core.controller.BaseController;
import com.fjb.common.core.domain.AjaxResult;
import com.fjb.common.core.domain.FilePathVo;
import com.fjb.common.core.domain.entity.SysDept;
import com.fjb.common.core.domain.entity.SysUser;
import com.fjb.common.core.domain.model.LoginUser;
import com.fjb.common.core.page.TableDataInfo;
import com.fjb.common.enums.BusinessType;
import com.fjb.common.utils.SecurityUtils;
import com.fjb.common.utils.ServletUtils;
import com.fjb.common.utils.StringUtils;
import com.fjb.common.utils.file.FileUploadUtils;
import com.fjb.common.utils.file.FileUtils;
import com.fjb.common.utils.poi.ExcelUtil;
import com.fjb.framework.config.ServerConfig;
import com.fjb.framework.web.service.TokenService;
import com.fjb.system.domain.SysReceiptseizure;
import com.fjb.system.service.ISysDeptService;
import com.fjb.system.service.ISysReceiptseizureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.alibaba.fastjson.JSON;
/**
 * 假币收缴凭证Controller
 *
 * @author liu
 * @date 2021-08-17
 */
@RestController
@RequestMapping("/business/receiptseizure")
public class SysReceiptseizureController extends BaseController
{
    @Autowired
    private ISysReceiptseizureService sysReceiptseizureService;
    @Autowired
    private ISysDeptService deptService;

    /**
     * 查询假币收缴凭证列表
     */
    @PreAuthorize("@ss.hasPermi('business:receiptseizure:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysReceiptseizure sysReceiptseizure)
    {
        startPage();
        List<SysReceiptseizure> list = sysReceiptseizureService.selectSysReceiptseizureList(sysReceiptseizure);
        return getDataTable(list);
    }

    /**
     * 导出假币收缴凭证列表
     */
    @PreAuthorize("@ss.hasPermi('business:receiptseizure:export')")
    @Log(title = "收缴凭证", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysReceiptseizure sysReceiptseizure)
    {
        List<SysReceiptseizure> list = sysReceiptseizureService.selectSysReceiptseizureList(sysReceiptseizure);
        ExcelUtil<SysReceiptseizure> util = new ExcelUtil<SysReceiptseizure>(SysReceiptseizure.class);
        return util.exportExcel(list, "receiptseizure");
    }

    /**
     * 获取假币收缴凭证详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:receiptseizure:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysReceiptseizureService.selectSysReceiptseizureById(id));
    }

    /**
     * 新增假币收缴凭证
     */
    @PreAuthorize("@ss.hasPermi('business:receiptseizure:add')")
    @Log(title = "收缴凭证", businessType = BusinessType.INSERT)
    @PostMapping(value = "/add")
    public AjaxResult add(@RequestParam("file") MultipartFile[] file, SysReceiptseizure sysReceiptseizure) throws IOException {
        if(StringUtils.isNotEmpty(file)) {
            Long id= Long.valueOf(sysReceiptseizure.getDeptId());
            SysDept dept=deptService.selectDeptById(id);
            List<FilePathVo> filePathVos = new ArrayList<>();
            for (MultipartFile s : file) {
                String avatar = FileUploadUtils.upload(FjbConfig.getUploadPath()+"/收缴/"+dept.getJianCheng(), s);
                FilePathVo f = new FilePathVo();
                f.setName(avatar.substring(avatar.lastIndexOf("/") + 1, avatar.length()));
                f.setUrl(avatar);
                filePathVos.add(f);
            }
            if (!filePathVos.isEmpty()) {
                sysReceiptseizure.setImgList(JSON.toJSONString(filePathVos));
                String bh=sysReceiptseizureService.selectNumberByDeptid(sysReceiptseizure.getDeptId());
                if(StringUtils.isNotEmpty(bh)){
                    int parseInt = Integer.parseInt(bh.substring(bh.length() - 5));
                    DecimalFormat decimalFormat = new DecimalFormat("000000");
                    String numFormat= decimalFormat .format(parseInt+1);
                    sysReceiptseizure.setNumber(dept.getJigouCode()+numFormat);
                }else {
                    sysReceiptseizure.setNumber(dept.getJigouCode()+"000001");
                }
                if(!"网页".equals(sysReceiptseizure.getDataSource())){
                    SysReceiptseizure zu=sysReceiptseizureService.selectParentId(sysReceiptseizure);
                    if(StringUtils.isNotNull(zu)){
                        sysReceiptseizure.setParentId(zu.getId());
                    }else {
                        sysReceiptseizure.setParentId((long) 0);
                    }
                }
                return toAjax(sysReceiptseizureService.insertSysReceiptseizure(sysReceiptseizure));
            } else {
                return AjaxResult.error("上传图片失败，请重新提交");
            }
        }else {
            return AjaxResult.error("上传图片不能为空");
        }
    }

    /**
     * 修改假币收缴凭证
     */
    @PreAuthorize("@ss.hasPermi('business:receiptseizure:edit')")
    @Log(title = "收缴凭证", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/update")
    public AjaxResult edit(@RequestParam("file") MultipartFile[] file,  SysReceiptseizure sysReceiptseizure) throws IOException {
        SysReceiptseizure re = sysReceiptseizureService.selectSysReceiptseizureById(sysReceiptseizure.getId());
        if (file.length > 0) {
            if ("add".equals(sysReceiptseizure.getStatus())) {
                Long id= Long.valueOf(sysReceiptseizure.getDeptId());
                SysDept dept=deptService.selectDeptById(id);
                String avatar = FileUploadUtils.upload(FjbConfig.getUploadPath()+"/收缴/"+dept.getJianCheng(), file[0]);
                List<FilePathVo> filePathVos=new ArrayList<>();
                if(StringUtils.isNotEmpty(re.getImgList())&&!"[]".equals(re.getImgList())) {
                    filePathVos = JSON.parseArray(re.getImgList(), FilePathVo.class);
                }
                FilePathVo f = new FilePathVo();
                f.setName(avatar.substring(avatar.lastIndexOf("/") + 1, avatar.length()));
                f.setUrl(avatar);
                filePathVos.add(f);
                re.setImgList(JSON.toJSONString(filePathVos));
                int rows = sysReceiptseizureService.updateSysReceiptseizure(re);
                return rows > 0 ? AjaxResult.success(f) : AjaxResult.error();
            }
        } else {
            if ("del".equals(sysReceiptseizure.getStatus())) {
                String localPath = FjbConfig.getProfile();
                List<FilePathVo> filePathVos = JSON.parseArray(re.getImgList(), FilePathVo.class);
                Iterator<FilePathVo> iterator = filePathVos.iterator();
                while (iterator.hasNext()){
                    FilePathVo str = iterator.next();
                    if (sysReceiptseizure.getImgList().equals(str.getName())) {
                        String filePath = localPath + StringUtils.substringAfter(str.getUrl(), "/profile");
                        FileUtils.deleteFile(filePath);
                        iterator.remove();
                    }
                }
                re.setImgList(JSON.toJSONString(filePathVos));
                return toAjax(sysReceiptseizureService.updateSysReceiptseizure(re));
            } else {
                int row=sysReceiptseizureService.getParentNum(sysReceiptseizure.getId());
                if(row>0){
                    SysReceiptseizure r=new SysReceiptseizure();
                    r.setParentId(sysReceiptseizure.getId());
                    List<SysReceiptseizure> rs=sysReceiptseizureService.selectSysReceiptseizureList(r);
                    if(!rs.isEmpty()){
                        for(SysReceiptseizure y:rs){
                            y.setReceiptdate(sysReceiptseizure.getReceiptdate());
                            y.setUnitOrIndividual(sysReceiptseizure.getUnitOrIndividual());
                            y.setUnitName(sysReceiptseizure.getUnitName());
                            y.setReceiptManname(sysReceiptseizure.getReceiptManname());
                            y.setHolderTelephone(sysReceiptseizure.getHolderTelephone());
                            y.setHolderName(sysReceiptseizure.getHolderName());
                            y.setHolderIdcard(sysReceiptseizure.getHolderIdcard());
                            y.setDocumentType(sysReceiptseizure.getDocumentType());
                            y.setCheekup(sysReceiptseizure.getCheekup());
                            sysReceiptseizureService.updateSysReceiptseizure(y);
                        }
                    }
                }
                return toAjax(sysReceiptseizureService.updateSysReceiptseizure(sysReceiptseizure));
            }
        }
        return AjaxResult.error();
    }

    /**
     * 删除假币收缴凭证
     */
    @PreAuthorize("@ss.hasPermi('business:receiptseizure:remove')")
    @Log(title = "收缴凭证", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        int row=sysReceiptseizureService.getParentNum(id);
        if(row>0){
            return AjaxResult.error("请先删除关联的数据");
        }
        return toAjax(sysReceiptseizureService.deleteSysReceiptseizureById(id));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('business:receiptseizure:edit')")
    @Log(title = "收缴凭证", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysReceiptseizure sysReceiptseizure)
    {
        sysReceiptseizure.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(sysReceiptseizureService.updateReceiptseizureStatus(sysReceiptseizure));
    }

    /**
     * 获取绑定个数
     */
    @PreAuthorize("@ss.hasPermi('business:receiptseizure:query')")
    @GetMapping(value = "/parentNum/{id}")
    public AjaxResult getParentNum(@PathVariable("id") Long id)
    {
        int row=sysReceiptseizureService.getParentNum(id);
        return AjaxResult.success(row);
    }

}
