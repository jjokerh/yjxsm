layui.use(['element', 'layer', 'layuimini','jquery','jquery_cookie'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        $ = layui.jquery_cookie($);

    //退出账号返回登录
    // 菜单初始化
    $('#layuiminiHomeTabIframe').html('<iframe width="100%" height="100%" frameborder="0"  src="welcome"></iframe>')
    layuimini.initTab();
    /*选择原始绑定事件*/
    $(".login-out").click(function () {
        $.ajax({
            type:"post",
            url:ctx+"/logout",
            data:"",
            dataType:"json",
            success:function (data){
                //console.log(data);
                layer.msg(data.msg)
            }
        });
        //清空Cookie信息
        $.removeCookie("JSESSIONID",{domain:"localhost",path:"/yjxms"})
        $.removeCookie("userIdStr",{domain:"localhost",path:"/yjxms"})
        $.removeCookie("userName",{domain:"localhost",path:"/yjxms"})
        $.removeCookie("role",{domain:"localhost",path:"/yjxms"})

        //跳转页面
        window.parent.location.href=ctx+"/index";
    });
});