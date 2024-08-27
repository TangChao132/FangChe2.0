package com.fangche.service2.Pojo.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class VideoFileAddVo {
    private MultipartFile video;

    private String name;
}
