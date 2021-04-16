<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份" prop="idcard">
        <el-input
          v-model="queryParams.idcard"
          placeholder="请输入身份证"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="日期" prop="kaoshiTime">
        <el-date-picker clearable size="small" style="width: 200px"
                        v-model="queryParams.kaoshiTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择考试日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="补考" prop="bukao">
        <el-select v-model="queryParams.bukao" placeholder="请选择是否补考" clearable size="small">
          <el-option
            v-for="dict in bukaoOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="完成" prop="sfwc">
        <el-select v-model="queryParams.sfwc" placeholder="请选择是否完成" clearable size="small">
          <el-option
            v-for="dict in bukaoOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="批次" prop="pici">
        <el-select v-model="queryParams.pici" placeholder="请选择批次" clearable @change="handleChangePici" size="small">
          <el-option
            v-for="dict in piciOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="机构" prop="deptId">
        <el-cascader
          v-model="queryParams.deptId"
          placeholder="请选择机构名称"
          :options="deptOptions"
          size="medium"
          :show-all-levels="false"
          @change="handleChange"
          ref="myCascader"
          clearable
        >
        </el-cascader>
      </el-form-item>
      <el-form-item label="类型" prop="kaoshiType">
        <el-select v-model="queryParams.kaoshiType" placeholder="请选择考试类型" clearable size="small">
          <el-option
            v-for="dict in kaoshiTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item >
        <el-button type="cyan" icon="el-icon-search"  @click="handleQuery">搜索</el-button>
        <el-button type="danger" icon="el-icon-refresh"  @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
<!--  </div>-->

    <el-row :gutter="10" class="mb8 ">
      <el-col :span="1.5">
        <el-button
          type="cyan"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:bmb:add']"
        >新增</el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="cyan"
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['system:bmb:import']"
        >导入</el-button>
      </el-col>
      <el-tooltip class="item" effect="dark" content="请选择机构或者批次" placement="bottom">
      <el-col :span="1.5">
        <el-button
          type="cyan"
          icon="el-icon-download"
          size="mini"
          :disabled="queryParams.pici==null&&queryParams.deptId==null"
          @click="handleExport"
          v-hasPermi="['system:bmb:export']"
        >导出</el-button>
      </el-col>
      </el-tooltip>
      <el-tooltip class="item" effect="dark" content="请选择一行数据" placement="bottom">
      <el-col :span="1.5">
        <el-button
          type="cyan"
          icon="el-icon-edit-outline"
          size="mini"
          :disabled="multiple"
          @click="handleLuru"
          v-hasPermi="['system:bmb:luru']"
        >录入
        </el-button>
      </el-col>
      </el-tooltip>
      <el-tooltip class="item" effect="dark" content="请选择一行数据" placement="bottom">
      <el-col :span="1.5">
        <el-button
          type="cyan"
          icon="el-icon-top"
          size="mini"
          :disabled="single"
          @click="handleUpdateAvatar"
          v-hasPermi="['system:bmb:luru']"
        >
          照片
        </el-button>
      </el-col>
      </el-tooltip>
      <el-tooltip class="item" effect="dark" content="请选择批次" placement="bottom">
      <el-col :span="1.5">
        <el-button
          type="cyan"
          icon="el-icon-download"
          size="mini"
          :disabled="queryParams.pici==null"
          @click="handleCount"
          v-hasPermi="['system:bmb:luru']"
        >
          统计
        </el-button>
      </el-col>
      </el-tooltip>
      <el-tooltip class="item" effect="dark" content="请选择批次和机构" placement="bottom">
        <el-col :span="1.5">
          <el-button
            type="cyan"
            icon="el-icon-download"
            size="mini"
            :disabled="queryParams.pici==null||queryParams.deptId==null"
            @click="handleChengji"
            v-hasPermi="['system:bmb:luru']"
          >
            成绩
          </el-button>
        </el-col>
      </el-tooltip>
      <el-tooltip class="item" effect="dark" content="请选择批次和机构" placement="bottom">
        <el-col :span="1.5">
          <el-button
            type="cyan"
            icon="el-icon-upload2"
            size="mini"
            :disabled="queryParams.pici==null||queryParams.deptId==null"
            @click="handleChengji"
            v-hasPermi="['system:bmb:luru']"
          >
            实操
          </el-button>
        </el-col>
      </el-tooltip>
      <el-tooltip class="item" effect="dark" content="请选择一行数据" placement="bottom">
        <el-col :span="1.5">
          <el-button
            type="cyan"
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['system:bmb:edit']"
          >修改</el-button>
        </el-col>
      </el-tooltip>
      <el-tooltip class="item" effect="dark" content="至少选择一行数据" placement="bottom">
        <el-col :span="1.5">
          <el-button
            type="cyan"
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['system:bmb:remove']"
          >删除</el-button>
        </el-col>
      </el-tooltip>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bmbList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" min-width="5%" align="center" />
      <el-table-column label="id" min-width="8%" align="center" prop="id" />
      <el-table-column label="姓名" min-width="10%" align="center" prop="name" />
      <el-table-column label="身份证" min-width="20%" align="center" prop="idcard" />
      <el-table-column label="机构" min-width="12%" align="center" prop="dept.jianCheng" :show-overflow-tooltip="true" />
      <el-table-column label="类型" min-width="8%" align="center" prop="kaoshiType" :formatter="ikashilx" />
      <el-table-column label="岗位" min-width="8%" align="center" prop="examType" />
      <el-table-column label="理论成绩"  min-width="10%" align="center" prop="liluen" :formatter="ifendcase" />
      <el-table-column label="实操成绩"  min-width="10%" align="center" prop="shichao" :formatter="ishicase" />
      <el-table-column label="补考" min-width="10%" align="center"  >
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.bukao"
            active-value="Y"
            inactive-value="N"
            @change="handleBukaoChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="完成" align="center"min-width="10%">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.sfwc"
            active-value="Y"
            inactive-value="N"
            @change="handleStatusChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="当前状态" min-width="20%" align="center" prop="fucha" :show-overflow-tooltip="true" />
