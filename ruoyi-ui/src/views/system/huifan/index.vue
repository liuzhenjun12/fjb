<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="客户名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入客户名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号码" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入手机号码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading"
              :data="customerList"
              width="100%"
              :default-sort = "{prop: 'sendTime', order: 'descending'}"
    >
      <el-table-column label="客户名称"  min-width="10%"   align="center" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <router-link :to="'/customer/product/data/' + scope.row.id" class="link-type">
            <span>{{ scope.row.name }}</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="客户手机" min-width="10%"  align="center"  prop="phone" />
      <el-table-column label="客户行业" min-width="10%"  align="center"  prop="industry" :formatter="hangye" />
      <el-table-column label="购买次数" min-width="10%"  align="center"  prop="cishu" :formatter="cis" />
      <el-table-column label="总成交金额" min-width="10%"  align="center"  prop="money" :formatter="yuan" />
      <el-table-column label="总购买数量" min-width="10%"  align="center"  prop="num" :formatter="ge" />
      <el-table-column label="客户省份" min-width="10%"  align="center"  prop="sheng" />
      <el-table-column label="客户城市" min-width="10%"  align="center"  prop="shi" />
      <el-table-column label="创建时间" min-width="12%"  align="center"  sortable prop="createTime" :sort-method="paixu" >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" min-width="12%" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-phone-outline"
            @click="handleHuifang(scope.row)"
            v-hasPermi="['system:customer:remove']"
          >回访客户记录</el-button>
        </template>
      </el-table-column>
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
  import { listCustomer } from "@/api/system/customer/type";

  export default {
    name: "Customer",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 客户管理表格数据
        customerList: [],
        // 弹出层标题
        title: "",
        // 产品类型字典
        typeOptions: [],
        //行业类型字典
        industryOptions: [],
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          name: null,
          phone: null,
          address: null,
          industry: null,
          money: null,
          num: null,
          type: null,
          img: null,
          sendTime: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
        }
      };
    },
    created() {
      this.getList();
      this.getDicts("sys_product_type").then(response => {
        this.typeOptions = response.data;
      });
      this.getDicts("sys_industry_type").then(response => {
        this.industryOptions = response.data;
      });
    },
    methods: {
      /** 查询客户管理列表 */
      getList() {
        this.loading = true;
        listCustomer(this.queryParams).then(response => {
          this.customerList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      paixu(a,b){
        var str1 = a.sendTime.replace(/[^\d]/g,'/');
        var str2 = b.sendTime.replace(/[^\d]/g,'/');
        var at=Date.parse(str1)
        var bt=Date.parse(str2)
        if(at>bt){
          return -1
        }else {
          return 1
        }
      },
      // 行业字典翻译
      hangye(row, column){
        return this.selectDictLabel(this.industryOptions, row.industry);
      },
      cis(row, column){return row.cishu+'次'},
      yuan(row, column){return row.money+'元'},
      ge(row, column){return row.num+'个'},
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: null,
          name: null,
          phone: null,
          sheng:null,
          shi:null,
          address: null,
          industry: null,
          money: null,
          num: null,
          type: null,
          img: null,
          createTime: null,
          updateTime: null,
          createBy: null,
          updateBy: null,
          delFlag: null,
          remark: null,
          sendTime: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      handleHuifang(row){
        this.$router.push("/customer/huifan/data/"+row.id);
      }
    }
  };
</script>
