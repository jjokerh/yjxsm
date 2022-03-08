layui.use(['table','layer'],function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    //学生模块：课程展示
    var tableIns = table.render({
        elem: '#mcList',
        //返回给后台的list取到数据
        url : ctx+'/student/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "mcListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "id", title:'编号',fixed:"true", width:80},
            {field: 'course_name', title: '课程名称', minWidth:50, align:"center"},
            {field: 'credits', title: '课程学分', minWidth:50, align:'center'},
            {field: 'period', title: '课程学时', minWidth:100, align:'center'},
            {field: 'create_date', title: '开课时间', align:'center',minWidth:150},
            {field: 'update_date', title: '更新时间', align:'center',minWidth:150},
            {title: '操作', minWidth:150,
                templet:'#mcListBar',fixed:"right",align:"center"}
        ]]
    });
    //角色模块--头工具
        table.on('toolbar(myCourse)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'find':
                    openAddDialog();
                    break;
            }
        });
    //行工具栏
    table.on('tool(myCourse)', function(obj){
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
        //编辑
        if(layEvent === 'del'){
            layer.confirm('是否退出改课', function(index){
                obj.del();
                layer.close(index);
                deleteCourse(data);
            });
        }
    });

    // function findScore(courseId){
    //     $.ajax({
    //         type:"post",
    //         url:ctx+"/student/select",
    //         data: {"courseId":courseId},
    //         dataType:"json",
    //         success:function (res) {
    //             layer.msg(res);
    //         }
    //     })
    // }
    //单行退课
    function deleteCourse(data){
        $.post(ctx+"/student/delete1",{"courseId":data.id},function(res){
            //判断
            if(res.code == 200){
                layer.msg("已删除");
                layer.close(index);
                //刷新
                tableIns.reload();
            }else{
                //提示一下
                layer.msg(res.msg);
            }
        },"json");
    }
    //分数
    function openAddDialog(studentId) {
        var title="<h2>分数详情</h2>"
        url=ctx+"/student/find";
        // console.log(url);
        layui.layer.open({
            title:title,
            type:2,
            maxmin:true,
            area:["1000px","500px"],
            content:url
        });
    }














});