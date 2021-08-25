<template>
  <div class="login">
    <div style="position: absolute;top: 5%"><img  src="../assets/image/fjb_logo_5.png"></div>
    <el-row :gutter="30">
    <el-col :span="16">
    <!--动效显示-->
    <div class="flasheffectoutbox">
      <div style="width: 1000px; height: 500px; position: relative">
        <div class="line2 line"></div>
        <div class="line3 line"></div>
        <div class="line4 line"></div>
        <div class="line5 line"></div>
        <div class="line6 line"></div>
        <!--主动效-->
        <div class="maineffectoutbox circleeffectclass">
          <a href="http://www.txgis.com" target="_blank">
            <img class="effectposition maindataeffect" src="../assets/img/maindataeffect.png">
            <div class="effectposition acrossremindeffectout" style=" width: 76px; height: 76px; margin: 9px;">
              <div class="acrossremindeffectin" style="width: 68px; height: 68px; margin: 4px;"></div>
            </div>
            <img class="effectposition maindataspecialeffect" src="../assets/img/maindataspecialeffect.png">
            <img class="effectposition acrossremindeffecindex" src="../assets/img/maindataicon.png">
            <span class="stagetitle">系统中心</span>
          </a>
        </div>
        <!--卫星动效-->
        <div class="circleeffectclass aroundfunctioneffect" style="left: 254px;top: 135px;">
          <img class="effectposition arounddataeffect02" src="../assets/img/aroundeffect.png">
          <div class="effectposition acrossremindeffectout acrossremindeffectoutard">
            <div class="acrossremindeffectin acrossremindeffectinard"></div>
          </div>
          <img class="effectposition acrossremindeffecindex" src="../assets/img/xingzhengshenpi.png">
          <span class="stagetitle stagearoundtitle">假币盖章</span>
        </div>

        <div class="circleeffectclass aroundfunctioneffect" style="left: 190px;bottom: 89px;">
          <img class="effectposition arounddataeffect01" src="../assets/img/aroundeffect.png">
          <div class="effectposition acrossremindeffectout acrossremindeffectoutard">
            <div class="acrossremindeffectin acrossremindeffectinard"></div>
          </div>
          <img class="effectposition acrossremindeffecindex" src="../assets/img/jianbie.png">
          <span class="stagetitle stagearoundtitle">假币鉴定</span>
        </div>

        <div class="circleeffectclass aroundfunctioneffect" style="right: 405px;bottom: 53px;">
          <img class="effectposition arounddataeffect02" src="../assets/img/aroundeffect.png">
          <div class="effectposition acrossremindeffectout acrossremindeffectoutard">
            <div class="acrossremindeffectin acrossremindeffectinard"></div>
          </div>
          <img class="effectposition acrossremindeffecindex" src="../assets/img/zhatuyunshu.png">
          <span class="stagetitle stagearoundtitle">采集归档</span>
        </div>

        <div class="circleeffectclass aroundfunctioneffect" style="right: 243px;bottom: 165px;">
          <img class="effectposition arounddataeffect01" src="../assets/img/aroundeffect.png">
          <div class="effectposition acrossremindeffectout acrossremindeffectoutard">
            <div class="acrossremindeffectin acrossremindeffectinard"></div>
          </div>
          <img class="effectposition acrossremindeffecindex" src="../assets/img/fenxi.png">
          <span class="stagetitle stagearoundtitle">数据分析</span>
        </div>

        <div class="circleeffectclass aroundfunctioneffect" style="right: 294px;top: 79px;">
          <img class="effectposition arounddataeffect03" src="../assets/img/aroundeffect.png">
          <div class="effectposition acrossremindeffectout acrossremindeffectoutard">
            <div class="acrossremindeffectin acrossremindeffectinard"></div>
          </div>
          <img class="effectposition acrossremindeffecindex" src="../assets/img/baixingpaiAPP.png">
          <span class="stagetitle stagearoundtitle">假币知识</span>
        </div>

        <div class="circleeffectclass decarround01" style="top: 218px;left: 92px;">
          <div class="acrossremindeffectin decarroundin01" style=" "></div>
        </div>
        <div class="circleeffectclass decarround01" style="top: 197px;right: 163px;">
          <div class="acrossremindeffectin decarroundin01" style=" "></div>
        </div>
        <div class="circleeffectclass decarround02" style="top: 99px;right: 93px;">
          <div class="acrossremindeffectin decarroundin02" style=" "></div>
        </div>
      </div>
    </div>
    </el-col>
      <el-col :span="8">
    <div class="xian">
      <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form">
        <h3 class="title">账号密码登录</h3>
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" type="text" auto-complete="off" placeholder="账号">
            <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            v-model="loginForm.password"
            type="password"
            auto-complete="off"
            placeholder="密码"
            @keyup.enter.native="handleLogin"
          >
            <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
          </el-input>
        </el-form-item>
        <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;color: #c89a25">记住密码</el-checkbox>
        <el-form-item style="width:100%;">
          <el-button
            :loading="loading"
            size="medium"
            type="primary"
            style="width:100%;"
            @click.native.prevent="handleLogin"
          >
            <span v-if="!loading">登 录</span>
            <span v-else>登 录 中...</span>
          </el-button>
        </el-form-item>
      </el-form>
    </div>
      </el-col>
      </el-row>
    <!--  底部  -->
    <div class="el-login-footer">
      <span style="font-size: 17px;color:white ">Copyright©&nbsp;&nbsp;&nbsp;<b>北京金储自动化技术有限公司</b>&nbsp;&nbsp;&nbsp;版本GZ20200114</span>
    </div>
  </div>
