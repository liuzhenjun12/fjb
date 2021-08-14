package com.fjb.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fjb.common.annotation.Excel;
import com.fjb.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 fjb_town
 *
 * @author liu
 * @date 2021-08-12
 */
public class FjbTown extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer tid;

    /** $column.columnComment */
    private String tname;

    /** $column.columnComment */
    private String cid;

    /** $column.columnComment */
    private String zipcode;

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
    public void setCid(String cid)
    {
        this.cid = cid;
    }

    public String getCid()
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tid", getTid())
            .append("tname", getTname())
            .append("cid", getCid())
            .append("zipcode", getZipcode())
            .toString();
    }
}
