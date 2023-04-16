package com.example.demo.service;

import com.example.demo.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jahui
 * @since 2023-03-25
 */
public interface IBrandService extends IService<Brand> {

    Integer getBrandNum();
}
