package com.fjb.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fjb.common.annotation.Excel;
import com.fjb.common.core.domain.BaseEntity;

/**
 * 评论人员对象 sys_remark_by
 *
 * @author fjb
 * @date 2021-04-19
 */
public class SysRemarkBy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 账号类型 */
    @Excel(name = "账号类型")
    private String ptType;

    /** 平台账号名称 */
    @Excel(name = "平台账号名称")
    private String ptName;

    /** 内容数量 */
    @Excel(name = "内容数量")
    private Integer contentNum;

    /** 点赞数量 */
    @Excel(name = "点赞数量")
    private Integer dianzanNum;

    /** 关注数量 */
    @Excel(name = "关注数量")
    private Integer guanzuNum;

    /** 粉丝数量 */
    @Excel(name = "粉丝数量")
    private Integer fensiNum;

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
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setPtType(String ptType)
    {
        this.ptType = ptType;
    }

    public String getPtType()
    {
        return ptType;
    }
    public void setPtName(String ptName)
    {
        this.ptName = ptName;
    }

    public String getPtName()
    {
        return ptName;
    }
    public void setContentNum(Integer contentNum)
    {
        this.contentNum = contentNum;
    }

    public Integer getContentNum()
    {
        return contentNum;
    }
    public void setDianzanNum(Integer dianzanNum)
    {
        this.dianzanNum = dianzanNum;
    }

    public Integer getDianzanNum()
    {
        return dianzanNum;
    }
    public void setGuanzuNum(Integer guanzuNum)
    {
        this.guanzuNum = guanzuNum;
    }

    public Integer getGuanzuNum()
    {
        return guanzuNum;
    }
    public void setFensiNum(Integer fensiNum)
    {
        this.fensiNum = fensiNum;
    }

    public Integer getFensiNum()
    {
        return fensiNum;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("ptType", getPtType())
            .append("ptName", getPtName())
            .append("contentNum", getContentNum())
            .append("dianzanNum", getDianzanNum())
            .append("guanzuNum", getGuanzuNum())
            .append("fensiNum", getFensiNum())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
