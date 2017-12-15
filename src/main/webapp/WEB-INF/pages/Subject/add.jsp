<%--
  Created by IntelliJ IDEA.
  User: Mango
  Date: 2017/5/2
  Time: 20:36
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
    <h1>实验课已注册的题目 </h1>
    <hr/>
    <c:if test="${empty subjectList}">
        <div class="alert alert-warning" role="alert">
            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>该实验目前没有题目，请添加
        </div>
    </c:if>

    <!-- 如果用户列表非空 -->
    <c:if test="${!empty subjectList}">

        <table class="table table-striped table-bordered table-hover">
            <tr>
                <th>subjectQuestion</th>
                <!--<th>studentAnswer</th>-->
                <th>operation</th>

            </tr>

            <c:forEach items="${subjectList}" var="subject">
                <tr>
                    <td>${subject.getQuestion()}</td>
                    <td>
                        <a href="/pages/Subject/update/${subject.subjectId}" type="button" class="btn btn-sm btn-warning">修改</a>
                        <a href="/pages/Subject/detail/${subject.subjectId}" type="button" class="btn btn-sm btn-success">详情</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <h1>实验课题目 添加</h1>
    <hr/>
    <form:form action="/addSubject/${experienceEntity.experienceId}" method="post" commandName="subject" role="form">
        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        添加实验题目
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>题目内容</th>
                                    <th>输入</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>
                                        <label for="question">实验问题</label>
                                    </td>
                                    <td>
                                        <input type="text" class="form-control" id="question" name="question" placeholder="输入题目"/>
                                    </td>

                                </tr>
                                <tr>
                                    <td>
                                        <label for="rightAnswer">题目答案</label>
                                    </td>
                                    <td>
                                        <textarea class="form-control" id="rightAnswer" name="rightAnswer" rows="3" placeholder="输入答案"></textarea>
                                    </td>

                                </tr>
                                <tr>
                                    <td>
                                        <label for="gist">评分重点</label>
                                    </td>
                                    <td>
                                        <textarea class="form-control" id="gist" name="gist" rows="3" placeholder="输入要点"></textarea>
                                    </td>

                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.table-responsive -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">提交</button>
        </div>
    </form:form>
</div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>