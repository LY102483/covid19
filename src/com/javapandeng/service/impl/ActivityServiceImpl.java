package com.javapandeng.service.impl;

import com.javapandeng.mapper.ActivityMapper;
import com.javapandeng.po.Activity;
import com.javapandeng.po.Volunteer;
import com.javapandeng.service.ActivityService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Create by LiuYang on 2022/5/8 03:46
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    SqlSessionFactory sqlSessionFactory;


    //获得所有活动列表
    @Override
    public List<Activity> getAllActivity() {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        ActivityMapper mapper= sqlSession.getMapper(ActivityMapper.class);
        List<Activity> activities=mapper.getAllActivity();//获取结果
        //创建时间格式化对象并对时间格式化
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (Activity activity : activities) {
            activity.setDate(simpleDateFormat.format(activity.getTime()));
            //获取本地时间
            Date date=new Date();
            //判断活动是否过期
            if(date.after(activity.getTime())){
                //已过期
                activity.setsState(1);
            }else{
                activity.setsState(0);
            }
            //判断活动是否参加
        }
        return activities;
    }

    @Override
    public void addActivity(Activity activity) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        ActivityMapper mapper= sqlSession.getMapper(ActivityMapper.class);
        mapper.addActivity(activity);
    }

    @Override
    public void updateActivity(int id, String time) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        ActivityMapper mapper= sqlSession.getMapper(ActivityMapper.class);
        mapper.updateActivity(id,time);
    }

    @Override
    public void deleteActivity(int id) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        ActivityMapper mapper= sqlSession.getMapper(ActivityMapper.class);
        mapper.deleteActivity(id);
    }

    @Override
    public List<Volunteer> selectJoinVolunteer(int id) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        ActivityMapper mapper= sqlSession.getMapper(ActivityMapper.class);
        // return mapper.selectJoinVolunteer(id);
        return null;
    }
}
