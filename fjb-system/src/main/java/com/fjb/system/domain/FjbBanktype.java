package com.fjb.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fjb.common.annotation.Excel;
import com.fjb.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 fjb_banktype
 *
 * @author liu
 * @date 2021-07-30
 */
public class FjbBanktype extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 营业网点id主键 */
    private Integer banktypeId;

    /** 营业网点名称 */
    @Excel(name = "营业网点名称")
    private String banktypeName;

    /** 鉴定中心描述 */
    @Excel(name = "鉴定中心描述")
    private String banktypeDiscription;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isdelete;

    public void setBanktypeId(Integer banktypeId)
    {
        this.banktypeId = banktypeId;
    }

    public Integer getBanktypeId()
    {
        return banktypeId;
    }
    public void setBanktypeName(String banktypeName)
    {
        this.banktypeName = banktypeName;
    }

    public String getBanktypeName()
    {
        return banktypeName;
    }
    public void setBanktypeDiscription(String banktypeDiscription)
    {
        this.banktypeDiscription = banktypeDiscription;
    }

    public String getBanktypeDiscription()
    {
        return banktypeDiscription;
    }
    public void setIsdelete(Integer isdelete)
    {
        this.isdelete = isdelete;
    }

    public Integer getIsdelete()
    {
        return isdelete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("banktypeId", getBanktypeId())
            .append("banktypeName", getBanktypeName())
            .append("banktypeDiscription", getBanktypeDiscription())
            .append("isdelete", getIsdelete())
            .toString();
    }
}
