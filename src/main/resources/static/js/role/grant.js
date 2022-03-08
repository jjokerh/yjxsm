layui.use('tree', function(){
    var tree = layui.tree;

    //渲染
    var inst1 = tree.render({
        elem: '#test1'  //绑定元素
        ,data: [{
            title: '学生管理' //一级菜单
            ,id:1
            ,field:'name10'
            ,checked:''
            ,children: [{
                title: '学生管理添加' //二级菜单
                ,id:2
                ,field:'name1010'
                ,checked:true
                ,children: [{
                    title: '学生管理删除' //三级菜单
                    ,id:3
                    ,field:'name1020'
                    ,checked:''
                    //…… //以此类推，可无限层级
                }]
            }]
        },{
            title: '教师管理' //一级菜单
            ,id:4
            ,field:'name20'
            ,checked:true
            ,children: [{
                title: '教师管理添加' //二级菜单
                ,id:5
                ,field:'name2010'
                ,checked:''
                ,children: [{
                    title: '教师管理删除' //三级菜单
                    ,id:6
                    ,field:'name2020'
                    ,checked:''
                    //…… //以此类推，可无限层级
                }]
            }]

        },{
            title: '班级管理' //一级菜单
            ,id:7
            ,field:'name30'
            ,checked:true
            ,children: [{
                title: '班级管理添加' //二级菜单
                ,id:8
                ,field:'name3010'
                ,checked:true
                ,children: [{
                    title: '班级管理删除' //三级菜单
                    ,id:9
                    ,field:'name3020'
                    ,checked:''
                    //…… //以此类推，可无限层级
                }]
            }]
        },{
            title: '课程管理' //一级菜单
            ,id:10
            ,field:'name40'
            ,checked:''
            ,children: [{
                title: '课程管理添加' //二级菜单
                ,id:11
                ,field:'name4010'
                ,checked:''
                ,children: [{
                    title: '课程管理删除' //三级菜单
                    ,id:12
                    ,field:'name4020'
                    ,checked:''
                    //…… //以此类推，可无限层级
                }]
            }]
        }
        ]
    });
});