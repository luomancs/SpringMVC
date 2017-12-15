<%--
  Created by IntelliJ IDEA.
  User: Mango
  Date: 2017/5/8
  Time: 15:36
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
    <title>实验课 详情</title>

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
    <h1>实验课 ${subjectEntity.question} 详情</h1>
    <hr/>
    <table class="table table-bordered table-striped">
        <tr>
            <th>指导教师</th>
            <th>${experienceEntity.userByUserId.userName}</th>
        </tr>
        <tr>
            <th>实验要点</th>
            <th>${subjectEntity.gist}</th>
        </tr>
        <tr>
            <th>实验截止时间</th>
            <th>${experienceEntity.deadline}</th>
        </tr>
        <tr>
            <th>我的答案</th>
            <th>${subjectEntity.studentAnswer}</th>
        </tr>
    </table>
</div>

</body>
</html>
