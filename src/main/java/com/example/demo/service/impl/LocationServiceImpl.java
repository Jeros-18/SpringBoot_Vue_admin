package com.example.demo.service.impl;

import com.example.demo.entity.Location;
import com.example.demo.mapper.LocationMapper;
import com.example.demo.service.ILocationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jahui
 * @since 2023-03-26
 */
@Service
public class LocationServiceImpl extends ServiceImpl<LocationMapper, Location> implements ILocationService {

}
