/*
 * 后台首页的js，主要做ajax请求，填充echarts数据。
 * @author zhaoyuan
 * @date 2019,Nov 27 2:48 pm
 * */

$(function () {
//var ip = window.location.href; //url
//var port = document.location.host;//ip:端口号

    itemStatiscticsEcharts();
    userStatiscticsEcharts();
});

function itemStatiscticsEcharts() {

    //{ITEMDOWNNUM: 5, ITEMUPNUM: 3153, ITEMPARAMNUM: 9, ITEMNUM: 3158}
    var itemStatisticsData = "";

    $.ajax({
        type: 'get',
        url: '/item/statistics/item',
        dataType: 'json',//返回的数据类型
        async: false,//发送同步请求
        success: function (result) {
            if (200 == result.status) {
                itemStatisticsData = result.data;
            }
        }
    });

    var myChart = echarts.init(document.getElementById('itemCh'));

    //指定图表的配置项和数据
    var option = {
        title : {
          text : '商品统计',
            x:'center',
          textStyle:{
              color:'#3398DB'
          }
        },
        color: ['#3398DB'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '22%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                data: ['商品数量', '上架商品数量', '下架商品数量', '有规格参数的商品数量'],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '直接访问',
                type: 'bar',
                barWidth: '60%',
                data: [itemStatisticsData.ITEMNUM, itemStatisticsData.ITEMUPNUM, itemStatisticsData.ITEMDOWNNUM, itemStatisticsData.ITEMPARAMNUM],
                width: 13,//柱图宽度
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function userStatiscticsEcharts() {

    var userStatisticsData = "";

    $.ajax({
        type: 'get',
        url: '/user/statistics/index',
        dataType: 'json',//返回的数据类型
        async: false,//发送同步请求
        success: function (result) {
            if (200 == result.status) {
                userStatisticsData = result.data;
            }
        }
    });

    var myChart = echarts.init(document.getElementById('userCh'));

    //指定图表的配置项和数据
    option = {
        title : {
            text: '用户统计',
            x:'center',
            color:'#3398DB'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['后台用户','购物用户']
        },
        series : [
            {
                name: '用户数量',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:userStatisticsData.ADMINUSERNUM, name:'后台用户'},
                    {value:userStatisticsData.SHOPUSERNUM, name:'购物用户'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}


