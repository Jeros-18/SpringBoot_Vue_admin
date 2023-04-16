package com.example.demo.service.impl;

import com.example.demo.entity.Own;
import com.example.demo.mapper.OwnMapper;
import com.example.demo.service.IOwnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jahui
 * @since 2023-03-26
 */
@Service
public class OwnServiceImpl extends ServiceImpl<OwnMapper, Own> implements IOwnService {
    @Resource
    private OwnMapper ownMapper;

    @Override
    public Own getDot(Integer dotid) {
        return ownMapper.getDot(dotid);
    }

    @Override
    public Integer getOwnerNum() {
        return ownMapper.getOwnerNum();

    }
}
