layui.use(['form', 'layer','formSelects'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        formSelects = layui.formSelects;
    $ = layui.jquery;

    //工具栏编辑信息
    form.on("submit(edit)",function () {
        // var index=layer.msg("正在提交数据请稍后..");
        var title="<h2>更改个人资料</h2>"
        url=ctx+"/student/updateMessage";
        console.log(url);
        layui.layer.open({
            title:title,
            type:2,
            maxmin:true,
            area:["500px","400px"],
            content:url
        });
    })
    //
    form.on("submit(class)",function () {
        // var index=layer.msg("正在提交数据请稍后..");
        var title="<h2>我的班级</h2>"
        url=ctx+"/student/class";
        console.log(url);
        layui.layer.open({
            title:title,
            type:2,
            maxmin:true,
            area:["600px","300px"],
            content:url
        });
    })
    //
    form.on("submit(course)",function () {
        // var index=layer.msg("正在提交数据请稍后..");
        var title="<h2>我的课程</h2>"
        url=ctx+"/student/course";
        console.log(url);
        layui.layer.open({
            title:title,
            type:2,
            maxmin:true,
            area:["600px","300px"],
            content:url
        });
    })












    // //搜索
    // $(".search_btn").click(function (){
    //     tableIns.reload({
    //         where:{
    //             userName:$("input[name='userName']").val(),
    //             email:$("input[name='email']").val(),
    //             phone:$("input[name='phone']").val()
    //         },
    //         page:{
    //             //默认第一页开始
    //             curr:1
    //         }
    //     });
    // });



   // //行工具栏
   //  table.on('tool(stu)', function(obj){
   //      var data = obj.data; //获得当前行数据
   //      var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
   //      var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
   //      //编辑
   //      if(layEvent === 'edit'){
   //          OpenDialogAddOrUpdateUser(data.id);
   //          //单行删除
   //      } else if(layEvent === 'del'){
   //         deleteUser(data);
   //      }
   //  });
   //
   // //修改函数
   // function updateMessage(studentId){
   //     var title="<h2>修改信息</h2>";
   //     var url=ctx+"/student/openDialogUpdate?studentId="+studentId;
   //     layer.open({
   //         title:title,
   //         type:2,//当前层
   //         content:url,
   //         maxmin:true,
   //         area:["550px","500px"]
   //     });
   // }
   //
   // //多行删除函数
   //  function deleteUsers(datas){
   //     if(datas.length==0){
   //         layer.msg("至少选择一个用户");
   //         return ;
   //     }
   //      layer.confirm('是否要删除这些数据',{btn:["确认","取消"]}, function(index){
   //          layer.close(index);
   //          var ids=[];
   //              for(var x in datas){
   //                  ids.push(datas[x].id);
   //              }
   //          //向服务端发送删除指令
   //          $.ajax({
   //              type:"post",
   //              url:ctx+"/user/delete2",
   //              data:{"ids":ids.toString()},
   //              success:function (result) {
   //                  if(result.code==200){
   //                      layer.msg("已删除")
   //                     tableIns.reload();
   //                  }else{
   //                      layer.msg(result.msg)
   //                  }
   //              }
   //          });
   //      });
   //  }
   //  //单行删除函数
   //  function deleteUser(data) {
   //      layer.confirm('是否要删除该条数据',{btn:["确认","取消"]}, function(index){
   //          layer.close(index);
   //          //向服务端发送删除指令
   //          $.ajax({
   //              type:"post",
   //              url:ctx+"/user/delete2",
   //              data:{"ids":data.id},
   //              success:function (result) {
   //                  if(result.code==200){
   //                      layer.msg("已删除")
   //                      tableIns.reload();
   //                  }else{
   //                      layer.msg(result.msg)
   //                  }
   //              }
   //          });
   //      });
   //  }
});
