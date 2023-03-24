package com.example.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;

import com.example.demo.service.IDotService;
import com.example.demo.entity.Dot;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jahui
 * @since 2023-03-20
 */
@RestController
@RequestMapping("/dot")
public class DotController {

    @Resource
    private IDotService dotService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Dot dot) {
        dotService.saveOrUpdate(dot);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        dotService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        dotService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(dotService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(dotService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam String img,
            @RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<Dot> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(dotService.page(new Page<>(pageNum, pageSize), queryWrapper));

    }

}

