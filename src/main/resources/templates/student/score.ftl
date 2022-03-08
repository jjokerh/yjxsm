<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <title>学生评分</title>
    <#include "../common.ftl">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">学生姓名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="studentName" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">班级</label>
                            <div class="layui-input-inline">
                                <select name="cId" id="cId">
                                    <option value="" >请选择班级</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">课程名称</label>
                            <div class="layui-input-inline">
                                <select name="course" id="course">
                                    <option value="">请选择课程名称</option>
                                </select>
                            </div>
                        </div>
                        <a class="layui-btn search_btn" data-type="reload" id="searchBtn">
                            <i class="layui-icon">&#xe615;</i> 搜索
                        </a>
                    </div>
                </form>
            </div>
        </fieldset>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">评分</a>
        </script>

    </div>
</div>
<script type="text/javascript" src="${ctx}/js/student/score.js"></script>
</body>
</html>