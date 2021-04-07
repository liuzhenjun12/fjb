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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:customer:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:customer:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:customer:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:customer:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading"
              :data="customerList"
              width="100%"
              @selection-change="handleSelectionChange"
              :default-sort = "{prop: 'sendTime', order: 'descending'}"
    >
      <el-table-column type="selection" min-width="5%"  align="center" />
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
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:customer:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:customer:remove']"
          >删除</el-button>
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

    <!-- 添加或修改客户管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入客户名称" />
        </el-form-item>
        <el-form-item label="手机号码" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号码" />
        </el-form-item>
        <el-form-item label="省份" prop="sheng">
          <el-input v-model="form.sheng" placeholder="请输入省份" />
        </el-form-item>
        <el-form-item label="城市" prop="shi">
          <el-input v-model="form.shi" placeholder="请输入城市" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="行业" prop="industry">
          <el-select v-model="form.industry" placeholder="请选择客户行业" style="width: 100%">
            <el-option
              v-for="dict in industryOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="描述" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入描述" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCustomer, getCustomer, delCustomer, addCustomer, updateCustomer, exportCustomer } from "@/api/system/customer/type";

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
    getSummaries(param){
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if(index==0||index==2||index==1||index==6||index==7||index==8){
          sums[index] = '';
          return;
        }
        if (index === 3) {
          sums[index] = '合计';
          return;
        }
        const values = data.map(item => Number(item[column.property]));
        if (!values.every(value => isNaN(value))) {
          sums[index] = values.reduce((prev, curr) => {
            const value = Number(curr);
            if (!isNaN(value)) {
              return prev + curr;
            } else {
              return prev;
            }
          }, 0);
          if(index==4) {
            sums[index] += ' 元';
          }else {
            sums[index] += ' 个';
          }
        } else {
          sums[index] = 'N/A';
        }
      });

      return sums;
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
    /** 跳转到回访页面 */
    handleHuifan:function(){

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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加客户管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCustomer(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改客户管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCustomer(this.form).then(response => {
              this.open = false;
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.getList();
              }else {
                this.msgError(response.msg)
              }
            });
          } else {
            addCustomer(this.form).then(response => {
              this.open = false;
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.getList();
              }else {
                this.msgError(response.msg)
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除客户管理编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCustomer(ids);
        }).then(response => {
          this.getList();
        if(response.code==200){
          this.msgSuccess("删除成功");
        }else {
          this.msgError(response.msg);
        }
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有客户管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCustomer(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
