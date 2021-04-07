package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户管理对象 sys_customer
 *
 * @author ruoyi
 * @date 2021-03-29
 */
public class SysCustomer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String name;

    /** 手机号码 */
    @Excel(name = "客户手机")
    private String phone;

    /** 省 */
    @Excel(name = "省")
    private String sheng;

    /** 市 */
    @Excel(name = "市")
    private String shi;

    /** 地址 */
    @Excel(name = "客户地址", width = 50)
    private String address;

    /** 行业 */
    @Excel(name = "客户行业",readConverterExp = "1=个体户,2=企业,3=银行,4=医院",type = Excel.Type.EXPORT)
    private String industry;

    /** 多少钱 */
    @Excel(name = "成交总金额")
    private Integer money;

    /** 购买个数 */
    @Excel(name = "购买总个数")
    private Integer num;

    /** 购买次数 */
    @Excel(name = "购买次数")
    private Integer cishu;

    /** 产品型号 */
    @Excel(name = "产品名称",readConverterExp = "1=智能箱柜锁",type = Excel.Type.EXPORT)
    private String type;

    /** 产品图片 */
    private String img;

    /** 是否删除 */
    private String delFlag;

    /** 发货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发货时间", width = 20, dateFormat = "yyyy-MM-dd")
    private Date sendTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setIndustry(String industry)
    {
        this.industry = industry;
    }

    public String getIndustry()
    {
        return industry;
    }
    public void setMoney(Integer money)
    {
        this.money = money;
    }

    public Integer getMoney()
    {
        return money;
    }
    public void setNum(Integer num)
    {
        this.num = num;
    }

    public Integer getNum()
    {
        return num;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setImg(String img)
    {
        this.img = img;
    }

    public String getImg()
    {
        return img;
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

    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng;
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }

    public Integer getCishu() {
        return cishu;
    }

    public void setCishu(Integer cishu) {
        this.cishu = cishu;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("phone", getPhone())
            .append("address", getAddress())
            .append("industry", getIndustry())
            .append("money", getMoney())
            .append("num", getNum())
            .append("type", getType())
            .append("img", getImg())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .append("sendTime", getSendTime())
            .append("sheng", getSheng())
            .append("shi", getShi())
            .append("cishu", getCishu())
            .toString();
    }
}
