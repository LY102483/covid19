<%@page language="java" contentType="text/html; character=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>志愿者登录</title>
    <link type="text/css" rel="stylesheet" href="${ctx}/resource/user/css/style.css">
    <script src="${ctx}/resource/user/js/jquery-1.8.3.min.js"></script>
    <script src="${ctx}/resource/user/js/jquery.luara.0.0.1.min.js"></script>
</head>
<body>
<div class="width100 hidden_yh" style="border-top: 1px solid #ddd">
    <div class="width1200 hidden_yh center_yh" style="margin-top: 75px">
        <form action="${ctx}/volunteerController/loginVolunteer" method="post">
            <h3 class="tcenter font30 c_33" style="font-weight: 100;margin-top: 36px;margin-bottom: 36px;">志愿者登录</h3>
            <div class="center_yh hidden_yh" style="width: 336px;">
                <div class="width100 box-sizing hidden_yh"
                     style="height: 44px;border:1px solid #c9c9c9;margin-bottom: 34px;">
                    <img src="${ctx}/resource/user/images/rw.jpg" alt="" class="left_yh" width="42" height="42">
                    <input type="text" placeholder="请输入手机号" name="phone" value=""
                           style="border: 0;width: 292px;height: 42px; font-size: 16px;text-indent: 22px;">
                </div>
                <div class="width100 box-sizing hidden_yh"
                     style="height: 44px;border:1px solid #c9c9c9;margin-bottom: 34px;">
                    <img src="${ctx}/resource/user/images/pass.jpg" alt="" class="left_yh" width="42" height="42">
                    <input type="password" placeholder="请输入密码" name="password" value=""
                           style="border: 0;width: 292px;height: 42px; font-size: 16px;text-indent: 22px;">
                </div>
                <input type="submit" value="登录" class="center_yh"
                       style="width: 100%;height: 43px;font-size: 16px;background: #dd4545;outline: none;border: 0;color: #fff; cursor:pointer;">
                <div class="width100 box-sizing hidden_yh" style="display: flex;justify-content: center">
                    <p style="color: red;margin-top: 10px">${info}</p>
                </div>
                <div class="width100 box-sizing hidden_yh" style="display: flex;justify-content: center">
                    <a href="${ctx}/login/beVolunteer" style="color: #00a0e9;margin-top: 20px">成为志愿者</a>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
