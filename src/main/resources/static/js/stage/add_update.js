layui.use(['form','jquery','jquery_cookie','laydate'], function () {
    var form = layui.form,
        layer = layui.layer,
        laydate = layui.laydate,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);
    laydate.render({
        elem:'#createDate'
    });
    laydate.render({
        elem:'#updateDate'
    });

    form.on("submit(addOrUpdate)",function (obj) {
        obj.field.createDate = new Date(obj.field.createDate);
        obj.field.updateDate = new Date(obj.field.updateDate);
        obj.field.tId = $("#teacher").find("option:selected").val();
        obj.field.teacherName= $("#teacher").find("option:selected").text();
        var url = ctx+"/stage/save";
        if ($("input[name=id]").val()){
            var url = ctx+"/stage/update";
        }
        /*发送ajax请求*/
        $.post(url,obj.field,function (data){
            if (data.code==200){
                layer.msg("编辑成功");
                layer.closeAll("iframe");
                window.parent.location.reload();
            }else{
                layer.msg(data.msg);
            }
        },"json");
        return false;
    });
    $("#closeBtn").click(function (){
        //关闭弹出层
        //假设这是iframe页
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    });
    $.ajax({
        type:"post",
         url:ctx+"/stage/teacher",
        dataType:"json",
        success:function (data){
            var clazzId=$("input[name='tid']").val();
            console.log(clazzId);
            for (var i = 0; i < data.length; i++ ){
                if (clazzId==data[i].id){
                     $("#teacher").append("<option  value='"+data[i].id+"' selected>"+data[i].teacherName+"</option>");
                     //$("#teacher").append('<option  value="'+data[x].id+'" selected>'+data[x].teacherName+'</option>');
                }else{
                     $("#teacher").append("<option  value='"+data[i].id+"'>"+data[i].teacherName+"</option>");
                    //$("#teacher").append('<option value="'+data[x].id+'">'+data[x].teacherName+'</option>');
                }
            }
            //重新渲染下拉框
            layui.form.render("select");
        }
    });

});