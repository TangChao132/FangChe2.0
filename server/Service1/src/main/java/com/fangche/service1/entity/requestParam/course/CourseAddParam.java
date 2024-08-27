package com.fangche.service1.entity.requestParam.course;

import com.fangche.service1.entity.Chapter;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CourseAddParam {
    @NotBlank
    private String name;
    private String introduction;
    @NotBlank
    private List<Chapter> chapters;
    private String resources;
    @NotBlank
    private Long teacher_id;
    private String category;
}
