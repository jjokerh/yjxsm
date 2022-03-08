<!DOCTYPE html>
<html>
<head>
    <title>学生管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="studentName"
                           class="layui-input
							searchVal" placeholder="用户名" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="cId" class="layui-input
							searchVal" placeholder="班级" />
                </div>
                <div class="layui-input-inline">
                    <select name="state"  id="state">
                        <option value="">学生状态</option>
                        <option value="0">休学</option>
                        <option value="1">正常</option>
                    </select>
                </div>
                <a class="layui-btn search_btn" data-type="reload" id="searchBtn">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>

    <!-- 数据表格 -->
    <table id="studentList" class="layui-table"  lay-filter="student">
    </table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe61f;</i>
                添加
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                删除
            </a>
        </div>
    </script>

    <!--操作-->
    <script id="studentListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>

</form>

<script type="text/javascript" src="${ctx}/js/student/student.js"></script>
</body>
</html>