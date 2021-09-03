package com.fjb.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fjb.common.annotation.Excel;
import com.fjb.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 没收凭证对象 sys_confiscation
 *
 * @author liu
 * @date 2021-08-26
 */
public class SysConfiscation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 没收收据id主键 */
    private Integer id;

    /** 没收收据编号 */
    @Excel(name = "没收收据编号")
    private String number;

    /** 用户id */
    @Excel(name = "用户id")
    private Integer userId;

    /** 机构id */
    @Excel(name = "机构id")
    private Integer deptId;

    /** 收缴人姓名 */
    @Excel(name = "收缴人姓名")
    private String identifyname;

    /** 复核人姓名 */
    @Excel(name = "复核人姓名")
    private String checkupname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "鉴定日期",  width = 20,dateFormat = "yyyy-MM-dd",type = Excel.Type.EXPORT)
    private Date identifydate;

    /** 没收假币数量 */
    @Excel(name = "没收假币数量")
    private Integer amount;

    /** 总金额 */
    @Excel(name = "总金额")
    private Integer totalamount;

    /** 假币持有人姓名 */
    @Excel(name = "假币持有人姓名")
    private String holderName;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String holderIdcard;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String holderTelephone;

    /** 银行卡号 */
    @Excel(name = "银行卡号")
    private String holderBanksnomber;

    /** 假币来源 */
    @Excel(name = "假币来源")
    private Integer source;

    /** 鉴定证书编号 */
    @Excel(name = "鉴定证书编号")
    private String numberofverification;

    /** 原鉴定(鉴别)单位 */
    @Excel(name = "原鉴定(鉴别)单位")
    private String originalcffi;

    /** 原鉴定（鉴别）结果 */
    @Excel(name = "原鉴定", readConverterExp = "鉴=别")
    private String originalresult;

    /** 鉴定结果 */
    @Excel(name = "鉴定结果")
    private String result;

    /** 详细鉴定结果 */
    @Excel(name = "详细鉴定结果")
    private String resultdetails;

    /** 删除状态（1正常2已删除0不限制） */
    private String delFlag;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 冠字号 */
    @Excel(name = "冠字号")
    private String serialNumber;

    /** 币种 */
    @Excel(name = "币种")
    private String ccCurrency;

    /** 假币劵别 */
    @Excel(name = "假币劵别")
    private String ccDenomination;

    /** 假币版别 */
    @Excel(name = "假币版别")
    private String ccSeries;

    /** 造假方式 */
    @Excel(name = "造假方式")
    private String ccMadeway;

    /** 组编号 */
    @Excel(name = "组编号")
    private Long parentId;

    /** 数据来源，鉴别仪-网页 */
    private String dataSource;

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
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getUserId()
    {
        return userId;
    }
    public void setDeptId(Integer deptId)
    {
        this.deptId = deptId;
    }

    public Integer getDeptId()
    {
        return deptId;
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
    public void setHolderName(String holderName)
    {
        this.holderName = holderName;
    }

    public String getHolderName()
    {
        return holderName;
    }
    public void setHolderIdcard(String holderIdcard)
    {
        this.holderIdcard = holderIdcard;
    }

    public String getHolderIdcard()
    {
        return holderIdcard;
    }
    public void setHolderTelephone(String holderTelephone)
    {
        this.holderTelephone = holderTelephone;
    }

    public String getHolderTelephone()
    {
        return holderTelephone;
    }
    public void setHolderBanksnomber(String holderBanksnomber)
    {
        this.holderBanksnomber = holderBanksnomber;
    }

    public String getHolderBanksnomber()
    {
        return holderBanksnomber;
    }
    public void setSource(Integer source)
    {
        this.source = source;
    }

    public Integer getSource()
    {
        return source;
    }
    public void setNumberofverification(String numberofverification)
    {
        this.numberofverification = numberofverification;
    }

    public String getNumberofverification()
    {
        return numberofverification;
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
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber()
    {
        return serialNumber;
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
    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("number", getNumber())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("identifyname", getIdentifyname())
            .append("checkupname", getCheckupname())
            .append("identifydate", getIdentifydate())
            .append("amount", getAmount())
            .append("totalamount", getTotalamount())
            .append("holderName", getHolderName())
            .append("holderIdcard", getHolderIdcard())
            .append("holderTelephone", getHolderTelephone())
            .append("holderBanksnomber", getHolderBanksnomber())
            .append("source", getSource())
            .append("remark", getRemark())
            .append("numberofverification", getNumberofverification())
            .append("originalcffi", getOriginalcffi())
            .append("originalresult", getOriginalresult())
            .append("result", getResult())
            .append("resultdetails", getResultdetails())
            .append("delFlag", getDelFlag())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("serialNumber", getSerialNumber())
            .append("ccCurrency", getCcCurrency())
            .append("ccDenomination", getCcDenomination())
            .append("ccSeries", getCcSeries())
            .append("ccMadeway", getCcMadeway())
            .append("parentId", getParentId())
            .append("dataSource", getDataSource())
            .toString();
    }
}
