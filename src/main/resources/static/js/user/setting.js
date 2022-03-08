layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);
    form.on("submit(saveBtn)",function (obj) {
        var fieldDdata=obj.field;
        //发送ajax修改数据
        $.ajax({
            type:"post",
            url:ctx+"/user/updatesetting",
            data:{
                phone:fieldDdata.phone,
                email:fieldDdata.email,
                truename:fieldDdata.trueName,
                id:fieldDdata.id
            },
            dataType:"json",
            success:function (data) {
                if(data.code=200){
                    layer.msg("修改成功",function (){

                        //跳转
                        window.parent.location.href=ctx+"/index";
                    });
                }else {
                    //提示一下
                    layer.msg(data.msg);
                }
            }
        });
        //取消默认表单提交
        return false;
    });
});