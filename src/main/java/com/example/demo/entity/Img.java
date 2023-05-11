package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author jahui
 * @since 2023-05-07
 */
@Getter
@Setter
  @ApiModel(value = "Img对象", description = "")
public class Img implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String imgname;
    private String yuan;

    private String kuang;

    private String part;

    private String recog;

    private String ryear;

    private LocalDateTime ctime;

    private Boolean isRecog;

    @TableLogic
    private Boolean isDelete;


}
