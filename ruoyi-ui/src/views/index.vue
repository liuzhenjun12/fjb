<template>
  <div class="dashboard-editor-container">

    <panel-group  />

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <china-xiao-chart />
    </el-row>

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <china-chart />
    </el-row>

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <el-form :model="queryParams" ref="queryForm" :inline="true"  label-width="68px">
        <el-form-item label="搜索年份" prop="nian">
          <el-date-picker clearable size="small" style="width: 200px"
                          v-model="queryParams.nian"
                          type="year"
                          value-format="yyyy"
                          placeholder="选择考试年份">
          </el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="cyan" icon="el-icon-search" size="mini" @click="getXianData">搜索</el-button>
        </el-form-item>
      </el-form>
    </el-row>

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <line-chart :chart-data="lineChartData" />
    </el-row>

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <china-xiao-xian :chart-data="lineChartData" />
    </el-row>

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <line-huifang :chart-data="huifanCharData" />
    </el-row>

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <line-baoming :chart-data="baoChartData" />
    </el-row>


    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <raddar-chart :chart-data="shengCharData" />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper" >
          <pie-chart :chart-data="hangChartData" />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <bar-chart :chart-data="maiCharData" />
        </div>
      </el-col>
    </el-row>


  </div>
</template>

<script>
  import PanelGroup from './dashboard/PanelGroup'
  import LineChart from './dashboard/LineChart'
  import RaddarChart from './dashboard/RaddarChart'
  import PieChart from './dashboard/PieChart'
  import BarChart from './dashboard/BarChart'
  import ChinaXiaoChart from './dashboard/ChinaXiaoChart'
  import ChinaXiaoXian from './dashboard/ChinaXiaoXian'
  import LineBaoming from './dashboard/LineBaoming'
  import ChinaChart from './dashboard/ChinaChart'
  import LineHuifang from './dashboard/LineHuifang'
  import { countOrderXian,countBaoXian,countQy,countSheng,countMai,countHuifanXian } from "@/api/system/bmb";
  export default {
    name: 'Index',
    components: {
      PanelGroup,
      LineChart,
      RaddarChart,
      PieChart,
      BarChart,
      ChinaChart,
      ChinaXiaoChart,
      ChinaXiaoXian,
      LineBaoming,
      LineHuifang
    },
    data() {
      return {
        lineChartData:{},
        baoChartData:{},
        hangChartData:{},
        shengCharData:{},
        maiCharData:{},
        huifanCharData:{},
        queryParams:{
          nian:null
        }
      }
    },
    created() {
      var shijian=new Date().getFullYear()+'';
      this.queryParams.nian=shijian;
      this.getXianData();
      this.getHangye();
      this.getShengBao();
      this.getGoumai();
    },
    methods: {
      getXianData(){
        countOrderXian(this.queryParams.nian).then(response => {
          this.lineChartData=response.data;
        });
        countBaoXian(this.queryParams.nian).then(response => {
          this.baoChartData=response.data;
        });
        countHuifanXian(this.queryParams.nian).then(response => {
          this.huifanCharData=response.data;
        });
      },
      getHangye(){
        countQy().then(response => {
          this.hangChartData=response;
        });
      },
      getShengBao(){
        countSheng().then(response => {
          this.shengCharData=response;
        });
      },
      getGoumai(){
        countMai().then(response => {
          this.maiCharData=response;
        });
      },
    }
  }
</script>

<style lang="scss" scoped>
  .dashboard-editor-container {
    padding: 16px 32px 32px 32px;
    background-color: rgb(240, 242, 245);
    position: relative;

    .chart-wrapper {
      background: #fff;
      padding: 16px 16px 0;
      margin-bottom: 32px;
    }
  }

  @media (max-width:1024px) {
    .chart-wrapper {
      padding: 8px;
    }
  }
</style>
