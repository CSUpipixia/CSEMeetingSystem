<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<body>
<ol class="breadcrumb">
    <li><a href="">会议管理</a></li>
    <li><a href="">扣除积分</a></li>
</ol>
<form id="dodeducttable">
    <table class="table">
        <tr>
            <input type="hidden" name="mid" value="${m.meetingid}"/>
            <td>会议名称：</td>
            <td>${m.meetingname}</td>
        </tr>
        <tr>
            <td>扣分理由：</td>
            <td><textarea id="deductreason" rows="5" name="deductreason" class="form-control"></textarea></td>
        </tr>
        <tr>
            <td class="command" colspan="2">
                <input type="button" class="btn btn-danger" onclick="dodeductcredits()" value="确认扣分">
                <input type="button" class="btn btn-info" value="返回"
                       onclick="getPage('/meeting/meetingdetail?mid=${m.meetingid}&type=admin');"/>
            </td>
        </tr>
    </table>
</form>
</body>

<script>
    function dodeductcredits() {
        encodeURI(encodeURI($('#deductreason').value));
        $.ajax({
            type: "post",
            url: "/meeting/dodeductcredits",
            data: $('#dodeducttable').serialize(),
            success: function (data) {
                alert("已扣分");
                $("#mainPage").html(data);
            },
            error: function (err) {
                console.log(err);
                alert(err);
            }
        })
    }
</script>
