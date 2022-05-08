package com.javapandeng.service.impl;

import com.javapandeng.mapper.ActivityMapper;
import com.javapandeng.mapper.VolunteerMapper;
import com.javapandeng.po.Activity;
import com.javapandeng.po.Volunteer;
import com.javapandeng.service.VolunteerService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Create by LiuYang on 2022/5/7 20:45
 */
@Service("volunteerService")
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    //志愿者登陆
    @Override
    public Volunteer loginVolunteer(Volunteer loginVolunteer){
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        VolunteerMapper mapper= sqlSession.getMapper(VolunteerMapper.class);
        Volunteer volunteer=mapper.loginVolunteer(loginVolunteer);
        return volunteer;
    }

    @Override
    public boolean addVolunteer(Volunteer volunteer) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        VolunteerMapper mapper= sqlSession.getMapper(VolunteerMapper.class);
        try {
            return mapper.addVolunteer(volunteer);
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void updatePassword(Volunteer volunteer, String newPassword) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        VolunteerMapper mapper= sqlSession.getMapper(VolunteerMapper.class);
        volunteer.setPassword(newPassword);
        mapper.updatePassword(volunteer);
    }

    @Override
    public void updateInfo(String newPhone, int newAge,int id) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        VolunteerMapper mapper= sqlSession.getMapper(VolunteerMapper.class);
        mapper.updateInfo(newPhone,newAge,id);
    }
    //删除志愿者的方法
    @Override
    public void deleteVolunteer(int id) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        VolunteerMapper vMapper= sqlSession.getMapper(VolunteerMapper.class);
        ActivityMapper aMapper= sqlSession.getMapper(ActivityMapper.class);
        System.out.println("进入了删除志愿者impl方法了");
        //删除关系表
        aMapper.deleteAllActivity(id);
        System.out.println("已经删除关系表了");
        //删除主表
        vMapper.deleteVolunteer(id);
    }

    //志愿者获取所有活动并检查是否报名
    @Override
    public List<Activity> getAllActivities(int volunteer_id) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        ActivityMapper aMapper= sqlSession.getMapper(ActivityMapper.class);
        VolunteerMapper vMapper= sqlSession.getMapper(VolunteerMapper.class);
        //获取所有活动
        List<Activity> res=aMapper.getAllActivity();
        //创建时间格式化对象并对时间格式化
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (Activity activity : res) {
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
        }
        //获取活动对应人的表格
        List<Integer> list = vMapper.checkJoin(volunteer_id);

        if(list.size()==0){
            return res;
        }else{
            for (Integer integer : list) {
                for (Activity activity : res) {
                    if(activity.getId()==(int)integer){
                        activity.setvState(1);
                        break;
                    }
                }
            }
        }
        return res;

    }

    @Override
    public void exitActivity(int vId, int aId) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        ActivityMapper aMapper= sqlSession.getMapper(ActivityMapper.class);
        aMapper.exitActivity(vId,aId);
    }

    @Override
    public void joinActivity(int vId, int aId) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        ActivityMapper aMapper= sqlSession.getMapper(ActivityMapper.class);
        aMapper.joinActivity(vId,aId);
    }

    @Override
    public List<Volunteer> getAllVolunteer() {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        VolunteerMapper mapper = sqlSession.getMapper(VolunteerMapper.class);
        return mapper.getAllVolunteer();
    }

    @Override
    public void reSetPassword(int id) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        VolunteerMapper mapper = sqlSession.getMapper(VolunteerMapper.class);
        mapper.reSetPassword(id);
    }


}
