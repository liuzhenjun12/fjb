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
        chart: null,
        queryParams: {
          nian: null
        },
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
          this.setOptions(this.chartData)
        },1000)
      },
      setOptions({ array1, array2,array3 } = {}) {
        this.chart.setOption({
          title : {
            text: '反假测评报名各省学员统计',
            textStyle:{
              color:"rgba(0, 0, 0, 0.45)"

            },
            x:'center'

          },
          tooltip : {
            trigger: 'axis'
          },
          legend: {
            orient : 'vertical',
            x : 'left',
            data:['测评人数统计'],
            textStyle:{
              color:"rgba(0, 0, 0, 0.45)"

            },
          },
          toolbox: {
            orient: 'vertical',
            right: '1%',
            top: '20%',
            iconStyle: {
              color: 'rgba(0, 0, 0, 0.45)',
              borderColor: 'rgba(0, 0, 0, 0.45)',
              borderWidth: 1,
            },
            feature: {
              saveAsImage: {},
              magicType: {
                // show: true,
                type: ['line','bar']
              }
            }
          },
          calculable : false,
          xAxis : [
            {
              type : 'category',
              splitLine:{show: false},
              axisLabel : {
                formatter: '{value} ',
                textStyle: {
                  color: '#a4a7ab',
                  align: 'right'
                }
              },
              data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
            }
          ],
          yAxis : [
            {
              type : 'value',
              splitLine:{show: false},
              axisLabel : {
                formatter: '{value} ',
                textStyle: {
                  color: '#a4a7ab',
                  align: 'right'
                }
              }
            }
          ],
          grid:{
            borderWidth:0
          },
          series : [
            {
              name:'福建',
              type:'bar',
              data:array1,
              itemStyle: {
                normal: {
                  color: '#2481ff'
                }
              },
              markPoint : {
                data : [
                  {type : 'max', name: '最大值'},
                  {type : 'min', name: '最小值'}
                ]
              },
              markLine : {
                data : [
                  {type : 'average', name : '平均值'}
                ]
              }
            },
        {
          name:'广东',
            type:'bar',
          data:array2,
          itemStyle: {
          normal: {
            color: '#24553f'
          }
        },
          markPoint : {
            data : [
              {type : 'max', name: '最大值'},
              {type : 'min', name: '最小值'}
            ]
          },
          markLine : {
            data : [
              {type : 'average', name : '平均值'}
            ]
          }
        },
            {
              name:'浙江',
              type:'bar',
              data:array3,
              itemStyle: {
                normal: {
                  color: '#7755ff'
                }
              },
              markPoint : {
                data : [
                  {type : 'max', name: '最大值'},
                  {type : 'min', name: '最小值'}
                ]
              },
              markLine : {
                data : [
                  {type : 'average', name : '平均值'}
                ]
              }
            }
          ]
        })
      }
    }
  }
</script>
