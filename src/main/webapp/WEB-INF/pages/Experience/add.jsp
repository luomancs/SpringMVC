<%--
  Created by IntelliJ IDEA.
  User: Mango
  Date: 2017/5/2
  Time: 09:41
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
    <h1>实验课 添加</h1>
    <hr/>
<form:form action="/addExperience" method="post" commandName="experience" role="form">
    <div class="row">
        <div class="col-lg-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                   添加实验
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>实验信息</th>
                                <th>输入</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>1</td>
                                <td>
                                    <label for="objective">实验标题:</label>
                                </td>
                                <td>
                                    <input type="text" class="form-control" id="objective" name="objective" placeholder="Enter objective:"/>
                                </td>

                            </tr>
                            <tr>
                                <td>2</td>
                                <td>
                                    <label for="subjectAmount">实验人数</label>
                                </td>
                                <td>
                                    <input type="text" class="form-control" id="subjectAmount" name="subjectAmount" placeholder="Enter subjectAmount"/>
                                </td>

                            </tr>
                            <tr>
                                <td>3</td>
                                <td>
                                    <label for="period">实验学时</label>
                                </td>
                                <td>
                                    <input type="text" class="form-control" id="period" name="period" placeholder="Enter period:"/>
                                </td>

                            </tr>
                            <tr>
                                <td>3</td>
                                <td>
                                    <label for="begin">开始时间</label>
                                </td>
                                <td>
                                    <input type="date" class="form-control" id="begin" name="begin" placeholder="Enter begin:yyyy-mm-dd"/>
                                </td>

                            </tr>
                            <tr>
                                <td>3</td>
                                <td>
                                    <label for="deadline">截止时间</label>
                                </td>
                                <td>
                                    <input type="date" class="form-control" id="deadline" name="deadline" placeholder="Enter deadline:yyyy-mm-dd"/>
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

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>