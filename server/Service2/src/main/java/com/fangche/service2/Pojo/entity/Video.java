package com.fangche.service2.Pojo.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Table
@Setter
@Getter
public class Video {
    //主键id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    //视频名称
    private String name;
    //资源路径
    private String url;
    //视频时长
    private Long timeLength;
    //视频点击量
    private Long hits;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime releaseTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
