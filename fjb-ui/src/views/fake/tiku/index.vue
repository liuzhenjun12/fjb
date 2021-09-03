<template>
  <div class="app-container">
    <div class="form_kuand" >
      <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px" class="form_input">
        <el-form-item label="题目标题" prop="title">
          <el-input
            v-model="queryParams.title"
            placeholder="请输入题目标题"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="难度等级" prop="level">
          <el-select
            v-model="queryParams.level"
            placeholder="请输入难度等级"
            clearable
            size="small"
            style="width: 150px"
          >
            <el-option
              v-for="dict in nanduOptions"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="答案类型" prop="title">
          <el-select
            v-model="queryParams.qtype"
            placeholder="请输入难度等级"
            clearable
            size="small"
            style="width: 150px"
          >
            <el-option
              v-for="dict in qtypeOptions"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="题目类别" prop="qcategory">
          <el-select
            v-model="queryParams.qcategory"
            placeholder="请输入难度等级"
            clearable
            size="small"
            style="width: 150px"
          >
            <el-option
              v-for="dict in typeOptions"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
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
            v-hasPermi="['system:danxuan:add']"
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:danxuan:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
    </div>
    <el-table
      v-loading="loading"
      :data="danxuanList"
    >
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand" v-for="item in props.row.ans">
            <el-form-item label="选项">
              <span>{{ item.qind }}：{{ item.qdetail }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="题目标题" min-width="450" align="left" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <router-link :to="'/tiku/an/' + scope.row.id" class="link-type">
            <span>{{ scope.row.title }}</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="是否使用" align="center" prop="status" >
      <template slot-scope="scope">
        <el-switch
          v-model="scope.row.status"
          active-value="0"
          inactive-value="1"
          @change="handleStatusChange(scope.row)"
        ></el-switch>
      </template>
      </el-table-column>
      <el-table-column label="答题类型" align="center" prop="qtype" />
      <el-table-column label="问题答案" align="center" prop="answer" />
      <el-table-column label="难度等级" align="center" prop="level" :filters="[{ text: '简单', value: '简单' }, { text: '普通', value: '普通' }, { text: '困难', value: '困难' }]"
                       :filter-method="filterTag"
                       filter-placement="bottom-end">
      <template slot-scope="scope">
        <el-tag
          :type="scope.row.level === '简单' ? 'primary' : 'success'"
          disable-transitions>{{scope.row.level}}</el-tag>
      </template>
      </el-table-column>
      <el-table-column label="题目类别" align="center" prop="qcategory" :filters="[{ text: '基础知识', value: '基础知识' }, { text: '人民币知识', value: '人民币知识' }, { text: '其他货币知识', value: '其他货币知识' }]"
                       :filter-method="filterTag1"  filter-placement="bottom-end">
        <template slot-scope="scope">
        <el-tag
          :type="scope.row.qcategory === '基础知识' ? 'primary' : scope.row.qcategory=='人民币知识' ? 'success' : 'warning' "
          disable-transitions>{{scope.row.qcategory}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="170"  class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:danxuan:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:danxuan:remove']"
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

    <!-- 添加或修改单选对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="难度等级" prop="level">
          <el-radio-group v-model="form.level">
            <el-radio
              v-for="dict in nanduOptions"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否显示">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="题目类别" prop="qcategory">
          <el-radio-group v-model="form.qcategory">
            <el-radio
              v-for="dict in typeOptions"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="答案类型" prop="qtype">
          <el-radio-group v-model="form.qtype">
            <el-radio
              v-for="dict in qtypeOptions"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="题目标题" prop="title">
          <el-input v-model="form.title" type="textarea" placeholder="请输入题目" />
        </el-form-item>
        <div style="text-align: right;margin-bottom: 20px">
        <el-upload
          :headers="upload.headers"
          :action="upload.url"
          class="upload-demo"
          :limit="1"
          accept="image/jpeg,image/png"
          :file-list="titlepicArr"
          :on-exceed="exceedHandle"
          :on-remove="handleRemove_1"
          :on-success="handleFileSuccess_1"
          list-type="picture">
          <el-button size="small" type="primary" >点击上传标题图片：非必须项，最多上传一张图片</el-button>
        </el-upload>
        </div>

        <el-form-item label="答题选项" >
        <el-table
          v-loading="loading"
          :data="ansList"
          :row-class-name="rowClassName"
          ref="tb"
          border
        >
          <el-table-column label="序号" align="center" prop="xh" min-width="8%"></el-table-column>
          <el-table-column label="选项" align="center" prop="qind" min-width="10%">
            <template slot-scope="scope">
              <el-input v-model="scope.row.qind" placeholder=""  maxlength="1" />
            </template>
          </el-table-column>
          <el-table-column label="内容" align="center" prop="qdetail" min-width="32%">
            <template slot-scope="scope">
              <el-input v-model="scope.row.qdetail" placeholder="请输入选项内容" type="textarea"  maxlength="500" />
            </template>
          </el-table-column>
          <el-table-column label="插图" align="center" prop="qdetail" min-width="30%">
            <template slot-scope="scope">
              <el-upload
                :headers="upload.headers"
                :action="upload.url"
                :http-request="(file)=>{return handleFileSuccess(file,scope.row)}"
                class="upload-demo"
                :limit="1"
                accept="image/jpeg,image/png"
                :on-exceed="exceedHandle"
                :on-preview="handlePreview"
                :on-remove="(file)=>{return handleRemove_2(file,scope.row)}"
                :file-list="scope.row.qpic"
                list-type="picture"
                :ref="scope.row.xh"
              >
                <el-button size="small" type="primary">点击上传插图：非必须项</el-button>
              </el-upload>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" min-width="15%">
            <template slot-scope="scope">
              <el-button type="danger" @click="handleDeleteDetails(scope.xh,scope.row)">删 除</el-button>
            </template>
          </el-table-column>
        </el-table>
          <i class="el-icon-circle-plus-outline" @click="addXuan"></i>
        </el-form-item>

        <el-form-item label="题目答案" prop="answer" :rules="form.qtype=='多选'?[{required: true, message: '题目答案不能为空', trigger: 'blur'},{ min: 3, max: 10, message: '多选时3至10位字符', trigger: 'blur'},{pattern: /^[A-Z]?(?:,[A-Z]?)*$/, message: '请输入大写字母,多选时以逗号分开', trigger: 'blur'}]:[{required: true, message: '题目答案不能为空', trigger: 'blur'},{ min: 1, max: 1, message: '1个大写字母', trigger: 'blur'},{pattern: /^[A-Z]+$/, message: '请输入大写字母,多选时以逗号分开', trigger: 'blur'}]">
          <el-input v-model="form.answer" placeholder="请输入答案，大写字母，多选时以逗号分开，例：A,B,C" />
        </el-form-item>
        <el-form-item label="题目分析" prop="jiexi">
          <el-input v-model="form.jiexi" placeholder="请输入解析" />
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
  import { listDanxuan, getDanxuan, delDanxuan, addDanxuan, updateDanxuan, exportDanxuan,changeDanxuanStatus } from "@/api/tiku/danxuan";
  import { addAn, updateAn, delAn } from "@/api/tiku/an";
  import { getToken } from "@/utils/auth";
  import axios from 'axios'
  export default {
    name: "Danxuan",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 单选表格数据
        danxuanList: [],
        //答案列表
        ansList:[],
        titlepicArr:[],
        //当前状态
        statusOptions:[],
        //难度等级困难 2：普通 3：简单
        nanduOptions:[{value: '简单', label: '简单'}, {value: '普通', label: '普通'}, {value: '困难', label: '困难'}],
        typeOptions:[{value: '基础知识', label: '基础知识'}, {value: '人民币知识', label: '人民币知识'}, {value: '其他货币知识', label: '其他货币知识'}],
        qtypeOptions:[{value: '单选', label: '单选'},{value: '多选', label: '多选'},{value: '判断', label: '判断'}],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        //当前序列号
        xh:0,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          level: null,
          title: null,
          qtype: null,
          qcategory: null
        },
        // 导入参数
        upload: {
          // 设置上传的请求头部
          headers: { Authorization: "Bearer " + getToken() },
          // 上传的地址
          url: process.env.VUE_APP_BASE_API + "/common/upload"
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          level: [
            { required: true, message: "题目难度不能为空", trigger: "blur" }
          ],
          status: [
            { required: true, message: "使用状态不能为空", trigger: "blur" }
          ],
          qcategory: [
            { required: true, message: "题目类别不能为空", trigger: "blur" }
          ],
          qtype: [
            { required: true, message: "答案类型不能为空", trigger: "blur" }
          ],
          title: [
            { required: true, message: "题目标题不能为空", trigger: "blur" }
          ],
          answer: [
            { required: true, message: "题目答案不能为空", trigger: "blur" },
            { min: 1, max: 10, message: '1至10个大写字母', trigger: 'blur'},
            {pattern: /^[A-Z]?(?:,[A-Z]?)*$/, message: "请输入大写字母,多选时以逗号分开", trigger: "blur"},
          ],
        }
      };
    },
    created() {
      this.getList();
      this.getDicts("sys_normal_disable").then(response => {
        this.statusOptions = response.data;
      });
    },
    methods: {
      /** 查询单选列表 */
      getList() {
        this.loading = true;
        listDanxuan(this.queryParams).then(response => {
          this.danxuanList = response.rows;
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
          level: '简单',
          title: null,
          answer: null,
          jiexi: null,
          titlepic: null,
          status: "0",
          qtype: '单选',
          qcategory: '基础知识'
        };
        this.titlepicArr=[]
        this.ansList=[]
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
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加单选";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        getDanxuan(row.id).then(response => {
          console.log(response.data)
          this.form = response.data;
          // this.ansList=response.data.ans;
          var an=response.data.ans

          if(an.length>0) {
            for(var i=0;i<an.length;i++){
              if(an[i].qpic==''){
                let obj={id:an[i].id,qind:an[i].qind,qdetail:an[i].qdetail,qpic:[]}
                this.ansList.push(obj)
              }else {
                let obj={id:an[i].id,qind:an[i].qind,qdetail:an[i].qdetail,qpic:[{name:'选项图',url:process.env.VUE_APP_BASE_API + an[i].qpic}]}
                this.ansList.push(obj)
              }
            }
            // for (var i = 0; i < this.ansList.length; i++) {
            //   if (this.ansList[i].qpic != '' && this.ansList[i].qpic != null) {
            //     let ob={name:'选项图',url:process.env.VUE_APP_BASE_API + this.ansList[i].qpic}
            //       this.ansList[i].qpic.push(ob)
            //   } else {
            //     this.ansList[i].qpic = []
            //   }
            // }
          }
          if(this.form.titlepic!=''&&this.form.titlepic!=null){
            let obj={name:'标题图片',url:this.form.titlepic}
            this.titlepicArr.push(obj)
          }
          this.open = true;
          this.title = "修改单选";
        });
      },
      /** 提交按钮 */
      submitForm() {
        console.log(this.ansList)
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateDanxuan(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                }
              });
            } else {
              if(this.ansList.length>=3){
                let arr=this.ansList;
                for(var i=0;i<arr.length;i++){
                  if(arr[i].qind==''||arr[i].qdetail==''){
                    this.msgError("选项或者选项内容不能为空");
                    return;
                  }
                }
                if(this.titlepicArr.length>0){
                  this.form.titlepic=this.titlepicArr[0].url;
                }
                for(var j=0;j<arr.length;j++){
                  if(arr[j].qpic.length>0){
                    arr[j].qpic=arr[j].qpic[0].url
                  }
                }
                this.form.remark=JSON.stringify(arr)
                addDanxuan(this.form).then(response => {
                  if(response.code===200){
                    this.msgSuccess("新增成功");
                    this.open = false;
                    this.getList();
                  }
                })
              }else {
                this.msgError("至少新增3条选项！");
                return;
              }
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        this.$confirm('是否确认删除单选编号为"' + row.id + '"的数据项（包含子项一起删除）?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDanxuan(row.id);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有单选数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportDanxuan(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
      },
      handleStatusChange(row){
        let text = row.status === "0" ? "启用" : "停用";
        this.$confirm('确认要"' + text + '这条题库吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return changeDanxuanStatus(row.id, row.status);
        }).then(() => {
          this.msgSuccess(text + "成功");
        }).catch(function() {
          row.status = row.status === "0" ? "1" : "0";
        });
      },
      filterTag(value, row) {
        return row.level === value;
      },
      filterTag1(value, row){
        return row.qcategory === value;
      },
      filterHandler(value, row, column) {
        const property = column['property'];
        return row[property] === value;
      },
      onFileChange(file){
      console.log(file)
      },
      //移除标题图片
      handleRemove_1(file){
        let config = {
          headers: {
            'Content-Type': 'multipart/form-data', 'Authorization': 'Bearer ' + getToken()
          }
        }
        var formData = new FormData();
        formData.append('filePath', file.url);
        axios.post(process.env.VUE_APP_BASE_API+"/common/unin", formData,config).then(res => {
          if (res.status === 200) {
            this.titlepicArr=[]
          }
        }).catch(res => {
          this.msgError('网络异常请重新上传!')
        })
      },
      //移除选项图片
      handleRemove_2(file,row){
        let config = {
          headers: {
            'Content-Type': 'multipart/form-data', 'Authorization': 'Bearer ' + getToken()
          }
        }
        var formData = new FormData();
        formData.append('filePath', file.url);
        axios.post(process.env.VUE_APP_BASE_API+"/common/unin", formData,config).then(res => {
          if (res.status === 200) {
            this.ansList[row.xh - 1].qpic = []
          }
        }).catch(res => {
          this.msgError('网络异常请重新上传!')
        })
      },
      //文件超出个数限制时的钩子
      exceedHandle(file){
        this.msgError('上传图片不能超过1张!')
      },
      handlePreview(file) {
      },
      handleFileSuccess_1(response, file, fileList){
        var obj={name:response.fileName.substring(response.fileName.lastIndexOf("/")+1),url:response.url};
        this.titlepicArr.push(obj)
      },
      /** 文件上传成功处理 */
      handleFileSuccess(file, row) {
        if(file.file.size>512000){
          this.msgError('上传文件不能大于500KB!')
          this.ansList[row.xh - 1].qpic = []
          return;
        }
        var formData = new FormData();
        formData.append('file', file.file);
        let config = {
          headers: {
            'Content-Type': 'multipart/form-data', 'Authorization': 'Bearer ' + getToken()
          }
        }
        axios.post(process.env.VUE_APP_BASE_API+"/common/upload", formData,config).then(res => {
          if (res.status === 200) {
            console.log(res.data)
                var o=this.ansList;
                var obj = [{name: res.data.fileName.substring(res.data.fileName.lastIndexOf("/") + 1), url: res.data.url}]
                o[row.xh - 1].qpic = obj
          }
        }).catch(res => {
          this.msgError('网络异常请重新上传!')
        })
      },
      rowClassName({ row, rowIndex }) {
        row.xh = rowIndex + 1;
      },
      //添加行
      addXuan(){
        let obj={qind:String.fromCharCode('A'.charCodeAt(0)+this.ansList.length),qdetail:'',qpic:[]}
        this.ansList.push(obj)
      },
      //删除行
      handleDeleteDetails(xh,row) {
        this.$confirm('确定删除吗?','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning'
        }).then(()=>{
          var o=this.ansList;
          for(var i=0;i<o.length;i++){
            if(row.xh==o[i].xh){
              this.ansList.splice(i,1);
            }
          }
        })
      },
    }
  };
</script>
<style lang="scss" scoped>
  .biao{
    table-layout: fixed;width: 100%;
    th{border:1px solid #C0C4CC;}
    td{border:1px solid #C0C4CC;}
  }
  .el-table__header-wrapper th, .el-table__fixed-header-wrapper th{
    background-color: white;
  }
</style>
