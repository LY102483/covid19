package com.javapandeng.mapper;

import com.javapandeng.po.Volunteer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    void updateInfo(Volunteer volunteer);

    @Delete("DELETE FROM volunteer WHERE id=#{id}")
    void deleteVolunteer(int id);
}
