<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<body>

<div>
    <ol class="breadcrumb">
        <li><a href="#">会议预订</a></li>
        <li><a href="#">查看会议室</a></li>
    </ol>
    <table class="table" style="text-align:center">
        <tr>
            <th>门牌编号</th>
            <th>会议室名称</th>
            <th>容纳人数</th>
            <th>使用范围</th>
            <th>当前状态</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${list}" var="mr">
            <tr>
                <td>${mr.roomnum}</td>
                <td>${mr.roomname}</td>

                <td>${mr.capacity}</td>
                <td>
                    <c:choose>
                        <c:when test="${mr.limits==0}">所有用户</c:when>
                        <c:when test="${mr.limits==1}">行政专用</c:when>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${mr.status==0}">启用</c:when>
                        <c:when test="${mr.status==1}">停用</c:when>
                    </c:choose>
                </td>
                <td>
                    <a class="btn btn-success btn-sm" onclick="roomdetails(${mr.roomid})"
                       href="javascript:void (0)">查看</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="clearfix"></div>
</div>

<script>
    function roomdetails(roomid) {
        $.ajax({
            type: "get",
            url: "/meeting/roomdetails?roomid=" + roomid,
            data: {},
            success: function (data) {
                console.log(data);
                $("#mainPage").html(data);
            }
        })
    }
</script>

</body>

