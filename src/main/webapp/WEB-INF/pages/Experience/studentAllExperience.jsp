<%--
  Created by IntelliJ IDEA.
  User: Mango
  Date: 2017/5/4
  Time: 14:17
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
<body>
<div class="container">
    <h1>查看所有实验课主页</h1>
    <hr/>
    <div>
        <a href="/pages/student/checkAllExperience" type="button" class="btn btn-primary btn-sm">查看所有实验课</a>
        <a href="/pages/student/MyExperience" type="button" class="btn btn-primary btn-sm">查看我的实验课</a>
        <a href="/pages/student/Score" type="button" class="btn btn-primary btn-sm">查看实验得分情况</a>
    </div>
    <br>
    <!-- 如果实验列表为空 -->
    <c:if test="${empty mapList}">
        <div class="alert alert-warning" role="alert">
            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>实验列表为空
        </div>
    </c:if>

    <!-- 如果实验列表非空 -->
    <c:if test="${!empty mapList}">
        <table class="table table-bordered table-striped">
            <tr>
                <th>objective</th>
                <th>teacher</th>
                <th>state</th>
                <th>operation</th>
            </tr>
            <c:forEach items="${mapList}" var="map" >
                <tr>
                    <td>${map.key.getObjective()}</td>
                    <td>${map.key.getUserByUserId().userName}</td>
                    <td>${map.value}</td>
                    <td>
                        <a href="/pages/Experience/student/detail/${map.key.experienceId}" type="button" class="btn btn-sm btn-success">实验详情</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
