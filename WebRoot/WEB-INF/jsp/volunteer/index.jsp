<%--
  Created by IntelliJ IDEA.
  User: liuyang
  Date: 2022/5/7
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; character=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>首页</title>
    <script src="${ctx}/resource/jquery/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/bootstrap-3.4.1/css/bootstrap.min.css">
    <script src="${ctx}/resource/bootstrap-3.4.1/js/bootstrap.min.js"></script>
</head>

<body>

<!-- 模态框start( Modal )  -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">修改个人密码</h4>
            </div>
            <form action="${ctx}/volunteerController/updatePassword">
                <div class="modal-body">
                        <p class="row"><span class="col-md-2">新密码：</span><input type="text" id="newPassword" name="newPassword"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary" onclick="return confirm('您确定修改吗？\n修改完成后将跳转至登陆界面')">提交更改</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">修改个人信息</h4>
            </div>
            <form action="${ctx}/volunteerController/updateInfo">
                <div class="modal-body">
                    <p class="row"><span class="col-md-2">联系电话：</span><input type="text" id="newPhone" name="newPhone" value="${volunteer.phone}"></p>
                </div>
                <div class="modal-body">
                    <p class="row"><span class="col-md-2">年龄：</span><input type="text" id="newAge" name="newAge" value="${volunteer.age}"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary" onclick="return confirm('您确定修改吗？\n修改完成后将跳转至登陆界面')">提交更改</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 模态框end( Modal )  -->
<div class="container" style="margin-top: 100px">
    <div class="row">
        <div class="col-md-12 panel panel-primary" style="padding: 0">
            <div class=" panel-heading" style="display: flex;justify-content: space-between">
                <h3 class="panel-title col-md-2" style="align-self: center">志愿者后台</h3>
                <div class="col-md-10" style="display: flex;justify-content: space-around">
                    <span>姓名：${volunteer.realname}</span><span>年龄：${volunteer.age}岁</span><span>联系电话:${volunteer.phone}</span>
                    <button class="btn btn-info" data-toggle='modal' data-target='#myModal1'>修改密码</button>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2">
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a href="student_info.php">志愿活动列表</a></li>
                <li><a data-toggle='modal' data-target='#myModal2'>修改个人信息</a></li>
                <li><a onclick="exit()">退出</a></li>
            </ul>
        </div>
        <div class="col-md-10 panel panel-primary" style="padding: 0">
            <div class="panel-heading" style="display: flex;justify-content: space-between">
                <h3 class="panel-title" style="align-self: center">志愿活动列表</h3>
            </div>
            <div class="panel-body">
                <table id="myTable" class="table table-striped">
                    <thead>
                    <tr>
                        <th>活动序号</th>
                        <th>活动名称</th>
                        <th>活动内容</th>
                        <th>截止时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="adminTbody">
                        <c:forEach items="${allActivities}" var="activity">
                            <tr>
                                <th>${activity.id}</th>
                                <th>${activity.activity}</th>
                                <th>${activity.manage}</th>
                                <th>${activity.date}</th>
                                <th>
                                    <c:choose>
                                        <c:when test="${activity.sState == 1 }">
                                            <button class='btn btn-danger disabled'>已结束
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <c:choose>
                                                <c:when test="${activity.vState == 1 }">
                                                    <button
                                                            class='btn btn-danger' data-toggle='modal'
                                                            data-target='#myModal3'
                                                            onclick="exitA(${volunteer.id},${activity.id})">取消报名
                                                    </button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button class='btn btn-success'
                                                            onclick="joinA(${volunteer.id},${activity.id})">报名
                                                    </button>
                                                </c:otherwise>
                                            </c:choose>
                                    </c:otherwise>
                                    </c:choose>

                                </th>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <!--                分页start-->
                <div id="barcon" class="" style="display: flex;align-items: center;justify-content: space-between">
                    <div id="barcon1" class="barcon1" style="color: #23abf0">共 1 条记录 共 1 页 当前第 1 页</div>
                    <div id="barcon2" class="">
                        <ul class="nav nav-tabs">
                            <li><a href="###" id="firstPage" class="ban">首页</a></li>
                            <li><a href="###" id="prePage" class="ban">上一页</a></li>
                            <li><a href="###" id="nextPage" class="ban">下一页</a></li>
                            <li><a href="###" id="lastPage" class="ban">尾页</a></li>
                            <li style="padding: 10px 15px"><select id="jumpWhere">
                                <option value="1">1</option>
                            </select></li>
                            <li><a href="###" id="jumpPage" onclick="jumpPage()">跳转</a></li>
                        </ul>
                    </div>
                </div>
                <!--                分页end-->
            </div>
        </div>
    </div>
