<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>thinkmall后台登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${basePath}/assets/plugins/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/assets/css/login.css">
    <link rel="icon" type="image/png" sizes="16x16" href="${basePath}/assets/images/favicon.png">
</head>
<body>
<div class="container">
    <form class="form-signin form-horizontal" action="${basePath}/manage/login" method="post">
        <h2 class="form-signin-heading">thinkmall后台</h2>
        <div class="form-group input-lg">
            <span class="glyphicon glyphicon-user"></span>
            <label for="inputAccountName"></label>
            <input id="inputAccountName" name="username" placeholder="用户名： 非空" required autofocus>
        </div>
        <div class="form-group input-lg">
            <span class="glyphicon glyphicon-lock"></span>
            <label for="inputPassword"></label>
            <input type="password" name="password" id="inputPassword" placeholder="密码：非空" required>
        </div>
        <div class="form-group input-lg">
            <span class="glyphicon glyphicon-check"></span>
            <label for="inputCaptcha"></label>
            <input typeof="text" name="captcha" id="inputCaptcha" style="width: 40%;"/>
            <img src="" alt="验证码" id="captchaImage">
        </div>
        <button class="btn btn-primary btn-lg btn-block" type="submit">登入</button>
    </form>
</div>

<script src="${basePath}/assets/plugins/jquery-3.2.1/jquery-3.2.1.min.js"></script>
<script src="${basePath}/assets/plugins/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</body>
</html>