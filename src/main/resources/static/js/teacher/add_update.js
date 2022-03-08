layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    /**
     * 监听表单事件
     */
    form.on("submit(addOrUpdateSaleChance)",function(obj) {
        // 提交数据时的加载层
        var index = layer.msg("数据提交中,请稍后...",{
            icon:16, // 图标
            time:false, // 不关闭
            shade:0.8 // 设置遮罩的透明度
        });

        //判断是添加还是修改id=null,添加；id！=null,修改
        var url=ctx+"/teacher/save";
        //判断当前页面的隐藏域有没有id，有id修改，否则做添加
        if($("input[name=id]").val()){
            url=ctx+"/teacher/update"
        }

        /*发送ajax*/
        $.ajax({
            type:"post",
            url:url,
            data:obj.field,
            dataType:"json",
            success:function(obj){
                if(obj.code==200){
                    //提示信息
                    layer.msg("操作成功了",{icon:1 });
                    //关闭加载层
                    layer.close(index);
                    //关闭iframe
                    layer.closeAll("iframe");
                    //刷新页面
                    window.parent.location.reload();
                }else{
                    layer.msg(obj.msg,{icon : 6 });
                }
            }
        });
        //取消跳转
        return false;
    });

    /*取消功能*/
    $("#closeBtn").click(function () {
        //获取弹出层的索引值
        var idx = parent.layer.getFrameIndex(window.name);
        //根据索引关闭
        parent.layer.close(idx);
    });

    /*加载下拉框*/
    var roleid=$("input[name='roleid']").val();
    console.log(roleid)
    $.ajax({
        type: "post",
        url:ctx+"/teacher/queryAllRoles",
        dataType: "json",
        success:function (data){
            //遍历
            for (var x in data){
                console.log(data[x].id)
                if (data[x].id==roleid){
                    $("#roleName").append('<option  selected value="'+data[x].id+'">'+data[x].roleName+'</option>');
                }
                   else{
                    $("#roleName").append('<option value="'+data[x].id+'">'+data[x].roleName+'</option>');
                }
            }
            // 重新渲染下拉框内容
            layui.form.render("select");
        }

    });

    /*加载下拉框*/
    var clazzid=$("input[name='clazzid']").val();
    console.log(clazzid)
    $.ajax({
        type: "post",
        url:ctx+"/teacher/queryAllClazz",
        dataType: "json",
        success:function (data){
            //遍历
            for (var x in data){
                console.log(data[x].id)
                if (data[x].id==clazzid){
                    $("#clazzName").append('<option  selected value="'+data[x].id+'">'+data[x].clazzName+'</option>');
                }
                else{
                    $("#clazzName").append('<option value="'+data[x].id+'">'+data[x].clazzName+'</option>');
                }
            }
            // 重新渲染下拉框内容
            layui.form.render("select");
        }

    });


});





