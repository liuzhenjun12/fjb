package com.fjb.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fjb.common.annotation.Excel;
import com.fjb.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 fjb_userinfo
 *
 * @author liu
 * @date 2021-07-30
 */
public class FjbUserinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 最近一次修改密码 */
    @Excel(name = "最近一次修改密码")
    private String pwdupdatedate;

    /** 省份id */
    @Excel(name = "省份id")
    private Integer provinceid;

    /** 地级市id */
    @Excel(name = "地级市id")
    private Integer cityid;

    /** 县级市id */
    @Excel(name = "县级市id")
    private Integer townid;

    /** 银行类型，关联banktype表的id */
    @Excel(name = "银行类型，关联banktype表的id")
    private Integer banktypeid;

    /** 所在银行名称 */
    @Excel(name = "所在银行名称")
    private String bankname;

    /** 是否为鉴定机构，0表示不是鉴定单位，1表示鉴定单位 */
    @Excel(name = "是否为鉴定机构，0表示不是鉴定单位，1表示鉴定单位")
    private Integer isidentify;

    /** 角色ID编号 */
    @Excel(name = "角色ID编号")
    private Integer roleid;

    /** $column.columnComment */
    @Excel(name = "角色ID编号")
    private String sealCoding;

    /** 备注 */
    @Excel(name = "备注")
    private String mark;

    /** 网点全称 */
    @Excel(name = "网点全称")
    private String branchname;

    /** 网点详细地址 */
    @Excel(name = "网点详细地址")
    private String branchaddress;

    /** 网点机构类型 */
    @Excel(name = "网点机构类型")
    private String branchtype;

    /** 网点机构代码 */
    @Excel(name = "网点机构代码")
    private String branchcode;

    /** 网点代码 */
    @Excel(name = "网点代码")
    private String branchfirm;

    /** 网点所在地经度 */
    @Excel(name = "网点所在地经度")
    private String branchlongitude;

    /** 网点所在地纬度 */
    @Excel(name = "网点所在地纬度")
    private String branchlatitude;

    /** 网点联系电话 */
    @Excel(name = "网点联系电话")
    private String contactNumber;

    /** 删除状态（1正常2已删除） */
    @Excel(name = "删除状态", readConverterExp = "1=正常2已删除")
    private Integer isdelete;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
    public void setPwdupdatedate(String pwdupdatedate)
    {
        this.pwdupdatedate = pwdupdatedate;
    }

    public String getPwdupdatedate()
    {
        return pwdupdatedate;
    }
    public void setProvinceid(Integer provinceid)
    {
        this.provinceid = provinceid;
    }

    public Integer getProvinceid()
    {
        return provinceid;
    }
    public void setCityid(Integer cityid)
    {
        this.cityid = cityid;
    }

    public Integer getCityid()
    {
        return cityid;
    }
    public void setTownid(Integer townid)
    {
        this.townid = townid;
    }

    public Integer getTownid()
    {
        return townid;
    }
    public void setBanktypeid(Integer banktypeid)
    {
        this.banktypeid = banktypeid;
    }

    public Integer getBanktypeid()
    {
        return banktypeid;
    }
    public void setBankname(String bankname)
    {
        this.bankname = bankname;
    }

    public String getBankname()
    {
        return bankname;
    }
    public void setIsidentify(Integer isidentify)
    {
        this.isidentify = isidentify;
    }

    public Integer getIsidentify()
    {
        return isidentify;
    }
    public void setRoleid(Integer roleid)
    {
        this.roleid = roleid;
    }

    public Integer getRoleid()
    {
        return roleid;
    }
    public void setSealCoding(String sealCoding)
    {
        this.sealCoding = sealCoding;
    }

    public String getSealCoding()
    {
        return sealCoding;
    }
    public void setMark(String mark)
    {
        this.mark = mark;
    }

    public String getMark()
    {
        return mark;
    }
    public void setBranchname(String branchname)
    {
        this.branchname = branchname;
    }

    public String getBranchname()
    {
        return branchname;
    }
    public void setBranchaddress(String branchaddress)
    {
        this.branchaddress = branchaddress;
    }

    public String getBranchaddress()
    {
        return branchaddress;
    }
    public void setBranchtype(String branchtype)
    {
        this.branchtype = branchtype;
    }

    public String getBranchtype()
    {
        return branchtype;
    }
    public void setBranchcode(String branchcode)
    {
        this.branchcode = branchcode;
    }

    public String getBranchcode()
    {
        return branchcode;
    }
    public void setBranchfirm(String branchfirm)
    {
        this.branchfirm = branchfirm;
    }

    public String getBranchfirm()
    {
        return branchfirm;
    }
    public void setBranchlongitude(String branchlongitude)
    {
        this.branchlongitude = branchlongitude;
    }

    public String getBranchlongitude()
    {
        return branchlongitude;
    }
    public void setBranchlatitude(String branchlatitude)
    {
        this.branchlatitude = branchlatitude;
    }

    public String getBranchlatitude()
    {
        return branchlatitude;
    }
    public void setContactNumber(String contactNumber)
    {
        this.contactNumber = contactNumber;
    }

    public String getContactNumber()
    {
        return contactNumber;
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
            .append("id", getId())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("pwdupdatedate", getPwdupdatedate())
            .append("provinceid", getProvinceid())
            .append("cityid", getCityid())
            .append("townid", getTownid())
            .append("banktypeid", getBanktypeid())
            .append("bankname", getBankname())
            .append("isidentify", getIsidentify())
            .append("roleid", getRoleid())
            .append("sealCoding", getSealCoding())
            .append("mark", getMark())
            .append("branchname", getBranchname())
            .append("branchaddress", getBranchaddress())
            .append("branchtype", getBranchtype())
            .append("branchcode", getBranchcode())
            .append("branchfirm", getBranchfirm())
            .append("branchlongitude", getBranchlongitude())
            .append("branchlatitude", getBranchlatitude())
            .append("contactNumber", getContactNumber())
            .append("isdelete", getIsdelete())
            .toString();
    }
}
