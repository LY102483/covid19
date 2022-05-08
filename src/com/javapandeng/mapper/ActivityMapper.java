package com.javapandeng.mapper;

import com.javapandeng.po.Activity;
import com.javapandeng.po.Volunteer;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Create by LiuYang on 2022/5/7 20:35
 */
public interface ActivityMapper {
    @Select("select * from activity order by id desc")
    List<Activity> getAllActivity();

    @Insert("insert into activity values (#{id},#{activity},#{time},#{manage})")
    void addActivity(Activity activity);

    @Update("update activity set time=#{time} where id=#{id}")
    void updateActivity(int id, String time);


    @Delete("DELETE FROM activity WHERE id=#{id}")
    void deleteActivity(int id);

    //志愿者退出活动的方法;
    @Delete("DELETE FROM volu_activity WHERE volunteer_id=#{volunteer_id} and activity_id=#{activity_id}")
    void exitActivity(@Param("volunteer_id") int vId, @Param("activity_id") int aId);

    //志愿者参加活动的方法
    @Insert("insert into volu_activity values (null,#{volunteer_id},#{activity_id})")
    void joinActivity(@Param("volunteer_id")int vId,@Param("activity_id") int aId);

    //查询某个活动有哪些人参加
    @Select("select v.id,v.realname,v.sex,v.age,v.phone from volunteer v,volu_activity va where va.activity_id=#{volunteer_id} and v.id=va.volunteer_id")
    List<Volunteer> selectJoinVolunteer(@Param("volunteer_id") int id);

    //批量取消某个人参加的活动
    @Select("delete from volu_activity  where volunteer_id=#{volunteer_id}")
    List<Integer> deleteAllActivity(@Param("volunteer_id") int id);

}
