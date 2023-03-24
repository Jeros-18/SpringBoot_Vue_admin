package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Dot;
import com.example.demo.mapper.DotMapper;
import com.example.demo.service.IDotService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jahui
 * @since 2023-03-20
 */
@Service
public class DotServiceImpl extends ServiceImpl<DotMapper, Dot> implements IDotService {

//    @Resource
//    private DotMapper dotMapper;
//
//    @Override
//    public Page<Dot> findPage(Page<Dot> page, String name) {
//        return dotMapper.findPage(page,name);
//    }
}
