layui.use(['table','layer'],function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    //角色列表展示
    var  tableIns = table.render({
        elem: '#roleList',
        url : ctx+'/role/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "roleListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "id", title:'编号',fixed:"true", width:80},
            {field: 'roleName', title: '角色名', minWidth:50, align:"center"},
            {field: 'roleRemark', title: '角色备注', minWidth:100, align:'center'},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:150},
            {field: 'updateDate', title: '更新时间', align:'center',minWidth:150},
            {title: '操作', minWidth:150, templet:'#roleListBar',fixed:"right",align:"center"}
        ]]
    });
    //绑定事件搜索
    $("#searchBtn").click(function () {

        table.reload('roleListTable', {
            where: { //设定异步数据接口的额外参数，任意设
                roleName:$("input[name='roleName']").val(), //姓名
            },
            page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    //绑定头工具栏
    table.on('toolbar(roles)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'add':
                openUpdateOrAdd();
                break;
            case 'grant':
                openAddGrantDailog(checkStatus.data);
                break;
        };
    });

    //绑定行内工具栏
    table.on('tool(roles)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
         if(layEvent === 'del'){ //删除
            layer.confirm('确认删除吗',{btn:["确认","取消"]}, function(index){
                layer.close(index);
                //向服务端发送删除指令
                $.ajax({
                    type:"post",
                    url:ctx+"/role/delete",
                    data:{
                        "id":data.id
                    },
                    dataType:"json",
                    success:function (data) {
                        if(data.code==200){
                            layer.msg("删除成功",function () {
                                //重新渲染表单
                                tableIns.reload();
                            });
                        }else {
                            layer.msg(data.msg);
                        }
                    }
                });
            });
         } else if(layEvent === 'edit') { //编辑
             //do something
             openUpdateOrAdd(data.id);
         }
    });

    //编辑或添加
    function openUpdateOrAdd(id) {
        var url= ctx + "/role/addOrUpdateRolePage";
        var title= "<h2>角色管理 - 添加</h2>";
        if(id){
            url= url + "?id=" +id;
            title="<h2>角色管理 - 修改</h2>";
        }
        layer.open({
            title:title,
            type: 2,     //iframe
            content: url,
            area:["600px","280px"],
            maxmin:true
        });
    }

    /**
     *  授权函数
     * @param datas
     */
    function openAddGrantDailog(datas) {
        if (datas.length == 0) {
            layer.msg("请选择待授权角色记录!", {icon: 5});
            return;
        }
        if (datas.length > 1) {
            layer.msg("暂不支持批量角色授权!", {icon: 5});
            return;
        }
        var url = ctx + "/role/toAddGrantPage?id=" + datas[0].id;
        var title = "角色管理-角色授权";
        layui.layer.open({
            title: title,
            type: 2,
            area: ["600px", "280px"],
            maxmin: true,
            content: url
        });
    }
})