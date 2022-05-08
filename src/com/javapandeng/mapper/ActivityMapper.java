package com.javapandeng.mapper;

import com.javapandeng.po.Activity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Create by LiuYang on 2022/5/7 20:35
 */
public interface ActivityMapper {
    @Select("select * from activity order by id desc")
    List<Activity> getAllActivity();

    @Insert("insert into activity values (#{id},#{activity},#{time},#{manage})")
    void addActivity(Activity activity);
}
