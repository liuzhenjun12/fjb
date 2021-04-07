<template>
  <div class="dashboard-editor-container">
    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="12">
        <div class="chart-wrapper">
          <china-chart />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="12">
        <div class="chart-wrapper">
          <el-table
            v-loading="loading"
            :data="bmbList"
            :row-style="rowStyle"
            :header-cell-style="headerStyle"
          >
            <el-table-column label="机构名称" min-width="20%" align="center" prop="jigou" />
            <el-table-column label="测评人数" min-width="15%" align="center" prop="shi" />
            <el-table-column label="理论合格" min-width="15%" align="center" prop="liluenshi" />
            <el-table-column label="实操合格" min-width="15%" align="center" prop="shichaoshi" />
            <el-table-column label="缺考人数" min-width="15%" align="center" prop="liluenque" />
            <el-table-column label="考试日期" min-width="20%" align="center" prop="kaoshitime" />
          </el-table>
            <pagination
              v-show="total>0"
              :total="total"
              :page.sync="queryParams.pageNum"
              :limit.sync="queryParams.pageSize"
              @pagination="getList"
            />
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import ChinaChart from './dashboard/ChinaChart'
import { countList } from "@/api/system/bmb";
export default {
  name: 'Index',
  components: {
    ChinaChart
  },
  data() {
    return {
      // 报名表格数据
      bmbList: [],
      total: 0,
      queryParams: {
        pageNum: 1,
        pageSize: 10
      }
    }
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询报名列表 */
    getList() {
      this.loading = true;
      countList(this.queryParams).then(response => {
        this.bmbList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    rowStyle({ row, rowIndex}){
      let stylejson={}
      stylejson.color='#097bba'
      return stylejson
    },
    headerStyle({row, rowIndex}){
      let stylejson={}
      stylejson.color='#025871'
      return stylejson
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  position: relative;
  .chart-wrapper {
    background: #fff;
  }
}

@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
