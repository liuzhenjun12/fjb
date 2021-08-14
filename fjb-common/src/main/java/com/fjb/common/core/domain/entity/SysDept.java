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

    /** 邮箱 */
    private String email;

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
    /*** 印章代码*/
    private String yinCode;
    /*** 机构地址*/
    private String address;

    private Integer sizeId;
    /*** 机构类型：1，省。2，市。3，县。4,镇。*/
    private String deptType;
    /*** 网点代码*/
    private String wangCode;
    /*** 网点类型*/
    private String wangType;
    /*** 所在省*/
    private String sheng;
    /*** 所在市*/
    private String shi;
    /*** 所在县*/
    private String xian;
    /*** 所在行*/
    private String hang;

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

    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
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

    public String getYinCode() {
        return yinCode;
    }

    public void setYinCode(String yinCode) {
        this.yinCode = yinCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    public String getWangCode() {
        return wangCode;
    }

    public void setWangCode(String wangCode) {
        this.wangCode = wangCode;
    }

    public String getWangType() {
        return wangType;
    }

    public void setWangType(String wangType) {
        this.wangType = wangType;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }

    public String getXian() {
        return xian;
    }

    public void setXian(String xian) {
        this.xian = xian;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng;
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
            .append("email", getEmail())
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
            .append("yinCode", getYinCode())
            .append("address", getAddress())
            .append("deptType", getDeptType())
            .append("wangCode", getWangCode())
            .append("wangType", getWangType())
            .append("sheng", getSheng())
            .append("shi", getShi())
            .append("xian", getXian())
            .append("hang", getHang())
            .toString();
    }
}
