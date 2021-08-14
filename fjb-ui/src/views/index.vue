<template>
  <div class="dashboard-editor-container">
  </div>
</template>

<script>
  export default {
    name: 'Index',
    components: {
    },
    data() {
      return {
      }
    },
    created() {

    },
    methods: {

    }
  }
</script>

<style lang="scss" scoped>
  .dashboard-editor-container {
    padding: 16px 32px 32px 32px;
    background-color: rgb(240, 242, 245);
    position: relative;

    .chart-wrapper {
      background: #fff;
      padding: 16px 16px 0;
      margin-bottom: 32px;
    }
  }

  @media (max-width:1024px) {
    .chart-wrapper {
      padding: 8px;
    }
  }
</style>
