package com.example.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;

import com.example.demo.service.IFactoryService;
import com.example.demo.entity.Factory;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jahui
 * @since 2023-04-16
 */
@RestController
@RequestMapping("/factory")
public class FactoryController {

    @Resource
    private IFactoryService factoryService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Factory factory) {
        factoryService.saveOrUpdate(factory);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        factoryService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        factoryService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(factoryService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(factoryService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<Factory> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(factoryService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

