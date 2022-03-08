<!DOCTYPE html>
<html>
<head>
    <title>用户管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >
    <input name="id" type="hidden" value="${(student.id)!}"/>
<#--    <blockquote class="layui-elem-quote quoteBox">-->
<#--        <form class="layui-form">-->
<#--            <div class="layui-inline">-->
<#--                <div class="layui-input-inline">-->
<#--                    <input type="text" name="userName" class="layui-input searchVal" placeholder="用户名" />-->
<#--                </div>-->
<#--                <div class="layui-input-inline">-->
<#--                    <input type="text" name="email" id="email" class="layui-input searchVal" placeholder="邮箱" />-->
<#--                </div>-->
<#--                <div class="layui-input-inline">-->
<#--                    <input type="text" name="phone" class="layui-input searchVal" placeholder="手机号" />-->
<#--                </div>-->
<#--                <a class="layui-btn search_btn" data-type="reload">-->
<#--                    <i class="layui-icon">&#xe615;</i>-->
<#--                    搜索-->
<#--                </a>-->
<#--            </div>-->
<#--        </form>-->
<#--    </blockquote>-->

    <table id="mcList" class="layui-table"  lay-filter="myCourse"></table>

    <script type="text/html" id="toolbarDemo">
        <#--<h2>${student.studentName}的课程</h2>-->
<#--        <div class="layui-btn-container">-->
<#--            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">-->
<#--                <i class="layui-icon">&#xe654;</i>-->
<#--                我要选课-->
<#--            </a>-->
<#--            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="del">-->
<#--                <i class="layui-icon">&#x1006;</i>-->
<#--                批量退课-->
<#--            </a>-->
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="find">
                <i class="layui-icon">&#xe615;</i>
                分数查询
            </a>
        </div>
    </script>

    <!--操作-->
    <script id="mcListBar" type="text/html">
<#--        <a class="layui-btn layui-btn-xs" id="edit" lay-event="find">-->
<#--            <i class="layui-icon">&#xe615;</i>-->
<#--            查询分数-->
<#--        </a>-->
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">
            <i class="layui-icon">&#xe640;</i>
            退课
        </a>
    </script>
</form>

<script type="text/javascript" src="${ctx}/js/student/myCourse.js"></script>
</body>



</html>