<template>
  <el-container>
    <el-header>
    <navbar />
    </el-header>
        <el-container>
          <div style="background-color: #081832cc;width: 100%">
          <el-aside>
            <sidebar class="sidebar-container" />
          </el-aside>
          <div class="main">
              <div class="sanguang">
            <tags-view v-if="needTagsView" />
            </div>
            <app-main />
                  <right-panel v-if="showSettings">
                  <settings />
                  </right-panel>
          </div>
          </div>
        </el-container>
  </el-container>
</template>

<script>
import RightPanel from '@/components/RightPanel'
import { AppMain, Navbar, Settings, Sidebar, TagsView } from './components'
import ResizeMixin from './mixin/ResizeHandler'
import { mapState } from 'vuex'

export default {
  name: 'Layout',
  components: {
    AppMain,
    Navbar,
    RightPanel,
    Settings,
    Sidebar,
    TagsView
  },
  mixins: [ResizeMixin],
  computed: {
    ...mapState({
      sidebar: state => state.app.sidebar,
      device: state => state.app.device,
      showSettings: state => state.settings.showSettings,
      needTagsView: state => state.settings.tagsView,
      fixedHeader: state => state.settings.fixedHeader
    }),
    classObj() {
      return {
        hideSidebar: !this.sidebar.opened,
        openSidebar: this.sidebar.opened,
        withoutAnimation: this.sidebar.withoutAnimation,
        mobile: this.device === 'mobile'
      }
    }
  },
  methods: {
    handleClickOutside() {
      this.$store.dispatch('app/closeSideBar', { withoutAnimation: false })
    }
  }
}
</script>

<style lang="scss" scoped>
  @import "~@/assets/styles/mixin.scss";
  @import "~@/assets/styles/variables.scss";
  .topbu{
    background: url(../assets/image/bg_top-2.png) center no-repeat;
    background-color: #030829;
  }
  .app-wrapper {
    @include clearfix;
    position: relative;
    height: 100%;
    width: 100%;
    &.mobile.openSidebar {
      position: fixed;
      top: 0;
    }
  }

  .drawer-bg {
    background: #000;
    opacity: 0.3;
    width: 100%;
    top: 0;
    height: 100%;
    position: absolute;
    z-index: 999;
  }

  .fixed-header {
    position: fixed;
    top: 0;
    right: 0;
    z-index: 9;
    width: calc(100% - #{$sideBarWidth});
    transition: width 0.28s;
  }

  .hideSidebar .fixed-header {
    width: calc(100% - 54px)
  }

  .mobile .fixed-header {
    width: 100%;
  }
  .el-aside{
    float: left;
    box-shadow: -8px 0px 10px #034c6a inset, 8px 0px 10px #034c6a inset;
    box-sizing: border-box;
    width: 16%!important;
    background-color: #081832;
    border-radius: 5px;
    min-height: 100%;
  }
  .set_list{
    margin-left: 15px;
    color: #eee;
    font-size: 14px;
  }
  .list_i {
    width: 16px;
    height: 16px;
    display: inline-block;
    background-image: url(../../src/assets/icons/nav_1.png);
    top: 3px;
    position: relative;
  }
  .main{
    float: right;
    border-radius: 7px;
    width: 83.5%!important;
    /*border-left: 10px #000 solid;*/
    background-color: #081832;
    min-height: 100%;
  }
</style>
