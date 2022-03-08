layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
        formSelects= layui.formSelects;

    form.on("submit(addOrUpdateStudent)",function (obj) {
        //var fieldData=obj.field;
        var index=top.layer.msg("数据正在加载中...",{time:false,shade:0.8,icon:16});
        //var url =ctx+"/studentStage/addScore";
        var url =ctx+"/studentStage/updateScore";

        //判断是否作修改操作
      /*  if ($("input[name='id']").val()){
            url=ctx+"/studentStage/updateScore"
        }*/
        $.post(url,obj.field,function(result){
            if(result.code==200){
                //定时执行，定时器
                setTimeout(function(){
                    //关闭加载层
                    top.layer.close("index");
                    //提示消息
                    top.layer.msg("评分成功了");
                    //关闭所有的iframe;
                    layer.closeAll("iframe");
                    //刷新
                    parent.location.reload();
                },500);
            }else{
                layer.msg(result.msg,{icon : 5 });
            }
        },"json");
        //取消默认跳转
        return false;
    });
    /**
     * 取消
     */
    $("#closeBtn").click(function () {
        // 当你在iframe页面关闭自身时
        var index = parent.layer.getFrameIndex(window.name);
        // 先得到当前iframe层的索引
        parent.layer.close(index);
        // 再执行关闭
    });

})