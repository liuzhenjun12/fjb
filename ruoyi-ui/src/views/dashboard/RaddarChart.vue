<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
  import echarts from 'echarts'
  require('echarts/theme/macarons') // echarts theme
  import resize from './mixins/resize'

  export default {
    mixins: [resize],
    props: {
      className: {
        type: String,
        default: 'chart'
      },
      width: {
        type: String,
        default: '100%'
      },
      height: {
        type: String,
        default: '350px'
      },
      autoResize: {
        type: Boolean,
        default: true
      },
      chartData: {
        type: Object,
        required: true
      }
    },
    data() {
      return {
        chart: null
      }
    },
    watch: {
      chartData: {
        deep: true,
        handler(val) {
          this.setOptions(val)
        }
      }
    },
    mounted() {
      this.$nextTick(() => {
        this.initChart()
      })
    },
    beforeDestroy() {
      if (!this.chart) {
        return
      }
      this.chart.dispose()
      this.chart = null
    },
    methods: {
      initChart() {
        this.chart = echarts.init(this.$el, 'macarons')
        setTimeout(()=>{
          console.log(JSON.stringify(this.chartData)+'=====')
          this.setOptions(this.chartData)
        },1500)
      },
      setOptions(data) {
        this.chart.setOption({
          title : {
            text: '各省报名总人数图',
            textStyle:{
              color:"rgba(0, 0, 0, 0.45)"

            },
            x:'center'
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          legend: {
            left: 'center',
            bottom: '10',
            data: ['福建', '广东', '浙江']
          },
          series: [
            {
              name: 'WEEKLY WRITE ARTICLES',
              type: 'pie',
              roseType: 'radius',
              radius: [15, 95],
              center: ['50%', '50%'],
              data: data.data,
              animationEasing: 'cubicInOut',
              animationDuration: 2600
            }
          ]
        })
      },
    }
  }
</script>
