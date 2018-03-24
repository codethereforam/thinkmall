<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>index</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${basePath}/assets/plugins/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/assets/css/index.css">
    <link rel="icon" type="image/png" sizes="16x16" href="${basePath}/assets/images/favicon.png">
</head>
<body>
<!-- nav bar -->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <span class="navbar-brand glyphicon glyphicon-arrow-left" id="toggleSidebar"></span>
            <a class="navbar-brand" href="${basePath}/manage/index">thinkmall后台</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">个人资料</a></li>
                <li><a href="#">设置</a></li>
                <li><a href="https://github.com/codethereforam/thinkmall">源码</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">
                        系统 <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-left">
                        <li><a href="#">帮助</a></li>
                        <li><a href="https://github.com/codethereforam/thinkmall/blob/master/README.md">关于</a></li>
                        <li><a href="${basePath}/manage/logout">退出</a></li>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-right">
                <input class="form-control" placeholder="搜索...">
            </form>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <div class="nav-sidebar">
                <div class="nav-sidebar-head">
                    <ul class="nav">
                        <li>
                            <a href="#group1" data-toggle="collapse">
                                <span class="glyphicon glyphicon-minus"></span>&nbsp;&nbsp;&nbsp;用户管理
                            </a>
                        </li>
                    </ul>
                </div>
                <div id="group1" class="nav-sidebar-body collapse in">
                    <ul class="nav">
                        <li class="active"><a href="/list" data-toggle="tab">list</a></li>
                        <li><a href="/form" data-toggle="tab">form</a></li>
                    </ul>
                </div>
            </div>
            <div class="nav-sidebar">
                <div class="nav-sidebar-head">
                    <ul class="nav">
                        <li>
                            <a href="#group2" data-toggle="collapse">
                                <span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;&nbsp;类别管理
                            </a>
                        </li>
                    </ul>
                </div>
                <div id="group2" class="nav-sidebar-body collapse">
                    <ul class="nav">
                        <li><a href="/manage/category/index" target="aaa" data-toggle="tab">类别列表</a></li>
                        <li><a href="/bbb" data-toggle="tab">bbb</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- main content -->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main tab-content" id="mainContent">
            <!-- js generate -->
            <!--
            <div id="list" class="tab-pane active fade in">
                <iframe class="main-iframe" src="list.html" width="100%" frameborder="0" scrolling="auto"></iframe>
            </div>
            -->
        </div>
    </div>
</div>
<script src="${basePath}/assets/plugins/jquery-3.2.1/jquery-3.2.1.min.js"></script>
<script src="${basePath}/assets/plugins/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="${basePath}/assets/js/index.js"></script>
<script>var BASE_PATH = '${basePath}';</script>
</body>
</html>