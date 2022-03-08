layui.use(['table','layer'],function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    //学生模块：分数
    var tableIns = table.render({
        elem: '#msList',
        //返回给后台的list取到数据
        url : ctx+'/student/add1',
        cellMinWidth : 80,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#msbarDemo",
        id : "msListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "score", title:'分数',fixed:"true", minWidth:50,align:"center"},
            {field: 'course_name', title: '课程名称', minWidth:50, align:"center"},

        ]]
    });
});
//分数
/*
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
}*/
    /*$("#closeBtn").click(function (){
        var index=parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });*/
