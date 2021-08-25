<template>
  <div class="navbar">
<!--    <hamburger id="hamburger-container" :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />-->
    <div style="float: left;width: 35%; ">
    <div  v-if="showLogo" class="tupian"><img v-if="showLogo" :src="logo" style="height: 35px;"></div>
<!--    <div class="biaoti"> 贵州省反假币系统<span class="ml0">|</span></div>-->
    <breadcrumb id="breadcrumb-container" class="breadcrumb-container" />
  </div>

    <div class="header_center left">
<!--      <div style="font-size: 23px;margin-top: 15px"><strong>贵州省反假币实时信息系统</strong></div>-->
<!--      <p class="color_font"><small>Anti-Counterfeit Currency Information System</small></p>-->
      <img src="../../assets/image/fanjiabi.png" style="width: 92%;margin-top: 16px;">
    </div>

    <div class="right-menu">
      <div  style="float: right">
      <el-dropdown class="avatar-container right-menu-item hover-effect" trigger="click">
        <div class="avatar-wrapper">
          <img :src="avatar" class="user-avatar">
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown">
          <router-link to="/user/profile">
            <el-dropdown-item>个人中心</el-dropdown-item>
          </router-link>
          <el-dropdown-item @click.native="setting = true">
            <span>布局设置</span>
          </el-dropdown-item>
          <el-dropdown-item divided @click.native="logout">
            <span>退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
      <div  class="juese">{{name}}</div>
      <div  class="juese">{{roleName}}<span class="ml0">|</span></div>
      <div  class="juese">{{deptName}}<span class="ml0">|</span></div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
// import Screenfull from '@/components/Screenfull'
// import SizeSelect from '@/components/SizeSelect'
// import Search from '@/components/HeaderSearch'
// import fjbGit from '@/components/fjb/Git'
// import fjbDoc from '@/components/fjb/Doc'
import logoImg from '@/assets/image/ren.png'
export default {
  components: {
    Breadcrumb,
    Hamburger,
  },
  data() {
    return {
      logo: logoImg
    }
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar',
      'device',
      'deptName',
      'roleName',
      'name'
    ]),
    showLogo() {
      return this.$store.state.settings.sidebarLogo;
    },
    setting: {
      get() {
        return this.$store.state.settings.showSettings
      },
      set(val) {
        this.$store.dispatch('settings/changeSetting', {
          key: 'showSettings',
          value: val
        })
      }
    }
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      this.$confirm('确定注销并退出系统吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.dispatch('LogOut').then(() => {
          location.href = '/index';
        })
      })
    }
  }
}
</script>
<style lang="scss" scoped>
.navbar {
  height: 80px;
  overflow: hidden;
  position: relative;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);
  background: url(../../assets/image/title.png) no-repeat;
  background-size: 100% 100%;
  .tupian{float: left;margin-left: 12px;margin-top: 22px}
  .biaoti{
    float: left;font-size: 22px;line-height: 80px;margin-left: 5px;font-weight: bold;
  }
  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color:transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;color: white;
  }

  .errLog-container {
    display: inline-block;
    vertical-align: top;
  }
  .header_center {
    width: 30%;
    float: left;
    color: rgb(255, 255, 255);
    text-align: center;
    height: 80px;
    background-image: url(../../assets/image/logoBg.png);
    background-size: 100% 100%;
    font-family: 微软雅黑 !important;
    margin: 0px auto;
  }
  .color_font{
    color: #e8f7fe !important;
    font-size: 12px !important;
    line-height: 0px;
  }
  .right-menu {
    float: right;
    height: 100%;
    line-height: 80px;
    .juese{
      float: right;margin-right: 10px;font-size: 20px;
    }
    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 15px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 50px;
          height: 50px;
          border-radius: 50px;
        }

        .el-icon-caret-bottom {
          color: white;
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 20px;
          font-size: 12px;
        }
      }
    }
  }
}
  .header_center{
    position: absolute;
    color: #00f6ff;
    text-align: center;
    height: 80px;
    font-family: 微软雅黑 !important;
    left: 0;
    right: 0;
    top: -10px;
  }
  .no-redirect{
    color: white !important;
  }
</style>
