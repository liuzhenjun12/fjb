package com.ruoyi.common.core.domain.chengji;

import java.io.Serializable;

public class ChengjiResult implements Serializable {
    private Integer result;
    private String resultCode;
    private String resultList;
    private String resultMsg;

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

    public String getResultList() {
        return resultList;
    }

    public void setResultList(String resultList) {
        this.resultList = resultList;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
