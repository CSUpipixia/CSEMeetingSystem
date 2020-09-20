<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <!-- container -->
    <ol class="breadcrumb">
        <li><a href="#">系统管理</a></li>
        <li><a href="#">修改会议室</a></li>
    </ol>
    <form id="changemr" action="javascript:void (0)">
        <h3>会议室信息</h3>
        <table class="table">
            <tr>
                <td>门牌号:</td>
                <td>
                    <input name="roomid" value="${mr.roomid}" type="hidden" class="form-control">
                    <input id="roomnumber" name="roomnum" type="text" value="${mr.roomnum}"
                           maxlength="10" class="form-control"/>
                </td>
            </tr>
            <tr>
                <td>会议室名称:</td>
                <td>
                    <input id="capacity" type="text" name="roomname" value="${mr.roomname}"
                           maxlength="20" class="form-control"/>
                </td>
            </tr>
            <tr>
                <td>最多容纳人数：</td>
                <td>
                    <input id="roomcapacity" type="text" name="capacity" value="${mr.capacity}" class="form-control"/>
                </td>
            </tr>
            <tr>
                <td>使用范围：</td>
                <td>
                    <c:choose>
                        <c:when test="${mr.limits==0}">
                            <input type="radio" id="status" name="limits" checked="checked" value="0"/>
                            <label for="status">所有用户</label>
                            <input type="radio" id="status" name="limits" value="1"/>
                            <label for="status" value="0">行政专用</label>
                        </c:when>
                        <c:otherwise>
                            <input type="radio" id="status" name="limits" value="0"/>
                            <label for="status">所有用户</label>
                            <input type="radio" id="status" name="limits" checked="checked" value="1"/>
                            <label for="status" value="0">行政专用</label>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>当前状态：</td>
                <td>
                    <c:choose>
                        <c:when test="${mr.status==0}">
                            <input type="radio" id="status" name="status" checked="checked" value="0"/>
                            <label for="status">启用</label>
                            <input type="radio" id="status" name="status" value="1"/>
                            <label for="status" value="0">停用</label>
                        </c:when>
                        <c:otherwise>
                            <input type="radio" id="status" name="status" value="0"/>
                            <label for="status">启用</label>
                            <input type="radio" id="status" name="status" checked="checked" value="1"/>
                            <label for="status" value="0">停用</label>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>备注：</td>
                <td>
                    <textarea id="description" maxlength="200" rows="5" name="description"
                              cols="60" class="form-control">${mr.description}</textarea>
                </td>
            </tr>
            <tr>
                <td colspan="2" class="command">
                    <c:if test="${loginUser.role==16}">
                        <input type="submit" onclick="changemr()" value="确认修改" class="btn btn-success"/>
                    </c:if>
                    <input type="button" class="btn btn-info" value="返回"
                           onclick="getPage('/meeting/getallmr')"/>
                </td>
            </tr>
        </table>
    </form>
</div>

<script>
    function changemr() {
        $.ajax({
            type: "post",
            url: "/meeting/addmr",
            data: $('#changemr').serialize(),
            success: function (data) {
                console.log(data);
                alert("修改成功！");
            }
        })
    }

</script>

