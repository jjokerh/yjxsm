layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    //学生模块----提交资料
    form.on("submit(save)", function (data) {
        var index = layer.msg("正在更新数据");
        //添加
        var url = ctx + "/student/save";
        console.log(url);
        //ajax
        $.post(url, data.field, function (result) {
            if (result.code == 200) {
                setTimeout(function () {
                    layer.close(index);
                    layer.msg("已保存");
                    layer.closeAll("iframe");
                    //刷新
                    parent.location.reload();
                }, 300);
            } else {
                layer.msg(result.msg);
            }
        })
        return false;
    })
    //取消提交
    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name)//拿到层数索引
        parent.layer.close(index);
    });




    // form.on("submit(class)", function (data) {
    //     var index = layer.msg("正在更新数据");
    //     //添加
    //     var url = ctx + "/student/class";
    //     console.log(url);
    //     //ajax
    //     $.post(url, data.field, function (result) {
    //         if (result.code == 200) {
    //             setTimeout(function () {
    //                 layer.close(index);
    //                 layer.msg("已保存");
    //                 layer.closeAll("iframe");
    //                 //刷新
    //                 parent.location.reload();
    //             }, 300);
    //         } else {
    //             layer.msg(result.msg);
    //         }
    //     })
    //     return false;
    // })




});












