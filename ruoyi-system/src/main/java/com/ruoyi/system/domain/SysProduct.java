package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 sys_product
 *
 * @author ruoyi
 * @date 2021-04-02
 */
public class SysProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 客户id */
    @Excel(name = "客户id")
    private Long cid;

    /** 客户id */
    @Excel(name = "客户名称")
    private String cname;

    /** 产品类型 */
    @Excel(name = "产品类型")
    private String type;

    /** 购买个数 */
    @Excel(name = "购买个数")
    private Integer num;

    /** 成交金额 */
    @Excel(name = "成交金额")
    private Integer money;

    /** 产品图片 */
    private String img;

    /** 产品尺寸 */
    @Excel(name = "产品尺寸")
    private String chicun;

    @Excel(name = "是否定制")
    /** 是否定制 */
    private String dingzhi;

    /** 是否删除 */
    private String delFlag;

    /** 购买日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "购买日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sendTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setCid(Long cid)
    {
        this.cid = cid;
    }

    public Long getCid()
    {
        return cid;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setNum(Integer num)
    {
        this.num = num;
    }

    public Integer getNum()
    {
        return num;
    }
    public void setMoney(Integer money)
    {
        this.money = money;
    }

    public Integer getMoney()
    {
        return money;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }
    public void setSendTime(Date sendTime)
    {
        this.sendTime = sendTime;
    }

    public Date getSendTime()
    {
        return sendTime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getChicun() {
        return chicun;
    }

    public void setChicun(String chicun) {
        this.chicun = chicun;
    }

    public String getDingzhi() {
        return dingzhi;
    }

    public void setDingzhi(String dingzhi) {
        this.dingzhi = dingzhi;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cid", getCid())
            .append("cname", getCname())
            .append("type", getType())
            .append("num", getNum())
            .append("money", getMoney())
            .append("img", getImg())
            .append("chicun", getChicun())
            .append("dingzhi", getDingzhi())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("delFlag", getDelFlag())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .append("sendTime", getSendTime())
            .toString();
    }
}
