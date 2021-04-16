package com.ruoyi.web.controller.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.vo.*;
import com.ruoyi.system.mapper.SysBmbMapper;
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

    @Autowired
    private SysBmbMapper bmbMapper;
    /**
     * 查询报名列表
     */
    @PreAuthorize("@ss.hasPermi('system:bmb:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysBmb sysBmb)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
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
     * 用于首页显示统计列表
     * @return
     */
    @GetMapping("/countList")
    public TableDataInfo countList(SysBmb sysBmb){
        System.out.println(sysBmb.toString());
        startPage();
        List<CountUserVo> list=bmbMapper.findAllList(sysBmb.getName(),sysBmb.getIdcard());
        return getDataTable(list);
    }

    /**
     * 用于首页地图数据
     * @return
     */
    @GetMapping("/findShengCount")
    public AjaxResult findShengCount(){
        List<CountUserVo> list=bmbMapper.findShengCount();
        return AjaxResult.success(list);
    }

    /**
     * 导出报名列表
     */
    @PreAuthorize("@ss.hasPermi('system:bmb:export')")
    @Log(title = "导出报名表", businessType = BusinessType.EXPORT)
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
    @PreAuthorize("@ss.hasPermi('system:bmb:exportCount')")
    @Log(title = "导出统计报表", businessType = BusinessType.EXPORT)
    @GetMapping("/exportCount")
    public AjaxResult exportCount(String pici) {
        return sysBmbService.exportCount(pici);
    }

    /**
     * 从测评中心下载成绩
     */
    @PreAuthorize("@ss.hasPermi('system:bmb:download')")
    @Log(title = "从测评中心下载成绩", businessType = BusinessType.UPDATE)
    @GetMapping("/downloadChengji")
    public AjaxResult downloadChengji(String pici,Long deptId) throws IOException {
        return sysBmbService.downloadChengji(pici,deptId);
    }

    @Log(title = "报名表导入", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:bmb:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport, String kaoshiTime,  Long deptId, String pici,String avatarUrl,String ancestors,Long shengId) throws Exception
    {
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
        String message = sysBmbService.importBmb(bmbList, updateSupport, operName,kaoshiTime,deptId,pici,avatarUrl,ancestors,shengId);
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
        return sysBmbService.luru(list,operName,loginUser.getUser().getUserId());
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
    @Log(title = "是否补考修改", businessType = BusinessType.UPDATE)
    @PutMapping("/changeBukao")
    public AjaxResult changeBukao(@RequestBody SysBmb sysBmb)
    {
        sysBmb.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(sysBmbService.updateSfwcStatus(sysBmb));
    }

    /**
     * 首页统计数量
     */
    @GetMapping("/countAll")
    public AjaxResult countAll()
    {
        CountAll countAll=bmbMapper.countXueyuan();
        CountAll countAll_1=bmbMapper.countProductAll();

        if(countAll!=null&&countAll_1!=null){
            countAll.setDingdan(countAll_1.getDingdan());
            countAll.setJine(countAll_1.getJine());
            return AjaxResult.success(countAll);
        }
        return null;
    }

    /**
     * 首页统计订单地图
     */
    @GetMapping("/countOrder")
    public AjaxResult countOrder()
    {
        List<JingduVo> list=bmbMapper.countOrder();
        return AjaxResult.success(list);
    }

    /**
     * 首页统计订单线性图
     */
    @GetMapping("/countOrderXian/{nian}")
    public AjaxResult countOrderXian(@PathVariable String nian)
    {
        int[] array1 = {0,0,0,0,0,0,0,0,0,0,0,0};
        int[] array2 = {0,0,0,0,0,0,0,0,0,0,0,0};
        ArrayVo arrayVo=new ArrayVo();
        List<JingduVo> list=bmbMapper.countOrderXian(nian);
        if(!list.isEmpty()) {
            for (int i = 1; i < 13; i++) {
                String ye = "";
                if (i < 10) {
                    ye = "0" + i;
                } else {
                    ye = i + "";
                }
                for (JingduVo v : list) {
                    if (ye.equals(v.getName())) {
                        array1[i - 1] = v.getNum();
                        array2[i - 1] = v.getMoney();
                        continue;
                    }
                }
            }
        }
        arrayVo.setArray1(array1);
        arrayVo.setArray2(array2);
        return AjaxResult.success(arrayVo);
    }

    /**
     * 首页统计回访
     */
    @GetMapping("/countHuifanXian/{nian}")
    public AjaxResult countHuifanXian(@PathVariable String nian)
    {
        int[] array1 = {0,0,0,0,0,0,0,0,0,0,0,0};
        int[] array2 = {0,0,0,0,0,0,0,0,0,0,0,0};
        int[] array3 = {0,0,0,0,0,0,0,0,0,0,0,0};
        ArrayVo arrayVo=new ArrayVo();
        List<JingduVo> list=bmbMapper.countHuifanXian(nian);
        if(!list.isEmpty()) {
            for (int i = 1; i < 13; i++) {
                String ye = "";
                if (i < 10) {
                    ye = "0" + i;
                } else {
                    ye = i + "";
                }
                for (JingduVo v : list) {
                    if (ye.equals(v.getName())) {
                        array1[i - 1] = v.getNum();
                        array2[i - 1] = v.getMoney();
                        array3[i - 1] = v.getValue();
                        continue;
                    }
                }
            }
        }
        arrayVo.setArray1(array1);
        arrayVo.setArray2(array2);
        arrayVo.setArray3(array3);
        return AjaxResult.success(arrayVo);
    }

    /**
     * 首页统计订单线性图
     */
    @GetMapping("/countBaoXian/{nian}")
    public AjaxResult countBaoXian(@PathVariable String nian)
    {
        int[] array1 = {0,0,0,0,0,0,0,0,0,0,0,0};
        int[] array2 = {0,0,0,0,0,0,0,0,0,0,0,0};
        int[] array3 = {0,0,0,0,0,0,0,0,0,0,0,0};
        ArrayVo arrayVo=new ArrayVo();
        List<JingduVo> list1=bmbMapper.countBaoXian(nian,"福建");
        List<JingduVo> list2=bmbMapper.countBaoXian(nian,"广东");
        List<JingduVo> list3=bmbMapper.countBaoXian(nian,"浙江");
        if(!list1.isEmpty()) {
            for (int i = 1; i < 13; i++) {
                String ye = "";
                if (i < 10) {
                    ye = "0" + i;
                } else {
                    ye = i + "";
                }
                for (JingduVo v : list1) {
                    if (ye.equals(v.getName())) {
                        array1[i - 1] = v.getNum();
                        continue;
                    }
                }
            }
        }
        if(!list2.isEmpty()) {
            for (int i = 1; i < 13; i++) {
                String ye = "";
                if (i < 10) {
                    ye = "0" + i;
                } else {
                    ye = i + "";
                }
                for (JingduVo v : list2) {
                    if (ye.equals(v.getName())) {
                        array2[i - 1] = v.getNum();
                        continue;
                    }
                }
            }
        }
        if(!list3.isEmpty()) {
            for (int i = 1; i < 13; i++) {
                String ye = "";
                if (i < 10) {
                    ye = "0" + i;
                } else {
                    ye = i + "";
                }
                for (JingduVo v : list3) {
                    if (ye.equals(v.getName())) {
                        array3[i - 1] = v.getNum();
                        continue;
                    }
                }
            }
        }
        arrayVo.setArray1(array1);
        arrayVo.setArray2(array2);
        arrayVo.setArray3(array3);
        return AjaxResult.success(arrayVo);
    }
    /**
     * 首页统计订单地图
     */
    @GetMapping("/countQy")
    public AjaxResult countQy()
    {
        List<NameValue> nameValues=new ArrayList<>();
        CountQy qy=bmbMapper.countQy();
        if(qy!=null){
            if(qy.getGr()!=null){
                NameValue nameValue=new NameValue();
                nameValue.setName("个体");
                nameValue.setValue(qy.getGr());
                nameValues.add(nameValue);
            }
            if(qy.getQy()!=null){
                NameValue nameValue=new NameValue();
                nameValue.setName("企业");
                nameValue.setValue(qy.getQy());
                nameValues.add(nameValue);
            }
            if(qy.getYh()!=null){
                NameValue nameValue=new NameValue();
                nameValue.setName("银行");
                nameValue.setValue(qy.getYh());
                nameValues.add(nameValue);
            }
            if(qy.getYy()!=null){
                NameValue nameValue=new NameValue();
                nameValue.setName("医院");
                nameValue.setValue(qy.getYy());
                nameValues.add(nameValue);
            }
        }
        return AjaxResult.success(nameValues);
    }
    /**
     * 首页统计省报名人数
     */
    @GetMapping("/countSheng")
    public AjaxResult countSheng()
    {
        List<NameValue> nameValues=bmbMapper.countSheng();
        return AjaxResult.success(nameValues);
    }

    /**
     * 购买产品最多的客户
     */
    @GetMapping("/countMai")
    public AjaxResult countMai()
    {
        List<NameValue> nameValues=bmbMapper.countMai();
        return AjaxResult.success(nameValues);
    }
}
