package com.ruoyi.common.core.domain;

/**
 * 提交测评中心注册内容
 */
public class SubmitVo {
    /** 图片地址**/
    private String imgShow;
    /** 省机构代码**/
    private String jigou;
    /** 用户名称**/
    private String username;
    /** 性别**/
    private String sex;
    /** 考试类型**/
    private String examtype;
    /** 身份证类型**/
    private String cardtype;
    /** 身份证号码**/
    private String idcard;
    /** 考试日期**/
    private String examdateText;
    /** 考试日期整数**/
    private Integer examdate;
    /** 考试日期**/
    private String pbexamdateText;
    /** 该测评不需要选择场次**/
    private String sessionText;
    /** 0**/
    private String sessionID;
    /** 考试类型**/
    private String certificateType;
    /** 证书编号**/
    private String certificateID;
    /** 银行机构代码**/
    private String bankno;
    /** 省**/
    private String province;
    /** 城市**/
    private String city;
    /** 培训单位**/
    private String peopleBankName;
    /** 银行名称**/
    private String bank;
    /** 邮箱**/
    private String email;
    /** **/
    private String qq;
    /** 手机**/
    private String phone;

    public String getImgShow() {
        return imgShow;
    }

    public void setImgShow(String imgShow) {
        this.imgShow = imgShow;
    }

    public String getJigou() {
        return jigou;
    }

    public void setJigou(String jigou) {
        this.jigou = jigou;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getExamtype() {
        return examtype;
    }

    public void setExamtype(String examtype) {
        this.examtype = examtype;
    }

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

    public String getExamdateText() {
        return examdateText;
    }

    public void setExamdateText(String examdateText) {
        this.examdateText = examdateText;
    }

    public Integer getExamdate() {
        return examdate;
    }

    public void setExamdate(Integer examdate) {
        this.examdate = examdate;
    }

    public String getPbexamdateText() {
        return pbexamdateText;
    }

    public void setPbexamdateText(String pbexamdateText) {
        this.pbexamdateText = pbexamdateText;
    }

    public String getSessionText() {
        return sessionText;
    }

    public void setSessionText(String sessionText) {
        this.sessionText = sessionText;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateID() {
        return certificateID;
    }

    public void setCertificateID(String certificateID) {
        this.certificateID = certificateID;
    }

    public String getBankno() {
        return bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno;
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



    @Override
    public String toString() {
        return "SubmitVo{" +
                "imgShow='" + imgShow + '\'' +
                ", jigou='" + jigou + '\'' +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", examtype='" + examtype + '\'' +
                ", cardtype='" + cardtype + '\'' +
                ", idcard='" + idcard + '\'' +
                ", examdateText='" + examdateText + '\'' +
                ", examdate='" + examdate + '\'' +
                ", pbexamdateText='" + pbexamdateText + '\'' +
                ", sessionText='" + sessionText + '\'' +
                ", sessionID='" + sessionID + '\'' +
                ", certificateType='" + certificateType + '\'' +
                ", certificateID='" + certificateID + '\'' +
                ", bankno='" + bankno + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", peopleBankName='" + peopleBankName + '\'' +
                ", bank='" + bank + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
