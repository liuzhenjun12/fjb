package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 注册对象 sys_zcb
 * 
 * @author ruoyi
 * @date 2020-11-30
 */
public class SysZcb extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 身份证 */
    @Excel(name = "身份证")
    private String sfz;

    /** 银行名称 */
    @Excel(name = "银行名称")
    private String zonghang;

    /** 考试类型 */
    @Excel(name = "考试类型")
    private String kaoshiType;

    /** 考试日期 */
    @Excel(name = "考试日期")
    private String kaoshiDate;

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
    public void setSfz(String sfz) 
    {
        this.sfz = sfz;
    }

    public String getSfz() 
    {
        return sfz;
    }
    public void setZonghang(String zonghang) 
    {
        this.zonghang = zonghang;
    }

    public String getZonghang() 
    {
        return zonghang;
    }
    public void setKaoshiType(String kaoshiType) 
    {
        this.kaoshiType = kaoshiType;
    }

    public String getKaoshiType() 
    {
        return kaoshiType;
    }
    public void setKaoshiDate(String kaoshiDate) 
    {
        this.kaoshiDate = kaoshiDate;
    }

    public String getKaoshiDate() 
    {
        return kaoshiDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("sfz", getSfz())
            .append("zonghang", getZonghang())
            .append("kaoshiType", getKaoshiType())
            .append("kaoshiDate", getKaoshiDate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
