package com.ruoyi.common.core.domain.yuyue;

import java.io.Serializable;
import java.util.List;

public class Shi implements Serializable {
    private List<ShiList> rows;

    public List<ShiList> getRows() {
        return rows;
    }

    public void setRows(List<ShiList> rows) {
        this.rows = rows;
    }
}
