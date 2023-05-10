package com.example.demo.service.impl;

import com.example.demo.entity.Camera;
import com.example.demo.mapper.CameraMapper;
import com.example.demo.service.ICameraService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jahui
 * @since 2023-05-09
 */
@Service
public class CameraServiceImpl extends ServiceImpl<CameraMapper, Camera> implements ICameraService {

}
