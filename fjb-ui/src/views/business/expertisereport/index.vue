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
          v-hasPermi="['business:expertisereport:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['business:expertisereport:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    </div>
    <el-table ref="table"
              v-loading="loading"
              row-key="id"
              :data="expertisereportList"
              :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="鉴定日期" width="130" align="center" prop="identifydate" />
      <el-table-column label="鉴定单位" width="150" align="center" prop="dept.jianCheng" :show-overflow-tooltip="true" />
      <el-table-column label="鉴定人员" align="center" prop="identifyname" />
      <el-table-column label="货币种类" align="center" prop="ccCurrency" />
      <el-table-column label="货币数量" align="center" prop="amount" />
      <el-table-column label="面额合计" align="center" prop="totalamount" />
      <el-table-column label="专家复核" align="center"  >
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.status"
            active-value="Y"
            inactive-value="N"
            @change="handleStatusChange(scope.row)"
            :disabled="disabled"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="冠字号码" align="center" prop="serialNumber" :show-overflow-tooltip="true" />
      <el-table-column label="数据来源" align="center" prop="dataSource" :show-overflow-tooltip="true" />
      <el-table-column label="操作" align="center" width="220" class-name="small-padding fixed-width">
        <template slot-scope="scope">
            <el-tooltip class="item" effect="dark" content="在此新增会绑定为同一批次" placement="bottom">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-plus"
                @click="handleAdd(scope.row)"
                v-hasPermi="['business:expertisereport:add']"
              >新增</el-button>
            </el-tooltip>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleYulan(scope.row)"
            v-hasPermi="['business:expertisereport:query']"
          >预览</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['business:expertisereport:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:expertisereport:remove']"
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
    <el-dialog  :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" label-width="80px">
        <!--startprint-->
        <el-row>
          <div id="printMe">
            <el-col :span="24" style="text-align: center">
              <h2>货币真伪鉴定书</h2>
            </el-col>
            <el-col :span="24" style="text-align: center">
              <h5>鉴定日期：{{form.identifydate}}</h5>
            </el-col>
          <el-col :span="12" style="text-align: left;margin-bottom:10px">
            <span style="line-height: 30px">鉴定单位：</span><span >{{form.deptName}}</span>
          </el-col>
          <el-col :span="12" style="text-align: right;margin-bottom: 10px">
            <span style="line-height: 30px">鉴定编号：</span><span >{{form.number}}</span>
          </el-col>
          <el-col :span="24" >
            <table class="tab">
              <tbody><tr>
                <td colspan="3">送鉴单位或持有人：{{form.holderName}}</td>
                <td colspan="3">原鉴定(鉴别)单位：{{form.originalcffi}}</td>
              </tr>
              <tr>
                <td colspan="3">持有人联系电话：{{form.holderTelephone}}</td>
                <td colspan="3">持有人证件号码：{{form.holderIdcard}}</td>
              </tr>
              <tr align="center">
                <td width="14%">币种</td>
                <td width="16%">券别</td>
                <td width="20%">版别</td>
                <td width="22%">冠字号码</td>
                <td width="16%">数量（张、枚）</td>
                <td width="12%">金额（元）</td>
              </tr>
              <tr align="center" v-for="v in form_obj">
                <td>{{v.ccCurrency}}</td>
                <td>{{v.ccDenomination}}</td>
                <td>{{v.ccSeries}}</td>
                <td>{{v.serialNumber}}</td>
                <td>{{v.amount}}</td>
                <td>{{v.totalamount}}</td>
              </tr>

              <tr align="center">
                <td colspan="4">合计</td>
                <td colspan="1">{{form.amount}}</td>
                <td colspan="1">{{form.totalamount}}</td>
              </tr>
              <tr>
                <td colspan="6">原鉴定（鉴别）结果：{{form.originalresult}}</td>
              </tr>
              <tr>
                <td colspan="6" rowspan="10">
                  <div style="margin-top: 6px;margin-bottom: 10px;">鉴定结果：{{form.result}}</div>
                  <div>详细鉴定结果：{{form.resultdetails}}</div>
                  <br>
                  <br>
                </td>
              </tr>
              </tbody></table>
          </el-col>
          <el-col :span="12" style="text-align: left;margin-top: 10px;margin-bottom: 50px">
            <span style="line-height: 30px">鉴定人：</span><span >{{form.identifyname}}</span>
          </el-col>
          <el-col :span="12" style="text-align: right;margin-top: 10px;margin-bottom: 50px">
            <span style="line-height: 30px">复核人员：</span><span >{{form.checkupname}}</span>
          </el-col>
          </div>
        </el-row>
        <!--endprint-->
        <el-row>
          <el-col :span="24" >
            <el-upload
              action="#"
              list-type="picture-card"
              :file-list="fileList"
              :disabled="true"
              ref="dynamic"
              :auto-upload="false" class="zaopian">
              <i slot="default" class="el-icon-plus"></i>
              <div slot="file" slot-scope="{file}">
                <img
                  class="el-upload-list__item-thumbnail"
                  :src="file.url" alt=""
                >
                <span class="el-upload-list__item-actions">
                  <span
                    class="el-upload-list__item-preview"
                    @click="handlePictureCardPreview(file)"
                  >
                    <i class="el-icon-zoom-in"></i>
                  </span>
                  <span
                    v-if="!disabled"
                    class="el-upload-list__item-delete"
                    @click="handleDownload(file)"
                  >
                    <i class="el-icon-download"></i>
                  </span>
                </span>
              </div>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible" append-to-body>
              <img width="100%" :src="dialogImageUrl" alt="">
            </el-dialog>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="doPrint" type="primary" v-print="'#printMe'">打 印</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listExpertisereport, getParentNum,delExpertisereport, exportExpertisereport,changeExpertisereportStatus } from "@/api/business/expertisereport";
