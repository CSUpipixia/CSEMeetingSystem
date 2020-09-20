<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>
<ol class="breadcrumb">
    <li><a href="">个人中心</a></li>
    <li><a href="">修改密码</a></li>
</ol>
<div>
    <div class="blog-top-grids">
        <div class="col-md-4 blog-top-right-grid">
        </div>
        <div class="col-md-4">
            <form id="changepasswordfrom" action="javascript:void(0)">
                <h3>修改密码</h3>
                <c:if test="${error!=null}">
                    <div class="alert alert-danger" role="alert">${error}</div>
                </c:if>
                <div class="form-group">
                    <label>原密码</label>
                    <input id="origin" name="origin" type="password" type="text" class="form-control"
                           placeholder="请输入您的原密码"/>
                </div>
                <div class="form-group">
                    <label>新密码</label>
                    <input id="new" name="new" type="password" type="text" class="form-control"
                           placeholder="请输入您的新密码"/>
                </div>
                <div class="form-group">
                    <label>确认密码</label>
                    <input id="confirm" name="confirm" type="password" type="text" class="form-control"
                           placeholder="请再次输入您的新密码"/>
                </div>
                <input type="submit" class="btn btn-success" onclick="changepassword()" value="确定 OK"/>
                <input type="reset" class="btn btn-info" value="清除 Reset"/>
            </form>
        </div>
        <div class="col-md-4 blog-top-left-grid"></div>
        <div class="clearfix"></div>
    </div>
</div>

<script>
    function changepassword() {
        var origin = $("#origin").val();
        var newdata = $("#new").val();
        var confirm = $("#confirm").val();
        $.ajax({
            type: "post",
            url: "/meeting/dochangepassword",
            data: {
                "origin": origin,
                "new": newdata,
                "confirm": confirm
            },
            error: function (err) {
                if (err.status == 400) {
                    alert("原密码输入错误，请重新输入");
                    $("#origin").val("");
                    $("#origin")[0].focus();
                }
                if (err.status == 401) {
                    alert("新密码不能为空");
                    $("#new")[0].focus();
                }
                if (err.status == 402) {
                    alert("两次密码输入不一致，请重新输入");
                    $("#new").val("");
                    $("#confirm").val("");
                    $("#new")[0].focus();
                }
                if (err.status == 500) {
                    alert("密码更新失败");
                    $("#changepasswordfrom")[0].reset();
                }
            },
            success: function (data) {
                alert("更新成功！");
                $("#changepasswordfrom")[0].reset();
            }
        })
    }
</script>

</body>


