package com.ruoyi.common.core.domain.yuyue;

import java.io.Serializable;
import java.util.List;

public class Pres implements Serializable {
    private Integer result;
    private String resultCode;
    private String resultMsg;
    private List<PicList> resultList;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public List<PicList> getResultList() {
        return resultList;
    }

    public void setResultList(List<PicList> resultList) {
        this.resultList = resultList;
    }
}
