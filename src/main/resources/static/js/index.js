layui.use(['form', 'jquery', 'jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);


    form.on("submit(login)", function (obj) {

        var data = obj.field;
        var url = ctx + "/teacher/login";

        var m = $("#role").find("option:selected").val();
        //判断老师或学生

        if (data.username === 'undefined' || data.username.trim() === '') {
            layer.msg("请输入用户名");
            return false;
        }
        if (data.password === 'undefined' || data.password.trim() === '') {
            layer.msg("请输入密码");
            return false;
        }

        if (m === "student") {
            url = ctx + "/student/login";
            $.ajax({
                type: "post",
                url: url,
                data: {
                    stuName: data.username,
                    stuPwd: data.password
                },
                dataType: "json",
                success: function (obj) {
                    if (obj.code === 200) {
                        layer.msg("登录成功", function () {
                            var result = obj.result;
                            console.log(result.userIdStr);
                            $.cookie("userIdStr", result.userIdStr);
                            $.cookie("userName", result.userName);
                            $.cookie("role", result.role);
                            //选择复选框
                            layer.msg($(":checkbox").prop("checked"));
                            if ($(":checkbox").prop("checked")) {
                                $.cookie("userIdStr", result.idStr, {expires: 7});
                                $.cookie("userName", result.userName, {expires: 7});
                                $.cookie("role", result.role, {expires: 7});
                            }
                            window.location.href = ctx + "/main";
                            //跳转
                            /* if (m == "teacher") {
                                 window.location.href = ctx + "/main";
                             }
                             window.location.href=ctx+"/student/main";*/
                        });
                    } else {
                        layer.msg(obj.msg, {icon: 6});
                    }
                }
            });
        }

        if (m === "teacher" || m === "headmaster" || m === "admin") {
            $.ajax({
                type: "post",
                url: url,
                data: {
                    teaName: data.username,
                    teaPwd: data.password,
                    role: $("#role").find("option:selected").val()
                },
                dataType: "json",
                success: function (obj) {
                    if (obj.code === 200) {
                        layer.msg("登录成功", function () {
                            var result = obj.result;
                            $.cookie("userIdStr", result.userIdStr);
                            $.cookie("userName", result.teacherName);
                            $.cookie("role", result.role);
                            //选择复选框
                            layer.msg($(":checkbox").prop("checked"));
                            if ($(":checkbox").prop("checked")) {
                                $.cookie("userIdStr", result.userIdStr, {expires: 7});
                                $.cookie("userName", result.teacherName, {expires: 7});
                                $.cookie("role", result.role, {expires: 7});
                            }
                            window.location.href = ctx + "/main";
                            //跳转
                            /* if (m == "teacher") {
                                 window.location.href = ctx + "/main";
                             }
                             window.location.href=ctx+"/student/main";*/
                        });
                    } else {
                        layer.msg(obj.msg, {icon: 6});
                    }
                }
            });
        }

        //阻止页面跳转
        return false;
    });

});