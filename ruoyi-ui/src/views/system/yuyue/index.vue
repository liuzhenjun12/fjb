<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="考试时间" prop="kaoshiTime">
        <el-date-picker clearable size="small" style="width: 200px"
                        v-model="queryParams.kaoshiTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择考试日期">
        </el-date-picker>
      </el-form-item>

      <el-form-item label="机构名称" prop="bankName">
        <el-cascader
          v-model="queryParams.bankNo"
          placeholder="请选择地区名称"
          :options="jigouOptions"
          size="small"
          :show-all-levels="false"
          @change="handleChange"
          clearable
        >
        </el-cascader>
      </el-form-item>
      <el-form-item label="考试类型" prop="certifType">
        <el-select v-model="queryParams.certifType" placeholder="请选择考试类型" clearable size="small">
          <el-option
            v-for="item in kaoOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search"  @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh"  @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:yuyue:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:yuyue:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:yuyue:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="yuyueList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" min-width="5%" align="center" />
      <el-table-column label="机构名称" min-width="10%" align="center" prop="bankName" />
      <el-table-column label="机构代码" min-width="10%" align="center" prop="bankNo" />
      <el-table-column label="考试类型" min-width="10%" align="center" prop="certifType" />
      <el-table-column label="机位数量" min-width="10%" align="center" prop="countNum" />
      <el-table-column label="考试时间" min-width="15%" align="center" prop="kaoshiTime" />
      <el-table-column label="报名时间" min-width="20%" align="center" prop="terminalTime" />
      <el-table-column label="审核时间" min-width="20%" align="center" prop="apperTime" />
      <el-table-column label="操作" min-width="10%" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:yuyue:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:yuyue:remove']"
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

    <!-- 添加或修改预约对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="考试时间" prop="kaoshiTime">
          <el-input v-model="form.kaoshiTime" placeholder="请输入考试时间" />
        </el-form-item>
        <el-form-item label="机构名称" prop="bankName">
          <el-input v-model="form.bankName" placeholder="请输入机构名称" />
        </el-form-item>
        <el-form-item label="机构代码" prop="bankNo">
          <el-input v-model="form.bankNo" placeholder="请输入机构代码" />
        </el-form-item>
        <el-form-item label="日期id" prop="examId">
          <el-input v-model="form.examId" placeholder="请输入考试日期id" />
        </el-form-item>
        <el-form-item label="报名时间" prop="terminalTime">
          <el-input v-model="form.terminalTime" placeholder="请输入报名结束时间" />
        </el-form-item>
        <el-form-item label="审核日期" prop="apperTime">
          <el-input v-model="form.apperTime" placeholder="请输入审核关闭日期" />
        </el-form-item>
        <el-form-item label="考试类型" prop="certifType" >
          <el-select v-model="form.certifType" placeholder="请选择考试类型" style="width: 100%">
            <el-option
              v-for="item in kaoOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="机位数量" prop="countNum">
          <el-input v-model="form.countNum" placeholder="请输入机位数量" />
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
import { listYuyue, getYuyue, delYuyue, addYuyue, updateYuyue, exportYuyue } from "@/api/system/yuyue";
import jigouList from '@/api/system/jigou.json'
export default {
  name: "Yuyue",
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
      // 预约表格数据
      yuyueList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        kaoshiTime: null,
        bankNo: null,
        certifType: null,
      },
      kaoOptions:[
        {
          value: '首次考试',
          label: '首次考试'
        },
        {
          value: '证书审验',
          label: '证书审验'
        }
      ],
      jigouOptions:jigouList
      ,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询预约列表 */
    getList() {
      this.loading = true;
      listYuyue(this.queryParams).then(response => {
        this.yuyueList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        kaoshiTime: null,
        kaoshiName: null,
        showTime: null,
        pbeExamid: null,
        terminalTime: null,
        bankName: null,
        bankNo: null,
        examId: null,
        apperTime: null,
        certifType: null,
        createTime: null,
        updateTime: null,
        createBy: null,
        updateBy: null,
        remark: null,
        delFlag: null,
        countNum:null
      };
      this.resetForm("form");
    },
    /** 搜索选择机构 */
    handleChange(value) {
      if(value){
        console.log('1')
        this.queryParams.bankNo =value[1]
      }else {
        console.log('2')
        this.queryParams.bankNo = null;
      }
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.queryParams.bankNo = null;
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
      this.title = "添加预约";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getYuyue(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改预约";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateYuyue(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addYuyue(this.form).then(response => {
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
      this.$confirm('是否确认删除预约编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delYuyue(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有预约数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportYuyue(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
