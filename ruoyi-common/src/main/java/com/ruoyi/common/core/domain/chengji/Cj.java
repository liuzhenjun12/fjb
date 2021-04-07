package com.ruoyi.common.core.domain.chengji;

import java.io.Serializable;
import java.util.List;

public class Cj implements Serializable {
    private List<ChengjiList> rows;

    public List<ChengjiList> getRows() {
        return rows;
    }

    public void setRows(List<ChengjiList> rows) {
        this.rows = rows;
    }
}
