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
public class History {
    //主键
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    //用户id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long uid;
    //套题类型
    private String type;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime submitTime;
}
