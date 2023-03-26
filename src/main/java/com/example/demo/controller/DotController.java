package com.example.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Course;
import com.example.demo.entity.vo.DotQuery;
import com.example.demo.mapper.DotMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
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

    @Autowired
    private DotMapper dotMapper;

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

//    @GetMapping("/page")
//    public Result findPage(@RequestParam String img,
//            @RequestParam Integer pageNum,
//                                @RequestParam Integer pageSize) {
//        QueryWrapper<Dot> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByDesc("id");
//        return Result.success(dotService.page(new Page<>(pageNum, pageSize), queryWrapper));
//
//    }

    @GetMapping("/page")
    public Result findPage(@RequestParam String brand,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {

        Page<Dot> page = dotService.findPage(new Page<>(pageNum, pageSize), brand);
        return Result.success(page);
    }
//    @GetMapping("/brand2")
//    public Result getByBrand2() {
//        QueryWrapper<Object> wrapper = new QueryWrapper<>();
//        wrapper.eq("brand","米其林");
//        dotService.getBaseMapper(wrapper);
//
//    }

    @Transactional
    @GetMapping("/pageLocal")
    public Result findPage2(@RequestParam(required = false) Integer localid,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {

        Page<Dot> page = dotService.findPageLocal(new Page<>(pageNum, pageSize), localid);
        return Result.success(page);
    }



    @GetMapping("/brand/{brand}")
    public Result getByBrand(@PathVariable String brand) {
        System.out.println(dotService.getByBrand(brand));
        return Result.success(dotService.getByBrand(brand));

    }

    @GetMapping("/recall")
    public Result recall() {

        return Result.success(dotService.recall());

    }


    @GetMapping("/getBrand")
    public Result findBrand(@RequestBody DotQuery dotQuery){
        String brand = dotQuery.getBrand();

        QueryWrapper<Dot> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(brand)) {
            wrapper.eq("brand", brand);
        }
        Page<Dot> page = new Page<>(1,4);
        dotService.page(page,wrapper);

        return Result.success(dotService.page(new Page<>(1, 4), wrapper));
    }



}

