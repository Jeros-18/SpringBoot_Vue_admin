package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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

    @TableField(exist = false)
    private String brandName;
    @TableField(exist = false)
    private String locationName;
    @TableField(exist = false)
    private String factoryName;

    private Integer year;

    @TableLogic
    private Boolean isDelete;

    private Boolean recall;


    private Integer localid;

    private Integer ownerId;
    private Integer brandId;
    private Integer factoryId;

    @TableField(exist = false)
    private String ownerName;

}
