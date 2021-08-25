package com.fjb.common.core.domain.entity;

import com.fjb.common.annotation.Excel;
import com.fjb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 城市对象 sys_city
 *
 * @author liu
 * @date 2021-08-16
 */
public class SysCity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 城市编号 */
    private Integer cid;

    /** 城市名称 */
    @Excel(name = "城市名称")
    private String cname;

    /** 省份编号 */
    @Excel(name = "省份编号")
    private Integer proid;

    /** 邮政编码 */
    @Excel(name = "邮政编码")
    private String zipcode;

    /** 经度*/
    private String jingDu;

    /** 未度*/
    private String weiDu;

    public void setCid(Integer cid)
    {
        this.cid = cid;
    }

    public Integer getCid()
    {
        return cid;
    }
    public void setCname(String cname)
    {
        this.cname = cname;
    }

    public String getCname()
    {
        return cname;
    }
    public void setProid(Integer proid)
    {
        this.proid = proid;
    }

    public Integer getProid()
    {
        return proid;
    }
    public void setZipcode(String zipcode)
    {
        this.zipcode = zipcode;
    }

    public String getZipcode()
    {
        return zipcode;
    }

    public String getJingDu() {
        return jingDu;
    }

    public void setJingDu(String jingDu) {
        this.jingDu = jingDu;
    }

    public String getWeiDu() {
        return weiDu;
    }

    public void setWeiDu(String weiDu) {
        this.weiDu = weiDu;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cid", getCid())
            .append("cname", getCname())
            .append("proid", getProid())
            .append("zipcode", getZipcode())
            .append("jingDu", getJingDu())
            .append("weiDu", getWeiDu())
            .toString();
    }
}
