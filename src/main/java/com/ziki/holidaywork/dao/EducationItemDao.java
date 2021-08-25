package com.ziki.holidaywork.dao;

import com.ziki.holidaywork.entity.EducationItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EducationItemDao {

    @Insert("INSERT INTO educationItem (date, type, content) VALUES(#{date}, #{type}, #{content})")
    int addUserData (@Param("date") String date, @Param("type") String type, @Param("content") String content);

    @Select("SELECT * FROM educationItem")
    List<EducationItem> getList();

    @Delete("DELETE FROM educationItem WHERE type = #{type} AND content = #{content} AND date = #{date}")
    int deleteEducationItem( @Param("type") String type, @Param("content") String content, @Param("date") String date);

    @Update("UPDATE educationItem SET type = #{newType}, content = #{newContent}, date = #{newDate} " +
            "WHERE type = #{oldType} AND content = #{oldContent} AND date = #{oldDate}")
    int updateEducationItem( @Param("newType") String newType, @Param("newContent") String newContent, @Param("newDate")
            String newDate, @Param("oldType") String oldType, @Param("oldContent") String oldContent, @Param("oldDate")
            String oldDate);

}
