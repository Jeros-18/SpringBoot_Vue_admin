package com.example.demo.service.impl;

import com.example.demo.entity.Brand;
import com.example.demo.mapper.BrandMapper;
import com.example.demo.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jahui
 * @since 2023-03-25
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

    @Resource
    private BrandMapper brandMapper;

    @Override
    public Integer getBrandNum() {
        return brandMapper.getBrandNum();
    }
}
