<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="utf-8" %>

<body>
<div>
    <ol class="breadcrumb">
        <li><a href="javascript:void(0)">系统管理</a></li>
        <li><a href="javascript:void(0)">搜索会议</a></li>
    </ol>
    <form id="searchmeetingtable" action="javascript:void (0)">
        <div class="row" style="text-align:center;font-size: smaller">
            <div class="col-md-1">
                <div class="form-group">
                    <label>会议名称</label>
                </div>
            </div>
            <div class="col-md-3">
                <input id="meetingname" name="meetingname" type="text" class="form-control"
                       value="${meetingname}" maxlength="20"/>
            </div>
            <div class="col-md-1">
                <label>会议室名称</label>
            </div>
            <div class="col-md-3">
                <input id="roomname" name="roomname" type="text" class="form-control"
                       value="${roomname}" maxlength="20"/>
            </div>
            <div class="col-md-1">
                <label>预定者姓名</label>
            </div>
            <div class="col-md-3">
                <input id="reservername" name="reservername" type="text" class="form-control"
                       value="${reservername}" maxlength="20"/>
            </div>
        </div>
        <div class="row" style="text-align:center;font-size: smaller;margin-top: 10px;">
            <div class="col-md-1">
                <label>会议日期</label>
            </div>
            <div class="col-md-2">
                <input class="form-control" type="text" name="meetingfromdate" autocomplete="off"
                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
            </div>
            <div class="col-md-1">
                至
            </div>
            <div class="col-md-2">
                <input class="form-control" type="text" name="meetingtodate" autocomplete="off"
                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
            </div>
            <div class="col-md-1">
                <label>会议时间</label>
            </div>
            <div class="col-md-2">
                <select name="meetingfromtime" class="form-control" autocomplete="off" class="col-md-2"
                        onmousedown="if(this.options.length>5){this.size=6}" onchange="this.size=0"
                        onblur="this.size=0;">
                    <option value="0"></option>
                    <c:forEach items="${starts}" var="item">
                        <option value="${item.id}">${item.starttime}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-1">
                至
            </div>
            <div class="col-md-2">
                <select name="meetingtotime" class="form-control" autocomplete="off" class="col-md-2"
                        onmousedown="if(this.options.length>5){this.size=6}" onchange="this.size=0"
                        onblur="this.size=0;">
                    <option value="0"></option>
                    <c:forEach items="${starts}" var="item">
                        <option value="${item.id}">${item.starttime}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row" style="text-align:center;font-size: smaller;margin-top: 10px;">
            <div class="col-md-1">
                    <label>预定日期</label>
            </div>
            <div class="col-md-3">
                <input class="form-control" type="text" name="reservefromdate" autocomplete="off" style="float:left"
                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
            </div>
            <div class="col-md-1">
                至
            </div>
            <div class="col-md-3">
                <input class="form-control" type="text" name="reservetodate" autocomplete="off" style="float:left"
                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
            </div>
        </div>
        <div colspan="6" class="command" style="margin-top: 10px;">
            <input type="submit" onclick="searchmeeting()" class="btn btn-success" value="查询"/>
            <input type="reset" class="btn btn-info" value="重置"/>
        </div>
    </form>
    <div>
        <h3 style="text-align:center">查询结果</h3>
        <table class="table" style="text-align:center">
            <tr>
                <th>会议名称</th>
                <th>会议室名称</th>
                <th>会议日期</th>
                <th>会议开始时间</th>
                <th>会议时长</th>
                <th>会议预定时间</th>
                <th>预定者</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${list}" var="m">
                <tr>
                    <td>${m.meetingname}</td>
                    <td>${m.roomname}</td>
                    <td><fmt:formatDate value="${m.orderdate}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                    <td>${m.starttime}</td>
                    <td>${m.duration}</td>
                    <td><fmt:formatDate value="${m.reservationtime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                    <td>${m.empname}</td>
                    <td>
                        <div>
                            <input class="btn btn-info btn-sm" type="submit"
                                   onclick="meetingdetail2(${m.meetingid})" value="查看详情">
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div style="text-align: center">共<span class="info-number">${totalCount}</span>条结果，
            分成<span class="info-number">${totalPage}</span>页显示，
            当前第<span class="info-number">${page}</span>页
            <input type="submit" class="btn btn-info btn-sm" href="javascript:void (0)"
                   onclick="searchmeetingbypage(1)" value="首页">
            <c:choose>
                <c:when test="${page>1}">
                    <input type="submit" class="btn btn-info btn-sm" href="javascript:void (0)"
                           onclick="searchmeetingbypage(${page-1})" value="上页">
                </c:when>
                <c:otherwise>
                    <%--<a type="button" class="btn btn-info btn-xs">上页</a>--%>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${page<totalPage}">
                    <input type="submit" class="btn btn-info btn-sm" href="javascript:void (0)"
                           onclick="searchmeetingbypage(${page+1})" value="下页">
                </c:when>
                <c:otherwise>
                    <%--<input type="submit" class="btn btn-info btn-xs">下页</a>--%>
                </c:otherwise>
            </c:choose>
            <input type="submit" class="btn btn-info btn-sm" href="javascript:void (0)"
                   onclick="searchmeetingbypage(${totalPage})" value="末页">

            跳到第
            <input class="form-control" style="width:20px;height:25px;display:initial;" type="text" autocomplete="off"
                   id="meetingpagenum" name="page" class="nav-number"/>
            页
            <input type="submit" onclick="gotoMeetingPage()" class="btn btn-info btn-sm" value="跳转"/>
        </div>
    </div>
    <div class="clearfix"></div>
</div>
</body>

<script>
    function searchmeeting() {
        $.ajax({
            type: "post",
            url: "/meeting/searchmeeting",
            data: $('#searchmeetingtable').serialize(),
            success: function (data) {
                $("#mainPage").html(data);
            }
        })
    }

    function searchmeetingbypage(page) {
        $.ajax({
            type: "post",
            data: {},
            url: "/meeting/searchmeeting?page=" + page + "&meetingname=${meetingname}&roomname=${roomname}&reservername=${reservername}&reservefromdate=${reservefromdate}&reservetodate=${reservetodate}&meetingfromdate=${meetingfromdate}&meetingtodate=${meetingtodate}",
            success: function (data) {
                $("#mainPage").html(data);
            }
        })
    }

    function gotoMeetingPage() {
        var page = $("#meetingpagenum").val();
        $.ajax({
            type: "post",
            url: "/meeting/searchmeeting?page=" + page + "&meetingname=${meetingname}&roomname=${roomname}&reservername=${reservername}&reservefromdate=${reservefromdate}&reservetodate=${reservetodate}&meetingfromdate=${meetingfromdate}&meetingtodate=${meetingtodate}",
            data: {},
            success: function (data) {
                $("#mainPage").html(data);
            }
        })
    }

    function meetingdetail2(meetingid) {
        $.ajax({
            type: "post",
            url: "/meeting/meetingdetail?mid=" + meetingid + "&type=admin",
            data: {},
            success: function (data) {
                $("#mainPage").html(data);
            }
        })
    }
</script>

