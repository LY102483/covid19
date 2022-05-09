<%--
  Created by IntelliJ IDEA.
  User: liuyang
  Date: 2022/5/8
  Time: 04:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入jstl标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
<%--    bootstarp样式文件--%>
    <script src="${ctx}/resource/jquery/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/bootstrap-3.4.1/css/bootstrap.min.css">
    <script src="${ctx}/resource/bootstrap-3.4.1/js/bootstrap.min.js"></script>
<%--    时间文件--%>
    <script src="${ctx}/resource/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.css">
    <script src="${ctx}/resource/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
</head>
<body>
<div class="container-fluid" >
    <div class="row">
        <div class="col-md-12 panel panel-primary" style="padding: 0">
            <div class="panel-heading" style="display: flex;justify-content: space-between;background-color: ">
                <h3 class="panel-title" style="align-self: center">志愿者管理</h3>
            </div>
            <div class="panel-body">
                <table id="myTable" class='table table-striped'>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>年龄</th>
                        <th>电话</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="adminTbody">
                    <c:forEach items="${allVolunteer}" var="volunteer">
                    <tr>
                        <td>${volunteer.id}</td>
                        <td>${volunteer.realname}</td>
                        <td>${volunteer.sex}</td>
                        <td>${volunteer.age}</td>
                        <td>${volunteer.phone}</td>
                        <td>
                            <button
                                    class='btn btn-info'
                                    onclick="updatePassword(${volunteer.id})">重置密码
                            </button>
                            <button
                                    class='btn btn-danger'
                                    onclick="deleteV(${volunteer.id})">删除
                            </button>
                        </td>

                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <!--                分页start-->
                <div id="barcon" class="" style="display: flex;align-items: center;justify-content: space-between">
                    <div id="barcon1" class="barcon1" style="color: #23abf0"></div>
                    <div id="barcon2" class="">

                        <ul class="nav nav-tabs">
                            <li><a href="###" id="firstPage">首页</a></li>
                            <li><a href="###" id="prePage">上一页</a></li>
                            <li><a href="###" id="nextPage">下一页</a></li>
                            <li><a href="###" id="lastPage">尾页</a></li>
                            <li style="padding: 10px 15px"><select id="jumpWhere">
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
    //重置密码
    function updatePassword(id){
        if(confirm("密码将会被重置为123456！！\n确定吗")){
            $.ajax({
                url:"${ctx}/volunteerSysController/reSetPassword?id="+id,
                method:"POST",

            })
            alert("重置成功")
        }
    }

    //删除
    function deleteV(id){
        if(confirm("确定删除吗？")){
            $.ajax({
                url:"${ctx}/volunteerSysController/deleteVolunteer?id="+id,
                method:"POST",
            })
            setTimeout(function(){
                alert("删除成功");
                location.reload();
            },3000);



        }
    }


    //时间选择器
    var date = new Date();
    $('#add').datetimepicker({
        forceParse: 0,//设置为0，时间不会跳转1899，会显示当前时间。
        language: 'zh-CN',//显示中文
        format: 'yyyy-mm-dd hh:ii',//显示格式
        minView: 0,//设置只显示到月份
        initialDate: date,//初始化当前日期
        autoclose: true,//选中自动关闭
        todayBtn: true,//显示今日按钮
        startDate: date
    })
    $("#add").datetimepicker("setDate", new Date() );  //设置显示默认当天的时间


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
