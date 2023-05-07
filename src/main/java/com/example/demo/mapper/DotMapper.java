package com.example.demo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Course;
import com.example.demo.entity.Dot;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jahui
 * @since 2023-03-20
 */
public interface DotMapper extends BaseMapper<Dot> {


   List<Dot> getByBrand(@Param("brand") String brand);

   Page<Dot> findPage(Page<Dot> page, @Param("brand") String brand);

   List<Dot> recall();

    Page<Dot> findPageLocal(Page<Dot> page, @Param("localid") Integer localid);

    Integer getTireNum();


    List<Map<String, Integer>> getBrandPie();
}
