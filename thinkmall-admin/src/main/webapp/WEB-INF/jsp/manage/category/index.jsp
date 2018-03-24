<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>类别管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${basePath}/assets/plugins/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/assets/plugins/zTree_v3-3.5.33/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="${basePath}/assets/css/manage/category/index.css">
    <link rel="stylesheet" href="${basePath}/assets/css/main.css">
    <link rel="icon" type="image/png" sizes="16x16" href="${basePath}/assets/images/favicon.png">
</head>
<body>
<div class="container">
    <div class="page-header">
        <div class="btn-group">
            <button class="btn btn-success glyphicon glyphicon-plus" data-toggle="modal"
                    data-target="#addDiv">添加类别
            </button>
        </div>
    </div>
    <%--<div class="modal fade" id="addDiv">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove"></span>
                    </button>
                    <h4 class="text-center">add user</h4>
                </div>
                <form action="#" method="post">
                    <div class="modal-body form-group">
                        <label for="usernameInput">username</label>
                        <input type="text" id="usernameInput" name="username">
                    </div>
                    <div class="modal-body form-group">
                        <label for="passwordInput">password</label>
                        <input type="password" id="passwordInput" name="password">
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary" data-dismiss="modal">
                            summit
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>--%>
    <div class="row">
        <div class="col-sm-3">
            <ul id="treeDemo" class="ztree"></ul>
        </div>
        <div class="col-sm-9">
            <h1>hello</h1>
        </div>
    </div>
</div>

<script src="${basePath}/assets/plugins/jquery-3.2.1/jquery-3.2.1.min.js"></script>
<script src="${basePath}/assets/plugins/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="${basePath}/assets/plugins/zTree_v3-3.5.33/js/jquery.ztree.core.min.js"></script>
<script src="${basePath}/assets/js/manage/category/index.js"></script>
<script>var BASE_PATH = '${basePath}';</script>
</body>
</html>
