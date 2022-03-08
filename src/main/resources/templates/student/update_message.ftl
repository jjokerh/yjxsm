<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="id" type="hidden" value="${(student.id)!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="studentName" id="studentName" value="${(student.studentName)!}" placeholder="请输入姓名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">qq号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName" name="qq" id="qq" value="${(student.qq)!}" placeholder="请输入qq">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName" lay-verify="required" readonly="readonly" name="sex" id="sex" value="${(student.sex)!}" placeholder="性别请谨慎修改">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName" lay-verify="required"
                   name="phone" id="phone" value="${(student.phone)!}" placeholder="请输入手机号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">QQ昵称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName" name="trueName" id="trueName" value="${(student.trueName)!}" placeholder="请输入昵称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="save">
                <i class="layui-icon">&#xe605;</i>
                提交
            </button>
            <button class="layui-btn layui-btn-danger" id="closeBtn">
                <i class="layui-icon">&#xe65c;</i>
                返回
            </button>
        </div>
    </div>

</form>
<script type="text/javascript" src="${ctx}/js/student/update_message.js"></script>
</body>
</html>