package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 网站评论对象 sys_guangao
 *
 * @author ruoyi
 * @date 2021-04-13
 */
public class SysGuangao extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 新闻名称 */
    @Excel(name = "新闻名称",type = Excel.Type.EXPORT)
    private String name;

    /** 新闻连接地址 */
    private String url;

    /** 来自哪 */
    @Excel(name = "平台名称")
    private String laizi;

    /** 评论次数*/
    private Integer num;

    /** 是否评论过 */
    @Excel(name = "是否评论过", readConverterExp = "Y=YES,N=NO",type = Excel.Type.EXPORT)
    private String status;

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
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
    public void setLaizi(String laizi)
    {
        this.laizi = laizi;
    }

    public String getLaizi()
    {
        return laizi;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("url", getUrl())
            .append("laizi", getLaizi())
            .append("num", getNum())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
