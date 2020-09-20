<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<body>

<div>
    <ol class="breadcrumb">
        <li><a href="#">个人中心</a></li>
        <li><a href="#">我的预定</a></li>
    </ol>
    <table id="mybookingtable" class="table" style="text-align:center">
        <tr>
            <th>会议名称</th>
            <th>会议室名称</th>
            <th>会议日期</th>
            <th>会议开始时间</th>
            <th>会议时长</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${mrs}" var="mr">
            <tr>
                <td>${mr.meetingname}</td>
                <td>${mr.roomname}</td>
                <td><fmt:formatDate value="${mr.orderdate}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                <td>${mr.starttime}</td>
                <td><fmt:formatDate value="${mr.reservationtime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                <c:choose>
                    <c:when test="${mr.status==0}">
                        <td>已预订</td>
                    </c:when>
                    <c:when test="${mr.status==1}">
                        <td>已撤销</td>
                    </c:when>
                    <c:when test="${mr.status==2}">
                        <td style="color: red">被撤销</td>
                    </c:when>
                    <c:otherwise>
                        <td>未知</td>
                    </c:otherwise>
                </c:choose>
                <td>
                    <input class="btn btn-warning btn-sm" type="submit" onclick="meetingdetail(${mr.meetingid})" value="查看详情">
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="clearfix"></div>
</body>

<script>
    function meetingdetail(meetingid) {
        $.ajax({
            type: "post",
            url: "/meeting/meetingdetail?mid=" + meetingid + "&type=my",
            data: {},
            success: function (data) {
                $("#mainPage").html(data);
            }
        })
    }
</script>

