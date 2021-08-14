package com.fjb.common.core.text;

import java.util.List;

public class UserList {
    private Long total;
    private List<UserSql> result;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<UserSql> getResult() {
        return result;
    }

    public void setResult(List<UserSql> result) {
        this.result = result;
    }
}
