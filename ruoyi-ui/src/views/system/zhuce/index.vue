<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="请输入用户名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证" prop="cid">
        <el-input
          v-model="queryParams.cid"
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
        <el-form-item label="状态" prop="status">
          <el-select v-model="queryParams.status" placeholder="请选择审核状态" clearable size="small">
            <el-option
              v-for="dict in appverOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>

      <el-form-item label="考试类型" prop="kaoshiType">
        <el-select v-model="queryParams.kaoshiType" placeholder="请选择考试类型" clearable size="small">
          <el-option
            v-for="item in kaoOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:zhuce:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['system:zhuce:import']"
        >导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:zhuce:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-check"
          :disabled="multiple"
          size="mini"
          @click="handleTong"
          v-hasPermi="['system:zhuce:appver']"
        >通过</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-close"
          :disabled="multiple"
          size="mini"
          @click="handleBo"
          v-hasPermi="['system:zhuce:appver']"
        >驳回</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          icon="el-icon-set-up"
          :disabled="queryParams.deptId==null||queryParams.pici==null"
          size="mini"
          @click="handleHedui"
          v-hasPermi="['system:zhuce:export']"
        >核对</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="zhuceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" min-width="5%" align="center" />
      <el-table-column label="id" min-width="5%" align="center" prop="zcId" />
      <el-table-column label="用户名称" min-width="10%" align="center" prop="username" />
      <el-table-column label="身份证" min-width="15%"  align="center" prop="cid" />
      <el-table-column label="机构名称" min-width="20%"  align="center" prop="alternatename" />
      <el-table-column label="考试名称" min-width="20%"  align="center" prop="examname" />
      <el-table-column label="状态" min-width="10%" align="center" prop="status" :formatter="ifendcase" />
      <el-table-column label="操作" min-width="10%" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:zhuce:edit']"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:zhuce:remove']"
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
      <el-form ref="form" :model="form"  label-width="80px">
        <el-form-item label="用户头像" prop="filename">
          <el-avatar shape="square" :size="100"  fit="fill" :src="form.filename" ></el-avatar>
        </el-form-item>
        <el-form-item label="用户名称" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名称" />
        </el-form-item>
        <el-form-item label="身份证" prop="cid">
          <el-input v-model="form.cid" placeholder="请输入身份证" />
        </el-form-item>
        <el-form-item label="考试名称" prop="examname">
          <el-input v-model="form.examname" placeholder="请输入考试名称" />
        </el-form-item>
        <el-form-item label="机构名称" prop="alternatename">
          <el-input v-model="form.alternatename" placeholder="请输入机构名称" />
        </el-form-item>
        <el-form-item label="批次" prop="pici">
          <el-input v-model="form.pici" placeholder="请输入批次" />
        </el-form-item>
        <el-form-item label="过期时间" prop="daoTime">
          <el-input v-model="form.daoTime" placeholder="请输入过期时间" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in appverOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!-- 报名表导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-form ref="upload" :model="upload"  label-width="80px">
        <el-form-item label="考试日期" prop="kaoshiTime"  :rules="[
      { required: true, message: '考试日期不能为空'}]">
          <el-date-picker clearable size="small" style="width: 100%"
                          v-model="upload.kaoshiTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择考试日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="机构id" prop="deptId" style="display: none">
          <el-input v-model="upload.deptId" placeholder="" type="hidden"/>
        </el-form-item>
        <el-form-item label="机构名称" prop="ancestors" :rules="[
      { required: true, message: '机构名称不能为空'}]">
          <el-cascader
            v-model="upload.ancestors"
            placeholder="请选择机构名称"
            :options="deptOptions"
            size="small"
            :show-all-levels="false"
            @change="handleZong"
            clearable
          >
          </el-cascader>
        </el-form-item>
        <el-form-item label="考试批次" prop="pici" :rules="[
      { required: true, message: '考试批次不能为空'}]">
          <el-select v-model="upload.pici" placeholder="请选择批次" style="width: 100%">
            <el-option
              v-for="dict in piciOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { listZhuce, getZhuce, delZhuce, addZhuce, updateZhuce, exportZhuce,importZhuce,appver,hedui } from "@/api/system/zhuce";
