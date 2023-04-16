package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Course;
import com.example.demo.entity.Dot;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jahui
 * @since 2023-03-20
 */
public interface IDotService extends IService<Dot> {

    List<Dot> getByBrand(String brand);

    Page<Dot> findPage(Page<Dot> page, String brand);

    List<Dot> recall();

    Page<Dot> findPageLocal(Page<Dot> page, Integer localid);

    Integer getTireNum();
}
