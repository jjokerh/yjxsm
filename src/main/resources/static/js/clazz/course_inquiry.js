layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 用户列表展示
     */
    var  tableIns = table.render({
        elem: '#userList',
        url : ctx+'/customer_serve/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "id", title: '班级编号',fixed:"true", width:100},
            {field: 'clazzName', title: '班级名称', minWidth:100, align:'center'},
            {field: 'num', title: '班级人数', minWidth:100, align:'center'},
            {field: 'campus', title: '校区', minWidth:100, align:'center'},
            {title: '操作', minWidth:150, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });

    /*条件查询*/
    $(".search_btn").click(function(){
        //这里以搜索为例
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                id: $("input[name='userName']").val(),
                clazzName: $("input[name='clazzname']").val(),
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    /*绑定头部工具栏*/
    //触发事件
    table.on('toolbar(users)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'add':
                openAddOrUpdateUserDialog();
                break;
            case 'del':
                // layer.msg('删除');
                deleteUser(checkStatus.data);
                break;
        }
    });


    /**
     * 删除
     */
    function  deleteUser(datas){
        if(datas.length==0){
            layer.msg("请选择数据");
            return ;
        }
        layer.confirm("你确定要删除这些数据?",{
            btn:["确认","取消"],
        },function(index){
            //关闭确认框
            layer.close(index);
            //收集数据
            var ids=[];
            //遍历
            for(var x in datas){
                ids.push(datas[x].id);
            }
            /*发送ajax删除*/
            $.ajax({
                type:"post",
                url:ctx+"/customer_serve/delete",
                data:{"ids":ids.toString()},
                success:function (result){
                    if(result.code==200){
                        layer.msg("删除成功了",{icon:6});
                        //刷新一下
                        parent.location.reload();
                    }else{
                        //提示一下
                        layer.msg(result.msg,{icon: 5 });
                    }
                }
            });
        });
    }

    function  openAddOrUpdateUserDialog(id){

        var title="<h2>课程管理--添加</h2>";
        var url=ctx+"/customer_serve/addCourse";
        //判断添加还是修改

        if(id){
            title="<h2>用户模块--学生人数更新</h2>";

            url=url+"?id="+id;
        }

        layer.open({
            title:title,
            type:2,//iframe
            content:url,
            maxmin:true,
            area:["550px","420px"]
        });
    }

    /*绑定行内工具栏*/

    table.on('tool(users)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

        if(layEvent === 'edit'){ //查看
            openAddOrUpdateUserDialog(data.id);
        } else if(layEvent === 'del'){ //删除
            layer.confirm('真的删除行么', function(index){

                layer.close(index);
                //向服务端发送删除指令
                $.ajax({
                    type:"post",
                    url:ctx+"/customer_serve/delete",
                    data:{"ids":data.id},
                    success:function (result){
                        if(result.code==200){
                            layer.msg("删除成功了",{icon:6});
                            //刷新一下
                            parent.location.reload();
                        }else{
                            //提示一下
                            layer.msg(result.msg,{icon: 5 });
                        }
                    }
                });
            });
        }
    });

});