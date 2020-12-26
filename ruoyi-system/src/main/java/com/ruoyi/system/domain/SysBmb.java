package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.core.domain.entity.SysDept;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 报名对象 sys_bmb
 *
 * @author ruoyi
 * @date 2020-12-14
 */
public class SysBmb extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 身份证 */
    @Excel(name = "身份证", width = 30)
    private String idcard;

    /** 考试日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考试日期",  width = 20,dateFormat = "yyyy-MM-dd",type = Excel.Type.EXPORT)
    private Date kaoshiTime;

    @Excel(name = "考试类型",  readConverterExp = "1=首次,2=证书,3=未知",type = Excel.Type.EXPORT)
    private String kaoshiType;

    /** 机构名称 */
    private Long deptId;

    /** 祖级列表 */
    private String ancestors;

    /** 是否补考 */
    @Excel(name = "是否补考", readConverterExp = "Y=是,N=不是,W=未知")
    private String bukao;

    /** 是否完成 */
    @Excel(name = "是否完成", readConverterExp = "Y=是,N=不是,W=未知",type = Excel.Type.EXPORT)
    private String sfwc;

    /** 复查情况 */
    @Excel(name = "复查情况", width = 40,type = Excel.Type.EXPORT)
    private String fucha;

    /** 理论成绩 */
    @Excel(name = "理论成绩", readConverterExp = "Y=合格,N=不合格,W=未公布")
    private String liluen;

    /** 实操成绩 */
    @Excel(name = "实操成绩", readConverterExp = "Y=合格,N=不合格,W=未公布")
    private String shichao;

    /** 批次 */
    @Excel(name = "批次", width = 30,type = Excel.Type.EXPORT)
    private String pici;

    /** 岗位类型 */
    @Excel(name = "岗位类型")
    private String examType;

    /** 日期id */
    private String examId;

    /** 删除标记 */
    private String delFlag;

    /** 相片地址 */
    private String avatarUrl;

    /** 部门对象 */
    @Excels({
            @Excel(name = "机构名称",  width = 40,targetAttr = "deptName", type = Excel.Type.EXPORT)
    })
    private SysDept dept;

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
    public void setIdcard(String idcard)
    {
        this.idcard = idcard;
    }

    public String getIdcard()
    {
        return idcard;
    }
    public void setKaoshiTime(Date kaoshiTime)
    {
        this.kaoshiTime = kaoshiTime;
    }

    public Date getKaoshiTime()
    {
        return kaoshiTime;
    }
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }
    public void setBukao(String bukao)
    {
        this.bukao = bukao;
    }

    public String getBukao()
    {
        return bukao;
    }
    public void setSfwc(String sfwc)
    {
        this.sfwc = sfwc;
    }

    public String getSfwc()
    {
        return sfwc;
    }
    public void setFucha(String fucha)
    {
        this.fucha = fucha;
    }

    public String getFucha()
    {
        return fucha;
    }
    public void setLiluen(String liluen)
    {
        this.liluen = liluen;
    }

    public String getLiluen()
    {
        return liluen;
    }
    public void setShichao(String shichao)
    {
        this.shichao = shichao;
    }

    public String getShichao()
    {
        return shichao;
    }
    public void setPici(String pici)
    {
        this.pici = pici;
    }

    public String getPici()
    {
        return pici;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }
    public void setAvatarUrl(String avatarUrl)
    {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl()
    {
        return avatarUrl;
    }

    public SysDept getDept() {
        return dept;
    }

    public String getKaoshiType() { return kaoshiType; }

    public void setKaoshiType(String kaoshiType) { this.kaoshiType = kaoshiType; }

    public String getAncestors() { return ancestors; }

    public void setAncestors(String ancestors) { this.ancestors = ancestors; }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    public String getExamType() { return examType; }

    public void setExamType(String examType) { this.examType = examType; }

    public String getExamId() { return examId; }

    public void setExamId(String examId) { this.examId = examId; }

    public SysBmb() { }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("idcard", getIdcard())
                .append("kaoshiTime", getKaoshiTime())
                .append("deptId", getDeptId())
                .append("ancestors", getAncestors())
                .append("bukao", getBukao())
                .append("sfwc", getSfwc())
                .append("fucha", getFucha())
                .append("liluen", getLiluen())
                .append("shichao", getShichao())
                .append("pici", getPici())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("remark", getRemark())
                .append("delFlag", getDelFlag())
                .append("avatarUrl", getAvatarUrl())
                .append("dept", getDept())
                .append("kaoshiType", getKaoshiType())
                .append("examType", getExamType())
                .append("examId", getExamId())
                .toString();
    }
}
