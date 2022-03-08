layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    //添加或修改
    form.on("submit(addOrUpdateRole)",function (obj) {
        var fieldData=obj.field;
        var url=ctx+"/role/add";
        var msg="添加成功";

        if(fieldData.id){
            //修改
            url=ctx+"/role/update";
            msg="修改成功";
        }
        $.ajax({
            type:"post",
            url:url,
            data:fieldData,
            dataType:"json",
            success:function (data) {
                if(data.code==200){
                    //成功
                    layer.msg(msg,function () {
                        // 关闭弹出层
                        layer.closeAll("iframe");
                        // 刷新父页面，重新渲染表格数据
                        parent.location.reload();
                    });
                }else {
                    //失败
                    layer.msg(data.msg);
                }
            }
        });
        //取消表单默认跳转
        return false;
    });

    //取消
    $("#closeBtn").click(function () {
        // 当你在iframe页面关闭自身时
        var index = parent.layer.getFrameIndex(window.name);
        // 先得到当前iframe层的索引再执行关闭
        parent.layer.close(index);
    });

})