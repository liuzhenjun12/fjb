<template>
  <div style="margin-top: 20px">
    <el-form ref="form" :model="form" :rules="rules"  label-width="200px" class="form_input">
      <div class="kuand">
        <!--左上边框-->
        <div class="t_line_box">
          <i class="t_l_line"></i>
          <i class="l_t_line"></i>
        </div>
        <!--右上边框-->
        <div class="t_line_box">
          <i class="t_r_line"></i>
          <i class="r_t_line"></i>
        </div>
        <!--左下边框-->
        <div class="t_line_box">
          <i class="l_b_line"></i>
          <i class="b_l_line"></i>
        </div>
        <!--右下边框-->
        <div class="t_line_box">
          <i class="r_b_line"></i>
          <i class="b_r_line"></i>
        </div>
        <div class="kuand_1" style="height: 166px">
        <div class="data-title" style="position: absolute;margin-top: 5px">
          <b class="data-title-left">[</b>
          <span>网点信息</span>
          <b class="data-title-right">]</b>
        </div>
        <el-row style="position: absolute;top: 40px;width: 100%;">
          <el-col :span="12">
            <el-form-item label="网点名称:"  prop="deptName">
              <el-input v-model="form.deptName" :disabled="true" placeholder="网点名称"  clearable  size="small" style="width: 60%"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="登录账号:" prop="userName">
              <el-input v-model="form.userName" :disabled="true" placeholder="登录账号" clearable  size="small" style="width: 60%"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话:" prop="contactNumber">
              <el-input v-model="form.contactNumber" :disabled="true" placeholder="联系电话" clearable  size="small" style="width: 60%"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="收缴日期:" prop="receiptdate">
              <el-date-picker clearable size="small" style="width: 60%"
                              v-model="form.receiptdate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="选择收缴日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <div class="kuand_1" style="height: 275px;">
        <div class="data-title">
          <b class="data-title-left">[</b>
          <span>凭证信息</span>
          <b class="data-title-right">]</b>
        </div>
        <el-row style="position: absolute;top: 30px;width: 99%;">
          <el-col :span="12">
            <el-form-item label="单位/个人" prop="unitOrIndividual">
              <el-radio-group v-model="form.unitOrIndividual">
                <el-radio
                  v-for="dict in isdwOptions"
                  :key="dict.value"
                  :label="dict.value"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="单位名称:" prop="unitName"  style="margin-left: 10px" :rules="form.unitOrIndividual=='2'? [{required: true, message: '单位名称不能为空', trigger: 'blur'},{pattern:/^[\u4E00-\u9FA5A-Za-z]+$/,message: '单位名称不合法', trigger: 'blur'},{ min: 5, max: 20, message: '5~20个字符', trigger: 'blur'}] : [{ required: false }]">
              <el-input v-model="form.unitName" placeholder="请输入单位名称" :disabled="form.unitOrIndividual=='1'" clearable   maxlength="20" size="small" style="width: 60%"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="持有人员:" prop="holderName">
              <el-input v-model="form.holderName" placeholder="请输入假币持有人姓名" clearable  size="small"  maxlength="10" style="width: 60%"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手机号码:" style="margin-left: 10px" prop="holderTelephone">
              <el-input v-model="form.holderTelephone" placeholder="请输入假币持有人手机号码" clearable  size="small"  style="width: 60%"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="证件类型:" prop="documentType">
              <el-select v-model="form.documentType" placeholder="请选择证件类型" style="width: 60%" clearable   size="small">
                <el-option
                  v-for="dict in certificateOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="证件号码:" style="margin-left: 10px" prop="holderIdcard">
              <el-input v-model="form.holderIdcard" placeholder="请输入假币持有人证件号" clearable  maxlength="20"  size="small" style="width: 60%"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="经办人员:" prop="receiptManname">
              <el-input v-model="form.receiptManname" placeholder="请输入经办人" clearable  maxlength="10" size="small" style="width: 60%"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="复合人员:" style="margin-left: 10px" prop="cheekup">
              <el-input v-model="form.cheekup" placeholder="请输入复合人" clearable   maxlength="10" size="small" style="width: 60%"/>
            </el-form-item>
          </el-col>
        </el-row>
      </div>


      <div class="kuand_1" style="height: 470px;">
        <div class="data-title">
          <b class="data-title-left">[</b>
          <span>假币信息</span>
          <b class="data-title-right">]</b>
        </div>
        <el-row style="position: absolute;top: 30px;width: 99%;">
          <el-col :span="12">
            <el-form-item label="假币类型:" prop="madeway">
              <el-radio-group v-model="form.madeway">
                <el-radio
                  v-for="dict in madewayOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="假币币种:" style="margin-left: 10px" prop="ccCurrency">
              <el-select
                v-model="form.ccCurrency"
                placeholder="请选择假币币种"
                clearable
                size="small"
                style="width: 60%"
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
          <el-col :span="12">
            <el-form-item label="假币劵别:" prop="ccDenomination">
              <el-select
                v-model="form.ccDenomination"
                placeholder="请选择假币卷别"
                clearable
                size="small"
                style="width: 60%"
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


          <el-col :span="12">
            <el-form-item label="假币版别:" style="margin-left: 10px" prop="ccSeries">
              <el-select
                v-model="form.ccSeries"
                placeholder="请选择假币版别"
                clearable
                size="small"
                style="width: 60%"
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

          <el-col :span="12">
            <el-form-item label="假币来源:" prop="source">
              <el-select
                v-model="form.source"
                placeholder="请选择假币假币来源"
                clearable
                size="small"
                style="width: 60%"
              >
                <el-option
                  v-for="dict in sourceOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="冠字号码:" style="margin-left: 10px" prop="serialNumber">
              <el-input v-model="form.serialNumber" placeholder="请输入冠字号码"  maxlength="15" size="small"  style="width: 60%"/>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="收缴数量:" prop="amount">
              <el-input-number v-model="form.amount" controls-position="right" :min="1" @change="changeAmount" size="small" style="width: 60%" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="收缴总额:" style="margin-left: 10px" prop="totalamount">
              <el-input-number v-model="form.totalamount" controls-position="right" :min="1" size="small" style="width: 60%" :disabled="true" />
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="假币特征:" prop="ccFeature"  :rules="form.madeway=='变造'? [{required: true, message: '假币特征不能为空', trigger: 'blur'}] : [{ required: false }]">
              <el-checkbox  v-model="checkAll" @change="handleCheckAllChange" :disabled="form.madeway=='伪造'">全选 / 只有变造币可选择</el-checkbox>
              <el-checkbox-group v-model="form.ccFeature" >
                <el-checkbox v-for="item in ccFeatureOptions" :disabled="form.madeway=='伪造'"
                             :label="item"
                             :key="item">{{item}}</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="收缴备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" class="texkuang"  maxlength="200" placeholder="请输入内容" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <div class="kuand_1" style="height: 162px;">
        <div class="data-title">
          <b class="data-title-left">[</b>
          <span>假币照片</span>
          <b class="data-title-right">]</b>
        </div>
        <div style="color: #ff4949;line-height: 40px;height: 40px;margin-left: 15px;">只能上传jpg/png格式图片，而且最多上传8张，每张图片的大小不得超过150KB!</div>
        <el-row style="position: absolute;top: 55px;width: 99%;margin-left: 15px;">
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
        </el-row>
      </div>

      <div class="kuand_1" style="height: 102px;margin-bottom: 100px">
        <div class="data-title">
          <b class="data-title-left">[</b>
          <span>信息修改</span>
          <b class="data-title-right">]</b>
        </div>
        <div  style="text-align: center;margin-top: 10px;cursor: pointer">
          <el-button type="primary" @click="submitForm">修 改</el-button>
          <el-button @click="cancel" style="margin-left: 20px;">重 置</el-button>
        </div>
      </div>
      </div>
    </el-form>
  </div>
