package com.ruoyi.common.core.domain.zuce;

import java.io.Serializable;
import java.util.List;

public class Zrow implements Serializable {
    private List<ZcRow> rows;
    private Integer total;

    public List<ZcRow> getRows() {
        return rows;
    }

    public void setRows(List<ZcRow> rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
