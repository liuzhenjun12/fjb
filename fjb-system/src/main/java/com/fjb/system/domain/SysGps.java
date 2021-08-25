package com.fjb.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fjb.common.annotation.Excel;
import com.fjb.common.core.domain.BaseEntity;

/**
 * 经纬度对象 sys_gps
 *
 * @author liu
 * @date 2021-08-24
 */
public class SysGps extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 经纬度 */
    @Excel(name = "经纬度")
    private String gps;

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
    public void setGps(String gps)
    {
        this.gps = gps;
    }

    public String getGps()
    {
        return gps;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("gps", getGps())
            .toString();
    }
}
