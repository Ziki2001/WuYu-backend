package com.ziki.holidaywork.dao;

import com.ziki.holidaywork.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
    @Select("SELECT * FROM user WHERE user = #{username}")
    User getUserByUsername(@Param("username") String username);

    @Insert("INSERT INTO user (user, password, aca, name, class, num) VALUES(#{user}, #{password}, #{aca}," +
            "#{name}, #{clas}, #{num} )")
    int addUser(@Param("user") String user, @Param("password") String password, @Param("aca") String aca,
                       @Param("name") String name, @Param("clas") String clas, @Param("num") String num);
}

