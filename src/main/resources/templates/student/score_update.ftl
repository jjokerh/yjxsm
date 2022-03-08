<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#-- 设置学生的ID -->
    <input type="hidden" name="studentId" value="${(studentStageDto.studentId)!}">
    <#--设置学生的班级-->
    <input type="hidden" name="courseName" value="${(studentStageDto.clazzName)!}">


    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">学生姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="studentName" id="studentName" value="${(studentStageDto.studentName)!}" placeholder="学生姓名" readonly>
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">学生成绩ID</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="id" id="id" value="${(studentStageDto.id)!}" placeholder="学生成绩ID" readonly>
        </div>
    </div>
    <br/>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="phone"
                   name="phone" value="${(studentStageDto.phone)!}" id="phone" placeholder="联系电话" readonly>
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">班级</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="clazzName" value="${(studentStageDto.clazzName)!}<#--<#if (studentStageDto.clazzName)??>${(studentStage.clazzName)?}</#if>-->" id="clazzName" placeholder="班级" readonly>
        </div>
    </div>
    <br/>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">阶段ID</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="stageId" id="id" value="${(studentStageDto.stageId)!}" placeholder="阶段ID" readonly>
        </div>
    </div>
    <br/>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">阶段名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="stageName" value="${(studentStageDto.stageName)!}" id="stageName" placeholder="班级名称" readonly>
        </div>
    </div>
   <#-- <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">课程</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="courseName" value="${(studentStageDao.stageName)!}" id="phone" placeholder="课程" readonly>
        </div>
    </div>-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">分数</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="score" value="${(studentStageDto.score)!}" id="score" placeholder="分数">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateStudent">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>

<script type="text/javascript" src="${ctx}/js/student/score_update.js"></script>
</body>
</html>