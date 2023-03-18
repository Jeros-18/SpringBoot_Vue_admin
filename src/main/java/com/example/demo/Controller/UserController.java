package com.example.demo.Controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "歌曲管理")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> index(){
        List<User> all = userMapper.finAll();
        return all;
    }

    @PostMapping
    public int save(@RequestBody User user){
        // 新增或更新
        return userService.save(user);
    }


}
