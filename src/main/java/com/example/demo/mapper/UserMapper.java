package com.example.demo.mapper;


import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {

    @Select("select * from sys_user")
    List<User> finAll();

    @Select("INSERT INTO sys_user(username,password,nickname,email,phone,address) VALUES (#{username},#{password},"+
            "#{nickname},#{email},#{phone},#{address})")
    Integer insert(User user);


    Integer update(User user);
}
