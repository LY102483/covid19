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
    public void updateInfo(Volunteer volunteer, String newPhone, int newAge) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        VolunteerMapper mapper= sqlSession.getMapper(VolunteerMapper.class);
        volunteer.setPhone(newPhone);
        volunteer.setAge(newAge);
        mapper.updateInfo(volunteer);
    }

    @Override
    public void deleteVolunteer(int id) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        VolunteerMapper mapper= sqlSession.getMapper(VolunteerMapper.class);
        mapper.deleteVolunteer(id);
    }

    //志愿者获取所有活动并检查是否报名
    @Override
    public List<Activity> getAllActivities(int id) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        ActivityMapper aMapper= sqlSession.getMapper(ActivityMapper.class);
        VolunteerMapper vMapper= sqlSession.getMapper(VolunteerMapper.class);
        //获取所有活动
        List<Activity> res=aMapper.getAllActivity();
        //遍历每个活动，然后查询数据库看参与状态
        for (Activity activity : res) {
            if(vMapper.checkJoin(id,activity.getId())!=0){
                activity.setvState(1);
            }else{
                activity.setvState(0);
            }
        }
        return res;
    }

}
