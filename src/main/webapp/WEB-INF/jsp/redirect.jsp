<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>跳转中...</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${basePath}/assets/plugins/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/assets/css/main.css">
    <link rel="icon" type="image/png" sizes="16x16" href="${basePath}/assets/images/favicon.png">
</head>
<body>
<div class="container">
    <div class="text-center">
        <h1 class="text-danger">${errorMsg}</h1>
        <h2>页面将在<span id="timeSpan" class="text-success">3</span>s后跳转到</h2>
        <h3><a href="${basePath}${redirectUrl}">${basePath}${redirectUrl}</a></h3>
    </div>
</div>

<script src="${basePath}/assets/plugins/jquery-3.2.1/jquery-3.2.1.min.js"></script>
<script src="${basePath}/assets/plugins/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script>
    var BASE_PATH = '${basePath}';
    var REDIRECT_URL = '${redirectUrl}';
</script>
<script src="${basePath}/assets/js/redirect.js"></script>
</body>
</html>