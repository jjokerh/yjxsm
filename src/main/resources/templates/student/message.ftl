<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<#--    <input name="id" type="hidden" value="${(student.id)!}"/>-->
<#--    <button class="layui-btn layui-btn-primary layui-border-blue" lay-submit=""-->
<#--            lay-filter="my">我的资料-->
<#--    </button>-->
<form class="layui-form" style="width:80%;">
    <input name="id" type="hidden" value="${(student.id)!}"/>
<#--    <div class="layui-form-item layui-row layui-col-xs12">-->
<#--        <label class="layui-form-label">ID</label>-->
<#--        <div class="layui-input-block">-->
<#--            <input type="text" class="layui-input userName"-->
<#--                   lay-verify="required" name="studentName" id="studentId" value="${(student.id)!}" placeholder="请输入姓名">-->
<#--        </div>-->
<#--    </div>-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" readonly="readonly" name="studentName" id="studentName" value="${(student.studentName)!}" placeholder="请输入姓名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">qq号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName" lay-verify="required" readonly="readonly"
                   name="qq" id="qq" value="${(student.qq)!}" placeholder="请输入qq">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName" lay-verify="required" readonly="readonly" name="sex" id="sex" value="${(student.sex)!}" placeholder="性别">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName" lay-verify="required"
                   name="phone" id="phone" readonly="readonly"  value="${(student.phone)!}" placeholder="请输入手机号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">班级号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName" lay-verify="required"
                   name="phone" id="phone" readonly="readonly"  value="${(student.cId)!}" placeholder="班级号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">QQ昵称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"  lay-verify="required" readonly="readonly" name="trueName" id="trueName" value="${(student.trueName)!}" placeholder="请输入昵称">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">创建日期</label>
        <div class="layui-input-block">
            <input type="text" name="admTime" readonly="readonly" id="createDate" value="<#if (student.createDate)??>${(student.createDate)?string("yyyy-MM-dd")}</#if>"
                   lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">修改日期</label>
        <div class="layui-input-block">
            <input type="text" name="admTime" id="updateDate" readonly="readonly" value="<#if (student.updateDate)??>${(student.updateDate)?string("yyyy-MM-dd")}</#if>"
                   lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
        </div>
    </div>
    <br/>
</form>
<div class="layui-form-item layui-row layui-col-xs12">
    <div class="layui-input-block">
        <button class="layui-btn layui-btn-primary layui-border-black" lay-submit=""
                lay-filter="edit">
            <i class="layui-icon">&#xe642;</i>
            编辑信息
        </button>
    </div>
</div>
<script type="text/javascript" src="${ctx}/js/student/message.js"></script>
</body>
</html>