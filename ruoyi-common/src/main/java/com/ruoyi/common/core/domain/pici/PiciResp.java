package com.ruoyi.common.core.domain.pici;

import java.io.Serializable;
import java.util.List;

public class PiciResp implements Serializable {
    private String result;
    private String resultCode;
    private List<PiciList> resultList;
    private String resultMsg;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public List<PiciList> getResultList() {
        return resultList;
    }

    public void setResultList(List<PiciList> resultList) {
        this.resultList = resultList;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
