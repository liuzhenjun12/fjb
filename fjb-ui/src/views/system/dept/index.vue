<template>
  <div class="app-container">
    <div class="sanguang">
    <el-row :gutter="10" class="gao">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:dept:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="shuaxin"></right-toolbar>
    </el-row>
    </div>
    <el-table
      v-loading="loading"
      element-loading-text="系统正拼命加载中"
      element-loading-spinner="el-icon-loading"
      :data="deptList"
      lazy=""
      :load="loadChildren"
      row-key="deptId"
      ref="tableFee"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column prop="deptName" label="机构名称" min-width="30%" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="province.proname" label="省" align="center" min-width="7%" ></el-table-column>
      <el-table-column prop="city.cname" label="市" align="center" min-width="7%" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="town.tname" label="县(区)" align="center" min-width="10%" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="jianCheng" label="机构简称" align="center" min-width="15%" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="jigouCode" label="机构代码" align="center" min-width="15%" :show-overflow-tooltip="true"></el-table-column>
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
            <el-form-item  label="所属省份" prop="proid">
              <el-select
                v-model="form.proid"
                @change="selectSheng"
                placeholder="请选择省份"
              >
                <el-option
                  v-for="item in shengOptions"
                  :key="item.proid"
                  :label="item.proname"
                  :value="item.proid"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item  label="所属城市" prop="cid">
              <el-select
                v-model="form.cid"
                @change="selectShi"
                placeholder="请选择城市"
              >
                <el-option
                  v-for="item in shiOptions"
                  :key="item.cid"
                  :label="item.cname"
                  :value="item.cid"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item  label="所属县区" prop="tid">
              <el-select
                v-model="form.tid"
                @change="selectXian"
                placeholder="请选择县区"
              >
                <el-option
                  v-for="item in xianOptions"
                  :key="item.tid"
                  :label="item.tname"
                  :value="item.tid"
                />
              </el-select>
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
            <el-form-item label="鉴定单位">
              <el-radio-group v-model="form.isJianDing">
                <el-radio
                  v-for="dict in statusOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{dict.dictLabel}}</el-radio>
              </el-radio-group>
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
import { listDept, getDept, delDept, addDept, updateDept, listDeptExcludeChild,nextDept,listAll,listProvince,listCity,listTown } from "@/api/system/dept";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
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
      // 是否数据字典
      statusOptions: [],
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
        proid:[
          { required: true, message: "所属省份不能为空", trigger: "blur" }
        ],
        shi:[
          { required: true, message: "所属城市不能为空", trigger: "blur" }
        ],
        xian:[
          { required: true, message: "所属县区不能为空", trigger: "blur" }
        ],
        jigouCode:[
          { required: true, message: "机构代码不能为空", trigger: "blur" },
          { min: 8, max: 15, message: '8~15位数字或字母', trigger: 'blur'},
          {pattern: /^[A-Z0-9]+$/, message: "请输入大写字母+数字的机构代码", trigger: "blur"}
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
    this.getDicts("sys_yes_no").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询部门列表 */
    getList() {
      this.loading = true;
      listDept().then(response => {
        this.deptList = this.handleTree(response.data, "deptId");
        this.loading = false;
      });
    },
    shuaxin(){
      this.deptList=[]
      this.getList();
    },
    /** 获取省 */
    getSheng(){
      listProvince().then(response => {
        this.shengOptions = response.data;
      });
    },
    /** 获取市 */
    getShi(val){
      var obj={proid:val}
      listCity(obj).then(response => {
        this.shiOptions = response.data;
      });
    },
    /** 获取县 */
    getXian(val){
      var obj={cid:val}
      listTown(obj).then(response => {
        this.xianOptions = response.data;
      });
    },
    /** 机构省选择后带出市 */
    selectSheng(val){
      this.form.cid=undefined;
      this.form.tid=undefined;
      this.form.jinDu=undefined;
      this.form.weiDu=undefined;
      this.form.proid=val;
      this.shiOptions=[];
      this.xianOptions=[];
      var obj={proid:val};
      listCity(obj).then(response => {
        this.shiOptions = response.data;
      });
    },
    /** 机构市选择带出县*/
    selectShi(val){
      this.form.tid=undefined;
      this.form.jinDu=undefined;
      this.form.weiDu=undefined;
      this.form.cid=val;
      this.xianOptions=[];
      var obj={cid:val};
      listTown(obj).then(response => {
        this.xianOptions = response.data;
      });
    },
    /** 机构县选择 */
    selectXian(val){
      this.form.tid=val;
      for(var i=0;i<this.xianOptions.length;i++){
        if(val==this.xianOptions[i].tid){
          this.form.jinDu=this.xianOptions[i].jingDu;
          this.form.weiDu=this.xianOptions[i].weiDu;
          continue;
        }
      }
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
        phone: undefined,
        jianCheng: undefined,
        jigouCode: undefined,
        weiDu: undefined,
        jinDu:undefined,
        isJianDing: "N",
        proid:undefined,
        cid:undefined,
        tid:undefined
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
        this.form.cid=row.cid;
        this.form.proid=row.proid;
        this.getShi(row.proid);
        this.getXian(row.cid)
      } else {
        this.form.parentId = 0;
        this.getConfigKey("sys.province.id").then(response => {
          if(response.msg!='') {
            this.form.proid = parseInt(response.msg);
          }
          this.getShi(parseInt(response.msg))
        });
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
      this.getShi(row.proid);
      this.getXian(row.cid)
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
