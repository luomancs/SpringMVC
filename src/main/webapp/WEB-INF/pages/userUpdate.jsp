<%--
  Created by IntelliJ IDEA.
  User: Mango
  Date: 2017/5/20
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>


<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>SpringMVC 添加用户</title>

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
    <h1>SpringMVC 添加用户</h1>
    <hr/>

    <form:form action="/updateUser" method="post" commandName="user" role="form">
        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <input type="hidden" class="form-control" id="id" name="id" value="${userEntity.id}" />

                    <div class="form-group">
                        <label for="userName">userName:</label>
                        <input type="text" class="form-control" id="userName" name="userName" value="${userEntity.userName}"/>
                    </div>
                    <div class="form-group">
                        <label for="identity">identity:</label>
                        <input type="text" class="form-control" id="identity" name="identity" value="${userEntity.identity}"/>
                    </div>
                    <div class="form-group">
                        <label for="institution">institution:</label>
                        <input type="text" class="form-control" id="institution" name="institution" value="${userEntity.institution}"/>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="text" class="form-control" id="password" name="password" value="${userEntity.password}"/>
                    </div>

                    <div class="form-group">
                        <label for="major">major:</label>
                        <input type="text" class="form-control" id="major" name="major" value="${userEntity.major}"/>
                    </div>
                    <div class="form-group">
                        <label for="img">img:</label>
                        <input type="text" class="form-control" id="img" name="img" value="${userEntity.img}"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success" >提交</button>
        </div>
    </form:form>

    <form action="/changePhoto" method="post" enctype="multipart/form-data" >
        <input type="file" name="file">
        <input type="submit">
    </form>
    <!--接收-->
    <img src="${pageContext.request.contextPath}/${img}"/>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>