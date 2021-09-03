<template>
  <div class="biaodan">
    <el-form ref="form" :model="form" :rules="rules"  label-width="140px" class="form_input">
      <el-row >
        <el-col :span="24" style="text-align: center">
          <h3>货币真伪鉴定书</h3>
        </el-col>
        <el-col :span="24" style="text-align: left">
          <h5>网点信息</h5>
        </el-col>
        <el-col :span="8">
          <el-form-item label="鉴定单位:"  prop="deptName">
            <el-input v-model="form.deptName" :disabled="true" placeholder="网点名称"  clearable  size="small" style="width: 90%"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="登录账号:" prop="userName">
            <el-input v-model="form.userName" :disabled="true" placeholder="登录账号" clearable  size="small" style="width: 90%"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="鉴定日期:" prop="identifydate">
            <el-date-picker clearable size="small" style="width: 90%"
                            v-model="form.identifydate"
                            type="date"
                            :disabled="disabled_date"
                            value-format="yyyy-MM-dd"
                            placeholder="选择收缴日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="24" style="text-align: left">
          <h5>来源信息</h5>
        </el-col>
        <el-col :span="8">
          <el-form-item label="持有人:" prop="holderName">
            <el-input v-model="form.holderName"  :disabled="disabled_date" placeholder="请输入送签单位或持有人" clearable  size="small"  maxlength="10" style="width: 90%"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="证件号码:" prop="holderIdcard">
            <el-input v-model="form.holderIdcard"  :disabled="disabled_date" placeholder="请输入持有人证件号" clearable  size="small"  maxlength="10" style="width: 90%"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系电话:" prop="holderTelephone">
            <el-input v-model="form.holderTelephone"  :disabled="disabled_date" placeholder="请输入送签单位或持有人联系电话" clearable  size="small"  maxlength="11" style="width: 90%"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="原鉴定单位:"  prop="originalcffi">
            <el-input v-model="form.originalcffi"  :disabled="disabled_date" placeholder="请输入原鉴定单位"  maxlength="15" size="small"  style="width: 90%"/>
          </el-form-item>
        </el-col>
        <el-col :span="15">
          <el-form-item label="原鉴定结果:"  prop="originalresult">
            <el-input v-model="form.originalresult" placeholder="请输入原鉴定结果"  maxlength="15" size="small"  style="width: 100%"/>
          </el-form-item>
        </el-col>
        <el-col :span="24" style="text-align: left">
          <h5>假币信息</h5>
        </el-col>
        <el-col :span="8">
          <el-form-item label="假币币种:" style="margin-left: 10px" prop="ccCurrency">
            <el-select
              v-model="form.ccCurrency"
              placeholder="请选择假币币种"
              clearable
              size="small"
              style="width: 90%"
            >
              <el-option
                v-for="dict in ccCurrencyOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="假币劵别:" prop="ccDenomination">
            <el-select
              v-model="form.ccDenomination"
              placeholder="请选择假币卷别"
              clearable
              size="small"
              style="width: 90%"
              @change="changeJuan"
              @clear="clearJuan"
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


        <el-col :span="8">
          <el-form-item label="假币版别:" style="margin-left: 10px" prop="ccSeries">
            <el-select
              v-model="form.ccSeries"
              placeholder="请选择假币版别"
              clearable
              size="small"
              style="width: 90%"
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

        <el-col :span="8">
          <el-form-item label="冠字号码:" style="margin-left: 10px" prop="serialNumber">
            <el-input v-model="form.serialNumber" placeholder="请输入冠字号码"  maxlength="15" size="small"  style="width: 90%"/>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="收缴数量:" prop="amount">
            <el-input-number v-model="form.amount" controls-position="right" :min="1" @change="changeAmount" size="small" style="width: 90%" />
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="收缴总额:" style="margin-left: 10px" prop="totalamount">
            <el-input-number v-model="form.totalamount" controls-position="right" :min="1" size="small" style="width: 90%" :disabled="true" />
          </el-form-item>
        </el-col>

        <el-col :span="24" style="text-align: left">
          <h5>鉴定结果</h5>
        </el-col>

        <el-col :span="23" >
          <el-form-item label="鉴定结果:" prop="result">
            <el-input v-model="form.result" placeholder="请输入鉴定结果" clearable  size="small"  maxlength="10" style="width: 100%"/>
          </el-form-item>
        </el-col>

        <el-col :span="23" >
          <el-form-item label="详细结果" prop="resultdetails">
            <el-input v-model="form.resultdetails" type="textarea"   maxlength="200" placeholder="请输入详细结果" ></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="23" >
          <el-form-item label="其他备注" prop="remark">
            <el-input v-model="form.remark" type="textarea"   maxlength="200" placeholder="请输入备注" ></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="24" style="text-align: left">
          <h5>鉴定图片</h5><p style="color: blue">只能上传jpg/png格式图片，而且最少上传{{zhang}}张，最多上传8张，其他包括鉴定图片及鉴定证书图片，每张图片的大小不得超过150KB!</p>
        </el-col>
        <el-col :span="24">
          <el-upload
            action="#"
            :headers="upload.headers"
            list-type="picture-card"
            :file-list="fileList"
            :before-upload="onBeforeUpload"
            :on-change="onFileChange"
            :disabled="upload.isUploading"
            :multiple="upload.multiple"
            :on-progress="handleFileUploadProgress"
            accept="image/jpeg,image/png"
            :limit="8"
            :on-exceed="exceedHandle"
            ref="dynamic"
            :http-request="uploadFile"
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
                  <span
                    v-if="!disabled"
                    class="el-upload-list__item-delete"
                    @click="handleRemove(file)"
                  >
                    <i class="el-icon-delete"></i>
                  </span>
                </span>
            </div>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-col>
        <el-col :span="24" style="text-align: left">
          <h5>鉴定人员</h5>
        </el-col>
        <el-col :span="8">
          <el-form-item label="鉴定人:" prop="identifyname">
            <el-input v-model="form.identifyname"  :disabled="disabled_date" placeholder="请输入鉴定人姓名" clearable  size="small"  maxlength="10" style="width: 90%"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="复核人:" prop="checkupname">
            <el-input v-model="form.checkupname"  :disabled="disabled_date" placeholder="请输入复核人姓名" clearable  size="small"  maxlength="10" style="width: 90%"/>
          </el-form-item>
        </el-col>
        <el-col :span="24" style="text-align: left">
          <h5>信息提交</h5>
        </el-col>
        <el-col :span="24" style="text-align: center">
          <el-button type="primary" @click="submitForm">提 交</el-button>
          <el-button @click="cancel" style="margin-left: 20px;">重 置</el-button>
        </el-col>
      </el-row>

    </el-form>
  </div>
