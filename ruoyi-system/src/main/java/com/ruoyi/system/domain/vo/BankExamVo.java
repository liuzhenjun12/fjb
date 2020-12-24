package com.ruoyi.system.domain.vo;

/**
 * 考试日期、机位
 */
public class BankExamVo {
    /**
     * 考试类型
     */
    private String certificateType;
    /**
     * 考试日期
     */
    private String date;
    /**
     * id
     */
    private Integer id;
    /**
     * 剩余机位
     */
    private Integer resTotal;

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResTotal() {
        return resTotal;
    }

    public void setResTotal(Integer resTotal) {
        this.resTotal = resTotal;
    }
}
