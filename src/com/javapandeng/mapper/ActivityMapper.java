package com.javapandeng.mapper;

import com.javapandeng.po.Activity;
import com.javapandeng.po.Volunteer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    // @Select("select * from activity where ")
    // List<Volunteer> selectJoinVolunteer(int id);
}
