<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>优极限系统-登录</title>
    <#include "common.ftl">
    <link rel="stylesheet" href="${ctx}/css/index.css" media="all">
</head>
<body>
<div class="layui-container">
    <div id="snowzone">
        <div class="admin-login-background">
            <div class="layui-form login-form">
                <form class="layui-form" action="">
                    <div class="layui-form-item logo-title">
                        <h1>优极限管理系统</h1>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-icon layui-icon-username" for="username"></label>
                        <input type="text" name="username" lay-verify="required|account" placeholder="用户名" autocomplete="off" class="layui-input" >
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-icon layui-icon-password" for="password"></label>
                        <input type="password" name="password" lay-verify="required|password" placeholder="密码" autocomplete="off" class="layui-input" >
                    </div>
                    <div>
                        <select id="role">
                            <option value="admin">管理员</option>
                            <option value="teacher">授课教师</option>
                            <option value="headmaster">班主任</option>
                            <option value="student">学生</option>
                        </select>
                    </div>

                    <div class="layui-form-item">
                        <input type="checkbox" name="rememberMe" id="rememberMe" value="true" lay_skin="primary" title="记住密码">
                    </div>

                    <div class="layui-form-item">
                        <button class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="login">登 录</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/js/index.js" charset="utf-8"></script>
</body>
</html>