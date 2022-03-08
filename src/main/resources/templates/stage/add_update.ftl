<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">

    <input type="hidden" id="id" name="id"
           value="<#if (stage.id)??>${(stage.id)?c}</#if>">

    <input type="hidden" name="tid" value="${(stage.tId)!}">

    <input type="hidden" name="teacherName" id="teacherName" value="${(stage.teacherName)!}">

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">阶段</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="stageName" id="stageName"  value="${(stage.stageName)!}" placeholder="请输入课程名称">
        </div>
    </div>
    <div class="layui-form">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">开课时间</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input"
                           value="<#if (stage.createDate)??>${(stage.createDate)?string("yyyy-MM-dd")}</#if>"
                           id="createDate" name="createDate" placeholder="yyyy-MM-dd">
                </div>
            </div>
        </div>
    </div>

    <div class="layui-form">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">结束时间</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input"
                           value="<#if (stage.updateDate)??>${(stage.updateDate)?string("yyyy-MM-dd")}</#if>"
                           id="updateDate" name="updateDate" placeholder="yyyy-MM-dd">
                </div>
            </div>
        </div>
    </div>


    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">上课周数</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="period"
                   name="period" value="${(stage.period)!}" id="period" placeholder="请设置周数">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">授课老师</label>
        <div class="layui-input-block">
            <select name="teacher" id="teacher">
                <option value="">请选择</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdate">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/stage/add_update.js"></script>
</body>
</html>