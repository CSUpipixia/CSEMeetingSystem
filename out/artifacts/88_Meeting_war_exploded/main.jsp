<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>会议管理系统</title>

</head>
<head>
    <jsp:include page="jss.jsp"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Collapsible sidebar using Bootstrap 4</title>
    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <!-- Our Custom CSS -->
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="jquery-powerFloat/css/powerFloat.css" type="text/css"/>
    <!-- Font Awesome JS -->
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"
            integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ"
            crossorigin="anonymous"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"
            integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY"
            crossorigin="anonymous"></script>

    <script src="js/jquery-3.4.1.min.js"></script>

    <!-- Popper.JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
            integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
            crossorigin="anonymous"></script>
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
            integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
            crossorigin="anonymous"></script>
</head>

<body>
<div class="wrapper">
    <!-- Sidebar  -->
    <nav id="sidebar">
        <div class="sidebar-header">
            <h3>
                <span class="glyphicon glyphicon-list-alt"></span>
                &nbsp&nbsp管理菜单
            </h3>
        </div>
        <ul class="list-unstyled components">
            <p>会议室管理系统</p>
            <li>
                <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                    <span class="glyphicon glyphicon-user"></span>&nbsp&nbsp个人中心</a>
                <ul class="collapse list-unstyled" id="homeSubmenu">
                    <li>
                        <a onclick="getPage('/meeting/mybooking')" href="javascript:void(0)"><span
                                class="glyphicon glyphicon-pushpin"></span>&nbsp&nbsp我的预定</a>
                    </li>
                    <li>
                        <a onclick="getPage('/meeting/changepassword')" href="javascript:void(0)"><span
                                class="glyphicon glyphicon-wrench"></span>&nbsp&nbsp修改密码</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#bookSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                    <span class="glyphicon glyphicon-book"></span>&nbsp&nbsp会议预订</a>
                <ul class="collapse list-unstyled" id="bookSubmenu">
                    <li>
                        <a onclick="getPage('/meeting/bookinginfo')" href="javascript:void(0)"><span
                                class="glyphicon glyphicon-pushpin"></span>&nbsp&nbsp预订情况</a>
                    </li>
                    <li>
                        <a onclick="getPage('/meeting/bookmeeting')" href="javascript:void(0)"><span
                                class="glyphicon glyphicon-book"></span>&nbsp&nbsp预订会议</a>
                    </li>
                    <li>
                        <a onclick="getPage('/meeting/getallmr')" href="javascript:void(0)"><span
                                class="glyphicon glyphicon-record"></span>&nbsp&nbsp查看会议室</a>
                    </li>
                </ul>
            </li>
            <c:if test="${loginUser.role==16}">
                <li>
                    <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                        <span class="glyphicon glyphicon-th-large"></span>&nbsp&nbsp系统管理</a>
                    <ul class="collapse list-unstyled" id="pageSubmenu">
                        <li>
                            <a onclick="getPage('/meeting/toaddmr')" href="javascript:void(0)"><span
                                    class="glyphicon glyphicon-plus"></span>&nbsp&nbsp添加会议室</a>
                        </li>
                        <li>
                            <a onclick="getPage('/meeting/serachemp')" href="javascript:void(0)"><span
                                    class="glyphicon glyphicon-search"></span>&nbsp&nbsp用户管理</a>
                        </li>
                        <li>
                            <a onclick="getPage('/meeting/searchmeeting')" href="javascript:void(0)"><span
                                    class="glyphicon glyphicon-search"></span>&nbsp&nbsp会议管理</a>
                        </li>
                    </ul>
                </li>
            </c:if>
            <li>
                <a onclick="getPage('/meeting/rule')" href="javascript:void(0)"><span
                        class="glyphicon glyphicon-fire"></span>&nbsp&nbsp会议室实施细则</a>
            </li>
        </ul>
    </nav>

    <!-- Page Content  -->
    <div id="content" style="padding-top: 0">
        <nav class="navbar navbar-expand-lg navbar-light bg-light" style="margin-bottom: 10px">
            <div class="container-fluid">
                <button type="button" id="sidebarCollapse" class="btn btn-info">
                    <i class="fas fa-align-left"></i>
                    <span></span>
                </button>
                <button class="btn btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fas fa-align-justify"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <jsp:include page="top.jsp"/>
                </div>
            </div>
        </nav>

        <div id="mainPage"></div>

    </div>
</div>


<script type="text/javascript">

    $(document).ready(function () {
        $('#sidebarCollapse').on('click', function () {
            $('#sidebar').toggleClass('active');
        });
    });

    function getPage(url) {
        $.ajax({
            type: "get",
            data: "",
            url: url,
            cache: false,
            success: function (data) {
                $("#mainPage").html(data);
            }
        })
    }

    // 获取当前日期
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        return currentdate;
    }


    // 初始页面
    getPage('/meeting/bookinginfo');

</script>

<jsp:include page="footer.jsp"/>


</body>
</html>


