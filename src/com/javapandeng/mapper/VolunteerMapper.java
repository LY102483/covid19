package com.javapandeng.mapper;

import com.javapandeng.po.Volunteer;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Create by LiuYang on 2022/5/7 20:11
 */
public interface VolunteerMapper {
    // 志愿者登陆(通过手机号)
    @Select("select * from volunteer where phone=#{phone} and password=#{password}")
    Volunteer loginVolunteer(Volunteer loginVolunteer);

    @Insert("insert into volunteer values (#{id},#{vname},#{password},#{realname},#{sex},#{age},#{phone},#{address})")
    boolean addVolunteer(Volunteer volunteer) throws Exception;

    @Update(" update volunteer set password=#{password} where id=#{id}")
    void updatePassword(Volunteer volunteer);

    @Update(" update volunteer set phone=#{phone},age=#{age} where id=#{id}")
    void updateInfo(@Param("phone") String newPhone, @Param("age")int newAge,@Param("id")int id);

    @Delete("DELETE FROM volunteer WHERE id=#{id}")
    void deleteVolunteer(int id);

    //志愿者查询该活动有没有报名
    @Select("select activity_id from volu_activity where volunteer_id=#{volunteer_id}")
    List<Integer> checkJoin(@Param("volunteer_id") int volunteer_id);
    //查询所有志愿者
    @Select("select * from volunteer")
    List<Volunteer> getAllVolunteer();
    //重置志愿者密码
    @Update("update volunteer set password='123456' where id=#{id}")
    void reSetPassword(int id);
}
