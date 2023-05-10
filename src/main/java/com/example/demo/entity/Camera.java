package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
 * @since 2023-05-09
 */
@Getter
@Setter
  @TableName("img_camera")
@ApiModel(value = "Camera对象", description = "")
public class Camera implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String name;

    private String yuan;

    private byte[] part;

    private String kuang;

    private String recog;

    private LocalDateTime ctime;

    private Boolean isRecog;

    @TableLogic
    private Boolean isDelete;


}
