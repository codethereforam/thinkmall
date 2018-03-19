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
    <form class="form-signin" action="${basePath}/adminLogin" method="post">
        <h2 class="form-signin-heading">thinkmall后台</h2>
        <div class="form-group input-lg">
            <span class="glyphicon glyphicon-user"></span>
            <label for="inputAccountName"></label>
            <input id="inputAccountName" name="username" placeholder="Account name: not blank" required autofocus>
        </div>
        <div class="form-group input-lg">
            <span class="glyphicon glyphicon-lock"></span>
            <label for="inputPassword"></label>
            <input type="password" name="password" id="inputPassword" placeholder="Password: not blank" required>
        </div>
        <div class="checkbox input-lg">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="btn btn-primary btn-lg btn-block" type="submit">Sign in</button>
    </form>
</div>

<script src="${basePath}/assets/plugins/jquery-3.2.1/jquery-3.2.1.min.js"></script>
<script src="${basePath}/assets/plugins/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</body>
</html>