layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    /*数据表格的渲染*/

    /**
     * 学生列表展示
     */
    var  tableIns = table.render({
        elem: '#studentList', // 表格绑定的ID
        url : ctx + '/student/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "studentListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true"},
            {field: 'studentName', title: '学生姓名',align:"center"},
            //{field: 'trueName', title: '学生姓名', align:'center'},
            {field: 'studentPwd', title: '密码',  align:'center'},
            {field: 'qq', title: 'QQ', align:'center'},
            {field: 'sex', title: '性别', align:'center'},
            {field: 'phone', title: '联系电话', align:'center'},
            {field: 'cId', title: '班级', align:'center'},
            {field: 'createDate', title: '入学时间', align:'center'},
            {field: 'updateDate', title: '毕业时间', align:'center'},
            {field: 'isValid', title: '学生状态', align:'center',templet: function(d){
                    return formatterState(d.isValid);}},
            {title: '操作', templet:'#studentListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });
    /**
     * 格式化分配状态
     * 0 - 休学
     * 1 - 正常
     * 其他 - 未知
     * @param state
     * @returns {string}
     */
    function formatterState(state) {

        if(state==0){
            return "<div style='color: yellow'>休学</div>";
        }else if (state==1){
            return "<div style='color: green'>正常</div>";
        }else {
            return "<div style='color: red'>未知</div>";
        }
    }

    /**
     * 绑定搜索 表单重载
     */
    $("#searchBtn").click(function () {
        table.reload("studentListTable",{
            where: {
                    //设定异步数据接口的额外参数，任意设
                    studentName: $("input[name='studentName']").val(), // 学生姓名
                    cId: $("input[name='cId']").val(),  //班级
                    isValid: $("#state").val()
            },  // 学生状态
                page: {
                    curr: 1  // 重新从第 1 页开始
                }
            });
    });

    //绑定头部工具栏
    table.on("toolbar(student)",function (obj) {
        //选中的对象
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'add':
                // 点击添加按钮，打开窗口
                openAddOrUpdateStudentDialog();
                break;
            case 'del':
                //批量删除
                deleteStudent(checkStatus.data);
                break;
        }

    });
    //绑定行内工具栏
    table.on("tool(student)",function (obj) {

        var data = obj.data;          //获得当前行数据
        var layEvent = obj.event;    //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        if(layEvent === 'edit'){     //查看
            var studentId = data.id;
            openAddOrUpdateStudentDialog(studentId);
            //do something
        } else if(layEvent === 'del'){
            //删除
            layer.confirm('是否确认删除',{
                btn:["确认","取消"]
            }, function(index){
                //关闭
                layer.close(index);
                //向服务端发送删除指令
                //发送ajax
                $.ajax({
                    type:"post",
                    url:ctx+"/student/delete",
                    data: {
                        "ids":data.id
                    },
                    dataType:"json",
                    success:function (obj) {
                        if(obj.code==200){
                            layer.msg("删除成功",function () {
                                //重新渲染表单
                                tableIns.reload();
                            });
                        }else {
                            layer.msg(obj.msg);
                        }
                    }
                });
            });
        }

    });
    //添加或修改数据
    function openAddOrUpdateStudentDialog(studentId) {
        var title = "<h2>学生管理 - 学生添加</h2>";
        var url = ctx + "/student/addOrUpdatePage";

        //判断saleChanceId
        if (studentId) {
            // 如果id不为空，则为修改操作
            title = "<h2>学生管理 - 信息编辑</h2>";
            url = url + "?id=" + studentId;
        }
        layer.open({
            title:title,
            type: 2, //iframe
            content: url,
            area:["500px","500px"],
            maxmin:true
        });
    }
    /*删除*/
    function deleteStudent(data) {
        if(data.length==0){
            layer.msg("请选择数据");
            return;
        }
        layer.confirm('是否确认删除',{
            btn:["确认","取消"]
        }, function(index){
            //关闭
            layer.close(index);
            //向服务端发送删除指令
            var ids = "&ids=";
            for (var i=0; i<data.length; i++){
                if (i< data.length-1){
                    ids=ids+data[i].id+"&ids=";
                }else {
                    ids=ids+data[i].id;
                }
            }
            console.log(ids);
            //发送ajax
            $.post(ctx+"/student/delete",ids,function (result){
                if (result.code==200){
                    //重新加载数据
                    tableIns.reload();
                }else {
                    //提示一下
                    layer.msg(result.msg,{icon: 6});
                }
            },"json");

            //重新加载数据
            tableIns.reload();
        });
    }

});