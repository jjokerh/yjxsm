layui.use(['form', 'table'], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table;
    //下拉框
    formSelects= layui.formSelects;
    var   tableIns=table.render({
            elem: '#currentTableId',
            url: ctx+'/studentStage/find',
            toolbar: '#toolbarDemo',
            id : "studentListTable",
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 80, title: 'ID', sort: true},
                {field: 'student_name', width: 200, title: '姓名', sort: true},
                {field: 'sex', width: 80, title: '性别', sort: true},
                {field: 'stage_name', width: 150, title: '课程'},
                {field: 'clazz_name', width: 120, title: '班级'},
                {field: 'phone', width: 150, title: '手机'},
                {field: 'qq', width: 150, title: 'qq'},
                {field: 'create_date', title: '入学时间', minWidth: 150, sort: true},
                {field: 'score', width: 150, title: '分数'},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 10,
            page: true,
            skin: 'line'
        });

    /**
     * 班级下拉加载框
     */
    $.ajax({
        type: "post",
        url:ctx+"/clazz/findClazz",
        dataType: "json",
        success:function (data) {
            for (var i = 0; i < data.length; i++) {
                $("#cId").append('<option value="'+data[i].id+'">'+data[i].clazz_name+'</option>');
            }
            // 重新渲染下拉框内容
            layui.form.render("select");
        }
    });
    /**
     * 课程阶段下拉加载框
     */
    $.ajax({
        type: "post",
        url:ctx+"/stage/findStage",
        dataType: "json",
        success:function (data) {
            for (var i = 0; i < data.length; i++) {
                $("#course").append('<option value="'+data[i].id+'">'+data[i].stage_name+'</option>');
            }
            // 重新渲染下拉框内容
            layui.form.render("select");
        }
    });
    /**
     * 绑定搜索 表单重载
     */
    $("#searchBtn").click(function () {
        table.reload('studentListTable',
            { where: {
                    //设定异步数据接口的额外参数，任意设
                    studentName: $("input[name=studentName]").val(), // 学生姓名
                    cId: $("select[name=cId]").val(),  //班级
                    stageId: $("select[name=course]").val()},  // 课程
                page: {
                    curr: 1  // 重新从第 1 页开始
                }
            });
    });

    /**
     * 行内工具栏
     */
    table.on('tool(currentTableFilter)', function (obj) {
        var data = obj.data;
        openAddStudent(data);
    });
    //评分
    function openAddStudent(data) {
        var studentId=data.id;
        var courseName=data.courseName;
        var title = "<h2>学生管理 - 评分</h2>";
        var url = ctx + "/studentStage/scorePage?id="+studentId;
        /*var url = ctx + "/studentStage/scorePage?id="+studentId+"&courseName="+courseName;*/
        layer.open({
            title:title,
            type: 2, //iframe
            content: url,
            area:["500px","500px"],
            maxmin:true
        });
    }
});