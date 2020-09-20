<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">

</head>
<body>
<div id="Header">
    <div class="header" style="padding-top: 0;padding-bottom: 0;">
        <div class="container">

            <div class="header-left" style="margin: 1.4em 0 1.4em 0;">
                <div class="w3layouts-logo">
                    <h1>
                        <img src="images/bluelogo.png">
<%--                        <a href="#"><span style="font-size: 1em;">智能会议室管理系统</span></a>--%>
                    </h1>
                </div>
            </div>

            <div class="header-right" style="margin: 1.4em 0 1.4em 0;">
                <div class="agileinfo-social-grids">
                    <c:if test="${loginUser!=null}">
                        <div>
                            欢迎您,<strong>${loginUser.employeename}</strong><br>
                            您当前信誉积分为：<strong>${loginUser.credits}</strong>
                        </div>
                        <a href="/meeting/login.jsp"><span class="glyphicon glyphicon-log-out"></span>[退出登录]</a>
                    </c:if>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
</body>
