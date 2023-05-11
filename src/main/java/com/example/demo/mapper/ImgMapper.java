package com.example.demo.mapper;

import com.example.demo.entity.Img;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jahui
 * @since 2023-05-07
 */
public interface ImgMapper extends BaseMapper<Img> {
    List<Img> findMaxTime();

    Img save(Img img);
}
