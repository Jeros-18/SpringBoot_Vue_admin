package com.example.demo.service;

import com.example.demo.entity.Img;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jahui
 * @since 2023-05-07
 */
public interface IImgService extends IService<Img> {

    List<Img> findMaxTime();
}
