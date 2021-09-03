<template>
  <div class="app-container">
    <div class="form_kuand" >
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px" class="form_input">
      <el-form-item label="货币种类" prop="ccCurrency">
        <el-select
          v-model="queryParams.ccCurrency"
          placeholder="请选择货币种类"
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
      <el-form-item label="假币卷别" prop="ccDenomination">
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
      <el-form-item label="使用状态" prop="status">
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
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    </div>
    <div class="sanguang">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:money:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:money:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:money:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:money:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    </div>

    <el-table v-loading="loading" :data="moneyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="货币种类" align="center" prop="ccCurrency" />
      <el-table-column label="货币劵别" align="center" prop="ccDenomination" />
      <el-table-column label="货币版别" align="center" prop="ccSeries" />
      <el-table-column label="正面图片" align="center" prop="zhengImgPath" />
      <el-table-column label="正面图片" align="center" prop="fanImgPath" />
      <el-table-column label="当前状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:money:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:money:remove']"
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

    <!-- 添加或修改货币标准对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row >
          <el-col :span="24" >
        <el-form-item label="货币种类" prop="ccCurrency">
          <el-select v-model="form.ccCurrency"  clearable style="width: 100%" placeholder="请选择货币种类">
            <el-option
              v-for="dict in ccCurrencyOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
          </el-col>
          <el-col :span="24" >
        <el-form-item label="货币劵别" prop="ccDenomination">
          <el-select
            v-model="form.ccDenomination"
            placeholder="请选择假币卷别"
            clearable
            size="small"
            style="width: 100%"
          >
            <el-option
              v-for="dict in ccDenominationOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
          </el-col>
          <el-col :span="24" >
        <el-form-item label="货币版别" prop="ccSeries">
          <el-select
            v-model="form.ccSeries"
            placeholder="请选择假币版别"
            clearable
            size="small"
            style="width: 100%"
          >
            <el-option
              v-for="dict in ccSeriesOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
          </el-col>
          <el-col :span="12" style="text-align: center">
          <el-upload
            class="upload-demo"
            action="https://jsonplaceholder.typicode.com/posts/"
            :on-preview="handlePreview"
            :on-remove="handleRemove_1"
            accept="image/jpeg,image/png"
            :limit="1"
            :file-list="fileList_1"
            ref="dynamic_1"
            :on-exceed="exceedHandle"
            :http-request="uploadFile_1"
            list-type="picture">
            <el-button size="small" type="primary" >请上传货币正面图片</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
          </el-col>
          <el-col :span="12" style="text-align: center" >
            <el-upload
              class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              :on-preview="handlePreview"
              :on-remove="handleRemove_2"
              accept="image/jpeg,image/png"
              :limit="1"
              :file-list="fileList_2"
              ref="dynamic_2"
              :on-exceed="exceedHandle"
              :http-request="uploadFile_2"
              list-type="picture">
              <el-button size="small" type="primary" >请上传货币反面图片</el-button>
              <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
            </el-upload>
          </el-col>
          <el-col :span="24" style="margin-top: 20px">
        <el-form-item label="当前状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
          </el-col>
          <el-col :span="24" >
        <el-form-item label="备注说明" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMoney, getMoney, delMoney, addMoney, updateMoney, exportMoney } from "@/api/business/money";

export default {
  name: "Money",
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
      // 货币标准表格数据
      moneyList: [],
      fileList_1:[],
      fileList_2:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //币种
      ccCurrencyOptions:[],
      //卷别
      ccDenominationOptions:[],
      //版别
      ccSeriesOptions:[],
      //当前状态
      statusOptions:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        ccCurrency: null,
        ccDenomination: null,
        ccSeries: null,
        zhengImgPath: null,
        fanImgPath: null,
        status: null,
      },
      // 上传图片参数
      upload: {
        //图片存储
        formDate:"",
        // 是否禁用上传
        isUploading: false,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        ccCurrency: [
          { required: true, message: "货币种类不能为空", trigger: "blur" }
        ],
        ccDenomination: [
          { required: true, message: "货币卷别不能为空", trigger: "blur" }
        ],
        ccSeries: [
          { required: true, message: "货币版别不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_money_type").then(response => {
      this.ccCurrencyOptions = response.data;
    });
    this.getDicts("sys_denomination").then(response => {
      this.ccDenominationOptions = response.data;
    });
    this.getDicts("sys_money_version").then(response => {
      this.ccSeriesOptions = response.data;
    });
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询货币标准列表 */
    getList() {
      this.loading = true;
      listMoney(this.queryParams).then(response => {
        this.moneyList = response.rows;
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
        ccCurrency: null,
        ccDenomination: null,
        ccSeries: null,
        zhengImgPath: null,
        fanImgPath: null,
        status: "0",
        delFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
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
      this.title = "添加货币标准";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMoney(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改货币标准";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateMoney(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            let arr_1=this.$refs['dynamic_1'].uploadFiles
            let arr_2=this.$refs['dynamic_2'].uploadFiles
            if(arr_1.length==1&&arr_2.length==1) {
              this.upload.formDate = new FormData();
              this.$refs['dynamic_1'].submit();
              this.$refs['dynamic_2'].submit();
              let count=0;
              for (var key of this.upload.formDate.keys()) {
                if(key=="file"){
                  count++;
                }
              }
              if(count==2){
                this.msgSuccess("新增成功");
              }else {
                this.msgError("请上传要求以内的图片")
              }
            }else {
              this.msgError("请上传货币图片")
            }
            // addMoney(this.form).then(response => {
            //   if (response.code === 200) {
            //     this.msgSuccess("新增成功");
            //     this.open = false;
            //     this.getList();
            //   }
            // });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除货币标准编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delMoney(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有货币标准数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportMoney(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    },
    handleRemove_1(file, fileList) {
      console.log(file, fileList);
    },
    handleRemove_2(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    //文件超出个数限制时的钩子
    exceedHandle(file,fileList){
      this.msgError('上传图片不能超过1张!')
    },
    //覆盖默认的上传方法
    uploadFile_1(file){
      console.log(file)
      // let obj={name:file.name,url:file.url}
      // this.fileList_1.push(obj)
      // console.log(this.fileList_1)
      // this.upload.formDate.append('file', file.file);
    },
    //覆盖默认的上传方法
    uploadFile_2(file){
      let obj={name:file.name,url:file.url}
      this.fileList_2.push(obj)
      console.log(this.fileList_2)
      // this.upload.formDate.append('file', file.file);
    },
  }
};
</script>
