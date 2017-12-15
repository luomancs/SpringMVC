<%--
  Created by IntelliJ IDEA.
  User: Mango
  Date: 2017/5/3
  Time: 20:03
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
    <title>实验题目详情</title>

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

    <h1>实验题目 ${subjectEntity.question} 详情</h1>
    <hr/>
    <table class="table table-bordered table-striped">
        <tr>
            <th>Property</th>
            <th>Value</th>
        </tr>
        <tr>
            <th>RightAnswer</th>
            <th>${subjectEntity.rightAnswer}</th>
        </tr>
        <tr>
            <th>Gist</th>
            <th>${subjectEntity.gist}</th>
        </tr>
        <tr>
            <th>Mark</th>
            <th>${subjectEntity.mark}</th>
        </tr>

    </table>
    <hr/>
    <a href="/pages/Subject/update/${subjectEntity.subjectId}" type="button" class="btn btn-sm btn-warning">修改</a>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>