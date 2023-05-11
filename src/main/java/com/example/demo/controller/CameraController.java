package com.example.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;

import com.example.demo.service.ICameraService;
import com.example.demo.entity.Camera;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jahui
 * @since 2023-05-09
 */
@RestController
@RequestMapping("/camera")
public class CameraController {

    @Resource
    private ICameraService cameraService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Camera camera) {
        cameraService.saveOrUpdate(camera);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        cameraService.removeById(id);
        return Result.success();
    }

    @DeleteMapping("/deleteAll")
    public Result deleteAll(){
        cameraService.remove(null);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        cameraService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(cameraService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(cameraService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<Camera> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(cameraService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

