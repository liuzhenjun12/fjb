package com.ruoyi.common.core.domain;

/**
 * 提交照片VO
 */
public class PhoteSubmitVo {
    private String cardtype;//身份证类型
    private String idcard;//身份证号码
    private String bankname;//银行名称
    private String name;//用户姓名
    private String province;//省
    private String city;//市
    private String jigou;//市机构代码
    private String peopleBankNo;//
    private String peopleBankName;
    private String bank;//行机构代码
    private String bankNumber;//行机构代码
    private String bankno;//行机构代码
    private String examdate;//日期id
    private String pbexamdate;//日期id
    private String session;
    private String sex;
    private String certificateID;//证书编码
    private String examtype;//临柜
    private String email;
    private String qq;
    private String  phone;
    private String username;//身份证号

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getJigou() {
        return jigou;
    }

    public void setJigou(String jigou) {
        this.jigou = jigou;
    }

    public String getPeopleBankNo() {
        return peopleBankNo;
    }

    public void setPeopleBankNo(String peopleBankNo) {
        this.peopleBankNo = peopleBankNo;
    }

    public String getPeopleBankName() {
        return peopleBankName;
    }

    public void setPeopleBankName(String peopleBankName) {
        this.peopleBankName = peopleBankName;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getBankno() {
        return bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno;
    }

    public String getExamdate() {
        return examdate;
    }

    public void setExamdate(String examdate) {
        this.examdate = examdate;
    }

    public String getPbexamdate() {
        return pbexamdate;
    }

    public void setPbexamdate(String pbexamdate) {
        this.pbexamdate = pbexamdate;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCertificateID() {
        return certificateID;
    }

    public void setCertificateID(String certificateID) {
        this.certificateID = certificateID;
    }

    public String getExamtype() {
        return examtype;
    }

    public void setExamtype(String examtype) {
        this.examtype = examtype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    @Override
    public String toString() {
        return "PhoteSubmitVo{" +
                ", cardtype='" + cardtype + '\'' +
                ", idcard='" + idcard + '\'' +
                ", bankname='" + bankname + '\'' +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", jigou='" + jigou + '\'' +
                ", peopleBankNo='" + peopleBankNo + '\'' +
                ", peopleBankName='" + peopleBankName + '\'' +
                ", bank='" + bank + '\'' +
                ", bankNumber='" + bankNumber + '\'' +
                ", bankno='" + bankno + '\'' +
                ", examdate='" + examdate + '\'' +
                ", pbexamdate='" + pbexamdate + '\'' +
                ", session='" + session + '\'' +
                ", sex='" + sex + '\'' +
                ", certificateID='" + certificateID + '\'' +
                ", examtype='" + examtype + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
