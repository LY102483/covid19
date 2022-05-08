package com.javapandeng.service;

import com.javapandeng.po.Activity;
import com.javapandeng.po.Volunteer;

import java.util.List;

/**
 * Create by LiuYang on 2022/5/7 20:37
 */
public interface VolunteerService {

    Volunteer loginVolunteer(Volunteer loginVolunteer);

    boolean addVolunteer(Volunteer volunteer);

    void updatePassword(Volunteer volunteer, String newPassword);

    void updateInfo(String newPhone, int newAge,int id);

    void deleteVolunteer(int id);

    //志愿者获取所有活动并检查是否报名
    List<Activity> getAllActivities(int volunteer_id);

    void exitActivity(int vId, int aId);

    void joinActivity(int vId, int aId);

    List<Volunteer> getAllVolunteer();

    void reSetPassword(int id);
}
