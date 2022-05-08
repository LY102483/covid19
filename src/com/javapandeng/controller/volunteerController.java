package com.javapandeng.controller;

import com.javapandeng.po.Volunteer;
import com.javapandeng.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Create by LiuYang on 2022/5/7 15:54
 */
@Controller
@RequestMapping("/volunteerController")
public class volunteerController {
    @Autowired
    private VolunteerService volunteerService;

    //登录页
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/login/vLogin";
    }
    //首页
    @RequestMapping("/toIndex")
    public String toIndex(HttpSession httpSession){
        Volunteer volunteer=(Volunteer) httpSession.getAttribute("volunteer");
        int volunteer_id=volunteer.getId();
        //进入首页前的准备工作
        httpSession.setAttribute("allActivities",volunteerService.getAllActivities(volunteer_id));
        return "/volunteer/index";
    }
    //登陆方法
    @RequestMapping("/loginVolunteer")
    public ModelAndView login(Volunteer loginVolunteer, HttpSession httpSession,ModelAndView modelAndView) throws Exception {
        Volunteer volunteer=volunteerService.loginVolunteer(loginVolunteer);
        if(volunteer!=null){
            httpSession.setAttribute("volunteer",volunteer);
            modelAndView.setViewName("redirect:/volunteerController/toIndex");
        }else {
            modelAndView.addObject("info","账号或密码不正确！！");
            modelAndView.setViewName("/login/vLogin");
        }
        return modelAndView;
    }
    //退出方法
    @RequestMapping("/exit")
    public boolean exit(HttpServletRequest request){
        request.getSession().setAttribute("volunteer",null);
        return true;
    }
    //志愿者注册
    @RequestMapping("/addVolunteer")
    public ModelAndView addVolunteer(Volunteer volunteer,ModelAndView modelAndView,HttpSession httpSession){
        boolean res=volunteerService.addVolunteer(volunteer);
        if(res){
            httpSession.setAttribute("volunteer",volunteer);
            modelAndView.setViewName("/volunteer/index");
        }else{
            modelAndView.addObject("info","当前手机号已注册，如有疑问，请联系管理员！");
            modelAndView.setViewName("redirect:/login/beVolunteer");
        }
        return modelAndView;
    }
    // 修改志愿者密码
    @RequestMapping("/updatePassword")
    public String updatePassword(String newPassword,HttpSession httpSession){
        Volunteer volunteer = (Volunteer) httpSession.getAttribute("volunteer");
        volunteerService.updatePassword(volunteer,newPassword);
        return "/login/vLogin";
    }

    // 修改志愿者联系方式和年龄
    @RequestMapping("/updateInfo")
    public String updateInfo(String newPhone,int newAge,HttpSession session){
        Volunteer volunteer=(Volunteer)session.getAttribute("volunteer");
        Integer id = volunteer.getId();
        volunteerService.updateInfo(newPhone,newAge,id);
        return "/login/vLogin";
    }

    // 删除志愿者(通过ID)
    @RequestMapping("/deleteVolunteer")
    public void deleteVolunteer(int id){
        volunteerService.deleteVolunteer(id);
    }

    //退出活动
    @RequestMapping("/exitActivity")
    public void exitActivity(int vId,int aId){
        volunteerService.exitActivity(vId,aId);
    }

    //加入活动
    @RequestMapping("/joinActivity")
    public String joinActivity(int vId,int aId,ModelAndView modelAndView){
        volunteerService.joinActivity(vId,aId);
        return "/volunteer/index";
    }




}
