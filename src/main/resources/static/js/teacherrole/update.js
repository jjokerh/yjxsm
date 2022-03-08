layui.use(['form', 'layer','formSelects'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        formSelects = layui.formSelects,
        $ = layui.jquery;

    form.on('submit(update)', function(data){
        console.log(data);
        var url=ctx+"/teacherRole/save";
        /*发送ajax操作*/
        $.post(url,data.field,function (result){
            console.log(result)
            //判断
            if(result.code==200){
                layer.msg("添加成功");
                //刷新
                window.parent.location.reload();
            }else{
                //提示一下
                layer.msg(result.msg,{icon:5 });
            }
        },"json");
        //取消的默认行为
        return false;
    });

    /**
     * 加载下拉框数据
     */
    formSelects.config('selectId',{
        type:"post",
        searchUrl:ctx + "/role/roles"+"?id="+$("[name=id]").val(),
        //自定义返回数据中name的key, 默认 name
        keyName: 'roleName',
        //自定义返回数据中value的key, 默认 value
        keyVal: 'rid'
    },true);

    $("#closeBtn").click(function(){
        //关闭弹出层
        //假设这是iframe页
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    });
});