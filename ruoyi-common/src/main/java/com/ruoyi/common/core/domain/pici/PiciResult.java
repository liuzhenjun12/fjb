package com.ruoyi.common.core.domain.pici;

import java.io.Serializable;

public class PiciResult implements Serializable {
    private String picChange;
    private String picChangeContentType;
    private String picChangeName;
    private PiciResp resp;

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

    public PiciResp getResp() {
        return resp;
    }

    public void setResp(PiciResp resp) {
        this.resp = resp;
    }
}
