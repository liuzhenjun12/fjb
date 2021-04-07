package com.ruoyi.common.core.domain.baoming;

import java.io.Serializable;

public class Jiwei implements Serializable {
    private Integer  id;
    private Integer resTotal;
    private String certificateType;
    private String date;

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
}
