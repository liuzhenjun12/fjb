package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 回访记录对象 sys_huifan
 *
 * @author ruoyi
 * @date 2021-04-02
 */
public class SysHuifan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 客户id */
    private Integer cuId;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String cname;

    /** 是否还要买 */
    @Excel(name = "是否还要买")
    private String yaoMai;

    /** 回访通道 */
    @Excel(name = "回访通道")
    private String tongDao;

    /** 客户态度 */
    @Excel(name = "客户态度")
    private String taiDu;

    /** 是否删除 */
    private String delFlag;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setCuId(Integer cuId)
    {
        this.cuId = cuId;
    }

    public Integer getCuId()
    {
        return cuId;
    }
    public void setYaoMai(String yaoMai)
    {
        this.yaoMai = yaoMai;
    }

    public String getYaoMai()
    {
        return yaoMai;
    }
    public void setTongDao(String tongDao)
    {
        this.tongDao = tongDao;
    }

    public String getTongDao()
    {
        return tongDao;
    }
    public void setTaiDu(String taiDu)
    {
        this.taiDu = taiDu;
    }

    public String getTaiDu()
    {
        return taiDu;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("cuId", getCuId())
                .append("cname", getCname())
                .append("yaoMai", getYaoMai())
                .append("tongDao", getTongDao())
                .append("taiDu", getTaiDu())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("remark", getRemark())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
