package com.javapandeng.service;

import com.javapandeng.po.Activity;
import com.javapandeng.po.Volunteer;

import java.util.List;

/**
 * Create by LiuYang on 2022/5/8 03:46
 */
public interface ActivityService {
    List<Activity> getAllActivity();

    void addActivity(Activity activity);

    void updateActivity(int id, String time);

    void deleteActivity(int id);

    List<Volunteer> selectJoinVolunteer(int id);
}
