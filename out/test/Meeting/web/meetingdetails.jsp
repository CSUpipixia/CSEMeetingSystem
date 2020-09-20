<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<body>
<ol class="breadcrumb">
    <li><a href="">会议预定</a></li>
    <li><a href="meetingdetail">会议详情</a></li>
</ol>
<form>
    <h3>会议信息</h3>
    <table class="table">
        <tr>
            <td><b>会议名称：</b></td>
            <td>${mt.meetingname}</td>
        </tr>
        <tr>
            <td><b>预计参加人数：</b></td>
            <td>${mt.numberofparticipants}</td>
        </tr>
        <tr>
            <td><b>会议日期：</b></td>
            <td>${mt.orderdate}</td>
        </tr>
        <tr>
            <td><b>预计开始时间：</b></td>
            <td>${mt.starttime}</td>
        </tr>
        <tr>
            <td><b>会议时长：</b></td>
            <td>${mt.duration}</td>
        </tr>
        <tr>
            <td><b>会议说明：</b></td>
            <td>
                <textarea id="description" rows="5" readonly class="form-control">${mt.description}</textarea>
            </td>
        </tr>
        <c:choose>
            <c:when test="${mt.status==1}">
                <tr>
                    <td><b>状态：</b></td>
                    <td>申请人已撤销</td>
                </tr>
                <tr>
                    <td><b>撤销时间：</b></td>
                    <td><fmt:formatDate value="${mt.canceledtime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                </tr>
                <tr>
                    <td><b>撤销原因：</b></td>
                    <td>
                        <textarea id="canceledreason1" rows="5" readonly
                                  class="form-control">${mt.canceledreason}</textarea>
                    </td>
                </tr>
            </c:when>
            <c:when test="${mt.status==2}">
                <tr>
                    <td><b>状态：</b></td>
                    <td style="color: red">被管理员撤销</td>
                </tr>
                <tr>
                    <td><b>撤销时间：</b></td>
                    <td><fmt:formatDate value="${mt.canceledtime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                </tr>
                <tr>
                    <td><b>撤销原因：</b></td>
                    <td>
                        <textarea id="canceledreason2" rows="5" readonly
                                  class="form-control">${mt.canceledreason}</textarea>
                    </td>
                </tr>
            </c:when>
            <c:otherwise>

            </c:otherwise>
        </c:choose>
        <c:if test="${mt.deductcredits!=0}">
            <tr>
                <td style="color: red"><b>已扣分：</b></td>
                <td style="color: red">扣除${mt.deductcredits}分信誉分</td>
            </tr>
            <tr>
                <td><b>扣分原因：</b></td>
                <td>
                    <textarea id="canceledreason3" rows="5" readonly
                              class="form-control">${mt.deductreason}</textarea>
                </td>
            </tr>
        </c:if>
    </table>
    <tr>
        <td class="command" colspan="2">
            <c:if test="${mt.status == 0}">
                <input type="button" class="btn btn-danger" value="撤销会议"
                       onclick="cancelmeeting(${mt.meetingid})"/>
            </c:if>
            <c:if test="${loginUser.role==16 && mt.deductcredits <= 0 && type=='admin'}">
                <input type="button" class="btn btn-warning" value="扣除积分"
                       onclick="deductcredits(${mt.meetingid})"/>
            </c:if>
            <input type="button" class="btn btn-info" value="返回"
                   onclick="if (${type=="my"}) getPage('/meeting/mybooking'); else getPage('/meeting/searchmeeting');"/>
        </td>
    </tr>
</form>
</body>

<script>
    function deductcredits(meetingid) {
        $.ajax({
            type: "post",
            url: "/meeting/deductcredits?mid=" + meetingid,
            data: {},
            success: function (data) {
                $("#mainPage").html(data);
            }
        })
    }

    function cancelmeeting(meetingid) {
        $.ajax({
            type: "post",
            url: "/meeting/cancelmeeting?mid=" + meetingid + "&type=${type}",
            data: {},
            success: function (data) {
                $("#mainPage").html(data);
            }
        })
    }
</script>