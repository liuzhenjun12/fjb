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
          placeholder="请选择鉴定单位名称"
          :options="deptidOptions"
          :props="{ checkStrictly: true }"
          size="small"
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
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['business:confiscation:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:confiscation:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:confiscation:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['business:confiscation:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    </div>
    <el-table v-loading="loading" :data="confiscationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="收缴人姓名" align="center" prop="identifyname" />
      <el-table-column label="复核人姓名" align="center" prop="checkupname" />
      <el-table-column label="鉴定日期" align="center" prop="identifydate" />
      <el-table-column label="没收数量" align="center" prop="amount" />
      <el-table-column label="总金额" align="center" prop="totalamount" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="冠字号" align="center" prop="serialNumber" />
      <el-table-column label="币种" align="center" prop="ccCurrency" />
      <el-table-column label="假币劵别" align="center" prop="ccDenomination" />
      <el-table-column label="假币版别" align="center" prop="ccSeries" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['business:confiscation:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:confiscation:remove']"
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

    <!-- 添加或修改没收凭证对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="没收收据编号" prop="number">
          <el-input v-model="form.number" placeholder="请输入没收收据编号" />
        </el-form-item>
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="机构id" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入机构id" />
        </el-form-item>
        <el-form-item label="收缴人姓名" prop="identifyname">
          <el-input v-model="form.identifyname" placeholder="请输入收缴人姓名" />
        </el-form-item>
        <el-form-item label="复核人姓名" prop="checkupname">
          <el-input v-model="form.checkupname" placeholder="请输入复核人姓名" />
        </el-form-item>
        <el-form-item label="鉴定日期" prop="identifydate">
          <el-input v-model="form.identifydate" placeholder="请输入鉴定日期" />
        </el-form-item>
        <el-form-item label="没收假币数量" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入没收假币数量" />
        </el-form-item>
        <el-form-item label="总金额" prop="totalamount">
          <el-input v-model="form.totalamount" placeholder="请输入总金额" />
        </el-form-item>
        <el-form-item label="假币持有人姓名" prop="holderName">
          <el-input v-model="form.holderName" placeholder="请输入假币持有人姓名" />
        </el-form-item>
        <el-form-item label="身份证号" prop="holderIdcard">
          <el-input v-model="form.holderIdcard" placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="手机号码" prop="holderTelephone">
          <el-input v-model="form.holderTelephone" placeholder="请输入手机号码" />
        </el-form-item>
        <el-form-item label="银行卡号" prop="holderBanksnomber">
          <el-input v-model="form.holderBanksnomber" placeholder="请输入银行卡号" />
        </el-form-item>
        <el-form-item label="假币来源" prop="source">
          <el-input v-model="form.source" placeholder="请输入假币来源" />
        </el-form-item>
        <el-form-item label="假币备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="鉴定证书编号" prop="numberofverification">
          <el-input v-model="form.numberofverification" placeholder="请输入鉴定证书编号" />
        </el-form-item>
        <el-form-item label="原鉴定(鉴别)单位" prop="originalcffi">
          <el-input v-model="form.originalcffi" placeholder="请输入原鉴定(鉴别)单位" />
        </el-form-item>
        <el-form-item label="原鉴定" prop="originalresult">
          <el-input v-model="form.originalresult" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="鉴定结果" prop="result">
          <el-input v-model="form.result" placeholder="请输入鉴定结果" />
        </el-form-item>
        <el-form-item label="详细鉴定结果" prop="resultdetails">
          <el-input v-model="form.resultdetails" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="删除状态" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除状态" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="冠字号" prop="serialNumber">
          <el-input v-model="form.serialNumber" placeholder="请输入冠字号" />
        </el-form-item>
        <el-form-item label="币种" prop="ccCurrency">
          <el-input v-model="form.ccCurrency" placeholder="请输入币种" />
        </el-form-item>
        <el-form-item label="假币劵别" prop="ccDenomination">
          <el-input v-model="form.ccDenomination" placeholder="请输入假币劵别" />
        </el-form-item>
        <el-form-item label="假币版别" prop="ccSeries">
          <el-input v-model="form.ccSeries" placeholder="请输入假币版别" />
        </el-form-item>
        <el-form-item label="造假方式" prop="ccMadeway">
          <el-input v-model="form.ccMadeway" placeholder="请输入造假方式" />
        </el-form-item>
        <el-form-item label="组编号" prop="parentId">
          <el-input v-model="form.parentId" placeholder="请输入组编号" />
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
import { listConfiscation, getConfiscation, delConfiscation, addConfiscation, updateConfiscation, exportConfiscation } from "@/api/business/confiscation";
import { Cascadeselect } from "@/api/system/dept";
export default {
  name: "Confiscation",
  data() {
    return {
      // 是否显示弹出层
      open: false,
      //图片集合
      fileList: [],
      dialogImageUrl: '',
      //动态窗口是否显示
      dialogVisible: false,
      //是否禁用开关
      disabled:true,
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
      deptidOptions:undefined,
      // 假币收缴凭证表格数据
      receiptseizureList: [],
      //币种
      ccCurrencyOptions:[],
      //卷别
      ccDenominationOptions:[],
      //版别
      ccSeriesOptions:[],
      //复核状态
      statusOptions:[],
      // 日期范围
      dateRange: [],
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
      // 没收凭证表格数据
      confiscationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        deptId: null,
        status: null,
        serialNumber: null,
        ccCurrency: null,
        ccDenomination: null,
        ccSeries: null,
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
    /** 查询没收凭证列表 */
    getList() {
      this.loading = true;
      listConfiscation(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.confiscationList = response.rows;
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
        userId: null,
        deptId: null,
        identifyname: null,
        checkupname: null,
        identifydate: null,
        amount: null,
        totalamount: null,
        holderName: null,
        holderIdcard: null,
        holderTelephone: null,
        holderBanksnomber: null,
        source: null,
        remark: null,
        numberofverification: null,
        originalcffi: null,
        originalresult: null,
        result: null,
        resultdetails: null,
        delFlag: null,
        status: "0",
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        serialNumber: null,
        ccCurrency: null,
        ccDenomination: null,
        ccSeries: null,
        ccMadeway: null,
        parentId: null
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
    handleAdd(row) {
      if (row != null && row.id) {
        this.$router.push("/confiscation/addPage/"+row.id);
      }else {
        this.$router.push("/confiscation/addPage/0");
      }
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      const id = row.id || this.ids
      this.$router.push("/confiscation/updatePage/"+id);
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除没收凭证编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delConfiscation(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有没收凭证数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportConfiscation(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
