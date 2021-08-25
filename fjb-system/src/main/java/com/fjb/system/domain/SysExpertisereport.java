package com.fjb.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fjb.common.annotation.Excels;
import com.fjb.common.core.domain.entity.SysDept;
import com.fjb.common.core.domain.entity.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fjb.common.annotation.Excel;
import com.fjb.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 假币鉴定对象 sys_expertisereport
 *
 * @author liu
 * @date 2021-08-23
 */
public class SysExpertisereport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 鉴定书id主键 */
    private Integer id;

    /** 鉴定书编号 */
    @Excel(name = "鉴定书编号")
    private String number;

    /** 机构id */
    @Excel(name = "机构id")
    private Integer deptId;

    /** 部门对象 */
    @Excels({
            @Excel(name = "收缴网点", width = 30, targetAttr = "deptName", type = Excel.Type.EXPORT),
    })
    private SysDept dept;

    /** 用户id */
    private Integer userId;

    /** 鉴定假币数量 */
    @Excel(name = "假币数量")
    private Integer amount;

    /** 总金额 */
    @Excel(name = "假币总额")
    private Integer totalamount;

    /** 冠字号 */
    @Excel(name = "冠字号码",type = Excel.Type.EXPORT)
    private String serialNumber;

    /** 鉴定人姓名 */
    @Excel(name = "鉴定人员")
    private String identifyname;

    /** 复核人姓名 */
    @Excel(name = "复核人员")
    private String checkupname;

    /** 鉴定日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "鉴定日期",  width = 20,dateFormat = "yyyy-MM-dd",type = Excel.Type.EXPORT)
    private Date identifydate;

    /** 假币持有人姓名 */
    @Excel(name = "持有人")
    private String holderName;

    /** 假币持有人手机号码 */
    @Excel(name = "手机号码")
    private String holderTelephone;

    /** 假币持有人身份证号 */
    @Excel(name = "身份证号")
    private String holderIdcard;

    /** 币种id */
    @Excel(name = "假币币种")
    private String ccCurrency;

    /** 假币劵别 */
    @Excel(name = "假币劵别")
    private String ccDenomination;

    /** 假币版别 */
    @Excel(name = "假币版别")
    private String ccSeries;

    /** 假币造假方式 */
    @Excel(name = "造假方式")
    private String ccMadeway;

    /** 假币特征 */
    @Excel(name = "假币特征")
    private String ccFeature;

    /** 鉴定报告文件上传服务器路径 */
    @Excel(name = "鉴定报告文件上传服务器路径")
    private String identifyreportFile;

    /** 原鉴定(鉴别)单位 */
    @Excel(name = "原鉴定(鉴别)单位")
    private String originalcffi;

    /** 原鉴定（鉴别）结果 */
    @Excel(name = "原鉴定结果")
    private String originalresult;

    /** 鉴定结果 */
    @Excel(name = "鉴定结果")
    private String result;

    /** 详细鉴定结果 */
    @Excel(name = "详细鉴定结果")
    private String resultdetails;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 删除状态（1正常2已删除0不限制） */
    private String delFlag;

    /** 照片路径 */
    @Excel(name = "照片路径")
    private String imgList;

    /** 鉴定组编号 */
    @Excel(name = "鉴定组编号")
    private String groupNumber;

    private SysUser user;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getNumber()
    {
        return number;
    }
    public void setDeptId(Integer deptId)
    {
        this.deptId = deptId;
    }

    public Integer getDeptId()
    {
        return deptId;
    }
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getUserId()
    {
        return userId;
    }
    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }

    public Integer getAmount()
    {
        return amount;
    }
    public void setTotalamount(Integer totalamount)
    {
        this.totalamount = totalamount;
    }

    public Integer getTotalamount()
    {
        return totalamount;
    }
    public void setIdentifyname(String identifyname)
    {
        this.identifyname = identifyname;
    }

    public String getIdentifyname()
    {
        return identifyname;
    }
    public void setCheckupname(String checkupname)
    {
        this.checkupname = checkupname;
    }

    public String getCheckupname()
    {
        return checkupname;
    }
    public void setIdentifydate(Date identifydate)
    {
        this.identifydate = identifydate;
    }

    public Date getIdentifydate()
    {
        return identifydate;
    }
    public void setHolderName(String holderName)
    {
        this.holderName = holderName;
    }

    public String getHolderName()
    {
        return holderName;
    }
    public void setHolderTelephone(String holderTelephone)
    {
        this.holderTelephone = holderTelephone;
    }

    public String getHolderTelephone()
    {
        return holderTelephone;
    }
    public void setHolderIdcard(String holderIdcard)
    {
        this.holderIdcard = holderIdcard;
    }

    public String getHolderIdcard()
    {
        return holderIdcard;
    }
    public void setCcCurrency(String ccCurrency)
    {
        this.ccCurrency = ccCurrency;
    }

    public String getCcCurrency()
    {
        return ccCurrency;
    }
    public void setCcDenomination(String ccDenomination)
    {
        this.ccDenomination = ccDenomination;
    }

    public String getCcDenomination()
    {
        return ccDenomination;
    }
    public void setCcSeries(String ccSeries)
    {
        this.ccSeries = ccSeries;
    }

    public String getCcSeries()
    {
        return ccSeries;
    }
    public void setCcMadeway(String ccMadeway)
    {
        this.ccMadeway = ccMadeway;
    }

    public String getCcMadeway()
    {
        return ccMadeway;
    }
    public void setCcFeature(String ccFeature)
    {
        this.ccFeature = ccFeature;
    }

    public String getCcFeature()
    {
        return ccFeature;
    }
    public void setIdentifyreportFile(String identifyreportFile)
    {
        this.identifyreportFile = identifyreportFile;
    }

    public String getIdentifyreportFile()
    {
        return identifyreportFile;
    }
    public void setOriginalcffi(String originalcffi)
    {
        this.originalcffi = originalcffi;
    }

    public String getOriginalcffi()
    {
        return originalcffi;
    }
    public void setOriginalresult(String originalresult)
    {
        this.originalresult = originalresult;
    }

    public String getOriginalresult()
    {
        return originalresult;
    }
    public void setResult(String result)
    {
        this.result = result;
    }

    public String getResult()
    {
        return result;
    }
    public void setResultdetails(String resultdetails)
    {
        this.resultdetails = resultdetails;
    }

    public String getResultdetails()
    {
        return resultdetails;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }
    public void setImgList(String imgList)
    {
        this.imgList = imgList;
    }

    public String getImgList()
    {
        return imgList;
    }
    public void setGroupNumber(String groupNumber)
    {
        this.groupNumber = groupNumber;
    }

    public String getGroupNumber()
    {
        return groupNumber;
    }

    public SysDept getDept() {
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("number", getNumber())
            .append("deptId", getDeptId())
            .append("userId", getUserId())
            .append("amount", getAmount())
            .append("totalamount", getTotalamount())
            .append("identifyname", getIdentifyname())
            .append("checkupname", getCheckupname())
            .append("identifydate", getIdentifydate())
            .append("holderName", getHolderName())
            .append("holderTelephone", getHolderTelephone())
            .append("holderIdcard", getHolderIdcard())
            .append("ccCurrency", getCcCurrency())
            .append("ccDenomination", getCcDenomination())
            .append("ccSeries", getCcSeries())
            .append("ccMadeway", getCcMadeway())
            .append("ccFeature", getCcFeature())
            .append("identifyreportFile", getIdentifyreportFile())
            .append("originalcffi", getOriginalcffi())
            .append("originalresult", getOriginalresult())
            .append("result", getResult())
            .append("resultdetails", getResultdetails())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("imgList", getImgList())
            .append("groupNumber", getGroupNumber())
            .append("serialNumber", getSerialNumber())
            .toString();
    }
}
