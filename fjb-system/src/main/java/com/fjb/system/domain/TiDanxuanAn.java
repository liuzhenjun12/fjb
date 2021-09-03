package com.fjb.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fjb.common.annotation.Excel;
import com.fjb.common.core.domain.BaseEntity;

/**
 * 单选答案对象 ti_danxuan_an
 *
 * @author liu
 * @date 2021-08-28
 */
public class TiDanxuanAn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 问题id */
    @Excel(name = "问题id")
    private Long qid;

    /** 选项 */
    @Excel(name = "选项")
    private String qind;

    /** 选项内容 */
    @Excel(name = "选项内容")
    private String qdetail;

    /** $column.columnComment */
    @Excel(name = "选项图片")
    private String qpic;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setQid(Long qid)
    {
        this.qid = qid;
    }

    public Long getQid()
    {
        return qid;
    }
    public void setQind(String qind)
    {
        this.qind = qind;
    }

    public String getQind()
    {
        return qind;
    }
    public void setQdetail(String qdetail)
    {
        this.qdetail = qdetail;
    }

    public String getQdetail()
    {
        return qdetail;
    }
    public void setQpic(String qpic)
    {
        this.qpic = qpic;
    }

    public String getQpic()
    {
        return qpic;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("qid", getQid())
            .append("qind", getQind())
            .append("qdetail", getQdetail())
            .append("qpic", getQpic())
            .toString();
    }
}
