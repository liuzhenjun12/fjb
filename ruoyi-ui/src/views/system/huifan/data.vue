<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
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
          v-hasPermi="['system:huifan:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:huifan:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:huifan:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:huifan:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="huifanList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="回访简述">
              <span>{{ props.row.remark }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="客户名称" align="center" prop="cname" />
      <el-table-column label="购买意向" align="center" prop="yaoMai" />
      <el-table-column label="回访通道" align="center" prop="tongDao" />
      <el-table-column label="客户态度" align="center" prop="taiDu" />
      <el-table-column label="回访日期" align="center"  sortable prop="createTime" :sort-method="paixu" >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:huifan:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:huifan:remove']"
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

    <!-- 添加或修改回访记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户id" prop="cuId" style="display: none">
          <el-input v-model="form.cuId" placeholder="请输入客户id" />
        </el-form-item>
        <el-form-item label="客户名称" prop="cid">
          <el-input v-model="form.cname" placeholder="请输入客户名称" :disabled="true" />
        </el-form-item>
        <el-form-item label="购买意向" prop="yaoMai">
          <el-select v-model="form.yaoMai" placeholder="请选择客户购买意向" style="width: 100%">
            <el-option
              v-for="item in maiOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="回访通道" prop="tongDao">
          <el-select v-model="form.tongDao" placeholder="请选择回访通道" style="width: 100%">
            <el-option
              v-for="item in tongOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="客户态度" prop="taiDu">
          <el-select v-model="form.taiDu" placeholder="请选择客户态度" style="width: 100%">
            <el-option
              v-for="item in taiduOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="回访简述" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容，字符长度在20至500之间" />
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
  import { listHuifan, getHuifan, delHuifan, addHuifan, updateHuifan, exportHuifan } from "@/api/system/huifan";
  import {getCustomer} from "@/api/system/customer/type";
  export default {
    name: "Huifan",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 默认字典类型
        defaultCid: 0,
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
        // 回访记录表格数据
        huifanList: [],
        maiOptions:[
          {
            value: '还想购买',
            label: '还想购买'
          },
          {
            value: '不想购买',
            label: '不想购买'
          }
        ],
        tongOptions:[
          {
            value: '微信',
            label: '微信'
          },
          {
            value: '电话',
            label: '电话'
          },
          {
            value: '千牛',
            label: '千牛'
          },
          {
            value: '其他',
            label: '其他'
          }
        ],
        taiduOptions:[
          {
            value: '非常满意',
            label: '非常满意'
          },
          {
            value: '很满意',
            label: '很满意'
          },
          {
            value: '感觉一般',
            label: '感觉一般'
          },
          {
            value: '不满意',
            label: '不满意'
          },
          {
            value: '非常不满意',
            label: '非常不满意'
          }
        ],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          cuId:null,
          cname:null,
          createTime: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          yaoMai: [
            { required: true, message: "购买意向不能为空", trigger: "blur" }
          ],
          tongDao: [
            { required: true, message: "回访通道不能为空", trigger: "blur" }
          ],
          taiDu: [
            { required: true, message: "客户态度不能为空", trigger: "blur" }
          ],
          remark: [
            { required: true, message: "回访简述不能为空", trigger: "blur" },{ min: 20, max: 500, message: '长度在 20 到 500 个字符', trigger: 'blur'}
          ],
        }
      };
    },
    created() {
      const id = this.$route.params && this.$route.params.id;
      this.getCustomer(id);
    },
    methods: {
      /** 查询客户详细 */
      getCustomer(id) {
        getCustomer(id).then(response => {
          this.queryParams.cuId = response.data.id;
          this.queryParams.cname = response.data.name;
          this.defaultCid=response.data.id;
          this.getList();
        });
      },
      /** 查询回访记录列表 */
      getList() {
        this.loading = true;
        listHuifan(this.queryParams).then(response => {
          this.huifanList = response.rows;
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
          cuId: null,
          cname:null,
          yaoMai: null,
          tongDao: null,
          taiDu: null,
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
        this.queryParams.cuId = this.defaultCid;
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
        this.title = "添加回访记录";
        this.form.cuId = this.queryParams.cuId;
        this.form.cname = this.queryParams.cname;
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getHuifan(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改回访记录";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateHuifan(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                }
              });
            } else {
              addHuifan(this.form).then(response => {
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
        this.$confirm('是否确认删除回访记录编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delHuifan(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有回访记录数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportHuifan(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
      }
    }
  };
</script>
