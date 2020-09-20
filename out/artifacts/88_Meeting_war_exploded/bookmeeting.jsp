<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<script src="./My97DatePicker/WdatePicker.js"></script>

<style type="text/css">
    #divoperator input[type="button"] {
        margin: 10px 0;
    }

    #selDepartments {
        float: left;
        display: inline-block;
        width: 49%;
        margin-bottom: 1rem;
    }

    #selEmployees {
        float: right;
        display: inline-block;
        width: 49%;
        margin-bottom: 1rem;
    }

    #selSelectedEmployees {
        display: block;
        width: 100%;
        height: 225px;
    }
</style>

<div>
    <ol class="breadcrumb">
        <li><a href="#">会议预订</a></li>
        <li><a href="#">预订会议</a></li>
    </ol>
    <form id="bookmeetingform" action="javascript:void (0)" method="post">
        <h3>会议信息</h3>
        <c:if test="${loginUser.credits <= 0}">
            <div class="col-md-6">
                您的信誉积分不足，无法预订
            </div>
        </c:if>
        <c:if test="${loginUser.credits > 0}">
        <div id="ShowDiv" class="alert alert-danger" role="alert">此会议室已被借阅</div>
        <div class="row">
            <div class="col-md-6">
                <c:if test="${loginUser.role==16}">
                    <div class="form-group">
                        <label>预订人</label>
                        <div>
                            <select class="form-control" id="selDepartments" onchange="fillEmployees()"></select>
                            <select class="form-control" id="selEmployees" name="reservationistid"></select>
                        </div>
                    </div>
                </c:if>
                <div class="form-group">
                    <label>会议名称</label>
                    <input id="meetingname" name="meetingname" maxlength="20" type="text" type="text"
                           class="form-control" autocomplete="off" required="required"
                           placeholder="请输入会议名称"/>
                </div>
                <div class="form-group">
                    <label>预计参加人数</label>
                    <input id="numofattendents" name="numberofparticipants" type="text" type="text"
                           class="form-control" autocomplete="off" required="required"
                           placeholder="请输入参与人数"/>
                </div>
                <div class="form-group">
                    <label>预订日期</label>
                    <c:if test="${loginUser.role==16}">
                        <input id="orderdate" name="orderdate" type="text" type="text" class="form-control"
                               autocomplete="off" required="required"
                               placeholder="请选择会议的预订日期"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                               onblur="checkroom();"/>
                    </c:if>
                    <c:if test="${loginUser.role!=16}">
                        <input id="orderdate" name="orderdate" type="text" type="text" class="form-control"
                               autocomplete="off" required="required"
                               placeholder="请选择会议的预订日期"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd', minDate:'%y-%M-%d', maxDate:'%y-%M-{%d+13}'})"
                               onblur="checkroom();"/>
                    </c:if>
                </div>
                <div class="form-group">
                    <label>预计开始时间</label>
                    <select id="starttimeid" name="starttimeid" class="form-control" autocomplete="off"
                            onmousedown="if(this.options.length>5){this.size=6}" onchange="this.size=0"
                            onblur="this.size=0;checkroom();">
                        <c:forEach items="${starts}" var="item">
                            <option value="${item.id}">${item.starttime}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label>预计持续时间</label>
                    <select id="durationid" name="durationid" class="form-control" autocomplete="off"
                            onmousedown="if(this.options.length>5){this.size=6}" onchange="this.size=0"
                            onblur="this.size=0;checkroom();">
                        <c:forEach items="${durations}" var="item">
                            <option value="${item.durationid}">${item.duration}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>选择会议室</label>
                    <select name="roomid" class="form-control" autocomplete="off"
                            onmousedown="if(this.options.length>5){this.size=6}" onchange="this.size=0"
                            onblur="this.size=0;checkroom();">
                        <c:forEach items="${mrs}" var="mr">
                            <option value="${mr.roomid}">${mr.roomname} (可容纳${mr.capacity}人)</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>会议说明</label>
                    <textarea class="form-control" id="description" placeholder="如：需要使用投影仪" rows="5"
                              name="description"></textarea>
                </div>
            </div>
        </div>
        <input type="submit" class="btn btn-success" onclick="bookmeeting()" value="预定会议"/>
        <input type="reset" class="btn btn-danger" value="重置"/>
    </form>
    </c:if>
    <div class="clearfix"></div>
    <br><br><br><br><br>
</div>

