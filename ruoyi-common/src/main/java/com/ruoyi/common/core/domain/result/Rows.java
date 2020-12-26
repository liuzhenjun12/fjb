package com.ruoyi.common.core.domain.result;

import java.io.Serializable;
import java.util.List;

public class Rows implements Serializable {
   private List<Row> rows;
   private Integer total;

   public List<Row> getRows() {
      return rows;
   }

   public void setRows(List<Row> rows) {
      this.rows = rows;
   }

   public Integer getTotal() {
      return total;
   }

   public void setTotal(Integer total) {
      this.total = total;
   }
}
