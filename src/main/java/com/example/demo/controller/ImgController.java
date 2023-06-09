package com.example.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;

import com.example.demo.service.IImgService;
import com.example.demo.entity.Img;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jahui
 * @since 2023-05-07
 */
@RestController
@RequestMapping("/img")
public class ImgController {

    @Resource
    private IImgService imgService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Img img) {
        imgService.saveOrUpdate(img);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        imgService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        imgService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(imgService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(imgService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<Img> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(imgService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @GetMapping("/time")
    public Result findMaxTime() {
//        LocalDateTime dateTime = LocalDateTime.now(); // get the current date and time
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        String time = dateTime.format(formatter);
//        System.out.println(time);

        return Result.success(imgService.findMaxTime());
    }
}

