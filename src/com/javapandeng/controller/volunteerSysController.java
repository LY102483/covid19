package com.javapandeng.controller;

import com.javapandeng.po.Volunteer;
import com.javapandeng.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Create by LiuYang on 2022/5/8 23:07
 */
@Controller
@RequestMapping("/volunteerSysController")
public class volunteerSysController {

    @Autowired
    private VolunteerService volunteerService;

    //管理
    //获取所有志愿者
    @RequestMapping("/getAllVolunteer")
    public ModelAndView getAllVolunteer(ModelAndView modelAndView){
        List<Volunteer> allVolunteer = volunteerService.getAllVolunteer();
        modelAndView.addObject("allVolunteer",allVolunteer);
        modelAndView.setViewName("/volunteer/system");
        return modelAndView;
    }
    @RequestMapping("/reSetPassword")
    public void reSetPassword(int id){
        System.out.println("进入到了重置密码的方法");
        volunteerService.reSetPassword(id);
    }

    // 删除志愿者(通过ID)
    @RequestMapping("/deleteVolunteer")
    public void deleteVolunteer(int id){
        volunteerService.deleteVolunteer(id);
    }

}
