package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author jahui
 * @since 2023-03-20
 */
@Getter
@Setter
  @ApiModel(value = "Dot对象", description = "")
public class Dot implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String img;

    private String brand;

    private String local;

    private String fact;

    private Integer year;

    @TableLogic
    private Boolean isDelete;

}
