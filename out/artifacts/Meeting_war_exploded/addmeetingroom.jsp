<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<style>
    .table > tbody > tr > th {
        padding: 0px !important;
    }
</style>

<body>

<div>
    <ol class="breadcrumb">
        <li><a href="#">系统管理</a></li>
        <li><a href="#">添加会议室</a></li>
    </ol>
    <form id="addmr" action="javascript:void (0)" method="post">
        <h3>会议室信息</h3>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label>门牌号</label>
                    <input id="roomnumber" name="roomnum" type="text" class="form-control"
                           placeholder="例如：201"
                           maxlength="10"
                           autocomplete="off"/>
                </div>
                <div class="form-group">
                    <label>会议室名称</label>
                    <input id="capacity" name="roomname" type="text" class="form-control"
                           placeholder="例如：第一会议室"
                           maxlength="20"
                           autocomplete="off"/>
                </div>
                <div class="form-group">
                    <label>使用范围</label><br>
                    <input type="radio" id="all" name="limits" checked="checked" value="0"/>所有用户
                    <input type="radio" id="limit" name="limits" value="1"/>行政专用
                </div>
                <div class="form-group">
                    <label>最多容纳人数</label>
                    <input id="roomcapacity" name="capacity" type="text" class="form-control"
                           placeholder="填写一个正整数"
                           maxlength="20"
                           autocomplete="off"/>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label>备注</label>
                    <textarea id="description" name="description" maxlength="200" rows="5" cols="60"
                              placeholder="200字以内的文字描述" class="form-control"
                              autocomplete="off"></textarea>
                </div>
                <div class="form-group">
                    <label>状态</label><br>
                    <input type="radio" id="open" name="status" checked="checked" value="0"/>启用
                    <input type="radio" id="close" name="status" value="1"/>禁用
                </div>
            </div>
        </div>
        <input type="submit" value="添加" onclick="addmr()" class="btn btn-success"/>
        <input type="reset" value="重置" class="btn btn-danger"/>
    </form>
    <div class="clearfix"></div>
</div>

<script>
    function addmr() {
        $.ajax({
            type: "post",
            url: "/meeting/addmr",
            data: $('#addmr').serialize(),
            success: function (data) {
                console.log(data);
                alert("添加成功！");
                $("#addmr")[0].reset();
            }
        })
    }
</script>

</body>

