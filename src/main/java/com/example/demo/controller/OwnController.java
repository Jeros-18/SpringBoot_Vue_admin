package com.example.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;

import com.example.demo.service.IOwnService;
import com.example.demo.entity.Own;

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
@RequestMapping("/own")
public class OwnController {

    @Resource
    private IOwnService ownService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Own own) {
        ownService.saveOrUpdate(own);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        ownService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        ownService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(ownService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(ownService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<Own> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(ownService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
    @Transactional
    @GetMapping("/getDot/{dotid}")
    public Result getDot(@RequestParam(required = false) Integer dotid){

        return Result.success(ownService.getDot(dotid));
    }

    @GetMapping("/getOwnerNum")
    public Result getOwnerNum(){
        return Result.success(ownService.getOwnerNum());
    }
}

