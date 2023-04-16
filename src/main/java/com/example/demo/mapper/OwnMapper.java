package com.example.demo.mapper;

import com.example.demo.entity.Own;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jahui
 * @since 2023-03-26
 */
public interface OwnMapper extends BaseMapper<Own> {

    Own getDot(@Param("dotid") Integer dotid);

    Integer getOwnerNum();
}
