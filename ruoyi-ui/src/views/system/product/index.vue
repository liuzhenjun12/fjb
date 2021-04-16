<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="产品类型" prop="type">
        <el-select v-model="form.type" placeholder="请选择产品型号"  clearable size="small">
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="购买日期" prop="sendTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.sendTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择购买日期">
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
          v-hasPermi="['system:product:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:product:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:product:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:product:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="productList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="产品样图"
        width="100">
        <template slot-scope="scope">
          <img :src="scope.row.img" min-width="100" height="70"/>
        </template>
      </el-table-column>
      <el-table-column label="客户名称" align="center" prop="cname" />
      <el-table-column label="产品名称" align="center" prop="type" :formatter="productFormat" />
      <el-table-column label="产品尺寸" align="center" prop="chicun"  />
      <el-table-column label="是否定制" align="center" prop="dingzhi" :formatter="dingzhiFormat" />
      <el-table-column label="购买个数" align="center" prop="num" />
      <el-table-column label="成交金额" align="center" prop="money" />
      <el-table-column label="购买日期" align="center" prop="sendTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.sendTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:product:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:product:remove']"
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

    <!-- 添加或修改【请填写功能名称】对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户id" prop="cid" style="display: none">
          <el-input v-model="form.cid" placeholder="请输入客户id" :disabled="true" />
        </el-form-item>
        <el-form-item label="客户名称" prop="cid">
          <el-input v-model="form.cname" placeholder="请输入客户名称" :disabled="true" />
        </el-form-item>
        <el-form-item label="产品类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择产品型号" style="width: 100%">
            <el-option
              v-for="dict in typeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="产品图片" prop="type">
          <el-select v-model="form.img" placeholder="请选择产品图片" style="width: 100%">
            <el-option
              v-for="dict in imgOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="产品尺寸" prop="chicun">
          <el-select v-model="form.chicun" placeholder="请输入产品尺寸" style="width: 100%">
            <el-option
              v-for="dict in gangOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否定制" prop="dingzhi">
          <el-select v-model="form.dingzhi" placeholder="请选择产品图片" style="width: 100%">
            <el-option
              v-for="dict in dingzhiOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="购买个数" prop="num">
          <el-input-number v-model="form.num" controls-position="right" :min="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="成交金额" prop="money">
          <el-input-number v-model="form.money" controls-position="right" :min="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="购买日期" prop="sendTime">
          <el-date-picker clearable size="small" style="width:100%"
            v-model="form.sendTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择购买日期">
          </el-date-picker>
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
import { listProduct, getProduct, delProduct, addProduct, updateProduct, exportProduct } from "@/api/system/product";
import {getCustomer} from "@/api/system/customer/type";
export default {
  name: "Product",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 产品类型字典
      typeOptions: [],
      //产品图片字典
      imgOptions:[],
      //是否定制字典
      dingzhiOptions:[],
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
      // 【请填写功能名称】表格数据
      productList: [],
      //产品尺寸
      gangOptions:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cid: null,
        cname:null,
        type: null,
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
    const id = this.$route.params && this.$route.params.id;
    this.getProduct(id);
    this.getDicts("sys_product_type").then(response => {
      this.typeOptions = response.data;
    });
    this.getDicts("sys_product_img").then(response => {
      this.imgOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.dingzhiOptions = response.data;
    });
    this.getDicts("sys_product_size").then(response => {
      this.gangOptions = response.data;
    });
  },
  methods: {
    /** 查询客户详细 */
    getProduct(id) {
      getCustomer(id).then(response => {
        this.queryParams.cid = response.data.id;
        this.queryParams.cname = response.data.name;
        this.defaultCid=response.data.id;
        this.getList();
      });
    },
    /** 查询【请填写功能名称】列表 */
    getList() {
      this.loading = true;
      listProduct(this.queryParams).then(response => {
        this.productList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 产品字典翻译
    productFormat(row, column){
      return this.selectDictLabel(this.typeOptions, row.type);
    },
    //是否定制字典翻译
    dingzhiFormat(row, column){
      return this.selectDictLabel(this.dingzhiOptions, row.dingzhi);
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
        cid: null,
        cname:null,
        type: null,
        num: null,
        img:null,
        money: null,
        createTime: null,
        updateTime: null,
        createBy: null,
        delFlag: null,
        updateBy: null,
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
      this.queryParams.cid = this.defaultCid;
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
      this.title = "添加【购买产品记录】";
      this.form.cid = this.queryParams.cid;
      this.form.cname = this.queryParams.cname;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getProduct(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改【购买产品记录】";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateProduct(this.form).then(response => {
              this.open = false;
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.getList();
              }else {
                this.msgError(response.msg)
              }
            });
          } else {
            addProduct(this.form).then(response => {
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
      this.$confirm('是否确认删除【购买产品记录】编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delProduct(ids);
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
      this.$confirm('是否确认导出所有【购买产品记录】数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportProduct(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
