package com.javapandeng.service;

import com.javapandeng.po.Volunteer;

/**
 * Create by LiuYang on 2022/5/7 20:37
 */
public interface VolunteerService {

    Volunteer loginVolunteer(Volunteer loginVolunteer);

    boolean addVolunteer(Volunteer volunteer);

    void updatePassword(Volunteer volunteer, String newPassword);

    void updateInfo(Volunteer volunteer, String newPhone, int newAge);

    void deleteVolunteer(int id);
}
