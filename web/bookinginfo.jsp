<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<script src="./js/jquery-2.0.0.js"></script>
<script src="./My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="jquery-powerFloat/js/mini/jquery-powerFloat-min.js"></script>

<style>
    .table > tbody > tr > th {
        padding: 0px !important;
    }
    .shadow{-moz-box-shadow:1px 1px 3px rgba(0,0,0,.4); -webkit-box-shadow:1px 1px 3px rgba(0,0,0,.4); box-shadow:1px 1px 3px rgba(0,0,0,.4);}
    .target_box{width:250px; padding:10px; border:1px solid #aaa; background-color:#fff;}
    .target_list{padding:4px; border-bottom:1px dotted #ddd; overflow:hidden; _zoom:1;}
    .target_list a{width:22px; line-height:20px; margin-right:5px; padding:1px; color:#333; font-size:12px; text-align:center; text-decoration:none; float:left;}
    .target_list a:hover{padding:0; border:1px solid #ddd; color:#cd0000;}
    .target_more{margin-top:-20px;}

    .target_fixed{height:25px; padding:1px; position:fixed; _position:absolute; top:0; right:0;}
    .custom_container{position:absolute; background-color:rgba(0, 0, 0, .5); background-color:#999\9;}
    .custom_container img{padding:0; position:relative; top:-5px; left:-5px;}
</style>

<body>
<div>
    <ol class="breadcrumb">
        <li><a href="#">会议预订</a></li>
        <li><a href="#">预订情况</a></li>
    </ol>
    <form action="javascript:void(0)" style="margin-top: 10px">
        预订日期:
        <input type="text" autocomplete="off" id="orderdate" name="orderdate" maxlength="40"
               class="form-control" required="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"
               style="width:300px;display:initial;"/>
        <input onclick="getbookinginfo()" type="submit" class="btn btn-success" value="查询"/>
    </form>

    <div style="display: block;width: 100%;overflow-x: auto;-webkit-overflow-scrolling: touch;margin-top: 10px">
        <table border="1" class="table" id="dateTable" style="font-size: smaller;text-align:center">
            <tr>
                <td id="dateTableHeader">RoomName</td>
                <c:forEach items="${starts}" var="item">
                    <td id="time${item.id}">${item.starttime}</td>
                </c:forEach>
            </tr>
        </table>
    </div>
</div>
</body>

<script>
    var today = getNowFormatDate();
    $("#orderdate").val(today);

    var tableHead = document.getElementById("dateTable").innerHTML;

    function getbookinginfo() {
        var orderdate = $("#orderdate").val();
        var cols = document.getElementById("dateTable").rows.item(0).cells.length;
        $.ajax({
            type: "get",
            url: "/meeting/getbookinginfo?orderdate=" + orderdate,
            success: function (data) {
                // console.log(cols);
                // console.log(data);
                var tempArray = new Array();
                for (var i = 0; i < data.length; i++) {
                    var exist = false;
                    for (var y in tempArray) {
                        if (y == data[i].roomid) {
                            exist = true;
                        }
                    }
                    if (!exist && tempArray[data[i].roomname] == null) {
                        tempArray[data[i].roomid] = new Array();
                        tempArray[data[i].roomid][0] = data[i].roomname;
                        for (var t = 1; t < cols - 1; t++) {
                            tempArray[data[i].roomid][t] = 0;
                        }
                    }
                    if (data[i].starttimeid != 0 || data[i].durationid != 0) {
                        for (var k = data[i].starttimeid; k < data[i].starttimeid + data[i].durationid; k++) {
                            tempArray[data[i].roomid][k] = data[i].realname;
                        }
                    }
                }
                // console.log(tempArray);
                var htmlContent = tableHead;
                for (var x in tempArray) {
                    htmlContent = htmlContent + "<tr><td id='room" + x + "'>" + tempArray[x][0] + "</td>";
                    for (var a = 1; a < tempArray[x].length; a++) {
                        if (tempArray[x][a] == 0) {
                            var htmlContent = htmlContent + "<td bgcolor=\"#B3EE3A\" class='chooseTime'>" + "闲" + "</td>";
                        } else {
                            var htmlContent = htmlContent + "<td bgcolor=\"#ABABAB\" class='tipTrigger' tip='借阅人: " + tempArray[x][a] + "'\n'借阅日期: " + orderdate + "'>忙</td>";
                        }
                    }
                    htmlContent = htmlContent + "</tr>";
                }
                document.getElementById("dateTable").innerHTML = htmlContent;
                $(".tipTrigger").powerFloat({
                    offsets: {
                        x: -10,
                        y: 0
                    },
                    showDelay: 200,

                    hoverHold: false,
                    hoverFollow: "x",
                    targetMode: "tip",
                    targetAttr: "tip",
                    position: "5-7"
                });
            }
        })
    }

    getbookinginfo();

    $(document).ready(function () {
        $("#dateTable").on('click', '.chooseTime', function () {
            var currentRow=$(this).closest("tr");
            var roomId = currentRow.find("td:eq(0)").attr('id');

            var timeIndex = $(this).parent().find("td").index($(this)[0]);
            var tableHeader = $("#dateTableHeader");
            var timeId = tableHeader.find("td:eq(0)").attr("id");

            console.log(timeIndex);
            console.log(timeId);
            console.log(roomId);
        });
    });

</script>