import { Cascadeselect } from "@/api/system/dept";

export default {
  name: "Expertisereport",
  data() {
    return {
      // 是否显示弹出预览层
      open: false,
      //图片集合
      fileList: [],
      form_obj:[],
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
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 假币鉴定表格数据
      expertisereportList: [],
      //预览
      expertisereportList_1:[],
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
      form_1:{}
    };
  },
  created() {
    this.getList();
    this.getCascadeselect();
    let permissions=this.$store.state.user.permissions
    if(permissions.length>1){
      for (var value of permissions) {
        if(value=='business:expertisereport:edit'){
          this.disabled=false;
          continue;
        }
      }
    }else {
      this.disabled=false;
    }
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
  activated () {
    this.getList()
  },
  methods: {
    /** 查询假币鉴定列表 */
    getList() {
      this.loading = true;
      listExpertisereport(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.expertisereportList_1 = response.rows;
        this.expertisereportList = this.handleTree(response.rows, "id");
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
    // 状态修改
    handleStatusChange(row) {
      let text = row.status === "Y" ? "最终复核通过" : "最终复核拒绝";
      this.$confirm('确认要"' + text + '""' + row.identifyname + '"经办的鉴定凭证吗?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return changeExpertisereportStatus(row.id, row.status);
      }).then(() => {
        this.msgSuccess(text + "成功");
      }).catch(function() {
        row.status = row.status === "Y" ? "N" : "Y";
      });
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
    /** 新增按钮操作 */
    handleAdd(row) {
      if (row != null && row.id) {
        getParentNum(row.id).then(response => {
          if(response.data<4){
            this.$router.push("/expertisereport/addPage/"+row.id);
          }else {
            this.msgError('一批最多5条数据')
          }
        });
      }else {
        this.$router.push("/expertisereport/addPage/0");
      }
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.$router.push("/expertisereport/updatePage/"+row.id );
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$confirm('是否确认删除假币鉴定的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delExpertisereport(row.id);
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
    },
    /** 预览按钮操作 */
    handleYulan(row) {
      this.form_obj=[]
      for(var i=0;i<this.expertisereportList_1.length;i++){
        var sub=this.expertisereportList_1[i]
        if(sub.id==row.id){
          this.form=sub;
          this.form.userName=sub.createBy;
          this.form.deptName=sub.dept.deptName
          var obj={amount:sub.amount,totalamount:sub.totalamount,ccCurrency:sub.ccCurrency,
            ccDenomination:sub.ccDenomination,ccSeries:sub.ccSeries,serialNumber:sub.serialNumber};
          this.form_obj.push(obj);
          if(sub.imgList!=''&&sub.imgList!=null){
            this.fileList=JSON.parse(sub.imgList);
            for(var j=0;j<this.fileList.length;j++){
              this.fileList[j].url=process.env.VUE_APP_BASE_API+this.fileList[j].url;
            }
          }
          continue;
        }
      }
      if(row.parentId==0){
        for(var i=0;i<this.expertisereportList_1.length;i++){
          var sub=this.expertisereportList_1[i]
          if(sub.parentId==row.id ){
            this.form.amount=parseInt(this.form.amount)+parseInt(sub.amount)
            this.form.totalamount=parseInt(this.form.totalamount)+parseInt(sub.totalamount)
            let obj={amount:sub.amount,totalamount:sub.totalamount,
              ccCurrency:sub.ccCurrency, ccDenomination:sub.ccDenomination,
              ccSeries:sub.ccSeries,serialNumber:sub.serialNumber};
            this.form_obj.push(obj);
          }
        }
      }
      if(this.form_obj.length<5){
        for(var i=this.form_obj.length;i<5;i++){
          let obj={amount:'',totalamount:'', ccCurrency:'', ccDenomination:'', ccSeries:'',serialNumber:''};
          this.form_obj.push(obj)
        }
      }
      this.open = true;
    },
    /** 图片预览 */
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    getnow(){
      var nowDate = new Date();
      var year = nowDate.getFullYear();
      var month = nowDate.getMonth() + 1 < 10 ? "0" + (nowDate.getMonth() + 1)
        : nowDate.getMonth() + 1;
      var day = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate
        .getDate();
      var dateStr = year + "年" + month + "月" + day+"日";
      return dateStr;
    },
    /** 打印 */
    doPrint(){}
  }
};
</script>

<style lang="scss" scoped>
  .tab {
    width: 100%;
    border-collapse: collapse;
  }
  .tab td {
    border: 1px solid #000;
    height: 32px;
    padding-left: 10px;
    width: 16.67%;
  }
</style>
