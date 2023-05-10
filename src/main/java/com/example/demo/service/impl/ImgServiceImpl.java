package com.example.demo.service.impl;

import com.example.demo.entity.Img;
import com.example.demo.mapper.ImgMapper;
import com.example.demo.service.IImgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jahui
 * @since 2023-05-07
 */
@Service
public class ImgServiceImpl extends ServiceImpl<ImgMapper, Img> implements IImgService {

    @Resource
    private ImgMapper imgMapper;
    @Override
    public Img findMaxTime() {
        return imgMapper.findMaxTime();
    }
}
