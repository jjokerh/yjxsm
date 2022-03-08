layui.use(['form', 'table', 'jquery', 'jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        table = layui.table,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

     /*
     表格渲染
     */
    var tableIns =
        table.render({
            elem: '#saleChanceList'                    //表格绑定的ID
            , url: ctx + '/teacher/list'               //访问数据的地址
            , cellMinWidth: 95                         //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , page: true                               //开启分页
            , height: "full-125",
            limits: [10, 15, 20, 25],
            limit: 10,                                 //每页显示数据条
            toolbar: "#toolbarDemo",                   //头部工具栏
            id: "saleChanceListTable"
            , cols: [[
                {type: "checkbox", fixed: "center"},
                {field: "id", title: '编号', fixed: "true"},
                {field: 'teacherName', title: '教师姓名', align: 'center'},
                {field: 'sex', title: '性别', align: 'center'},
                {field: 'email', title: 'Email', align: 'center'},
                {field: 'phone', title: '联系电话', align: 'center'},
                {field: 'address', title: '住址', align: 'center'},
                {field: 'createDate', title: '入职时间', align: 'center'},
                {field: 'updateDate', title: '更新时间', align: 'center'},
                {field: 'clazzName', title: '授课班级', align: 'center'},
                {field: 'roleName', title: '分配职位', align: 'center'},
                {title: '操作', templet: '#saleChanceListBar', fixed: "right", align: "center", minWidth: 150}
            ]]
        });


    //绑定搜索功能
    $(".search_btn").click(function () {
        console.log( $("#roleName option:selected").val())
        //这里以搜索为例
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设 //$(id)是前端的值
                teacherName: $("input[name='teacherName']").val()
                , sex: $("#sex option:selected").val()
                , id: $("#id").val()
                ,roleName: $("#roleName option:selected").val()
                //…
            }
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    })
    /*
        绑定头部工具栏
     */
    table.on("toolbar(saleChances)", function (obj) {
        //获取选中对象状态
        var checkStatus=table.checkStatus(obj.config.id);

        if (obj.event === 'add') {
            //alert("添加");
            openAddOrUpdateSaleChanceDialog();
        } else if (obj.event === 'del') {
            //alert("dels")删除
            deleteSaleChances(checkStatus.data);
        }
    });

    function deleteSaleChances(data) {
        //前端验证
        if (data.length == 0) {
            layer.msg("请选择数据");
            return;
        }
        //提示确定是否删除
        //alert("删除one")
        //提示确定是否删除
        layer.confirm("你确定要删除这些数据吗？",{
            btn:["确认","取消"],
        },function (index) {
            //关闭确认框
            layer.close(index);
            //收集数据
            var ids=[];
            for(var x in data){
                ids.push(data[x].id);
            }
            //发送ajax[1,2,3]
            $.ajax({
                type:"post",
                url:ctx+"/teacher/dels",
                data:{"ids":ids.toString()},
                dataType: "json",
                success:function (result){
                    //判断
                    if(result.code==200){
                        //刷新页面
                        tableIns.reload();
                    }else {
                        //提示一下
                        layer.msg(result.msg,{icon:5});
                    }
                }
            })
        });
    }
    //定义函数  添加或者编辑更新
    function openAddOrUpdateSaleChanceDialog(id){
        /*console("nihao"+id);*/
        var title="<h2>教师信息-添加</h2>";
        var url=ctx+"/teacher/addOrUpdateSaleChancePage";
        //判断是否有ID
        if(id){
            var title="<h2>教师信息-更新</h2>";
            url=url+"?id="+id;
        }
       /* console.log(url);*/
        //添加弹出层
        layer.open({
            title:title,
            type:2,//iframe
            area:["420px","500px"],
            maxmin:true,
            content:url
        });
    }

    /*
        绑定行内工具栏
     */
    table.on("tool(saleChances)", function (obj) {
        //获取当前行对象
        var data=obj.data;
        if (obj.event === 'edit') {

            //alert("编辑");
            var saleChance = data.id;
            openAddOrUpdateSaleChanceDialog(data.id);
            return ;
        } else if (obj.event === "del") {
            //alert("删除one")
            //提示确定是否删除
            layer.confirm("你确定要删除这条数据吗？",{
                btn:["确认","取消"],
            },function (index) {
                //关闭确认框
                layer.close(index);
                //发送ajax[1,2,3]
                $.ajax({
                    type:"post",
                    data:{"ids":data.id},
                    url:ctx+"/teacher/dels",
                    dataType: "json",
                    success:function (result){
                        //判断
                        if(result.code==200){
                            //刷新页面
                            tableIns.reload();
                        }else {
                            //提示一下
                            layer.msg(result.msg,{icon:5});
                        }
                    }
                })
            })
        }
    });

});