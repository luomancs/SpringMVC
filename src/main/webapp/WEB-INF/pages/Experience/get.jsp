<%--
  Created by IntelliJ IDEA.
  User: Mango
  Date: 2017/5/2
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>教师实验课信息</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body >
<div class="container">
    <h1>教师实验课主页</h1>
    <hr/>
    <div>
        <a href="/pages/teacher/findExperienceAdd" type="button" class="btn btn-primary btn-sm">添加实验课</a>
        <a href="/pages/teacher/ExperienceGet" type="button" class="btn btn-primary btn-sm">查看实验课</a>
        <a href="/pages/teacher/Score" type="button" class="btn btn-primary btn-sm">查看实验得分情况</a>
    </div>
    <br>
    <!-- 如果用户列表为空 -->
    <div class="row">
        <div class="col-lg-8">
            <div class="panel panel-default">
    <c:if test="${empty experienceList}">
        <div class="alert alert-warning" role="alert">
            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>Experience表为空，请<a href="pages/teacher/ExperienceAdd/${userId}" type="button" class="btn btn-primary btn-sm">添加</a>
        </div>
    </c:if>

    <!-- 如果用户列表非空 -->
    <c:if test="${!empty experienceList}">
        <table class="table table-bordered table-striped">
            <tr>
                <th>实验</th>
                <th>人数限定</th>
                <th>已选人数</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${experienceList}" var="experience">
                <tr>
                    <td>${experience.getObjective()}</td>
                    <td>${experience.getSubjectAmount()}</td>
                    <td>${experience.getSelectAmount()}</td>
                    <td>
                        <a href="/pages/Experience/get/subjectJudge/${experience.getExperienceId()}" type="button" class="btn btn-sm btn-success">实验评分</a>
                        <a href="/pages/Experience/get/subjectAdd/${experience.getExperienceId()}" type="button" class="btn btn-sm btn-success">添加题目</a>
                        <a href="/pages/Experience/get/delete/${experience.getExperienceId()}" type="button" class="btn btn-sm btn-success"  onclick="return confirm('是否删除')">删除</a>
                        <a href="/pages/Experience/update/${experience.getExperienceId()}" type="button" class="btn btn-sm btn-success">修改</a>
                        <a href="/pages/Experience/detail/${experience.getExperienceId()}" type="button" class="btn btn-sm btn-success">详情</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
            </div>
        </div>
    </div>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