<!--      <el-table-column label="批次" align="center" prop="pici" :formatter="piciFormat" />-->
      <el-table-column label="操作" min-width="15%" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:bmb:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:bmb:remove']"
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
    <!-- 添加或修改报名对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="身份证" prop="idcard">
          <el-input v-model="form.idcard" placeholder="请输入身份证" />
        </el-form-item>
        <el-form-item label="考试日期" prop="kaoshiTime">
          <el-date-picker clearable size="small" style="width: 100%"
                          v-model="form.kaoshiTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择考试日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="机构id" prop="deptId" style="display: none">
          <el-input v-model="form.deptId" placeholder="" type="hidden"/>
        </el-form-item>
        <el-form-item label="省id" prop="shengId" style="display: none">
          <el-input v-model="form.shengId" placeholder="" type="hidden"/>
        </el-form-item>
        <el-form-item label="机构名称" prop="ancestors">
          <el-cascader
            v-model="form.ancestors"
            placeholder="请选择机构名称"
            :options="deptOptions"
            size="small"
            :show-all-levels="false"
            @change="changDept"
            clearable
          >
          </el-cascader>
        </el-form-item>
        <el-form-item label="考试批次" prop="pici">
          <el-select v-model="form.pici" placeholder="请选择批次" style="width: 100%">
            <el-option
              v-for="dict in piciOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="岗位类型" prop="examType">
          <el-select v-model="form.examType" placeholder="请选择岗位类型" style="width: 100%">
            <el-option
              v-for="item in gangOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="相片地址" prop="avatarUrl">
        <el-input placeholder="请输入相片地址" v-model="form.avatarUrl">
          <template slot="prepend">/profile/avatar/</template>
        </el-input>
        </el-form-item>
        <el-form-item label="是否补考">
          <el-radio-group v-model="form.bukao">
            <el-radio
              v-for="dict in bukaoOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否完成">
          <el-radio-group v-model="form.sfwc">
            <el-radio
              v-for="dict in bukaoOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="考试类型" >
          <el-radio-group v-model="form.kaoshiType">
            <el-radio
              v-for="dict in kaoshiTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="理论成绩" >
          <el-radio-group v-model="form.liluen">
            <el-radio
              v-for="dict in liluenOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="实操成绩" >
          <el-radio-group v-model="form.shichao">
            <el-radio
              v-for="dict in liluenOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="当前状态" prop="fucha">
          <el-input v-model="form.fucha" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 报名表导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport+'&kaoshiTime='+upload.kaoshiTime+'&deptId='+upload.deptId+'&pici='+upload.pici+'&avatarUrl='+upload.avatarUrl+'&ancestors='+upload.ancestors+'&shengId='+this.upload.shengId"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">
          <el-cascader
            v-model="upload.deptId"
            placeholder="请选择机构名称"
            :options="deptOptions"
            size="small"
            :show-all-levels="false"
            @change="handleZong"
            clearable
          >
          </el-cascader>
        </div>
        <div class="el-upload__tip" slot="tip">
          <el-date-picker clearable  style="width: 100%"
                          v-model="upload.kaoshiTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择考试日期">
          </el-date-picker>
        </div>
        <div class="el-upload__tip" slot="tip">
          <el-select v-model="upload.pici" placeholder="请选择批次" style="width: 100%">
            <el-option
              v-for="dict in piciOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </div>
        <div class="el-upload__tip" slot="tip">
          <el-input placeholder="请输入相片地址"  clearable v-model="upload.avatarUrl">
            <template slot="prepend">/相片目录/</template>
          </el-input>
        </div>
        <div class="el-upload__tip" slot="tip">
          <el-checkbox v-model="upload.updateSupport" />是否更新已经存在的报名表数据
          <el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import { listBmb, getBmb, delBmb, addBmb, updateBmb, exportBmb,importTemplate,luruBmb,changeSfwc,changeBukao,updateAvatar,exportCount,downloadChengji } from "@/api/system/bmb";
  import { getToken } from "@/utils/auth";
  import { Cascadeselect } from "@/api/system/dept";
  export default {
    name: "Bmb",
    data() {
      return {
        value:[],
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        //是否选中银行
        youhang:true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 报名表格数据
        bmbList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 部门树选项
        deptOptions: undefined,
        defaultProps: {
          children: "children",
          label: "label"
        },
        // 是否补考字典
        bukaoOptions: [],
        // 批次字典
        piciOptions: [],
        //理论字典
        liluenOptions:[],
        //考试类型
        kaoshiTypeOptions:[],
        gangOptions:[
          {
            value: '临柜',
            label: '临柜'
          },
          {
            value: '清分',
            label: '清分'
          }
        ],
        res:{msg:'',code:200},
        // 导入参数
        upload: {
          // 是否显示弹出层（用户导入）
          open: false,
          // 弹出层标题（用户导入）
          title: "",
          // 是否禁用上传
          isUploading: false,
          // 是否更新已经存在的用户数据
          updateSupport: 0,
          //考试日期
          kaoshiTime:'',
          //机构名称
          deptId:null,
          //祖级列表
          ancestors:null,
          //省id
          shengId:null,
          //批次
          pici:'',
          //相片地址
          avatarUrl:'',
          // 设置上传的请求头部
          headers: { Authorization: "Bearer " + getToken() },
          // 上传的地址
          url: process.env.VUE_APP_BASE_API + "/system/bmb/importData"
        },
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          name: null,
          idcard: null,
          kaoshiType:null,
          kaoshiTime: null,
          deptId: null,
          bukao: null,
          sfwc: null,
          pici: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          name: [
            { required: true, message: "姓名不能为空", trigger: "blur" },{ min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
          ],
          idcard: [
            { required: true, message: "身份证不能为空", trigger: "blur" },{ min: 9, max: 19, message: '长度在 9 到 19 个字符', trigger: 'blur' }
          ],
          ancestors: [
            { required: true, message: "机构不能为空", trigger: "blur" }
          ],
          pici: [
            { required: true, message: "考试批次不能为空", trigger: "blur" }
          ],
          kaoshiTime: [
            { required: true, message: "考试日期不能为空", trigger: "blur" }
          ],
          examType: [
            { required: true, message: "岗位类型不能为空", trigger: "blur" }
          ],
          avatarUrl: [
            { required: true, message: "照片地址不能为空", trigger: "blur" }
          ],
          phone:[
            { required: true, message: "手机号不能为空", trigger: "blur" }
          ],
        }
      };
    },
    watch: {

    },
    created() {
      this.getList();
      this.getDicts("sys_kaoshi_status").then(response => {
        this.bukaoOptions = response.data;
      });
      this.getDicts("sys_kaoshi_pici").then(response => {
        this.piciOptions = response.data;
      });
      this.getDicts("kaoshi_chengji").then(response => {
        this.liluenOptions = response.data;
      });
      this.getDicts("sys_kao_type").then(response => {
        this.kaoshiTypeOptions = response.data;
      });
      this.getTreeselect();
    },
    methods: {
      /** 查询报名列表 */
      getList() {
        this.loading = true;
        listBmb(this.queryParams).then(response => {
          this.bmbList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      /** 查询部门下拉树结构 */
      getTreeselect() {
        Cascadeselect().then(response => {
          this.deptOptions = response.data;
        });
      },
      // 是否补考字典翻译
      bukaoFormat(row, column) {
        return this.selectDictLabel(this.bukaoOptions, row.bukao);
      },
      // 是否完成字典翻译
      sfwcFormat(row, column) {
        return this.selectDictLabel(this.bukaoOptions, row.sfwc);
      },
      // 批次字典翻译
      piciFormat(row, column) {
        return this.selectDictLabel(this.piciOptions, row.pici);
      },
      //格式化理论
      ifendcase(val){
        if(val.liluen=='N'){
          return '不合格'
        }else if(val.liluen=='Y'){
          return '合格'
        }else if(val.liluen=='W'){
          return '未公布'
        }else {
          return '缺考'
        }
      },
      //格式化实操
      ishicase(val){
        if(val.shichao=='N'){
          return '不合格'
        }else if(val.shichao=='Y'){
          return '合格'
        }else if(val.shichao=='W'){
          return '未公布'
        }else {
          return '缺考'
        }
      },
      ikashilx(row){
        if(row.kaoshiType=='1'){
          return '首次'
        }else if(row.kaoshiType=='2'){
          return '证书'
        }else {
          return '未知'
        }
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 是否完成状态修改
      handleStatusChange(row) {
        let text = row.sfwc === "Y" ? "是" : "否";
        this.$confirm('确认要修改"' + row.name + '"的完成状态为"'+text+'"吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return changeSfwc(row.id, row.sfwc);
        }).then(() => {
          this.msgSuccess(text + "修改成功");
        }).catch(function() {
          row.sfwc = row.sfwc === "Y" ? "N" : "Y";
        });
      },
      //是否补考
      handleBukaoChange(row){
        let text = row.bukao === "Y" ? "是" : "否";
        this.$confirm('确认要修改"' + row.name + '"的补考状态为"'+text+'"吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return changeBukao(row.id, row.bukao);
        }).then(() => {
          this.msgSuccess(text + "修改成功");
        }).catch(function() {
          row.bukao = row.bukao === "Y" ? "N" : "Y";
        });
      },
      // 表单重置
      reset() {
        this.form = {
          id: null,
          name: null,
          idcard: null,
          phone:null,
          kaoshiTime: null,
          deptId: null,
          bukao: 'N',
          sfwc: 'N',
          liluen: 'W',
          shichao: 'W',
          kaoshiType:'3',
          pici: null,
          shengId:null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 搜索选择机构 */
      handleChange(value) {
        if(value){
          this.queryParams.deptId =value[2]
        }else {
          this.queryParams.deptId = null;
        }
      },
      //批次发生变化时
      handleChangePici(value){
        if(value){
          this.queryParams.pici =value
        }else {
          this.queryParams.pici = null;
        }
      },
      /** 新增修改选择机构 */
      changDept(value){
        if(value){
          this.form.shengId=value[0];
          this.form.deptId =value[2];
        }else {
          this.form.deptId = null;
          this.form.shengId=null;
        }
      },
      /** 上传时选择机构 */
      handleZong(value){
        if(value){
          this.upload.shengId=value[0];
          this.upload.deptId =value[2];
          this.upload.ancestors=value.toString();
        }else {
          this.upload.shengId=null;
          this.upload.deptId = null;
          this.upload.ancestors=null;
        }
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加报名";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getBmb(id).then(response => {
          if(response.code==200) {
            this.form = response.data;
            this.form.ancestors = this.form.ancestors.split(',');
            this.open = true;
            this.title = "修改报名";
          }else {
            this.msgError(response.msg);
          }
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              this.form.ancestors=this.form.ancestors.toString();
              updateBmb(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                }
              });
            } else {
              this.form.ancestors=this.form.ancestors.toString();
              if(this.form.bukao=='0'){
                this.form.bukao='N'
              }
              if(this.form.sfwc=='0'){
                this.form.sfwc='N'
              }
              this.form.liluen='W';
              this.form.shichao='W';
              addBmb(this.form).then(response => {
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
        this.$confirm('是否确认删除报名编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delBmb(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        const h = this.$createElement;
        this.$msgbox({
          title: '消息',
          message: h('p', null, [
            h('span', null, '是否确认导出报名数据项?')
          ]),
          showCancelButton: true,
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          beforeClose: (action, instance, done) => {
            if (action === 'confirm') {
              instance.confirmButtonLoading = true;
              instance.confirmButtonText = '执行中...';
              exportBmb(queryParams).then(res=>{
                this.res.msg=res.msg;
                this.res.code=res.code;
                setTimeout(() => {
                  done();
                  setTimeout(() => {
                    instance.confirmButtonLoading = false;
                  }, 300);
                }, 500);
              })
            } else {
              done();
            }
          }
        }).then(action => {
          if(this.res.code==200){
            this.download(this.res.msg);
          }else {
            this.msgError(this.res.msg);
          }
        });

      },
      /** 导入按钮操作 */
      handleImport() {
        this.upload.title = "报名表导入";
        this.upload.open = true;
      },
      /** 下载模板操作 */
      importTemplate() {
        importTemplate().then(response => {
          this.download(response.msg);
        });
      },
      // 文件上传中处理
      handleFileUploadProgress(event, file, fileList) {
        this.upload.isUploading = true;
      },
      // 文件上传成功处理
      handleFileSuccess(response, file, fileList) {
        this.upload.open = false;
        this.upload.isUploading = false;
        this.$refs.upload.clearFiles();
        this.$alert(response.msg, "导入结果", {dangerouslyUseHTMLString: true});
        this.getList();
      },
      // 提交上传文件
      submitFileForm() {
        if(this.upload.pici==''||this.upload.deptId==null||this.upload.ancestors==null||this.upload.avatarUrl==''||this.upload.kaoshiTime==''){
          this.msgError("请完善必选项");
        }else {
          this.$refs.upload.submit();
        }
      },
      //录入按钮
      handleLuru(row) {
        const ids = row.id || this.ids;
        const h = this.$createElement;
        this.$msgbox({
          title: '消息',
          message: h('p', null, [
            h('span', null, '是否确认要录入?')
          ]),
          showCancelButton: true,
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          beforeClose: (action, instance, done) => {
            if (action === 'confirm') {
              instance.confirmButtonLoading = true;
              instance.confirmButtonText = '执行中...';
              luruBmb(ids).then(res=>{
                this.res.msg=res.msg;
                this.res.code=res.code;
                setTimeout(() => {
                  done();
                  setTimeout(() => {
                    instance.confirmButtonLoading = false;
                  }, 300);
                }, 500);
              })
            } else {
              done();
            }
          }
        }).then(action => {
          if(this.res.code==200){
            this.msgSuccess(this.res.msg);
            this.getList();
          }else {
            this.msgError(this.res.msg);
          }
        });
      },
      /*** 修改照片* @param row*/
      handleUpdateAvatar(row) {
        const id = row.id || this.ids
        const h = this.$createElement;
        this.$msgbox({
          title: '消息',
          message: h('p', null, [
            h('span', null, '是否确认要修改照片?')
          ]),
          showCancelButton: true,
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          beforeClose: (action, instance, done) => {
            if (action === 'confirm') {
              instance.confirmButtonLoading = true;
              instance.confirmButtonText = '执行中...';
             updateAvatar(id).then(res=>{
               this.res.msg=res.msg;
               this.res.code=res.code;
               setTimeout(() => {
                 done();
                 setTimeout(() => {
                   instance.confirmButtonLoading = false;
                 }, 300);
               }, 500);
             })
            } else {
              done();
            }
          }
        }).then(action => {
          if(this.res.code==200){
            this.msgSuccess(this.res.msg);
            this.getList();
          }else {
            this.msgError(this.res.msg);
          }
        });
      },
      /*** 导出统计报表*/
      handleCount(){
        const h = this.$createElement;
        this.$msgbox({
          title: '消息',
          message: h('p', null, [
            h('span', null, '是否确认要导出统计报表?')
          ]),
          showCancelButton: true,
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          beforeClose: (action, instance, done) => {
            if (action === 'confirm') {
              instance.confirmButtonLoading = true;
              instance.confirmButtonText = '执行中...';
              exportCount(this.queryParams.pici).then(res=>{
                this.res.msg=res.msg;
                this.res.code=res.code;
                setTimeout(() => {
                  done();
                  setTimeout(() => {
                    instance.confirmButtonLoading = false;
                  }, 300);
                }, 500);
              })
            } else {
              done();
            }
          }
        }).then(action => {
          if(this.res.code==200){
            this.download(this.res.msg);
          }else {
            this.msgError(this.res.msg);
          }
        });
      },
      handleChengji(){
        const h = this.$createElement;
        this.$msgbox({
          title: '消息',
          message: h('p', null, [
            h('span', null, '是否确认要从测评中心导入成绩?')
          ]),
          showCancelButton: true,
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          beforeClose: (action, instance, done) => {
            if (action === 'confirm') {
              instance.confirmButtonLoading = true;
              instance.confirmButtonText = '执行中...';
              downloadChengji(this.queryParams.pici,this.queryParams.deptId).then(res=>{
                this.res.msg=res.msg;
                this.res.code=res.code;
                setTimeout(() => {
                  done();
                  setTimeout(() => {
                    instance.confirmButtonLoading = false;
                  }, 300);
                }, 500);
              })
            } else {
              done();
            }
          }
        }).then(action => {
          if(this.res.code==200){
            this.msgSuccess(this.res.msg);
          }else {
            this.msgError(this.res.msg);
          }
        });
      }
    }
  };
</script>
