package com.ruoyi.common.core.domain.result;

import java.io.Serializable;

public class Re implements Serializable {
    private Resp resp;
    private String roleID;

    public Resp getResp() {
        return resp;
    }

    public void setResp(Resp resp) {
        this.resp = resp;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
}
