package com.fjb.common.core.text;

import java.io.Serializable;

public class UserSql implements Serializable {
    private int id;// 用户ID
    private String username;// 登录账号
    private String password;// 密码
    private String pwdupdatedate;// 密码
    private int provinceid;// 省份code
    private int cityid;// 地级市code
    private int townid;// 县级市code
    private String place;// 负责区域
    private int banktypeid; //银行类型，关联banktype表的id
    private String banktype; //银行类型，关联banktype表的id
    private String bankname;
    private int roleid;// 所属角色ID
    private String rolename;
    private boolean isidentify; //是否为鉴定机构，0或false表示不是鉴定单位，1或true表示鉴定单位
    private String mark; //用户备注
    private String seal_coding; //印章编号
    private String branchname; //网点全称
    private String branchaddress; //网点地址
    private String branchtype; //网点全称
    private String branchcode; //网点全称
    private String branchfirm; //网点全称
    private String branchlongitude; //网点全称
    private String branchlatitude; //网点全称
    private String contact_number;//网点联系电话
    private int isdelete;// 删除状态（1正常2已删除）

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPwdupdatedate() {
        return pwdupdatedate;
    }

    public void setPwdupdatedate(String pwdupdatedate) {
        this.pwdupdatedate = pwdupdatedate;
    }

    public int getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(int provinceid) {
        this.provinceid = provinceid;
    }

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    public int getTownid() {
        return townid;
    }

    public void setTownid(int townid) {
        this.townid = townid;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getBanktypeid() {
        return banktypeid;
    }

    public void setBanktypeid(int banktypeid) {
        this.banktypeid = banktypeid;
    }

    public String getBanktype() {
        return banktype;
    }

    public void setBanktype(String banktype) {
        this.banktype = banktype;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public boolean isIsidentify() {
        return isidentify;
    }

    public void setIsidentify(boolean isidentify) {
        this.isidentify = isidentify;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getSeal_coding() {
        return seal_coding;
    }

    public void setSeal_coding(String seal_coding) {
        this.seal_coding = seal_coding;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getBranchaddress() {
        return branchaddress;
    }

    public void setBranchaddress(String branchaddress) {
        this.branchaddress = branchaddress;
    }

    public String getBranchtype() {
        return branchtype;
    }

    public void setBranchtype(String branchtype) {
        this.branchtype = branchtype;
    }

    public String getBranchcode() {
        return branchcode;
    }

    public void setBranchcode(String branchcode) {
        this.branchcode = branchcode;
    }

    public String getBranchfirm() {
        return branchfirm;
    }

    public void setBranchfirm(String branchfirm) {
        this.branchfirm = branchfirm;
    }

    public String getBranchlongitude() {
        return branchlongitude;
    }

    public void setBranchlongitude(String branchlongitude) {
        this.branchlongitude = branchlongitude;
    }

    public String getBranchlatitude() {
        return branchlatitude;
    }

    public void setBranchlatitude(String branchlatitude) {
        this.branchlatitude = branchlatitude;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public int getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserSql{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", pwdupdatedate='").append(pwdupdatedate).append('\'');
        sb.append(", provinceid=").append(provinceid);
        sb.append(", cityid=").append(cityid);
        sb.append(", townid=").append(townid);
        sb.append(", place='").append(place).append('\'');
        sb.append(", banktypeid=").append(banktypeid);
        sb.append(", banktype='").append(banktype).append('\'');
        sb.append(", bankname='").append(bankname).append('\'');
        sb.append(", roleid=").append(roleid);
        sb.append(", rolename='").append(rolename).append('\'');
        sb.append(", isidentify=").append(isidentify);
        sb.append(", mark='").append(mark).append('\'');
        sb.append(", seal_coding='").append(seal_coding).append('\'');
        sb.append(", branchname='").append(branchname).append('\'');
        sb.append(", branchaddress='").append(branchaddress).append('\'');
        sb.append(", branchtype='").append(branchtype).append('\'');
        sb.append(", branchcode='").append(branchcode).append('\'');
        sb.append(", branchfirm='").append(branchfirm).append('\'');
        sb.append(", branchlongitude='").append(branchlongitude).append('\'');
        sb.append(", branchlatitude='").append(branchlatitude).append('\'');
        sb.append(", contact_number='").append(contact_number).append('\'');
        sb.append(", isdelete=").append(isdelete);
        sb.append('}');
        return sb.toString();
    }
}
