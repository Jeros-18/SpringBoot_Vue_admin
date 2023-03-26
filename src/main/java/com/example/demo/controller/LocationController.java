package com.example.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;

import com.example.demo.service.ILocationService;
import com.example.demo.entity.Location;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jahui
 * @since 2023-03-26
 */
@RestController
@RequestMapping("/location")
public class LocationController {

    @Resource
    private ILocationService locationService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Location location) {
        locationService.saveOrUpdate(location);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        locationService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        locationService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(locationService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(locationService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<Location> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(locationService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