<script type="application/javascript">

    $("#ShowDiv").hide();

    var today = getNowFormatDate();
    $("#orderdate").val(today);

    function checkroom() {
        $.ajax({
            type: "post",
            url: "/meeting/checkroom?time=" + new Date().getTime(),
            data: $('#bookmeetingform').serialize(),
            success: function (data) {
                if (data > 0) {
                    $("#ShowDiv").show();
                } else {
                    $("#ShowDiv").hide();
                }
            },
            error: function (err) {
                // alert("查询失败");
            }
        })
    }

    function bookmeeting() {
        encodeURI(encodeURI($('#meetingname').value));
        encodeURI(encodeURI($('#description').value));
        $.ajax({
            type: "post",
            url: "/meeting/dobookmeeting",
            data: $('#bookmeetingform').serialize(),
            success: function (data) {
                if (data > 0) {
                    alert("此会议室已被借用");
                } else {
                    alert("预订成功！");
                    getPage("/meeting/mybooking");
                }
            },
            error: function (err) {
                alert("订阅失败");
            }
        })
    }

    function employee(employeeid, employeename) {
        this.employeeid = employeeid;
        this.employeename = employeename;
    }

    function department(departmentid, departmentname, employees) {
        this.departmentid = departmentid;
        this.departmentname = departmentname;
        this.employees = employees;
    }

    // var data = new Array(
    //     new department(1, "技术部222222", new Array(
    //         new employee(1001, "a00"), new employee(1002, "a01"), new employee(1003, "a02"), new employee(1004, "a03"))),
    //     new department(2, "销售部", new Array(
    //         new employee(2001, "b00"), new employee(2002, "b01"), new employee(2003, "b02"), new employee(2004, "b03"))),
    //     new department(3, "市场部", new Array(
    //         new employee(3001, "c00"), new employee(3002, "c01"), new employee(3003, "c02"), new employee(3004, "c03"))),
    //     new department(4, "行政部", new Array(
    //         new employee(4001, "d00"), new employee(4002, "d01"), new employee(4003, "d02"), new employee(4004, "d03"))));

    var selDepartments;
    var selEmployees;
    var selSelectedEmployees;

    selDepartments = document.getElementById("selDepartments");
    selEmployees = document.getElementById("selEmployees");
    selSelectedEmployees = document.getElementById("selSelectedEmployees");

    //分析：这里的方法是用来给部门下拉框设置值用的
    /*for (var i = 0; i < data.length; i++) {
     var dep = document.createElement("option");
     dep.value = data[i].departmentid;
     dep.text = data[i].departmentname;
     selDepartments.appendChild(dep);
     }*/
    $.post("/meeting/getalldepjson", function (msg) {
        console.log(msg);
        for (var i = 0; i < msg.length; i++) {
            var item = msg[i];
            var dep = document.createElement("option");
            dep.value = item.departmentid;
            dep.text = item.departmentname;
            selDepartments.appendChild(dep);
            $("#selDepartments ").val(${loginUser.departmentid});
        }
        fillEmployees();
    });

    function fillEmployees() {
        //清空左边多选下拉框中的所有元素
        clearList(selEmployees);
        //获取当前选中部门的id
        var departmentid = selDepartments.options[selDepartments.selectedIndex].value;
        // 根据id去data数组中遍历找到对应的部门中的员工
        //    for (var i = 0; i < data.length; i++) {
        //        if (departmentid == data[i].departmentid) {
        //            employees = data[i].employees;
        //            break;
        //        }
        //    }

        $.post("/meeting/getempbydepid", {depid: departmentid}, function (msg) {
            for (var i = 0; i < msg.length; i++) {
                var item = msg[i];
                var emp = document.createElement("option");
                emp.value = item.employeeid;
                emp.text = item.employeename;
                selEmployees.appendChild(emp);
                $("#selEmployees ").val(${loginUser.employeeid});
            }
        });
    }

    function clearList(list) {
        while (list.childElementCount > 0) {
            list.removeChild(list.lastChild);
        }
    }

    function selectEmployees() {
        for (var i = 0; i < selEmployees.options.length; i++) {
            if (selEmployees.options[i].selected) {
                addEmployee(selEmployees.options[i]);
                selEmployees.options[i].selected = false;
            }
        }
    }

    function deSelectEmployees() {
        var elementsToRemoved = new Array();
        var options = selSelectedEmployees.options;
        for (var i = 0; i < options.length; i++) {
            if (options[i].selected) {
                elementsToRemoved.push(options[i]);
            }
        }
        for (i = 0; i < elementsToRemoved.length; i++) {
            selSelectedEmployees.removeChild(elementsToRemoved[i]);
        }
    }

    function addEmployee(optEmployee) {
        var options = selSelectedEmployees.options;
        var i = 0;
        var insertIndex = -1;
        while (i < options.length) {
            if (optEmployee.value == options[i].value) {
                return;
            } else if (optEmployee.value < options[i].value) {
                insertIndex = i;
                break;
            }
            i++;
        }
        var opt = document.createElement("option");
        opt.value = optEmployee.value;
        opt.text = optEmployee.text;
        opt.selected = true;

        if (insertIndex == -1) {
            selSelectedEmployees.appendChild(opt);
        } else {
            selSelectedEmployees.insertBefore(opt, options[insertIndex]);
        }
    }
</script>

