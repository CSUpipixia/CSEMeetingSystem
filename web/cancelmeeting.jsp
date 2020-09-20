<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<body>
<ol class="breadcrumb">
    <li><a href="">会议预定</a></li>
    <li><a href="cancelmeeting">撤销会议预定</a></li>
</ol>
<form id="docanceltable">
    <table class="table">
        <tr>
            <input type="hidden" name="mid" value="${m.meetingid}"/>
            <td>会议名称：</td>
            <td>${m.meetingname}</td>
        </tr>
        <tr>
            <td>撤销理由：</td>
            <td><textarea id="canceledreason" rows="5" name="canceledreason" class="form-control"></textarea></td>
        </tr>
        <tr>
            <td class="command" colspan="2">
                <input type="button"  class="btn btn-danger" onclick="showDialog()" value="确认撤销">
                <input type="button" class="btn btn-info" value="返回" onclick="if (${type=="my"}) getPage('/meeting/meetingdetail?mid=${m.meetingid}&type=my'); else getPage('/meeting/meetingdetail?mid=${m.meetingid}&type=admin');"/>
            </td>
        </tr>
    </table>
</form>

<!-- 确认对话框 -->
<div id="cancelmeeting" style="border: 1px solid;width: 100%;margin: auto;height: 100%;position: fixed;left: 0px;top: 0px;background: rgb(0,0,0,0.6);overflow: auto;text-align: center;display: none;">
    <div style="background: white;width: 250px;height: 150px;margin: auto;margin-top: 50px;border-radius: 5px;">
        <div style="height: 110px;border-bottom: 1px solid #CCCCCC;">
            <!-- 框内内容 -->
            <div style="font-size: 0.9rem;padding-top: 30px;">您确定要撤销该会议吗？</div>
            <div style="font-size: 0.8rem;margin-top: 15px;">一旦撤销将不可恢复。</div>
        </div>
        <div style="height: 39px;">
            <!-- 确认按钮 -->
            <div onclick="hideDialog();docanceled();" style="float: left;width: 49%;height: 39px;border-right: 1px solid #CCCCCC;line-height: 39px;font-size: 0.8rem;">确认</div>
            <!-- 取消按钮 -->
            <div onclick="hideDialog();" style="float: right;width: 50%;height: 39px;line-height: 39px;font-size: 0.8rem">取消</div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function showDialog(){
        /* 显示 */
        // $("#cancelmeeting").style.display="block";
        document.getElementById("cancelmeeting").style.display="block";
    }
    function hideDialog(){
        /* 隐藏 */
        // $("#cancelmeeting").style.display="none";
        document.getElementById("cancelmeeting").style.display="none";
    }
</script>

</body>

<script>
    function docanceled() {
        encodeURI(encodeURI($('#canceledreason').value));
        $.ajax({
            type: "post",
            url: "/meeting/docanceled?type=${type}",
            data: $('#docanceltable').serialize(),
            success: function (data) {
                alert("撤销成功");
                $("#mainPage").html(data);
            }
        })
    }
</script>
