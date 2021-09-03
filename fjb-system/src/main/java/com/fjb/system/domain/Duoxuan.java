package com.fjb.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fjb.common.annotation.Excel;
import com.fjb.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 duoxuan
 *
 * @author liu
 * @date 2021-08-30
 */
public class Duoxuan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 难度等级 1：困难 2：普通 3：简单 */
    @Excel(name = "难度等级 1：困难 2：普通 3：简单")
    private Long level;

    /** 题目 */
    @Excel(name = "题目")
    private String title;

    /** 答案 */
    @Excel(name = "答案")
    private String answer;

    /** 解析 */
    @Excel(name = "解析")
    private String jiexi;

    /** 题目插图 */
    @Excel(name = "题目插图")
    private String titlepic;

    /** 是否显示 0：显示 1：不显示 */
    @Excel(name = "是否显示 0：显示 1：不显示")
    private Integer status;

    /** 题类型，1首次，2证书审验集中 */
    @Excel(name = "题类型，1首次，2证书审验集中")
    private Integer qtype;

    /** 题类别，0待分类，1第一章：基础知识，2第二、三章：人民币防伪知识和假人民币，3第四章：相关法律法规和规章，4第五章：相关制度规定 */
    @Excel(name = "题类别，0待分类，1第一章：基础知识，2第二、三章：人民币防伪知识和假人民币，3第四章：相关法律法规和规章，4第五章：相关制度规定")
    private Integer qcategory;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setLevel(Long level)
    {
        this.level = level;
    }

    public Long getLevel()
    {
        return level;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public String getAnswer()
    {
        return answer;
    }
    public void setJiexi(String jiexi)
    {
        this.jiexi = jiexi;
    }

    public String getJiexi()
    {
        return jiexi;
    }
    public void setTitlepic(String titlepic)
    {
        this.titlepic = titlepic;
    }

    public String getTitlepic()
    {
        return titlepic;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setQtype(Integer qtype)
    {
        this.qtype = qtype;
    }

    public Integer getQtype()
    {
        return qtype;
    }
    public void setQcategory(Integer qcategory)
    {
        this.qcategory = qcategory;
    }

    public Integer getQcategory()
    {
        return qcategory;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("level", getLevel())
            .append("title", getTitle())
            .append("answer", getAnswer())
            .append("jiexi", getJiexi())
            .append("titlepic", getTitlepic())
            .append("status", getStatus())
            .append("qtype", getQtype())
            .append("qcategory", getQcategory())
            .toString();
    }
}
