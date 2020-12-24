package com.ruoyi.common.core.domain;

public class PostResult {
    private String reason;//结果描述
    private String result;//failed或者success

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "PostResult{" +
                "reason='" + reason + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
