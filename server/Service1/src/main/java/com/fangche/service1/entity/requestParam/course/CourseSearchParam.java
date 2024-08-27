package com.fangche.service1.entity.requestParam.course;

import lombok.Data;

@Data
public class CourseSearchParam {
    private int  page;
    private int  pageSize;
    private String name;
    private String category;
    private String teacher;
    private String keyword;
    private Long id;
}