</template>

<script>
  import axios from 'axios'
  import { getToken } from "@/utils/auth";
  import {getReceiptseizure} from "@/api/business/receiptseizure";

  export default {
    name: "addReceiptseizure",
    data() {
      return {
        //最少上传多少张图片
        zhang:2,
        dialogImageUrl: '',
        //动态窗口是否显示
        dialogVisible: false,
        //是否禁用
        disabled: false,
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
        //复核状态
        statusOptions:[],
        //证件类型
        certificateOptions:[],
        //假币来源
        sourceOptions:[],
        //造假方式
        madewayOptions:[],
        //假币特征
        ccFeatureOptions:[],
        //是单位还是个人
        isdwOptions:[{value: '1', label: '个人'}, {value: '2', label: '单位'}],
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
          url: process.env.VUE_APP_BASE_API + "/business/receiptseizure/update"
        },
        // 表单参数
        form: {ccFeature:[]},
        // 表单校验
        rules: {
          receiptdate: [
            { required: true, message: "收缴日期不能为空", trigger: "blur" }
          ],
          holderName: [
            { required: true, message: "持有人姓名不能为空", trigger: "blur" },
            { min: 2, max: 5, message: '2~5个字符', trigger: 'blur'}
          ],
          receiptManname: [
            { required: true, message: "经办人姓名不能为空", trigger: "blur" },
            { min: 2, max: 5, message: '2~5个字符', trigger: 'blur'}
          ],
          cheekup: [
            { required: true, message: "复核人姓名不能为空", trigger: "blur" },
            { min: 2, max: 5, message: '2~5个字符', trigger: 'blur'}
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
          source: [
            { required: true, message: "假币来源不能为空", trigger: "blur" }
          ],
          serialNumber: [
            { required: true, message: "假币冠字号码不能为空", trigger: "blur" },
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
          documentType: [
            { required: true, message: "持有人证件类型不能为空", trigger: "blur" }
          ],
          holderIdcard: [
            { required: true, message: "持有人证件号码不能为空", trigger: "blur" },
            {pattern: /^[A-Za-z0-9]+$/, message: "请输入字母+数字的证件号码", trigger: "blur"},
            { min: 5, max: 20, message: '5~20个数字或字母', trigger: 'blur'}
          ],
          unitOrIndividual: [
            { required: true, message: "单位或个人不能为空", trigger: "blur" }
          ],
          madeway: [
            { required: true, message: "假币类型不能为空", trigger: "blur" }
          ],
          holderTelephone: [
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
      this.getReceiptseizure();
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
      this.getDicts("sys_certificate_type").then(response => {
        this.certificateOptions = response.data;
      });
      this.getDicts("sys_money_source").then(response => {
        this.sourceOptions = response.data;
      });
      this.getDicts("sys_false_type").then(response => {
        this.madewayOptions = response.data;
      });
      this.getDicts("sys_false_way").then(response => {
        response.data.forEach((item, index) => {
          this.ccFeatureOptions.push(item.dictLabel)
        })
      });
      this.getConfigKey("sys_shou_number").then(response => {
        if(response.msg) {
          this.zhang = parseInt(response.msg);
        }
      })
    },
    methods: {
      getReceiptseizure(){
        const id = this.$route.params && this.$route.params.id;
        getReceiptseizure(id).then(response => {
          this.form = response.data
          this.form.userName=response.data.createBy;
          this.form.deptName=response.data.dept.deptName
          this.form.contactNumber=response.data.dept.phone
          if(response.data.ccFeature!=''&&response.data.ccFeature!=null){
            this.form.ccFeature=response.data.ccFeature.split(',');
          }else {
            this.form.ccFeature=[]
          }
          if(response.data.imgList!=''&&response.data.imgList!=null){
            this.fileList=JSON.parse(response.data.imgList);
            for(var i=0;i<this.fileList.length;i++){
              this.fileList[i].url=process.env.VUE_APP_BASE_API+this.fileList[i].url;
            }
          }
        });
      },
      /** 假币特征选择全部时 */
      handleCheckAllChange(val) {
        this.form.ccFeature = val ? this.ccFeatureOptions : [];
      },
      // 取消按钮
      cancel() {
        this.reset();
      },
      // 表单重置
      reset() {
        this.getReceiptseizure();
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
      onFileChange(file, fileList){
          this.upload.formDate = new FormData();
          this.$refs['dynamic'].submit();
          this.upload.formDate.append('id', this.form.id);
          this.upload.formDate.append('status', "add");
        let config = {
          headers: {
            'Content-Type': 'multipart/form-data', 'Authorization': 'Bearer ' + getToken()
          }
        }
        axios.post("/dev-api/business/receiptseizure/update", this.upload.formDate, config).then(res => {
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
        axios.post("/dev-api/business/receiptseizure/update", this.upload.formDate, config).then(res => {
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
                if(this.form.receiptdate instanceof Date){
                  let date = new Date()
                  var defalutEndTime = date.getFullYear() + '-' + ((date.getMonth() + 1) >= 10 ? (date.getMonth() + 1) : '0' + (date.getMonth() + 1)) + '-' + (date.getDate() >= 10 ? date.getDate() : '0' + date.getDate())
                  this.upload.formDate.append('receiptdate', defalutEndTime);
                }else {
                  this.upload.formDate.append('receiptdate', this.form.receiptdate);
                }
                this.upload.formDate.append('imgList', '');
                this.upload.formDate.append('amount', this.form.amount);
                this.upload.formDate.append('totalamount', this.form.totalamount);
                this.upload.formDate.append('receiptManname', this.form.receiptManname);
                this.upload.formDate.append('unitOrIndividual', this.form.unitOrIndividual);
                this.upload.formDate.append('unitName', this.form.unitOrIndividual == '1' ? '' : this.form.unitName);
                this.upload.formDate.append('holderName', this.form.holderName);
                this.upload.formDate.append('documentType', this.form.documentType);
                this.upload.formDate.append('holderIdcard', this.form.holderIdcard);
                this.upload.formDate.append('holderTelephone', this.form.holderTelephone);
                this.upload.formDate.append('ccCurrency', this.form.ccCurrency);
                this.upload.formDate.append('ccDenomination', this.form.ccDenomination);
                this.upload.formDate.append('ccSeries', this.form.ccSeries);
                this.upload.formDate.append('source', this.form.source);
                this.upload.formDate.append('madeway', this.form.madeway);
                this.upload.formDate.append('ccFeature', this.form.madeway == '变造' ? this.form.ccFeature.toString() : '');
                this.upload.formDate.append('cheekup', this.form.cheekup);
                this.upload.formDate.append('remark', this.form.remark);
                this.upload.formDate.append('serialNumber', this.form.serialNumber);
                this.upload.formDate.append('updateBy', this.$store.state.user.name);
                let config = {
                  headers: {
                    'Content-Type': 'multipart/form-data', 'Authorization': 'Bearer ' + getToken()
                  }
                }
                axios.post("/dev-api/business/receiptseizure/update", this.upload.formDate, config).then(res => {
                  if (res.data.code === 200) {
                    this.$confirm('修改成功, 是否要关闭此页面?', '提示', {
                      confirmButtonText: '确定',
                      cancelButtonText: '取消',
                      type: 'warning'
                    }).then(() => {
                      this.$store.state.tagsView.visitedViews.splice(this.$store.state.tagsView.visitedViews.findIndex(item => item.path === this.$route.path), 1)
                      this.$router.push(this.$store.state.tagsView.visitedViews[this.$store.state.tagsView.visitedViews.length-1].path)
                    }).catch(() => {
                    });
                  }
                }).catch(res => {
                  this.msgError('网络异常请重新提交!')
                })
            }else {
              this.msgError('最少上传"'+this.zhang+'"张假币图片!')
            }
          }
        });
      }
    }
  };
</script>
<style lang="scss" scoped>
  .main_title{
    width: 210px;
    height: 35px;
    line-height: 33px;
    background-color: #2C58A6;
    border-radius: 18px;
    position: absolute;
    top: -17px;
    left:50%;
    margin-left: -90px;
    color:#fff;
    font-size: 18px;
    font-weight: 600;
    box-sizing: border-box;
    padding-left: 47px;
    z-index: 1000;
  }
  .main_title img{
    position: absolute;
    top: 8px;
    left: 20px;
  }
  .kuand_1{position: relative;}
  .data-title {
    width: 96px;
    color: cyan;
    font-size: 15px;
    margin-left: 10px;
  }
  .data-title-left, .data-title-right {
    color: cyan;
    font-family: 微软雅黑;
    font-size: 15px;
  }
  .data-title span {
    text-align: center;
    width: 80px;
    display: inline-block;
    text-align: center;
    font-size: 14px;
  }
  .el-radio{
    color: white !important;
  }
  .el-checkbox{
    color: white !important;
  }

</style>
