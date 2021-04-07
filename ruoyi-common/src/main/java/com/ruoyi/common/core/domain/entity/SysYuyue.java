package com.ruoyi.common.core.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 预约对象 sys_yuyue
 *
 * @author ruoyi
 * @date 2021-01-14
 */
public class SysYuyue extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 考试时间 */
    @Excel(name = "考试时间")
    private String kaoshiTime;

    /** 考试名称 */
    private String kaoshiName;

    /** 显示分配时间 */
    private String showTime;

    /** 日期id */
    private Integer pbeExamid;

    /** 报名结束时间 */
    @Excel(name = "报名时间")
    private String terminalTime;

    /** 机构名称 */
    @Excel(name = "机构名称")
    private String bankName;

    /** 机构代码 */
    @Excel(name = "机构代码")
    private String bankNo;

    /** 考试日期id */
    private String examId;

    /** 审核关闭日期 */
    @Excel(name = "审核日期")
    private String apperTime;

    /** 考试类型 */
    @Excel(name = "考试类型")
    private String certifType;

    /** 删除标记 */
    private String delFlag;

    /***预约机位数量*/
    @Excel(name = "机位数量")
    private Integer countNum;

    /***已使用机位数量*/
    private Integer useNum;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setKaoshiTime(String kaoshiTime)
    {
        this.kaoshiTime = kaoshiTime;
    }

    public String getKaoshiTime()
    {
        return kaoshiTime;
    }
    public void setKaoshiName(String kaoshiName)
    {
        this.kaoshiName = kaoshiName;
    }

    public String getKaoshiName()
    {
        return kaoshiName;
    }
    public void setShowTime(String showTime)
    {
        this.showTime = showTime;
    }

    public String getShowTime()
    {
        return showTime;
    }
    public void setPbeExamid(Integer pbeExamid)
    {
        this.pbeExamid = pbeExamid;
    }

    public Integer getPbeExamid()
    {
        return pbeExamid;
    }
    public void setTerminalTime(String terminalTime)
    {
        this.terminalTime = terminalTime;
    }

    public String getTerminalTime()
    {
        return terminalTime;
    }
    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }

    public String getBankName()
    {
        return bankName;
    }
    public void setBankNo(String bankNo)
    {
        this.bankNo = bankNo;
    }

    public String getBankNo()
    {
        return bankNo;
    }
    public void setExamId(String examId)
    {
        this.examId = examId;
    }

    public String getExamId()
    {
        return examId;
    }
    public void setApperTime(String apperTime)
    {
        this.apperTime = apperTime;
    }

    public String getApperTime()
    {
        return apperTime;
    }
    public void setCertifType(String certifType)
    {
        this.certifType = certifType;
    }

    public String getCertifType()
    {
        return certifType;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public Integer getCountNum() {
        return countNum;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    public Integer getUseNum() {
        return useNum;
    }

    public void setUseNum(Integer useNum) {
        this.useNum = useNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("kaoshiTime", getKaoshiTime())
            .append("kaoshiName", getKaoshiName())
            .append("showTime", getShowTime())
            .append("pbeExamid", getPbeExamid())
            .append("terminalTime", getTerminalTime())
            .append("bankName", getBankName())
            .append("bankNo", getBankNo())
            .append("examId", getExamId())
            .append("apperTime", getApperTime())
            .append("certifType", getCertifType())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("countNum", getCountNum())
            .append("useNum", getUseNum())
            .toString();
    }
}
