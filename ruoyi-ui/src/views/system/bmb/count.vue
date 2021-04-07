<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="考试年份" prop="name">
        <el-date-picker clearable size="small" style="width: 200px"
                        v-model="queryParams.name"
                        type="year"
                        value-format="yyyy"
                        placeholder="选择考试年份">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table
      v-loading="loading"
      :data="bmbList"
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
</template>
<script>
  import { countList } from "@/api/system/bmb";
  export default {
    name: 'count',
    data() {
      return {
        // 显示搜索条件
        showSearch: true,
        // 报名表格数据
        bmbList: [],
        total: 0,
        queryParams: {
          name:null,
          pageNum: 1,
          pageSize: 10
        }
      }
    },
    created() {
      var shijian=new Date().getFullYear()+'';
      this.queryParams.name=shijian;
      this.getList();
    },
    methods: {
      /** 查询报名列表 */
      getList() {
        console.log(JSON.stringify(this.queryParams))
        this.loading = true;
        countList(this.queryParams).then(response => {
          this.bmbList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      handleQuery(){
        this.queryParams.pageNum = 1;
        this.getList();
      },
      resetQuery(){

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
