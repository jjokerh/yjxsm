<!DOCTYPE html>
<html>
<head>
    <title>阶段管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="id" class="layui-input
							searchVal" placeholder="阶段ID" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="stageName"
                           class="layui-input
							searchVal" placeholder="阶段名" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="teacherName" class="layui-input
							searchVal" placeholder="授课老师" />
                </div>
                <a class="layui-btn course_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>

    <!-- 数据表格 -->
    <table id="course" class="layui-table"  lay-filter="courseFilter">
    </table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                新增
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                删除
            </a>
        </div>
    </script>


    <!--操作-->
    <script id="courseListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>

</form>

<script type="text/javascript" src="${ctx}/js/stage/stage.js"></script>
</body>
</html>