</template>

<script>
  import axios from 'axios'
  import { getToken } from "@/utils/auth";
  import {getExpertisereport} from "@/api/business/expertisereport";
  export default {
    name: "updateExpertisereport",
    data() {
      return {
        //最少上传多少张图片
        zhang:2,
        dialogImageUrl: '',
        //动态窗口是否显示
        dialogVisible: false,
        //是否禁用
        disabled: false,
        disabled_date:false,
        //图片集合
        fileList: [],
        //假币特征是否全选
        checkAll: false,
        // 假币收缴凭证表格数据
        receiptseizureList: [],
        //币种
        ccCurrencyOptions:[],
        //卷别
        ccDenominationOptions:[],
        //版别
        ccSeriesOptions:[],
        //证件类型
        certificateOptions:[],
        // 上传图片参数
        upload: {
          //上传多个
          multiple:true,
          //图片存储
          formDate:"",
          // 是否禁用上传
          isUploading: false,
          // 设置上传的请求头部
          headers: { Authorization: "Bearer " + getToken() },
          // 上传的地址
          url: process.env.VUE_APP_BASE_API + "/business/expertisereport/update"
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          identifydate: [
            { required: true, message: "鉴定日期不能为空", trigger: "blur" }
          ],
          identifyname: [
            { required: true, message: "鉴定人姓名不能为空", trigger: "blur" },
            { min: 2, max: 5, message: '2~5个字符', trigger: 'blur'}
          ],
          checkupname: [
            { required: true, message: "复核人姓名不能为空", trigger: "blur" },
            { min: 2, max: 5, message: '2~5个字符', trigger: 'blur'}
          ],
          holderName: [
            { required: true, message: "送签单位或持有人姓名不能为空", trigger: "blur" },
            { min: 2, max: 5, message: '2~5个字符', trigger: 'blur'}
          ],
          originalcffi: [
            { required: true, message: "原鉴定单位名称不能为空", trigger: "blur" },
            { min: 4, max: 20, message: '4~20个字符', trigger: 'blur'}
          ],
          originalresult: [
            { required: true, message: "原鉴定结果不能为空", trigger: "blur" },
            { min: 4, max: 50, message: '4~50个字符', trigger: 'blur'}
          ],
          result: [
            { required: true, message: "现鉴定结果不能为空", trigger: "blur" },
            { min: 4, max: 50, message: '4~50个字符', trigger: 'blur'}
          ],
          resultdetails: [
            { required: true, message: "现鉴定详细结果不能为空", trigger: "blur" },
            { min: 4, max: 200, message: '4~200个字符', trigger: 'blur'}
          ],
          ccCurrency: [
            { required: true, message: "币种不能为空", trigger: "blur" }
          ],
          ccDenomination: [
            { required: true, message: "卷别不能为空", trigger: "blur" }
          ],
          ccSeries: [
            { required: true, message: "版别不能为空", trigger: "blur" }
          ],
          serialNumber: [
            { required: true, message: "假币冠字号码不能为空", trigger: "blur" },
            { min: 8, max: 12, message: '8~12个字符', trigger: 'blur'},
            {pattern: /^[A-Za-z0-9]+$/, message: "请输入字母+数字的冠字号码", trigger: "blur"}
          ],
          amount: [
            { required: true, message: "收缴数量不能为空", trigger: "blur" }
          ],
          totalamount: [
            { required: true, message: "收缴总额不能为空", trigger: "blur" }
          ],
          deptName: [
            { required: true, message: "网点名称不能为空", trigger: "blur" }
          ],
          userName: [
            { required: true, message: "登录账号不能为空", trigger: "blur" }
          ],
          holderIdcard: [
            { required: true, message: "持有人证件号码不能为空", trigger: "blur" },
            {pattern: /^[A-Za-z0-9]+$/, message: "请输入字母+数字的证件号码", trigger: "blur"},
            { min: 8, max: 20, message: '5~20个数字或字母', trigger: 'blur'}
          ],
          holderTelephone: [
            { required: true, message: "持有人手机号码不能为空", trigger: "blur" },
            {
              pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
              message: "请输入正确的手机号码",
              trigger: "blur"
            }
          ],
        }
      }
    },
    created() {
      this.getExpertisereport();
      this.getDicts("sys_money_type").then(response => {
        this.ccCurrencyOptions = response.data;
      });
      this.getDicts("sys_denomination").then(response => {
        this.ccDenominationOptions = response.data;
      });
      this.getDicts("sys_money_version").then(response => {
        this.ccSeriesOptions = response.data;
      });
      this.getDicts("sys_certificate_type").then(response => {
        this.certificateOptions = response.data;
      });
      this.getConfigKey("sys_shou_number").then(response => {
        if(response.msg) {
          this.zhang = parseInt(response.msg);
        }
      })
    },
    methods: {
      getExpertisereport(){
        const id = this.$route.params && this.$route.params.id;
        getExpertisereport(id).then(response => {
          this.form = response.data
          if(response.data.parentId>0){
            this.disabled_date=true;
          }
          this.form.userName=response.data.createBy;
          this.form.deptName=response.data.dept.deptName
          if(response.data.imgList!=''&&response.data.imgList!=null){
            this.fileList=JSON.parse(response.data.imgList);
            for(var i=0;i<this.fileList.length;i++){
              this.fileList[i].url=process.env.VUE_APP_BASE_API+this.fileList[i].url;
            }
          }
        });
      },
      // 取消按钮
      cancel() {
        this.reset();
      },
      // 表单重置
      reset() {
        this.getExpertisereport();
        this.resetForm("form");
      },
      /** 修改数量时 */
      changeAmount(currentValue){
        if(this.form.ccDenomination!=undefined){
          this.form.totalamount=currentValue*parseInt(this.form.ccDenomination)
        }else {
          this.form.totalamount=1
        }
      },
      /** 修改卷别时 */
      changeJuan(currentValue){
        this.form.totalamount=this.form.amount*parseInt(currentValue)
      },
      /** 清除卷别时 */
      clearJuan(){
        this.form.totalamount=1;
        this.form.amount=1
      },
      //覆盖默认的上传方法
      uploadFile(file){
        this.upload.formDate.append('file', file.file);
      },
      //上传文件前
      onBeforeUpload(file){
        if(file.size>153600){
          return false;
        }
      },
      //文件超出个数限制时的钩子
      exceedHandle(file,fileList){
        this.msgError('上传图片不能超过8张!')
      },
      //图片下载
      handleDownload(file){
        window.location.href = process.env.VUE_APP_BASE_API + "/common/download/resource?name=" + encodeURI(file.url);
      },
      //移除文件
      handleRemove(file) {
        this.upload.formDate = new FormData();
        this.upload.formDate.append('id', this.form.id);
        this.upload.formDate.append('status', "del");
        this.upload.formDate.append('imgList', file.name);
        let config = {
          headers: {
            'Content-Type': 'multipart/form-data', 'Authorization': 'Bearer ' + getToken()
          }
        }
        axios.post(process.env.VUE_APP_BASE_API+"/business/expertisereport/update", this.upload.formDate, config).then(res => {
          if (res.data.code === 200) {
            this.msgSuccess("删除成功");
            this.upload.isUploading = false;
            let arr=this.fileList
            let num=0;
            this.fileList.map(item=>{
              if(item.name==file.name){
                this.fileList.splice(num,1);
              }
              num++;
            })
          }
        }).catch(res => {
          this.msgError('网络异常请重新提交!')
        })
      },
      /** 文件上传中处理 */
      handleFileUploadProgress(event, file, fileList) {
        this.upload.isUploading = true;
      },
      onFileChange(file, fileList){
        this.upload.formDate = new FormData();
        this.$refs['dynamic'].submit();
        this.upload.formDate.append('id', this.form.id);
        this.upload.formDate.append('deptId', this.form.deptId);
        this.upload.formDate.append('status', "add");
        let config = {
          headers: {
            'Content-Type': 'multipart/form-data', 'Authorization': 'Bearer ' + getToken()
          }
        }
        axios.post(process.env.VUE_APP_BASE_API+"/business/expertisereport/update", this.upload.formDate, config).then(res => {
          if (res.data.code === 200) {
            this.msgSuccess("上传成功");
            this.upload.isUploading = false;
            let obj={name:res.data.data.name,url:process.env.VUE_APP_BASE_API+res.data.data.url}
            this.fileList.push(obj)
          }
        }).catch(res => {
          this.msgError('网络异常请重新提交!')
        })
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },
      //提交表单
      submitForm: function(){
        this.$refs["form"].validate(valid => {
          if (valid) {
            if(this.fileList.length>=this.zhang) {
                this.upload.formDate = new FormData();
                this.upload.formDate.append('id', this.form.id);
                this.upload.formDate.append('userId', this.form.userId);
                this.upload.formDate.append('deptId', this.$store.state.user.deptId);
                if(this.form.identifydate instanceof Date){
                  let date = new Date()
                  var defalutEndTime = date.getFullYear() + '-' + ((date.getMonth() + 1) >= 10 ? (date.getMonth() + 1) : '0' + (date.getMonth() + 1)) + '-' + (date.getDate() >= 10 ? date.getDate() : '0' + date.getDate())
                  this.upload.formDate.append('identifydate', defalutEndTime);
                }else {
                  this.upload.formDate.append('identifydate', this.form.identifydate);
                }
                this.upload.formDate.append('amount', this.form.amount);
                this.upload.formDate.append('totalamount', this.form.totalamount);
                this.upload.formDate.append('holderName', this.form.holderName);
                this.upload.formDate.append('holderIdcard', this.form.holderIdcard);
                this.upload.formDate.append('holderTelephone', this.form.holderTelephone);
                this.upload.formDate.append('ccCurrency', this.form.ccCurrency);
                this.upload.formDate.append('ccDenomination', this.form.ccDenomination);
                this.upload.formDate.append('ccSeries', this.form.ccSeries);
                this.upload.formDate.append('identifyname', this.form.identifyname);
                this.upload.formDate.append('checkupname', this.form.checkupname);
                this.upload.formDate.append('remark', this.form.remark);
                this.upload.formDate.append('serialNumber', this.form.serialNumber);
                this.upload.formDate.append('createBy', this.$store.state.user.name);
                this.upload.formDate.append('result',this.form.result);
                this.upload.formDate.append('resultdetails', this.form.resultdetails);
                this.upload.formDate.append('originalcffi',this.form.originalcffi);
                this.upload.formDate.append('originalresult', this.form.originalresult);
                let config = {
                  headers: {
                    'Content-Type': 'multipart/form-data', 'Authorization': 'Bearer ' + getToken()
                  }
                }
                axios.post(process.env.VUE_APP_BASE_API+"/business/expertisereport/update", this.upload.formDate, config).then(res => {
                  if (res.data.code === 200) {
                    this.$confirm('新增成功, 是否要关闭此页面?', '提示', {
                      confirmButtonText: '确定',
                      cancelButtonText: '取消',
                      type: 'warning'
                    }).then(() => {
                      // 调用全局挂载的方法,关闭当前标签页
                      this.$store.dispatch("tagsView/delView", this.$route);
                      // 返回上一步路由，返回上一个标签页
                      this.$router.go(-1);
                      // this.$store.state.tagsView.visitedViews.splice(this.$store.state.tagsView.visitedViews.findIndex(item => item.path === this.$route.path), 1)
                      // this.$router.push(this.$store.state.tagsView.visitedViews[this.$store.state.tagsView.visitedViews.length-1].path)
                    }).catch(() => {
                    });
                  }
                }).catch(res => {
                  this.msgError('网络异常请重新提交!')
                })
            }else {
              this.msgError('最少上传"'+this.zhang+'"张假币图片!')
            }
          }else {
            this.msgError('请完善表单数据!')
          }
        });
      }
    }
  };
</script>
<style lang="scss" scoped>


</style>
