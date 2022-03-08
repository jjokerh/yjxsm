layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);
    form.on("submit(saveBtn)",function (obj) {
        var fieldData=obj.field;
        $.ajax({
            type:"post",
            url:ctx+"/student/updatePwd",
            data:{
                oldPassword:fieldData.old_password,
                newPassword:fieldData.new_password,
                confirmPassword:fieldData.again_password
            },
            dataType:"json",
            success:function (data) {
                if(data.code==200){
                    layer.msg("修改成功，3秒后跳转登录界面",function () {
                        $.removeCookie("studentIdStr",{domain:"localhost",path:"/ems"});
                        $.removeCookie("studentName",{domain:"localhost",path:"/ems"});
                        $.removeCookie("trueName",{domain:"localhost",path:"/ems"});
                        window.parent.location.href=ctx+"/index";
                    });
                }else {
                    layer.msg(data.msg);
                }
            }
        });
        return false;
    })
    //退出登录
    $("#login-out").click(function (){

        $.removeCookie("studentIdStr",{domain:"localhost",path:"/ems"});
        $.removeCookie("studentName",{domain:"localhost",path:"/ems"});
        $.removeCookie("trueName",{domain:"localhost",path:"/ems"});
        window.parent.location.href=ctx+"/index";
    });




});
