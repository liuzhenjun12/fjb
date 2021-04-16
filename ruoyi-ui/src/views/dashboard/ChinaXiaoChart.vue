<template>
  <div class="echarts">
    <div :style="{height:height,width:width}" ref="myEchart"></div>
  </div>
</template>
<script>
  import { countOrder } from "@/api/system/bmb";
  export default {
    name: "ChinaXiaoChart",
    props: {
      width: {
        type: String,
        default: '100%'
      },
      height: {
        type: String,
        default: '700px'
      }
    },
    data() {
      return {
        chart: null,
        data:[]
      };
    },
    mounted() {
      this.getMsg();
      setTimeout(()=> {
        this.chinaConfigure();
      },500)
    },
    beforeDestroy() {
      if (!this.chart) {
        return;
      }
      this.chart.dispose();
      this.chart = null;
    },
    methods: {
      getMsg(){
        countOrder().then(response => {
          this.data=response.data
        });
      },
      convertData(data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
          var to=[]
          var dataItem = data[i].name;
          var jindu=data[i].jindu;
          to.push(jindu)
          var weidu=data[i].weidu;
          to.push(weidu)
            res.push({
              fromName: '深圳',
              toName: dataItem,
              coords: [[114.07,22.62], to]
            });
        }
        return res;
      },
      convertJwd(name){
        var res=[];
        for (var i = 0; i < this.data.length; i++) {
          if(name==this.data[i].name){
            res.push(this.data[i].jindu);
            res.push(this.data[i].weidu);
            if(name=='深圳'){
              res.push(100);
            }else {
              res.push(60);
            }
            // res.push(this.data[i].value);
            res.push(this.data[i].num);
            res.push(this.data[i].money);
            continue;
          }
        }
        return res;
      },
      chinaConfigure() {
        let myChart = this.$echarts.init(this.$refs.myEchart); //这里是为了获得容器所在位置
        /*获取地图数据*/
        myChart.showLoading();
        var mapFeatures = this.$echarts.getMap('china').geoJson.features;
        myChart.hideLoading();
        var color = ['#c5f80e'];
        var series = [];
        var s=[['深圳', this.data]].forEach((item, i)=> {
          series.push({
            name: item[0],
            type: 'lines',
            zlevel: 2,
            symbol: ['none', 'arrow'],
            symbolSize: 10,
            effect: {
              show: true,
              period: 6,
              trailLength: 0,
              symbol: 'arrow',
              symbolSize: 5
            },
            lineStyle: {
              normal: {
                color: color[i],
                width: 1,
                opacity: 0.6,
                curveness: 0.2
              }
            },
            data: this.convertData(item[1])
          }, {
            name: item[0],
            type: 'effectScatter',
            coordinateSystem: 'geo',
            zlevel: 2,
            rippleEffect: {
              brushType: 'stroke'
            },
            label: {
              normal: {
                show: true,
                position: 'right',
                formatter: '{b}'
              }
            },
            symbolSize: function (val) {
              return val[2] / 8;
            },
            itemStyle: {
              normal: {
                color: color[i]
              }
            },
            data: item[1].map((dataItem)=> {
              return {
                name: dataItem.name,
                value: this.convertJwd(dataItem.name)
              };
            })
          });
        });
        myChart.setOption({ // 进行相关配置
          title:{
            text:'智能箱柜锁销售客户分布地图',
            textStyle:{
              //文字颜色
              color:'rgba(0, 0, 0, 0.45)',
              //字体风格,'normal','italic','oblique'
              fontStyle:'normal',
              //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
              fontWeight:'bold',
              //字体系列
              fontFamily:'sans-serif',
              left:'center',
              //字体大小
              fontSize:20
            }
          },
          tooltip: {
            trigger: 'item',
            formatter: params=>{
              var toolTiphtml = ''
              var name=params.data.name;
              var value=params.data.value;
              if(name){
                toolTiphtml += name+':<br>';
              }
              if(value) {
                if (value.length > 2) {
                  toolTiphtml += "购买个数:" + value[3] + "个:<br>";
                }
                if (value.length > 3) {
                  toolTiphtml += "购买金额:" + value[4] + "元:<br>";
                }
              }
              return toolTiphtml;
            }
          },
          toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
              dataView: {
                readOnly: false
              },
              restore: {},
              saveAsImage: {}
            }
          },
          geo: {
            map: 'china',
            label: {
              emphasis: {
                show: true
              }
            },
            roam: true,
            itemStyle: {
              normal: {
                borderColor: 'rgba(147, 235, 248, 1)',
                borderWidth: 1,
                areaColor: {
                  type: 'radial',
                  x: 0.5,
                  y: 0.5,
                  r: 0.8,
                  colorStops: [{
                    offset: 0,
                    color: '#000' // 0% 处的颜色
                  }, {
                    offset: 1,
                    color: '#000' // 100% 处的颜色
                  }],
                  globalCoord: false // 缺省为 false
                },
                shadowColor: 'rgba(128, 217, 248, 1)',
                // shadowColor: 'rgba(255, 255, 255, 1)',
                shadowOffsetX: -2,
                shadowOffsetY: 2,
                shadowBlur: 10
              },
              emphasis: {
                areaColor: '#389BB7',
                borderWidth: 0
              }
            }
          },
          series: series
        })
        window.onresize = myChart.resize;
      }
    }
  }
</script>
