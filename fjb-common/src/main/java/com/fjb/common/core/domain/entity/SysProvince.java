package com.fjb.common.core.domain.entity;

import com.fjb.common.annotation.Excel;
import com.fjb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 省份对象 sys_province
 *
 * @author liu
 * @date 2021-08-16
 */
public class SysProvince extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 省份编号 */
    private Integer proid;

    /** 省份名称 */
    @Excel(name = "省份名称")
    private String proname;

    /** 国家名称 */
    @Excel(name = "国家名称")
    private String countryname;

    /** 英文名称 */
    @Excel(name = "英文名称")
    private String englishname;

    /** 邮政编码 */
    @Excel(name = "邮政编码")
    private String zipcode;

    public void setProid(Integer proid)
    {
        this.proid = proid;
    }

    public Integer getProid()
    {
        return proid;
    }
    public void setProname(String proname)
    {
        this.proname = proname;
    }

    public String getProname()
    {
        return proname;
    }
    public void setCountryname(String countryname)
    {
        this.countryname = countryname;
    }

    public String getCountryname()
    {
        return countryname;
    }
    public void setEnglishname(String englishname)
    {
        this.englishname = englishname;
    }

    public String getEnglishname()
    {
        return englishname;
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
            .append("proid", getProid())
            .append("proname", getProname())
            .append("countryname", getCountryname())
            .append("englishname", getEnglishname())
            .append("zipcode", getZipcode())
            .toString();
    }
}
