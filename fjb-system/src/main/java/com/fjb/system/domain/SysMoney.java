package com.fjb.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fjb.common.annotation.Excel;
import com.fjb.common.core.domain.BaseEntity;

/**
 * 货币标准对象 sys_money
 *
 * @author liu
 * @date 2021-08-27
 */
public class SysMoney extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;

    /** 币种 */
    @Excel(name = "币种")
    private String ccCurrency;

    /** 劵别 */
    @Excel(name = "劵别")
    private String ccDenomination;

    /** 版别 */
    @Excel(name = "版别")
    private String ccSeries;

    /** 正面图片 */
    @Excel(name = "正面图片")
    private String zhengImgPath;

    /** $column.columnComment */
    @Excel(name = "正面图片")
    private String fanImgPath;

    /** 当前状态 */
    @Excel(name = "当前状态")
    private String status;

    /** 删除状态（1正常2已删除0不限制） */
    private String delFlag;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
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
    public void setZhengImgPath(String zhengImgPath)
    {
        this.zhengImgPath = zhengImgPath;
    }

    public String getZhengImgPath()
    {
        return zhengImgPath;
    }
    public void setFanImgPath(String fanImgPath)
    {
        this.fanImgPath = fanImgPath;
    }

    public String getFanImgPath()
    {
        return fanImgPath;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ccCurrency", getCcCurrency())
            .append("ccDenomination", getCcDenomination())
            .append("ccSeries", getCcSeries())
            .append("zhengImgPath", getZhengImgPath())
            .append("fanImgPath", getFanImgPath())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
