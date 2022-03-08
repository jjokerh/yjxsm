<!DOCTYPE html>
<html>
<head>
    <title>分数</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >
    <input name="id" type="hidden" value="${(student.id)!}"/>
    <!-- 数据表格 -->
    <table id="msList" class="layui-table"  lay-filter="scoreFind">
    </table>

    <#--<div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
&lt;#&ndash;            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdate">&ndash;&gt;
&lt;#&ndash;                确认&ndash;&gt;
&lt;#&ndash;            </button>&ndash;&gt;
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">确认</button>
        </div>
    </div>-->
</form>
<script type="text/javascript" src="${ctx}/js/student/findScore.js"></script>
</body>
</html>