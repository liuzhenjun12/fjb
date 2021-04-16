<template>
  <div class="echarts">
    <div :style="{height:height,width:width}" ref="myEchart"></div>
  </div>
</template>
<script>
  import { findShengCount } from "@/api/system/bmb";
  export default {
    name: "ChinaChart",
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
        maps:[],
        geoCoordMap:{},
        data:[
          {name:'福建',value:'101'},{name:'广东',value:'102'},{name:'浙江',value:'103'}
        ]
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
        findShengCount().then(response => {
          this.maps=response.data
        });
      },
      convertData(data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
          var geoCoord = this.geoCoordMap[data[i].name];
          if (geoCoord) {
            res.push({
              name: data[i].name,
              value: geoCoord.concat(data[i].value)
            });
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
        mapFeatures.forEach(v=> {
          // 地区名称
          var name = v.properties.name;
          // 地区经纬度
          this.geoCoordMap[name] = v.properties.cp;
        });
        myChart.setOption({ // 进行相关配置
          title:{
            text:'反假报名测评情况省地图',
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
              if (typeof(params.value)[2] == "undefined") {
                var toolTiphtml = ''
                for(var i = 0;i<this.maps.length;i++){
                  if(params.name==this.maps[i].jigou){
                    toolTiphtml += this.maps[i].jigou+':<br>';
                    toolTiphtml +="测评考试人数:"+this.maps[i].shi+"<br>";
                    toolTiphtml +="理论合格人数:"+this.maps[i].liluenshi+"<br>";
                    toolTiphtml +="理论不合格人数:"+this.maps[i].liluenbu+"<br>";
                    toolTiphtml +="实操合格人数:"+this.maps[i].shichaoshi+"<br>";
                    toolTiphtml +="理论缺考人数:"+this.maps[i].liluenque+"<br>";
                  }
                }
                return toolTiphtml;
              } else {
                var toolTiphtml = ''
                for(var i = 0;i<this.maps.length;i++){
                  if(params.name==this.maps[i].jigou){
                    toolTiphtml += this.maps[i].jigou+':<br>';
                    toolTiphtml +="测评考试人数:"+this.maps[i].shi+"<br>";
                    toolTiphtml +="理论合格人数:"+this.maps[i].liluenshi+"<br>";
                    toolTiphtml +="理论不合格人数:"+this.maps[i].liluenbu+"<br>";
                    toolTiphtml +="实操合格人数:"+this.maps[i].shichaoshi+"<br>";
                    toolTiphtml +="理论缺考人数:"+this.maps[i].liluenque+"<br>";
                  }
                }
                return toolTiphtml;
              }
            }
          },
          legend: {
            orient: 'vertical',
            x: 'left',
            y: 'bottom',
            data: ['credit_pm2.5'],
            textStyle: {color: '#fff'}
          },
          visualMap: {
            show:false,
            min: 0,
            max: 2500,
            left: 'right',
            top: 'bottom',
            text: ['高', '低'], // 文本，默认为数值文本
            calculable: true,
            //		color: ['#26cfe4', '#f2b600', '#ec5845'],
            textStyle: {
              color: '#fff'
            }
          },
          /*工具按钮组*/
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
            show: true,
            map: 'china',
            label: {
              normal: {
                show: true,//显示省份标签
                textStyle:{color:"#fff"}//省份标签字体颜色
              },
              emphasis: {
                show: true,
                textStyle:{color:"#fff"}
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
          series: [
            {
              type: 'map',
              map: 'china',
              geoIndex: 0,
              aspectScale: 0.75, //长宽比
              showLegendSymbol: false, // 存在legend时显示
              label: {
                normal: {
                  show: true
                },
                emphasis: {
                  show: true,
                  textStyle: {
                    color: 'blue'
                  }
                }
              },
              roam: true,
              itemStyle: {
                normal: {
                  areaColor: '#031525',
                  borderColor: 'white',
                },
                emphasis: {
                  areaColor: 'blue'
                }
              },
              animation: false,
              data: this.data
            },
            {
              name: 'Top 5',
              type: 'effectScatter',
              coordinateSystem: 'geo',
              data:  this.convertData(this.data.sort(function(a, b) {
                return b.value - a.value;
              })),
              symbolSize: function(val) {
                return val[2] / 10;
              },
              showEffectOn: 'render',
              rippleEffect: {
                brushType: 'stroke'
              },
              hoverAnimation: true,
              label: {
                normal: {
                  formatter: '{b}',
                  position: 'right',
                  show: true
                }
              },
              itemStyle: {
                normal: {
                  color: 'blue',
                  shadowBlur: 10,
                  shadowColor: '#000'
                }
              },
              zlevel: 1
            },

          ]
        })
        window.onresize = myChart.resize;
      }
    }
  }
</script>
