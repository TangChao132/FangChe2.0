package com.fangche.service1.entity.requestParam.course;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CourseSetImageParam {
    private MultipartFile file;
    private Long id;
}
