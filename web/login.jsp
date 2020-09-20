<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
    body, div, p, h1, h2, h3, h4, h5, h6, form, input, textarea, select, button, fieldset, legend, img, ul, ol, li, dl, dt, dd, th, td, pre, blockquote, div {
        margin: 0;
        padding: 0;
        font-family: "Microsoft YaHei", "SimSun", Helvetica, Arial, sans-serif, "microsoft yahei";
    }

    a, img, select {
        vertical-align: middle;
    }

    .zn-header {
        height: 100px;
        margin-top: 2px;
        background: url('images/topbck.jpg') no-repeat;
    }

    .zn-header img {
        margin-top: 15px;
        margin-left: 110px;
    }

    .main {
        height: 560px;
        position: relative;
        overflow: hidden;
        background: url('images/bj.jpg') no-repeat;
        background-size: cover;
    }

    .loginbar {
        width: 350px;
        height: 350px;
        background-color: #fff;
        margin: 77px auto;
        position: relative;
        text-align: center;
        border: 1px solid #bdbdbd;
        opacity: 0.90;
    }

    .loginbar h1 {
        font-size: 24px;
        display: inline-block;
        color: #10478c;
        margin-top: 35px;
        margin-bottom: 10px;
    }

    .loginbar div {
        position: relative;
        margin-top: 30px;
        display: inline-block;
        padding-left: 40px;
        height: 35px;
    }

    .loginbar div span {
        position: absolute;
        height: 40px;
        left: 0px;
        top: 0px;
        width: 40px;
        line-height: 40px;
        text-align: center;
        border: 1px solid #bdbdbd;
        border-right: 0;
        background-color: #f3f3f3;
    }

    .wid254 {
        width: 254px;
        height: 40px;
        padding-left: 10px;
        position: relative;
        line-height: 40px;
        z-index: 2;
        border: 1px solid #bdbdbd;
    }

    .wid360 {
        border: 0;
        background-color: #184e99;
        width: 304px;
        height: 40px;
        margin-left: -35px;
        color: #fff;
        font-size: 16px;
        cursor: pointer;
        line-height: 40px;
    }

    .footer {
        line-height: 75px;
        text-align: center;
    }
</style>

<!doctype html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>中南大学计算机学院智能会议室管理平台</title>
    <link rel="stylesheet" href="index.css">
    <script src="js/jquery-1.11.1.min.js"></script>
</head>

<body>
<div class="zn-header">
    <img src="images/whitelogo.png">
</div>
<div class="main">
    <div class="loginbar">
        <h1>会议室管理系统</h1>
        <form method="post" action="/meeting/login">
            <c:if test="${loginError!=null}">
                <p style="color: red;font-size: 14px">${loginError}</p>
            </c:if>
            <div>
                <input id="accountname" type="text" name="accountname" class="wid254" placeholder="工号/学号"
                       required="required">
                <span><img src="images/logoin.gif"></span>
            </div>
            <div>
                <input id="password" name="password" type="password" class="wid254" placeholder="密码"
                       required="required">
                <span><img src="images/suo.gif"></span>
            </div>
            <div style="margin-top: 10px;display: inline-block;padding-left: 21px;padding-right: 21px;height: 20px;text-align: left;">
                <label for="readme" style="font-size: 11px;">
                    <input id="readme" name="readme" type="checkbox"
                           checked="checked" value="flag">我已阅读并接受<a href="rule.jsp" target="_blank">中南大学计算机学院会议场地使用实施方案</a>
                </label>
            </div>
            <div style="margin-top: 20px;">
                <input type="submit" class="wid360" value="登录 Login"/>
            </div>
        </form>
    </div>
</div>
<div class="footer">
    <p>Copyright &copy; 2019 All rights reserved.</p>
</div>
</body>

</html>


