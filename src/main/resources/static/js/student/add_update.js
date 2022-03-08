layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    formSelects = layui.formSelects;

    form.on("submit(addOrUpdateStudent)", function (data) {
        var index = top.layer.msg("数据正在加载中...", {time: false, shade: 0.8, icon: 16});
        //判断添加还是修改
        var url = ctx + "/student/add";
        //判断是否做修改操作
        if ($("input[name='id']").val()) {
            url = ctx + "/student/update";
        }
        /*发送ajax添加*/
        $.post(url, data.field, function (result) {
            if (result.code == 200) {
                //定时执行，定时器
                setTimeout(function () {
                    //关闭加载层
                    top.layer.close("index");
                    //提示消息
                    top.layer.msg("添加成功了");
                    //关闭所有的iframe;
                    layer.closeAll("iframe");
                    //刷新
                    parent.location.reload();
                });
            } else {
                layer.msg(result.msg, {icon: 5});
            }
        }, "json");

        //取消表单默认跳转
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

    /**
     * 下拉加载框
     */
    $.ajax({
        type: "post",
        url: ctx + "/student/find",
        dataType: "json",
        success: function (data) {
            // 如果是修改操作，判断当前修改记录的班级名称的值
            var clazzId = $("input[name='cId']").val();

            for (var i = 0; i < data.length; i++) {
                // 当前修改记录的班级名称 与 循环到的值相等，下拉框则选中
                if (clazzId == data[i].id) {
                    $("#cId").append('<option value="' + data[i].id + '" selected>' + data[i].clazz_name + '</option>');
                } else {
                    $("#cId").append('<option value="' + data[i].id + '">' + data[i].clazz_name + '</option>');
                }
            }
            // 重新渲染下拉框内容
            layui.form.render("select");
        }
    });

})