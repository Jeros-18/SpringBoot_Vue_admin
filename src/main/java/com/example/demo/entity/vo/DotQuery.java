package com.example.demo.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DotQuery implements Serializable {
    private Integer id;

    private String brand;

    private String local;

    private String year;

}
