package com.ruoyi.common.core.domain.yuyue;

import java.util.List;

public class YueTa {
    private List<ShiList> sessionAM;//上午
    private List<ShiList> sessionPM;//下午
    private List<ShiList> sessionNM;//晚上

    public List<ShiList> getSessionAM() {
        return sessionAM;
    }

    public void setSessionAM(List<ShiList> sessionAM) {
        this.sessionAM = sessionAM;
    }

    public List<ShiList> getSessionPM() {
        return sessionPM;
    }

    public void setSessionPM(List<ShiList> sessionPM) {
        this.sessionPM = sessionPM;
    }

    public List<ShiList> getSessionNM() {
        return sessionNM;
    }

    public void setSessionNM(List<ShiList> sessionNM) {
        this.sessionNM = sessionNM;
    }
}
