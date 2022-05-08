package com.javapandeng.controller;

import com.javapandeng.po.Activity;
import com.javapandeng.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Create by LiuYang on 2022/5/8 03:41
 */
@Controller
@RequestMapping("/activityController")
public class ActivityController {
    @Autowired
    ActivityService activityService;
    //获取活动列表
    @RequestMapping("/getAllActivity")
    public ModelAndView getAllActivity(ModelAndView modelAndView){
        List<Activity> allActivity = activityService.getAllActivity();
        if(allActivity.size()!=0){
            modelAndView.addObject("allActivity",allActivity);
        }else{
            modelAndView.addObject("allActivity",null);
        }
        modelAndView.setViewName("/activity/activity");
        return modelAndView;
    }

    //增加活动
    @RequestMapping("/addActivity")
    public String addActivity(String activity,String time,String manage) throws ParseException {
        //转换时间
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=format.parse(time);
        //设置活动
        Activity res=new Activity();
        res.setActivity(activity);
        res.setTime(date);
        res.setManage(manage);
        //数据处理
        activityService.addActivity(res);
        return "redirect:/activityController//getAllActivity";
    }
}
