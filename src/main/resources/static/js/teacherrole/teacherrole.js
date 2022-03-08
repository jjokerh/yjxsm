layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    var tableIns =
        table.render({
            elem: '#saleChanceList'//表格绑定的ID
            , url: ctx + '/teacherRole/list'//访问数据的地址
            , cellMinWidth: 95 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , height: "full-125",
            limits: [10, 15, 20, 25],
            limit: 10,//每页显示数据条
            id: "saleChanceListTable"
            , cols: [[
                {type: "checkbox", fixed: "center"},
                {field: "id", title: '编号', fixed: "true"},
                {field: 'teacherName', title: '教师昵称', align: 'center'},
                {field: 'trueName', title: '教师姓名', align: 'center'},
                {field: 'phone', title: '联系电话', align: 'center'},
                {field: 'sex', title: '性别', align: 'center'},
                {field: 'qq', title: 'qq', align: 'center'},
                {field: 'createDate', title: '入职时间', align: 'center'},
                {field: 'updateDate', title: '更新时间', align: 'center'},
                {title: '操作', templet: '#saleChanceListBar', fixed: "right", align: "center", minWidth: 150}
            ]]
        });

    table.on("tool(saleChances)",function (obj){
        //获取当前行对象
        var data=obj.data;

        if(obj.event==='edit'){
            UpdateCourseDialog(data.id);
        }
    });
    function UpdateCourseDialog(id) {
        var title = "<h2>教务授权</h2>";
        var url=ctx+"/teacherRole/addOrUpdateDialog"+"?id="+id;
        layer.open({
            title:title,
            type: 2,
            area:["500px","300px"],
            content:url
        });
    }

});