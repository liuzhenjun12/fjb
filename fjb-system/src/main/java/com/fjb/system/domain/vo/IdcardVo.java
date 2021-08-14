package com.fjb.system.domain.vo;

/**
 * 身份证验证
 */
public class IdcardVo {
    /**
     * 银行名称
     */
    private String BusinessBankName;
    /**
     * 证书编号
     */
    private String CertificateCode;
    /**
     * 通过身份证自动带出的姓名
     */
    private String Name;
    /**
     *银行机构代码
     */
    private String alternateId;
    /**
     * 城市
     */
    private String city;
    /**
     * 省
     */
    private String province;

    public String getBusinessBankName() {
        return BusinessBankName;
    }

    public void setBusinessBankName(String businessBankName) {
        BusinessBankName = businessBankName;
    }

    public String getCertificateCode() {
        return CertificateCode;
    }

    public void setCertificateCode(String certificateCode) {
        CertificateCode = certificateCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAlternateId() {
        return alternateId;
    }

    public void setAlternateId(String alternateId) {
        this.alternateId = alternateId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