import { getToken } from "@/utils/auth";
import { Cascadeselect } from "@/api/system/dept";
export default {
  name: "Zhuce",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      //注册用户id数组
      zcIds:[],
      //到期时间
      daoqi:'',
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 【请填写功能名称】表格数据
      zhuceList: [],
      // 批次字典
      piciOptions: [],
      // 状态字典
      appverOptions: [],
      kaoOptions:[
        {
          value: '首次',
          label: '首次'
        },
        {
          value: '证书',
          label: '证书'
        }
      ],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 部门树选项
      deptOptions: undefined,
      // 导入参数
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        //考试日期
        kaoshiTime:'',
        //机构名称
        deptId:null,
        //祖级列表
        ancestors:null,
        //批次
        pici:''
      },
      res:{msg:'',code:200},
      apper:{ids:'',is:true,deptId:0},
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        username: null,
        cid: null,
        deptId: null,
        pici: null,
        kaoshiTime: null,
        kaoshiType:null,
        status: null
      },
      // 表单参数
      form: {}
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_appver_status").then(response => {
      this.appverOptions = response.data;
    });
    this.getDicts("sys_kaoshi_pici").then(response => {
      this.piciOptions = response.data;
    });
    this.getTreeselect();
  },
  methods: {
    /** 查询【请填写功能名称】列表 */
    getList() {
      this.loading = true;
      listZhuce(this.queryParams).then(response => {
        this.zhuceList = response.rows;
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
    //格式化状态
    ifendcase(val){
      if(val.status=='0'){
        return '等待审核'
      }else if(val.status=='1'){
        return '已通过'
      }else {
        return '已驳回'
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
        zcId: null,
        kaoshiTime: null,
        kaoshiType:null,
        username: null,
        cid: null,
        deptId: null,
        pici: null,
        status: null
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
      this.ids = selection.map(item => item.zcId)
      this.zcIds=selection.map(item=>item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
      this.daoqi=selection[0].daoTime
      this.apper.deptId=selection[0].deptId
    },
    /** 注册详情 */
    handleUpdate(row) {
      this.reset();
      const zcId = row.zcId || this.ids
      getZhuce(zcId).then(response => {
        this.form = response.data;
        this.form.filename='http://221.226.21.180/examinationRY/upload/'+this.form.filename;
        this.open = true;
        this.title = this.form.username+"【注册详情】";
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const zcIds = row.zcId || this.ids;
      this.$confirm('是否确认删除【请填写功能名称】编号为"' + zcIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delZhuce(zcIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 上传时选择机构 */
    handleZong(value){
      if(value){
        this.upload.deptId =value[2];
        this.upload.ancestors=value.toString();
      }else {
        this.upload.deptId = null;
        this.upload.ancestors=null;
      }
    },
    // 导入注册表
    submitFileForm() {
      this.$refs["upload"].validate(valid => {
        if (valid) {
          this.upload.open=false;
          this.upload.ancestors=this.upload.ancestors.toString();
          const h = this.$createElement;
          this.$msgbox({
            title: '消息',
            message: h('p', null, [
              h('span', null, '是否确认要导入?')
            ]),
            showCancelButton: true,
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            beforeClose: (action, instance, done) => {
              if (action === 'confirm') {
                instance.confirmButtonLoading = true;
                instance.confirmButtonText = '执行中...';
                importZhuce(this.upload).then(res=>{
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
        }
      });
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "注册表导入";
      this.upload.open = true;
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有【请填写功能名称】数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportZhuce(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    },
    /** 审核通过 */
    handleTong(row){
      const zcIds = row.id || this.zcIds;
      var end =new Date(this.daoqi);
      var now = new Date();
      if(now.getTime()>end.getTime()){
        this.msgError('审核时间已过期');
        return;
      }else {
        this.apper.ids=zcIds.toString();
        this.apper.is=true;
        const h = this.$createElement;
        this.$msgbox({
          title: '消息',
          message: h('p', null, [
            h('span', null, '是否确认要通过审核?')
          ]),
          showCancelButton: true,
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          beforeClose: (action, instance, done) => {
            if (action === 'confirm') {
              instance.confirmButtonLoading = true;
              instance.confirmButtonText = '执行中...';
              appver(this.apper).then(res=>{
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
      }
    },
    //与报名表核对
    handleHedui(){
      if(this.queryParams.pici==null||this.queryParams.deptId==null){
        this.msgError("请选择批次和机构名称");
        return false;
      }
      const h = this.$createElement;
      this.$msgbox({
        title: '消息',
        message: h('p', null, [
          h('span', null, '是否确认要和报名表进行核对?')
        ]),
        showCancelButton: true,
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        beforeClose: (action, instance, done) => {
          if (action === 'confirm') {
            instance.confirmButtonLoading = true;
            instance.confirmButtonText = '执行中...';
            var parms={pici:this.queryParams.pici,deptId:this.queryParams.deptId}
            hedui(parms).then(res=>{
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
    }
  }
};
</script>
