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
    <div class="page-header bg-success">
        <div class="btn-group">
            <button type="button" class="btn btn-default glyphicon glyphicon-th-list dropdown-toggle"
                    data-toggle="dropdown" id="filterByDeletedBtn">
                <span class="text">启用类别</span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu" id="filterByDeletedMenu">
                <li><a href="javascript:void(0)" class="all-category">全部类别</a></li>
                <li><a href="javascript:void(0)" class="enabled-category">启用类别</a></li>
            </ul>
            <button class="btn btn-success glyphicon glyphicon-plus" data-toggle="modal"
                    data-target="#addDiv">添加类别
            </button>
        </div>
        <span class="text-primary h2 col-sm-offset-2"><em>类别详情</em></span>
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
            <ul id="categoryTreeBlock" class="ztree"></ul>
        </div>
        <div class="col-sm-9">
            <form id="updateForm" method="post" action="#" class="form-horizontal">
                <fieldset>
                    <div class="form-group">
                        <label for="inputName" class="col-sm-2 control-label">类别名称</label>
                        <div class="col-sm-3">
                            <input type="text" id="inputName" name="name" class="form-control" placeholder="非空"
                                   autofocus required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputParentName" class="col-sm-2 control-label">父类别名称</label>
                        <div class="col-sm-3">
                            <input type="text" id="inputParentName" name="parentName"
                                   class="form-control" required/>
                            <input type="hidden" id="inputParentId" name="parentId"/>
                        </div>
                        <button class="btn btn-primary" type="button" id="parentSelectBtn">选择父类别</button>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否启用</label>
                        <div class="radio col-sm-3">
                            <label class="radio-inline">
                                <input type="radio" name="deleted" value="false" checked>启用
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="deleted" value="true">禁用
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputDescription" class="col-sm-2 control-label">类别描述</label>
                        <div class="col-sm-4">
                        <textarea id="inputDescription" name="description" class="form-control" cols="30"
                                  rows="5"></textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2 col-sm-offset-2">
                            <button type="submit" class="btn btn-success btn-block" id="updateBtn">修改</button>
                        </div>
                        <div class="col-sm-2">
                            <button type="reset" class="btn btn-danger btn-block">重置</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>
<%-- category select modal--%>
<div class="modal fade" id="parentSelectModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
                <h4 class="modal-title">选择父类别</h4>
            </div>
            <div class="modal-body">
                <ul id="categorySelectTreeBlock" class="ztree">

                </ul>
            </div>
        </div>
    </div>
</div>
<%-- parent can not select modal--%>
<div class="modal fade" id="parentCannotSelectModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-warning">
                <button type="button" class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
                <h4 class="modal-title">信息</h4>
            </div>
            <div class="modal-body text-danger">
                <h5>无法选择三级类别，请选择父类别</h5>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">知道了</button>
            </div>
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
