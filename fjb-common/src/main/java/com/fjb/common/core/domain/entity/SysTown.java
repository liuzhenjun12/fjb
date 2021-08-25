package com.fjb.common.core.domain.entity;

import com.fjb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 县区对象 sys_town
 *
 * @author liu
 * @date 2021-08-16
 */
public class SysTown extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer tid;

    /** $column.columnComment */
    private String tname;

    /** $column.columnComment */
    private Integer cid;

    /** $column.columnComment */
    private String zipcode;

    /** 经度*/
    private String jingDu;

    /** 未度*/
    private String weiDu;

    public void setTid(Integer tid)
    {
        this.tid = tid;
    }

    public Integer getTid()
    {
        return tid;
    }
    public void setTname(String tname)
    {
        this.tname = tname;
    }

    public String getTname()
    {
        return tname;
    }
    public void setCid(Integer cid)
    {
        this.cid = cid;
    }

    public Integer getCid()
    {
        return cid;
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
            .append("tid", getTid())
            .append("tname", getTname())
            .append("cid", getCid())
            .append("zipcode", getZipcode())
            .append("jingDu", getJingDu())
            .append("weiDu", getWeiDu())
            .toString();
    }
}
