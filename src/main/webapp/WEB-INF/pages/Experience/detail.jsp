<%--
  Created by IntelliJ IDEA.
  User: Mango
  Date: 2017/5/2
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>


<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>实验课 添加</title>

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

    <h1>实验课 ${experience.objective} 详情</h1>
    <hr/>
    <table class="table table-bordered table-striped">
        <tr>
            <th>Property</th>
            <th>Value</th>

        </tr>
        <tr>
            <th>TeacherId</th>
            <th>${experience.userByUserId.id}</th>
        </tr>
        <tr>
            <th>SelectAmount</th>
            <th>${experience.selectAmount}</th>
        </tr>
        <tr>
            <th>SubjectAmount</th>
            <th>${experience.subjectAmount}</th>
        </tr>
        <tr>
            <th>Period</th>
            <th>${experience.period}</th>
        </tr>
        <tr>
            <th>Period</th>
            <th>${experience.begin}</th>
        </tr>
        <tr>
            <th>Deadline</th>
            <th>${experience.deadline}</th>
        </tr>

    </table>
    <h2>已公布的的题目</h2>
    <hr/>
    <c:if test="${empty subjectList}">
        <div class="alert alert-warning" role="alert">
            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>Subject表为空，请添加
        </div>
    </c:if>

    <!-- 如果用户列表非空 -->
    <c:if test="${!empty subjectList}">
        <table class="table table-bordered table-striped">
            <tr>
                <th>subjectQuestion</th>
                <!--<th>studentAnswer</th>-->
                <th>operation</th>

            </tr>

            <c:forEach items="${subjectList}" var="subject">

                <tr>
                    <td>${subject.getQuestion()}</td>
                    <td>
                        <a href="/pages/Subject/delete/${subject.subjectId}" type="button" class="btn btn-sm btn-danger">删除</a>
                        <a href="/pages/Subject/update/${subject.subjectId}" type="button" class="btn btn-sm btn-warning">修改</a>
                        <a href="/pages/Subject/detail/${subject.subjectId}" type="button" class="btn btn-sm btn-success">详情</a>
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
</html>