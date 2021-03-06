<%@page language="java" contentType="text/html; character=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>管理员后台</title>
    <link rel="stylesheet" href="${ctx}/resource/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/resource/css/admin.css">
    <script src="${ctx}/resource/js/jquery.js"></script>
    <script src="${ctx}/resource/js/pintuer.js"></script>
<%--    添加--%>

    <%--    bootstarp样式文件--%>
    <script src="${ctx}/resource/jquery/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/bootstrap-3.4.1/css/bootstrap.min.css">
    <script src="${ctx}/resource/bootstrap-3.4.1/js/bootstrap.min.js"></script>
    <%--    时间文件--%>
    <script src="${ctx}/resource/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.css">
    <script src="${ctx}/resource/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

</head>

<body style="background-color: #f2f9fd">
<div class="header bg-main">
    <div class="logo margin-big-left fadein-top">
        <h1>管理员后台</h1>
    </div>
    <div class="head-l">
        <a class="button button-little bg-green" href="${ctx}/login/uIndex" target="_blank">
            <span class="icon-home"></span>前台首页
        </a>
        <a class="button button-little bg-red" href="mtuichu.html">
            <span class="icon-power-off"></span>退出登录
        </a>
    </div>
</div>
<div class="leftnav">
    <div class="leftnav-title"><strong><span class="icon-list">菜单列表</span> </strong></div>
    <h2><span class="icon-user"></span>基本设置 </h2>
    <ul style="display: block">
        <li><a href="${ctx}/activity/activity" target="right"><span class="icon-caret-right"></span>用户管理</a></li>
        <li><a href="${ctx}/volunteerSysController/getAllVolunteer" target="right"><span class="icon-caret-right"></span>志愿者管理</a></li>
        <li><a href="${ctx}/activityController/getAllActivity" target="right"><span class="icon-caret-right"></span>志愿活动管理</a></li>
        <li><a href="${ctx}/itemCategory/findBySql" target="right"><span class="icon-caret-right"></span>类目管理</a></li>
        <li><a href="${ctx}/item/findBySql" target="right"><span class="icon-caret-right"></span>商品管理</a></li>
        <li><a href="${ctx}/itemOrder/findBySql" target="right"><span class="icon-caret-right"></span>订单管理</a></li>
        <li><a href="${ctx}/news/findBySql" target="right"><span class="icon-caret-right"></span>公告管理</a></li>
        <li><a href="${ctx}/message/findBySql" target="right"><span class="icon-caret-right"></span>留言管理</a></li>
    </ul>
</div>
<ul class="bread">
    <li><a href="{:}" target="right" class="icon-home">欢迎来到便民服务平台管理员首页</a></li>
</ul>
<div class="admin">
    <iframe scrolling="auto" rameborder="0" src="${ctx}/itemCategory/tj" name="right" width="100%"
            height="100%"></iframe>

</div>


</body>
</html>