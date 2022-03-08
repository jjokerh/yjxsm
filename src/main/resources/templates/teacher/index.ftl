<!DOCTYPE html>
<html>
<head><title>教师信息</title> <#include "../common.ftl"> </head>
<body class="childrenBody">
<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" id="id" name="id" class="layui-input searchVal" placeholder="编号"/>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="teacherName" class="layui-input searchVal" placeholder="教师姓名"/>
                </div>
                <div class="layui-input-inline">
                    <select name="sex" id="sex">
                        <option value="">性别</option>
                        <option value="男" name="sex">男</option>
                        <option value="女" name="sex">女</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="roleName" id="roleName">
                        <option value="">职位</option>
                        <option value="teacher" name="roleName">授课老师</option>
                        <option value="headmaster" name="roleName">班主任</option>
                        <option value="admin" name="roleName">admin</option>
                    </select>
                </div>

                <a class="layui-btn search_btn" data-type="reload"> <i class="layui-icon">&#xe615;</i> 搜索 </a>
            </div>
        </form>
    </blockquote>
    <!-- 数据表格 -->
    <table id="saleChanceList" class="layui-table" lay-filter="saleChances"></table>
    <!-- 头部工具栏 -->
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container"><a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i> 添加 </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>删除</a>
        </div>
    </script>
    <!-- 行工具栏 -->
    <script id="saleChanceListBar" type="text/html"> <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a> </script>
</form>
<script type="text/javascript" src="${ctx}/js/teacher/teach.js"></script>
</body>
</html>