<template>
  <div class="app-container">
<!--    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" class="form_input">-->
<!--      <el-form-item label="机构名称" prop="deptName">-->
<!--        <el-input-->
<!--          v-model="queryParams.deptName"-->
<!--          placeholder="请输入机构名称"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="机构代码" prop="jigouCode">-->
<!--        <el-input-->
<!--          v-model="queryParams.jigouCode"-->
<!--          placeholder="请输入部门机构代码"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="状态" prop="status">-->
<!--        <el-select v-model="queryParams.status" placeholder="部门状态" clearable size="small">-->
<!--          <el-option-->
<!--            v-for="dict in statusOptions"-->
<!--            :key="dict.dictValue"-->
<!--            :label="dict.dictLabel"-->
<!--            :value="dict.dictValue"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>-->
<!--        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->
    <div class="sanguang">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:dept:add']"
        >新增</el-button>
      </el-col>
<!--      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>-->
    </el-row>
    </div>
    <el-table
      v-loading="loading"
      element-loading-text="系统正拼命加载中"
      element-loading-spinner="el-icon-loading"
      :data="deptList"
      lazy=""
      row-key="deptId"
      :load="loadChildren"
      :expand-row-keys="['100']"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column prop="deptName" label="机构名称" min-width="40%" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="jigouCode" label="机构代码" align="center" min-width="11%"></el-table-column>
      <el-table-column prop="deptType" label="机构类型" align="center" :formatter="deptTypeFormat" min-width="8%"></el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" min-width="15%">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="orderNum" label="排序" align="center" min-width="5%"></el-table-column>
      <el-table-column label="操作" min-width="15%" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:dept:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['system:dept:add']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:dept:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改部门对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="24" >
            <span style="margin-bottom: 30px;display: block;color: blue;">请以省行、市行、县(区)行、镇(乡)行的结构设置机构</span>
          </el-col>
          <el-col :span="24">
            <el-form-item label="上级机构" prop="parentId">
              <treeselect v-model="form.parentId" :options="deptOptions" :normalizer="normalizer" placeholder="选择上级部门" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="机构全称" prop="deptName">
              <el-input v-model="form.deptName" placeholder="请输入机构全称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="显示排序" prop="orderNum">
              <el-input-number v-model="form.orderNum" controls-position="right" :min="0" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="机构简称" prop="jianCheng">
              <el-input v-model="form.jianCheng" placeholder="请输入机构简称" maxlength="50" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入联系电话" maxlength="11" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="机构代码" prop="jigouCode">
              <el-input v-model="form.jigouCode" placeholder="请输入机构代码" maxlength="20" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="印章代码" prop="yinCode">
              <el-input v-model="form.yinCode" placeholder="请输入印章代码" maxlength="25" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地图经度" prop="jinDu">
              <el-input v-model="form.jinDu" placeholder="请输入地图经度" maxlength="10" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地图纬度" prop="weiDu">
              <el-input v-model="form.weiDu" placeholder="请输入地图纬度" maxlength="10" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item  label="所属省份" prop="sheng">
              <el-select
                v-model="form.sheng"
                @change="selectSheng"
                placeholder="请选择省份"
              >
                <el-option
                  v-for="item in shengOptions"
                  :key="item.name"
                  :label="item.name"
                  :value="item.name"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item  label="所属城市" prop="shi">
              <el-select
                v-model="form.shi"
                @change="selectShi"
                placeholder="请选择城市"
              >
                <el-option
                  v-for="item in shiOptions"
                  :key="item.name"
                  :label="item.name"
                  :value="item.name"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item  label="所属县区" prop="xian">
              <el-select
                v-model="form.xian"
                @change="selectXian"
                placeholder="请选择县区"
              >
                <el-option
                  v-for="item in xianOptions"
                  :key="item.name"
                  :label="item.name"
                  :value="item.name"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="机构状态">
              <el-radio-group v-model="form.status">
                <el-radio
                  v-for="dict in statusOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="鉴定单位">
              <el-radio-group v-model="form.leader">
                <el-radio
                  v-for="dict in leaderOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="具体地址"  prop="address">
              <el-input v-model="form.address" placeholder="请输入机构具体地址" maxlength="50" />
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
import { listDept, getDept, delDept, addDept, updateDept, listDeptExcludeChild,nextDept,listAll } from "@/api/system/dept";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import { listCity } from "@/api/system/city";
export default {
  name: "Dept",
  components: { Treeselect },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 表格树数据
      deptList: [],
      // 部门树选项
      deptOptions: [],
      //机构类型
      deptTypeOptions:[],
      //机构省选择
      shengOptions:[],
      //机构市选择
      shiOptions:[],
      //机构县选择
      xianOptions:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 状态数据字典
      statusOptions: [],
      //是否是鉴定单位
      leaderOptions:[],
      //网点类型
      wangTypeOptions:[],
      // 查询参数
      queryParams: {
        deptName: undefined,
        status: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        parentId: [
          { required: true, message: "上级机构不能为空", trigger: "blur" }
        ],
        deptName: [
          { required: true, message: "机构名称不能为空", trigger: "blur" }
        ],
        orderNum: [
          { required: true, message: "机构顺序不能为空", trigger: "blur" }
        ],
        jianCheng:[
          { required: true, message: "机构简称不能为空", trigger: "blur" }
        ],
        sheng:[
          { required: true, message: "所属省份不能为空", trigger: "blur" }
        ],
        shi:[
          { required: true, message: "所属城市不能为空", trigger: "blur" }
        ],
        xian:[
          { required: true, message: "所属县区不能为空", trigger: "blur" }
        ],
        phone: [
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur"
          }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getSheng();
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.leaderOptions = response.data;
    });
    this.getDicts("sys_dept_type").then(response => {
      this.deptTypeOptions = response.data;
    });
    this.getDicts("sys_wang_type").then(response => {
      this.wangTypeOptions = response.data;
    });
  },
  methods: {
    /** 查询部门列表 */
    getList() {
      this.loading = true;
      listDept(this.queryParams).then(response => {
        this.deptList = this.handleTree(response.data, "deptId");
        this.loading = false;
      });
    },
    /** 获取省 */
    getSheng(){
      var obj={cityType:'1'}
      listCity(obj).then(response => {
        this.shengOptions = response.data;
      });
    },
    /** 获取市 */
    getShi(val){
      var obj={cityType:'2',parentId:val}
      listCity(obj).then(response => {
        this.shiOptions = response.data;
      });
    },
    /** 获取县 */
    getXian(val){
      var obj={cityType:'3',parentId:val}
      listCity(obj).then(response => {
        this.xianOptions = response.data;
      });
    },
    /** 机构省选择 */
    selectSheng(val){
      this.form.shi=undefined;
      this.form.sheng=val;
      this.shengOptions.forEach((res, index) => {
        if(val==res.name){
          var obj={cityType:'2',parentId:res.id}
          listCity(obj).then(response => {
            this.shiOptions = response.data;
          });
        }
      });
    },
    /** 机构市选择 */
    selectShi(val){
      this.form.xian=undefined;
      this.form.shi=val;
      this.shiOptions.forEach((res, index) => {
        if(val==res.name){
          var obj={cityType:'3',parentId:res.id}
          listCity(obj).then(response => {
            this.xianOptions = response.data;
          });
        }
      });
    },
    /** 机构县选择 */
    selectXian(){
      this.form.xian=val;
    },
    /** 查询菜单下拉树结构 */
    getTreeselect() {
      listAll().then(response => {
        this.deptOptions = [];
        const dept = { deptId: 0, parentId:0,deptName: '最上级', children: [] };
        dept.children = this.handleTree(response.data, "deptId");
        this.deptOptions.push(dept);
      });
    },
    /** 延迟加载子节点 */
    loadChildren(tree, treeNode, resolve){
      nextDept(tree.deptId).then(response => {
        resolve(response.data)
      });
    },
    /** 转换部门数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.deptId,
        label: node.deptName,
        children: node.children
      };
    },
    // 字典状态字典翻译
    deptTypeFormat(row, column) {
      return this.selectDictLabel(this.deptTypeOptions, row.deptType);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        deptId: undefined,
        parentId: undefined,
        deptName: undefined,
        orderNum: undefined,
        leader: 'N',
        phone: undefined,
        jianCheng: undefined,
        weiDu: undefined,
        jinDu:undefined,
        yinCode:undefined,
        address:undefined,
        status: "0",
        deptType:"3",
        wangCode:undefined,
        wangType:'',
        sheng:undefined,
        shi:undefined,
        xian:undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.deptId) {
        this.form.parentId = row.deptId;
      } else {
        this.form.parentId = 0;
      }
      this.open = true;
      this.title = "添加机构";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      getDept(row.deptId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改机构";
      });
      listDeptExcludeChild(row.deptId).then(response => {
        if(row.parentId==0){
          this.deptOptions = [];
          const dept = { deptId: 0, parentId:0,deptName: '最上级', children: [] };
          dept.children = this.handleTree(response.data, "deptId");
          this.deptOptions.push(dept);
        }else {
          this.deptOptions = this.handleTree(response.data, "deptId");
        }
      });
      if(row.shi){
        var obj={cityType:'1',name:row.sheng}
        listCity(obj).then(response => {
          this.getShi(response.data[0].id)
        });
      }
      if(row.xian){
        var obj={cityType:'2',name:row.shi}
        listCity(obj).then(response => {
          this.getXian(response.data[0].id)
        });
      }
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.deptId != undefined) {
            updateDept(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addDept(this.form).then(response => {
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
      this.$confirm('是否确认删除名称为"' + row.deptName + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDept(row.deptId);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    }
  }
};
</script>