</div>

<script>
    //参加活动
    function joinA(vId,aId) {
        if(confirm('确定报名吗？')){
            $.post('${ctx}/volunteerController/joinActivity?vId='+vId+'&'+'aId='+aId);
            $.ajax({
                url:"${ctx}/volunteerController/toIndex",
                type:"GET"
            })
            location.reload();
        }
    }
    //退出活动
    function exitA(vId,aId) {
        if(confirm('确定取消报名？')){
            $.post('${ctx}/volunteerController/exitActivity?vId='+vId+'&'+'aId='+aId);
            $.ajax({
                url:"${ctx}/volunteerController/toIndex",
                type:"GET"
            })
            location.reload();
        }
    }
    //志愿者退出
    function exit(){
        if(confirm("确认退出吗？")){
            $.ajax({
                url:"${ctx}/volunteerController/exit",
               type:"GET"
            })
            location.reload();
        }
    }

    //分页逻辑start
    var pageSize = 10; //每页显示行数
    var currentPage_ = 1; //当前页全局变量，用于跳转时判断是否在相同页，在就不跳，否则跳转。
    var totalPage; //总页数
    function goPage(pno, psize) {
        undefined
        var itable = document.getElementById("adminTbody");
        var num = itable.rows.length; //表格所有行数(所有记录数)

        pageSize = psize; //每页显示行数
        //总共分几页
        if (num / pageSize > parseInt(num / pageSize)) {
            totalPage = parseInt(num / pageSize) + 1;
        } else {
            totalPage = parseInt(num / pageSize);
        }
        var currentPage = pno; //当前页数
        currentPage_ = currentPage;
        var startRow = (currentPage - 1) * pageSize + 1;
        var endRow = currentPage * pageSize;
        endRow = (endRow > num) ? num : endRow;

        $("#adminTbody tr").hide();
        for (var i = startRow - 1; i < endRow; i++) {
            undefined
            $("#adminTbody tr").eq(i).show();
        }
        var tempStr = "共 " + num + " 条记录        共 " + totalPage + " 页        当前第 " + currentPage + " 页";
        document.getElementById("barcon1").innerHTML = tempStr;

        if (currentPage > 1) {
            undefined
            $("#firstPage").on("click", function () {
                undefined
                goPage(1, psize);
            }).removeClass("ban");
            $("#prePage").on("click", function () {
                undefined
                goPage(currentPage - 1, psize);
            }).removeClass("ban");
        } else {
            undefined
            $("#firstPage").off("click").addClass("ban");
            $("#prePage").off("click").addClass("ban");
        }

        if (currentPage < totalPage) {
            undefined
            $("#nextPage").on("click", function () {
                undefined
                goPage(currentPage + 1, psize);
            }).removeClass("ban")
            $("#lastPage").on("click", function () {
                undefined
                goPage(totalPage, psize);
            }).removeClass("ban")
        } else {
            undefined
            $("#nextPage").off("click").addClass("ban");
            $("#lastPage").off("click").addClass("ban");
        }
        var tempOption = "";
        for (var i = 1; i <= totalPage; i++) {
            undefined
            tempOption += '<option value=' + i + '>' + i + '</option>'
        }
        $("#jumpWhere").html(tempOption);
        $("#jumpWhere").val(currentPage);
    }


    function jumpPage() {
        var num = parseInt($("#jumpWhere").val());
        if (num != currentPage_) {
            undefined
            goPage(num, pageSize);
        }
    }

    //分页逻辑end
    goPage(1, 10) //调用分页
</script>

</body>
</html>

