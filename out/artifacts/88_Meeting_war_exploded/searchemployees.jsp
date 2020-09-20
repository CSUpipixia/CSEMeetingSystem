<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    .table > tbody > tr > th {
        padding: 0px !important;
    }
</style>
<body>

<div>
    <ol class="breadcrumb">
        <li><a href="bookmeeting">系统管理</a></li>
        <li><a href="serachemp">搜索用户</a></li>
    </ol>
    <form id="serachemp" action="javascript:void (0)" method="post">
        <div class="row">
            <div class="col-md-1">
                <div class="form-group">
                    <label>姓名</label>
                </div>
            </div>
            <div class="col-md-3">
                <div class="form-group">
                    <input id="employeename" name="employeename" type="text" class="form-control"
                           value="${employeename}"
                           maxlength="20"/>
                </div>
            </div>
            <div class="col-md-1">
                <div class="form-group">
                    <label>账号名</label>
                </div>
            </div>
            <div class="col-md-3">
                <div class="form-group">
                    <input id="accountname" name="username" type="text" class="form-control"
                           value="${username}" maxlength="20"/>
                </div>
            </div>
        </div>
        <input type="submit" class="btn btn-success" onclick="serachemp()" value="查询"/>
        <input type="reset" class="btn btn-info" value="重置"/>
    </form>
    <div>
        <h3 style="text-align:center;color:black">查询结果</h3>
    </div>
    <table class="table" style="text-align:center">
        <tr>
            <th>姓名</th>
            <th>账号名</th>
            <th>用户类型</th>
            <th>是否锁定</th>
            <th>信誉积分</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${list}" var="emp">
            <tr id="emprow${emp.employeeid}">
                <td>${emp.employeename}</td>
                <td>${emp.username}</td>
                <td>${emp.phone}</td>
                <td>${emp.email}</td>
                <td id="credits${emp.employeeid}">${emp.credits}</td>
                <td>
                    <form id="serachempform" method="post" action="javascript:void(0)">
                        <input class="form-control" type="hidden" name="employeename"
                               value="${employeename}">
                        <input class="form-control" type="hidden" name="username" value="${username}">
                        <input class="form-control" type="hidden" name="status" value="${status}">
                        <input class="form-control" type="hidden" name="updateStatus" value="-1">
                        <input class="form-control" type="hidden" name="empid" value="${emp.employeeid}">
                        <input class="btn btn-warning btn-sm" onclick="resetcredits(${emp.employeeid})" value="重置积分"
                               type="submit">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <script>
        function resetcredits(empid) {
            $.ajax({
                type: "post",
                url: "/meeting/resetcredits?eid=" + empid,
                data: {},
                success: function (data) {
                    $("#credits" + empid).html("10");
                }
            })
        }

        function serachemp() {
            $.ajax({
                type: "post",
                url: "/meeting/serachemp",
                data: $('#serachemp').serialize(),
                success: function (data) {
                    $("#mainPage").html(data);
                }
            })
        }
    </script>

    <div style="text-align: center">共<span class="info-number">${totalCount}</span>条结果，
        分成<span class="info-number">${totalPage}</span>页显示，
        当前第<span class="info-number">${page}</span>页
        <input type="submit" class="btn btn-info btn-sm" onclick="serachempbypage(1)"
               href="javascript:void (0)" value="首页">
        <c:choose>
            <c:when test="${page>1}">
                <input type="submit" class="btn btn-info btn-sm" onclick="serachempbypage(${page-1})"
                       href="javascript:void (0)" value="上页">
            </c:when>
            <c:otherwise>
                <%--                <input type="submit" class="btn btn-info btn-sm" value="上页">--%>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${page<totalPage}">
                <input type="submit" class="btn btn-info btn-sm" onclick="serachempbypage(${page+1})"
                       href="javascript:void (0)" value="下页">
            </c:when>
            <c:otherwise>
                <%--                <input type="submit" class="btn btn-info btn-sm" value="下页">--%>
            </c:otherwise>
        </c:choose>
        <input type="submit" class="btn btn-info btn-sm" onclick="serachempbypage(${totalPage})"
               href="javascript:void (0)" value="末页">
        <%--        <form action="/meeting/serachemp" method="post" style="display: inline">--%>
        <%--            <input class="form-control" type="hidden" name="employeename" value="${employeename}">--%>
        <%--            <input class="form-control" type="hidden" name="username" value="${username}">--%>
        <%--            <input class="form-control" type="hidden" name="status" value="${status}">--%>
        跳到第
        <input class="form-control" style="width:20px;height:25px;display:initial;" type="text" autocomplete="off"
               id="pagenum" name="page" class="nav-number"/>
        页
        <input type="submit" class="btn btn-info btn-sm" onclick="gotoEmpPage()" value="跳转"/>
        <%--        </form>--%>
    </div>
    <div class="clearfix"></div>
</div>
</body>

<script>
    function serachempbypage(page) {
        $.ajax({
            type: "post",
            url: "/meeting/serachemp?page=" + page + "&status=${status}&employeename=${employeename}&username=${username}",
            data: {},
            success: function (data) {
                $("#mainPage").html(data);
            }
        })
    }

    function gotoEmpPage() {
        var page = $("#pagenum").val();
        $.ajax({
            type: "post",
            url: "/meeting/serachemp?page=" + page + "&status=${status}&employeename=${employeename}&username=${username}",
            data: {},
            success: function (data) {
                $("#mainPage").html(data);
            }
        })
    }
</script>
