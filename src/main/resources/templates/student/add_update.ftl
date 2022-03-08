<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#-- 设置学生的ID -->
    <input type="hidden" name="id" value="${(student.id)!}">
    <#--设置学生的班级-->
    <input type="hidden" name="cId" value="<#if (student.cId)??>${(student.cId)?c}</#if>">

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">学生姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="studentName" id="studentName"  value="${(student.studentName)!}" placeholder="请输入学生姓名">
        </div>
    </div>
 <#--   <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">学生姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="trueName" id="trueName" value="${(student.trueName)!}" placeholder="请输入学生姓名">
        </div>
    </div>-->

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <#--<input type="text" class="layui-input" lay-verify="required"
                   name="sex" id="sex" value="${(student.sex)!}" placeholder="请输入性别">-->
            <input type="radio" name="sex" value="男" title="男" checked>
            <input type="radio" name="sex" value="女" title="女" ${(student.sex)!}>
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">qq</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="qq"
                   lay-verify="required"  value="${(student.qq)!}" placeholder="请输入qq">
        </div>
    </div>

        <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="phone"
                   name="phone" value="${(student.phone)!}" id="phone" placeholder="请输入联系电话">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">选择班级</label>
        <div class="layui-input-block">
            <select name="cId" id="cId">
                <option value="">请选择</option>
            </select>
        </div>
    </div>
    <br/>

    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateStudent">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>

<script type="text/javascript" src="${ctx}/js/student/add_update.js"></script>
</body>
</html>