</template>

<script>
  // import { getCodeImg } from "@/api/login";
  import Cookies from "js-cookie";
  import { encrypt, decrypt } from '@/utils/jsencrypt'

  export default {
    name: "Login",
    data() {
      return {
        codeUrl: "",
        cookiePassword: "",
        loginForm: {
          username: "",
          password: "",
          rememberMe: false
        },
        loginRules: {
          username: [
            { required: true, trigger: "blur", message: "用户名不能为空" }
          ],
          password: [
            { required: true, trigger: "blur", message: "密码不能为空" }
          ]
          // , code: [{ required: true, trigger: "change", message: "验证码不能为空" }]
        },
        loading: false,
        redirect: undefined
      };
    },
    watch: {
      $route: {
        handler: function(route) {
          this.redirect = route.query && route.query.redirect;
        },
        immediate: true
      }
    },
    created() {
      // this.getCode();
      this.getCookie();
    },
    methods: {
      // getCode() {
      //   getCodeImg().then(res => {
      //     this.codeUrl = "data:image/gif;base64," + res.img;
      //     this.loginForm.uuid = res.uuid;
      //   });
      // },
      getCookie() {
        const username = Cookies.get("username");
        const password = Cookies.get("password");
        const rememberMe = Cookies.get('rememberMe')
        this.loginForm = {
          username: username === undefined ? this.loginForm.username : username,
          password: password === undefined ? this.loginForm.password : decrypt(password),
          rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
        };
      },
      handleLogin() {
        this.$refs.loginForm.validate(valid => {
          if (valid) {
            this.loading = true;
            if (this.loginForm.rememberMe) {
              Cookies.set("username", this.loginForm.username, { expires: 30 });
              Cookies.set("password", encrypt(this.loginForm.password), { expires: 30 });
              Cookies.set('rememberMe', this.loginForm.rememberMe, { expires: 30 });
            } else {
              Cookies.remove("username");
              Cookies.remove("password");
              Cookies.remove('rememberMe');
            }
            this.$store
              .dispatch("Login", this.loginForm)
              .then(() => {
                this.$router.push({ path: this.redirect || "/" });
              })
              .catch(() => {
                this.loading = false;
                // this.getCode();
              });
          }
        });
      }
    }
  };
</script>

