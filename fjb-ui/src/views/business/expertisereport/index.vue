<template>
  <div class="app-container">
    <div class="form_kuand" >
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px" class="form_input">
      <el-form-item label="假币币种" prop="ccCurrency">
        <el-select
          v-model="queryParams.ccCurrency"
          placeholder="请选择假币币种"
          clearable
          size="small"
          style="width: 150px"
        >
          <el-option
            v-for="dict in ccCurrencyOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="假币劵别" prop="ccDenomination">
        <el-select
          v-model="queryParams.ccDenomination"
          placeholder="请选择假币卷别"
          clearable
          size="small"
          style="width: 150px"
        >
          <el-option
            v-for="dict in ccDenominationOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="假币版别" prop="ccSeries">
        <el-select
          v-model="queryParams.ccSeries"
          placeholder="请选择假币版别"
          clearable
          size="small"
          style="width: 150px"
        >
          <el-option
            v-for="dict in ccSeriesOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="最终复核" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="请选择复核状态"
          clearable
          size="small"
          style="width: 150px"
        >
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="冠字号码" prop="serialNumber">
        <el-input
          v-model="queryParams.serialNumber"
          placeholder="请输入收缴冠字号码"
          clearable
          size="small"
          style="width: 150px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="鉴定单位" prop="deptId">
        <el-cascader
          v-model="queryParams.deptId"
          placeholder="请选择鉴定单位"
          :options="deptidOptions"
          :props="{ checkStrictly: true }"
          size="small"
          style="width: 150px"
          :show-all-levels="false"
          @change="handleZong"
          ref="cascaderUser"
          clearable
        >
        </el-cascader>
      </el-form-item>

      <el-form-item label="收缴日期">
        <el-date-picker
          v-model="dateRange"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          align="right"
          unlink-panels
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :picker-options="pickerOptions"
        ></el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>

    </el-form>
    </div>
    <div class="sanguang">
    <el-row :gutter="10" class="gao">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:expertisereport:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:expertisereport:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:expertisereport:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:expertisereport:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    </div>
    <el-table v-loading="loading" :data="expertisereportList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="鉴定编号" align="center" prop="number" />
      <el-table-column label="机构id" align="center" prop="deptId" />
      <el-table-column label="假币数量" align="center" prop="amount" />
      <el-table-column label="假币总额" align="center" prop="totalamount" />
      <el-table-column label="鉴定日期" align="center" prop="identifydate" />
      <el-table-column label="币种id" align="center" prop="ccCurrency" />
      <el-table-column label="假币劵别" align="center" prop="ccDenomination" />
      <el-table-column label="假币版别" align="center" prop="ccSeries" />
      <el-table-column label="鉴定结果" align="center" prop="result" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:expertisereport:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:expertisereport:remove']"
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
  </div>
</template>

<script>
import { listExpertisereport, getExpertisereport, delExpertisereport, addExpertisereport, updateExpertisereport, exportExpertisereport } from "@/api/business/expertisereport";
import { Cascadeselect } from "@/api/system/dept";

export default {
  name: "Expertisereport",
  data() {
    return {
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
      // 日期范围
      dateRange: [],
      //币种
      ccCurrencyOptions:[],
      //卷别
      ccDenominationOptions:[],
      //版别
      ccSeriesOptions:[],
      //复核状态
      statusOptions:[],
      //鉴定单位
      deptidOptions:undefined,
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
      // 假币鉴定表格数据
      expertisereportList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deptId: null,
        identifydate: null,
        ccCurrency: null,
        ccDenomination: null,
        ccSeries: null,
        ccMadeway: null,
        status: null,
        serialNumber: null
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
    this.getCascadeselect();
    this.getDicts("sys_money_type").then(response => {
      this.ccCurrencyOptions = response.data;
    });
    this.getDicts("sys_denomination").then(response => {
      this.ccDenominationOptions = response.data;
    });
    this.getDicts("sys_money_version").then(response => {
      this.ccSeriesOptions = response.data;
    });
    this.getDicts("sys_money_status").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询假币鉴定列表 */
    getList() {
      this.loading = true;
      listExpertisereport(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.expertisereportList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //机构级联
    getCascadeselect(){
      let obj={isJianDing:'Y'}
      Cascadeselect(obj).then(response => {
        this.deptidOptions = response.data;
      });
    },
    /** 选择机构 */
    handleZong(value){
      if(value){
        this.queryParams.deptId =value[value.length-1];
      }else {
        this.queryParams.deptId = null;
      }
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        number: null,
        deptId: null,
        userId: null,
        amount: null,
        totalamount: null,
        identifyname: null,
        checkupname: null,
        identifydate: null,
        holderName: null,
        holderTelephone: null,
        holderIdcard: null,
        ccCurrency: null,
        ccDenomination: null,
        ccSeries: null,
        ccMadeway: null,
        ccFeature: null,
        identifyreportFile: null,
        originalcffi: null,
        originalresult: null,
        result: null,
        resultdetails: null,
        status: "0",
        remark: null,
        delFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        imgList: null,
        groupNumber: null
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.$router.push("/expertisereport/addPage");
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      const id = row.id || this.ids
      this.$router.push("/expertisereport/updatePage/"+id);
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateExpertisereport(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addExpertisereport(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除假币鉴定编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delExpertisereport(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有假币鉴定数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportExpertisereport(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
