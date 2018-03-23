<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>服务器错误</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${basePath}/assets/plugins/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/assets/css/main.css">
    <link rel="icon" type="image/png" sizes="16x16" href="${basePath}/assets/images/favicon.png">
</head>
<body>
<% Exception e = (Exception) request.getAttribute("ex"); %>
<div class="container">
    <h2 class="text-danger">
        错误: <%= e.getClass().getSimpleName()%>
    </h2>
    <hr style="border-color: #a94442;"/>
    <h4 class="text-warning"><strong>错误描述：</strong></h4>
    <%= e.getMessage()%>
    <h4 class="text-warning"><strong>错误信息：</strong></h4>
    <pre>
        <% e.printStackTrace(new java.io.PrintWriter(out)); %>
    </pre>
</div>

<script src="${basePath}/assets/plugins/jquery-3.2.1/jquery-3.2.1.min.js"></script>
<script src="${basePath}/assets/plugins/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</body>
</html>