<style rel="stylesheet/scss" lang="scss">
  .login {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    background-color:#081832;
    /*background-image: url("../assets/img/bg.png");*/
    /*background-size: cover;*/
  }
  .title {
    margin: 0px auto 30px auto;
    text-align: center;
    color: #67c23a;
  }
  /*动效栏*/
  .flasheffectoutbox{ flex:1;width: 1000px; height: 500px;margin: auto; margin-top: 7%; background-image: url("../assets/image/chain_bg.png"); background-repeat: no-repeat; background-position: center;position: relative}
  .circleeffectclass{ border-radius: 50%; border: rgba(0,168,255,0.6) 1px solid; cursor: pointer; position: absolute;}
  .effectposition{ position: absolute;}
  .acrossremindeffectout{ background-color: rgba(0,126,255,0.05); border-radius: 50%;}
  .acrossremindeffectoutho{ background-color: rgba(0,126,255,0.15);}
  .acrossremindeffectin{ background-color: rgba(0,126,255,0.3); border-radius: 50%; margin: auto;}
  .acrossremindeffectinho{ background-color: rgba(0,126,255,0.45);}
  .stagetitle{color: azure; top: 104px; font-size: 14px;font-weight: bold; font-family: "新宋体"; font-weight: lighter; display: block;width: 116px; text-align: center; position: absolute;left: -10%;}
  .stagearoundtitle{left: -25%;top:85px;}

  .line{position: absolute;transform-origin:left;animation-iteration-count:infinite}
  .line2{z-index: 1;left: 330px;top: 183px;width: 0;height: 3px;transform: rotate(21deg);animation: mm2 4s ;
    animation-delay:2s;animation-iteration-count:infinite;
    background-image: url("../assets/image/linght_ef.png");
    background-repeat: no-repeat;
    background-position: 100%;
  }
  .line3{z-index: 1;left: 262px;top: 351px;width: 0;height: 3px;transform: rotate(-25deg);animation: mm 5s ;
    animation-delay:4s;animation-iteration-count:infinite;
    background-image: url("../assets/image/linght_ef.png");
    background-repeat: no-repeat;
    background-position: 100%;

  }
  .line4{z-index: 1;left: 638px;top: 141px;width: 0;height: 3px;transform: rotate(-211deg);animation: mm4 3s  ;
    animation-delay:3s;animation-iteration-count:infinite;
    background-image: url("../assets/image/linght_ef.png");
    background-repeat: no-repeat;
    background-position: 100%;
  }
  .line5{z-index: 1;left: 679px;top: 289px;width: 0;height: 3px;transform: rotate(-172deg);animation: mm5 5s  ;animation-delay:1s;
    animation-iteration-count:infinite;
    background-image: url("../assets/image/linght_ef.png");
    background-repeat: no-repeat;
    background-position: 100%;
  }
  .line6{z-index: 1;left: 537px;top: 373px;width: 0;height: 3px;transform: rotate(-116deg);animation: mm6 4s  ;animation-delay:0s;
    animation-iteration-count:infinite;
    background-image: url("../assets/image/linght_ef.png");
    background-repeat: no-repeat;
    background-position: 100%;
  }

  @keyframes mm{
    0%{width: 0}
    100%{width: 194px}
  }
  @keyframes mm2{
    0%{width: 0}
    100%{width: 113px}
  }
  @keyframes mm4{
    0%{width: 0}
    100%{width: 140px}
  }
  @keyframes mm5{
    0%{width: 0}
    100%{width: 158px}
  }
  @keyframes mm6{
    0%{width: 0}
    100%{width: 94px}
  }
  /*主动效*/
  .maineffectoutbox{border: rgba(0,168,255,0.6) 3px solid; width: 99px; height: 99px;top: 194px;left: 430px;}
  .maindataeffect{ animation: scrolleffect 6.5s infinite linear;}
  .maindataspecialeffectct{animation: maindataspecialeffect 0.6s forwards;}
  @keyframes scrolleffect {
    0%{transfrom: rotate(0deg);}
    25%{transform: rotate(90deg);}
    50%{transform: rotate(180deg);}
    75%{transform: rotate(270deg);}
    100%{transform: rotate(360deg);}
  }
  @keyframes maindataspecialeffect {
    0%{transform: rotate(0deg);}
    100%{transform: rotate(360deg);}
  }

  /*卫星动效*/
  .aroundfunctioneffect{ width: 76px; height: 76px;}
  .acrossremindeffectoutard{width: 60px; height: 60px; margin: 8px;}
  .acrossremindeffectinard{width: 54px; height: 54px; margin: 3px;}
  .arounddataeffect01{ animation: scrolleffectarround01 linear infinite 4s forwards;}
  .arounddataeffect02{ animation: scrolleffectarround01 linear infinite 3.8s forwards;}
  .arounddataeffect03{ animation: scrolleffectarround01 linear infinite 4.2s forwards;}

  @keyframes scrolleffectarround01 {
    0%{transfrom: rotate(0deg);}
    25%{transform: rotate(-90deg);}
    50%{transform: rotate(-180deg);}
    75%{transform: rotate(-270deg);}
    100%{transform: rotate(-360deg);}
  }

  /*修饰卫星*/
  .decarround01{ width: 39px; height: 38px; cursor: auto;}
  .decarroundin01{ width: 33px; height: 33px; margin: 2px;background-color: rgba(0,126,255,0.8);}
  .decarroundin01:hover{ background-color: rgba(0,126,255,0.9);}
  .decarround02{ width: 28px; height: 28px; cursor: auto;}
  .decarroundin02{ width: 23px; height: 23px; margin: 2px;background-color: rgba(0,126,255,0.7);}
  .decarroundin02:hover{ background-color: rgba(0,126,255,0.8);}

  .xian{flex: 1;margin-top: 45%}
  .login-form {
    background: rgba(109, 109, 109, 0.23);
    width: 350px;
    height: 300px;
    padding: 25px 25px 5px 25px;
    border: 0px solid rgb(221, 222, 225);
    .el-form-item{
      margin-bottom: 20px;
    }
    .el-input {
      height: 38px;
      border-radius: 0px;
      input {
        height: 38px;border-radius: 0px;
      }
    }
    .input-icon {
      height: 39px;
      width: 14px;
      margin-left: 2px;
    }
  }
  .login-tip {
    font-size: 13px;
    text-align: center;
    color: #bfbfbf;
  }
  .login-code {
    width: 33%;
    height: 38px;
    float: right;
    img {
      cursor: pointer;
      vertical-align: middle;
    }
  }
  .el-login-footer {
    height: 40px;
    line-height: 40px;
    position: fixed;
    bottom: 0;
    width: 100%;
    text-align: center;
    color: #fff;
    font-family: Arial;
    font-size: 12px;
    letter-spacing: 1px;
  }
  .login-code-img {
    height: 38px;
  }
</style>
