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
                    data-target="#addModal">添加类别
            </button>
        </div>
        <span class="text-primary h2 col-sm-offset-2"><em>类别详情</em></span>
        <div class="btn-group pull-right">
            <button class="btn btn-warning glyphicon glyphicon-refresh" id="refreshBtn" title="刷新"></button>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-3">
            <ul id="categoryTreeBlock" class="ztree"></ul>
        </div>
        <div class="col-sm-9">
            <form id="updateForm" class="form-horizontal">
                <fieldset>
                    <input type="hidden" id="inputCategoryId" name="categoryId"/>
                    <input type="hidden" id="inputParentId" name="parentId"/>
                    <input type="hidden" id="inputLevel" name="level"/>
                    <input type="hidden" id="inputCreateTime" name="createTime"/>
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
                            <button type="button" class="btn btn-success btn-block" id="updateBtn">修改</button>
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
<%-- parent category select modal--%>
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
<%-- add category modal --%>
<div class="modal fade" id="addModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
                <h4 class="modal-title">添加类别</h4>
            </div>
            <div class="modal-body">
                <form id="addForm" class="form-horizontal">
                    <input type="hidden" id="inputAddParentId" name="parentId"/>
                    <input type="hidden" id="inputAddLevel" name="level"/>
                    <div class="form-group">
                        <label for="inputAddName" class="col-sm-2 control-label">类别名称</label>
                        <div class="col-sm-3">
                            <input id="inputAddName" type="text" name="name" class="form-control" placeholder="1-20个字符"
                                   title="请输入1-20个字符，仅支持中英文、数字和下划线"
                                   autofocus required pattern="[0-9a-zA-Z\u4e00-\u9fa5_]{1,20}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputAddParentName" class="col-sm-2 control-label">父类别名称</label>
                        <div class="col-sm-3">
                            <input id="inputAddParentName" type="text" name="parentName"
                                   class="form-control" required/>
                        </div>
                        <button class="btn btn-primary" type="button" id="parentSelectAddBtn">选择父类别</button>
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
                        <label for="inputAddDescription" class="col-sm-2 control-label">类别描述</label>
                        <div class="col-sm-4">
                            <textarea id="inputAddDescription" name="description" class="form-control" cols="30"
                                      rows="5" maxlength="255" placeholder="请输入255个字符以内"
                                      title="类别描述输入字符过多，超过255个字符"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="addBtn">添加</button>
            </div>
        </div>
    </div>
</div>
<%-- parent category select add modal--%>
<div class="modal fade" id="parentSelectAddModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
                <h4 class="modal-title">选择父类别</h4>
            </div>
            <div class="modal-body">
                <ul id="categorySelectTreeAddBlock" class="ztree">

                </ul>
            </div>
        </div>
    </div>
</div>
<%-- hint modal--%>
<div class="modal fade" id="hintModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
                <h4 class="modal-title">信息</h4>
            </div>
            <div class="modal-body">
                <h5><span id="hintContent"></span></h5>
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
