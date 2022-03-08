layui.use(['element', 'echarts', 'layer'], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        echarts = layui.echarts;

    var chartZhu1 = echarts.init(document.getElementById('main1'));
    var chartZhu2 = echarts.init(document.getElementById('main2'));

    $.ajax({
        type:"post",
        url: ctx+"/stage/findSC",
        dataType: "json",
        success:function (obj){
            console.log(obj);
            var name = new Array();
            var date = new Array();
            var stusum = new Array();
            for (var i in obj){
                name[i] = obj[i].cname;
                date[i] = obj[i].td;
                stusum[i] = obj[i].stusum;
            }
            var optionchart = {
                title: {
                    text: '课程阶段以及学生所处阶段人数分析'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                        right: '20%'
                },
                legend: {
                    data: ['课程时长']
                },
                xAxis: {
                    type: 'category',
                    data: name,
                    axisLabel:{interval: 0}
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    name: '选课时长',
                    type: 'bar', //柱状
                    data: date,
                    itemStyle: {
                        normal: { //柱子颜色
                            color: 'pink'
                        }
                    },
                },{
                    name: '选课人数',
                    type: 'bar', //柱状
                    data: stusum,
                    itemStyle: {
                        normal: { //柱子颜色
                            color: 'orange'
                        }
                    },
                }]
            };
            chartZhu1.setOption(optionchart, true);
        }
    });

    $.ajax({
        type:"post",
        url:ctx+"/stage/findStuSta",
        dataType:"json",
        success:function (obj){
            var date = new Array();
            for (var x in obj){
                date[x] = obj[x].name;
            }
            var optionchartBing = {
                title: {
                    text: '学生阶段分析',
                    subtext: '学生阶段情况', //副标题
                    x: 'center' //标题居中
                },
                tooltip: {
                    trigger: 'item' //悬浮显示对比
                },
                legend: {
                    orient: 'vertical', //类型垂直,默认水平
                    left: 'left', //类型区分在左 默认居中
                    data: date
                },
                series: [{
                    type: 'pie', //饼状
                    radius: '60%', //圆的大小
                    center: ['50%', '50%'], //居中
                    data: obj
                }]
            };
            chartZhu2.setOption(optionchartBing, true);
        }
    });
});
