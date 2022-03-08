<!DOCTYPE html>
<html>
<head><title>营销机会管理</title> <#include "../common.ftl"> </head>
<body class="childrenBody">
<form class="layui-form">
    <!-- 数据表格 -->
    <table id="saleChanceList" class="layui-table" lay-filter="saleChances"></table>
    <!-- 行工具栏 -->
    <script id="saleChanceListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">职务</a>
    </script>
</form>
<script type="text/javascript" src="${ctx}/js/teacherrole/teacherrole.js"></script>
</body>
</html>