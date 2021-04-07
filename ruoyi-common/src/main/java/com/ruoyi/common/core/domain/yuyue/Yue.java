package com.ruoyi.common.core.domain.yuyue;

import java.io.Serializable;
import java.util.List;

public class Yue implements Serializable {
    private List<YueList> rows;

    public List<YueList> getRows() {
        return rows;
    }

    public void setRows(List<YueList> rows) {
        this.rows = rows;
    }
}
