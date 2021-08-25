package com.fjb.common.core.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fjb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fjb.common.core.domain.BaseEntity;

/**
 * 部门表 sys_dept
 *
 */
public class SysDept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 部门ID */
    private Long deptId;

    /** 父部门ID */
    private Long parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 部门名称 */
    private String deptName;

    /** 显示顺序 */
    private String orderNum;

    /** 是鉴定单位  */
    private String leader;

    /** 联系电话 */
    private String phone;

    /** 部门状态:0正常,1停用 */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 父部门名称 */
    private String parentName;

    /*** 机构代码*/
    private String jigouCode;

    /*** 机构简称*/
    private String  jianCheng;
    /*** 经度*/
    private String jinDu;
    /*** 纬度*/
    private String weiDu;

    private Integer sizeId;

    /*** 所在省id*/
    private Integer proid;
    /*** 所在市id*/
    private Integer cid;
    /*** 所在县id*/
    private Integer tid;
    /*** 省*/
    private SysProvince province;
    /*** 市i*/
    private SysCity city;
    /*** 县*/
    private SysTown town;
    /*** 是鉴定单位：N或Y*/
    private String isJianDing;

    private boolean hasChildren;

    /** 子部门 */
    private List<SysDept> children = new ArrayList<SysDept>();

    public Long getDeptId()
    {
        return deptId;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public String getAncestors()
    {
        return ancestors;
    }

    public void setAncestors(String ancestors)
    {
        this.ancestors = ancestors;
    }

    @NotBlank(message = "部门名称不能为空")
    @Size(min = 0, max = 30, message = "部门名称长度不能超过30个字符")
    public String getDeptName()
    {
        return deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    @NotBlank(message = "显示顺序不能为空")
    public String getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(String orderNum)
    {
        this.orderNum = orderNum;
    }

    public String getLeader()
    {
        return leader;
    }

    public void setLeader(String leader)
    {
        this.leader = leader;
    }

    @Size(min = 0, max = 11, message = "联系电话长度不能超过11个字符")
    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getParentName()
    {
        return parentName;
    }

    public void setParentName(String parentName)
    {
        this.parentName = parentName;
    }

    public List<SysDept> getChildren()
    {
        return children;
    }

    public void setChildren(List<SysDept> children)
    {
        this.children = children;
    }

    public String getJigouCode() { return jigouCode; }

    public void setJigouCode(String jigouCode) { this.jigouCode = jigouCode; }

    public String getJianCheng() { return jianCheng; }

    public void setJianCheng(String jianCheng) { this.jianCheng = jianCheng; }

    public Integer getSizeId() { return sizeId; }

    public void setSizeId(Integer sizeId) { this.sizeId = sizeId; }

    public String getJinDu() {
        return jinDu;
    }

    public void setJinDu(String jinDu) {
        this.jinDu = jinDu;
    }

    public String getWeiDu() {
        return weiDu;
    }

    public void setWeiDu(String weiDu) {
        this.weiDu = weiDu;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }

    public SysProvince getProvince() {
        return province;
    }

    public void setProvince(SysProvince province) {
        this.province = province;
    }

    public SysCity getCity() {
        return city;
    }

    public void setCity(SysCity city) {
        this.city = city;
    }

    public SysTown getTown() {
        return town;
    }

    public void setTown(SysTown town) {
        this.town = town;
    }

    public String getIsJianDing() {
        return isJianDing;
    }

    public void setIsJianDing(String isJianDing) {
        this.isJianDing = isJianDing;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deptId", getDeptId())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("deptName", getDeptName())
            .append("orderNum", getOrderNum())
            .append("leader", getLeader())
            .append("phone", getPhone())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("jigouCode", getJigouCode())
            .append("jianCheng", getJianCheng())
            .append("jinDu", getJinDu())
            .append("weiDu", getWeiDu())
            .append("isJianDing", getIsJianDing())
            .toString();
    }
}
