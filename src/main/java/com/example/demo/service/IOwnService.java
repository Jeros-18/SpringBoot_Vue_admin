package com.example.demo.service;

import com.example.demo.entity.Own;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jahui
 * @since 2023-03-26
 */
public interface IOwnService extends IService<Own> {

    Own getDot(Integer dotid);

    Integer getOwnerNum();
}
