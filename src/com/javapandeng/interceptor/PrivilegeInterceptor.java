package com.javapandeng.interceptor;

import com.javapandeng.po.Volunteer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Create by LiuYang on 2022/5/6 00:40
 */
public class PrivilegeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //取得session
        HttpSession httpSession = request.getSession();
        //获取session中的volunteer
        Volunteer volunteer = (Volunteer) httpSession.getAttribute("volunteer");
        // 如果volunteer为空则重定向
        if (volunteer == null) {

            response.sendRedirect(request.getContextPath() + "/login/toVLogin");
            return false;
        }
        // 放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
