package com.fjb.web.controller.system;

import com.fjb.common.core.domain.AjaxResult;
import com.fjb.common.exception.CustomException;
import com.fjb.common.utils.poi.ExcelUtil;
import com.fjb.system.domain.vo.SqlVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/system/sql")
public class SqlController {
    /**
     * 收缴凭证修改数据模板
     * @return
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
        ExcelUtil<SqlVo> util = new ExcelUtil<SqlVo>(SqlVo.class);
        return util.importTemplateExcel("修改数据库sql模板");
    }

    @PreAuthorize("@ss.hasPermi('system:sql:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<SqlVo> util = new ExcelUtil<SqlVo>(SqlVo.class);
        List<SqlVo> sqlList = util.importExcel(file.getInputStream());
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for(SqlVo v:sqlList){
            String sql="";
            if(StringUtils.isNotBlank(v.getAttr1())){
                successNum++;
                if(v.getAttr6().indexOf("N")>-1){
                    v.setAttr6("");
                }
                sql=v.getAttr2()+v.getAttr3()+",'"+v.getAttr1()+"','"+v.getAttr4()+"','"+v.getAttr5()+"','"+v.getAttr6()+"','"+v.getAttr7()+"','"+v.getAttr8()+"','"+v.getAttr8()+"');";
            }
            System.out.println(sql);
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return AjaxResult.success(successMsg.toString());
    }
}
