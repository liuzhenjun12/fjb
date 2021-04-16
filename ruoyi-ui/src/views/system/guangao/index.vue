<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="新闻名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入新闻名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="平台名称" prop="laizi">
        <el-select v-model="queryParams.laizi" placeholder="请选择平台名称" clearable size="small">
          <el-option
            v-for="dict in laiOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否评论" prop="sfwc">
        <el-select v-model="queryParams.status" placeholder="请选择是否评论过" clearable size="small">
          <el-option
            v-for="dict in bukaoOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建日期" prop="createTime">
        <el-date-picker clearable size="small" style="width: 200px"
                        v-model="queryParams.createTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择日期">
        </el-date-picker>
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
          v-hasPermi="['system:guangao:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:guangao:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:guangao:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:guangao:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="guangaoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" min-width="5%" align="center" />
      <el-table-column label="编号" min-width="5%" align="center" prop="id" />
      <el-table-column label="新闻名称" min-width="40%" align="center" prop="name" >
      <template slot-scope="scope">
        <router-link :to="'/customer/product/data/' + scope.row.id" class="link-type">
          <span>{{ scope.row.name }}</span>
        </router-link>
      </template>
      </el-table-column>
      <el-table-column label="评论次数" min-width="10%" align="center" prop="num" />
      <el-table-column label="创建日期" min-width="10%" align="center"   prop="createTime"  >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="laizi"
        label="平台名称"
        width="100"
        :filters="bukaoOptions"
        :filter-method="filterTag"
        filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.laizi === '百度热榜' ? 'success' : 'primary'"
            disable-transitions>{{scope.row.laizi}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="评论状态" min-width="10%" align="center"  >
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.status"
            active-value="Y"
            inactive-value="N"
            disabled
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作" min-width="15%" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-search"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:guangao:query']"
          >查看</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:guangao:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:guangao:remove']"
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

    <!-- 添加或修改网站评论对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="新闻名称" prop="name">
          <el-input v-model="form.name" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="新闻地址" prop="url">
          <el-input v-model="form.url" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="平台名称" prop="laizi">
          <el-select v-model="form.laizi" placeholder="请选择平台名称" style="width: 100%">
            <el-option
              v-for="dict in laiOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
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
import { listGuangao, getGuangao, delGuangao, addGuangao, updateGuangao, exportGuangao } from "@/api/system/guangao";

export default {
  name: "Guangao",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      laiOptions:[],
      bukaoOptions: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 网站评论表格数据
      guangaoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        status:null,
        createTime:null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    var shijian=new Date();
    this.queryParams.createTime=shijian;
    this.getList();
    this.getDicts("sys_tai_name").then(response => {
      this.laiOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.bukaoOptions = response.data;
    });
  },
  methods: {
    /** 查询网站评论列表 */
    getList() {
      this.loading = true;
      listGuangao(this.queryParams).then(response => {
        this.guangaoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    filterTag(value, row) {
      return row.tag === value;
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
        name: null,
        url: null,
        laizi: null,
        num:null,
        status:null,
        createTime: null,
        updateTime: null,
        createBy: null,
        updateBy: null,
        remark: null,
        delFlag: null
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
      this.reset();
      this.open = true;
      this.title = "添加网站评论";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getGuangao(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改网站评论";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateGuangao(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addGuangao(this.form).then(response => {
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
      this.$confirm('是否确认删除网站评论编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delGuangao(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有网站评论数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportGuangao(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
