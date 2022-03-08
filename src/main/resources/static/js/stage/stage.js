layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    var tableIns = table.render({
        elem: '#course',//表格绑定的Id
        url: ctx + '/stage/list',//访问数据的地址
        cellMinWidth: 50,
        page: true,
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        toolbar: "#toolbarDemo",
        id: "course",
        cols: [[
            {type: "checkbox", fixed: "center"},
            {field: "id", title: '阶段id', fixed: "true"},
            {field: 'stageName', title: '阶段', align: "center"},
            {field: 'createDate', title: '开课时间', align: 'center'},
            {field: 'updateDate', title: '结课时间', align: 'center'},
            {field: 'period', title: '上课周数', align: 'center'},
            {field: 'teacherName', title: '授课老师', align: 'center'},
            {
                field: 'createDate', title: '课程状态', align: 'center', templet: function (d) {
                    return formatterState(d.createDate,d.updateDate);
                }
            },
            {title: '操作', templet: '#courseListBar', fixed: "right", align: "center", minWidth: 150}
        ]]
    });
    /**
     * 格式化分配状态
     *  0 - 未分配
     *  1 - 已分配
     *  其他 - 未知
     * @param state
     * @returns {string}
     */
    function formatterState(createDate,updateDate){
       var kaike = new Date(createDate);
       var jieke = new Date(updateDate);
       var now = new Date();
        if(kaike<now&&jieke>now) {
            return "<div style='color: green'>上课中</div>";
        } else if(kaike>now) {
            return "<div style='color: deepskyblue'>未开课</div>";
        } else if(jieke<now){
            return "<div style='color: red'>课程已完结</div>";
        }
    }

    /*计算时间差*/
    function daysBetween(DateOne,DateTwo) {
        var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ('-'));
        var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ('-')+1);
        var OneYear = DateOne.substring(0,DateOne.indexOf ('-'));
        var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ('-'));
        var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ('-')+1);
        var TwoYear = DateTwo.substring(0,DateTwo.indexOf ('-'));
        var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear)- Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear))/86400000);
        return cha;
    }
    Date.prototype.Format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }

    /*绑定搜索功能*/
    $(".course_btn").click(function (){
        //这里以搜索为例
        tableIns.reload({
            where: { //设定异步数据接口的额外参数
                id: $("input[name='id']").val(),
                stageName: $("input[name='stageName']").val(),
                teacherName: $("input[name='teacherName']").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    /*绑定头部工具栏*/
    table.on("toolbar(courseFilter)",function (obj){
        var checkStatus = table.checkStatus(obj.config.id);
        if (obj.event==="add"){
            openAddOrUpdateCourseDialog();
        }else if (obj.event==="del"){
            deleteSaleChances(checkStatus.data);
        }
    })

    function  deleteSaleChances(data){
        //前端验证
        if(data.length==0){
            layer.msg("没有选中数据");
            return ;
        }
        //提示确定要是删除
        layer.confirm("你确定要删除这些数据吗?",{
            btn:["确认","取消"],
        },function (index){
            //关闭确认框
            layer.close(index);
            //收集收集
            var ids=[];
            //循环遍历
            for(var x in data){
                ids.push(data[x].id);
            }
            //发送ajax
            $.ajax({
                type:"post",
                data:{"ids":ids.toString()},
                url:ctx+"/stage/delete",
                dataType:"json",
                success:function(result){
                    //判断
                    if(result.code==200){
                        //刷新页面
                        tableIns.reload();
                    }else{
                        //提示一下
                        layer.msg(result.msg,{icon:5});
                    }
                }

            });
        });
    }

    function openAddOrUpdateCourseDialog(id) {
        var title = "<h2>添加阶段</h2>";
        var url=ctx+"/stage/addOrUpdateDialog";
        if (id){
            title="<h2>更新阶段</h2>";
            // url = url+"?id="+id;
        }
        layer.open({
            title:title,
            type: 2,
            area:["500px","550px"],
            content:url
        });
    }
    table.on("tool(courseFilter)",function (obj){
        //获取当前行对象
        var data=obj.data;

        if(obj.event==='edit'){
            openAddOrUpdateCourseDialog(data.id);
        }else if(obj.event ==="del"){
            //提示确定要是删除
            layer.confirm("你确定要删除这些数据吗?",{
                btn:["确认","取消"],
            },function (index){
                //关闭确认框
                layer.close(index);
                //发送ajax【1,2,3】
                $.ajax({
                    type:"post",
                    data:{"ids":data.id},
                    url:ctx+"/stage/delete",
                    dataType:"json",
                    success:function(result){
                        //判断
                        if(result.code==200){
                            //刷新页面
                            tableIns.reload();
                        }else{
                            //提示一下
                            layer.msg(result.msg,{icon:5 });
                        }
                    }

                });
            });
        }
    });
});