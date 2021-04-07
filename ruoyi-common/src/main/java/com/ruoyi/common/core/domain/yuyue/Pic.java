package com.ruoyi.common.core.domain.yuyue;

import java.io.Serializable;

public class Pic implements Serializable {
    private String picChange;
    private String picChangeContentType;
    private String picChangeName;
    private Pres resp;

    public String getPicChange() {
        return picChange;
    }

    public void setPicChange(String picChange) {
        this.picChange = picChange;
    }

    public String getPicChangeContentType() {
        return picChangeContentType;
    }

    public void setPicChangeContentType(String picChangeContentType) {
        this.picChangeContentType = picChangeContentType;
    }

    public String getPicChangeName() {
        return picChangeName;
    }

    public void setPicChangeName(String picChangeName) {
        this.picChangeName = picChangeName;
    }

    public Pres getResp() {
        return resp;
    }

    public void setResp(Pres resp) {
        this.resp = resp;
    }
}
