<%--
  Created by IntelliJ IDEA.
  User: Mango
  Date: 2017/7/1
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html ang="zh-CN">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>实验课管理系统</title>
    <!-- BOOTSTRAP STYLES-->
    <link href="/assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="/assets/css/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLES-->
    <link href="/assets/css/custom.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
</head>
<body>
<div id="wrapper">
    <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">Binary admin</a>
        </div>
        <div style="color: white;
padding: 15px 50px 5px 50px;
float: right;
font-size: 16px;"> Last access : 30 May 2014 &nbsp; <a href="login.jsp" class="btn btn-danger square-btn-adjust">Logout</a> </div>
    </nav>
    <!-- /. NAV TOP  -->
    <nav class="navbar-default navbar-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="main-menu">
                <li class="text-center">
                    <img src="/assets/img/find_user.png" class="user-image img-responsive"/>
                </li>
                <li>
                    <a href="#"><i class="fa fa-sitemap fa-3x"></i> 实验课程管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="tGetAllExperience">查看实验课程</a>
                        </li>
                        <li>
                            <a href="tFindExperienceAdd">添加实验课程</a>
                        </li>
                        <li>
                            <a href="tFindExperienceUpdate">修改实验课程</a>
                        </li>
                        <li>
                            <a href="tFindExperienceDelete">删除实验课程</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-sitemap fa-3x"></i> 实验题目管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="tGetAllSubject">查看实验题目</a>
                        </li>
                        <li>
                            <a href="tFindSubjectAdd">添加实验题目</a>
                        </li>
                        <li>
                            <a href="tFindSubjectUpdate">修改实验题目</a>
                        </li>
                        <li>
                            <a href="tFindSubjectDelete">删除实验题目</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-sitemap fa-3x"></i> 学生管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="tAddStudent">添加学生信息</a>
                        </li>
                        <li>
                            <a href="tFindStudentUpdate">更新学生信息</a>
                        </li>
                        <li>
                            <a href="tFindStudentDelete">删除学生信息</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-sitemap fa-3x"></i> 学生成绩管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="tFindStudentScore">按学生统计成绩</a>
                        </li>
                        <li>
                            <a href="tFindExperienceScore">按实验统计成绩</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
    <!-- /. NAV SIDE  -->
    <div id="page-wrapper" >
        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <h5>Welcome ${userEntity.userName} , Love to see you back. </h5>
                </div>
            </div>
            <!-- 横向导航开始 -->
            <hr />
            <div class="row">
                <div class="col-md-3 col-sm-6 col-xs-6">
                    <div class="panel panel-back noti-box">
            <span class="icon-box bg-color-red set-icon">
                <i class="fa fa-envelope-o"></i>
            </span>
                        <div class="text-box" >
                            <p class="main-text">${eAmount}</p>
                            <p class="text-muted">已发布实验总数</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-6">
                    <div class="panel panel-back noti-box">
            <span class="icon-box bg-color-green set-icon">
                <i class="fa fa-bars"></i>
            </span>
                        <div class="text-box" >
                            <p class="main-text">${sAmount}</p>
                            <p class="text-muted">已发布题目总数</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-6">
                    <div class="panel panel-back noti-box">
            <span class="icon-box bg-color-red set-icon">
                <i class="fa fa-envelope-o"></i>
            </span>
                        <div class="text-box" >
                            <p class="main-text">${eAmount}</p>
                            <p class="text-muted">已发布实验总数</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-6">
                    <div class="panel panel-back noti-box">
            <span class="icon-box bg-color-green set-icon">
                <i class="fa fa-bars"></i>
            </span>
                        <div class="text-box" >
                            <p class="main-text">${sAmount}</p>
                            <p class="text-muted">已发布题目总数</p>
                        </div>
                    </div>
                </div>
            </div>
            <hr/>
            <!--横向导航结束-->
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            修改学生信息
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>学生学号</th>
                                        <th>学生姓名</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${studentList}" var="student" varStatus="status">
                                        <tr class="gradeA">
                                            <td>${status.index + 1}</td>
                                            <td>
                                                    ${student.id}
                                            </td>
                                            <td>
                                                <a href="tStudentUpdate${student.id}">${student.userName}</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /. PAGE INNER  -->
            </div>
            <!-- /. PAGE WRAPPER  -->
        </div>
    </div>

    <!-- /. WRAPPER  -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    <script src="assets/js/jquery-1.10.2.js"></script>
    <!-- BOOTSTRAP SCRIPTS -->
    <script src="assets/js/bootstrap.min.js"></script>
    <!-- METISMENU SCRIPTS -->
    <script src="assets/js/jquery.metisMenu.js"></script>
    <!-- DATA TABLE SCRIPTS -->
    <script src="assets/js/dataTables/jquery.dataTables.js"></script>
    <script src="assets/js/dataTables/dataTables.bootstrap.js"></script>
    <script>
        $(document).ready(function () {
            $('#dataTables-example').dataTable();
        });
    </script>
    <!-- CUSTOM SCRIPTS -->
    <script src="assets/js/custom.js"></script>
</div>
</body>
</html>
