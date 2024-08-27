package com.fangche.service1.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.tangzc.mpe.autotable.annotation.Column;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Table
@Data
public class CourseCollection extends BaseEntity{
    @JsonSerialize(using = ToStringSerializer.class)
    private  Long uid;
    @Column(length = 2048)
    private  String courses;
}
