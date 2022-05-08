package com.javapandeng.po;

import java.util.Date;

/**
 * Create by LiuYang on 2022/5/7 20:29
 */
public class Activity {
    private Integer id;
    private String activity;
    private Date time;
    private String manage;
    private Volunteer volunteer;
    //时间传给前端是格式化字符串
    private String date;
    //对于志愿者而言该活动的状态
    private int vState;// 0表示未参加，1表示已参加
    //该活动是否已过期
    private int sState;// 0表示未过期，1表示已过期

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getvState() {
        return vState;
    }

    public void setvState(int vState) {
        this.vState = vState;
    }

    public int getsState() {
        return sState;
    }

    public void setsState(int sState) {
        this.sState = sState;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activity='" + activity + '\'' +
                ", time=" + time +
                ", manage='" + manage + '\'' +
                ", volunteer=" + volunteer +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getManage() {
        return manage;
    }

    public void setManage(String manage) {
        this.manage = manage;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }
}
